package com.rs.controller;
import com.rs.po.CompanyTag;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.po.returnPo.CompanyTagReturn;
import com.rs.service.CompanyTagService;
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
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
/**
 * 主体类型控制层
 * @ClassName: CompanyTagController
 * @Description: 
 * @Author sven
 * @DateTime 2020年2月6日 上午10:43:36
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/companytag")
public class CompanyTagController extends BaseController {

	@Autowired
	private CompanyTagService companyTagService;
	@Autowired
	private TokenParam tokenParam;
	
	private Logger logger = LoggerFactory.getLogger(CompanyTagController.class);

	/**
	 * 根据食品监管分类查询对应主体类型_不分页
	 * @Title: all
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:37:26
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/all")
	public Object all(CompanyTag form) {
		List<CompanyTagReturn> ctrList = new ArrayList<CompanyTagReturn>();
		try {
		/*	String companytype_code = form.getCompanytype_code();
			if (StringUtils.isEmpty(companytype_code) || RegularUtil.matchLength(companytype_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}*/
			form.setState(1);
			List<CompanyTag> ctList = companyTagService.findByAll(form);
			for (CompanyTag ct : ctList) {
				CompanyTagReturn ctr = new CompanyTagReturn();
				BeanUtils.copyProperties(ct,ctr);
				ctrList.add(ctr);
			}
			
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, ctrList);
	}

	/**
	 * 根据食品监管分类查询对应主体类型_分页
	 * @Title: list
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:37:55
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(CompanyTag form) {
		Map<String,Object> map = new HashMap<>();
		try {
			String companytype_code = form.getCompanytype_code();
			if (StringUtils.isEmpty(companytype_code) || RegularUtil.matchLength(companytype_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			List<CompanyTag> ctList = companyTagService.findByList(form);
			List<CompanyTagReturn> ctrList = new ArrayList<>();
			for (CompanyTag ct : ctList) {
				CompanyTagReturn ctr = new CompanyTagReturn();
				BeanUtils.copyProperties(ct,ctr);
				ctrList.add(ctr);
			}
			map.put("company_tag_list",ctrList);
			map.put("pager_count",companyTagService.findByCount(form));
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
	 * 新增主体类型
	 * @Title: save
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:38:42
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody CompanyTag form ,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String companytype_code = form.getCompanytype_code();
			if (StringUtils.isEmpty(companytype_code) || RegularUtil.matchLength(companytype_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String companytag_name = form.getCompanytag_name();
			if(!StringUtils.isEmpty(companytag_name) && RegularUtil.isSpecialChar(companytag_name)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_code_add(user.getUser_code());
			form.setUser_name_add(user.getUser_name());
			form.setCompanytag_code(CodeUtil.getSystemCode("companyTag"));
			int count = companyTagService.findByExist(form);
			if (count > 0) {
				CODEMSG = COMPANY_TAG_EXIST;
				return finish(CODEMSG, null);
			}
			int result = companyTagService.save(form);
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
	 * 查询单条主体类型
	 * @Title: single
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:39:00
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/single")
	public Object single(CompanyTag form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String companytag_code = form.getCompanytag_code();
			if (StringUtils.isEmpty(companytag_code) || RegularUtil.matchLength(companytag_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			form = companyTagService.findByCode(form);
			if (!StringUtils.isEmpty(form)) {
				CompanyTagReturn ctr = new CompanyTagReturn();
				BeanUtils.copyProperties(form, ctr);
				map.put("company_tag", ctr);
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
	 * 编辑主体类型
	 * @Title: update
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:39:10
	 * @param form
	 * @return
	 */
	@PutMapping(value = "/update")
	public Object update(@RequestBody CompanyTag form, HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String companytype_code = form.getCompanytype_code();
			if (StringUtils.isEmpty(companytype_code) || RegularUtil.isSpecialChar(companytype_code)|| RegularUtil.matchLength(companytype_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String companytag_code = form.getCompanytag_code();
			if (StringUtils.isEmpty(companytag_code) || RegularUtil.matchLength(companytag_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String companytag_name = form.getCompanytag_name();
			if (!StringUtils.isEmpty(companytag_name) && RegularUtil.isSpecialChar(companytag_name)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			int count = companyTagService.findByExist(form);
			if (count > 0) {
				CODEMSG = COMPANY_TAG_EXIST;
				return finish(CODEMSG, null);
			}
			form.setUser_code_update(user.getUser_code());
			form.setUser_name_update(user.getUser_name());
			int result = companyTagService.update(form);
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
	 * 查询主体类型是否存在
	 * @Title: exist
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月6日 上午11:43:22
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/exist")
	public Object exist(CompanyTag form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String companytag_name = form.getCompanytag_name();
			if(!StringUtils.isEmpty(companytag_name) && RegularUtil.isSpecialChar(companytag_name)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			int count = companyTagService.findByExist(form);
			if (count == 0) {
				CODEMSG = SUCCESS;
			}else {
				map = null;
				CODEMSG = COMPANY_TAG_EXIST;
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
	 * 更新主体类型状态
	 * @Title: updateState
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月7日 下午3:13:29
	 * @param form
	 * @return
	 */
	@PutMapping(value = "/state/update")
	public Object updateState(@RequestBody CompanyTag form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String companytag_code = form.getCompanytag_code();
			int state = form.getState();
			if (StringUtils.isEmpty(companytag_code) || RegularUtil.isSpecialChar(companytag_code) || RegularUtil.matchLength(companytag_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if (!(state == 1 || state == 2)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int result = companyTagService.update(form);
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
