package com.rs.service;

import com.rs.dao.*;
import com.rs.po.*;
import com.rs.po.returnPo.AccessoryReturn;
import com.rs.po.returnPo.CompanyEmployReturn;
import com.rs.po.returnPo.CompanyReturn;
import com.rs.util.BaiDuMapUtil;
import com.rs.util.CharacterUtil;
import com.rs.util.CodeUtil;
import com.rs.util.DateUtil;
import com.rs.util.Md5Util;
import com.rs.util.RegularUtil;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService extends BaseService<Company> {
    @Autowired
    private ICompanyDao companyDao;
    @Autowired
    private ICompanySnapDao companySnapDao;
    @Autowired
    private IRegionDao regionDao;
    @Autowired
    private IDepartmentDao departmentDao;
    @Autowired
    private IDepartmentUserDao departmentUserDao;
    @Autowired
    private IUserDao userDao;
    @Autowired
    private ICompanyEmployDao companyEmployDao;
    @Autowired
    private IAccessoryDao accessoryDao;
    @Autowired
	private ICompanyCCLJDao companyCCLJDao;
    @Autowired
    private UserService userService;
    @Autowired
    private AccessoryService accessoryService;
    @Autowired
    private SubjectDetailService subjectDetailService;
    @Autowired
    private LogService LogService;
    @Autowired
    private MessLogService messLogService;
    
    public String findByLast() {
        return companyDao.findByLast();
    }

    public int findBySearchCount(Company form) {
        return companyDao.findBySearchCount(form);
    }
    
    /**
     * 
     * @Title: findByUserCompanyList
     * @Description: 根据用户权限查询对应的数据
     * @Author tangsh
     * @DateTime 2019年12月27日 下午4:19:59
     * @param form
     * @param user
     * @return
     */
    public List<CompanyReturn> findByUserCompanyList(Company form,User user){
    	List<CompanyReturn> cList=new ArrayList<CompanyReturn>();
    	form.setBusiness_forms(RegularUtil.getStringToSqlString(user.getBusiness_forms(),","));
    	form.setCompanytag_codes(RegularUtil.getStringToSqlString(user.getCompanytag_codes(),","));
    	form.setCompanytype_codes(RegularUtil.getStringToSqlString(user.getCompanytype_codes(),","));
    	
    	if(!StringUtils.isEmpty(form.getFiling_state())&&form.getFiling_state()<3) {//草稿流程单独流程
    		//新增逻辑
    		if(user.getUser_type().equals("协管员")) {
        		form.setIs_manage_or_info(1);
        		form.setOther_code(user.getUser_code());
    		}else {
    			//新增管理区域判断
        		DepartmentUser du=new DepartmentUser();
    			du.setUser_code(user.getUser_code());
    			int result=departmentUserDao.findByCount(du);
    			if(result>0) {
    				form.setIs_manage_or_info(2);
    	    		form.setOther_code(user.getUser_code());
    			}else {
    				userAddToCompany(user,form);
    			}
    		}
			
			Integer filing_state=form.getFiling_state();
			Integer rstate=form.getState();
			form.setFiling_state(null);
			form.setFiling_state_str("1,2");
			form.setState(null);
			List<Company> _clist=companyDao.findByList(form);
			form.setFiling_state(filing_state);
			form.setState(rstate);
			
    		CompanyReturn cr=null;
    		if (_clist != null && _clist.size() > 0) {
                Region region = null;
                int state = 0;
                if (form.getState() != null && form.getState() == 2) {
                    state = 2;
                }
                if (form.getState() != null && form.getState() == 4) {
                    state = 4;
                }
                Department _d=null;
                Log log=null;
                for (Company c : _clist) {
                	cr=new CompanyReturn();
                	BeanUtils.copyProperties(c, cr);
                    if (state != 0) {
                        cr.setState(state);
                    }else {
                    	if(c.getState()!=null&&c.getState()!=3) {
                    		cr.setState(companyDao.findStateByCode(c.getCompany_code()));
                    	}
                    }
                    _d=new Department();
                    _d.setDepartment_code(cr.getDepartment_code());
                    _d=departmentDao.findByCode(_d);
                    if(_d!=null) {
                    	cr.setDepartment_name(_d.getDepartment_name());
                    }
                    region = regionDao.findByCode(c.getRegion_code());
                    if (region != null) {
                        cr.setRegion_name(region.getRegion_name());
                    }
                    //查询日志备注
                    log=LogService.findLastByCompanyCode(cr.getCompany_code());
                    if(log!=null) {
                    	cr.setIsblacklist_remark(log.getLog_remark());
                    	cr.setIsblacklist_time(log.getAdd_time());
                    }
                    
                    cList.add(cr);
                }
    		}
    		
    		return cList;
    	}
    	
    	
    	if(user.getUser_type().equals("协管员")) {
    		form.setIs_manage_or_info(1);
    		form.setOther_code(user.getUser_code());
    		List<Company> _clist=companyDao.findByList(form);
    		CompanyReturn cr=null;
    		if (_clist != null && _clist.size() > 0) {
                Region region = null;
                int state = 0;
                if (form.getState() != null && form.getState() == 2) {
                    state = 2;
                }
                if (form.getState() != null && form.getState() == 4) {
                    state = 4;
                }
                Department _d=null;
                Log log=null;
                for (Company c : _clist) {
                	cr=new CompanyReturn();
                	BeanUtils.copyProperties(c, cr);
                    if (state != 0) {
                        cr.setState(state);
                    }else {
                    	if(c.getState()!=null&&c.getState()!=3) {
                    		cr.setState(companyDao.findStateByCode(c.getCompany_code()));
                    	}
                    }
                    _d=new Department();
                    _d.setDepartment_code(cr.getDepartment_code());
                    _d=departmentDao.findByCode(_d);
                    if(_d!=null) {
                    	cr.setDepartment_name(_d.getDepartment_name());
                    }
                    region = regionDao.findByCode(c.getRegion_code());
                    if (region != null) {
                        cr.setRegion_name(region.getRegion_name());
                    }
                    //查询日志备注
                    log=LogService.findLastByCompanyCode(cr.getCompany_code());
                    if(log!=null) {
                    	cr.setIsblacklist_remark(log.getLog_remark());
                    	cr.setIsblacklist_time(log.getAdd_time());
                    }
                    cList.add(cr);
                }
            }
		}else {
			DepartmentUser du=new DepartmentUser();
			du.setUser_code(user.getUser_code());
			int result=departmentUserDao.findByCount(du);
			if(result>0) {
				form.setIs_manage_or_info(2);
	    		form.setOther_code(user.getUser_code());
	    		List<Company> _clist=companyDao.findByList(form);
	    		CompanyReturn cr=null;
	    		if (_clist != null && _clist.size() > 0) {
	                Region region = null;
	                int state = 0;
	                if (form.getState() != null && form.getState() == 2) {
	                    state = 2;
	                }
	                if (form.getState() != null && form.getState() == 4) {
	                    state = 4;
	                }
	                Department _d=null;
	                Log log=null;
	                for (Company c : _clist) {
	                	cr=new CompanyReturn();
	                	BeanUtils.copyProperties(c, cr);
	                    if (state != 0) {
	                        cr.setState(state);
	                    }else {
	                    	if(c.getState()!=null&&c.getState()!=3) {
	                    		cr.setState(companyDao.findStateByCode(c.getCompany_code()));
	                    	}
	                    }
	                    _d=new Department();
	                    _d.setDepartment_code(cr.getDepartment_code());
	                    _d=departmentDao.findByCode(_d);
	                    if(_d!=null) {
	                    	cr.setDepartment_name(_d.getDepartment_name());
	                    }
	                    region = regionDao.findByCode(c.getRegion_code());
	                    if (region != null) {
	                        cr.setRegion_name(region.getRegion_name());
	                    }
	                    //查询日志备注
	                    log=LogService.findLastByCompanyCode(cr.getCompany_code());
	                    if(log!=null) {
	                    	cr.setIsblacklist_remark(log.getLog_remark());
	                    	cr.setIsblacklist_time(log.getAdd_time());
	                    }
	                    cList.add(cr);
	                }
	    		}
			}else {
				userAddToCompany(user,form);
				List<Company> _clist=companyDao.findByList(form);
	    		CompanyReturn cr=null;
	    		if (_clist != null && _clist.size() > 0) {
	                Region region = null;
	                int state = 0;
	                if (form.getState() != null && form.getState() == 2) {
	                    state = 2;
	                }
	                if (form.getState() != null && form.getState() == 4) {
	                    state = 4;
	                }
	                Department _d=null;
	                Log log=null;
	                for (Company c : _clist) {
	                	cr=new CompanyReturn();
	                	BeanUtils.copyProperties(c, cr);
	                    if (state != 0) {
	                        cr.setState(state);
	                    }else {
	                    	if(c.getState()!=null&&c.getState()!=3) {
	                    		cr.setState(companyDao.findStateByCode(c.getCompany_code()));
	                    	}
	                    }
	                    _d=new Department();
	                    _d.setDepartment_code(cr.getDepartment_code());
	                    _d=departmentDao.findByCode(_d);
	                    if(_d!=null) {
	                    	cr.setDepartment_name(_d.getDepartment_name());
	                    }
	                    region = regionDao.findByCode(c.getRegion_code());
	                    if (region != null) {
	                        cr.setRegion_name(region.getRegion_name());
	                    }
	                    //查询日志备注
	                    log=LogService.findLastByCompanyCode(cr.getCompany_code());
	                    if(log!=null) {
	                    	cr.setIsblacklist_remark(log.getLog_remark());
	                    	cr.setIsblacklist_time(log.getAdd_time());
	                    }
	                    cList.add(cr);
	                }
	    		}
			}
		}
    	return cList;
    }
    
    /**
     * 
     * @Title: findByUserCompanyCount
     * @Description: 根据用户权限查询对应的条目数
     * @Author tangsh
     * @DateTime 2019年12月27日 下午4:22:14
     * @param form
     * @param user
     * @return
     */
    public Integer findByUserCompanyCount(Company form,User user){
    	form.setBusiness_forms(RegularUtil.getStringToSqlString(user.getBusiness_forms(),","));
    	form.setCompanytag_codes(RegularUtil.getStringToSqlString(user.getCompanytag_codes(),","));
    	form.setCompanytype_codes(RegularUtil.getStringToSqlString(user.getCompanytype_codes(),","));
    	if(!StringUtils.isEmpty(form.getFiling_state())&&form.getFiling_state()<3) {//草稿流程单独流程
    		//新增逻辑
    		if(user.getUser_type().equals("协管员")) {
        		form.setIs_manage_or_info(1);
        		form.setOther_code(user.getUser_code());
    		}else {
    			//新增管理区域判断
        		DepartmentUser du=new DepartmentUser();
    			du.setUser_code(user.getUser_code());
    			int result=departmentUserDao.findByCount(du);
    			if(result>0) {
    				form.setIs_manage_or_info(2);
    	    		form.setOther_code(user.getUser_code());
    			}else {
    				userAddToCompany(user,form);
    			}
    		}
    		
			Integer filing_state=form.getFiling_state();
			form.setFiling_state(null);
			form.setFiling_state_str("1,2");
			Integer rstate=form.getState();
			form.setState(null);
			Integer result=companyDao.findByCount(form);
			form.setFiling_state(filing_state);
			form.setState(rstate);
			return result;
    	}
    	if(user.getUser_type().equals("协管员")) {
    		form.setIs_manage_or_info(1);
    		form.setOther_code(user.getUser_code());
    		return companyDao.findByCount(form);
		}else {
			DepartmentUser du=new DepartmentUser();
			du.setUser_code(user.getUser_code());
			int result=departmentUserDao.findByCount(du);
			if(result>0) {
				form.setIs_manage_or_info(2);
	    		form.setOther_code(user.getUser_code());
	    		return companyDao.findByCount(form);
			}
			userAddToCompany(user,form);
			return companyDao.findByCount(form);
		}
    }
    /**
     * 
     * @Title: findByCompanyEmployCount
     * @Description: 统计企业用户信息
     * @Author tangsh
     * @DateTime 2020年3月24日 下午4:31:37
     * @param form
     * @param user
     * @return
     */
    public Integer findByCompanyEmployCount(Company form,User user){
    	form.setBusiness_forms(RegularUtil.getStringToSqlString(user.getBusiness_forms(),","));
    	form.setCompanytag_codes(RegularUtil.getStringToSqlString(user.getCompanytag_codes(),","));
    	form.setCompanytype_codes(RegularUtil.getStringToSqlString(user.getCompanytype_codes(),","));
    	
    	if(!StringUtils.isEmpty(form.getFiling_state())&&form.getFiling_state()==1) {//草稿流程单独设置
    		//新增逻辑
    		if(user.getUser_type().equals("协管员")) {
        		form.setIs_manage_or_info(1);
        		form.setOther_code(user.getUser_code());
    		}else {
    			//新增管理区域判断
        		DepartmentUser du=new DepartmentUser();
    			du.setUser_code(user.getUser_code());
    			int result=departmentUserDao.findByCount(du);
    			if(result>0) {
    				form.setIs_manage_or_info(2);
    	    		form.setOther_code(user.getUser_code());
    			}else {
    				userAddToCompany(user,form);
    			}
    		}
    		
			form.setFiling_state_str("1,2");
			form.setState(null);
			return companyDao.findByCompanyEmployCount(form);
    	}
    	
    	if(user.getUser_type().equals("协管员")) {
    		form.setIs_manage_or_info(1);
    		form.setOther_code(user.getUser_code());
    		return companyDao.findByCompanyEmployCount(form);
		}else {
			DepartmentUser du=new DepartmentUser();
			du.setUser_code(user.getUser_code());
			int result=departmentUserDao.findByCount(du);
			if(result>0) {
				form.setIs_manage_or_info(2);
	    		form.setOther_code(user.getUser_code());
	    		return companyDao.findByCompanyEmployCount(form);
			}else {
				userAddToCompany(user,form);
				return companyDao.findByCompanyEmployCount(form);
			}
		}
    }
    
    /**
     * 如果页面没给区域条件 则以用户默认区域，如果带了则以页面为准
     * @Title: userAddToCompany
     * @Description: 
     * @Author sven
     * @DateTime 2020年2月5日 下午2:45:46
     * @param user
     * @param form
     */
    private void userAddToCompany(User user , Company form) {
    	//登录用户区域
		String user_province = user.getUser_province();
		String user_city = user.getUser_city();
		String user_area = user.getUser_area();
		String user_town = user.getUser_town(); 
		String user_vill = user.getUser_vill();
		
		//页面查询区域
		String province = form.getProvince();
		String city = form.getCity();
		String area = form.getArea();
		String town = form.getTown();
		String vill = form.getVill();
		
		if(StringUtils.isEmpty(province)) {
			form.setProvince(user_province);
		}
		if(StringUtils.isEmpty(city)) {
			form.setCity(user_city);
		}
		if(StringUtils.isEmpty(area)) {
			form.setArea(user_area);
		}
		if(StringUtils.isEmpty(town)) {
			form.setTown(user_town);
		}
		if(StringUtils.isEmpty(vill)) {
			form.setVill(user_vill);
		}
    }
    /**
     * 
     * @Title: findByUserCompanyFCList
     * @Description: 查询用户管理的溯源备案企业信息列表
     * @Author tangsh
     * @DateTime 2019年12月30日 下午2:27:16
     * @param form
     * @param user
     * @return
     */
    public List<CompanyReturn> findByUserCompanyFCList(Company form,User user){
    	List<CompanyReturn> cList=new ArrayList<CompanyReturn>();
    	
    	form.setBusiness_forms(RegularUtil.getStringToSqlString(user.getBusiness_forms(),","));
    	form.setCompanytag_codes(RegularUtil.getStringToSqlString(user.getCompanytag_codes(),","));
    	form.setCompanytype_codes(RegularUtil.getStringToSqlString(user.getCompanytype_codes(),","));
    	
    	if(user.getUser_type().equals("协管员")) {
    		form.setIs_manage_or_info(1);
    		form.setOther_code(user.getUser_code());
    		List<Company> _clist=companyDao.findByJoinFsList(form);
    		CompanyReturn cr=null;
    		if (_clist != null && _clist.size() > 0) {
                Region region = null;
                int state = 0;
                if (form.getState() != null && form.getState() == 2) {
                    state = 2;
                }
                if (form.getState() != null && form.getState() == 4) {
                    state = 4;
                }
                Department _d=null;
                for (Company c : _clist) {
                	cr=new CompanyReturn();
                	BeanUtils.copyProperties(c, cr);
                    if (state != 0) {
                        cr.setState(state);
                    }
                    _d=new Department();
                    _d.setDepartment_code(cr.getDepartment_code());
                    _d=departmentDao.findByCode(_d);
                    if(_d!=null) {
                    	cr.setDepartment_name(_d.getDepartment_name());
                    }
                    region = regionDao.findByCode(c.getRegion_code());
                    if (region != null) {
                        cr.setRegion_name(region.getRegion_name());
                    }
                    cList.add(cr);
                }
            }
		}else {
			DepartmentUser du=new DepartmentUser();
			du.setUser_code(user.getUser_code());
			int result=departmentUserDao.findByCount(du);
			if(result>0) {
				form.setIs_manage_or_info(2);
	    		form.setOther_code(user.getUser_code());
	    		List<Company> _clist=companyDao.findByJoinFsList(form);
	    		CompanyReturn cr=null;
	    		if (_clist != null && _clist.size() > 0) {
	                Region region = null;
	                int state = 0;
	                if (form.getState() != null && form.getState() == 2) {
	                    state = 2;
	                }
	                if (form.getState() != null && form.getState() == 4) {
	                    state = 4;
	                }
	                for (Company c : _clist) {
	                	cr=new CompanyReturn();
	                	BeanUtils.copyProperties(c, cr);
	                    if (state != 0) {
	                        cr.setState(state);
	                    }
	                    region = regionDao.findByCode(c.getRegion_code());
	                    if (region != null) {
	                        cr.setRegion_name(region.getRegion_name());
	                    }
	                    cList.add(cr);
	                }
	    		}
			}else {
				userAddToCompany(user,form);
				List<Company> _clist=companyDao.findByJoinFsList(form);
	    		CompanyReturn cr=null;
	    		if (_clist != null && _clist.size() > 0) {
	                Region region = null;
	                int state = 0;
	                if (form.getState() != null && form.getState() == 2) {
	                    state = 2;
	                }
	                if (form.getState() != null && form.getState() == 4) {
	                    state = 4;
	                }
	                for (Company c : _clist) {
	                	cr=new CompanyReturn();
	                	BeanUtils.copyProperties(c, cr);
	                    if (state != 0) {
	                        cr.setState(state);
	                    }
	                    region = regionDao.findByCode(c.getRegion_code());
	                    if (region != null) {
	                        cr.setRegion_name(region.getRegion_name());
	                    }
	                    cList.add(cr);
	                }
	    		}
			}
		}
    	return cList;
    }
    
    /**
     * 
     * @Title: findByUserCompanyFCCount
     * @Description: 查询用户管理的溯源备案企业信息总数
     * @Author tangsh
     * @DateTime 2019年12月30日 下午2:30:31
     * @param form
     * @param user
     * @return
     */
    public Integer findByUserCompanyFCCount(Company form,User user){
    	if(user.getUser_type().equals("协管员")) {
    		form.setIs_manage_or_info(1);
    		form.setOther_code(user.getUser_code());
    		return companyDao.findByJoinFsCount(form);
		}else {
			DepartmentUser du=new DepartmentUser();
			du.setUser_code(user.getUser_code());
			int result=departmentUserDao.findByCount(du);
			if(result>0) {
				form.setIs_manage_or_info(2);
	    		form.setOther_code(user.getUser_code());
	    		return companyDao.findByJoinFsCount(form);
			}else {
				userAddToCompany(user,form);
				return companyDao.findByJoinFsCount(form);
			}
		}
    }
    
    /**
     * 
     * @Title: saveCompany
     * @Description: 新增企业备案
     * @Author tangsh
     * @DateTime 2020年3月24日 上午9:45:45
     * @param form
     * @param suser
     * @return
     * @throws ParseException
     */
    public int saveCompany(Company form,User suser) throws ParseException {
    	User userTemp = new User();
    	userTemp.setUser_code(form.getUser_code());
    	userTemp = userService.findByCode(userTemp);
        if(userTemp==null) {
        	userTemp = new User();
        }
        /*
        //将创建档案用户的地址绑定在档案上
        form.setProvince(user.getUser_province());
        form.setCity(user.getUser_city());
        form.setTown(user.getUser_town());
        form.setVill(user.getUser_vill());
        form.setArea(user.getUser_area());*/
        
        //初始化档案状态
        form.setBusiness_type(1);
        form.setState(1);
        form.setDate_source(1);
        //转义主营兼营等数据
        form.setMain_subject(RegularUtil.getListToString(form.getMain_subject_list(),","));
        form.setSub_subject(RegularUtil.getListToString(form.getSub_subject_list(),","));
        form.setPlaces(RegularUtil.getListToString(form.getPlaces_list(),","));
        form.setCategory_names(RegularUtil.getListToString(form.getCategory_names_list(),","));
        form.setSubjectdetail_codes(RegularUtil.getListToString(form.getSubjectdetail_codes_list(),","));
        form.setFoodmake_names(RegularUtil.getListToString(form.getFoodmake_names_list(),","));
        form.setFoodsell_names(RegularUtil.getListToString(form.getFoodsell_names_list(),","));
        form.setProduce_form(RegularUtil.getListToString(form.getProduce_form_list(),","));
        form.setProducetype_codes(RegularUtil.getListToString(form.getProducetype_codes_list(),","));
        form.setUser_code_manage(RegularUtil.getListToString(form.getUser_code_manage_list(),","));
        form.setUser_moblephone_manage(RegularUtil.getListToString(form.getUser_moblephone_manage_list(),","));
        form.setUser_code_info(RegularUtil.getListToString(form.getUser_code_info_list(),","));
        form.setUser_moblephone_info(RegularUtil.getListToString(form.getUser_moblephone_info_list(),","));
        form.setBusiness_range(RegularUtil.getListToString(form.getBusiness_range_list(),","));
        form.setFiling_state_time(DateUtil.getCurrentTime());
        
        //更新坐标信息
        if(StringUtils.isEmpty(form.getLatitude())) {
        	String address=form.getAddress();
        	if(!StringUtils.isEmpty(address)) {
        		String[] arr=new String[2];
	        	arr=BaiDuMapUtil.getLngAndLatByLoc(address);
	        	if(!StringUtils.isEmpty(arr[0])) {
	        		form.setLongitude(arr[0]);
		        	form.setLatitude(arr[1]);
	        	}else {
	        		address=form.getProvince()+form.getCity()+form.getArea()+form.getTown()+form.getVill();
		        	arr=BaiDuMapUtil.getLngAndLatByLoc(address);
		        	form.setLongitude(arr[0]);
		        	form.setLatitude(arr[1]);
	        	}
        	}else {
	        	address=form.getProvince()+form.getCity()+form.getArea()+form.getTown()+form.getVill();
	        	String[] arr=new String[2];
	        	arr=BaiDuMapUtil.getLngAndLatByLoc(address);
	        	form.setLongitude(arr[0]);
	        	form.setLatitude(arr[1]);
        	}
        }
        //判断是否存在company_code如果存在，那么是保存草稿的档案
        String code = form.getCompany_code();
        int i =0;
        //如果不是生成则code
        if (form.getCompany_code() == null || form.getCompany_code() == "" || form.getCompany_code().length() != 25) {
            code = CodeUtil.getSystemCode("company");
            form.setCompany_code(code);
            i = companyDao.save(form);
            
            //查询用户手机号是否已经注册,如果已经注册则不新增
        	String user_loginname = form.getMobilephone();
        	User user = new User();
        	user.setUser_loginname(user_loginname);
        	user = userService.findByLoginNameAndPass(user);
        	if(StringUtils.isEmpty(user)) {
        		user = new User();
        		user.setUser_code(CodeUtil.getSystemCode("user"));
        		user.setUser_loginname(user_loginname);
        		user.setUser_loginpass(Md5Util.stringToMD5(user_loginname.substring(5, 11)));
        		user.setUser_type("食品经营者");
        		user.setUser_name(form.getOperator());
        		user.setUser_enname_short(CharacterUtil.getAllSpell(user.getUser_name()));
        		user.setUser_idcard_logo_front("images/user_idcard_logo_front.png");
        		user.setUser_idcard_logo_back("images/user_idcard_logo_back.png");
        		user.setUser_business_logo_url("images/user_business_logo_url.png");
        		user.setUser_food_logo_url("images/user_food_logo_url.png");
        		user.setUser_logo_url("images/user_logo_url.png");
        		user.setUser_sex("男");
        		user.setUser_mobilephone(user_loginname);
        		user.setUser_province(form.getProvince());
        		user.setUser_city(form.getCity());
        		user.setUser_area(form.getArea());
        		user.setUser_town(form.getTown());
        		user.setUser_vill(form.getVill());
        		user.setUser_audit_state(2);
        		user.setCompany_code(code);
        		userService.saveCompanyUser(user);
        	}
        	
        }else {
        	//判断是否已经审核
        	Company c=companyDao.findByCode(form);
        	if(c==null||c.getFiling_state()>2) {
        		return 0;
        	}
        	//删除对应的信息
        	accessoryDao.deleteByCompany(form.getCompany_code());
        	companyEmployDao.deleteByCompany(form.getCompany_code());
        	form.setCompany_code(code);
        	i = companyDao.update(form);
        }
        
        LogService.saveObject("更新", "已归档", form.getUnpass_cause(), suser.getUser_code(), suser.getUser_name(), form.getCompany_code());
        //创建报备编号
        /*String bzcode = "510100";
        if (!StringUtils.isEmpty(form.getRegion_code())) {
            bzcode = form.getRegion_code();
            if (bzcode.length() > 7) {
                bzcode = bzcode.substring(1, 7);
            } else {
                bzcode = bzcode.substring(1, bzcode.length());
            }
        }

        String record_code = findByLast();
        if (StringUtils.isEmpty(record_code)) {
            record_code = "000001";
        } else {
            record_code = CodeUtil.getSortNum(record_code);
        }

        if (form.getCompanytag_code().equals("1")) {
            bzcode += "餐" + DateUtil.getDateYear() + record_code;
        } else if (form.getCompanytag_code().equals("2")) {
            bzcode += "销" + DateUtil.getDateYear() + record_code;
        } else if (form.getCompanytag_code().equals("3")) {
            bzcode += "坊" + DateUtil.getDateYear() + record_code;
        } else {
            if (!StringUtils.isEmpty(form.getStall_type()) && form.getStall_type() == 2) {
                bzcode += "固" + DateUtil.getDateYear() + record_code;
            } else {
                bzcode += "流" + DateUtil.getDateYear() + record_code;
            }
        }
        form.setRecord_code(bzcode);*/
        if (i > 0) {
        	//保存订单成功后保存上传附件、人员
            List<Accessory> accessoryList = form.getAccessoryList();
            if (accessoryList != null && accessoryList.size() > 0) {
                for (Accessory accessory : accessoryList) {
                    accessory.setCompany_code(code);
                    accessory.setAccessory_type(accessory.getName());
                    accessory.setAccessory_url(accessory.getUrl());
                    accessory.setAccessory_code(CodeUtil.getSystemCode("accessory"));
                    accessoryService.save(accessory);
                }
            }
            List<CompanyEmploy> list = form.getCompanyEmployList();
            if (list != null && list.size() > 0) {
                for (CompanyEmploy companyEmploy : list) {
                	companyEmploy.setCompany_code(code);
                    companyEmploy.setEmploy_code(CodeUtil.getSystemCode("companyEmploy"));
                    if(!StringUtils.isEmpty(companyEmploy.getUser_health_datedue())) {
                    	companyEmploy.setEmploy_ishealth(1);
                    }
                    companyEmployDao.save(companyEmploy);
                }
            }
            
            //发送消息日志
            messLogService.saveMessLogByOtherCode(1, form.getCompany_code(), form.getCompany_code(), suser);
            return 1;
        }
        return 0;
    }
    
    /**
     * 
     * @Title: saveOpenCompany
     * @Description: 新增开放接口企业备案
     * @Author tangsh
     * @DateTime 2020年5月21日 下午3:08:47
     * @param form
     * @param suser
     * @return
     * @throws ParseException
     */
    public int saveOpenCompany(Company form,User suser) throws ParseException {
    	form.setUser_code(suser.getUser_code());//这里默认添加用户
        //初始化档案状态
        form.setBusiness_type(1);
        form.setState(1);
        form.setDate_source(1);
        //转义主营兼营等数据
        form.setMain_subject(RegularUtil.getListToString(form.getMain_subject_list(),","));
        form.setSub_subject(RegularUtil.getListToString(form.getSub_subject_list(),","));
        form.setPlaces(RegularUtil.getListToString(form.getPlaces_list(),","));
        form.setCategory_names(RegularUtil.getListToString(form.getCategory_names_list(),","));
        form.setSubjectdetail_codes(RegularUtil.getListToString(form.getSubjectdetail_codes_list(),","));
        form.setFoodmake_names(RegularUtil.getListToString(form.getFoodmake_names_list(),","));
        form.setFoodsell_names(RegularUtil.getListToString(form.getFoodsell_names_list(),","));
        form.setProduce_form(RegularUtil.getListToString(form.getProduce_form_list(),","));
        form.setProducetype_codes(RegularUtil.getListToString(form.getProducetype_codes_list(),","));
        form.setUser_code_manage(RegularUtil.getListToString(form.getUser_code_manage_list(),","));
        form.setUser_moblephone_manage(RegularUtil.getListToString(form.getUser_moblephone_manage_list(),","));
        form.setUser_code_info(RegularUtil.getListToString(form.getUser_code_info_list(),","));
        form.setUser_moblephone_info(RegularUtil.getListToString(form.getUser_moblephone_info_list(),","));
        form.setBusiness_range(RegularUtil.getListToString(form.getBusiness_range_list(),","));
        form.setFiling_state_time(DateUtil.getCurrentTime());
        
        //更新坐标信息
        if(StringUtils.isEmpty(form.getLatitude())) {
        	String address=form.getAddress();
        	if(!StringUtils.isEmpty(address)) {
        		String[] arr=new String[2];
	        	arr=BaiDuMapUtil.getLngAndLatByLoc(address);
	        	if(!StringUtils.isEmpty(arr[0])) {
	        		form.setLongitude(arr[0]);
		        	form.setLatitude(arr[1]);
	        	}else {
	        		address=form.getProvince()+form.getCity()+form.getArea()+form.getTown()+form.getVill();
		        	arr=BaiDuMapUtil.getLngAndLatByLoc(address);
		        	form.setLongitude(arr[0]);
		        	form.setLatitude(arr[1]);
	        	}
        	}else {
	        	address=form.getProvince()+form.getCity()+form.getArea()+form.getTown()+form.getVill();
	        	String[] arr=new String[2];
	        	arr=BaiDuMapUtil.getLngAndLatByLoc(address);
	        	form.setLongitude(arr[0]);
	        	form.setLatitude(arr[1]);
        	}
        }
        //判断是否存在company_code如果存在，那么是保存草稿的档案
        String code = form.getCompany_code();
        //如果不是生成则code
        int i = companyDao.save(form);
        //查询用户手机号是否已经注册,如果已经注册则不新增
    	String user_loginname = form.getMobilephone();
    	User user = new User();
    	user.setUser_loginname(user_loginname);
    	user = userService.findByLoginNameAndPass(user);
    	if(StringUtils.isEmpty(user)) {
    		user = new User();
    		user.setUser_code(CodeUtil.getSystemCode("user"));
    		user.setUser_loginname(user_loginname);
    		user.setUser_loginpass(Md5Util.stringToMD5(user_loginname.substring(5, 11)));
    		user.setUser_type("食品经营者");
    		user.setUser_name(form.getOperator());
    		user.setUser_enname_short(CharacterUtil.getAllSpell(user.getUser_name()));
    		user.setUser_idcard_logo_front("images/user_idcard_logo_front.png");
    		user.setUser_idcard_logo_back("images/user_idcard_logo_back.png");
    		user.setUser_business_logo_url("images/user_business_logo_url.png");
    		user.setUser_food_logo_url("images/user_food_logo_url.png");
    		user.setUser_logo_url("images/user_logo_url.png");
    		user.setUser_sex("男");
    		user.setUser_mobilephone(user_loginname);
    		user.setUser_province(form.getProvince());
    		user.setUser_city(form.getCity());
    		user.setUser_area(form.getArea());
    		user.setUser_town(form.getTown());
    		user.setUser_vill(form.getVill());
    		user.setUser_audit_state(2);
    		user.setCompany_code(code);
    		userService.saveCompanyUser(user);
    	}
        //LogService.saveObject("更新", "已归档", form.getUnpass_cause(), suser.getUser_code(), suser.getUser_name(), form.getCompany_code());
        if (i > 0) {
            //发送消息日志
            //messLogService.saveMessLogByOtherCode(1, form.getCompany_code(), form.getCompany_code(), suser);
            return 1;
        }
        return 0;
    }
    
    /**
     * 
     * @Title: saveUserCompany
     * @Description: 微信端用户新增备案
     * @Author tangsh
     * @DateTime 2020年5月21日 下午3:34:14
     * @param form
     * @param suser
     * @return
     * @throws ParseException
     */
    public int saveUserCompany(Company form,User suser) throws ParseException {
        //初始化档案状态
        form.setFiling_state(2);
        form.setBusiness_state(1);
		form.setBusiness_type(1);
		form.setState(1);
		form.setExamine("3");
		form.setRecord_time(form.getIssue_time());
		form.setDate_source(2);
        //转义主营兼营等数据
        form.setMain_subject(RegularUtil.getListToString(form.getMain_subject_list(),","));
        form.setSub_subject(RegularUtil.getListToString(form.getSub_subject_list(),","));
        form.setPlaces(RegularUtil.getListToString(form.getPlaces_list(),","));
        form.setCategory_names(RegularUtil.getListToString(form.getCategory_names_list(),","));
        form.setSubjectdetail_codes(RegularUtil.getListToString(form.getSubjectdetail_codes_list(),","));
        form.setFoodmake_names(RegularUtil.getListToString(form.getFoodmake_names_list(),","));
        form.setFoodsell_names(RegularUtil.getListToString(form.getFoodsell_names_list(),","));
        form.setProduce_form(RegularUtil.getListToString(form.getProduce_form_list(),","));
        form.setProducetype_codes(RegularUtil.getListToString(form.getProducetype_codes_list(),","));
        form.setUser_code_manage(RegularUtil.getListToString(form.getUser_code_manage_list(),","));
        form.setUser_moblephone_manage(RegularUtil.getListToString(form.getUser_moblephone_manage_list(),","));
        form.setUser_code_info(RegularUtil.getListToString(form.getUser_code_info_list(),","));
        form.setUser_moblephone_info(RegularUtil.getListToString(form.getUser_moblephone_info_list(),","));
        form.setBusiness_range(RegularUtil.getListToString(form.getBusiness_range_list(),","));
        form.setFiling_state_time(DateUtil.getCurrentTime());
        
        //更新坐标信息
        if(StringUtils.isEmpty(form.getLatitude())) {
        	String address=form.getAddress();
        	if(!StringUtils.isEmpty(address)) {
        		String[] arr=new String[2];
	        	arr=BaiDuMapUtil.getLngAndLatByLoc(address);
	        	if(!StringUtils.isEmpty(arr[0])) {
	        		form.setLongitude(arr[0]);
		        	form.setLatitude(arr[1]);
	        	}else {
	        		address=form.getProvince()+form.getCity()+form.getArea()+form.getTown()+form.getVill();
		        	arr=BaiDuMapUtil.getLngAndLatByLoc(address);
		        	form.setLongitude(arr[0]);
		        	form.setLatitude(arr[1]);
	        	}
        	}else {
	        	address=form.getProvince()+form.getCity()+form.getArea()+form.getTown()+form.getVill();
	        	String[] arr=new String[2];
	        	arr=BaiDuMapUtil.getLngAndLatByLoc(address);
	        	form.setLongitude(arr[0]);
	        	form.setLatitude(arr[1]);
        	}
        }
        String code=CodeUtil.getSystemCode("company");
        form.setCompany_code(code);
        int i = companyDao.save(form);
        LogService.saveObject("提交", "待审核", form.getUnpass_cause(), suser.getUser_code(), suser.getUser_name(), form.getCompany_code());
        if (i > 0) {
        	//更新用户信息
        	User _user=new User();
        	_user.setUser_code(suser.getUser_code());
        	_user.setCompany_code(code);
        	userDao.update(_user);
        	
        	//保存订单成功后保存上传附件、人员
            List<Accessory> accessoryList = form.getAccessoryList();
            if (accessoryList != null && accessoryList.size() > 0) {
                for (Accessory accessory : accessoryList) {
                    accessory.setCompany_code(code);
                    accessory.setAccessory_type(accessory.getName());
                    accessory.setAccessory_url(accessory.getUrl());
                    accessory.setAccessory_code(CodeUtil.getSystemCode("accessory"));
                    accessoryService.save(accessory);
                }
            }
            List<CompanyEmploy> list = form.getCompanyEmployList();
            if (list != null && list.size() > 0) {
                for (CompanyEmploy companyEmploy : list) {
                	companyEmploy.setCompany_code(code);
                    companyEmploy.setEmploy_code(CodeUtil.getSystemCode("companyEmploy"));
                    if(!StringUtils.isEmpty(companyEmploy.getUser_health_datedue())) {
                    	companyEmploy.setEmploy_ishealth(1);
                    }
                    companyEmployDao.save(companyEmploy);
                }
            }
            return 1;
        }
        return 0;
    }

    /**
     * 
     * @Title: findByFsSearch
     * @Description: 查询企业溯源列表
     * @Author tangsh
     * @DateTime 2020年3月24日 上午9:45:27
     * @param form
     * @return
     */
    public List<CompanyReturn> findByFsSearch(Company form) {
        List<CompanyReturn> cList = companyDao.findByFsSearch(form);
        String d_name="";
        if(!StringUtils.isEmpty(form.getDepartment_code())) {
        	Department d=new Department();
        	d.setDepartment_code(form.getDepartment_code());
        	d=departmentDao.findByCode(d);
        	if(d!=null) {
        		d_name=d.getDepartment_name();
        	}
        }
        if (cList != null && cList.size() > 0) {
            Region region = null;
            Department dt=null;
            for (CompanyReturn c : cList) {
                region = regionDao.findByCode(c.getRegion_code());
                if (region != null) {
                    c.setRegion_name(region.getRegion_name());
                }
                if(!StringUtils.isEmpty(d_name)) {
                	c.setDepartment_name(d_name);
                }else {
                	if(!StringUtils.isEmpty(c.getDepartment_code())) {
                		dt=new Department();
                		dt.setDepartment_code(c.getDepartment_code());
                		dt=departmentDao.findByCode(dt);
                    	if(dt!=null) {
                    		c.setDepartment_name(dt.getDepartment_name());
                    	}
                	}
                }
            }
        }
        return cList;
    }

    /**
     * 
     * @Title: findById
     * @Description: 查询详情
     * @Author tangsh
     * @DateTime 2020年3月24日 上午9:45:19
     * @param t
     * @return
     */
    public Company findById(Company t) {
        Company c = findByCode(t);
        if (c != null) {
        	if(c.getState()!=null&&c.getState()!=3) {
        		c.setState(companyDao.findStateByCode(c.getCompany_code()));
        	}
        	if(!StringUtils.isEmpty(c.getRegion_code())) {
        		Region region = regionDao.findByCode(c.getRegion_code());
                if (region != null) {
                    c.setRegion_name(region.getRegion_name());
                }
        	}
        	if(!StringUtils.isEmpty(c.getDepartment_code())) {
        		Department department = departmentDao.findByCode(c.getDepartment_code());
                if (department != null) {
                    c.setDepartment_name(department.getDepartment_name());
                }
        	}
        	if(!StringUtils.isEmpty(c.getUser_code_manage())) {
        		User u = new User();
                u.setUser_code(c.getUser_code_manage());
                u = userDao.findByCode(u);
                if (u != null) {
                    c.setUser_name_manage(u.getUser_name());
                    c.setUser_moblephone_manage(u.getUser_mobilephone());
                }
        	}
        	if(!StringUtils.isEmpty(c.getUser_code_info())) {
        		User  u= new User();
                u.setUser_code(c.getUser_code_info());
                u = userDao.findByCode(u);
                if (u != null) {
                    c.setUser_name_info(u.getUser_name());
                    c.setUser_moblephone_info(u.getUser_mobilephone());
                }
        	}
            
            CompanyEmploy ce = new CompanyEmploy();
            ce.setCompany_code(c.getCompany_code());
            c.setCompanyEmployList(companyEmployDao.findByAll(ce)==null?new ArrayList<>():companyEmployDao.findByAll(ce));


            Accessory accessory = new Accessory();
            accessory.setCompany_code(c.getCompany_code());
            c.setAccessoryList(accessoryDao.findByAll(accessory)==null?new ArrayList<>():accessoryDao.findByAll(accessory));
        }

        return c;
    }

    /**
     * 
     * @Title: examine
     * @Description: 1：注销、2：驳回、3：通过操作
     * @Author tangsh
     * @DateTime 2020年3月24日 上午9:44:55
     * @param form
     * @param user
     * @return
     */
    public boolean examine(Company form,User user) {
    	if(form.getExamine().equals("1")) {
    		form.setState(3);
    		form.setBusiness_type(3);
    	}else if(form.getExamine().equals("2")) {
    		form.setFiling_state(4);
    		form.setFiling_state_time(DateUtil.getDateYearMonthDate());
    	}else if(form.getExamine().equals("3")) {
    		form.setFiling_state(3);
    		form.setFiling_state_time(DateUtil.getDateYearMonthDate());
    	}
    	Company _company=companyDao.findByCode(form);
        int result = companyDao.update(form);
        if (result <= 0) {
            return false;
        }
        
        if(form.getExamine().equals("1")) {
        	CompanySnap cs=new CompanySnap();
        	BeanUtils.copyProperties(_company, cs);
        	companySnapDao.save(cs);
        	LogService.saveObject("注销", "注销", null, user.getUser_code(), user.getUser_name(), form.getCompany_code());
    	}else if(form.getExamine().equals("2")) {
    		LogService.saveObject("更新", "已驳回", form.getUnpass_cause(), user.getUser_code(), user.getUser_name(), form.getCompany_code());
    		messLogService.saveMessLogByOtherCode(2, form.getCompany_code(), form.getCompany_code(), user);
    	}else if(form.getExamine().equals("3")) {
    		LogService.saveObject("更新", "已归档", null, user.getUser_code(), user.getUser_name(), form.getCompany_code());
    	}
        return true;
    }

    /**
     * 
     * @Title: change
     * @Description: 变更
     * @Author tangsh
     * @DateTime 2020年3月24日 上午9:44:47
     * @param form
     * @param user
     * @return
     */
    public Boolean change(Company form,User user) {
    	Company _c=companyDao.findByCode(form);
        //修改变更数据
        form.setProducetype_codes(RegularUtil.getListToString(form.getProducetype_codes_list(),","));
        form.setProduce_form(RegularUtil.getListToString(form.getProduce_form_list(),","));
        form.setMain_subject(RegularUtil.getListToString(form.getMain_subject_list(),","));
        form.setSub_subject(RegularUtil.getListToString(form.getSub_subject_list(),","));
        form.setCategory_names(RegularUtil.getListToString(form.getCategory_names_list(),","));
        form.setSubjectdetail_codes(RegularUtil.getListToString(form.getSubjectdetail_codes_list(),","));
        form.setFoodmake_names(RegularUtil.getListToString(form.getFoodmake_names_list(),","));
        form.setFoodsell_names(RegularUtil.getListToString(form.getFoodsell_names_list(),","));
        
        form.setProduce_form(RegularUtil.getListToString(form.getProduce_form_list(),","));
        form.setProducetype_codes(RegularUtil.getListToString(form.getProducetype_codes_list(),","));
        form.setUser_code_manage(RegularUtil.getListToString(form.getUser_code_manage_list(),","));
        form.setUser_moblephone_manage(RegularUtil.getListToString(form.getUser_moblephone_manage_list(),","));
        form.setUser_code_info(RegularUtil.getListToString(form.getUser_code_info_list(),","));
        form.setUser_moblephone_info(RegularUtil.getListToString(form.getUser_moblephone_info_list(),","));
        form.setBusiness_range(RegularUtil.getListToString(form.getBusiness_range_list(),","));
        //开始变更
        //form.setFiling_state(2);
        form.setBusiness_type(4);
        Company _cy=companyDao.findByCode(form); 
        int i = companyDao.update(form);
        if (i != 0) {
        	String note="";
        	if(_c.getCompanytype_code().equals("1001574645421581000000001")) {
        		if(!_c.getProduce_form().equals(form.getProduce_form())) {
            		note+="生产形式由【"+_c.getProduce_form()+"】变更为【"+form.getProduce_form()+"】;";
            	}
            	if(!_c.getProducetype_codes().equals(form.getProducetype_codes())) {
            		note+="生产类别由【"+_c.getProducetype_codes()+"】变更为【"+form.getProducetype_codes()+"】;";
            	}
        	}
        	if(_c.getCompanytype_code().equals("1001574645421581000000002")) {
        		if(!_c.getMain_subject().equals(form.getMain_subject())) {
        			note+="主营项目由【"+_c.getMain_subject()+"】变更为【"+form.getMain_subject()+"】;";
        		}
        		if(!_c.getSub_subject().equals(form.getSub_subject())) {
        			note+="兼营项目由【"+_c.getSub_subject()+"】变更为【"+form.getSub_subject()+"】;";
        		}
        	}
        	if(_c.getCompanytype_code().equals("1001574645421581000000003")) {
        		if(!_c.getBusiness_range().equals(form.getBusiness_range())) {
        			note+="经营范围由【"+_c.getBusiness_range()+"】变更为【"+form.getBusiness_range()+"】;";
        		}
        	}
        	if(StringUtils.isEmpty(form.getIsblacklist_remark())&&!StringUtils.isEmpty(note)) {
        		form.setIsblacklist_remark(note);
        	}
        	CompanySnap cs=new CompanySnap();
        	BeanUtils.copyProperties(_cy, cs);
        	companySnapDao.save(cs);
        	LogService.saveObject("变更", "变更信息", form.getIsblacklist_remark(), user.getUser_code(), user.getUser_name(), form.getCompany_code());
        	return true;
        }
        return false;
    }
    
    /**
     * 
     * @Title: continueTime
     * @Description: 延续
     * @Author tangsh
     * @DateTime 2020年3月25日 下午3:38:10
     * @param form
     * @param user
     * @return
     */
    public Boolean continueTime(Company form,User user) {
        form.setBusiness_type(2);
        int i = companyDao.update(form);
        if (i != 0) {
        	LogService.saveObject("更新", "延续", "延续企业备案时间", user.getUser_code(), user.getUser_name(), form.getCompany_code());
        	return true;
        }
        return false;
    }
    
    /**
     * 
     * @Title: detail
     * @Description: 查询详情
     * @Author tangsh
     * @DateTime 2020年3月24日 上午9:44:34
     * @param form
     * @return
     */
    public CompanyReturn detail(Company form) {
        CompanyReturn cr = new CompanyReturn();
        //查询档案主体
        Company detail = findById(form);
        if(detail==null) {
        	return cr;
        }
        detail = setEmpAndAccReturnList(detail);
        detail = setSubjectCodetoName(detail);
        User user=new User();
        if(!StringUtils.isEmpty(detail.getUser_code_manage())) {
        	user.setUser_codes(RegularUtil.getStringToSqlString(detail.getUser_code_manage(), ","));
            user.setUser_screens("user_name");
            detail.setUser_name_manage_list(userDao.findByUserCol(user));
            user.setUser_screens("user_mobilephone");
            detail.setUser_moblephone_manage_list(userDao.findByUserCol(user));
        }
        if(!StringUtils.isEmpty(detail.getUser_code_info())) {
        	user.setUser_codes(RegularUtil.getStringToSqlString(detail.getUser_code_info(), ","));
            user.setUser_screens("user_name");
            detail.setUser_name_info_list(userDao.findByUserCol(user));
            user.setUser_screens("user_mobilephone");
            detail.setUser_moblephone_info_list(userDao.findByUserCol(user));
        }
        //复制为reutn对象
        BeanUtils.copyProperties(detail, cr);
        //查询日志
        Log log=new Log();
        log.setCompany_code(detail.getCompany_code());
        cr.setLoglist(LogService.findByAll(log));
        return cr;
    }

    /**
     * 
     * @Title: drafts
     * @Description: 查询草稿箱
     * @Author tangsh
     * @DateTime 2020年3月24日 上午9:44:20
     * @param form
     * @return
     */
    public CompanyReturn drafts(Company form) {
        CompanyReturn cr = new CompanyReturn();
        //查询档案主体
        Company detail = findById(form);
        if(detail==null){
            return null;
        }
        //转移字段到指定字段
        detail.setMain_subject_list(RegularUtil.getStringToList(detail.getMain_subject(),","));
        detail.setSubjectdetail_codes_list(RegularUtil.getStringToList(detail.getSubjectdetail_codes(),","));
        detail.setSub_subject_list(RegularUtil.getStringToList(detail.getSub_subject(),","));
        detail.setProduce_form_list(RegularUtil.getStringToList(detail.getProduce_form(),","));
        detail.setProducetype_codes_list(RegularUtil.getStringToList(detail.getProducetype_codes(),","));
        detail.setFoodmake_names_list(RegularUtil.getStringToList(detail.getFoodmake_names(),","));
        detail.setFoodsell_names_list(RegularUtil.getStringToList(detail.getFoodsell_names(),","));
        detail.setPlaces_list(RegularUtil.getStringToList(detail.getPlaces(),","));
        detail.setCategory_names_list(RegularUtil.getStringToList(detail.getCategory_names(),","));
        detail.setBusiness_range_list(RegularUtil.getStringToList(detail.getBusiness_range(),","));
        
        detail.setUser_code_manage_list(RegularUtil.getStringToList(detail.getUser_code_manage(),","));
        detail.setUser_moblephone_manage_list(RegularUtil.getStringToList(detail.getUser_moblephone_manage(),","));
        detail.setUser_code_info_list(RegularUtil.getStringToList(detail.getUser_code_info(),","));
        detail.setUser_moblephone_info_list(RegularUtil.getStringToList(detail.getUser_moblephone_info(),","));
        
        
        detail = setSubjectCodetoName(detail);
        detail = setEmpAndAccReturnList(detail);
        detail.setAccessoryList(new ArrayList<>());
        detail.setCompanyEmployList(new ArrayList<>());
        //复制为reutn对象
        BeanUtils.copyProperties(detail, cr);
        return cr;
    }

    /**
     * 
     * @Title: setSubjectCodetoName
     * @Description: 设置经营信息名称
     * @Author tangsh
     * @DateTime 2020年3月24日 上午9:46:38
     * @param detail
     * @return
     */
    private Company setSubjectCodetoName(Company detail) {
        //查询详细经营信息，通过详细经营code查出name拼接为“name1，name2，name3 ...”
        if (detail.getSubjectdetail_codes() != null && detail.getSubjectdetail_codes().length() >= 1) {
            String[] split = detail.getSubjectdetail_codes().split(",");
            String name = "";
            SubjectDetail sb = new SubjectDetail();
            for (String code : split) {
                sb.setSubjectdetail_code(code);
                SubjectDetail subjectDetail = subjectDetailService.findByCode(sb);
                if (subjectDetail != null) {
                    name += subjectDetail.getSubjectdetail_name() + ",";
                }
            }
            if (name.length() > 1) {
                detail.setSubjectdetail_codes(name.substring(0, name.length() - 1));
            }
        }
        return detail;
    }

    /**
     * 
     * @Title: setEmpAndAccReturnList
     * @Description: 设置人员信息
     * @Author tangsh
     * @DateTime 2020年3月24日 上午9:46:54
     * @param c
     * @return
     */
    private Company setEmpAndAccReturnList(Company c) {

        List<CompanyEmploy> employList = c.getCompanyEmployList();
        List<CompanyEmployReturn> companyEmployReturns = new ArrayList<>();
        for (CompanyEmploy employ : employList) {
            CompanyEmployReturn cer = new CompanyEmployReturn();
            BeanUtils.copyProperties(employ, cer);
            companyEmployReturns.add(cer);
        }

        List<Accessory> accessoryList = c.getAccessoryList();
        c.setCompanyEmployReturnList(companyEmployReturns);
        List<AccessoryReturn> accessoryReturns = null;
        AccessoryReturn ar =null;
        if(accessoryList!=null&&accessoryList.size()>0) {
        	accessoryReturns = new ArrayList<AccessoryReturn>();
        	for (Accessory accessory1 :accessoryList) {
                ar = new AccessoryReturn();
                ar.setName(accessory1.getAccessory_type());
                ar.setUrl(accessory1.getAccessory_url());
                accessoryReturns.add(ar);
            }
        	c.setAccessoryReturnList(accessoryReturns);
        }
        
        return c;
    }
    
    /**
     * 
     * @Title: updateBlacklist
     * @Description: 更新黑名单
     * @Author tangsh
     * @DateTime 2020年3月24日 上午9:47:11
     * @param form
     * @param user
     * @return
     */
    public boolean updateBlacklist(Company form,User user) {
        int result = companyDao.update(form);
        if (result <= 0) {
            return false;
        }
        if(form.getIsblacklist()==1) {
    		LogService.saveObject("更改", "加入白名单", form.getIsblacklist_remark(), user.getUser_code(), user.getUser_name(), form.getCompany_code());
    	}else {
    		LogService.saveObject("更改", "加入黑名单", form.getIsblacklist_remark(), user.getUser_code(), user.getUser_name(), form.getCompany_code());
    	}
        return true;
    }
    
    
    public Integer updateUserCompany(Company form,User suser) {
    	form.setUser_code_manage(RegularUtil.getListToString(form.getUser_code_manage_list(),","));
        form.setUser_moblephone_manage(RegularUtil.getListToString(form.getUser_moblephone_manage_list(),","));
        form.setUser_code_info(RegularUtil.getListToString(form.getUser_code_info_list(),","));
        form.setUser_moblephone_info(RegularUtil.getListToString(form.getUser_moblephone_info_list(),","));
    	
        Integer result = companyDao.update(form);
    	if (result > 0) {
    		String code=form.getCompany_code();
    		//删除对应的信息
        	accessoryDao.deleteByCompany(form.getCompany_code());
        	companyEmployDao.deleteByCompany(form.getCompany_code());
        	//保存订单成功后保存上传附件、人员
            List<Accessory> accessoryList = form.getAccessoryList();
            if (accessoryList != null && accessoryList.size() > 0) {
                for (Accessory accessory : accessoryList) {
                    accessory.setCompany_code(code);
                    accessory.setAccessory_type(accessory.getName());
                    accessory.setAccessory_url(accessory.getUrl());
                    accessory.setAccessory_code(CodeUtil.getSystemCode("accessory"));
                    accessoryDao.save(accessory);
                }
            }
            List<CompanyEmploy> list = form.getCompanyEmployList();
            if (list != null && list.size() > 0) {
                for (CompanyEmploy companyEmploy : list) {
                	companyEmploy.setCompany_code(code);
                    companyEmploy.setEmploy_code(CodeUtil.getSystemCode("companyEmploy"));
                    if(!StringUtils.isEmpty(companyEmploy.getUser_health_datedue())) {
                    	companyEmploy.setEmploy_ishealth(1);
                    }
                    companyEmployDao.save(companyEmploy);
                }
            }
    	}
    	LogService.saveObject("提交", "待审核", form.getUnpass_cause(), suser.getUser_code(), suser.getUser_name(), form.getCompany_code());
    	
    	return result;
    }
    
    
    public List<Company> findByList(Company company){
    	return companyDao.findByList(company);
    }
    
    /**
     * 根据企业地址逆解析坐标
     * @Title: geocoder
     * @Description: 
     * @Author sven
     * @DateTime 2020年3月24日 上午11:31:16
     * @return
     */
    public boolean geocoder() {
    	Company c = new Company();
    	c.setQuery_param("lngLat is null");
    	List<Company> companyList = companyDao.findByAll(c);
    	String address = "";
    	String[] arr = new String[2];
    	for (Company company : companyList) {
    		address = company.getProvince()+company.getCity()+company.getArea()+company.getTown()+company.getVill()+company.getAddress();
    		arr = BaiDuMapUtil.getLngAndLatByLoc(address);
    		company.setLongitude(arr[0]);
    		company.setLatitude(arr[1]);
		}
    	int result = companyDao.updateBatch(companyList);
    	if(result == 0) {
    		return false;
    	}else {
    		return true;
    	}
    }
    
    /**
     * 
     * @Title: findCandCCLJList
     * @Description: 根据用户权限查询企业列表并查询对应企业餐厨垃圾的数据
     * @Author tangsh
     * @DateTime 2020年5月14日 下午12:00:20
     * @param form
     * @param user
     * @return
     */
    public List<CompanyReturn> findCandCCLJList(Company form,User user){
    	List<CompanyReturn> crlist=findByUserCompanyList(form, user);
    	if(crlist!=null&&crlist.size()>0) {
    		String cclj_time=form.getCclj_time_last();
    		String end_time=form.getCclj_time_last_end();
    		crlist.forEach(cr ->{
    			CompanyCCLJ cc=new CompanyCCLJ();
    			cc.setCompany_code(cr.getCompany_code());
    			cc.setCclj_time(cclj_time);
    			cc.setEnd_time(end_time);
    			cr.setCclj_count(companyCCLJDao.findByCount(cc));
    		});
    	}
    	return crlist;
    }
}
