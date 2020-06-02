package com.rs.controller;
import com.alibaba.fastjson.JSON;
import com.rs.po.PatrolConfig;
import com.rs.service.PatrolConfigService;
import com.rs.util.CodeUtil;
import com.rs.util.RegularUtil;
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
/**
 * 
 * @ClassName: PatrolConfigController
 * @Description: 巡查配置控制层
 * @Author tangsh
 * @DateTime 2020年1月20日 上午9:37:08
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/patrolConfig")
public class PatrolConfigController extends BaseController {

	@Autowired
	private PatrolConfigService patrolConfigService;
	
	private Logger logger = LoggerFactory.getLogger(PatrolConfigController.class);

	/**
	 * 
	 * @Title: list
	 * @Description: 查询列表
	 * @Author tangsh
	 * @DateTime 2020年1月20日 上午9:40:14
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(PatrolConfig form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			map.put("pager_count", patrolConfigService.findByCount(form));
			map.put("pager_list",patrolConfigService.findByList(form));
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
	 * @Title: save
	 * @Description: 保存
	 * @Author tangsh
	 * @DateTime 2020年1月20日 上午9:40:23
	 * @param form
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody PatrolConfig form) {
		try {
			String companytag_code = form.getCompanytag_code();
			if(StringUtils.isEmpty(companytag_code) || RegularUtil.matchLength(companytag_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(StringUtils.isEmpty(form.getConfig_content())) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(patrolConfigService.findByExist(form)>0) {
				CODEMSG = KEY_PARAM_EXIST;
				return finish(CODEMSG, null);
			}
			form.setConfig_code(CodeUtil.getSystemCode("patrolConfig"));
			form.setConfig_content(JSON.toJSONString(form.getConfig_content()));
			int result = patrolConfigService.save(form);
			if (result > 0) {
				CODEMSG = SUCCESS;
			} else {
				CODEMSG = ERROR;
			}
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, form);
	}
	
	/**
	 * 
	 * @Title: single
	 * @Description: 单个查询
	 * @Author tangsh
	 * @DateTime 2020年1月20日 上午9:41:27
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/single")
	public Object single(PatrolConfig form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String config_code = form.getConfig_code();
			if(StringUtils.isEmpty(config_code) || RegularUtil.matchLength(config_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			form = patrolConfigService.findByCode(form);
			if(!StringUtils.isEmpty(form)) {
				map.put("device", form);
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
	 * 
	 * @Title: update
	 * @Description: 编辑
	 * @Author tangsh
	 * @DateTime 2020年1月20日 上午9:47:09
	 * @param form
	 * @return
	 */
	@PutMapping(value = "/update")
	public Object update(@RequestBody PatrolConfig form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String config_code = form.getConfig_code();
			if(StringUtils.isEmpty(config_code) || RegularUtil.matchLength(config_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String companytag_code = form.getCompanytag_code();
			if(StringUtils.isEmpty(companytag_code) || RegularUtil.matchLength(companytag_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(StringUtils.isEmpty(form.getConfig_content())) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(patrolConfigService.findByExist(form)>0) {
				CODEMSG = KEY_PARAM_EXIST;
				return finish(CODEMSG, null);
			}
			form.setConfig_content(JSON.toJSONString(form.getConfig_content()));
			int result = patrolConfigService.update(form);
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
}
