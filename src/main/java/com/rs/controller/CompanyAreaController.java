package com.rs.controller;
import com.rs.po.CompanyArea;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.po.returnPo.CompanyAreaReturn;
import com.rs.service.CompanyAreaService;
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
 * 企业区域控制层
 * @ClassName: CompanyAreaController
 * @Description: 
 * @Author sven
 * @DateTime 2020年3月16日 上午11:12:54
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/companyArea")
public class CompanyAreaController extends BaseController {

	@Autowired
	private CompanyAreaService companyAreaService;
	@Autowired
	private TokenParam tokenParam;
	
	private Logger logger = LoggerFactory.getLogger(CompanyAreaController.class);

	/**
	 * 查询企业区域列表
	 * @Title: list
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月16日 上午11:18:28
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(CompanyArea form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String company_area_name = form.getCompany_area_name();
			if(!StringUtils.isEmpty(company_area_name) && RegularUtil.isSpecialChar(company_area_name)) {
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
			Integer state = form.getState();
			if(!StringUtils.isEmpty(state) && !RegularUtil.isNumber(state+"")) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			map.put("pager_count", companyAreaService.findByCount(form));
			map.put("company_area_list",companyAreaService.findByList_app(form));
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
	 * 添加企业区域
	 * @Title: save
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月16日 上午11:18:46
	 * @param form
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody CompanyArea form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String company_area_name = form.getCompany_area_name();
			if(!StringUtils.isEmpty(company_area_name) && RegularUtil.isSpecialChar(company_area_name)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			String company_code = form.getCompany_code();
			if(StringUtils.isEmpty(company_code) || RegularUtil.matchLength(company_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String company_name = form.getCompany_name();
			if(StringUtils.isEmpty(company_name) || !RegularUtil.isLength(company_name, 1, 50)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int count = companyAreaService.findByExist(form);
			if (count > 0) {
				CODEMSG = COMPANY_AREA_NAME_EXIST;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_code_add(user.getUser_code());
			form.setUser_name_add(user.getUser_name());
			form.setCompany_area_code(CodeUtil.getSystemCode("companyArea"));
			int result = companyAreaService.save(form);
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
	 * 查询企业区域
	 * @Title: single
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月16日 上午11:20:17
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/single")
	public Object single(CompanyArea form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String company_area_code = form.getCompany_area_code();
			if(StringUtils.isEmpty(company_area_code) || RegularUtil.matchLength(company_area_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			CompanyAreaReturn c = companyAreaService.findByCode_app(form);
			if(!StringUtils.isEmpty(c)) {
				map.put("company_area", c);
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
	 * 编辑企业区域
	 * @Title: update
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月16日 上午11:22:07
	 * @param form
	 * @return
	 */
	@PutMapping(value = "/update")
	public Object update(@RequestBody CompanyArea form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String company_area_code = form.getCompany_area_code();
			if(StringUtils.isEmpty(company_area_code) || RegularUtil.matchLength(company_area_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String company_area_name = form.getCompany_area_name();
			if(!StringUtils.isEmpty(company_area_name) && RegularUtil.isSpecialChar(company_area_name)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			String company_code = form.getCompany_code();
			if(StringUtils.isEmpty(company_code) || RegularUtil.matchLength(company_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int count = companyAreaService.findByExist(form);
			if (count > 0) {
				CODEMSG = COMPANY_AREA_NAME_EXIST;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_code_update(user.getUser_code());
			form.setUser_name_update(user.getUser_name());
			int result = companyAreaService.update(form);
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
	 * 更新企业区域状态
	 * @Title: updateState
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月16日 上午11:23:05
	 * @param form
	 * @return
	 */
	@PutMapping(value="/state/update")
	public Object updateState(@RequestBody CompanyArea form,HttpServletRequest request){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String company_area_code = form.getCompany_area_code();
			int state = form.getState();
			if(StringUtils.isEmpty(company_area_code) || RegularUtil.isSpecialChar(company_area_code) || RegularUtil.matchLength(company_area_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(!(state == 1 || state == 2)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_code_update(user.getUser_code());
			form.setUser_name_update(user.getUser_name());
			int result = companyAreaService.update(form);
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
	 * 查询企业区域是否存在
	 * @Title: exist
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月16日 上午11:24:33
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/exist")
	public Object exist(CompanyArea form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String company_area_name = form.getCompany_area_name();
			if (StringUtils.isEmpty(company_area_name)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if (RegularUtil.isSpecialChar(company_area_name)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			int count = companyAreaService.findByExist(form);
			if (count == 0) {
				CODEMSG = SUCCESS;
			}else {
				map = null;
				CODEMSG = COMPANY_AREA_NAME_EXIST;
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
