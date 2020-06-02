package com.rs.controller;

import com.rs.po.News;
import com.rs.po.User;
import com.rs.po.returnPo.NewsReturn;
import com.rs.service.NewsService;
import com.rs.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName: NewsController
 * @Description: 新闻宣传控制层
 * @Author tangsh
 * @DateTime 2019年12月16日 下午3:15:56
 */
@Transactional
@RestController
@RequestMapping("/app/news")
public class AppNewsController extends BaseController {

	@Autowired
	private NewsService newsService;
	@Autowired
	private UserService userService;

	private Logger logger = LoggerFactory.getLogger(AppNewsController.class);
	
	/**
	 * 
	 * @Title: list
	 * @Description: 获取新闻宣传列表
	 * @Author tangsh
	 * @DateTime 2019年12月16日 下午3:35:30
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object search(News form,User user) {
		CODEMSG = ERROR;
		Map<String,Object> map = new HashMap<>();
		try {
			user = userService.findByCode(user);
//			form.setNews_province(user.getUser_province());
//			form.setNews_city(user.getUser_city());
//			form.setNews_area(user.getUser_area());
//			form.setNews_town(user.getUser_town());
//			form.setNews_vill(user.getUser_vill());
			form.setUser_code(null);
			form.setNews_state(1);
			map.put("pager_list",newsService.findBySearch(form));
			map.put("pager_count",newsService.findBySearchCount(form));
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
	 * @Description: 获取所有有效的新闻宣传
	 * @Author tangsh
	 * @DateTime 2019年12月16日 下午3:36:11
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/all")
	public Object all(News form) {
		CODEMSG = ERROR;
		try {
			form.setNews_state(1);
			form.setPager_list(newsService.findByList(form));
			CODEMSG=SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, form);
	}
	
    /**
     * 
     * @Title: single
     * @Description: 查询单个新闻宣传栏目
     * @Author tangsh
     * @DateTime 2019年12月16日 下午3:36:38
     * @param form
     * @return
     */
    @GetMapping(value="/single")
    public Object single(News form){
		NewsReturn nr = new NewsReturn();
    	try {
    		form=newsService.findByCode(form);
			BeanUtils.copyProperties(form,nr);
			CODEMSG = SUCCESS;
    	}catch (Exception ex) {
    		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
    	}
    	return finish(CODEMSG,nr);
	}
}
