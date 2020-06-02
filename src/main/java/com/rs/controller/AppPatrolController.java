package com.rs.controller;

import com.rs.po.*;
import com.rs.service.CompanyService;
import com.rs.service.LogService;
import com.rs.service.PatrolDisposeService;
import com.rs.service.PatrolPunishService;
import com.rs.service.PatrolService;
import com.rs.util.RegularUtil;
import com.rs.util.TokenUserUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @ClassName: PatrolController
 * @Description:巡查报备控制层
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/app/patrol")
public class AppPatrolController extends BaseController {

	@Autowired
	private PatrolService patrolService;
	@Autowired
	private PatrolDisposeService patrolDisposeService;
	@Autowired
	private PatrolPunishService patrolPunishService;
	@Autowired
	private TokenParam tokenParam;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private LogService logService;
	
	private Logger logger = LoggerFactory.getLogger(AppPatrolController.class);

	/**
	 * 
	 * @Title: detail
	 * @Description: 查询巡查详情
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:29:30
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/detail")
	public Object detail(Patrol form) {
		CODEMSG = ERROR;
		try {
			form = patrolService.findByPatrolCompany(form);
			if(StringUtils.isEmpty(form)) {
				CODEMSG = EXCEPTION;
				return finish(CODEMSG, null);
			}
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, form);
	}

	/**
	 * 
	 * @Title: dispose
	 * @Description: 查询处理方式
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:29:39
	 * @return
	 */
	@GetMapping(value = "/dispose")
	public Object dispose(PatrolDispose form) {
		CODEMSG = ERROR;
		List<PatrolDispose> list = null;
		try {
			form.setState(1);
			list = patrolDisposeService.findByAll(form);
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, list);
		}
		return finish(CODEMSG, list);
	}

	/**
	 * 
	 * @Title: punish
	 * @Description: 查询处罚方式
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:29:44
	 * @return
	 */
	@GetMapping(value = "/punish")
	public Object punish(PatrolPunish form) {
		CODEMSG = ERROR;
		List<PatrolPunish> list = null;
		try {
			form.setState(1);
			list = patrolPunishService.findByAll(form);
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, list);
	}

	/**
	 * 
	 * @Title: save
	 * @Description: 保存巡查
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:29:50
	 * @param form
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody Patrol form,HttpServletRequest request) {
		CODEMSG = PARAMETER_ERROR;
		try {
			//检验字段是否正确
			if(StringUtils.isEmpty(form.getCompany_code())) {
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_code(user.getUser_code());
			form.setUser_name(user.getUser_name());
			int i = patrolService.save(form);
			if(i!=0){
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
	 * @Description: 
	 * @Author tangsh
	 * @DateTime 2020年1月14日 下午4:00:05
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/update")
	public Object update(@RequestBody Patrol form) {
		Company company = new Company();
		try {
			if(StringUtils.isEmpty(form.getPatrol_code())){
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG,null);
			}
			//将上传的图片list转存为字符串格式用“,”分开
			if(form.getPatrol_imgs_list()!=null&&form.getPatrol_imgs_list().size()>0){
				form.setPatrol_imgs(RegularUtil.getListToString(form.getPatrol_imgs_list(),","));
			}
			//将处罚方式list转存为字符串格式用“,”分开
			if(form.getPunish_name_list()!=null&&form.getPunish_name_list().size()>0){
				form.setPunish_name(RegularUtil.getListToString(form.getPunish_name_list(),","));
			}
			
			Integer result=patrolService.update(form);
			
			if(form.getIsblacklist()!=null) {
				company.setCompany_code(form.getCompany_code());
				company.setIsblacklist(form.getIsblacklist());
				companyService.update(company);
			}
			
			String log_result = "";
			String log_remark = form.getIsblacklist_remark();
			String log_user_code = form.getPatrol_user_code();
			String log_user_name = form.getPatrol_user_name();
			String company_code = form.getCompany_code();
			if (form.getIsblacklist()!=null && form.getIsblacklist() == 1) {
				log_result = "加入白名单";
			} else {
				log_result = "加入黑名单";
			}
			logService.saveObject("更改", log_result, log_remark, log_user_code, log_user_name, company_code);
			
			if(result>0){
				CODEMSG = SUCCESS;
			}else {
				CODEMSG = ERROR;
			}
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, form);
	}

	/**
	 * 
	 * @Title: search
	 * @Description: 查询工单巡查列表
	 * @Author tangsh
	 * @DateTime 2020年1月14日 下午2:41:58
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/search")
	public Object search(Patrol form,HttpServletRequest request) {
		Patrol patrol = null;
		CODEMSG = ERROR;
		try {
			User user = TokenUserUtil.generateUser(request, tokenParam);
			patrol=patrolService.search(form, user);
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, patrol);
	}
	
	
	@GetMapping(value = "/list")
	public Object search(
			@RequestParam(value="user_code") String user_code,
			@RequestParam(value="company_name") String company_name,
			@RequestParam(value="patrol_state") Integer patrol_state,
			@RequestParam(value="pager_openset") Integer pager_openset,
			@RequestParam(value="pager_offset") Integer pager_offset) {
		Patrol patrol = new Patrol();
		
		patrol.setPatrol_state(patrol_state);
		patrol.setPager_offset(pager_offset);
		patrol.setPager_openset(pager_openset);
		patrol.setPatrol_user_code(user_code);
		patrol.setCompany_name(company_name);
		try {
			patrol = patrolService.appSearch(patrol);
			CODEMSG = SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(e.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, patrol);
	}
}
