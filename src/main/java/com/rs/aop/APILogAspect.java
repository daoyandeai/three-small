package com.rs.aop;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.alibaba.fastjson.JSON;
import com.rs.po.TokenParam;
import com.rs.po.APILog;
import com.rs.util.CodeUtil;
import com.rs.util.HttpUtil;
import com.rs.util.IPUtil;
/**
 * 日志aop统一切面处理
 * @ClassName: WebLogAspect
 * @Description: 
 * @Author sven
 * @DateTime 2019年9月5日 上午11:34:05
 */
@Aspect
@Component
@Order(1)
public class APILogAspect {
	@Autowired
	private TokenParam tokenParam;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(APILogAspect.class);
	
    @Pointcut("execution(public * com.rs.controller.*.*(..))")
    public void apiLog() {
    }

    @Before("apiLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
    }

    @AfterReturning(value = "apiLog()", returning = "ret")
    public void doAfterReturning(Object ret) throws Throwable {
    	//处理完请求，返回内容
    	//LOGGER.info("RESPONSE : " + ret);
    }

    @Around("apiLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
    	
        long beginTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        
        String startTime = timeStampToDate(beginTime);
        String finishTime = timeStampToDate(endTime);
        
        //获取当前请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        
        //记录请求信息
        APILog apiLog = new APILog();
        Object result = joinPoint.proceed();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
       
        String urlStr = request.getServerName()+request.getContextPath();
        apiLog.setBasePath(urlStr.substring(0,urlStr.indexOf("/")));
        apiLog.setMethod(request.getMethod());
        apiLog.setParameter(getParameter(method,joinPoint.getArgs()));
        apiLog.setResult(result);
        apiLog.setStartTime(startTime);
        apiLog.setFinishTime(finishTime);
        apiLog.setSpendTime((int) (endTime - beginTime));
        apiLog.setUri(request.getRequestURI());
        apiLog.setIp(IPUtil.getRealIp(request));
        //根据IP获取城市定位
        StringBuffer buffer = new StringBuffer();
        buffer.append(tokenParam.getBaidu_url()).append(tokenParam.getBaidu_uri()).append("ip=").append(apiLog.getIp()).append("&ak=").append(tokenParam.getBaidu_ak());
        String address = HttpUtil.sendGet(buffer.toString(), "UTF-8", null);
        address = CodeUtil.unicodeToString(address);
        apiLog.setAddress(address);
        
        LOGGER.info("{}",JSON.toJSON(apiLog));
        return result;
    }

    /**
     * 根据方法和传入的参数获取请求参数
     */
    private Object getParameter(Method method, Object[] args) {
        List<Object> argList = new ArrayList<Object>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            //将RequestBody注解修饰的参数作为请求参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                argList.add(args[i]);
            }
            //将RequestParam注解修饰的参数作为请求参数
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (requestParam != null) {
                Map<String, Object> map = new HashMap<String, Object>();
                String key = parameters[i].getName();
                if (!StringUtils.isEmpty(requestParam.value())) {
                    key = requestParam.value();
                }
                map.put(key, args[i]);
                argList.add(map);
            }
        }
        if (argList.size() == 0) {
            return null;
        } else if (argList.size() == 1) {
            return argList.get(0);
        } else {
            return argList;
        }
    }
    
    /**
	 * 时间戳转换日期时间
	 * @Author tangsh
	 * @param str 时间戳
	 * @return
	 */
	public String timeStampToDate(long time) {
		 if (time < 10000000000L) {
		     time = time * 1000;
		 }
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String sd = sdf.format(new Date(Long.parseLong(String.valueOf(time))));
		 return sd;
	}
}
