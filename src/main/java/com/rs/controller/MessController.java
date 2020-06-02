package com.rs.controller;
import com.rs.po.Mess;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.po.returnPo.MessReturn;
import com.rs.service.MessLogService;
import com.rs.service.MessService;
import com.rs.util.CodeUtil;
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
 * 消息推送控制层
 * @ClassName: MessController
 * @Description: 
 * @Author sven
 * @DateTime 2020年2月11日 上午10:17:46
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/mess")
public class MessController extends BaseController {

	@Autowired
	private MessService messService;
	@Autowired
	private TokenParam tokenParam;
	@Autowired
	private MessLogService messLogService;
	
	private Logger logger = LoggerFactory.getLogger(MessController.class);

	/**
	 * 新增消息推送
	 * @Title: save
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:35:40
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody Mess form ,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			Integer mess_event = form.getMess_event();
			if (!RegularUtil.OneToAnyNumber(mess_event+"")) {
				CODEMSG = MESS_EVENT_UNVALID;
				return finish(CODEMSG, null);
			}
			Integer mess_type = form.getMess_type();
			if (StringUtils.isEmpty(mess_type) ) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			Integer mess_warn_state = form.getMess_warn_state();
			if (StringUtils.isEmpty(mess_warn_state) ) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_code_add(user.getUser_code());
			form.setUser_name_add(user.getUser_name());
			int result = 0;
			if(StringUtils.isEmpty(form.getMess_code())) {
				form.setMess_code(CodeUtil.getSystemCode("mess"));
				result = messService.save(form);
			}else {
				form.setUser_code_update(user.getUser_code());
				form.setUser_name_update(user.getUser_name());
				result = messService.update(form);
			}
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
	 * 查询单条消息推送
	 * @Title: single
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:35:51
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/single")
	public Object single(Mess form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			Integer mess_event = form.getMess_event();
			if (!RegularUtil.OneToAnyNumber(mess_event+"")) {
				CODEMSG = MESS_EVENT_UNVALID;
				return finish(CODEMSG, null);
			}
			MessReturn ms = messService.findByEvent(form);
			if (!StringUtils.isEmpty(ms)) {
				map.put("mess", ms);
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
	
	
	/***************************列表类消息模板start*********************************/
	/**
	 * 查询列表类消息模板_分页
	 * @Title: list
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:34:44
	 * @param form
	 * @return
	 */
	@GetMapping(value = "notice/list")
	public Object noticeList(Mess form) {
		Map<String,Object> map = new HashMap<>();
		try {
			Integer mess_event = form.getMess_event();
			if (StringUtils.isEmpty(mess_event) || mess_event != 8) {
				CODEMSG = MESS_EVENT_UNVALID;
				return finish(CODEMSG, null);
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
			map.put("notice_list",messService.findByList_app(form));
			map.put("pager_count",messService.findByCount(form));
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
	 * 新增列表类消息模板
	 * @Title: save
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:35:40
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/notice/save")
	public Object saveNotice(@RequestBody Mess form ,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			Integer mess_type = form.getMess_type();
			if (StringUtils.isEmpty(mess_type) ) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			Integer mess_warn_state = form.getMess_warn_state();
			if (StringUtils.isEmpty(mess_warn_state) ) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			form.setMess_code(CodeUtil.getSystemCode("mess"));
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_code_add(user.getUser_code());
			form.setUser_name_add(user.getUser_name());
			int result = messService.save(form);
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
	 * 查询单条列表类消息模板
	 * @Title: single
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:35:51
	 * @param form
	 * @return
	 */
	@GetMapping(value = "notice/single")
	public Object noticeSingle(Mess form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String mess_code = form.getMess_code();
			if (StringUtils.isEmpty(mess_code) || RegularUtil.matchLength(mess_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			MessReturn mr = messService.findByCode_app(form);
			if (!StringUtils.isEmpty(mr)) {
				map.put("notice", mr);
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
	 * 编辑列表类消息模板
	 * @Title: update
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:36:07
	 * @param form
	 * @return
	 */
	@PutMapping(value = "/notice/update")
	public Object updateNotice(@RequestBody Mess form, HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			Integer mess_type = form.getMess_type();
			if (StringUtils.isEmpty(mess_type) ) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			Integer mess_warn_state = form.getMess_warn_state();
			if (StringUtils.isEmpty(mess_warn_state) ) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_code_update(user.getUser_code());
			form.setUser_name_update(user.getUser_name());
			int result = messService.update(form);
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
	 * 删除公告消息模板
	 * @Title: deleteNotice
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月9日 上午11:20:22
	 * @param form
	 * @return
	 */
	@DeleteMapping(value = "/notice/delete")
	public Object deleteNotice(Mess form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String mess_code = form.getMess_code();
			if (StringUtils.isEmpty(mess_code) || RegularUtil.isSpecialChar(mess_code)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int result = messService.delete(form);
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
	 * 发布应急通告
	 * @Title: publish
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月4日 下午2:21:51
	 * @param form
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/publish")
	public Object publish(@RequestBody Mess form ,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String mess_code = form.getMess_code();
			if (StringUtils.isEmpty(mess_code) || RegularUtil.isSpecialChar(mess_code)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form = messService.findByCode(form);
			int result = messLogService.saveMessLogByEvent(form,user);
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
