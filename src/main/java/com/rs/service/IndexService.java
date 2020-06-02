package com.rs.service;

import com.alibaba.fastjson.JSON;
import com.rs.dao.ICompanyDao;
import com.rs.dao.IComplaintReportDao;
import com.rs.dao.IPatrolDao;
import com.rs.dao.ITrainExamLogDao;
import com.rs.po.Company;
import com.rs.po.ComplaintReport;
import com.rs.po.Level;
import com.rs.po.Patrol;
import com.rs.po.TrainExamLog;
import com.rs.po.User;
import com.rs.util.CalcUtil;
import com.rs.util.DateUtil;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndexService {
    @Autowired
    private ICompanyDao companyDao;
    @Autowired
    private ITrainExamLogDao trainExamLogDao;
    @Autowired
	private CompanyService companyService;
    @Autowired
    private IPatrolDao patrolDao;
    @Autowired
    private IComplaintReportDao complaintReportDao;
    
    public List<Level> coverageList(String user_code) {
        Level level = companyDao.findRegionLevel(user_code);
        List<Level> list = new ArrayList<>();
        if(level!=null){
            if(level.getArea()!=null&&!"".equals(level.getArea())&&"3".equals(level.getUser_level())){
                list = companyDao.findAreaCount(level.getArea());
            }else if(level.getCity()!=null&&!"".equals(level.getCity())&&"2".equals(level.getUser_level())){
                list = companyDao.findCityCount(level.getCity());
            }
        }
        return list;
    }

    public List<String> smAddressList(String user_code) {
            Level level = companyDao.findRegionLevel(user_code);
            if(level!=null&&((level.getArea()!=null&&!"".equals(level.getArea()))||(level.getCity()!=null&&!"".equals(level.getCity())))){
                return companyDao.findMap(level);
            }
        return null;
    }

    /**
     * 
     * @Title: count
     * @Description: 
     * @Author tangsh
     * @DateTime 2020年3月24日 下午5:29:00
     * @param user
     * @return
     */
    public String count(User user) {
        Map<String,Object> map = new HashMap<>();
        //查询今日待办事项
        //备案
        Company c=new Company();
        c.setIsblacklist(1);
        c.setFiling_state(1);
        c.setSearch_time_bigen(DateUtil.getDateYearMonthDate()+" 00:00:00");
        c.setSearch_time_end(DateUtil.getDateYearMonthDate()+" 23:59:59");
        //c.setCompanytype_code("1001574645421581000000001");
        map.put("company_not_check", companyService.findByUserCompanyCount(c, user));//今日待审核备案
        c.setFiling_state(3);
        c.setState(1);
        Integer c_count=companyService.findByUserCompanyCount(c, user);//查询已归档
        c.setState(null);
        c.setFiling_state(4);
        c_count+=companyService.findByUserCompanyCount(c, user);//查询已驳回
        map.put("company_pass_check", c_count);//今日已审核备案=已归档+已驳回
        //工单
        Patrol patrol=new Patrol();
        patrol.setSearch_time_bigen(DateUtil.getDateYearMonthDate()+" 00:00:00");
        patrol.setSearch_time_end(DateUtil.getDateYearMonthDate()+" 23:59:59");
        patrol.setSearch_code(user.getUser_code());
        patrol.setPatrol_state(1);
        map.put("patrol_not_check", patrolDao.findLeftJoinCompanyCount(patrol));//待处理工单
        patrol.setPatrol_state(2);
        map.put("patrol_pass_check", patrolDao.findLeftJoinCompanyCount(patrol));//已处理工单
        //投诉举报
        ComplaintReport cr=new ComplaintReport();
        cr.setComplaintreport_state(1);
        map.put("patrol_not_check", complaintReportDao.findByCount(cr));//待处理投诉举报
        cr.setComplaintreport_state(2);
        cr.setResult_user_code(user.getUser_code());
        map.put("patrol_pass_check", complaintReportDao.findByCount(cr));//已处理投诉举报
        
        
        //即将到期预警
        c=new Company();
        c.setIsblacklist(1);
        c.setFiling_state(3);
        c.setState(4);
        c.setCompanytype_code("1001574645421581000000001");//食品生产
        map.put("company_willexpire_sc", companyService.findByUserCompanyCount(c, user));
        c.setEmploy_state(4);
        map.put("c_employ_willexpire_sc", companyService.findByCompanyEmployCount(c, user));
        c.setCompanytype_code("1001574645421581000000002");//食品流通
        c.setEmploy_state(null);
        map.put("company_willExpire_lt", companyService.findByUserCompanyCount(c, user));
        c.setEmploy_state(4);
        map.put("c_employ_willexpire_lt", companyService.findByCompanyEmployCount(c, user));
        c.setCompanytype_code("1001574645421581000000003");//食品餐饮
        c.setEmploy_state(null);
        map.put("company_willexpire_cy", companyService.findByUserCompanyCount(c, user));
        c.setEmploy_state(4);
        map.put("c_employ_willexpire_cy", companyService.findByCompanyEmployCount(c, user));
        //到期预警
        c.setState(2);
        c.setCompanytype_code("1001574645421581000000001");//食品生产
        c.setEmploy_state(null);
        map.put("company_expired_sc", companyService.findByUserCompanyCount(c, user));
        c.setEmploy_state(4);
        map.put("c_employ_expired_sc", companyService.findByCompanyEmployCount(c, user));
        c.setCompanytype_code("1001574645421581000000002");//食品流通
        c.setEmploy_state(null);
        map.put("company_expired_lt", companyService.findByUserCompanyCount(c, user));
        c.setEmploy_state(4);
        map.put("c_employ_expired_lt", companyService.findByCompanyEmployCount(c, user));
        c.setCompanytype_code("1001574645421581000000003");//食品餐饮
        c.setEmploy_state(null);
        map.put("company_expired_cy", companyService.findByUserCompanyCount(c, user));
        c.setEmploy_state(4);
        map.put("c_employ_expired_cy", companyService.findByCompanyEmployCount(c, user));
        
        
        
        
        /*c.setFiling_state(2);
        //查询
        map.put("willRecode",companyDao.findByCount(c));
        //三小商家总数
        c.setFiling_state(3);
        map.put("shopCount",companyDao.findByCount(c));
        //小作坊（生产）
        c.setCompanytag_code("1001574645421581111111101");
        map.put("smallFoodShop",companyDao.findByCount(c));
        //小副食店（流通）
        c.setCompanytag_code("1001574645421581111111102");
        map.put("smallSellShop",companyDao.findByCount(c));
        //食品经营许可
        c.setCompanytag_code("1001574645421581111111103");
        map.put("workShop",companyDao.findByCount(c));
        //小餐饮（备案）
        c.setCompanytag_code("1001574645421581111111104");
        map.put("streetPedlar",companyDao.findByCount(c));
        
        //即将到期预警
        List<Integer> list = new ArrayList<>();
        c.setCompanytag_code("1001574645421581111111101");
        c.setState(4);
        list.add(companyDao.findByCount(c));
        c.setCompanytag_code("1001574645421581111111102");
        list.add(companyDao.findByCount(c));
        c.setCompanytag_code("1001574645421581111111103");
        list.add(companyDao.findByCount(c));
        c.setCompanytag_code("1001574645421581111111104");
        list.add(companyDao.findByCount(c));
        map.put("willExpire",list);
        
        //过期预警
        List<Integer> list1 = new ArrayList<>();
        c.setState(2);
        c.setCompanytag_code("1001574645421581111111101");
        list1.add(companyDao.findByCount(c));
        c.setCompanytag_code("1001574645421581111111102");
        list1.add(companyDao.findByCount(c));
        c.setCompanytag_code("1001574645421581111111103");
        list1.add(companyDao.findByCount(c));
        c.setCompanytag_code("1001574645421581111111104");
        list1.add(companyDao.findByCount(c));
        map.put("expired",list1);
        
        //查询用户对应等级下一级的三小区域分布统计
        map.put("coverage",coverageList(user.getUser_code()));
        //查询用户对应等级下一级地域所有的三小企业
        map.put("smAddressList",smAddressList(user.getUser_code()));*/
       String json = JSON.toJSONString(map);
       return json;
    }
    
    /**
     * 
     * @Title: todolist
     * @Description: 今日待办事项
     * @Author tangsh
     * @DateTime 2020年3月24日 下午5:22:27
     * @param user
     * @return
     */
    public Map<String,Integer> todoList(User user) {
        Map<String,Integer> map = new HashMap<String,Integer>();
        //查询今日待办事项
        //备案
        Company c=new Company();
        c.setIsblacklist(1);
        c.setFiling_state(1);
        //c.setSearch_time_bigen(DateUtil.getDateYearMonthDate()+" 00:00:00");
        //c.setSearch_time_end(DateUtil.getDateYearMonthDate()+" 23:59:59");
        //c.setCompanytype_code("1001574645421581000000001");
        map.put("company_not_check", companyService.findByUserCompanyCount(c, user));//今日待审核备案
        c=new Company();
        c.setIsblacklist(1);
        c.setFiling_state(3);
        //c.setSearch_time_bigen(DateUtil.getDateYearMonthDate()+" 00:00:00");
        //c.setSearch_time_end(DateUtil.getDateYearMonthDate()+" 23:59:59");
        //c.setState(1);
        Integer c_count=companyService.findByUserCompanyCount(c, user);//查询已归档
        c=new Company();
        c.setIsblacklist(1);
        c.setState(null);
        c.setFiling_state(4);
        //c.setSearch_time_bigen(DateUtil.getDateYearMonthDate()+" 00:00:00");
        //c.setSearch_time_end(DateUtil.getDateYearMonthDate()+" 23:59:59");
        c_count+=companyService.findByUserCompanyCount(c, user);//查询已驳回
        map.put("company_pass_check", c_count);//今日已审核备案=已归档+已驳回
        //工单
        Patrol patrol=new Patrol();
        //patrol.setSearch_time_bigen(DateUtil.getDateYearMonthDate()+" 00:00:00");
        //patrol.setSearch_time_end(DateUtil.getDateYearMonthDate()+" 23:59:59");
        patrol.setSearch_code(user.getUser_code());
        patrol.setPatrol_state(1);
        map.put("patrol_not_check", patrolDao.findLeftJoinCompanyCount(patrol));//待处理工单
        patrol.setPatrol_state(2);
        map.put("patrol_pass_check", patrolDao.findLeftJoinCompanyCount(patrol));//已处理工单
        //投诉举报
        ComplaintReport cr=new ComplaintReport();
        cr.setComplaintreport_state(1);
        map.put("cr_not_check", complaintReportDao.findByCount(cr));//待处理投诉举报
        cr.setComplaintreport_state(2);
        cr.setResult_user_code(user.getUser_code());
        map.put("cr_pass_check", complaintReportDao.findByCount(cr));//已处理投诉举报
        return map;
    }
    
    /**
     * 
     * @Title: willexpirelist
     * @Description: 即将到期预警
     * @Author tangsh
     * @DateTime 2020年3月24日 下午5:25:31
     * @param user
     * @return
     */
    public Map<String,Integer> willExpireList(User user) {
        Map<String,Integer> map = new HashMap<String,Integer>();
        //即将到期预警
        Company c=new Company();
        c=new Company();
        c.setIsblacklist(1);
        c.setFiling_state(3);
        c.setState(4);
        c.setCompanytype_code("1001574645421581000000001");//食品生产
        map.put("company_willexpire_sc", companyService.findByUserCompanyCount(c, user));
        c.setEmploy_state(4);
        map.put("c_employ_willexpire_sc", companyService.findByCompanyEmployCount(c, user));
        c.setCompanytype_code("1001574645421581000000002");//食品流通
        c.setEmploy_state(null);
        map.put("company_willExpire_lt", companyService.findByUserCompanyCount(c, user));
        c.setEmploy_state(4);
        map.put("c_employ_willexpire_lt", companyService.findByCompanyEmployCount(c, user));
        c.setCompanytype_code("1001574645421581000000003");//食品餐饮
        c.setEmploy_state(null);
        map.put("company_willexpire_cy", companyService.findByUserCompanyCount(c, user));
        c.setEmploy_state(4);
        map.put("c_employ_willexpire_cy", companyService.findByCompanyEmployCount(c, user));
        return map;
    }
    
    /**
     * 
     * @Title: expiredlist
     * @Description: 过期预警
     * @Author tangsh
     * @DateTime 2020年3月24日 下午5:27:24
     * @param user
     * @return
     */
    public Map<String,Integer> expiredList(User user) {
        Map<String,Integer> map = new HashMap<String,Integer>();
        //到期预警
        Company c=new Company();
        c=new Company();
        c.setIsblacklist(1);
        c.setFiling_state(3);
        c.setState(2);
        c.setCompanytype_code("1001574645421581000000001");//食品生产
        c.setEmploy_state(null);
        map.put("company_expired_sc", companyService.findByUserCompanyCount(c, user));
        c.setEmploy_state(4);
        map.put("c_employ_expired_sc", companyService.findByCompanyEmployCount(c, user));
        c.setCompanytype_code("1001574645421581000000002");//食品流通
        c.setEmploy_state(null);
        map.put("company_expired_lt", companyService.findByUserCompanyCount(c, user));
        c.setEmploy_state(4);
        map.put("c_employ_expired_lt", companyService.findByCompanyEmployCount(c, user));
        c.setCompanytype_code("1001574645421581000000003");//食品餐饮
        c.setEmploy_state(null);
        map.put("company_expired_cy", companyService.findByUserCompanyCount(c, user));
        c.setEmploy_state(4);
        map.put("c_employ_expired_cy", companyService.findByCompanyEmployCount(c, user));
        return map;
    }
    
    /**
     * 
     * @Title: newsavelist
     * @Description: 今日新增食品经营责任人 
     * @Author tangsh
     * @DateTime 2020年3月24日 下午5:43:20
     * @param user
     * @return
     */
    public Map<String,Integer> newSaveList(User user) {
        Map<String,Integer> map = new HashMap<String,Integer>();
        //累计
        Company c=new Company();
        c.setIsblacklist(1);
        c.setFiling_state(3);
        map.put("company_all", companyService.findByUserCompanyCount(c, user));//累计归档企业
        c.setCompanytype_code("1001574645421581000000001");
        map.put("company_all_sc", companyService.findByUserCompanyCount(c, user));//累计食品生产
        c.setCompanytype_code("1001574645421581000000002");
        map.put("company_all_lt", companyService.findByUserCompanyCount(c, user));//累计食品流通
        c.setCompanytype_code("1001574645421581000000003");
        map.put("company_all_cy", companyService.findByUserCompanyCount(c, user));//累计食品餐饮
        //今日
        c.setSearch_time_bigen(DateUtil.getDateYearMonthDate()+" 00:00:00");
        c.setSearch_time_end(DateUtil.getDateYearMonthDate()+" 23:59:59");
        c.setCompanytype_code(null);
        map.put("company_today_all", companyService.findByUserCompanyCount(c, user));//今日归档企业
        c.setCompanytype_code("1001574645421581000000001");
        map.put("company_today_all_sc", companyService.findByUserCompanyCount(c, user));//今日食品生产
        c.setCompanytype_code("1001574645421581000000002");
        map.put("company_today_all_lt", companyService.findByUserCompanyCount(c, user));//今日食品流通
        c.setCompanytype_code("1001574645421581000000003");
        map.put("company_today_all_cy", companyService.findByUserCompanyCount(c, user));//今日食品餐饮
        return map;
    }
    
    /**
     * 
     * @Title: todaycompanylist
     * @Description: 今日备案管理情况
     * @Author tangsh
     * @DateTime 2020年3月25日 上午9:18:34
     * @param user
     * @return
     */
    public Map<String,Integer> todayCompanyList(User user) {
        Map<String,Integer> map = new HashMap<String,Integer>();
        Company c=new Company();
        c.setIsblacklist(1);
        c.setFiling_state(1);
        map.put("company_not_check_all", companyService.findByUserCompanyCount(c, user));//累计待审核备案
        c=new Company();
        c.setIsblacklist(1);
        c.setFiling_state(3);
        //c.setState(1);
        map.put("company_pass_check_all", companyService.findByUserCompanyCount(c, user));//累计已归档
        c.setState(null);
        c.setFiling_state(4);
        map.put("company_goback_check_all", companyService.findByUserCompanyCount(c, user));//累计已驳回
        
        c.setSearch_time_bigen(DateUtil.getDateYearMonthDate()+" 00:00:00");
        c.setSearch_time_end(DateUtil.getDateYearMonthDate()+" 23:59:59");
        c.setFiling_state(1);
        map.put("company_not_check", companyService.findByUserCompanyCount(c, user));//今日待审核备案
        
        c=new Company();
        c.setIsblacklist(1);
        c.setFiling_state(3);
        //c.setState(1);
        c.setFiling_state_time(DateUtil.getDateYearMonthDate());
        map.put("company_pass_check", companyService.findByUserCompanyCount(c, user));//今日已归档
        c.setState(null);
        c.setFiling_state(4);
        map.put("company_goback_check", companyService.findByUserCompanyCount(c, user));//今日已驳回
        
        
        return map;
    }
    
    /**
     * 
     * @Title: gismaplist
     * @Description: 今日GIS地图数据
     * @Author tangsh
     * @DateTime 2020年3月25日 上午9:43:39
     * @param form
     * @param user
     * @return
     */
    public Map<String,Object> gisMapList(Patrol form,User user) {
        Map<String,Object> map = new HashMap<String,Object>();
        //工单
        form.setSearch_time_bigen(DateUtil.getDateYearMonthDate()+" 00:00:00");
        form.setSearch_time_end(DateUtil.getDateYearMonthDate()+" 23:59:59");
        form.setSearch_code(user.getUser_code());
        form.setPatrol_state(1);
        map.put("patrol_not_check", patrolDao.findLeftJoinCompanyCount(form));//待巡查工单
        form.setPatrol_state(2);
        map.put("patrol_pass_check", patrolDao.findLeftJoinCompanyCount(form));//已处理工单
        form.setPatrol_result("合格");
        map.put("patrol_pass_check_yes", patrolDao.findLeftJoinCompanyCount(form));//已处理工单合格
        form.setPatrol_result("不合格");
        map.put("patrol_pass_check_no", patrolDao.findLeftJoinCompanyCount(form));//已处理工单不合格
        //查询已巡查列表
        form.setPatrol_result(null);
        if(StringUtils.isEmpty(form.getPager_openset())) {
        	form.setPager_openset(100);
        }
        map.put("patrol_list", patrolDao.findLeftJoinCompanyList(form));//已处理工单
        map.put("pager_count", patrolDao.findLeftJoinCompanyCount(form));
        return map;
    }
    
    /**
     * 
     * @Title: companyCheckRate
     * @Description: 备案审核率环比
     * @Author tangsh
     * @DateTime 2020年3月25日 上午10:00:37
     * @param user
     * @return
     */
    public Map<String,Object> companyCheckRate(User user) {
        Map<String,Object> map = new HashMap<String,Object>();
        Company c=new Company();
        //本月
        c.setIsblacklist(1);
        c.setFiling_state(1);
        c.setMonthstr(DateUtil.getDateYearMonth());
        Integer dsh=companyService.findByUserCompanyCount(c, user);//待审核
        c=new Company();
        c.setIsblacklist(1);
        c.setFiling_state(3);
        c.setMonthstr(DateUtil.getDateYearMonth());
        //c.setState(1);
        Integer ygd=companyService.findByUserCompanyCount(c, user);//已归档
        //c.setState(null);
        c.setFiling_state(4);
        Integer ybh=companyService.findByUserCompanyCount(c, user);//已驳回
        Integer _all=ygd+ybh;
        map.put("company_no_check", dsh);//本月待审核
        map.put("company_pass_check", _all);//本月已审核
        //上月
        c.setFiling_state(1);
        c.setMonthstr(DateUtil.calcDate(DateUtil.Y_M_FORMAT, DateUtil.MONTH, DateUtil.getDateYearMonth(), -1));
        Integer last_dsh=companyService.findByUserCompanyCount(c, user);//待审核
        c=new Company();
        c.setIsblacklist(1);
        c.setMonthstr(DateUtil.calcDate(DateUtil.Y_M_FORMAT, DateUtil.MONTH, DateUtil.getDateYearMonth(), -1));
        c.setFiling_state(3);
        //c.setState(1);
        Integer last_ygd=companyService.findByUserCompanyCount(c, user);//已归档
        //c.setState(null);
        c.setFiling_state(4);
        Integer last_ybh=companyService.findByUserCompanyCount(c, user);//已驳回
        Integer last_all=last_ygd+last_ybh;
        map.put("last_company_no_check", last_dsh);//上月待审核
        map.put("last_company_pass_check", last_all);//上月已审核
        
        //环比审核率
    	String chain_rate = "0";
    	String link_sub = CalcUtil.subtr(_all+"", last_all+"");
    	if(!(link_sub.equals("0") || link_sub.equals("0.00") || last_all == 0)) {
    		chain_rate = CalcUtil.reserve(CalcUtil.multiply(CalcUtil.division(link_sub,last_all+""),"100"),2);
    	}
    	map.put("chain_rate", chain_rate);
        return map;
    }
    
    /**
     * 
     * @Title: patrolCheckRate
     * @Description: 巡查合格率环比
     * @Author tangsh
     * @DateTime 2020年3月25日 下午2:31:54
     * @param user
     * @return
     */
    public Map<String,Object> patrolCheckRate(User user) {
        Map<String,Object> map = new HashMap<String,Object>();
        //本月
        Patrol patrol=new Patrol();
        patrol.setSearch_code(user.getUser_code());
        patrol.setPatrol_state(2);
        patrol.setMonthstr(DateUtil.getDateYearMonth());
        Integer yxc=patrolDao.findLeftJoinCompanyCount(patrol);//已处理
        patrol.setPatrol_result("合格");
        Integer hg=patrolDao.findLeftJoinCompanyCount(patrol);//合格
        map.put("patrol_check_all", yxc);//本月已处理
        map.put("patrol_check_yes", hg);//本月合格
        
        //上月
        patrol.setMonthstr(DateUtil.calcDate(DateUtil.Y_M_FORMAT, DateUtil.MONTH, DateUtil.getDateYearMonth(), -1));
        patrol.setPatrol_result(null);
        Integer last_yxc=patrolDao.findLeftJoinCompanyCount(patrol);//已处理
        patrol.setPatrol_result("合格");
        Integer last_hg=patrolDao.findLeftJoinCompanyCount(patrol);//合格
        map.put("last_patrol_check_all", last_yxc);//上月已处理
        map.put("last_patrol_check_yes", last_hg);//上月合格
        
        //环比合格率
    	String chain_rate = "0";
    	String link_sub = CalcUtil.subtr(hg+"", last_hg+"");
    	if(!(link_sub.equals("0") || link_sub.equals("0.00") || last_hg == 0)) {
    		chain_rate = CalcUtil.reserve(CalcUtil.multiply(CalcUtil.division(link_sub,last_hg+""),"100"),2);
    	}
    	map.put("chain_rate", chain_rate);
        return map;
    }
    
    
    /**
     * 根据登陆用户查询对应管辖区域考试
     * @Title: findExamUserCount
     * @Description: 
     * @Author sven
     * @DateTime 2020年3月23日 下午5:40:45
     * @param form
     * @param trainExamLog
     * @return
     */
    public List<Object> findExamUserCount(User form,TrainExamLog trainExamLog){
    	BeanUtils.copyProperties(form, trainExamLog);
    	List<Object> exam_list = new ArrayList<Object>();
    	
    	//本月
    	Map<String, Object> exam_map = new HashMap<String, Object>();
    	trainExamLog.setAddtime(DateUtil.getDateYearMonth());
    	//考试数
    	int exam_count_cur_month = trainExamLogDao.findExamUserCount(trainExamLog);
    	exam_map.put("exam_count_cur_month", exam_count_cur_month);
    	//合格数
    	trainExamLog.setTrain_exam_standard(2);
    	int exam_pass_count_cur_month = trainExamLogDao.findExamUserCount(trainExamLog);
    	exam_map.put("exam_pass_count_cur_month", exam_pass_count_cur_month);
    	
    	//上月
    	trainExamLog.setAddtime(DateUtil.calcDate(DateUtil.Y_M_FORMAT, DateUtil.MONTH, DateUtil.getDateYearMonth(), -1));
    	//考试数
    	trainExamLog.setTrain_exam_standard(null);
    	int exam_count_pre_month = trainExamLogDao.findExamUserCount(trainExamLog);
    	exam_map.put("exam_count_pre_month", exam_count_pre_month);
    	//合格数
    	trainExamLog.setTrain_exam_standard(2);
    	int exam_pass_count_pre_month = trainExamLogDao.findExamUserCount(trainExamLog);
    	exam_map.put("exam_pass_count_pre_month", exam_pass_count_pre_month);
    	
    	//环比增加率
    	String link_rate = "0";
    	String link_sub = CalcUtil.subtr(exam_pass_count_cur_month+"", exam_pass_count_pre_month+"");
    	if(!(link_sub.equals("0") || link_sub.equals("0.00") || exam_pass_count_cur_month == 0)) {
    		link_rate = CalcUtil.reserve(CalcUtil.multiply(CalcUtil.division(link_sub,exam_pass_count_cur_month+""),"100"),2);
    	}
    	exam_map.put("link_rate", link_rate);
    	
    	exam_list.add(exam_map);
    	return exam_list;
    }
}
