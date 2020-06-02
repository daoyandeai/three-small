package com.rs.controller;
import com.rs.po.Region;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.service.RegionService;
import com.rs.service.UserService;
import com.rs.util.RegularUtil;
import com.rs.util.TokenUserUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/app/user")
public class AppUserController extends BaseController {

	@Autowired
	private UserService userService;
	@Autowired
	private RegionService regionService;
	@Autowired
	private TokenParam tokenParam;
	
	private Logger logger = LoggerFactory.getLogger(AppUserController.class);

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
	 * @Description: 
	 * @Author tangsh
	 * @DateTime 2020年1月8日 下午5:42:23
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(User form){
		Map<Object, Object> map = new HashMap<>();
		try {
			map.put("user_list", userService.findByList(form));
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
			if(userService.findByExist(form)>0) {
				CODEMSG= USER_EXIST;
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
	 * 查询乡厨列表
	 * @Title: chefList
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月15日 上午11:17:05
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/chef/list")
	public Object chefList(User form){
		Map<Object, Object> map = new HashMap<>();
		try {
			if(StringUtils.isEmpty(form.getUser_province())) {
				CODEMSG = PROVINCE_UNVALID;
				return finish(CODEMSG, null);
			}
			if(StringUtils.isEmpty(form.getUser_city())) {
				CODEMSG = CITY_UNVALID;
				return finish(CODEMSG, null);
			}
			if(StringUtils.isEmpty(form.getUser_area())) {
				CODEMSG = AREA_UNVALID;
				return finish(CODEMSG, null);
			}
			if(StringUtils.isEmpty(form.getUser_town())) {
				CODEMSG = TOWN_UNVALID;
				return finish(CODEMSG, null);
			}
			if(StringUtils.isEmpty(form.getUser_vill())) {
				CODEMSG = VILL_UNVALID;
				return finish(CODEMSG, null);
			}
			form.setUser_state(1);
			form.setUser_type("乡厨");
			
			List<Object> user_list = new ArrayList<Object>();
			List<User> query_list = userService.findByList(form);
			Map<String,Object> result_map = null;
			for (User r : query_list) {
				result_map = new HashMap<String,Object>();
				result_map.put("user_code", r.getUser_code());
				result_map.put("user_name", r.getUser_name());
				result_map.put("user_mobilephone", r.getUser_mobilephone());
				result_map.put("user_logo_url", r.getUser_logo_url());
				result_map.put("user_province", r.getUser_province());
				result_map.put("user_city", r.getUser_city());
				result_map.put("user_area", r.getUser_area());
				result_map.put("user_town", r.getUser_town());
				result_map.put("user_vill", r.getUser_vill());
				user_list.add(result_map);
			}
			map.put("chef_list", user_list);
			map.put("pager_count", userService.findByCount(form));
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.error(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, map);
	}
	
	/**
	 * 查询举办者列表
	 * @Title: chefList
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月15日 上午11:17:05
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/conduct/list")
	public Object conductList(User form){
		Map<Object, Object> map = new HashMap<>();
		try {
			String user_code = form.getUser_code();
			if(StringUtils.isEmpty(user_code) || RegularUtil.isSpecialChar(user_code) || RegularUtil.matchLength(user_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}	
			form = TokenUserUtil.findByCode(userService, form);
			if(StringUtils.isEmpty(form)) {
				CODEMSG = USER_UNDEFINED;
				return finish(CODEMSG, null);
			}
			List<Object> user_list = new ArrayList<Object>();
			List<User> query_list = userService.findListInnerReport(form);
			Map<String,Object> result_map = null;
			for (User r : query_list) {
				result_map = new HashMap<String,Object>();
				result_map.put("user_code", r.getUser_code());
				result_map.put("user_name", r.getUser_name());
				result_map.put("user_mobilephone", r.getUser_mobilephone());
				result_map.put("user_province", r.getUser_province());
				result_map.put("user_city", r.getUser_city());
				result_map.put("user_area", r.getUser_area());
				result_map.put("user_town", r.getUser_town());
				result_map.put("user_vill", r.getUser_vill());
				user_list.add(result_map);
			}
			map.put("conduct_list", user_list);
			map.put("pager_count", userService.findByCount(form));
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.error(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, map);
	}
}
