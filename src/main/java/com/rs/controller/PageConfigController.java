package com.rs.controller;
import com.alibaba.fastjson.JSON;
import com.rs.po.PageConfig;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.po.returnPo.PageConfigReturn;
import com.rs.service.PageConfigService;
import com.rs.util.CodeUtil;
import com.rs.util.RegularUtil;
import com.rs.util.TokenUserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
/**
 * 页面参数模板控制层
 * @ClassName: CompanyTypeController
 * @Description: 
 * @Author sven
 * @DateTime 2020年2月5日 下午4:24:50
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/pageConfig")
public class PageConfigController extends BaseController {

	@Autowired
	private PageConfigService pageConfigService;
	@Autowired
	private TokenParam tokenParam;
	
	private Logger logger = LoggerFactory.getLogger(PageConfigController.class);


	/**
	 * 查询页面参数模板_分页
	 * @Title: list
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:34:44
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(PageConfig form,HttpServletRequest request) {
		Map<String,Object> map = new HashMap<>();
		try {
			int owner_type = form.getOwner_type();
			User user = TokenUserUtil.generateUser(request, tokenParam);
			switch (owner_type) {
			case 1:
				//全部
				form.setUser_code_add(null);
				break;
			case 2:
				//自己
				form.setUser_code_add(user.getUser_code());
				break;
			}
			map.put("page_config_list",pageConfigService.findByList_app(form));
			map.put("pager_count",pageConfigService.findByCount(form));
			CODEMSG = SUCCESS ;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, map);
	}

	/**
	 * 新增页面参数模板
	 * @Title: save
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:35:40
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody PageConfig form ,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String companytype_code = form.getCompanytype_code();
			if (StringUtils.isEmpty(companytype_code) || RegularUtil.isSpecialChar(companytype_code)|| RegularUtil.matchLength(companytype_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String companytag_code = form.getCompanytag_code();
			if (StringUtils.isEmpty(companytag_code) || RegularUtil.matchLength(companytag_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String page_config_name = form.getPage_config_name();
			if(!StringUtils.isEmpty(page_config_name) && RegularUtil.isSpecialChar(page_config_name)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_code_add(user.getUser_code());
			form.setUser_name_add(user.getUser_name());
			form.setPage_config_code(CodeUtil.getSystemCode("pageConfig"));
			int count = pageConfigService.findByExist(form);
			if (count > 0) {
				CODEMSG = PAGE_CONFIG_NAME_EXIST;
				return finish(CODEMSG, null);
			}
			form.setPage_config_content(JSON.toJSONString(form.getPage_config_content()));
			int result = pageConfigService.save(form);
			if (result > 0) {
				CODEMSG = SUCCESS;
			} else {
				map = null;
				CODEMSG = ERROR;
			}
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, map);
	}

	/**
	 * 查询单条页面参数模板
	 * @Title: single
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:35:51
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/single")
	public Object single(PageConfig form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String page_config_code = form.getPage_config_code();
			if (StringUtils.isEmpty(page_config_code) || RegularUtil.matchLength(page_config_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			PageConfigReturn pcr = pageConfigService.findByCode_app(form);
			if (!StringUtils.isEmpty(pcr)) {
				map.put("page_config", pcr);
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
	 * 编辑页面参数模板
	 * @Title: update
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:36:07
	 * @param form
	 * @return
	 */
	@PutMapping(value = "/update")
	public Object update(@RequestBody PageConfig form, HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String companytype_code = form.getCompanytype_code();
			if (StringUtils.isEmpty(companytype_code) || RegularUtil.isSpecialChar(companytype_code)|| RegularUtil.matchLength(companytype_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String companytag_code = form.getCompanytag_code();
			if (StringUtils.isEmpty(companytag_code) || RegularUtil.matchLength(companytag_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String page_config_code = form.getPage_config_code();
			if (StringUtils.isEmpty(page_config_code) || RegularUtil.matchLength(page_config_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String page_config_name = form.getPage_config_name();
			if(!StringUtils.isEmpty(page_config_name) && RegularUtil.isSpecialChar(page_config_name)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			int count = pageConfigService.findByExist(form);
			if (count > 0) {
				CODEMSG = PAGE_CONFIG_NAME_EXIST;
				return finish(CODEMSG, null);
			}
			form.setUser_code_update(user.getUser_code());
			form.setUser_name_update(user.getUser_name());
			form.setPage_config_content(JSON.toJSONString(form.getPage_config_content()));
			int result = pageConfigService.update(form);
			if (result > 0) {
				CODEMSG = SUCCESS;
			} else {
				map = null;
				CODEMSG = ERROR;
			}
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, map);
	}
	
	/**
	 * 查询页面参数模板是否存在
	 * @Title: exist
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月6日 上午10:23:17
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/exist")
	public Object exist(PageConfig form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String companytype_code = form.getCompanytype_code();
			if (StringUtils.isEmpty(companytype_code) || RegularUtil.isSpecialChar(companytype_code)|| RegularUtil.matchLength(companytype_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String companytag_code = form.getCompanytag_code();
			if (StringUtils.isEmpty(companytag_code) || RegularUtil.matchLength(companytag_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String page_config_name = form.getPage_config_name();
			if(!StringUtils.isEmpty(page_config_name) && RegularUtil.isSpecialChar(page_config_name)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			int count = pageConfigService.findByExist(form);
			if (count == 0) {
				CODEMSG = SUCCESS;
			}else {
				map = null;
				CODEMSG = PAGE_CONFIG_NAME_EXIST;
			}
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, map);
	}
	
	/**
	 * 更新页面参数模板状态
	 * @Title: updateState
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月7日 下午3:12:37
	 * @param form
	 * @return
	 */
	@PutMapping(value = "/state/update")
	public Object updateState(@RequestBody PageConfig form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String page_config_code = form.getPage_config_code();
			int state = form.getState();
			if (StringUtils.isEmpty(page_config_code) || RegularUtil.isSpecialChar(page_config_code) || RegularUtil.matchLength(page_config_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if (!(state == 1 || state == 2)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int result = pageConfigService.update(form);
			if (result > 0) {
				CODEMSG = SUCCESS;
			} else {
				map = null;
				CODEMSG = ERROR;
			}
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, map);
	}
}
