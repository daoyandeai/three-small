package com.rs.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.rs.po.CodeMsgEnum;
import com.rs.po.RedisEnum;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.util.JwtUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * 方法拦截器
 * @ClassName: MethodInterceptor
 * @Description: 
 * @Author sven
 * @DateTime 2019年8月30日 上午11:57:36
 */
public class MethodInterceptor implements HandlerInterceptor {

	@Autowired
	private TokenParam tokenParam;
	@Autowired
    private RedisTemplate<String,Object> redisTemplate;
	private static final Logger logger = LoggerFactory.getLogger(MethodInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			String token = request.getHeader("token");
			logger.info("token：{}",token);
			if (StringUtils.isEmpty(token)) {
				tokenUnValid(response, CodeMsgEnum.TOKEN_UNVALID.getCode(),CodeMsgEnum.TOKEN_UNVALID.getMsg(),null);
				return false;
			}
			User user = JwtUtil.getValByT(token, tokenParam.getEncry_key(),tokenParam.getLogin_user() ,User.class);
			if(StringUtils.isEmpty(user)){
				tokenUnValid(response, CodeMsgEnum.TOKEN_UNVALID.getCode(),CodeMsgEnum.TOKEN_UNVALID.getMsg(),null);
				return false;
			}
			String r_token = (String) redisTemplate.opsForValue().get(RedisEnum.TOKEN.getKey()+user.getUser_loginname());
			if (StringUtils.isEmpty(r_token)){
				tokenUnValid(response, CodeMsgEnum.TOKEN_UNVALID.getCode(),CodeMsgEnum.TOKEN_UNVALID.getMsg(),null);
				return false;
			}
			if (!token.equals(r_token)){
				tokenUnValid(response, CodeMsgEnum.TOKEN_DISCONNECT.getCode(),CodeMsgEnum.TOKEN_DISCONNECT.getMsg(),null);
				return false;
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}
	
	/**
	 * token
	 * @Title: tokenValid
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年9月2日 上午11:09:51
	 * @param rp
	 * @param code
	 * @param msg
	 * @param data
	 * @throws IOException
	 */
	private void tokenUnValid(HttpServletResponse rp,int code,String msg,Object data) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("msg", msg);
		map.put("data",StringUtils.isEmpty(data) ? "" : data);
		rp.setHeader("Content-type", "text/json;charset=UTF-8");
		rp.setCharacterEncoding("UTF-8");
		rp.getWriter().write(JSONObject.toJSONString(map));
	}
	
}
