package com.rs.controller;
import com.rs.po.CompanyIntegrityLog;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.service.CompanyIntegrityLogService;
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
 * 企业诚信评价日志控制层
 * @ClassName: CompanyIntegrityLogController
 * @Description: 
 * @Author sven
 * @DateTime 2020年2月12日 下午2:22:07
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/companyIntegrityLog")
public class CompanyIntegrityLogController extends BaseController {

	@Autowired
	private CompanyIntegrityLogService companyIntegrityLogService;
	@Autowired
	private TokenParam tokenParam;
	
	private Logger logger = LoggerFactory.getLogger(CompanyIntegrityLogController.class);


	/**
	 * 查询企业诚信评价日志_分页
	 * @Title: list
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:34:44
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(CompanyIntegrityLog form) {
		Map<String,Object> map = new HashMap<>();
		try {
			String company_integrity_code = form.getCompany_integrity_code();
			if (StringUtils.isEmpty(company_integrity_code) || RegularUtil.matchLength(company_integrity_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			map.put("company_integrity_log_list",companyIntegrityLogService.findByList_app(form));
			map.put("pager_count",companyIntegrityLogService.findByCount(form));
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
	 * 新增企业诚信评价日志
	 * @Title: save
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:35:40
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody CompanyIntegrityLog form ,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			float integrity_lvl = form.getIntegrity_lvl();
			if(StringUtils.isEmpty(integrity_lvl) || integrity_lvl==0.0) {
				CODEMSG = INTEGRITY_LVL_UNVALID;
				return finish(CODEMSG, null);
			}
			String company_code = form.getCompany_code();
			if (StringUtils.isEmpty(company_code) || RegularUtil.matchLength(company_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String remark = form.getRemark();
			if (StringUtils.isEmpty(remark)) {
				CODEMSG = REMARK_UNVALID;
				return finish(CODEMSG, null);
			}
			form.setMete_type(2);
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_code_add(user.getUser_code());
			form.setUser_name_add(user.getUser_name());
			form.setCompany_integrity_log_code(CodeUtil.getSystemCode("companyIntegrityLog"));
			int result = companyIntegrityLogService.save(form);
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
