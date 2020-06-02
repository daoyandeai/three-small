package com.rs.config;

import com.rs.interceptor.AccessLimitInterceptor;
import com.rs.interceptor.MethodInterceptor;
import com.rs.interceptor.SignAuthInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 拦截器配置
 * 
 * @ClassName: InterceptorConfig
 * @Description:
 * @Author sven
 * @DateTime 2019年7月14日 下午9:37:40
 */
@SuppressWarnings("deprecation")
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(InterceptorConfig.class);
	
	@Value(value="${spring.profiles.active}")
	private String profiles;
	
	/**
	 * 添加spring中的拦截器路径
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(accessLimitInterceptor()).addPathPatterns("/api/**");
		registry.addInterceptor(methodInterceptor()).addPathPatterns("/api/**").excludePathPatterns("/api/login","/api/token","/api/loginout","/api/open/login","/api/file/upload","/api/mess","/api/user/exist","/api/user/upname","/api/messLog/save","/api/company/geocoder","/api/department/geocoder");
		if(!profiles.contains("dev")) {
			registry.addInterceptor(signInterceptor()).addPathPatterns("/api/**").excludePathPatterns("/api/login","/api/token","/api/loginout","/api/open/login","/api/file/upload","/api/mess","/api/user/exist","/api/user/upname","/api/messLog/save","/api/company/geocoder","/api/department/geocoder");
		}
		super.addInterceptors(registry);
	}
	
	@Bean
	public AccessLimitInterceptor accessLimitInterceptor() {
		return new AccessLimitInterceptor();
	}
	
	@Bean
	public MethodInterceptor methodInterceptor() {
		return new MethodInterceptor();
	}
	
	@Bean
	public SignAuthInterceptor signInterceptor() {
		return new SignAuthInterceptor();
	}
}
