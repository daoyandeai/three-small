package com.rs.controller;

import com.rs.po.CompanyEmploy;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.po.returnPo.CompanyEmployReturn;
import com.rs.service.CompanyEmployService;
import com.rs.util.CodeUtil;
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
 * 
 * @ClassName: CompanyEmployController
 * @Description: 三小档案人员管理控制层
 * @Author tangsh
 * @DateTime 2020年3月17日 下午3:25:15
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/companyEmploy")
public class CompanyEmployController extends BaseController {

	@Autowired
	private CompanyEmployService companyEmployService;
	@Autowired
	private TokenParam tokenParam;

	private Logger logger = LoggerFactory.getLogger(CompanyEmployController.class);

	/**
	 * 
	 * @Title: list
	 * @Description: 三小档案人员查询
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:25:08
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(CompanyEmploy form) {
		List<CompanyEmployReturn> cerlist = new ArrayList<CompanyEmployReturn>();
		try {
			List<CompanyEmploy> celist = companyEmployService.findByAll(form);
			if(celist!=null&&celist.size()>0) {
				celist.forEach(ce ->{
					CompanyEmployReturn cer=new CompanyEmployReturn();
					BeanUtils.copyProperties(ce, cer);
					cer.setEmploy_flag(false);
					cerlist.add(cer);
				});
			}
			CODEMSG = SUCCESS ;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, cerlist);
	}
	
	/**
	 * 
	 * @Title: search
	 * @Description: 从业人员过期预警情况
	 * @Author tangsh
	 * @DateTime 2020年3月31日 上午11:00:07
	 * @param form
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/search")
	public Object search(CompanyEmploy form,HttpServletRequest request) {
		Map<String,Object> map = new HashMap<>();
		try {
			User user = TokenUserUtil.generateUser(request, tokenParam);
			map.put("pager_count",companyEmployService.findBySearchCount(form,user));
			map.put("pager_list",companyEmployService.findBySearchList(form,user));
			//即将过期1.正常 2.过期  4.即将过期
			form.setE_state(4);
			map.put("will_expire",companyEmployService.findBySearchCount(form,user));
			//已过期
			form.setE_state(2);
			map.put("expired",companyEmployService.findBySearchCount(form,user));
			
			CODEMSG = SUCCESS;
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
	 * @Title: delete
	 * @Description: 三小档案人员删除
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:25:03
	 * @param form
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Object delete(CompanyEmploy form) {
		try {
			if(StringUtils.isEmpty(form.getEmploy_code())){
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG,null);
			}
			if(companyEmployService.delete(form) != 0){
				CODEMSG=SUCCESS;
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
	 * @Title: save
	 * @Description: 备案人员新增接口
	 * @Author tangsh
	 * @DateTime 2020年5月15日 下午3:09:25
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody CompanyEmploy form) {
		CODEMSG = SUCCESS;
		CompanyEmployReturn cer=null;
		try {
			if(StringUtils.isEmpty(form.getCompany_code())||StringUtils.isEmpty(form.getEmploy_name())){
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG,null);
			}
			form.setEmploy_ishealth(1);
			form.setEmploy_code(CodeUtil.getSystemCode("companyEmploy"));
			if (companyEmployService.save(form) <= 0) {
				CODEMSG = ERROR;
			}else {
				cer=new CompanyEmployReturn();
				BeanUtils.copyProperties(form, cer);
			}
			
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, cer);
	}
	
	/**
	 * 
	 * @Title: update
	 * @Description: 备案人员更新接口
	 * @Author tangsh
	 * @DateTime 2020年5月15日 下午3:15:11
	 * @param form
	 * @return
	 */
	@PutMapping(value="/update")
    public Object update(@RequestBody CompanyEmploy form) {
    	CODEMSG = SUCCESS;
    	try {
    		if(StringUtils.isEmpty(form.getEmploy_code())){
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG,null);
			}
	    	if(companyEmployService.update(form) <= 0){
	    		CODEMSG = ERROR;
	    	}
    	}catch(Exception ex) {
    		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    		CODEMSG = EXCEPTION;
    		logger.info(ex.toString());
			return finish(CODEMSG,null);
		}
		return finish(CODEMSG,null);
    }
}
