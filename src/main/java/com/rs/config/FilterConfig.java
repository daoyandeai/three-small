package com.rs.config;

import com.rs.filter.AuthenFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * 过滤器注册中心
 * @ClassName: FilterConfig
 * @Description: 
 * @Author sven
 * @DateTime 2019年6月12日 下午5:13:59
 */
@Configuration
public class FilterConfig {
	
	@Value("${token.login_user}")
	private String login_user;
	
	@Value("${token.encry_key}")
	private String encry_key;
	
	@Value("${token.second_timeout}")
	private Integer second_timeout;

    @Bean
    public FilterRegistrationBean<AuthenFilter> registFilter() {
        FilterRegistrationBean<AuthenFilter> registration = new FilterRegistrationBean<AuthenFilter>();
        registration.setFilter(new AuthenFilter());
        registration.addInitParameter("encry_key", encry_key);
        registration.addInitParameter("login_user", login_user);
//        registration.addUrlPatterns("/api/*");
        registration.setOrder(1);
        return registration;
    }
}
