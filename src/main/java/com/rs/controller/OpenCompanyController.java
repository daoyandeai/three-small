package com.rs.controller;

import com.rs.po.Accessory;
import com.rs.po.Company;
import com.rs.po.CompanyEmploy;
import com.rs.po.CompanyTag;
import com.rs.po.CompanyType;
import com.rs.po.Dictionary;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.po.returnPo.OpenCompanyTagReturn;
import com.rs.po.returnPo.OpenCompanyTypeReturn;
import com.rs.po.returnPo.OpenDictionaryReturn;
import com.rs.service.AccessoryService;
import com.rs.service.CompanyEmployService;
import com.rs.service.CompanyService;
import com.rs.service.CompanyTagService;
import com.rs.service.CompanyTypeService;
import com.rs.service.DictionaryService;
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
 * 
 * @ClassName: OpenCompanyController
 * @Description: 三小档案控制层
 * @Author tangsh
 * @DateTime 2020年4月1日 上午10:31:26
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/open/company")
public class OpenCompanyController extends BaseController {

	@Autowired
	private CompanyService companyService;
	@Autowired
	private AccessoryService accessoryService;
	@Autowired
	private CompanyEmployService companyEmployService;
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private CompanyTypeService companyTypeService;
	@Autowired
	private CompanyTagService companyTagService;
	@Autowired
	private TokenParam tokenParam;

	private Logger logger = LoggerFactory.getLogger(OpenCompanyController.class);
	
	/**
	 * 
	 * @Title: findCompanyType
	 * @Description: 查询主体类型
	 * @Author tangsh
	 * @DateTime 2020年4月1日 上午10:57:22
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/type/list")
	public Object findCompanyType(CompanyType form) {
		Map<String, Object> map = null;
		try {
			map = new HashMap<>();
			form.setState(1);
			List<CompanyType> ctlist=companyTypeService.findByAll(form);
			List<OpenCompanyTypeReturn> octlist=null;
			if(ctlist!=null&&ctlist.size()>0) {
				OpenCompanyTypeReturn oct=null;
				octlist=new ArrayList<OpenCompanyTypeReturn>();
				for(CompanyType ct:ctlist) {
					oct=new OpenCompanyTypeReturn();
					BeanUtils.copyProperties(ct, oct);
					octlist.add(oct);
				}
			}
			map.put("company_type_list", octlist);
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
	 * 
	 * @Title: findCompanyTag
	 * @Description: 查询指定类型下标签
	 * @Author tangsh
	 * @DateTime 2020年4月1日 上午11:07:59
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/tag/list")
	public Object findCompanyTag(CompanyTag form) {
		Map<String, Object> map = null;
		try {
			if(StringUtils.isEmpty(form.getCompanytype_code())||RegularUtil.isSpecialChar(form.getCompanytype_code())) {
				return finish(PARAMETER_ERROR, null);
			}
			map = new HashMap<>();
			form.setState(1);
			List<CompanyTag> ctlist=companyTagService.findByAll(form);
			List<OpenCompanyTagReturn> octlist=null;
			if(ctlist!=null&&ctlist.size()>0) {
				OpenCompanyTagReturn oct=null;
				octlist=new ArrayList<OpenCompanyTagReturn>();
				for(CompanyTag ct:ctlist) {
					oct=new OpenCompanyTagReturn();
					BeanUtils.copyProperties(ct, oct);
					octlist.add(oct);
				}
			}
			map.put("company_tag_list", octlist);
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
	 * 
	 * @Title: findDict
	 * @Description: 查询字典信息
	 * @Author tangsh
	 * @DateTime 2020年4月1日 下午2:48:23
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/dict/list")
	public Object findDict(Dictionary form) {
		Map<String ,Map<String,List<OpenDictionaryReturn>>> map = null;
		try {
			if(StringUtils.isEmpty(form.getCompanytag_code())||RegularUtil.isSpecialChar(form.getCompanytag_code())) {
				return finish(PARAMETER_ERROR, null);
			}
			map = dictionaryService.openSearch(form);
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
	 * 
	 * @Title: save
	 * @Description: 三小档案保存接口
	 * @Author tangsh
	 * @DateTime 2019年12月31日 下午3:39:05
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody Company form,HttpServletRequest request) {
		Map<String, String> map = null;
		try {
			if(StringUtils.isEmpty(form.getCompany_name())||RegularUtil.isSpecialChar(form.getCompany_name())){
				return finish(PARAMETER_ERROR, null);
			}
			if(StringUtils.isEmpty(form.getMobilephone())||!RegularUtil.isMobilePhone(form.getMobilephone())){
				return finish(PARAMETER_ERROR, null);
			}
			if(StringUtils.isEmpty(form.getProvince())||StringUtils.isEmpty(form.getCity())||StringUtils.isEmpty(form.getArea())||StringUtils.isEmpty(form.getTown())||StringUtils.isEmpty(form.getVill())) {
				return finish(PARAMETER_ERROR, null);
			}
			form.setCompany_code(CodeUtil.getSystemCode("company"));
			form.setFiling_state(3);
			User user = TokenUserUtil.generateUser(request, tokenParam);
			if(companyService.saveOpenCompany(form,user)>0){
				CODEMSG = SUCCESS;
				map=new HashMap<String, String>();
				map.put("company_code", form.getCompany_code());
			}else {
				CODEMSG = ERROR;
			}
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
	 * @Title: saveEmploy
	 * @Description: 根据企业备案编码新增从业人员
	 * @Author tangsh
	 * @DateTime 2020年4月1日 下午3:49:56
	 * @param form
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/employ/save")
	public Object saveEmploy(@RequestBody CompanyEmploy form,HttpServletRequest request) {
		Map<String, String> map = null;
		try {
			if(StringUtils.isEmpty(form.getCompany_code())) {
				return finish(PARAMETER_ERROR, null);
			}
			Company c=new Company();
			c.setCompany_code(form.getCompany_code());
			c=companyService.findByCode(c);
			if(c==null) {
				return finish(COMPANY_UNDEFINED, null);
			}
			if(StringUtils.isEmpty(form.getEmploy_name())||RegularUtil.isSpecialChar(form.getEmploy_name())){
				return finish(PARAMETER_ERROR, null);
			}
			if(StringUtils.isEmpty(form.getUser_health_datedue())){
				return finish(PARAMETER_ERROR, null);
			}
			form.setEmploy_code(CodeUtil.getSystemCode("companyEmploy"));
			form.setEmploy_ishealth(1);
			if(companyEmployService.save(form)>0){
				CODEMSG = SUCCESS;
				map=new HashMap<String, String>();
				map.put("employ_code", form.getEmploy_code());
			}else {
				CODEMSG = ERROR;
			}
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
	 * @Title: saveAccessory
	 * @Description: 根据企业备案编码新增附件
	 * @Author tangsh
	 * @DateTime 2020年4月1日 下午3:54:01
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/accessory/save")
	public Object saveAccessory(@RequestBody Accessory form) {
		Map<String, String> map = null;
		try {
			if(StringUtils.isEmpty(form.getCompany_code())) {
				return finish(PARAMETER_ERROR, null);
			}
			Company c=new Company();
			c.setCompany_code(form.getCompany_code());
			c=companyService.findByCode(c);
			if(c==null) {
				return finish(COMPANY_UNDEFINED, null);
			}
			if(StringUtils.isEmpty(form.getAccessory_type())||RegularUtil.isSpecialChar(form.getAccessory_type())){
				return finish(PARAMETER_ERROR, null);
			}
			if(StringUtils.isEmpty(form.getAccessory_url())){
				return finish(PARAMETER_ERROR, null);
			}
			form.setAccessory_code(CodeUtil.getSystemCode("accessory"));
			if(accessoryService.save(form)>0){
				CODEMSG = SUCCESS;
				map=new HashMap<String, String>();
				map.put("accessory_code", form.getAccessory_code());
			}else {
				CODEMSG = ERROR;
			}
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, map);
	}
	
}
