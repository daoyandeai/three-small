package com.rs.service;

import com.rs.dao.ICompanyDao;
import com.rs.dao.IFoodSourceDao;
import com.rs.dao.IFoodSourceDetailDao;
import com.rs.dao.IPatrolDao;
import com.rs.dao.IRegionDao;
import com.rs.dao.ISensorDao;
import com.rs.dao.ISensorLogDao;
import com.rs.dao.IUserDao;
import com.rs.po.Company;
import com.rs.po.FoodSource;
import com.rs.po.FoodSourceDetail;
import com.rs.po.Patrol;
import com.rs.po.Region;
import com.rs.po.Sensor;
import com.rs.po.SensorLog;
import com.rs.po.User;
import com.rs.po.returnPo.BigData;
import com.rs.po.returnPo.CompanyReturn;
import com.rs.po.returnPo.FoodSourceDetailReturn;
import com.rs.po.returnPo.FoodSourceReturn;
import com.rs.util.CalcUtil;
import com.rs.util.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: bigDataService
 * @Description: 大数据统计服务层
 * @Author tangsh
 * @DateTime 2020年1月9日 下午3:32:04
 */
@Service
public class BigDataService {
    @Autowired
    private ICompanyDao companyDao;
    @Autowired
    private IUserDao userDao;
    @Autowired
	private CompanyService companyService;
    @Autowired
    private ISensorDao sensorDao;
    @Autowired
    private ISensorLogDao sensorLogDao;
    @Autowired
    private IRegionDao regionDao;
    @Autowired
    private IPatrolDao patrolDao;
    @Autowired
    private IFoodSourceDao foodSourceDao;
    @Autowired
    private IFoodSourceDetailDao foodSourceDetailDao;
    
    /**
     * 
     * @Title: getUserTypeCount
     * @Description: 根据用户 /企业类型统计数量
     * @Author tangsh
     * @DateTime 2020年3月26日 上午9:55:12
     * @param user
     * @return
     */
    public List<BigData> getUserTypeCount(User user) {
    	List<BigData> bdlist= new ArrayList<BigData>();
        User _seachuser=new User();
        _seachuser.setUser_province(user.getUser_province());
        _seachuser.setUser_city(user.getUser_city());
        _seachuser.setUser_area(user.getUser_area());
        _seachuser.setUser_town(user.getUser_town());
        _seachuser.setUser_vill(user.getUser_vill());
        _seachuser.setUser_state(1);
        _seachuser.setReturncode("2");
        //统计管理员人员
        _seachuser.setUser_type("平台管理员");
        BigData bd=new BigData();
        bd.setSname("管理人员");
        bd.setSvalue(userDao.findByCount(_seachuser)+"");
        bdlist.add(bd);
        //协管员
        _seachuser.setUser_type("协管员");
        BigData bd1=new BigData();
        bd1.setSname("网格员");
        bd1.setSvalue(userDao.findByCount(_seachuser)+"");
        bdlist.add(bd1);
        //食品生成
        Company c=new Company();
        c.setIsblacklist(1);
        c.setFiling_state(3);
        c.setCompanytype_code("1001574645421581000000001");
        BigData bd2=new BigData();
        bd2.setSname("食品生产");
        bd2.setSvalue(companyService.findByUserCompanyCount(c, user)+"");
        bdlist.add(bd2);
        c.setCompanytype_code("1001574645421581000000002");
        BigData bd3=new BigData();
        bd3.setSname("食品流通");
        bd3.setSvalue(companyService.findByUserCompanyCount(c, user)+"");
        bdlist.add(bd3);
        c.setCompanytype_code("1001574645421581000000003");
        BigData bd4=new BigData();
        bd4.setSname("食品餐饮");
        bd4.setSvalue(companyService.findByUserCompanyCount(c, user)+"");
        bdlist.add(bd4);
        //三小管理部门
       /* Department _d=new Department();
        _d.setProvince(user.getUser_province());
        _d.setCity(user.getUser_city());
        _d.setArea(user.getUser_area());
        _d.setTown(user.getUser_town());
        _d.setVill(user.getUser_vill());
        _d.setState(1);
        BigData bd2=new BigData();
        bd2.setSname("管理部门");
        bd2.setSvalue(departmentDao.findByCount(_d)+"");
        bdlist.add(bd2);
        //三小企业
        Company c=new Company();
        c.setProvince(user.getUser_province());
        c.setCity(user.getUser_city());
        c.setArea(user.getUser_area());
        c.setTown(user.getUser_town());
        c.setVill(user.getUser_vill());
        c.setFiling_state(3);
        BigData bd3=new BigData();
        bd3.setSname("三小企业");
        bd3.setSvalue(companyDao.findByCount(c)+"");
        bdlist.add(bd3);
        //从业人员
        BigData bd4=new BigData();
        bd4.setSname("从业人员");
        bd4.setSvalue(companyDao.findEmployByCompanyCount(c)+"");
        bdlist.add(bd4);*/
       return bdlist;
    }
    
    /**
     * 
     * @Title: cpList
     * @Description: 备案巡查统计
     * @Author tangsh
     * @DateTime 2020年3月26日 上午9:55:35
     * @param user
     * @return
     */
    public List<BigData> cpList(User user){
    	List<BigData> bdlist= new ArrayList<BigData>();
    	Company c=new Company();
        c.setIsblacklist(1);
        c.setFiling_state(3);
        BigData bd0=new BigData();
		bd0.setSname("总备案数量");
		bd0.setSvalue(companyService.findByUserCompanyCount(c, user)+"");
        bdlist.add(bd0);
    	Patrol form =new Patrol();
		form.setSearch_code(user.getUser_code());
		form.setPatrol_state(2);
		form.setPatrol_result("不合格");
		BigData bd1=new BigData();
		bd1.setSname("巡查不合格");
		bd1.setSvalue(patrolDao.findLeftJoinCompanyCount(form)+"");
        bdlist.add(bd1);
        form.setPatrol_state(1);
        form.setPatrol_result(null);
        BigData bd2=new BigData();
        bd2.setSname("待巡查");
        bd2.setSvalue(patrolDao.findLeftJoinCompanyCount(form)+"");
        bdlist.add(bd2);
        form.setPatrol_state(2);
		form.setPatrol_result("合格");
		BigData bd3=new BigData();
		bd3.setSname("巡查合格");
		bd3.setSvalue(patrolDao.findLeftJoinCompanyCount(form)+"");
        bdlist.add(bd3);
    	return bdlist;
    }
    
    public List<BigData> companyByCity(User user,Company form){
    	form.setProvince(user.getUser_province());
    	form.setCity(user.getUser_city());
    	form.setArea(user.getUser_area());
    	form.setTown(user.getUser_town());
    	form.setVill(user.getUser_vill());
    	form.setFiling_state(3);
    	return companyDao.findCompanyByCityStatistics(form);
    }
    
    public List<BigData> mapCompany(User user,Company form){
    	List<BigData> bdlist=new ArrayList<BigData>();
    	form.setProvince(user.getUser_province());
    	form.setCity(user.getUser_city());
    	form.setArea(user.getUser_area());
    	form.setTown(user.getUser_town());
    	form.setVill(user.getUser_vill());
    	//备案总数
    	Integer total_num=companyDao.findByCount(form);
    	
    	BigData bd1=new BigData();
    	bd1.setSname("备案总数");
    	bd1.setSvalue(total_num+"");
        bdlist.add(bd1);
    	//审核数
    	form.setReturncode("1");
    	Integer sh_num=companyDao.findByCount(form);
    	
    	BigData bd2=new BigData();
    	bd2.setSname("审核数");
    	bd2.setSvalue(sh_num+"");
        bdlist.add(bd2);
        
        //审核率
    	String shl="0";
    	if(total_num!=null&&sh_num!=null&&total_num>0&&sh_num>0) {
    		shl=CalcUtil.reserve(CalcUtil.division(sh_num.toString(), total_num.toString()),2);
    	}
    	
    	BigData bd3=new BigData();
    	bd3.setSname("审核率");
    	bd3.setSvalue(shl+"");
        bdlist.add(bd3);
        
    	return bdlist;
    }
    
    /**
     * 根据登陆用户查询区域鹰眼
     * @Title: findSensorByCity
     * @Description: 
     * @Author sven
     * @DateTime 2020年3月18日 上午11:13:27
     * @param user
     * @param form
     * @return
     */
    public List<BigData> findSensorByUser(User user,Sensor form){
    	List<BigData> bdlist=new ArrayList<BigData>();
    	form.setProvince(user.getUser_province());
    	form.setCity(user.getUser_city());
    	form.setArea(user.getUser_area());
    	form.setTown(user.getUser_town());
    	form.setVill(user.getUser_vill());
    	
    	//启动设备
    	BigData onLine_count = new BigData();
    	onLine_count.setSname("启动设备");
    	form.setOnline_state(1);
    	onLine_count.setSvalue(sensorDao.findOnLineCount(form)+"");
        bdlist.add(onLine_count);
        
        //报警设备
    	BigData warn_count = new BigData();
    	warn_count.setSname("报警设备");
    	form.setWarn_state(2);
    	warn_count.setSvalue(sensorDao.findWarnCount(form)+"");
        bdlist.add(warn_count);
        
        /***************报警项***********************/
        //查询预警项编号
        String sensor_numbers = sensorDao.findWarnSensorNumber(form);
        
        SensorLog sl = new SensorLog();
        sl.setSensor_numbers(sensor_numbers);
        if(StringUtils.isEmpty(sl.getAdd_time())) {
        	sl.setAdd_time(DateUtil.getDateYearMonthDate()+" 00:00:00");
		}
		if(StringUtils.isEmpty(form.getEnd_time())) {
			sl.setEnd_time(DateUtil.getDateYearMonthDate()+" 23:59:59");
		}
		
        //温度
        BigData temp = new BigData();
        sl.setTemp_state(2);
        sl.setItem("temp_state");
        temp.setSname("温度");
        if(!StringUtils.isEmpty(sensor_numbers)) {
        	temp.setSvalue(sensorLogDao.findWarnItemBySensorNumber(sl)+"");
        }else {
        	temp.setSvalue("0");
        }
        sl.setTemp_state(null);
        bdlist.add(temp);
        
        //烟雾
        BigData mq2 = new BigData();
        sl.setMq2_state(2);
        sl.setItem("mq2_state");
        mq2.setSname("烟雾");
        if(!StringUtils.isEmpty(sensor_numbers)) {
        	 mq2.setSvalue(sensorLogDao.findWarnItemBySensorNumber(sl)+"");
        }else {
        	mq2.setSvalue("0");
        }
        sl.setMq2_state(null);
        bdlist.add(mq2);
        
        //甲烷
        BigData mq4 = new BigData();
        sl.setMq4_state(2);
        sl.setItem("mq4_state");
        mq4.setSname("甲烷");
        if(!StringUtils.isEmpty(sensor_numbers)) {
        	mq4.setSvalue(sensorLogDao.findWarnItemBySensorNumber(sl)+"");
        }else {
        	mq4.setSvalue("0");
        }
        sl.setMq4_state(null);
        bdlist.add(mq4);
        
        //液化气
        BigData mq5 = new BigData();
        sl.setMq5_state(2);
        sl.setItem("mq5_state");
        mq5.setSname("液化气");
        if(!StringUtils.isEmpty(sensor_numbers)) {
        	mq5.setSvalue(sensorLogDao.findWarnItemBySensorNumber(sl)+"");
        }else {
        	mq5.setSvalue("0");
        }
        sl.setMq5_state(null);
        bdlist.add(mq5);
        
        //一氧化碳
        BigData mq9 = new BigData();
        sl.setMq9_state(2);
        sl.setItem("mq9_state");
        mq9.setSname("一氧化碳");
        if(!StringUtils.isEmpty(sensor_numbers)) {
        	mq9.setSvalue(sensorLogDao.findWarnItemBySensorNumber(sl)+"");
        }else {
        	mq9.setSvalue("0");
        }
        sl.setMq9_state(null);
        bdlist.add(mq9);
        
        //水质
        BigData tds = new BigData();
        sl.setTds_state(2);
        sl.setItem("tds_state");
        tds.setSname("水质");
        if(!StringUtils.isEmpty(sensor_numbers)) {
        	tds.setSvalue(sensorLogDao.findWarnItemBySensorNumber(sl)+"");
        }else {
        	tds.setSvalue("0");
        }
        sl.setTds_state(null);
        bdlist.add(tds);
        
        //酸碱度
        BigData ph = new BigData();
        sl.setPh_state(2);
        sl.setItem("ph_state");
        ph.setSname("酸碱度");
        if(!StringUtils.isEmpty(sensor_numbers)) {
        	 ph.setSvalue(sensorLogDao.findWarnItemBySensorNumber(sl)+"");
        }else {
        	 ph.setSvalue("0");
        }
        sl.setPh_state(null);
        bdlist.add(ph);
        
        //湿度
        BigData humidity = new BigData();
        sl.setHumidity_state(2);
        sl.setItem("humidity_state");
        humidity.setSname("湿度");
        if(!StringUtils.isEmpty(sensor_numbers)) {
        	humidity.setSvalue(sensorLogDao.findWarnItemBySensorNumber(sl)+"");
        }else {
        	humidity.setSvalue("0");
        }
        sl.setHumidity_state(null);
        bdlist.add(humidity);
    	return bdlist;
    }
    
    
   /**
    * 根据登陆用户查询管辖区域培训记录
    * @Title: findTrainByUser
    * @Description: 
    * @Author sven
    * @DateTime 2020年3月23日 上午11:25:35
    * @param user
    * @param form
    * @return
    */
    public List<Object> findTrainByUser(User user,Region form){
    	List<Object> train_list = new ArrayList<Object>();
    	
    	form.setRegion_province(user.getUser_province());
    	form.setRegion_city(user.getUser_city());
    	form.setRegion_area(user.getUser_area());
    	form.setRegion_town(user.getUser_town());
    	form.setRegion_vill(user.getUser_vill());
    	//递归查询当前登录用户下级区域
    	List<Region> regionList = regionDao.findByName_ReportStatistics(form);
    	
    	
    	if(!StringUtils.isEmpty(user.getUser_vill())) {
			user.setZone_num(5);
		}else if(!StringUtils.isEmpty(user.getUser_town())) {
			user.setZone_num(4);
		}else if(!StringUtils.isEmpty(user.getUser_area())) {
			user.setZone_num(3);
		}else if(!StringUtils.isEmpty(user.getUser_city())) {
			user.setZone_num(2);
		}else if(!StringUtils.isEmpty(user.getUser_province())) {
			user.setZone_num(1);
		}
    	
    	user.setUser_type("食品经营者");
    	user.setUser_state(1);
    	user.setThisyear(DateUtil.getDateYear());
    	
		//查询参加培训的合格厨师数据
    	user.setTrain_exam_standard(2);
		List<User> trainPassUserList = userDao.findTrainStateUserGroupAddress(user);
    	//管辖区域用户培训不合格数
    	user.setTrain_exam_standard(1);
		List<User> trainUnPassUserList = userDao.findTrainStateUserGroupAddress(user);
		
		String region_name = "";
		Map<String, String> trainUser = null;
		for (Region region : regionList) {
			trainUser = new HashMap<String, String>();
			region_name = region.getRegion_name();
			trainUser.put("region_name", region_name);
			trainUser.put("train_pass_count", "0");
			trainUser.put("train_uppass_count", "0");
			
			for(User item : trainPassUserList) {
				if(user.getZone_num()== 1) {
					if(region_name.equals(item.getUser_city())) {
						trainUser.put("train_pass_count", item.getUser_number()+"");
					}
				}
				if(user.getZone_num() == 2) {
					if(region_name.equals(item.getUser_area())) {
						trainUser.put("train_pass_count", item.getUser_number()+"");
					}
				}
				if(user.getZone_num() == 3) {
					if(region_name.equals(item.getUser_town())) {
						trainUser.put("train_pass_count", item.getUser_number()+"");
					}
				}
				if(user.getZone_num() == 4 ||user.getZone_num() == 5) {
					if(region_name.equals(item.getUser_vill())) {
						trainUser.put("train_pass_count", item.getUser_number()+"");
					}
				}
			}
			
			for(User item : trainUnPassUserList) {
				if(user.getZone_num()== 1) {
					if(region_name.equals(item.getUser_city())) {
						trainUser.put("train_uppass_count", item.getUser_number()+"");
					}
				}
				if(user.getZone_num() == 2) {
					if(region_name.equals(item.getUser_area())) {
						trainUser.put("train_uppass_count", item.getUser_number()+"");
					}
				}
				if(user.getZone_num() == 3) {
					if(region_name.equals(item.getUser_town())) {
						trainUser.put("train_uppass_count", item.getUser_number()+"");
					}
				}
				if(user.getZone_num() == 4 ||user.getZone_num() == 5) {
					if(region_name.equals(item.getUser_vill())) {
						trainUser.put("train_uppass_count", item.getUser_number()+"");
					}
				}
			}
			
			train_list.add(trainUser);
		}
    	return train_list;
    }
    
    /**
     * 根据登陆用户查询该区域下企业溯源
     * @Title: findFoodSourceByUser
     * @Description: 
     * @Author sven
     * @DateTime 2020年4月20日 下午5:13:25
     * @param form
     * @param user
     * @return
     */
    public List<Object> findFoodSourceByUser(Company form,User user,Map<Object, Object> map){
    	map.put("pager_count",companyService.findByUserCompanyCount(form,user));
    	
    	List<Object> company_list = new ArrayList<Object>();
    	Map<String,Object> map_company = null;
    	FoodSource foodSource = null;
    	form.setSort_file("food_source_count");
    	List<CompanyReturn> query_list = companyService.findByUserCompanyList(form,user);
    	for (CompanyReturn companyReturn : query_list) {
    		map_company = new HashMap<String,Object>();
    		foodSource = new FoodSource();
    		foodSource.setCompany_code(companyReturn.getCompany_code());
    		
    		map_company.put("company_code", companyReturn.getCompany_code());
    		map_company.put("company_name", companyReturn.getCompany_name());
    		map_company.put("operator", companyReturn.getOperator());
    		map_company.put("food_source_count", foodSourceDao.findByCount(foodSource));
    		
    		company_list.add(map_company);
		}
    	
    	return company_list;
    }
    
    
    /**
     * 根据企业编码查询溯源列表
     * @Title: findFoodSourceByCompany
     * @Description: 
     * @Author sven
     * @DateTime 2020年4月21日 上午9:26:03
     * @param form
     * @param user
     * @return
     */
    public List<Object> findFoodSourceByCompany(FoodSource form,Map<Object, Object> map){
    	map.put("pager_count",foodSourceDao.findByCount(form));
    	
    	List<Object> food_source_list = new ArrayList<Object>();
    	Map<String,Object> map_food_source = null;
    	FoodSourceDetail foodSourceDetail = null;
    	
    	List<FoodSourceReturn> query_list = foodSourceDao.findByList_app(form);
    	for (FoodSourceReturn foodSourceReturn : query_list) {
    		map_food_source = new HashMap<String,Object>();
    		foodSourceDetail = new FoodSourceDetail();
    		foodSourceDetail.setFoodsource_code(foodSourceReturn.getFoodsource_code());
    		
    		map_food_source.put("foodsource_code", foodSourceReturn.getFoodsource_code());
    		map_food_source.put("sell_time", foodSourceReturn.getSell_time());
    		map_food_source.put("food_source_detail_count", foodSourceDetailDao.findByCount(foodSourceDetail));
    		map_food_source.put("sell_person", foodSourceReturn.getSell_person());
    		map_food_source.put("supplier_address", foodSourceReturn.getSupplier_address());
    		
    		food_source_list.add(map_food_source);
		}
    	
    	return food_source_list;
    }
    
    
    /**
     * 根据foodsource_code查询明细
     * @Title: findFoodSourceDetail
     * @Description: 
     * @Author sven
     * @DateTime 2020年4月21日 上午9:53:06
     * @param foodsource_code
     * @return
     */
    public List<Object> findFoodSourceDetail(String foodsource_code){
    	
    	List<Object> food_source_detail_list = new ArrayList<Object>();
    	Map<String,Object> map_food_source_detail = null;
    	
    	List<FoodSourceDetailReturn> query_list = foodSourceDetailDao.findByAll_app(foodsource_code);
    	for (FoodSourceDetailReturn foodSourceDetailReturn : query_list) {
    		map_food_source_detail = new HashMap<String,Object>();
    		
    		map_food_source_detail.put("product_logo", foodSourceDetailReturn.getProduct_logo());
    		map_food_source_detail.put("category_name", foodSourceDetailReturn.getCategory_name());
    		map_food_source_detail.put("product_name", foodSourceDetailReturn.getProduct_name());
    		map_food_source_detail.put("product_spec", foodSourceDetailReturn.getProduct_spec());
    		map_food_source_detail.put("product_unit", foodSourceDetailReturn.getProduct_unit());
    		map_food_source_detail.put("sell_count", foodSourceDetailReturn.getSell_count());
    		map_food_source_detail.put("supplier_name", foodSourceDetailReturn.getSupplier_name());
    		map_food_source_detail.put("userset_name", foodSourceDetailReturn.getUserset_name());
    		map_food_source_detail.put("produce_name", foodSourceDetailReturn.getProduce_name());
    		
    		
    		food_source_detail_list.add(map_food_source_detail);
		}
    	
    	return food_source_detail_list;
    }
}
