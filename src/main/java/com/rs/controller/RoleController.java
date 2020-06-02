package com.rs.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rs.po.Role;
import com.rs.service.RoleService;
import com.rs.util.RegularUtil;

/**
 * 
 * @ClassName: RoleController
 * @Description: 角色控制层
 * @Author tangsh
 * @DateTime 2019年12月18日 上午11:15:41
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/role")
public class RoleController extends BaseController {
	
	@Autowired
	private RoleService roleService;
	
	private Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	
	/**
	 * 
	 * @Title: list
	 * @Description: 分页查询角色列表
	 * @Author tangsh
	 * @DateTime 2019年12月18日 上午11:24:32
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(Role form){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			if(!StringUtils.isEmpty(form.getRole_name())){   
				if(!RegularUtil.isLength(form.getRole_name(), 1, 50)){
					form.setRole_name(null);
				}
			}
			map.put("role_list", roleService.findByList(form));
			map.put("pager_count", roleService.findByCount(form));
			CODEMSG = SUCCESS;
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
	 * @Title: single
	 * @Description: 查询单个角色对象
	 * @Author tangsh
	 * @DateTime 2019年12月18日 上午11:39:13
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/single")
	public Object single(Role form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			if(RegularUtil.isSpecialChar(form.getRole_code()) || RegularUtil.matchLength(form.getRole_code(),25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			map.put("role", roleService.single(form));
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
	 * @Title: save
	 * @Description: 新增单个角色
	 * @Author tangsh
	 * @DateTime 2019年12月18日 上午11:43:17
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody Role form){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			if(StringUtils.isEmpty(form.getRole_name())||!RegularUtil.isLength(form.getRole_name(), 1, 50)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(roleService.findByExist(form)>0){
				CODEMSG = KEY_PARAM_EXIST;
				return finish(CODEMSG, null);
			}
			int result = roleService.save(form);
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
	 * @Title: update
	 * @Description: 更新单个角色
	 * @Author tangsh
	 * @DateTime 2019年12月18日 上午11:43:48
	 * @param form
	 * @param request
	 * @return
	 */
	@PutMapping(value = "/update")
	public Object update(@RequestBody Role form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			
			if(RegularUtil.isSpecialChar(form.getRole_code()) || RegularUtil.matchLength(form.getRole_code(),25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(RegularUtil.isSpecialChar(form.getRole_name()) || !RegularUtil.isLength(form.getRole_name(), 1, 50)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(roleService.findByExist(form)>0){
				CODEMSG = KEY_PARAM_EXIST;
				return finish(CODEMSG, null);
			}
			int result = roleService.update(form);
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
	 * 删除当前权限，同时删除用户权限表，权限菜单表
	 */
	@DeleteMapping(value="/delete")
	public Object delete(Role form){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			map.put("role_code", form.getRole_code());
			if(RegularUtil.isSpecialChar(form.getRole_code()) || RegularUtil.matchLength(form.getRole_code(),25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int result = roleService.delete(form);
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
	 * @Title: exist
	 * @Description: 判断是否重复权限名称
	 * @Author tangsh
	 * @DateTime 2019年12月18日 上午11:56:51
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/exist")
	public Object exist(Role form) {
		try {
			if(RegularUtil.isSpecialChar(form.getRole_name()) || !RegularUtil.isLength(form.getRole_name(), 1, 50)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(!StringUtils.isEmpty(form.getRole_code())) {
				if(RegularUtil.isSpecialChar(form.getRole_code()) || RegularUtil.matchLength(form.getRole_code(),25)) {
					CODEMSG = PARAMETER_ERROR;
					return finish(CODEMSG, null);
				}
			}
			if(roleService.findByExist(form)>0){
				CODEMSG = KEY_PARAM_EXIST;
				return finish(CODEMSG, null);
			}
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, null);
	}
	
	
	/**
	 * 
	 * @Title: roleUser
	 * @Description: 角色用户
	 * @Author tangsh
	 * @DateTime 2019年12月18日 下午1:09:41
	 * @param form
	 * @param model
	 * @return
	 */
	@GetMapping(value="/roleUser")
	public Object roleUser(Role form,Model model){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			if(RegularUtil.isSpecialChar(form.getRole_code()) || RegularUtil.matchLength(form.getRole_code(),25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			
			map.put("role", roleService.findByCode(form));
			map.put("user_list", roleService.findByRoleUserList(form));
			map.put("pager_count", roleService.findByRoleUserCount(form));
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			map=null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, map);
	}
	
	/**
	 * 
	 * @Title: roleMenu
	 * @Description: 角色菜单
	 * @Author tangsh
	 * @DateTime 2019年12月18日 下午4:17:26
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/roleMenu")
	public Object roleMenu(Role form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			if(RegularUtil.isSpecialChar(form.getRole_code()) || RegularUtil.matchLength(form.getRole_code(),25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			map.put("role", roleService.findByCode(form));
			map.put("menu_list", roleService.findByMenu());
			map.put("rolemenu_list", roleService.findByRoleMenu(form.getRole_code()));
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, map);
	}
}
