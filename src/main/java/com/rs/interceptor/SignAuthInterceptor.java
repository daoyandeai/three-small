package com.rs.interceptor;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSONObject;
import com.rs.po.CodeMsgEnum;
import com.rs.po.TokenParam;
import com.rs.util.HttpUtil;
import com.rs.util.SignUtil;
/**
 * 签名验证拦截器
 * @ClassName: SignAuthInterceptor
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月4日 下午4:02:04
 */
public class SignAuthInterceptor implements HandlerInterceptor {
	
	@Autowired
	private TokenParam tokenParam;
	private static final Logger logger = LoggerFactory.getLogger(SignAuthInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
        // 防止流读取一次后就没有了, 所以需要将流继续写出去
		HttpServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
    	//获取客户端时间+10分钟，如果小于系统时间,则该次请求过期
    	String timestamp = request.getHeader("timestamp");
    	logger.info("timestamp_client : {}", timestamp);
    	if(StringUtils.isEmpty(timestamp)) {
    		 signUnValid(response,CodeMsgEnum.TIMESTAMP_UNVALID.getCode(),CodeMsgEnum.TIMESTAMP_UNVALID.getMsg(),null);
             return false;
    	}
    	
    	String token = request.getHeader("token");
    	logger.info("token : {}", token);
    	
    	long timestampDate = Long.valueOf(timestamp) + 1000 * 60 * 10;
    	long currDate = System.currentTimeMillis();
        // 请求过期
        if (timestampDate < currDate) {
            signUnValid(response,CodeMsgEnum.TIMESTAMP_UNVALID.getCode(),CodeMsgEnum.TIMESTAMP_UNVALID.getMsg(),null);
            return false;
        }
        
        //获取app_key 
		String app_key_client = request.getHeader("app_key");
		String app_key_server = tokenParam.getApp_key();
	    logger.info("app_key_server : {}", app_key_server);
	    logger.info("app_key_client : {}", app_key_client);
	    
		/*if(!app_key_server.equals(app_key_client)) {
			signUnValid(response,CodeMsgEnum.APP_KEY_UNVALID.getCode(),CodeMsgEnum.APP_KEY_UNVALID.getMsg(),null);
            return false;
		}*/
	    
		//获取app_secret
		String app_secret = tokenParam.getApp_secret();
		if(StringUtils.isEmpty(app_secret)) {
			signUnValid(response,CodeMsgEnum.APP_SECRET_UNVALID.getCode(),CodeMsgEnum.APP_SECRET_UNVALID.getMsg(),null);
			return false;
		}
        // 对参数进行签名验证
		// 获取全部参数(包括URL和body上的)
        SortedMap<String, Object> allParams = HttpUtil.getAllParams(requestWrapper);
        boolean isSigned = SignUtil.verifySign(app_secret,allParams);
        if (isSigned) {
        	return true;
        } else {
        	signUnValid(response,CodeMsgEnum.SIGN_UNVALID.getCode(),CodeMsgEnum.SIGN_UNVALID.getMsg(),null);
        	return false;
        }
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
	/**
	 * 签名无效
	 * @Title: signUnValid
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月4日 下午4:06:09
	 * @param rp
	 * @param code
	 * @param msg
	 * @param data
	 * @throws IOException
	 */
	private void signUnValid(HttpServletResponse rp,int code,String msg,Object data) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("msg", msg);
		map.put("data",StringUtils.isEmpty(data) ? "" : data);
		rp.setHeader("Content-type", "text/json;charset=UTF-8");
		rp.setCharacterEncoding("UTF-8");
		rp.getWriter().write(JSONObject.toJSONString(map));
	}
}
