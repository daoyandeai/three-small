package com.rs.controller;
import com.rs.po.Company;
import com.rs.po.CompanyIntegrity;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.service.CompanyIntegrityService;
import com.rs.service.CompanyService;
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
 * 企业诚信评价结果控制层
 * @ClassName: CompanyIntegrityController
 * @Description: 
 * @Author sven
 * @DateTime 2020年2月12日 下午4:48:47
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/companyIntegrity")
public class CompanyIntegrityController extends BaseController {

	@Autowired
	private CompanyIntegrityService companyIntegrityService;
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private TokenParam tokenParam;
	
	private Logger logger = LoggerFactory.getLogger(CompanyIntegrityController.class);


	/**
	 * 查询企业诚信评价日志_分页
	 * @Title: list
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:34:44
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(CompanyIntegrity form) {
		Map<String,Object> map = new HashMap<>();
		try {
			String company_code = form.getCompany_code();
			if (StringUtils.isEmpty(company_code) || RegularUtil.matchLength(company_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			map.put("company_integrity_list",companyIntegrityService.findByList_app(form));
			map.put("pager_count",companyIntegrityService.findByCount(form));
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
	 * 新增企业诚信评价日志
	 * @Title: save
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:35:40
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody CompanyIntegrity form ,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			float integrity_lvl_new = form.getIntegrity_lvl_new();
			if(StringUtils.isEmpty(integrity_lvl_new)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String company_code = form.getCompany_code();
			if (StringUtils.isEmpty(company_code) || RegularUtil.matchLength(company_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String remark = form.getRemark();
			if (StringUtils.isEmpty(remark)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_code_add(user.getUser_code());
			form.setUser_name_add(user.getUser_name());
			form.setMete_type(2);
			//查询该企业最新的一次评价
			CompanyIntegrity companyIntegrity = companyIntegrityService.findLast(form);
			float integrity_lvl_last = 0;
			if(!StringUtils.isEmpty(companyIntegrity)) {
				integrity_lvl_last = companyIntegrity.getIntegrity_lvl_new();
				form.setMete_time_last(companyIntegrity.getAdd_time());
			}
			
			form.setIntegrity_lvl_last(integrity_lvl_last);
			//判断变化趋势
			int integrity_lvl_change_trend = 1;
			if(integrity_lvl_new == integrity_lvl_last) {
				integrity_lvl_change_trend = 1;
			}else if(integrity_lvl_new > integrity_lvl_last) {
				integrity_lvl_change_trend = 2;
			}else{
				integrity_lvl_change_trend = 3;
			}
			form.setIntegrity_lvl_change_trend(integrity_lvl_change_trend);
			
			form.setRemark(user.getUser_name()+"进行了手动更新,原因："+form.getRemark());
			form.setCompany_integrity_code(CodeUtil.getSystemCode("companyIntegrity"));
			int result = companyIntegrityService.save(form);
			if (result > 0) {
				//更新企业最新诚信结果
				Company company = new Company();
				company.setCompany_code(company_code);
				company.setIntegrity_lvl_last(form.getIntegrity_lvl_last());
				company.setMete_time_last(form.getMete_time_last());
				company.setIntegrity_lvl_new(form.getIntegrity_lvl_new());
				//查询最新一条保存的时间
				form = companyIntegrityService.findByCode(form);
				company.setMete_time_new(form.getAdd_time());
				company.setIntegrity_lvl_change_trend(integrity_lvl_change_trend);
				companyService.update(company);
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
