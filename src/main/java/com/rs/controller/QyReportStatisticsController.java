package com.rs.controller;

import com.rs.po.Report;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.service.ReportService;
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
@RequestMapping("/api/qy/reportStatistics")
public class QyReportStatisticsController extends BaseController {

	@Autowired
	private ReportService reportService;
	@Autowired
	private TokenParam tokenParam;

	private Logger logger = LoggerFactory.getLogger(QyReportStatisticsController.class);

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
}
