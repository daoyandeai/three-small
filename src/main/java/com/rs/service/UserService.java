package com.rs.service;

import com.rs.dao.IConfigDao;
import com.rs.dao.IDepartmentUserDao;
import com.rs.dao.IDictionaryDao;
import com.rs.dao.IInfoRegionDao;
import com.rs.dao.IPatrolDao;
import com.rs.dao.IRoleDao;
import com.rs.dao.IUserDao;
import com.rs.dao.IUserRoleDao;
import com.rs.po.Config;
import com.rs.po.Department;
import com.rs.po.Dictionary;
import com.rs.po.Menu;
import com.rs.po.Patrol;
import com.rs.po.User;
import com.rs.po.UserRole;
import com.rs.po.returnPo.UserReturn;
import com.rs.util.CalcUtil;
import com.rs.util.CharacterUtil;
import com.rs.util.CodeUtil;
import com.rs.util.RegularUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class UserService extends BaseService<User>{
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IDepartmentUserDao departmentUserDao;
	@Autowired
	private IInfoRegionDao infoRegionDao;
	@Autowired
	private IUserRoleDao userRoleDao;
	@Autowired
	private IRoleDao roleDao;
	@Autowired
    private IDictionaryDao dictionaryDao;
	@Autowired
	private IPatrolDao patrolDao;
	@Autowired
	private IConfigDao configDao;
	@Autowired
	private InfoRegionService infoRegionService;

	/**
	 * 
	 * @Title: findByLoginNameAndPass
	 * @Description: 通过登陆账户密码查询用户信息
	 * @Author tangsh
	 * @DateTime 2020年3月30日 下午2:51:47
	 * @param form
	 * @return
	 */
	public User findByLoginNameAndPass(User form) {
		return userDao.findByLoginNameAndPass(form);
	}
	/**
	 * 
	 * @Title: login
	 * @Description: 登陆
	 * @Author tangsh
	 * @DateTime 2020年3月30日 下午2:51:39
	 * @param form
	 * @return
	 */
    public User login(User form) {
    	return userDao.login(form);
    }
    
    /**
     * 
     * @Title: findLoginMenu
     * @Description: 查询用户登陆角色菜单
     * @Author tangsh
     * @DateTime 2020年3月30日 下午2:51:20
     * @param form
     * @return
     */
    public List<Menu> findLoginMenu(User form){
    	form.setParam_type(1);
    	List<Menu> mlist=userDao.findLoginMenu(form);
    	if(mlist!=null&&mlist.size()>0) {
    		User _u=null;
    		User _ulast=null;
    		List<Menu> mlastlist=null;
    		for(Menu m:mlist) {
    			_u=new User();
    			_u.setUser_code(form.getUser_code());
    			_u.setMenu_parentcode(m.getMenu_code());
    			mlastlist=userDao.findLoginMenu(_u);
    			if(mlastlist!=null&&mlastlist.size()>0) {
    				for(Menu ml:mlastlist) {
    					_ulast=new User();
    					_ulast.setUser_code(form.getUser_code());
    					_ulast.setMenu_parentcode(ml.getMenu_code());
    					ml.setMenu_btns(userDao.findLoginMenuButton(_ulast));
        			}
    			}
    			m.setMenu_list(mlastlist);
    		}
    	}
    	return mlist;
    }
    
    /**
     * 
     * @Title: findLoginMenuButton
     * @Description: 查询登陆用户按钮权限
     * @Author tangsh
     * @DateTime 2020年3月30日 下午2:51:00
     * @param form
     * @return
     */
    public String findLoginMenuButton(User form){
    	return userDao.findLoginMenuButton(form);
    }

    /**
     * 
     * @Title: findBySearch
     * @Description: 查询用户列表
     * @Author tangsh
     * @DateTime 2020年3月30日 下午2:50:43
     * @param form
     * @return
     */
    public List<User> findBySearch(User form) {
    	//查询所
    	List<User> urList=userDao.findBySearch(form);
    	if(urList!=null&&urList.size()>0) {
    		for(User ur:urList) {
    			//判断用户类型
    			if(ur.getUser_type().equals("平台管理员")) {
    				ur.setDepartment_names(departmentUserDao.findByUserCode(ur.getUser_code()));
    			}else {
    				ur.setDepartment_names(infoRegionDao.findByUserCode(ur.getUser_code()));
    			}
    			form.setBusiness_forms_list(RegularUtil.getStringToList(form.getBusiness_forms(),","));
    			form.setCompanytype_codes_list(RegularUtil.getStringToList(form.getCompanytype_codes(),","));
    			form.setCompanytag_codes_list(RegularUtil.getStringToList(form.getCompanytag_codes(),","));
    			form.setDictionary_codes_list(RegularUtil.getStringToList(form.getDictionary_codes(),","));
    		}
    	}
    	return urList;
    }

    /**
     * 
     * @Title: findBySearchCount
     * @Description: 查询用户总数
     * @Author tangsh
     * @DateTime 2020年3月30日 下午2:52:13
     * @param form
     * @return
     */
	public Integer findBySearchCount(User form) {
		return userDao.findBySearchCount(form);
	}
	
	/**
	 * 
	 * @Title: findByQyList
	 * @Description: 群宴人员管理（举办者、乡厨、平台管理员、协管员、农家乐、乡村酒店、配送企业）查询列表
	 * @Author tangsh
	 * @DateTime 2020年4月7日 下午4:41:10
	 * @param form
	 * @param loginUser
	 * @return
	 */
	public List<User> findByQyList(User form,User loginUser){
		if(StringUtils.isEmpty(form.getUser_province())) {
			form.setUser_province(loginUser.getUser_province());
		}
		if(StringUtils.isEmpty(form.getUser_city())) {
			form.setUser_city(loginUser.getUser_city());
		}
		if(StringUtils.isEmpty(form.getUser_area())) {
			form.setUser_area(loginUser.getUser_area());
		}
		if(StringUtils.isEmpty(form.getUser_town())) {
			form.setUser_town(loginUser.getUser_town());
		}
		if(StringUtils.isEmpty(form.getUser_vill())) {
			form.setUser_vill(loginUser.getUser_vill());
		}
		
		if(!StringUtils.isEmpty(form.getUser_type())) {
			if(form.getUser_type().equals("乡厨")) {
				if(StringUtils.isEmpty(form.getSort_type())) {
					form.setSort_type("desc");
				}
				if(StringUtils.isEmpty(form.getSort_column())) {//banquet_count 办宴次数
					form.setSort_column("report_count");   
				}
				return userDao.findBySortList(form);
			}
			if(form.getUser_type().equals("平台管理员")) {
				form.setOperate_type(Integer.valueOf((CalcUtil.addtr("1", loginUser.getUser_level()))));
			}
		}
		return userDao.findByList(form);
	}
	
	/**
	 * 
	 * @Title: findByQyCount
	 * @Description: 群宴人员管理（举办者、乡厨、平台管理员、协管员、农家乐、乡村酒店、配送企业）查询总数
	 * @Author tangsh
	 * @DateTime 2020年4月7日 下午5:07:32
	 * @param form
	 * @param loginUser
	 * @return
	 */
	public Integer findByQyCount(User form,User loginUser){
		if(StringUtils.isEmpty(form.getUser_province())) {
			form.setUser_province(loginUser.getUser_province());
		}
		if(StringUtils.isEmpty(form.getUser_city())) {
			form.setUser_city(loginUser.getUser_city());
		}
		if(StringUtils.isEmpty(form.getUser_area())) {
			form.setUser_area(loginUser.getUser_area());
		}
		if(StringUtils.isEmpty(form.getUser_town())) {
			form.setUser_town(loginUser.getUser_town());
		}
		if(StringUtils.isEmpty(form.getUser_vill())) {
			form.setUser_vill(loginUser.getUser_vill());
		}
		if(!StringUtils.isEmpty(form.getUser_type())) {
			if(form.getUser_type().equals("平台管理员")) {
				form.setOperate_type(Integer.valueOf((CalcUtil.addtr("1", loginUser.getUser_level()))));
			}
		}
		return userDao.findByCount(form);
	}
	
	/**
	 * 新增用户
	 */
	@Override
	public int save(User form){
		form.setUser_code(CodeUtil.getSystemCode("user"));
		form.setUser_audit_state(2);
		form.setUser_enname_short(CharacterUtil.getAllSpell(form.getUser_name()));
		
		form.setBusiness_forms(RegularUtil.getListToString(form.getBusiness_forms_list(),","));
		form.setCompanytype_codes(RegularUtil.getListToString(form.getCompanytype_codes_list(),","));
		form.setCompanytag_codes(RegularUtil.getListToString(form.getCompanytag_codes_list(),","));
		form.setDictionary_codes(RegularUtil.getListToString(form.getDictionary_codes_list(),","));
		
		if(StringUtils.isEmpty(form.getUser_logo_url())) {
			if(form.getUser_type().equals("协管员")||form.getUser_type().equals("平台管理员")) {
				form.setUser_logo_url("images/manager_logo.png");
			}else {
				form.setUser_logo_url("images/user_logo.png");
			}
		}
		if(StringUtils.isEmpty(form.getUser_idcard_logo_front())) {
			form.setUser_idcard_logo_front("images/user_idcard_logo_front.png");
		}
		if(StringUtils.isEmpty(form.getUser_idcard_logo_back())) {
			form.setUser_idcard_logo_back("images/user_idcard_logo_back.png");
		}
		if(StringUtils.isEmpty(form.getUser_health_logo())) {
			form.setUser_health_logo("images/user_health_logo.png");
		}
		if(StringUtils.isEmpty(form.getUser_registcard_logo())) {
			form.setUser_registcard_logo("images/user_registcard_logo.png");
		}
		if(StringUtils.isEmpty(form.getUser_business_logo_url())) {
			form.setUser_business_logo_url("images/user_business_logo_url.png");
		}
		if(StringUtils.isEmpty(form.getUser_food_logo_url())) {
			form.setUser_food_logo_url("images/user_food_logo_url.png");
		}
		
		
		Integer result=userDao.save(form);
		if(result>0){
			if(form.getManage_type().equals("3")) {
				Map<String,String> map = CodeUtil.setUserRole(form);
				roleDao.saveRoleUser(map);
			}
			UserRole ur=new UserRole();
			ur.setUser_code(form.getUser_code());
			ur.setRole_code(CodeUtil.setTsUserRole(form));
			userRoleDao.save(ur);
			
			if(form.getUser_type().equals("协管员")) {
				infoRegionService.saveByUser(form.getUser_code(), form.getUser_region_code());
			}
		}
		return result;
	}
	
	/**
	 * 保存企业用户,与管理员逻辑分开
	 * @Title: saveCompanyUser
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年1月19日 上午10:52:22
	 * @param form
	 * @return
	 */
	public int saveCompanyUser(User form) {
		return userDao.save(form);
	}
	
	/**
	 * 更新用户信息
	 */
	@Override
	public int update(User t) {
		User _u=userDao.findByCode(t);		
		if(_u==null) {
			return 0;
		}
		String manager_type=_u.getManage_type();
		t.setBusiness_forms(RegularUtil.getListToString(t.getBusiness_forms_list(),","));
		t.setCompanytype_codes(RegularUtil.getListToString(t.getCompanytype_codes_list(),","));
		t.setCompanytag_codes(RegularUtil.getListToString(t.getCompanytag_codes_list(),","));
		t.setDictionary_codes(RegularUtil.getListToString(t.getDictionary_codes_list(),","));
		Integer result=userDao.update(t);
		if(result != 0){
			if(!StringUtils.isEmpty(t.getManage_type())) {
				if(manager_type.equals("3")) {
					roleDao.deleteByUser(t);
				}
				if(t.getManage_type().equals("3")) {
					Map<String,String> map = CodeUtil.setUserRole(t);
					roleDao.saveRoleUser(map);
				}
				
				UserRole ur=new UserRole();
				ur.setUser_code(t.getUser_code());
				userRoleDao.delete(ur);
				ur.setRole_code(CodeUtil.setTsUserRole(t));
				userRoleDao.save(ur);
			}
			
			if(t.getUser_type().equals("协管员")) {
				infoRegionService.saveByUser(t.getUser_code(), t.getUser_region_code());
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: personUpdate
	 * @Description: 个人资料维护
	 * @Author tangsh
	 * @DateTime 2020年3月30日 下午2:49:55
	 * @param t
	 * @return
	 */
	public int personUpdate(User t) {
		return userDao.update(t);
	}
	
	public User single(User form) {
		form = userDao.findByCode(form);
		//封装数据
		form.setBusiness_forms_list(RegularUtil.getStringToList(form.getBusiness_forms(),","));
		form.setCompanytype_codes_list(RegularUtil.getStringToList(form.getCompanytype_codes(),","));
		form.setCompanytag_codes_list(RegularUtil.getStringToList(form.getCompanytag_codes(),","));
		form.setDictionary_codes_list(RegularUtil.getStringToList(form.getDictionary_codes(),","));
		if(form.getDictionary_codes_list()!=null&&form.getDictionary_codes_list().size()>0) {
			Dictionary _d=new Dictionary();
			_d.setDictionary_codes(RegularUtil.getListToSqlString(form.getDictionary_codes_list()));
			form.setDictionaryList(dictionaryDao.findBySearch(_d));
		}
		return form;
	}
	
	
	public List<UserReturn> findByDept(String department_code) {
		return userDao.findByDept(department_code);
	}
	
	public Integer updateByLoginName(User form) {
		return userDao.updateByLoginName(form);
	}
	
	public List<UserReturn> findByGisUserList(User form) {
		form.setUser_state(1);
		form.setReturncode("2");
		List<UserReturn> urlist=userDao.findByGisUserList(form);
		String user_screens=form.getUser_screens();
		if(urlist!=null&&urlist.size()>0) {
			Department dept=null;
			for(UserReturn ur:urlist) {
				if(StringUtils.isEmpty(user_screens)) {//查询全部
					Integer resnum=patrolDao.findTodayUserPatrolSum(ur.getUser_code());
					if(resnum==null) {
						ur.setUser_patrol_state(1);
					}else if(resnum>0) {
						ur.setUser_patrol_state(2);
						//查询一个经纬度给用户
						Patrol patrol=new Patrol();
						patrol.setPatrol_user_code(ur.getUser_code());
						patrol.setTimesearch("today");
						patrol.setPatrol_state(1);
						List<Patrol> plist=patrolDao.findLeftJoinCompanyAll(patrol);
						if(plist!=null&&plist.size()>0) {
							patrol=plist.get(0);
							ur.setPatrol_code(patrol.getPatrol_code());
							ur.setP_longitude(patrol.getLongitude());
							ur.setP_latitude(patrol.getLatitude());
						}
					}else {
						ur.setUser_patrol_state(3);
					}
				}
				//查询部门和部门的地址
				dept=userDao.findDepartmentByUserCode(ur.getUser_code());
				if(dept!=null) {
					ur.setDepartment_names(dept.getDepartment_name());
					ur.setLongitude(dept.getLongitude());
					ur.setLatitude(dept.getLatitude());
					ur.setUser_province(dept.getProvince());
					ur.setUser_city(dept.getCity());
					ur.setUser_area(dept.getArea());
					ur.setUser_town(dept.getTown());
					ur.setUser_vill(dept.getVill());
				}
			}
		}
		return urlist;
	}
	
	/**
	 * 
	 * @Title: findByGisUserCount
	 * @Description: GIS查询用户数量
	 * @Author tangsh
	 * @DateTime 2020年4月7日 下午5:14:55
	 * @param form
	 * @return
	 */
	public Integer findByGisUserCount(User form) {
		form.setUser_state(1);
		form.setReturncode("2");
		return userDao.findByGisUserCount(form);
	}
	
	/**
	 * 
	 * @Title: findByHealthCount
	 * @Description: 查询用户健康证数
	 * @Author tangsh
	 * @DateTime 2020年4月7日 下午5:15:36
	 * @param form
	 * @return
	 */
	public Integer findByHealthCount(User form) {
		return userDao.findByHealthCount(form);
	}
	
	/**
	 * 
	 * @Title: qy_save
	 * @Description: 群宴人员新增
	 * @Author tangsh
	 * @DateTime 2020年4月8日 下午2:47:42
	 * @param form
	 * @return
	 */
	public int qy_save(User form,User user){
		form.setUser_code(CodeUtil.getSystemCode("user"));
		form.setUser_audit_state(2);
		form.setUser_registersource(2);
		form.setUser_enname_short(CharacterUtil.getAllSpell(form.getUser_name()));
		
		if(form.getUser_type().equals("乡厨")) {
			Config config=new Config();
			config.setAll_address(form.getUser_province()+form.getUser_city()+form.getUser_area()+form.getUser_town()+form.getUser_vill());
			Integer result=configDao.findByInclude(form.getUser_province()+form.getUser_city()+form.getUser_area()+form.getUser_town()+form.getUser_vill());
			if (result>0) {
				form.setUser_audit_state(1);
			}else {
				form.setUser_audit_state(2);
			}
		}
		form.setBusiness_forms(RegularUtil.getListToString(form.getBusiness_forms_list(),","));
		form.setCompanytype_codes(RegularUtil.getListToString(form.getCompanytype_codes_list(),","));
		form.setCompanytag_codes(RegularUtil.getListToString(form.getCompanytag_codes_list(),","));
		form.setDictionary_codes(RegularUtil.getListToString(form.getDictionary_codes_list(),","));
		
		if(StringUtils.isEmpty(form.getUser_logo_url())) {
			if(form.getUser_type().equals("协管员")||form.getUser_type().equals("平台管理员")) {
				form.setUser_logo_url("images/manager_logo.png");
			}else {
				form.setUser_logo_url("images/user_logo.png");
			}
		}
		if(StringUtils.isEmpty(form.getUser_idcard_logo_front())) {
			form.setUser_idcard_logo_front("images/user_idcard_logo_front.png");
		}
		if(StringUtils.isEmpty(form.getUser_idcard_logo_back())) {
			form.setUser_idcard_logo_back("images/user_idcard_logo_back.png");
		}
		if(StringUtils.isEmpty(form.getUser_health_logo())) {
			form.setUser_health_logo("images/user_health_logo.png");
		}
		if(StringUtils.isEmpty(form.getUser_registcard_logo())) {
			form.setUser_registcard_logo("images/user_registcard_logo.png");
		}
		if(StringUtils.isEmpty(form.getUser_business_logo_url())) {
			form.setUser_business_logo_url("images/user_business_logo_url.png");
		}
		if(StringUtils.isEmpty(form.getUser_food_logo_url())) {
			form.setUser_food_logo_url("images/user_food_logo_url.png");
		}
		
		Integer result=userDao.save(form);
		if(result>0){
			Map<String,String> map = CodeUtil.setUserRole(form);
			roleDao.saveRoleUser(map);
			if(form.getManage_type()!=null&&form.getManage_type().equals("3")) {
				UserRole ur=new UserRole();
				ur.setUser_code(form.getUser_code());
				ur.setRole_code(CodeUtil.setTsUserRole(form));
				userRoleDao.save(ur);
				
				if(form.getUser_type().equals("协管员")) {
					infoRegionService.saveByUser(form.getUser_code(), form.getUser_region_code());
				}
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: qy_update
	 * @Description: 群宴管理更新
	 * @Author tangsh
	 * @DateTime 2020年4月8日 下午3:49:05
	 * @param t
	 * @return
	 */
	public int qy_update(User t) {
		return userDao.update(t);
	}
	
	public List<User> findListInnerReport(User form){
		return userDao.findListInnerReport(form);
	}
	
	public Integer findCountInnerReport(User form){
		return userDao.findCountInnerReport(form);
	}
	
	public User findByColumn(User form){
		return userDao.findByColumn(form);
	}
	
	/**
	 * 
	 * @Title: superManageUpdate
	 * @Description: 超级管理人员更新用户的类型信息
	 * @Author tangsh
	 * @DateTime 2020年5月21日 上午10:04:18
	 * @param t
	 * @return
	 */
	public Integer superManageUpdate(User t) {
		User _u=userDao.findByCode(t);		
		if(_u==null) {
			return 0;
		}
		String manager_type=_u.getManage_type();
		if(!StringUtils.isEmpty(t.getManage_type())) {
			if(manager_type.equals("3")) {
				roleDao.deleteByUser(t);
			}
			if(t.getManage_type().equals("3")) {
				Map<String,String> map = CodeUtil.setUserRole(t);
				roleDao.saveRoleUser(map);
			}
			
			UserRole ur=new UserRole();
			ur.setUser_code(t.getUser_code());
			userRoleDao.delete(ur);
			ur.setRole_code(CodeUtil.setTsUserRole(t));
			userRoleDao.save(ur);
		}else {
			roleDao.deleteByUser(t);
			Map<String,String> map = CodeUtil.setUserRole(t);
			roleDao.saveRoleUser(map);
		}
		return userDao.update(t);
	}
}
