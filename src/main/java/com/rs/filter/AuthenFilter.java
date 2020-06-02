package com.rs.filter;

import com.rs.interceptor.BodyReaderHttpServletRequestWrapper;
import com.rs.controller.BaseController;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.IOException;
import java.util.Map;

/**
 * token认证过滤器：如果此处不用如下注解,则需要通过FilterConfig去注册
 * 
 * @ClassName: AuthenFilter
 * @Description:
 * @Author sven
 * @DateTime 2019年6月12日 下午5:12:01
 */
public class AuthenFilter extends BaseController implements Filter {
	@Override
	public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		// 防止流读取一次后就没有了, 所以需要将流继续写出去
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		ServletRequest requestWrapper = null;
		try {
			//获取文件
			MultipartResolver resolver = new CommonsMultipartResolver(servletRequest.getServletContext());
			MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(httpServletRequest);
			Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
			// 这里将原始request传入，读出流并存储
			requestWrapper = new BodyReaderHttpServletRequestWrapper(httpServletRequest);
			// 这里将原始request替换为包装后的request，此后所有进入controller的request均为包装后的
			requestWrapper.setAttribute("file", fileMap);
		}catch(Exception ex) {
			requestWrapper = new BodyReaderHttpServletRequestWrapper(httpServletRequest);
		}finally {
			filterChain.doFilter(requestWrapper, servletResponse);
		}
	}

	@Override
	public void destroy() {
	}
}