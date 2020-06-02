package com.rs.controller;
import com.rs.po.Accessory;
import com.rs.po.CaseCenter;
import com.rs.po.User;
import com.rs.service.AccessoryService;
import com.rs.service.CaseCenterService;
import com.rs.service.UserService;
import com.rs.util.CodeUtil;
import com.rs.util.RegularUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
/**
 * 
 * @ClassName: CaseCenterController
 * @Description: 案件中心控制层
 * @Author tangsh
 * @DateTime 2020年3月16日 上午11:01:47
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/app/caseCenter")
public class AppCaseCenterController extends BaseController {

	@Autowired
	private CaseCenterService caseCenterService;
	@Autowired
	private AccessoryService accessoryService;
	@Autowired
	private UserService userService;
	
	private Logger logger = LoggerFactory.getLogger(AppCaseCenterController.class);

	/**
	 * 
	 * @Title: list
	 * @Description: 查询案件中心列表（分页）
	 * @Author tangsh
	 * @DateTime 2020年3月16日 上午11:03:04
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(@RequestParam Integer pager_offset,
			@RequestParam Integer pager_openset,
			@RequestParam String casecenter_title,
			@RequestParam Integer casecenter_audit_state,
			@RequestParam Integer result_state,
			@RequestParam String user_code,
			HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			
			User user = new User();
			user.setUser_code(user_code);
			User newUser = userService.findByCode(user);
			CaseCenter caseCenter = new CaseCenter();
			caseCenter.setPager_offset(pager_offset);
			caseCenter.setPager_offset(pager_offset);
			caseCenter.setCasecenter_title(casecenter_title);
			caseCenter.setCasecenter_audit_state(casecenter_audit_state);
			caseCenter.setResult_state(result_state);
			if (casecenter_audit_state == 1) {

				caseCenter.setProvince(newUser.getUser_province());
				caseCenter.setCity(newUser.getUser_city());
				caseCenter.setArea(newUser.getUser_area());
				caseCenter.setTown(newUser.getUser_town());
				caseCenter.setVill(newUser.getUser_vill());
			} else {
				caseCenter.setUser_code_main(user_code);
			}


			map.put("pager_count", caseCenterService.findByCount(caseCenter));
			map.put("pager_list",caseCenterService.findByList(caseCenter));
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, map);
	}
	

	/**
	 * 
	 * @Title: single
	 * @Description: 查询单个案件详情
	 * @Author tangsh
	 * @DateTime 2020年3月16日 上午11:25:20
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/single")
	public Object single(CaseCenter form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String casecenter_code = form.getCasecenter_code();
			if(StringUtils.isEmpty(casecenter_code) || RegularUtil.matchLength(casecenter_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			
			CaseCenter caseCenter = caseCenterService.findByCode(form);
			map.put("caseCenter", caseCenter);
			Accessory accessory = new Accessory();
			accessory.setOther_code(casecenter_code);
			map.put("accessory_list", accessoryService.findByAll(accessory));
			
			if (!StringUtils.isEmpty(caseCenter.getUser_code_main())) {
				User user = new User();
				user.setUser_code(caseCenter.getUser_code_main());
				User newUser = userService.findByCode(user);
				map.put("user_code_main", newUser);
					
			}
			if (!StringUtils.isEmpty(caseCenter.getUser_codes_secondary())) {
				List<User> lt = caseCenterService.findByUser(caseCenter.getUser_codes_secondary());
				map.put("user_code_secondary", lt);
					
			}
			if (!StringUtils.isEmpty(caseCenter.getUser_code_audit())) {
				User user = new User();
				user.setUser_code(caseCenter.getUser_code_audit());
				User newUser = userService.findByCode(user);
				map.put("user_code_audit", newUser);
			}
			
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, map);
	}
	
	
	/**
	 * 
	 * @Title: update
	 * @Description: 处理案件案件
	 * @Author tangsh
	 * @DateTime 2020年3月16日 上午11:26:43
	 * @param form
	 * @return
	 */
	@PutMapping(value = "/update")
	public Object update(@RequestBody Map<String,String> map) {
		try {
			String caseCenter_code = map.get("casecenter_code");
			if(StringUtils.isEmpty(caseCenter_code) || RegularUtil.matchLength(caseCenter_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			CaseCenter caseCenter = new CaseCenter();
			caseCenter.setCasecenter_code(caseCenter_code);
			caseCenter.setCasecenter_result(map.get("casecenter_result"));
			String rs=map.get("result_state");
			if(StringUtils.isEmpty(rs)) {
				rs="2";
			}
			caseCenter.setResult_state(Integer.parseInt(rs));
			int result = caseCenterService.update(caseCenter);
			
			String images = map.get("images");
			if (!StringUtils.isEmpty(images)) {
				String[] arrImg = images.split(",");
				for(Object image: arrImg) {
					Accessory accessory = new Accessory();
					accessory.setAccessory_code(CodeUtil.getSystemCode("accessory"));
					accessory.setAccessory_type("案件中心");
					accessory.setAccessory_url((String)image);
					accessory.setOther_code(caseCenter_code);
					
					accessoryService.save(accessory);
				}
			}
			
			if (result > 0) {
				CODEMSG = SUCCESS;
			} else {
				CODEMSG = ERROR;
			}
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, null);
	}
}
