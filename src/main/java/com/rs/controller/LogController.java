package com.rs.controller;

import com.rs.po.Log;
import com.rs.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName: LogController
 * @Description: 日志管理控制层
 * @Author tangsh
 * @DateTime 2020年2月12日 下午3:10:20
 */
@Transactional
@RestController
@RequestMapping("/api/log")
public class LogController extends BaseController {

	@Autowired
	private LogService logService;

	private Logger logger = LoggerFactory.getLogger(LogController.class);
	
	/**
	 * 
	 * @Title: search
	 * @Description: 日志列表
	 * @Author tangsh
	 * @DateTime 2020年2月12日 下午3:11:25
	 * @param form
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/search")
	public Object search(Log form) {
		CODEMSG = ERROR;
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("pager_list",logService.findByList(form));
			map.put("pager_count",logService.findByCount(form));
			CODEMSG=SUCCESS;
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
	 * @Title: all
	 * @Description: 查询所有日志
	 * @Author tangsh
	 * @DateTime 2020年2月12日 下午3:13:15
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/all")
	public Object all(Log form) {
		CODEMSG = ERROR;
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("pager_list",logService.findByAll(form));
			CODEMSG=SUCCESS;
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
     * @Title: single
     * @Description: 查询单条日志
     * @Author tangsh
     * @DateTime 2020年2月12日 下午3:14:37
     * @param form
     * @return
     */
    @GetMapping(value="/single")
    public Object single(Log form){
    	try {
    		form=logService.findByCode(form);
			CODEMSG = SUCCESS;
    	}catch (Exception ex) {
    		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
    	}
    	return finish(CODEMSG,form);
	}

	/**
	 * 添加新闻
	 * @Title: save
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年12月16日 上午11:27:46
	 * @param user_code 上传人员code
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody Log form) {
		try {
			int result = logService.save(form);
			if (result > 0) {
				CODEMSG = SUCCESS;
			} else {
				CODEMSG = ERROR;
			}
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, null);
	}
}
