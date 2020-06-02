package com.rs.util;

import javax.servlet.http.HttpServletRequest;

import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.service.UserService;
/**
 * token解析用户对象
 * @ClassName: TokenUserUtil
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月17日 下午4:09:10
 */
public class TokenUserUtil {
	
	/**
	 * 生成用户对象
	 * @Title: generateUser
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月17日 下午4:10:12
	 * @param request
	 * @param tokenParam
	 * @return
	 */
	public static User generateUser(HttpServletRequest request,TokenParam tokenParam) {
		String token = request.getHeader("token");
		User user = JwtUtil.getValByT(token, tokenParam.getEncry_key(), tokenParam.getLogin_user() ,User.class);
		return user;
	}
	/**
	 * 根据编码查询用户
	 * @Title: findByCode
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月15日 下午3:23:06
	 * @param userService
	 * @param user
	 * @return
	 */
	public static User findByCode(UserService userService,User user) {
		user = userService.findByCode(user);
		return user;
	}
}
