package com.rs.controller;

import com.rs.po.Company;
import com.rs.po.User;
import com.rs.service.CompanyService;
import com.rs.service.UserService;
import com.rs.util.RegularUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @ClassName: CompanyController
 * @Description: 三小档案控制层
 * @Author tangsh
 * @DateTime 2019年12月31日 下午3:38:07
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/app/company")
public class AppCompanyController extends BaseController {

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private UserService userService;

	private Logger logger = LoggerFactory.getLogger(AppCompanyController.class);

	/**
	 * 
	 * @Title: search
	 * @Description: 条件查询三小档案（列表）
	 * @Author tangsh
	 * @DateTime 2019年12月27日 下午3:07:23
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/search")
	public Object search(Company form,HttpServletRequest request) {
		if(StringUtils.isEmpty(form.getUser_code())){
			return finish(PARAMETER_ERROR, null);
		}
		User user=new User();
		user.setUser_code(form.getUser_code());
		user=userService.single(user);
		if(user==null) {
			return finish(USER_UNDEFINED, null);
		}
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("pager_count",companyService.findByUserCompanyCount(form,user));
			map.put("companyList",companyService.findByUserCompanyList(form,user));
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, map);
	}
	
	/**
	 * 
	 * @Title: searchCount
	 * @Description: 三小备案统计数据
	 * @Author tangsh
	 * @DateTime 2020年5月22日 上午10:55:25
	 * @param form
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/search/count")
	public Object searchCount(Company form,HttpServletRequest request) {
		if(StringUtils.isEmpty(form.getUser_code())){
			return finish(PARAMETER_ERROR, null);
		}
		User user=new User();
		user.setUser_code(form.getUser_code());
		user=userService.single(user);
		if(user==null) {
			return finish(USER_UNDEFINED, null);
		}
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("pager_count",companyService.findByUserCompanyCount(form,user));
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, map);
	}
	
	/**
	 * 
	 * @Title: findByList
	 * @Description: 查询列表
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:24:39
	 * @param company
	 * @return
	 */
	@GetMapping(value = "/findByList")
    public Object findByList(Company company){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("pager_list",companyService.findByList(company));
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, map);
    }
	
	/**
	 * 
	 * @Title: save
	 * @Description: 微信端用户新增备案接口
	 * @Author tangsh
	 * @DateTime 2020年5月21日 下午3:34:33
	 * @param form
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody Company form) {
		CODEMSG = ERROR;
		try {
			if(StringUtils.isEmpty(form.getUser_code())){
				return finish(PARAMETER_ERROR, null);
			}
			if(RegularUtil.isSpecialChar(form.getCompany_name())){
				return finish(PARAMETER_ERROR, null);
			}
			if(!RegularUtil.isMobilePhone(form.getMobilephone())){
				return finish(PARAMETER_ERROR, null);
			}
			
			User user=new User();
			user.setUser_code(form.getUser_code());
			user=userService.findByCode(user);
			if(user==null) {
				return finish(USER_UNDEFINED, null);
			}
			if(companyService.saveUserCompany(form,user)>0){
				CODEMSG = SUCCESS;
			}
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, null);
	}
	
	/**
	 * 
	 * @Title: update
	 * @Description: 微信端用户修改备案接口
	 * @Author tangsh
	 * @DateTime 2020年5月21日 下午3:40:35
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/update")
	public Object update(@RequestBody Company form) {
		CODEMSG = ERROR;
		try {
			if(StringUtils.isEmpty(form.getUser_code())){
				return finish(PARAMETER_ERROR, null);
			}
			if(StringUtils.isEmpty(form.getCompany_code())){
				return finish(PARAMETER_ERROR,null);
			}
			/*Company _company=companyService.findByCode(form);
			if(_company==null||_company.getFiling_state()==3) {
				return finish(ERROR,null);
			}*/
			User user=new User();
			user.setUser_code(form.getUser_code());
			user=userService.findByCode(user);
			if(user==null) {
				return finish(USER_UNDEFINED, null);
			}
			if(companyService.updateUserCompany(form,user)>0){
				CODEMSG = SUCCESS;
			}
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, null);
	}
	
}
