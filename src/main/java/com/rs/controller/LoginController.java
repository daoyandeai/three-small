package com.rs.controller;

import com.rs.po.Department;
import com.rs.po.RedisEnum;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.service.DepartmentService;
import com.rs.service.UserService;
import com.rs.util.JwtUtil;
import com.rs.util.RegularUtil;
import com.rs.util.ucpass.RestTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @ClassName: LoginController
 * @Description:用户登录控制层
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api")
public class LoginController extends BaseController {

	@Autowired
	private TokenParam tokenParam;
	@Autowired
	private UserService userService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	// 过期时间倍数 单位:秒（s）
	private static final int EXPIRETIME = 24;

	private Logger logger = LoggerFactory.getLogger(LoginController.class);

	/**
	 * 
	 * @Title: login
	 * @Description: 登录接口
	 * @Author tangsh
	 * @DateTime 2020年2月10日 上午11:17:47
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/login")
	public Object login(@RequestBody User form) {
		Map<String, Object> map = new HashMap<>();
		try {
			//检测是否输入账户和密码
			if((form.getUser_loginname()==null||form.getUser_loginname()=="")||(form.getUser_loginpass()==null||form.getUser_loginpass()=="")){
				CODEMSG = LOGIN_UNVALID;
				return finish(CODEMSG, null);
			}
			//如果有账户和密码查询数据库
			form.setUser_state(1);
			form = userService.login(form);
			//判断存在该用户
			if (StringUtils.isEmpty(form)) {
				map = null;
				CODEMSG = LOGIN_UNVALID;
				return finish(CODEMSG, map);
			}
			//删除redis中存在的token用于单点登录
			redisTemplate.delete(RedisEnum.TOKEN.getKey() + form.getUser_loginname());
			form.setQyurl(tokenParam.getQyurl());
			map.put(tokenParam.getLogin_user(),form);
			//生成token
			String token = JwtUtil.getTokenByJson(map,
					tokenParam.getEncry_key(),
					tokenParam.getSecond_timeout());
			form.setToken(token);
			//将token放入到redis中有效期24小时
			redisTemplate.opsForValue().set(RedisEnum.TOKEN.getKey() + form.getUser_loginname(),token,EXPIRETIME,TimeUnit.HOURS);
			map.put("Token", form.getToken());
			map.put("manage_type",form.getManage_type());
			map.put("user_code",form.getUser_code());
			map.put("user_name",form.getUser_name());
			map.put("user_level",form.getUser_level());
			map.put("user_type",form.getUser_type());
			map.put("user_idcard",form.getUser_idcard());
			map.put("user_sex",form.getUser_sex());
			map.put("user_address",form.getUser_address());
			map.put("province",form.getUser_province());
			map.put("city",form.getUser_city());
			map.put("area",form.getUser_area());
			map.put("town",form.getUser_town());
			map.put("vill",form.getUser_vill());
			//查询用户角色菜单
			map.put("menu_list",userService.findLoginMenu(form));
			map.put("menu_btns",userService.findLoginMenuButton(form));
			map.put("qyurl", tokenParam.getQyurl());
			map.remove(tokenParam.getLogin_user());
			//查询当前登录用户所在监管部门
			Department dr = new Department();
			dr.setUser_code(form.getUser_code());
			map.put("department", departmentService.findByUserCode(dr));
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, map);
	}

	//注销登录
	@GetMapping(value = "/loginout")
	public Object loginout(User form) {
		try {
			//判断token是否存在redis中，如果有就删除，如果没有就不删除
			String token = (String)redisTemplate.opsForValue().get(RedisEnum.TOKEN.getKey() + form.getUser_loginname());
			if(StringUtils.isEmpty(token)){
				CODEMSG = TOKEN_UNVALID;
				return finish(CODEMSG,null);
			}
			redisTemplate.delete(RedisEnum.TOKEN.getKey() + form.getUser_loginname());
			CODEMSG = LOGINOUT_VALID;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, null);
	}
	
	
	
	private static final Integer COUNT = 4;
	/**
	 * 
	 * @Title: getMesscode
	 * @Description: 获取短信验证码
	 * @Author tangsh
	 * @DateTime 2020年2月10日 上午11:18:52
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/mess")
	public Object getMesscode(User form) {
		if(StringUtils.isEmpty(form.getUser_loginname())){
			CODEMSG = KEY_PARAM_EXIST;
			return finish(CODEMSG,null);
		}
		form.setUser_state(1);
		if(userService.findByExist(form)<1) {
			CODEMSG = USER_UNDEFINED;
			return finish(CODEMSG,null);
		}
		String messcode = "";
		try {
			Random ran = new Random();
	        if (COUNT == 1) {
	        	CODEMSG = KEY_PARAM_EXIST;
				return finish(CODEMSG,null);
	        }
	        int bitField = 0;
	        char[] chs = new char[COUNT];
	        for (int i = 0; i < COUNT ; i++) {
	            while(true) {
	                int k = ran.nextInt(10);
	                if( (bitField & (1 << k)) == 0) {
	                    bitField |= 1 << k;
	                    chs[i] = (char)(k + '0');
	                    break;
	                }
	            }
	        }
	        messcode = new String(chs);
	        //将token放入到redis中有效期24分钟
			redisTemplate.opsForValue().set(RedisEnum.MESSCODE.getKey() + form.getUser_loginname(),messcode,EXPIRETIME,TimeUnit.MINUTES);
	        System.out.println("短信码====="+messcode);
	        form.setMesscode(messcode);
	        if(StringUtils.isEmpty(form.getMesstype())) {//找回密码
	        	RestTest.sms(tokenParam.getRest_server(),tokenParam.getIs_test(),"90542",messcode,form.getUser_loginname());
	        	return finish(CODEMSG, form);
	        }
	        RestTest.sms(tokenParam.getRest_server(),tokenParam.getIs_test(),form.getMesstype(),messcode,form.getUser_loginname());
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			messcode = null;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, messcode);
	}
	
	/**
	 * 根据user_code查询token
	 * @Title: token
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月29日 下午4:28:40
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/token")
	public Object token(User form) {
		Map<String, Object> map = new HashMap<>();
		try {
			String user_code = form.getUser_code();
			if(StringUtils.isEmpty(user_code) || RegularUtil.isSpecialChar(user_code) || RegularUtil.matchLength(user_code,25)) {
				CODEMSG = USER_CODE_UNVALID;
				return finish(CODEMSG, null);
			}
			form = userService.findByCode(form);
			if(StringUtils.isEmpty(form)) {
				CODEMSG = USER_UNDEFINED;
				return finish(CODEMSG, null);
			}
			//判断token是否存在redis中，如果有就删除，如果没有就不删除
			String token = (String)redisTemplate.opsForValue().get(RedisEnum.TOKEN.getKey() + form.getUser_loginname());
			if(StringUtils.isEmpty(token)){
				CODEMSG = TOKEN_UNVALID;
				return finish(CODEMSG,null);
			}
			map.put("token", token);
			map.put("user_loginname", form.getUser_loginname());
			map.put("user_loginpass", form.getUser_loginpass());
			map.put("qyurl", tokenParam.getQyurl());
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.error(ex.toString());
		}
		return finish(CODEMSG, map);
	}
}
