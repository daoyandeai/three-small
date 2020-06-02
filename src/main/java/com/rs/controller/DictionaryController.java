package com.rs.controller;

import com.rs.po.Dictionary;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.po.returnPo.DictionaryReturn;
import com.rs.service.DictionaryService;
import com.rs.util.CodeUtil;
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
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @ClassName: DictionaryController
 * @Description: 业务字典控制层
 * @Author tangsh
 * @DateTime 2020年1月7日 上午10:50:17
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/dictionary")
public class DictionaryController extends BaseController {

	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private TokenParam tokenParam;
	
	private Logger logger = LoggerFactory.getLogger(DictionaryController.class);

	/**
	 * 
	 * @Title: search
	 * @Description: 查询列表信息
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:27:43
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/search")
	public Object search(Dictionary form) {
		CODEMSG = ERROR;
		Map<String ,Map<String,List<Dictionary>>> map = new HashMap<>();
		try {
			map = dictionaryService.search(form);
			CODEMSG=SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, map);
	}
	
	/**
	 * 查询餐厨垃圾模板_分页
	 * @Title: list
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:34:44
	 * @param form
	 * @return
	 */
	@GetMapping(value = "cclj/list")
	public Object ccljList(Dictionary form) {
		Map<String,Object> map = new HashMap<>();
		try {
			if(!StringUtils.isEmpty(form.getCompanytype_codes())) {
				form.setCompanytype_codes(form.getCompanytype_codes().replace(",", "|"));
			}
			if(!StringUtils.isEmpty(form.getAdd_time())) {
				form.setAdd_time(form.getAdd_time()+" 00:00:00");
			}
			if(!StringUtils.isEmpty(form.getEnd_time())) {
				form.setEnd_time(form.getEnd_time()+" 23:59:59");
			}
			map.put("cclj_list",dictionaryService.findByList_app(form));
			map.put("pager_count",dictionaryService.findByCount(form));
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
	 * 新增餐厨垃圾模板
	 * @Title: save
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:35:40
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/cclj/save")
	public Object saveCCLJ(@RequestBody Dictionary form ,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String dictionary_module = form.getDictionary_module();
			if(StringUtils.isEmpty(dictionary_module) || RegularUtil.isSpecialChar(dictionary_module)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String companytype_codes = form.getCompanytype_codes();
			if(StringUtils.isEmpty(companytype_codes) || RegularUtil.isSpecialChar(companytype_codes)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			form.setDictionary_code(CodeUtil.getSystemCode("dictionary"));
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_code_add(user.getUser_code());
			form.setUser_name_add(user.getUser_name());
			int result = dictionaryService.save(form);
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
	 * 查询单条餐厨垃圾模板
	 * @Title: single
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:35:51
	 * @param form
	 * @return
	 */
	@GetMapping(value = "cclj/single")
	public Object ccljSingle(Dictionary form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String dictionary_code = form.getDictionary_code();
			if (StringUtils.isEmpty(dictionary_code) || RegularUtil.matchLength(dictionary_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			DictionaryReturn dr = dictionaryService.findByCode_app(form);
			if (!StringUtils.isEmpty(dr)) {
				map.put("cclj", dr);
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
	 * 编辑餐厨垃圾模板
	 * @Title: update
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:36:07
	 * @param form
	 * @return
	 */
	@PutMapping(value = "/cclj/update")
	public Object updateCCLJ(@RequestBody Dictionary form, HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String dictionary_code = form.getDictionary_code();
			if (StringUtils.isEmpty(dictionary_code) || RegularUtil.matchLength(dictionary_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String dictionary_module = form.getDictionary_module();
			if(StringUtils.isEmpty(dictionary_module) || RegularUtil.isSpecialChar(dictionary_module)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String companytype_codes = form.getCompanytype_codes();
			if(StringUtils.isEmpty(companytype_codes) || RegularUtil.isSpecialChar(companytype_codes)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_code_update(user.getUser_code());
			form.setUser_name_update(user.getUser_name());
			int result = dictionaryService.update(form);
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
	 * 删除餐厨垃圾
	 * @Title: deleteCCLZ
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月9日 上午11:19:58
	 * @param form
	 * @return
	 */
	@DeleteMapping(value = "/cclj/delete")
	public Object deleteCCLJ(Dictionary form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String dictionary_code = form.getDictionary_code();
			if (StringUtils.isEmpty(dictionary_code) || RegularUtil.matchLength(dictionary_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int result = dictionaryService.delete(form);
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
