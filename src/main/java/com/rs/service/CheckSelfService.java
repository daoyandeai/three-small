package com.rs.service;
import com.rs.dao.ICheckSelfDao;
import com.rs.dao.ICompanyDao;
import com.rs.dao.IPageConfigRegionDao;
import com.rs.dao.IUserDao;
import com.rs.po.CheckSelf;
import com.rs.po.Company;
import com.rs.po.PageConfigRegion;
import com.rs.po.User;
import com.rs.po.returnPo.PageConfigRegionReturn;
import com.rs.util.RegularUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 
 * @ClassName: CheckSelfService
 * @Description: 自查自纠服务层接口
 * @Author tangsh
 * @DateTime 2020年3月2日 上午11:48:06
 */
@Service
public class CheckSelfService extends BaseService<CheckSelf>{
	@Autowired
	private ICheckSelfDao checkSelfDao;
	@Autowired
	private IPageConfigRegionDao pageConfigRegionDao;
	@Autowired
	private IUserDao userDao;
	@Autowired
	private ICompanyDao companyDao;
	
	
	@Autowired
	private MessLogService messLogService;
	
	public Map<Object, Object> findList(CheckSelf form,User user){
		Map<Object, Object> map = new HashMap<Object, Object>();
		//管理员权限
		form.setBusiness_forms(RegularUtil.getStringToSqlString(user.getBusiness_forms(),","));
    	form.setCompanytag_codes(RegularUtil.getStringToSqlString(user.getCompanytag_codes(),","));
    	form.setCompanytype_codes(RegularUtil.getStringToSqlString(user.getCompanytype_codes(),","));
		//是否已查
    	PageConfigRegion pcf=new PageConfigRegion();
    	pcf.setState(1);
    	pcf.setProvince(user.getUser_province());
    	pcf.setPage_config_name("食品生产");
    	List<PageConfigRegionReturn> pcflist = pageConfigRegionDao.findByAll_app(pcf);
    	if(pcflist==null||pcflist.size()<0) {
    		map.put("pager_count", 0);
    		map.put("check_list",null);
    		return map;
    	}
    	Integer spsc_days=pcflist.get(0).getCalc_period();
    	form.setSpsc_days(spsc_days==null?0:spsc_days);
    	
    	pcf.setPage_config_name("食品流通");
    	pcflist = pageConfigRegionDao.findByAll_app(pcf);
    	if(pcflist==null||pcflist.size()<0) {
    		map.put("pager_count", 0);
    		map.put("check_list",null);
    		return map;
    	}
    	Integer splt_days=pcflist.get(0).getCalc_period();
    	form.setSplt_days(splt_days==null?0:splt_days);
    	
    	pcf.setPage_config_name("食品餐饮");
    	pcflist = pageConfigRegionDao.findByAll_app(pcf);
    	if(pcflist==null||pcflist.size()<0) {
    		map.put("pager_count", 0);
    		map.put("check_list",null);
    		return map;
    	}
    	Integer spcy_days=pcflist.get(0).getCalc_period();
    	form.setSpcy_days(spcy_days==null?0:spcy_days);
    	
    	//判断流程
    	if(form.getIscheckself()==1) {
    		map.put("pager_count", checkSelfDao.findCountByCompany(form));
    		map.put("check_list",checkSelfDao.findListByCompany(form));
    	}else {
    		map.put("pager_count", checkSelfDao.findCountByNotCSCompany(form));
    		List<CheckSelf> checkSelflist=checkSelfDao.findListByNotCSCompany(form);
    		if(checkSelflist!=null&&checkSelflist.size()>0) {
    			CheckSelf _cs=null;
    			for(CheckSelf cs:checkSelflist) {
    				_cs=checkSelfDao.findByCompanyLast(cs.getCompany_code());
    				if(_cs!=null) {
    					cs.setCheckself_content(_cs.getCheckself_content());
    					cs.setUser_name_add(_cs.getUser_name_add());
    					cs.setAdd_time(_cs.getAdd_time());
    				}
    			}
    		}
    		map.put("check_list",checkSelflist);
    	}
		
		return map;
	}
	
	public Integer findListCount(CheckSelf form,User user){
		//管理员权限
		form.setBusiness_forms(RegularUtil.getStringToSqlString(user.getBusiness_forms(),","));
    	form.setCompanytag_codes(RegularUtil.getStringToSqlString(user.getCompanytag_codes(),","));
    	form.setCompanytype_codes(RegularUtil.getStringToSqlString(user.getCompanytype_codes(),","));
		//是否已查
    	PageConfigRegion pcf=new PageConfigRegion();
    	pcf.setState(1);
    	pcf.setProvince(user.getUser_province());
    	pcf.setPage_config_name("食品生产");
    	List<PageConfigRegionReturn> pcflist = pageConfigRegionDao.findByAll_app(pcf);
    	if(pcflist==null||pcflist.size()<0) {
    		return 0;
    	}
    	Integer spsc_days=pcflist.get(0).getCalc_period();
    	form.setSpsc_days(spsc_days==null?0:spsc_days);
    	
    	pcf.setPage_config_name("食品流通");
    	pcflist = pageConfigRegionDao.findByAll_app(pcf);
    	if(pcflist==null||pcflist.size()<0) {
    		return 0;
    	}
    	Integer splt_days=pcflist.get(0).getCalc_period();
    	form.setSplt_days(splt_days==null?0:splt_days);
    	
    	pcf.setPage_config_name("食品餐饮");
    	pcflist = pageConfigRegionDao.findByAll_app(pcf);
    	if(pcflist==null||pcflist.size()<0) {
    		return 0;
    	}
    	Integer spcy_days=pcflist.get(0).getCalc_period();
    	form.setSpcy_days(spcy_days==null?0:spcy_days);
    	
		return checkSelfDao.findCountByCompany(form);
	}
	
	public List<PageConfigRegion> findByPageConfig(User user){
		User newUser = userDao.findByCode(user);
		return checkSelfDao.findByPageConfig(newUser);
	}
	
	public int save(String checkself_content,String checkself_code,String user_code) {
		User user = new User();
		user.setUser_code(user_code);
		User newUser = userDao.findByCode(user);
		Company company = new Company();
		company.setCompany_code(newUser.getCompany_code());
		Company newCompany = companyDao.findByCode(company);
		
		CheckSelf checkSelf = new CheckSelf();
		checkSelf.setCheckself_code(checkself_code);
		checkSelf.setCheckself_content(checkself_content);
		checkSelf.setCompany_code(newCompany.getCompany_code());
		checkSelf.setCompany_name(newCompany.getCompany_name());
		checkSelf.setCompanytag_code(newCompany.getCompanytag_code());
		checkSelf.setCompanytype_code(newCompany.getCompanytype_code());
		checkSelf.setOperator(newCompany.getOperator());
		checkSelf.setUser_code_add(newUser.getUser_code());
		checkSelf.setUser_name_add(newUser.getUser_name());
		
		
		messLogService.saveMessLogByOtherCode(5, company.getCompany_code(), checkself_code, newUser);
		return checkSelfDao.save(checkSelf);
	}
	
	
	public List<CheckSelf> findByList(String user_code, Integer pager_openset, Integer pager_offset){
		CheckSelf checkSelf = new CheckSelf();
		checkSelf.setUser_code_add(user_code);
		checkSelf.setPager_offset(pager_offset);
		checkSelf.setPager_openset(pager_openset);
		return checkSelfDao.findByList(checkSelf);
	}
	
	public CheckSelf findByCode(CheckSelf checkself) {
		return checkSelfDao.findByCode(checkself);
	}
}
