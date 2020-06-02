package com.rs.controller;
import com.rs.po.CompanyType;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.po.returnPo.CompanyTypeReturn;
import com.rs.service.CompanyTypeService;
import com.rs.util.CodeUtil;
import com.rs.util.RegularUtil;
import com.rs.util.TokenUserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
/**
 * 食品监管分类控制层
 * @ClassName: CompanyTypeController
 * @Description: 
 * @Author sven
 * @DateTime 2020年2月5日 下午4:24:50
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/companytype")
public class CompanyTypeController extends BaseController {

	@Autowired
	private CompanyTypeService companyTypeService;
	@Autowired
	private TokenParam tokenParam;
	
	private Logger logger = LoggerFactory.getLogger(CompanyTypeController.class);

	/**
	 * 查询食品监管分类_不分页
	 * @Title: all
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:34:27
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/all")
	public Object all(CompanyType form) {
		List<CompanyTypeReturn> ctrList = new ArrayList<CompanyTypeReturn>();
		try {
			form.setState(1);
			List<CompanyType> ctList = companyTypeService.findByAll(form);
			for (CompanyType ct : ctList) {
				CompanyTypeReturn ctr = new CompanyTypeReturn();
				BeanUtils.copyProperties(ct,ctr);
				ctrList.add(ctr);
			}
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, ctrList);
	}

	/**
	 * 查询食品监管分类_分页
	 * @Title: list
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:34:44
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(CompanyType form) {
		Map<String,Object> map = new HashMap<>();
		try {
			List<CompanyType> ctList = companyTypeService.findByList(form);
			List<CompanyTypeReturn> ctrList = new ArrayList<>();
			for (CompanyType ct : ctList) {
				CompanyTypeReturn ctr = new CompanyTypeReturn();
				BeanUtils.copyProperties(ct,ctr);
				ctrList.add(ctr);
			}
			map.put("company_type_list",ctrList);
			map.put("pager_count",companyTypeService.findByCount(form));
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
	 * 新增食品监管分类
	 * @Title: save
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:35:40
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody CompanyType form ,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String companytype_name = form.getCompanytype_name();
			if(!StringUtils.isEmpty(companytype_name) && RegularUtil.isSpecialChar(companytype_name)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_code_add(user.getUser_code());
			form.setUser_name_add(user.getUser_name());
			form.setCompanytype_code(CodeUtil.getSystemCode("companyType"));
			int count = companyTypeService.findByExist(form);
			if (count > 0) {
				CODEMSG = COMPANY_TYPE_EXIST;
				return finish(CODEMSG, null);
			}
			int result = companyTypeService.save(form);
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
	 * 查询单条食品监管分类
	 * @Title: single
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:35:51
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/single")
	public Object single(CompanyType form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String companytype_code = form.getCompanytype_code();
			if (StringUtils.isEmpty(companytype_code) || RegularUtil.matchLength(companytype_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			form = companyTypeService.findByCode(form);
			if (!StringUtils.isEmpty(form)) {
				CompanyTypeReturn ctr = new CompanyTypeReturn();
				BeanUtils.copyProperties(form, ctr);
				map.put("company_type", ctr);
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
	 * 编辑食品监管分类
	 * @Title: update
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:36:07
	 * @param form
	 * @return
	 */
	@PutMapping(value = "/update")
	public Object update(@RequestBody CompanyType form, HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String companytype_code = form.getCompanytype_code();
			if (StringUtils.isEmpty(companytype_code) || RegularUtil.isSpecialChar(companytype_code)
					|| RegularUtil.matchLength(companytype_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String companytype_name = form.getCompanytype_name();
			if (!StringUtils.isEmpty(companytype_name) && RegularUtil.isSpecialChar(companytype_name)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			int count = companyTypeService.findByExist(form);
			if (count > 0) {
				CODEMSG = COMPANY_TYPE_EXIST;
				return finish(CODEMSG, null);
			}
			form.setUser_code_update(user.getUser_code());
			form.setUser_name_update(user.getUser_name());
			int result = companyTypeService.update(form);
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
	 * 查询食品监管分类是否存在
	 * @Title: exist
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月6日 上午10:23:17
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/exist")
	public Object exist(CompanyType form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String companytype_name = form.getCompanytype_name();
			if(!StringUtils.isEmpty(companytype_name) && RegularUtil.isSpecialChar(companytype_name)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			int count = companyTypeService.findByExist(form);
			if (count == 0) {
				CODEMSG = SUCCESS;
			}else {
				map = null;
				CODEMSG = COMPANY_TYPE_EXIST;
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
	 * 更新食品监管分类状态
	 * @Title: updateState
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月7日 下午3:12:37
	 * @param form
	 * @return
	 */
	@PutMapping(value = "/state/update")
	public Object updateState(@RequestBody CompanyType form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String companytype_code = form.getCompanytype_code();
			int state = form.getState();
			if (StringUtils.isEmpty(companytype_code) || RegularUtil.isSpecialChar(companytype_code) || RegularUtil.matchLength(companytype_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if (!(state == 1 || state == 2)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int result = companyTypeService.update(form);
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
