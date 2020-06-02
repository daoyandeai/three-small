package com.rs.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rs.po.UserRole;
import com.rs.service.UserRoleService;
import com.rs.util.RegularUtil;

/**
 * 
 * @ClassName: UserRoleController
 * @Description: 用户角色控制层
 * @Author tangsh
 * @DateTime 2019年12月18日 下午4:12:03
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/userRole")
public class UserRoleController extends BaseController {
	
	@Autowired
	private UserRoleService userRoleService;
	
	private Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	
	/**
	 * 
	 * @Title: save
	 * @Description: 新增单个用户角色
	 * @Author tangsh
	 * @DateTime 2019年12月18日 上午11:43:17
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody UserRole form){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			if(RegularUtil.isSpecialChar(form.getUser_code()) || RegularUtil.matchLength(form.getUser_code(),25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(RegularUtil.isSpecialChar(form.getRole_code()) || RegularUtil.matchLength(form.getRole_code(),25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(userRoleService.findByCount(form)>0){
				CODEMSG = KEY_PARAM_EXIST;
				return finish(CODEMSG, null);
			}
			int result = userRoleService.save(form);
			if (result > 0) {
				CODEMSG = SUCCESS;
			} else {
				map = null;
				CODEMSG = ERROR;
			}
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, map);
	}
	
	/**
	 * 
	 * @Title: delete
	 * @Description: 删除单个用户角色
	 * @Author tangsh
	 * @DateTime 2019年12月18日 下午4:14:25
	 * @param form
	 * @param attr
	 * @return
	 */
	@DeleteMapping(value="/delete")
	public Object delete(UserRole form){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			if(RegularUtil.isSpecialChar(form.getUser_code()) || RegularUtil.matchLength(form.getUser_code(),25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(RegularUtil.isSpecialChar(form.getRole_code()) || RegularUtil.matchLength(form.getRole_code(),25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int result = userRoleService.delete(form);
			if (result > 0) {
				CODEMSG = SUCCESS;
			} else {
				map = null;
				CODEMSG = ERROR;
			}
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, map);
	}
}
