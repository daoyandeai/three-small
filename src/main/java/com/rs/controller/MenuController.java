package com.rs.controller;

import com.rs.po.Menu;
import com.rs.service.MenuService;
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
 * @ClassName: MenuController
 * @Description: 菜单控制层
 * @Author tangsh
 * @DateTime 2020年1月8日 上午10:59:38
 */
@Transactional
@RestController
@RequestMapping("/api/menu")
public class MenuController extends BaseController {

	@Autowired
	private MenuService menuService;
	
	private Logger logger = LoggerFactory.getLogger(MenuController.class);
	
	/**
	 * 
	 * @Title: list
	 * @Description: 
	 * @Author tangsh
	 * @DateTime 2020年1月8日 上午11:07:29
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(Menu form) {
		Map<Object, Object> map = new HashMap<>();
		try {
			map.put("menu_list", menuService.findByList(form));
			map.put("pager_count", menuService.findByCount(form));
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
	 * @Title: all
	 * @Description: 
	 * @Author tangsh
	 * @DateTime 2020年1月8日 上午11:07:26
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/all")
	public Object all(Menu form) {
		Map<Object, Object> map = new HashMap<>();
		try {
			map.put("menu_list", menuService.findMenuAndButtonAll(form));
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
     * @Description: 
     * @Author tangsh
     * @DateTime 2020年1月8日 上午11:07:23
     * @param form
     * @return
     */
    @GetMapping(value="/single")
    public Object single(Menu form){
    	Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			map.put("menu", menuService.findByCode(form));
    		CODEMSG = SUCCESS;
    	}catch (Exception ex) {
    		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
    	}
    	return finish(CODEMSG,map);
	}
    
    /**
     * 
     * @Title: save
     * @Description: 
     * @Author tangsh
     * @DateTime 2020年1月8日 上午11:07:17
     * @param form
     * @return
     */
	@PostMapping(value="/save")
    public Object save(@RequestBody Menu form) {
    	try {
	    	Integer result = menuService.save(form);
	    	if(result<=0){
	    		CODEMSG = ERROR;
	    	}
    	}catch(Exception ex) {
    		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    		CODEMSG = EXCEPTION;
    		logger.info(ex.toString());
			return finish(CODEMSG,null);
		}
		return finish(CODEMSG,form);
    }
    
    /**
     * 
     * @Title: update
     * @Description: 
     * @Author tangsh
     * @DateTime 2020年1月8日 上午11:07:13
     * @param form
     * @return
     */
	@PutMapping(value="/update")
    public Object update(@RequestBody Menu form) {
    	try {
    		Integer result = menuService.update(form);
	    	if(result <= 0){
	    		CODEMSG = ERROR;
	    	}
    	}catch(Exception ex) {
    		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    		CODEMSG = EXCEPTION;
    		logger.info(ex.toString());
			return finish(CODEMSG,null);
		}
		return finish(CODEMSG,form);
    }
	
	
}
