package com.rs.controller;
import com.rs.po.PageAttr;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.po.returnPo.PageAttrReturn;
import com.rs.service.PageAttrService;
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
 * 页面属性控制层
 * @ClassName: PageAttrController
 * @Description: 
 * @Author sven
 * @DateTime 2020年2月11日 上午10:17:46
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/pageAttr")
public class PageAttrController extends BaseController {

	@Autowired
	private PageAttrService pageAttrService;
	@Autowired
	private TokenParam tokenParam;
	
	private Logger logger = LoggerFactory.getLogger(PageAttrController.class);


	/**
	 * 查询页面属性_分页
	 * @Title: list
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:34:44
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(PageAttr form) {
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("page_attr_list",pageAttrService.findByList_app(form));
			map.put("pager_count",pageAttrService.findByCount(form));
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
	 * 新增页面属性
	 * @Title: save
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:35:40
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody PageAttr form ,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String attr_en = form.getAttr_en();
			if(!StringUtils.isEmpty(attr_en) && RegularUtil.isSpecialChar(attr_en)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			String attr_ch = form.getAttr_ch();
			if(!StringUtils.isEmpty(attr_ch) && RegularUtil.isSpecialChar(attr_ch)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			Integer page_module = form.getPage_module();
			if(!StringUtils.isEmpty(page_module) && !RegularUtil.OneToAnyNumber(page_module+"")) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_code_add(user.getUser_code());
			form.setUser_name_add(user.getUser_name());
			form.setPage_attr_code(CodeUtil.getSystemCode("pageAttr"));
			int count = pageAttrService.findByExist(form);
			if (count > 0) {
				CODEMSG = PAGE_ATTR_EN_EXIST;
				return finish(CODEMSG, null);
			}
			int result = pageAttrService.save(form);
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
	 * 查询单条页面属性
	 * @Title: single
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:35:51
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/single")
	public Object single(PageAttr form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String page_attr_code = form.getPage_attr_code();
			if (StringUtils.isEmpty(page_attr_code) || RegularUtil.matchLength(page_attr_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			PageAttrReturn ptr = pageAttrService.findByCode_app(form);
			if (!StringUtils.isEmpty(ptr)) {
				map.put("page_attr", ptr);
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
	 * 编辑页面属性
	 * @Title: update
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:36:07
	 * @param form
	 * @return
	 */
	@PutMapping(value = "/update")
	public Object update(@RequestBody PageAttr form, HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String page_attr_code = form.getPage_attr_code();
			if (StringUtils.isEmpty(page_attr_code) || RegularUtil.matchLength(page_attr_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String attr_en = form.getAttr_en();
			if(!StringUtils.isEmpty(attr_en) && RegularUtil.isSpecialChar(attr_en)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			String attr_ch = form.getAttr_ch();
			if(!StringUtils.isEmpty(attr_ch) && RegularUtil.isSpecialChar(attr_ch)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			Integer page_module = form.getPage_module();
			if(!StringUtils.isEmpty(page_module) && !RegularUtil.OneToAnyNumber(page_module+"")) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			int count = pageAttrService.findByExist(form);
			if (count > 0) {
				CODEMSG = PAGE_ATTR_EN_EXIST;
				return finish(CODEMSG, null);
			}
			form.setUser_code_update(user.getUser_code());
			form.setUser_name_update(user.getUser_name());
			int result = pageAttrService.update(form);
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
	 * 查询页面属性是否存在
	 * @Title: exist
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月6日 上午10:23:17
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/exist")
	public Object exist(PageAttr form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			Integer page_module = form.getPage_module();
			if(!StringUtils.isEmpty(page_module) && !RegularUtil.OneToAnyNumber(page_module+"")) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String attr_en = form.getAttr_en();
			if(!StringUtils.isEmpty(attr_en) && RegularUtil.isSpecialChar(attr_en)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			int count = pageAttrService.findByExist(form);
			if (count == 0) {
				CODEMSG = SUCCESS;
			}else {
				map = null;
				CODEMSG = PAGE_ATTR_EN_EXIST;
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
	 * 更新页面属性状态
	 * @Title: updateState
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月7日 下午3:12:37
	 * @param form
	 * @return
	 */
	@PutMapping(value = "/state/update")
	public Object updateState(@RequestBody PageAttr form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String page_attr_code = form.getPage_attr_code();
			int state = form.getState();
			if (StringUtils.isEmpty(page_attr_code) || RegularUtil.isSpecialChar(page_attr_code) || RegularUtil.matchLength(page_attr_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if (!(state == 1 || state == 2)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int result = pageAttrService.update(form);
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
