package com.rs.controller;

import com.rs.po.ComplaintReport;
import com.rs.po.User;
import com.rs.service.ComplaintReportService;
import com.rs.service.MessLogService;
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
@RequestMapping("/api/complaintReport")
public class ComplaintReportController extends BaseController {

	@Autowired
	private ComplaintReportService complaintReportService;
	@Autowired
	private MessLogService messLogService;
//	@Autowired
//	private TokenParam tokenParam;

	private Logger logger = LoggerFactory.getLogger(ComplaintReportController.class);

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
			map.put("complaintReportList",complaintReportService.findByList(form));
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
		try {
			if(!RegularUtil.isMobilePhone(form.getComplaintreport_telephone())){
				return finish(PARAMETER_ERROR, null);
			}
			if(StringUtils.isEmpty(form.getComplaintreport_title())){
				return finish(PARAMETER_ERROR, null);
			}
//			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setComplaintreport_code(CodeUtil.getSystemCode("complaintReport"));
			if(complaintReportService.save(form)>0){
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

	/**
	 * 
	 * @Title: update
	 * @Description: 更新接口
	 * @Author tangsh
	 * @DateTime 2020年3月9日 上午11:05:48
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/update")
	public Object update(@RequestBody ComplaintReport form) {
		CODEMSG = ERROR;
		try {
			if(StringUtils.isEmpty(form.getComplaintreport_code())){
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG,null);
			}
			if(complaintReportService.update(form)>0){
				CODEMSG = SUCCESS;
				if(!StringUtils.isEmpty(form.getComplaintreport_state())&&form.getComplaintreport_state()==2) {
					User user=new User();
					user.setUser_code(form.getResult_user_code());
					user.setUser_name(form.getResult_user_name());
					form=complaintReportService.findByCode(form);
					messLogService.saveMessLogByUserCode(7, form.getComplaintreport_openid(), form.getResult_user_code(), form.getComplaintreport_telephone(), form.getComplaintreport_code(), user,null);
//					messLogService.saveMessLogByOtherCode(7, form.getCompany_code(),form.getComplaintreport_code(), user);
				}
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
