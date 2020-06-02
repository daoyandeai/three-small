package com.rs.controller;
import com.rs.po.MainSubChef;
import com.rs.service.MainSubChefService;
import com.rs.util.CodeUtil;

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
 * 
 * @ClassName: MainSubChefController
 * @Description: 帮厨控制层
 * @Author tangsh
 * @DateTime 2020年4月8日 上午9:47:03
 */
@Transactional
@RestController
@RequestMapping("/api/mainSubChef")
public class MainSubChefController extends BaseController {

	@Autowired
	private MainSubChefService mainSubChefService;
	
	private Logger logger = LoggerFactory.getLogger(MainSubChefController.class);

	
	/**
	 * 
	 * @Title: list
	 * @Description: 帮厨管理列表
	 * @Author tangsh
	 * @DateTime 2020年4月8日 上午10:00:06
	 * @param form
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(MainSubChef form,HttpServletRequest request){
		Map<Object, Object> map = new HashMap<>();
		try {
			map.put("pager_list", mainSubChefService.findByList(form));
			map.put("pager_count", mainSubChefService.findByCount(form));
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
	 * @Title: save
	 * @Description: 新增
	 * @Author tangsh
	 * @DateTime 2020年4月8日 上午10:00:16
	 * @param form
	 * @param request
	 * @return
	 */
	@PostMapping(value="/save")
	public Object save(@RequestBody MainSubChef form,HttpServletRequest request) {
		CODEMSG= ERROR;
		try {
			if(StringUtils.isEmpty(form.getUser_name_subchef())) {
				CODEMSG= PARAMETER_ERROR;
				return finish(CODEMSG,null); 
			}
			form.setUser_code_subchef(CodeUtil.getSystemCode("mainSubChef"));
			Integer result = mainSubChefService.save(form);
			if(result>0){
				CODEMSG= SUCCESS;
			}
		}catch(Exception ex) {
			logger.info(ex.toString());
			CODEMSG = EXCEPTION;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return finish(CODEMSG,null);
		}
		return finish(CODEMSG,null);
	}

	/**
	 * 
	 * @Title: single
	 * @Description: 查询单个对象
	 * @Author tangsh
	 * @DateTime 2020年4月8日 上午10:02:47
	 * @param form
	 * @return
	 */
	@GetMapping(value="/single")
	public Object single(MainSubChef form){
		try {
			if(StringUtils.isEmpty(form.getUser_code_subchef())) {
				CODEMSG= PARAMETER_ERROR;
				return finish(CODEMSG,null); 
			}
			form = mainSubChefService.findByCode(form);
			CODEMSG = SUCCESS;
		}catch(Exception ex) {
			logger.info(ex.toString());
			CODEMSG = EXCEPTION;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return finish(CODEMSG,null);
		}
		return finish(CODEMSG,form);
	}

    /**
     * 
     * @Title: delete
     * @Description: 删除
     * @Author tangsh
     * @DateTime 2020年4月8日 上午10:05:58
     * @param form
     * @return
     */
    @DeleteMapping(value="/delete")
    public Object delete(MainSubChef form) {
    	try {
    		if(StringUtils.isEmpty(form.getUser_code_subchef())) {
				CODEMSG= PARAMETER_ERROR;
				return finish(CODEMSG,null); 
			}
    		mainSubChefService.delete(form);
    		CODEMSG = SUCCESS;
    	}catch(Exception ex) {
    		logger.info(ex.toString());
			CODEMSG = EXCEPTION;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
    	return finish(CODEMSG,null);
    }

	/**
	 * 更新
	 * @Title: update
	 * @Description:
	 * @Author sven
	 * @param form
	 * @return
	 */
	@PutMapping(value="/update")
	public Object update(@RequestBody MainSubChef form) {
		CODEMSG = ERROR;
		try {
			if(StringUtils.isEmpty(form.getUser_code_subchef())) {
				CODEMSG= PARAMETER_ERROR;
				return finish(CODEMSG,null); 
			}
			Integer result = mainSubChefService.update(form);
			if(result > 0){
				CODEMSG = SUCCESS;
			}
		}catch(Exception ex) {
			logger.info(ex.toString());
			CODEMSG = EXCEPTION;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return finish(CODEMSG,null);
		}
		return finish(CODEMSG,null);
	}
}
