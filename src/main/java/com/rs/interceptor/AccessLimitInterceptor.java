package com.rs.interceptor;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.rs.po.AccessLimit;
import com.rs.po.CodeMsgEnum;
import com.rs.util.IPUtil;

/**
 * API限流拦截器
 * @ClassName: AccessLimitInterceptor
 * @Description: 
 * @Author sven
 * @DateTime 2019年8月22日 下午2:49:06
 */
public class AccessLimitInterceptor implements HandlerInterceptor {
	
	@Autowired
    private RedisTemplate<String,Object> redisTemplate;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			if (!method.isAnnotationPresent(AccessLimit.class)) {
				return true;
			}
			AccessLimit accessLimit = method.getAnnotation(AccessLimit.class);
			if (accessLimit == null) {
				return true;
			}
			int limit = accessLimit.limit();
			int sec = accessLimit.sec();
			
			//String key =  "192.168.11.12"+request.getRequestURI();
			String key =  IPUtil.getRealIp(request) + request.getRequestURI();
			
			Object maxLimit = redisTemplate.opsForValue().get(key);
			if(StringUtils.isEmpty(maxLimit)) {
				// set时一定要加过期时间
				redisTemplate.opsForValue().set(key, 1, sec, TimeUnit.SECONDS);
			}else if (Integer.valueOf(maxLimit.toString()) < limit) {
				redisTemplate.opsForValue().set(key, Integer.valueOf(maxLimit.toString()) + 1, sec, TimeUnit.SECONDS);
			} else {
				apiLimit(response,CodeMsgEnum.API_LIMIT.getCode(),CodeMsgEnum.API_LIMIT.getMsg());
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
	
	private void apiLimit(HttpServletResponse rp,int code,String msg) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("msg", msg);
		map.put("data","");
		rp.setHeader("Content-type", "text/html;charset=UTF-8");
		rp.setCharacterEncoding("UTF-8");
		rp.getWriter().write(JSONObject.toJSONString(map));
	}
}
