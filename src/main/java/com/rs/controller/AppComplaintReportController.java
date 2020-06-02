package com.rs.controller;

import com.rs.po.ComplaintReport;
import com.rs.service.ComplaintReportService;
import com.rs.util.CodeUtil;
import com.rs.util.RegularUtil;

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
 * @ClassName: ComplaintReportController
 * @Description: 投诉举报控制层
 * @Author tangsh
 * @DateTime 2020年3月9日 上午10:55:21
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/app/complaintReport")
public class AppComplaintReportController extends BaseController {

	@Autowired
	private ComplaintReportService complaintReportService;

	private Logger logger = LoggerFactory.getLogger(AppComplaintReportController.class);

	/**
	 * 
	 * @Title: save
	 * @Description: 保存接口
	 * @Author tangsh
	 * @DateTime 2020年3月9日 上午11:03:54
	 * @param form
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody ComplaintReport form,HttpServletRequest request) {
		CODEMSG = ERROR;
		Map<String, String> map = new HashMap<>();
		try {
			if(!RegularUtil.isMobilePhone(form.getComplaintreport_telephone())){
				return finish(PARAMETER_ERROR, null);
			}
			if(StringUtils.isEmpty(form.getComplaintreport_title())){
				return finish(PARAMETER_ERROR, null);
			}
//			User user = TokenUserUtil.generateUser(request, tokenParam);
			String complaintreport_code = CodeUtil.getSystemCode("complaintReport");
			form.setComplaintreport_code(complaintreport_code);
			if(complaintReportService.save(form)>0){
				CODEMSG = SUCCESS;
			}
			map.put("other_code", complaintreport_code);
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
	 * @Title: list
	 * @Description: 根据条件查询（列表）
	 * @Author tangsh
	 * @DateTime 2020年3月9日 上午10:58:08
	 * @param form
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(ComplaintReport form,HttpServletRequest request) {
		Map<String,Object> map = new HashMap<>();
		try {
//			User user = TokenUserUtil.generateUser(request, tokenParam);
			map.put("pager_count",complaintReportService.findByCount(form));
			map.put("pager_list",complaintReportService.findByList(form));
			//未处理
			ComplaintReport cr=new ComplaintReport();
			cr.setComplaintreport_state(1);
			map.put("not_result",complaintReportService.findByCount(form));
			
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
	 * @Description: 详情接口
	 * @Author tangsh
	 * @DateTime 2020年3月9日 上午11:03:37
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/single")
	public Object single(ComplaintReport form) {
		try {
			form = complaintReportService.findByCode(form);
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG,form);
	}
}
