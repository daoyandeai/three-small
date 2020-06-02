package com.rs.controller;
import com.rs.po.PatrolDispose;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.po.returnPo.PatrolDisposeReturn;
import com.rs.service.PatrolDisposeService;
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
 * @ClassName: PatrolDisposeController
 * @Description: 工单处理方式控制层
 * @Author tangsh
 * @DateTime 2020年5月12日 上午10:17:56
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/dispose")
public class PatrolDisposeController extends BaseController {

	@Autowired
	private PatrolDisposeService patrolDisposeService;
	@Autowired
	private TokenParam tokenParam;
	
	private Logger logger = LoggerFactory.getLogger(PatrolDisposeController.class);

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
	public Object list(PatrolDispose form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String dispose_name = form.getDispose_name();
			if(!StringUtils.isEmpty(dispose_name) && RegularUtil.isSpecialChar(dispose_name)) {
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
			map.put("pager_count", patrolDisposeService.findByCount(form));
			List<PatrolDispose> _list = patrolDisposeService.findByList(form);
			List<PatrolDisposeReturn> _pdrlist = new ArrayList<PatrolDisposeReturn>();
			_list.forEach(pd ->{
				PatrolDisposeReturn pdr = new PatrolDisposeReturn();
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
	public Object save(@RequestBody PatrolDispose form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String dispose_name = form.getDispose_name();
			if(StringUtils.isEmpty(dispose_name) || RegularUtil.trimZero(dispose_name) || RegularUtil.isSpecialChar(dispose_name)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if (patrolDisposeService.findByExist(form) > 0) {
				CODEMSG = KEY_PARAM_EXIST;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_code_add(user.getUser_code());
			form.setUser_name_add(user.getUser_name());
			form.setDispose_code(CodeUtil.getSystemCode("dispose"));
			int result = patrolDisposeService.save(form);
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
	public Object single(PatrolDispose form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String dispose_code = form.getDispose_code();
			if(StringUtils.isEmpty(dispose_code) || RegularUtil.matchLength(dispose_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			
			form = patrolDisposeService.findByCode(form);
			if(!StringUtils.isEmpty(form)) {
				PatrolDisposeReturn dr = new PatrolDisposeReturn();
				BeanUtils.copyProperties(form, dr);
				map.put("patrolDispose", dr);
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
	public Object update(@RequestBody PatrolDispose form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String dispose_code = form.getDispose_code();
			if(StringUtils.isEmpty(dispose_code) || RegularUtil.matchLength(dispose_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String dispose_name = form.getDispose_name();
			if(StringUtils.isEmpty(dispose_name) || RegularUtil.trimZero(dispose_name) || RegularUtil.isSpecialChar(dispose_name)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if (patrolDisposeService.findByExist(form) > 0) {
				CODEMSG = KEY_PARAM_EXIST;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_code_update(user.getUser_code());
			form.setUser_name_update(user.getUser_name());
			int result = patrolDisposeService.update(form);
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
	public Object updateState(@RequestBody PatrolDispose form,HttpServletRequest request){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String dispose_code = form.getDispose_code();
			if(StringUtils.isEmpty(dispose_code) || RegularUtil.isSpecialChar(dispose_code) || RegularUtil.matchLength(dispose_code,25)) {
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
			int result = patrolDisposeService.update(form);
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
