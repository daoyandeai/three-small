package com.rs.controller;

import com.rs.po.CompanyType;
import com.rs.po.News;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.po.returnPo.NewsReturn;
import com.rs.service.CompanyTypeService;
import com.rs.service.NewsService;
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

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
@RequestMapping("/api/news")
public class NewsController extends BaseController {

	@Autowired
	private NewsService newsService;
	@Autowired
	private TokenParam tokenParam;
	@Autowired
	private CompanyTypeService companyTypeService;

	private Logger logger = LoggerFactory.getLogger(NewsController.class);
	
	/**
	 * 
	 * @Title: list
	 * @Description: 获取新闻宣传列表
	 * @Author tangsh
	 * @DateTime 2019年12月16日 下午3:35:30
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/search")
	public Object search(News form,HttpServletRequest request) {
		CODEMSG = ERROR;
		Map<String,Object> map = new HashMap<>();
		List<NewsReturn> list = new ArrayList<>();
		try {
			User user = TokenUserUtil.generateUser(request, tokenParam);
			if(user==null){
				CODEMSG = USER_UNDEFINED;
				return finish(CODEMSG,null);
			}
			switch (user.getUser_level()){
				case "1":
					form.setNews_province(user.getUser_province());break;
				case "2":
					form.setNews_city(user.getUser_city());break;
				case "3":
					form.setNews_area(user.getUser_area());break;
				case "4":
					form.setNews_town(user.getUser_town());break;
				case "5":
					form.setNews_vill(user.getUser_vill());break;
			}
			form.setUser_code(null);
			form.setNews_state(1);
			List<News> newsList = newsService.findBySearch(form);
			for (News news:
				 newsList) {
				NewsReturn nr = new NewsReturn();
				BeanUtils.copyProperties(news,nr);
				
				if (!StringUtils.isEmpty(news.getCompanytype_codes())){
					String[] str = news.getCompanytype_codes().split(",");
					String strs = "";
					for (String s : str) {
							CompanyType c = companyTypeService.findByOne(s);
							strs += c.getCompanytype_name() + ",";

					}
					nr.setCompanytype_names(strs);
				}
				list.add(nr);
			}
			map.put("pager_list",list);
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
	public Object save(@RequestBody News form,@RequestBody String user_code, HttpServletRequest request) {
		try {
			String news_title = form.getNews_title();
			String news_content =  form.getNews_content();
			if(StringUtils.isEmpty(news_title) ) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(StringUtils.isEmpty(news_content)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			User user  = TokenUserUtil.generateUser(request, tokenParam);
			if(user==null){
				CODEMSG = USER_UNDEFINED;
				return finish(CODEMSG,null);
			}
/*			//培训区域
			String new_province = form.getNews_province();
			String new_city = form.getNews_city();
			String new_area = form.getNews_area();
			String new_town = form.getNews_town();
			String new_vill = form.getNews_vill();*/
			//登录用户区域
//			String user_province = user.getUser_province();
//			String user_city = user.getUser_city();
//			String user_area = user.getUser_area();
//			String user_town = user.getUser_town();
//			String user_vill = user.getUser_vill();
//
//			form.setNews_province(user_province);
//			form.setNews_city(user_city);
//			form.setNews_area(user_area);
//			form.setNews_town(user_town);
//			form.setNews_vill(user_vill);

			form.setUser_code(user.getUser_code());
			form.setUser_name(user.getUser_name());
			form.setNews_code(CodeUtil.getSystemCode("news"));

			int result = newsService.save(form);
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
    
    /**
     * 
     * @Title: update
     * @Description: 更新单个新闻宣传
     * @Author tangsh
     * @DateTime 2019年12月16日 下午3:37:23
     * @param form
     * @return
     */
	@PutMapping(value="/update")
    public Object update(@RequestBody News form,HttpServletRequest request) {
    	CODEMSG = SUCCESS;
    	try {
			User user = TokenUserUtil.generateUser(request, tokenParam);
			if(user==null){
				CODEMSG = USER_UNDEFINED;
				return finish(CODEMSG,null);
			}
			if(!user.getUser_code().equals(form.getUser_code())){
				CODEMSG = ERROR;
				return finish(CODEMSG,null);
			}

    		Integer result = newsService.update(form);
	    	if(result <= 0){
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
