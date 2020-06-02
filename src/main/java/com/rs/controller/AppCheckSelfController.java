package com.rs.controller;
import com.rs.po.CheckSelf;
import com.rs.po.User;
import com.rs.service.CheckSelfService;
import com.rs.service.UserService;
import com.rs.util.CodeUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @ClassName: CheckSelfController
 * @Description: 自查自纠控制层
 * @Author tangsh
 * @DateTime 2020年3月2日 上午11:58:05
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/app/checkSelf")
public class AppCheckSelfController extends BaseController {

	@Autowired
	private CheckSelfService checkSelfService;
	@Autowired
	private UserService userService;
	
	private Logger logger = LoggerFactory.getLogger(AppCheckSelfController.class);
	
	/**
	 * 
	 * @Title: findByPageConfig
	 * @Description: 查询页面配置
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:22:55
	 * @param user
	 * @return
	 */
	@GetMapping(value = "/findByPageConfig")
	public Object findByPageConfig(User user){
		Map<Object, Object> map = new HashMap<>();
		try {
			map.put("pageConfig", checkSelfService.findByPageConfig(user));
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, map);
	}

	/**
	 * 
	 * @Title: save
	 * @Description: 新增数据
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:23:03
	 * @param map
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody Map<String, String> map) {
		Map<String, Object> newmap = new HashMap<>();
		User user = new User();
		try {
			String checkself_content = map.get("checkself_content");
			String checkself_code = CodeUtil.getSystemCode("checkSelf");
			String user_code = map.get("user_code");
			user.setUser_code(user_code);
			User newUser = userService.findByCode(user);
			
			newmap.put("company_code", newUser.getCompany_code());
			newmap.put("user_code", user_code);
			newmap.put("other_code", checkself_code);
			if(checkSelfService.save(checkself_content, checkself_code, user_code)>0){
				CODEMSG = SUCCESS;
			}
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, newmap);
	}
	/**
	 * 
	 * @Title: findByList
	 * @Description: 查询列表
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:23:09
	 * @param user_code
	 * @param pager_openset
	 * @param pager_offset
	 * @return
	 */
	@GetMapping(value = "/findByList")
	public Object findByList(String user_code, Integer pager_openset, Integer pager_offset){
		Map<Object, Object> map = new HashMap<>();
		try {
			map.put("pager_list",checkSelfService.findByList(user_code, pager_openset, pager_offset));
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, map);
	}
	/**
	 * 
	 * @Title: findByCode
	 * @Description: 查询明细
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:23:16
	 * @param checkself
	 * @return
	 */
	@GetMapping(value = "/findByCode")
	public Object findByCode(CheckSelf checkself) {
		Map<Object, Object> map = new HashMap<>();
		try {
			map.put("checkself",checkSelfService.findByCode(checkself));
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, map);
	}
}
