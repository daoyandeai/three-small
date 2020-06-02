package com.rs.controller;
import com.rs.po.Company;
import com.rs.po.ComplaintReport;
import com.rs.po.MessLog;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.service.ComplaintReportService;
import com.rs.service.MessLogService;
import com.rs.service.UserService;
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
 * @ClassName: MessLogController
 * @Description: 保存消息日志控制层
 * @Author tangsh
 * @DateTime 2020年3月4日 上午10:33:41
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/messLog")
public class MessLogController extends BaseController {

	@Autowired
	private MessLogService messLogService;
	@Autowired
	private UserService userService;
	@Autowired
	private ComplaintReportService complaintReportService;
	@Autowired
	private TokenParam tokenParam;
	
	private Logger logger = LoggerFactory.getLogger(MessLogController.class);
	
	/**
	 * 查询列表类消息模板_分页
	 * @Title: list
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:34:44
	 * @param form
	 * @return
	 */
	@GetMapping(value = "list")
	public Object noticeList(MessLog form,HttpServletRequest request) {
		Map<String,Object> map = new HashMap<>();
		try {
			
			User user = TokenUserUtil.generateUser(request, tokenParam);
			//超级管理员查询所有,其余管理员查询自己
			if(!("超级管理人员").equals(user.getUser_type())) {
				form.setUser_code(user.getUser_code());
			}
			if(!StringUtils.isEmpty(form.getMess_receive_person())) {
				form.setMess_receive_person(form.getMess_receive_person().replace(",", "|"));
			}
			if(!StringUtils.isEmpty(form.getCompanytype_codes())) {
				form.setCompanytype_codes(form.getCompanytype_codes().replace(",", "|"));
			}
			if(!StringUtils.isEmpty(form.getCompanytag_codes())) {
				form.setCompanytag_codes(form.getCompanytag_codes().replace(",", "|"));
			}
			if(!StringUtils.isEmpty(form.getAdd_time())) {
				form.setAdd_time(form.getAdd_time()+" 00:00:00");
			}
			if(!StringUtils.isEmpty(form.getEnd_time())) {
				form.setEnd_time(form.getEnd_time()+" 23:59:59");
			}
			map.put("mess_log_list",messLogService.findByList_app(form));
			map.put("pager_count",messLogService.findByCount(form));
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
	 * 
	 * @Title: save
	 * @Description: 新增接口
	 * @Author tangsh
	 * @DateTime 2020年3月4日 上午10:34:18
	 * @param form
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody Company form ,HttpServletRequest request) {
		try {
			Integer mess_event = form.getMess_event();
			if (StringUtils.isEmpty(mess_event) ) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			if(user==null) {
				user=new User();
				user.setUser_code(form.getUser_code());
				user=userService.findByCode(user);
			}
			if (user==null) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			System.out.println("==================================================");
			System.out.println("mess_event====================================="+mess_event);
			System.out.println("Company_code====================================="+form.getCompany_code());
			System.out.println("other_code====================================="+form.getOther_code());
			System.out.println("==================================================");
			if(mess_event==6||mess_event==7) {
				ComplaintReport cr=new ComplaintReport();
				cr.setComplaintreport_code(form.getOther_code());
				cr=complaintReportService.findByCode(cr);
				if(cr!=null) {
					messLogService.saveMessLogByUserCode(mess_event, cr.getComplaintreport_openid(), cr.getResult_user_code(), cr.getComplaintreport_telephone(), cr.getComplaintreport_code(), user,null);
				}
			}else {
				messLogService.saveMessLogByOtherCode(mess_event, form.getCompany_code(),form.getOther_code(), user);
			}
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, form);
	}

}
