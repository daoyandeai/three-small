package com.rs.controller;
import com.rs.po.PatrolPunish;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.po.returnPo.PatrolPunishReturn;
import com.rs.service.PatrolPunishService;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
/**
 * 
 * @ClassName: PatrolPunishController
 * @Description: 工单处罚方式控制层
 * @Author tangsh
 * @DateTime 2020年5月12日 上午10:17:56
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/punish")
public class PatrolPunishController extends BaseController {

	@Autowired
	private PatrolPunishService patrolPunishService;
	@Autowired
	private TokenParam tokenParam;
	
	private Logger logger = LoggerFactory.getLogger(PatrolPunishController.class);

	/**
	 * 
	 * @Title: list
	 * @Description: 列表
	 * @Author tangsh
	 * @DateTime 2020年5月12日 上午10:18:35
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(PatrolPunish form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String punish_name = form.getPunish_name();
			if(!StringUtils.isEmpty(punish_name) && RegularUtil.isSpecialChar(punish_name)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int pager_offset = form.getPager_offset();
			if(StringUtils.isEmpty(pager_offset) || !RegularUtil.isNumber(pager_offset+"")) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int pager_openset = form.getPager_openset();
			if(StringUtils.isEmpty(pager_openset) || !RegularUtil.isNumber(pager_openset+"")) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			map.put("pager_count", patrolPunishService.findByCount(form));
			List<PatrolPunish> _list = patrolPunishService.findByList(form);
			List<PatrolPunishReturn> _pdrlist = new ArrayList<PatrolPunishReturn>();
			_list.forEach(pd ->{
				PatrolPunishReturn pdr = new PatrolPunishReturn();
				BeanUtils.copyProperties(pd, pdr);
				_pdrlist.add(pdr);
			});
			map.put("pager_list",_pdrlist);
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
	 * @Description: 新增
	 * @Author tangsh
	 * @DateTime 2020年5月12日 上午10:38:38
	 * @param form
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody PatrolPunish form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String punish_name = form.getPunish_name();
			if(StringUtils.isEmpty(punish_name) || RegularUtil.trimZero(punish_name) || RegularUtil.isSpecialChar(punish_name)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if (patrolPunishService.findByExist(form) > 0) {
				CODEMSG = KEY_PARAM_EXIST;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_code_add(user.getUser_code());
			form.setUser_name_add(user.getUser_name());
			form.setPunish_code(CodeUtil.getSystemCode("Punish"));
			int result = patrolPunishService.save(form);
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
	
	/**
	 * 
	 * @Title: single
	 * @Description: 查询单个对象
	 * @Author tangsh
	 * @DateTime 2020年5月12日 上午10:43:27
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/single")
	public Object single(PatrolPunish form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String punish_code = form.getPunish_code();
			if(StringUtils.isEmpty(punish_code) || RegularUtil.matchLength(punish_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			
			form = patrolPunishService.findByCode(form);
			if(!StringUtils.isEmpty(form)) {
				PatrolPunishReturn dr = new PatrolPunishReturn();
				BeanUtils.copyProperties(form, dr);
				map.put("patrolPunish", dr);
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
	 * @DateTime 2020年5月12日 上午10:45:41
	 * @param form
	 * @param request
	 * @return
	 */
	@PutMapping(value = "/update")
	public Object update(@RequestBody PatrolPunish form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String punish_code = form.getPunish_code();
			if(StringUtils.isEmpty(punish_code) || RegularUtil.matchLength(punish_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String punish_name = form.getPunish_name();
			if(StringUtils.isEmpty(punish_name) || RegularUtil.trimZero(punish_name) || RegularUtil.isSpecialChar(punish_name)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if (patrolPunishService.findByExist(form) > 0) {
				CODEMSG = KEY_PARAM_EXIST;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_code_update(user.getUser_code());
			form.setUser_name_update(user.getUser_name());
			int result = patrolPunishService.update(form);
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
	
	/**
	 * 
	 * @Title: updateState
	 * @Description: 更新状态
	 * @Author tangsh
	 * @DateTime 2020年5月12日 上午10:48:41
	 * @param form
	 * @return
	 */
	@PutMapping(value="/state/update")
	public Object updateState(@RequestBody PatrolPunish form,HttpServletRequest request){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String punish_code = form.getPunish_code();
			if(StringUtils.isEmpty(punish_code) || RegularUtil.isSpecialChar(punish_code) || RegularUtil.matchLength(punish_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(!(form.getState() == 1 || form.getState() == 2)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_code_update(user.getUser_code());
			form.setUser_name_update(user.getUser_name());
			int result = patrolPunishService.update(form);
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
