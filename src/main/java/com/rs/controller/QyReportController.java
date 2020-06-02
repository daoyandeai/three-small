package com.rs.controller;

import com.rs.po.Report;
import com.rs.po.ReportCheck;
import com.rs.po.ReportSubChef;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.service.ReportCheckService;
import com.rs.service.ReportService;
import com.rs.service.ReportSubChefService;
import com.rs.service.UserService;
import com.rs.util.CodeUtil;
import com.rs.util.DateUtil;
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
 * 
 * @ClassName: QyReportController
 * @Description: 群宴报备控制层
 * @Author tangsh
 * @DateTime 2020年4月13日 上午9:29:53
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/qy/report")
public class QyReportController extends BaseController {

	@Autowired
	private ReportService reportService;
	@Autowired
	private ReportCheckService reportCheckService;
	@Autowired
	private ReportSubChefService reportSubChefService;
	@Autowired
	private UserService userService;
	@Autowired
	private TokenParam tokenParam;

	private Logger logger = LoggerFactory.getLogger(QyReportController.class);

	/**
	 * 
	 * @Title: search
	 * @Description: 条件查询群宴报备（列表）
	 * @Author tangsh
	 * @DateTime 2020年4月13日 上午10:09:22
	 * @param form
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/search")
	public Object search(Report form,HttpServletRequest request) {
		Map<String,Object> map = new HashMap<>();
		CODEMSG = ERROR;
		try {
			User user = TokenUserUtil.generateUser(request, tokenParam);
			if(user==null) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null); 
			}
			if(("平台管理员").equals(user.getUser_type()) || ("协管员").equals(user.getUser_type())) {
				if(StringUtils.isEmpty(form.getProvince_conduct())) {
					form.setProvince_conduct(user.getUser_province());
				}
				if(StringUtils.isEmpty(form.getCity_conduct())) {
					form.setCity_conduct(user.getUser_city());	
				}
				if(StringUtils.isEmpty(form.getArea_conduct())) {
					form.setArea_conduct(user.getUser_area());
				}
				if(StringUtils.isEmpty(form.getTown_conduct())) {
					form.setTown_conduct(user.getUser_town());
				}
				if(StringUtils.isEmpty(form.getVill_conduct())) {
					form.setVill_conduct(user.getUser_vill());
				}
				form.setReport_full(2);
			}
			map.put("pager_count",reportService.findByCount(form));
			map.put("reportList",reportService.findByList(form));
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
	 * @Title: single
	 * @Description: 查询单个群宴报备
	 * @Author tangsh
	 * @DateTime 2020年4月13日 上午10:16:26
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/single")
	public Object single(Report form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		CODEMSG = ERROR;
		try {
			String report_code = form.getReport_code();
			if(StringUtils.isEmpty(report_code) || RegularUtil.matchLength(report_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			map.put("report", reportService.findByCode_detail(form));
			
			map.put("reportCheck", reportCheckService.findByReportCode(report_code));
			ReportSubChef rsc =new ReportSubChef();
			rsc.setReport_code(report_code);
			map.put("reportSubChefCount", reportSubChefService.findByCount(rsc));
			map.put("reportSubChefList", reportSubChefService.findByList(rsc));
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
	 * @Title: updateState
	 * @Description: 更新群宴报备检查数据
	 * @Author tangsh
	 * @DateTime 2020年4月13日 上午11:23:24
	 * @param form
	 * @param request
	 * @return
	 */
	@PutMapping(value = "/updateState")
	public Object updateState(@RequestBody Report form,HttpServletRequest request) {
		CODEMSG = ERROR;
		try {
			String report_code = form.getReport_code();
			if(StringUtils.isEmpty(report_code) || RegularUtil.matchLength(report_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			if(user==null) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null); 
			}
			if (form.getReport_state()==2||form.getReport_state()==3) {
				form.setUser_code_qualified(user.getUser_code());
				form.setUser_name_qualified(user.getUser_name());
				form.setQualified_time(DateUtil.getCurrentTime());
			}else if (form.getReport_state()==4||form.getReport_state()==5) {
				form.setUser_code_check(user.getUser_code());
				form.setUser_name_check(user.getUser_name());
				form.setCheck_time(DateUtil.getCurrentTime());
			}
			if(form.getReport_state()==3) {
				 Report report =reportService.findByCode(form);
				 User chef = new User();
				 chef.setUser_code(report.getUser_code_mainchef());
				 chef = userService.findByCode(chef);
				 chef.setBanquet_count(chef.getBanquet_count()+1);
				 userService.personUpdate(chef);
			}
			if (form.getReport_state()>=4) {
				ReportCheck reportCheck =new ReportCheck();
				reportCheck.setReport_code(report_code);
				Integer result=reportCheckService.findByCount(reportCheck);
				//BeanUtils.copyProperties(form, reportCheck);
				reportCheck=form.getReport_check();
				reportCheck.setReport_code(report_code);
				if (result>0){
					reportCheckService.update(reportCheck);
				}else {
					reportCheck.setUser_code(user.getUser_code());
					reportCheck.setUser_name(user.getUser_name());
					reportCheck.setUser_mobilephone(user.getUser_mobilephone());
					reportCheck.setCheck_code(CodeUtil.getSystemCode("reportCheck"));
					reportCheckService.save(reportCheck);
				}
			}
			if(reportService.update(form)>0){
				CODEMSG = SUCCESS;
			}
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, null);
	}
}
