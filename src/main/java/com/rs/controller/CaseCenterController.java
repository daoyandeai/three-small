package com.rs.controller;
import com.rs.po.Accessory;
import com.rs.po.CaseCenter;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.service.AccessoryService;
import com.rs.service.CaseCenterService;
import com.rs.util.RegularUtil;
import com.rs.util.TokenUserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
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
@RequestMapping("/api/caseCenter")
public class CaseCenterController extends BaseController {

	@Autowired
	private CaseCenterService caseCenterService;
	@Autowired
	private AccessoryService accessoryService;
	@Autowired
	private TokenParam tokenParam;
	
	private Logger logger = LoggerFactory.getLogger(CaseCenterController.class);

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
	public Object list(CaseCenter form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String casecenter_title = form.getCasecenter_title();
			if(!StringUtils.isEmpty(casecenter_title)&&RegularUtil.isSpecialChar(casecenter_title)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			int pager_offset = form.getPager_offset();
			if(StringUtils.isEmpty(pager_offset) || !RegularUtil.isNumber(pager_offset+"")) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int pager_openset = form.getPager_openset();
			if(StringUtils.isEmpty(pager_openset) || !RegularUtil.isNumber(pager_openset+"")) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setProvince(user.getUser_province());
			form.setCity(user.getUser_city());
			form.setArea(user.getUser_area());
			form.setTown(user.getUser_town());
			form.setVill(user.getUser_vill());
			map.put("pager_count", caseCenterService.findByCount(form));
			map.put("pager_list",caseCenterService.findByList(form));
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
	 * @Title: save
	 * @Description: 案件新增
	 * @Author tangsh
	 * @DateTime 2020年3月16日 上午11:20:20
	 * @param form
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody CaseCenter form,HttpServletRequest request) {
		try {
			String casecenter_title = form.getCasecenter_title();
			if(StringUtils.isEmpty(casecenter_title) || RegularUtil.isSpecialChar(casecenter_title)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String casecenter_content = form.getCasecenter_content();
			if(StringUtils.isEmpty(casecenter_content) || RegularUtil.isSpecialChar(casecenter_content)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String user_code_main = form.getUser_code_main();
			if(StringUtils.isEmpty(user_code_main) || RegularUtil.matchLength(user_code_main, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			int result = caseCenterService.saveCaseCenter(form,user);
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
			map.put("caseCenter", caseCenterService.findCaseCenter(form));
			Accessory accessory = new Accessory();
			accessory.setOther_code(casecenter_code);
			map.put("accessory_list", accessoryService.findByAll(accessory));
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
	 * @Description: 编辑案件
	 * @Author tangsh
	 * @DateTime 2020年3月16日 上午11:26:43
	 * @param form
	 * @return
	 */
	@PutMapping(value = "/update")
	public Object update(@RequestBody CaseCenter form,HttpServletRequest request) {
		try {
			String caseCenter_code = form.getCasecenter_code();
			if(StringUtils.isEmpty(caseCenter_code) || RegularUtil.matchLength(caseCenter_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String casecenter_title = form.getCasecenter_title();
			if(StringUtils.isEmpty(casecenter_title) || RegularUtil.isSpecialChar(casecenter_title)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String casecenter_content = form.getCasecenter_content();
			if(StringUtils.isEmpty(casecenter_content) || RegularUtil.isSpecialChar(casecenter_content)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String user_code_main = form.getUser_code_main();
			if(StringUtils.isEmpty(user_code_main) || RegularUtil.matchLength(user_code_main, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(StringUtils.isEmpty(form.getUser_code_audit())) {
				User user = TokenUserUtil.generateUser(request, tokenParam);
				form.setUser_code_audit(user.getUser_code());
				form.setUser_name_audit(user.getUser_name());
			}
			int result = caseCenterService.update(form);
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
	
	/**
	 * 
	 * @Title: delete
	 * @Description: 案件删除接口
	 * @Author tangsh
	 * @DateTime 2020年3月16日 下午3:19:07
	 * @param form
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Object delete(CaseCenter form) {
		try {
			String caseCenter_code = form.getCasecenter_code();
			if (StringUtils.isEmpty(caseCenter_code) || RegularUtil.isSpecialChar(caseCenter_code) || RegularUtil.matchLength(caseCenter_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int result = caseCenterService.delete(form);
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
