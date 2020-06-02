package com.rs.controller;

import com.rs.po.RedisEnum;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.service.UserService;
import com.rs.util.JwtUtil;

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
import java.util.concurrent.TimeUnit;

/**
 * 
 * @ClassName: OpenLoginController
 * @Description: 用户登录控制层
 * @Author tangsh
 * @DateTime 2020年4月1日 上午9:55:50
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/open")
public class OpenLoginController extends BaseController {

	@Autowired
	private TokenParam tokenParam;
	@Autowired
	private UserService userService;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	// 过期时间倍数 单位:秒（s）
	private static final int EXPIRETIME = 2;

	private Logger logger = LoggerFactory.getLogger(OpenLoginController.class);

	/**
	 * 
	 * @Title: login
	 * @Description: 登录接口
	 * @Author tangsh
	 * @DateTime 2020年4月1日 上午9:55:44
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/login")
	public Object login(@RequestBody User form) {
		Map<String, Object> map = new HashMap<>();
		try {
			if(StringUtils.isEmpty(form.getUser_loginname())||StringUtils.isEmpty(form.getUser_loginpass())) {
				CODEMSG = LOGIN_UNVALID;
				return finish(CODEMSG, null);
			}
			form.setUser_state(1);
			form = userService.login(form);
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
			map.put("token", form.getToken());
			map.put("expires_in", EXPIRETIME*60*60);
			map.remove(tokenParam.getLogin_user());
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, map);
	}
}
