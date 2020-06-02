package com.rs.controller;

import com.rs.po.*;
import com.rs.service.MessLogService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @ClassName: PatrolController
 * @Description: 巡查报备控制层
 * @Author tangsh
 * @DateTime 2020年2月17日 上午10:58:12
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/patrol")
public class PatrolController extends BaseController {

	@Autowired
	private PatrolService patrolService;
	@Autowired
	private PatrolDisposeService patrolDisposeService;
	@Autowired
	private PatrolPunishService patrolPunishService;
	@Autowired
    private MessLogService messLogService;
	@Autowired
	private TokenParam tokenParam;

	private Logger logger = LoggerFactory.getLogger(PatrolController.class);

	/**
	 * 
	 * @Title: detail
	 * @Description: 查询巡查详情
	 * @Author tangsh
	 * @DateTime 2020年3月17日 上午10:58:45
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/detail")
	public Object detail(Patrol form) {
		CODEMSG = ERROR;
		try {
			form = patrolService.findByPatrolCompany(form);
			if(form==null) {
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
	 * @DateTime 2020年3月17日 上午10:58:51
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
	 * @DateTime 2020年3月17日 上午10:59:01
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
	 * @DateTime 2020年3月17日 上午10:59:07
	 * @param form
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody Patrol form,HttpServletRequest request) {
		CODEMSG = PARAMETER_ERROR;
		try {
			if(StringUtils.isEmpty(form.getCompany_code())) {
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_code(user.getUser_code());
			form.setUser_name(user.getUser_name());
			int i = patrolService.save(form);
			if(i!=0){
				if(!StringUtils.isEmpty(form.getPatrol_result())&&form.getPatrol_result().equals("不合格")) {
					messLogService.saveMessLogByOtherCode(3, form.getCompany_code(),form.getPatrol_code(), user);
				}
				if(StringUtils.isEmpty(form.getPatrol_result())) {//新增巡查
					messLogService.saveMessLogByUserCode(10, null, form.getPatrol_user_code(), null, form.getPatrol_code(), user,form.getPatrol_time());
					
				}
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
	 * @Description: 更新巡查
	 * @Author tangsh
	 * @DateTime 2020年1月14日 下午4:00:05
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/update")
	public Object update(@RequestBody Patrol form) {
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
	
	/**
	 * 
	 * @Title: all
	 * @Description: 根据用户查询当日所有的巡查
	 * @Author tangsh
	 * @DateTime 2020年3月17日 上午11:23:00
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/all")
	public Object all(Patrol form) {
		CODEMSG = ERROR;
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			map.put("patrol_list", patrolService.findLeftJoinCompanyAll(form));
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
