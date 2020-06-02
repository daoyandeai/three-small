package com.rs.controller;
import com.rs.po.RedisEnum;
import com.rs.po.Region;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.service.RegionService;
import com.rs.service.UserService;

import com.rs.util.TokenUserUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @ClassName: UserController
 * @Description: 用户控制层
 * @Author tangsh
 * @DateTime 2019年12月18日 下午4:29:37
 */
@Transactional
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	@Autowired
	private RegionService regionService;
	@Autowired
	private TokenParam tokenParam;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 *
	 * @Title: list
	 * @Description: 分页查询用户列表
	 * @Author tangsh
	 * @DateTime 2019年12月18日 下午4:29:26
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/search")
	public Object search(User form){
		Map<Object, Object> map = new HashMap<>();
		try {
			map.put("user_list", userService.findBySearch(form));
			map.put("pager_count", userService.findBySearchCount(form));
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
	 * @Title: list
	 * @Description: 群宴人员管理（举办者、乡厨、平台管理员、协管员、农家乐、乡村酒店、配送企业）列表
	 * @Author tangsh
	 * @DateTime 2020年4月7日 下午4:37:34
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(User form,HttpServletRequest request){
		Map<Object, Object> map = new HashMap<>();
		try {
			User user = TokenUserUtil.generateUser(request, tokenParam);
			map.put("user_list", userService.findByQyList(form,user));
			map.put("pager_count", userService.findByQyCount(form,user));
			
			if(!StringUtils.isEmpty(form.getUser_type())&&form.getUser_type().equals("乡厨")) {
				form.setUser_health_state(2);
				map.put("User_health_over_count", userService.findByHealthCount(form));
				form.setUser_health_state(1);
				map.put("User_health_ok_count", userService.findByHealthCount(form));
			}
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
	 * @Title: qy_save
	 * @Description: 群宴人员新增
	 * @Author tangsh
	 * @DateTime 2020年4月8日 下午2:45:23
	 * @param form
	 * @return
	 */
	@PostMapping(value="/qy_save")
	public Object qy_save(@RequestBody User form,HttpServletRequest request) {
		CODEMSG= ERROR;
		try {
			if(StringUtils.isEmpty(form.getUser_name())) {
				CODEMSG= PARAMETER_ERROR;
				return finish(CODEMSG,null); 
			}
			if(StringUtils.isEmpty(form.getUser_type())) {
				CODEMSG= PARAMETER_ERROR;
				return finish(CODEMSG,null); 
			}
			if(userService.findByExist(form)>0) {
				CODEMSG= USER_EXIST;
				return finish(CODEMSG,null); 
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			Integer result = userService.qy_save(form,user);
			if(result>0){
				CODEMSG= SUCCESS;
			}
		}catch(Exception ex) {
			logger.info(ex.toString());
			CODEMSG = EXCEPTION;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return finish(CODEMSG,null);
		}
		return finish(CODEMSG,null);
	}

	/**
	 * 新增
	 * @Title: save
	 * @Description:
	 * @Author sven
	 * @param form
	 * @return
	 */
	@PostMapping(value="/save")
	public Object save(@RequestBody User form) {
		CODEMSG= ERROR;
		try {
			if(StringUtils.isEmpty(form.getUser_name())) {
				CODEMSG= PARAMETER_ERROR;
				return finish(CODEMSG,null); 
			}
			if(userService.findByExist(form)>0) {
				CODEMSG= USER_EXIST;
				return finish(CODEMSG,null); 
			}
			Integer result = userService.save(form);
			if(result>0){
				CODEMSG= SUCCESS;
			}
		}catch(Exception ex) {
			logger.info(ex.toString());
			CODEMSG = EXCEPTION;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return finish(CODEMSG,null);
		}
		return finish(CODEMSG,form);
	}

	/**
	 *
	 * @Title: single
	 * @Description: 查询单个对象
	 * @Author sven
	 * @param form
	 * @return
	 * @throws InterruptedException
	 */
	@GetMapping(value="/single")
	public Object single(User form){
		try {
			if(StringUtils.isEmpty(form.getUser_code())) {
				CODEMSG= PARAMETER_ERROR;
				return finish(CODEMSG,null); 
			}
			form = userService.single(form);
			CODEMSG = SUCCESS;
		}catch(Exception ex) {
			logger.info(ex.toString());
			CODEMSG = EXCEPTION;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return finish(CODEMSG,null);
		}
		return finish(CODEMSG,form);
	}

//    /**
//     * 删除
//     * @Title: user_id
//     * @Description:
//     * @Author sven
//     * @param user_id
//     * @return
//     */
//    @DeleteMapping(value="/delete")
//    public Object delete(Integer user_id) {
//		CODEMSGEnum CODEMSG = SUCCESS;
//		User user = new User();
//		user.setUser_id(user_id);
//    	try {
//    		Integer result = userService.delete(user_id);
//	    	if(result > 0){
//	    		redisTemplate.delete(KEY+ user_id);
//	    	}
//    	}catch(Exception ex) {
//    		logger.info(ex.toString());
//			CODEMSG = EXCEPTION;
//			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//		}
//    	return finish(CODEMSG,user);
//    }

	/**
	 * 更新
	 * @Title: update
	 * @Description:
	 * @Author sven
	 * @param form
	 * @return
	 */
	@PutMapping(value="/update")
	public Object update(@RequestBody User form) {
		User user = form;
		try {
			if(StringUtils.isEmpty(form.getUser_code())) {
				CODEMSG= PARAMETER_ERROR;
				return finish(CODEMSG,null); 
			}
			if(userService.findByExist(form)>0) {
				CODEMSG= USER_UNDEFINED;
				return finish(CODEMSG,null); 
			}
			Integer result = userService.update(form);
			if(result > 0){
				CODEMSG = SUCCESS;
			}
		}catch(Exception ex) {
			logger.info(ex.toString());
			CODEMSG = EXCEPTION;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return finish(CODEMSG,null);
		}
		return finish(CODEMSG,user);
	}
	
	/**
	 * 
	 * @Title: personUpdate
	 * @Description: 个人资料更新
	 * @Author tangsh
	 * @DateTime 2020年3月30日 下午2:54:14
	 * @param form
	 * @return
	 */
	@PutMapping(value="/personUpdate")
	public Object personUpdate(@RequestBody User form) {
		User user=form;
		try {
			if(userService.findByExist(form)<0) {
				CODEMSG= USER_UNDEFINED;
				return finish(CODEMSG,null); 
			}
			Integer result = userService.personUpdate(form);
			if(result > 0){
				user=userService.findByCode(form);
				CODEMSG = SUCCESS;
			}
		}catch(Exception ex) {
			logger.info(ex.toString());
			CODEMSG = EXCEPTION;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return finish(CODEMSG,null);
		}
		return finish(CODEMSG,user);
	}
	
	/**
	 * 
	 * @Title: qy_update
	 * @Description: 群宴更新
	 * @Author tangsh
	 * @DateTime 2020年4月8日 下午3:44:52
	 * @param form
	 * @return
	 */
	@PutMapping(value="/qy_update")
	public Object qy_update(@RequestBody User form) {
		CODEMSG = ERROR;
		try {
			if(StringUtils.isEmpty(form.getUser_code())) {
				CODEMSG= PARAMETER_ERROR;
				return finish(CODEMSG,null); 
			}
			if(userService.findByExist(form)<0) {
				CODEMSG= USER_UNDEFINED;
				return finish(CODEMSG,null); 
			}
			Integer result = userService.qy_update(form);
			if(result > 0){
				CODEMSG = SUCCESS;
			}
		}catch(Exception ex) {
			logger.info(ex.toString());
			CODEMSG = EXCEPTION;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return finish(CODEMSG,null);
		}
		return finish(CODEMSG,null);
	}
	
	/**
	 * 
	 * @Title: updateState
	 * @Description: 更新状态
	 * @Author tangsh
	 * @DateTime 2020年4月8日 下午4:49:41
	 * @param form
	 * @return
	 */
	@PutMapping(value="/updateState")
	public Object updateState(@RequestBody User form) {
		CODEMSG = ERROR;
		try {
			if(StringUtils.isEmpty(form.getUser_code())||StringUtils.isEmpty(form.getUser_state())) {
				CODEMSG= PARAMETER_ERROR;
				return finish(CODEMSG,null); 
			}
			Integer result = userService.qy_update(form);
			if(result > 0){
				CODEMSG = SUCCESS;
			}
		}catch(Exception ex) {
			logger.info(ex.toString());
			CODEMSG = EXCEPTION;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return finish(CODEMSG,null);
		}
		return finish(CODEMSG,null);
	}
	
	/**
	 * 分页查询监管人员列表
	 * @Title: manageList
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年1月2日 下午4:59:24
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/manage/list")
	public Object manageList(User form){
		Map<Object, Object> map = new HashMap<>();
		try {
			String user_province = form.getUser_province();
			if (StringUtils.isEmpty(user_province)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String user_city = form.getUser_city();
			if (StringUtils.isEmpty(user_city)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String user_area = form.getUser_area();
			if (StringUtils.isEmpty(user_area)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String user_towns = form.getUser_towns();
			if (StringUtils.isEmpty(user_towns)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String user_type = form.getUser_type();
			if (StringUtils.isEmpty(user_type)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			form.setUser_state(1);
			//排除已在食药所的用户
			form.setUser_screens("not in dept_user");
			List<User> uList = userService.findByList(form);
			
			List<Object> urList = new ArrayList<Object>();
			Map<String, Object> uMap = null;
			for (User u : uList) {
				uMap = new HashMap<String, Object>();
				uMap.put("user_code", u.getUser_code());
				uMap.put("user_name", u.getUser_name());
				uMap.put("user_mobilephone", u.getUser_mobilephone());
				uMap.put("user_town", u.getUser_town());
				uMap.put("state", u.getUser_state());
				urList.add(uMap);
			}
			map.put("user_list",urList);
			map.put("pager_count", userService.findByCount(form));
			
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
	 * 分页查询网格信息员列表
	 * @Title: infoList
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年1月2日 下午4:59:40
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/info/list")
	public Object infoList(User form,HttpServletRequest request){
		Map<Object, Object> map = new HashMap<>();
		try {
			String user_vills = form.getUser_vills();
			if (StringUtils.isEmpty(user_vills)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String user_type = form.getUser_type();
			if (StringUtils.isEmpty(user_type)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_province(user.getUser_province());
			form.setUser_city(user.getUser_city());
			form.setUser_area(user.getUser_area());
			form.setUser_town(user.getUser_town());
			form.setUser_state(1);
			//排除已在食药所的用户
			form.setUser_screens("not in info_user");
			List<User> uList = userService.findByList(form);
			
			List<Object> urList = new ArrayList<Object>();
			Map<String, Object> uMap = null;
			Region region = null;
			for (User u : uList) {
				uMap = new HashMap<String, Object>();
				uMap.put("user_code", u.getUser_code());
				uMap.put("user_name", u.getUser_name());
				uMap.put("user_mobilephone", u.getUser_mobilephone());
				uMap.put("user_vill", u.getUser_vill());
				//查询当前用户的区域编码
				region = new Region();
				region.setRegion_province(u.getUser_province());
				region.setRegion_city(u.getUser_city());
				region.setRegion_area(u.getUser_area());
				region.setRegion_town(u.getUser_town());
				region.setRegion_vill(u.getUser_vill());
				region = regionService.findOneByRegion(region);
				uMap.put("region_code", region.getRegion_code());
				uMap.put("state", u.getUser_state());
				urList.add(uMap);
			}
			map.put("user_list",urList);
			map.put("pager_count", userService.findByCount(form));
			
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
	 * @Title: findByExist
	 * @Description: 用户是否存在
	 * @Author tangsh
	 * @DateTime 2020年1月9日 下午4:40:05
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/exist")
	public Object findByExist(User form){
		try {
			if(userService.findByExist(form)>0) {
				CODEMSG= USER_EXIST;
				return finish(CODEMSG,null); 
			}
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, form);
	}
	
	
	/**
	 * 
	 * @Title: findLoginNameExist
	 * @Description: 判断账号是否存在
	 * @Author tangsh
	 * @DateTime 2020年2月10日 下午2:33:34
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/upname")
	public Object updateLoginName(@RequestBody User form) {
		try {
			if(StringUtils.isEmpty(form.getUser_loginname())||StringUtils.isEmpty(form.getUser_loginpass())||StringUtils.isEmpty(form.getMesscode())){
				CODEMSG = KEY_PARAM_EXIST;
				return finish(CODEMSG,null);
			}
			String messcode = (String)redisTemplate.opsForValue().get(RedisEnum.MESSCODE.getKey() + form.getUser_loginname());
			if(StringUtils.isEmpty(messcode)){
				CODEMSG = KEY_PARAM_EXIST;
				return finish(CODEMSG,null);
			}
			if(!form.getMesscode().toUpperCase().equals(messcode.toUpperCase())) {
				CODEMSG = KEY_PARAM_EXIST;
				return finish(CODEMSG,null);
			}
			redisTemplate.delete(RedisEnum.MESSCODE.getKey() + form.getUser_loginname());
			
			Integer result = userService.updateByLoginName(form);
			if(result > 0){
				CODEMSG = SUCCESS;
			}else{
				CODEMSG = ERROR;
			}
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			
		}
		return finish(CODEMSG, form);
	}
	
	/**
	 * 
	 * @Title: realInfoUserlist
	 * @Description: 查询真实网格员列表
	 * @Author tangsh
	 * @DateTime 2020年3月16日 下午6:14:12
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/realInfoUserlist")
	public Object realInfoUserlist(User form){
		Map<Object, Object> map = new HashMap<>();
		try {
			map.put("pager_count",userService.findByGisUserCount(form));
			map.put("user_list",userService.findByGisUserList(form));
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
	 * @Title: superManageUpdate
	 * @Description: 超级管理人员更新用户的类型信息
	 * @Author tangsh
	 * @DateTime 2020年5月21日 上午9:50:04
	 * @param form
	 * @return
	 */
	@PutMapping(value="/supermanage/update")
	public Object superManageUpdate(@RequestBody User form) {
		CODEMSG = ERROR;
		try {
			if(userService.findByExist(form)<0) {
				CODEMSG= USER_UNDEFINED;
				return finish(CODEMSG,null); 
			}
			Integer result = userService.superManageUpdate(form);
			if(result > 0){
				CODEMSG = SUCCESS;
			}
		}catch(Exception ex) {
			logger.info(ex.toString());
			CODEMSG = EXCEPTION;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return finish(CODEMSG,null);
		}
		return finish(CODEMSG,null);
	}
}
