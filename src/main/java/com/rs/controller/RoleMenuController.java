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

import com.rs.po.RoleMenu;
import com.rs.service.RoleMenuService;
import com.rs.util.RegularUtil;

/**
 * 
 * @ClassName: RoleMenuController
 * @Description: 角色菜单控制层 
 * @Author tangsh
 * @DateTime 2019年12月18日 下午4:26:25
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/roleMenu")
public class RoleMenuController extends BaseController {
	
	@Autowired
	private RoleMenuService roleMenuService;
	
	private Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	
	/**
	 * 
	 * @Title: save
	 * @Description: 新增单个角色菜单
	 * @Author tangsh
	 * @DateTime 2019年12月18日 下午4:26:18
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody RoleMenu form){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			if(RegularUtil.isSpecialChar(form.getMenu_code())) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(RegularUtil.isSpecialChar(form.getRole_code()) || RegularUtil.matchLength(form.getRole_code(),25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(roleMenuService.findByCount(form)>0){
				CODEMSG = KEY_PARAM_EXIST;
				return finish(CODEMSG, null);
			}
			int result = roleMenuService.save(form);
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
	 * @Description: 删除单个角色菜单
	 * @Author tangsh
	 * @DateTime 2019年12月18日 下午4:26:12
	 * @param form
	 * @return
	 */
	@DeleteMapping(value="/delete")
	public Object delete(RoleMenu form){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			if(RegularUtil.isSpecialChar(form.getMenu_code()) || RegularUtil.matchLength(form.getMenu_code(),25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(RegularUtil.isSpecialChar(form.getRole_code()) || RegularUtil.matchLength(form.getRole_code(),25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int result = roleMenuService.delete(form);
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
