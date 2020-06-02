package com.rs.controller;
import com.rs.po.CheckSelf;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.service.CheckSelfService;
import com.rs.service.MessLogService;
import com.rs.util.CodeUtil;
import com.rs.util.RegularUtil;
import com.rs.util.TokenUserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
/**
 * 
 * @ClassName: CheckSelfController
 * @Description: 自查自纠控制层
 * @Author tangsh
 * @DateTime 2020年3月2日 上午11:58:05
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/checkSelf")
public class CheckSelfController extends BaseController {

	@Autowired
	private CheckSelfService checkSelfService;
	@Autowired
	private MessLogService messLogService;
	@Autowired
	private TokenParam tokenParam;
	
	private Logger logger = LoggerFactory.getLogger(CheckSelfController.class);

	/**
	 * 
	 * @Title: list
	 * @Description: 查询分页列表
	 * @Author tangsh
	 * @DateTime 2020年3月2日 上午11:58:53
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(CheckSelf form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			User user = TokenUserUtil.generateUser(request, tokenParam);
			if(user==null){
				map=null;
				CODEMSG = SUCCESS;
				return finish(CODEMSG, map);
			}
			String company_name = form.getCompany_name();
			if(!StringUtils.isEmpty(company_name) && RegularUtil.isSpecialChar(company_name)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			if(StringUtils.isEmpty(form.getIscheckself())) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			map=checkSelfService.findList(form,user);
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
	 * @Description: 新增一条数据
	 * @Author tangsh
	 * @DateTime 2020年3月2日 下午12:04:58
	 * @param form
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody CheckSelf form,HttpServletRequest request) {
		try {
			String company_code = form.getCompany_code();
			if(StringUtils.isEmpty(company_code) || RegularUtil.matchLength(company_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String company_name = form.getCompany_name();
			if(StringUtils.isEmpty(company_name) || !RegularUtil.isLength(company_name, 1, 50)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_code_add(user.getUser_code());
			form.setUser_name_add(user.getUser_name());
			form.setCheckself_code(CodeUtil.getSystemCode("checkSelf"));
			int result = checkSelfService.save(form);
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
	 * @Description: 查询单条信息
	 * @Author tangsh
	 * @DateTime 2020年3月2日 下午12:06:55
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/single")
	public Object single(CheckSelf form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String checkself_code = form.getCheckself_code();
			if(StringUtils.isEmpty(checkself_code) || RegularUtil.matchLength(checkself_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			map.put("checkSelf", checkSelfService.findByCode(form));
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
	 * @Title: clist
	 * @Description: 企业查询列表
	 * @Author tangsh
	 * @DateTime 2020年3月2日 下午1:06:57
	 * @param form
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/clist")
	public Object clist(CheckSelf form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			if(StringUtils.isEmpty(form.getCompany_code())) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			map.put("pager_count", checkSelfService.findByCount(form));
			map.put("check_list", checkSelfService.findByList(form));
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
	 * @Title: saveMessLog
	 * @Description: 自查自纠发送提醒
	 * @Author tangsh
	 * @DateTime 2020年3月5日 下午11:51:28
	 * @param form
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/saveMessLog")
	public Object saveMessLog(@RequestBody CheckSelf form,HttpServletRequest request) {
		try {
			String checkself_code = form.getCheckself_code();
			if(StringUtils.isEmpty(checkself_code)&&(form.getCheckself_code_list()==null||form.getCheckself_code_list().size()<=0)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			if(!StringUtils.isEmpty(checkself_code)) {
				CheckSelf cf=checkSelfService.findByCode(form);
				messLogService.saveMessLogByOtherCode(5, cf.getCompany_code(), checkself_code, user);
			}else {
				CheckSelf cf=null;
				int sendnum=0;
				for(String cf_code:form.getCheckself_code_list()) {
					if(StringUtils.isEmpty(cf_code)) {
						continue;
					}
					cf=new CheckSelf();
					cf.setCheckself_code(cf_code);
					cf=checkSelfService.findByCode(cf);
					messLogService.saveMessLogByOtherCode(5, cf.getCompany_code(), cf_code, user);
					sendnum++;
				}
				if(sendnum==0) {
					CODEMSG = PARAMETER_ERROR;
					return finish(CODEMSG, null);
				}
			}
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, form);
	}
	
	/**
	 * 
	 * @Title: saveMessLogByNotCS
	 * @Description: 未自查自纠消息提醒
	 * @Author tangsh
	 * @DateTime 2020年4月28日 上午11:22:46
	 * @param form
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/saveMessLogByNotCS")
	public Object saveMessLogByNotCS(@RequestBody CheckSelf form,HttpServletRequest request) {
		try {
			String company_code = form.getCompany_code();
			if(StringUtils.isEmpty(company_code)&&(form.getCheckself_code_list()==null||form.getCheckself_code_list().size()<=0)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			if(!StringUtils.isEmpty(company_code)) {				
				messLogService.saveMessLogByOtherCode(5, company_code, company_code, user);
			}else {
//				CheckSelf cf=null;
				int sendnum=0;
				for(String c_code:form.getCheckself_code_list()) {
					if(StringUtils.isEmpty(c_code)) {
						continue;
					}
//					cf=new CheckSelf();
//					cf.setCheckself_code(cf_code);
//					cf=checkSelfService.findByCode(cf);
					messLogService.saveMessLogByOtherCode(5, c_code, c_code, user);
					sendnum++;
				}
				if(sendnum==0) {
					CODEMSG = PARAMETER_ERROR;
					return finish(CODEMSG, null);
				}
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
