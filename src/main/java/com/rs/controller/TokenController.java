package com.rs.controller;

import com.rs.dao.IUserDao;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Token控制层
 * @ClassName: TokenController
 * @Description: 
 * @Author sven
 * @DateTime 2019年6月13日 下午2:37:42
 */
@RestController
@RequestMapping("token")
public class TokenController extends BaseController {
		@Autowired
		private TokenParam tokenParam;
		@Autowired
		private IUserDao userDao;
		
	    @PostMapping("/token")
	    public Object getToken(@RequestBody User user) {
	        //数据库查询用户是否存在
	    	User user_tmp = userDao.findByLoginNameAndPass(user);
	    	if(StringUtils.isEmpty(user_tmp)) {
	    		return finish(LOGIN_UNVALID,user);
	    	}
	    	
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put(tokenParam.getLogin_user(), user_tmp);  //这里的key---->user_code
	
	        return finish(LOGIN_VALID, JwtUtil.getTokenByJson(map,
	        		tokenParam.getEncry_key(),
	        		tokenParam.getSecond_timeout()));
	        
	      /*  return JwtUtil.getTokenByJson(map,
	        		"sven",
	        		1000);*/
	        
	    }
}
