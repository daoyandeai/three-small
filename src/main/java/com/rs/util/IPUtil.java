package com.rs.util;

import javax.servlet.http.HttpServletRequest;
/**
 * IP辅助类
 * @ClassName: IPUtil
 * @Description: 
 * @Author sven
 * @DateTime 2019年7月11日 下午4:42:18
 */
public class IPUtil {
	
	public static String getRealIp(HttpServletRequest req) {  		  
	    String ip = req.getHeader("X-Forwarded-For");  	  
	    if (ip != null) {  
	        if (ip.indexOf(',') == -1) {  
	            return ip;  
	        }  
	        return ip.split(",")[0];  
	    }  		  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = req.getHeader("Proxy-Client-IP");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = req.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = req.getRemoteAddr();  
	    }  
	  
	    return ip;  
	}  
}
