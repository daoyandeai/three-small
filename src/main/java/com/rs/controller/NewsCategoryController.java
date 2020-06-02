package com.rs.controller;

import com.rs.po.NewsCategory;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.po.returnPo.NewsCategoryReturn;
import com.rs.service.NewsCategoryService;
import com.rs.util.CodeUtil;
import com.rs.util.TokenUserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: NewsCategoryController
 * @Description: 新闻宣传栏目控制层
 * @Author tangsh
 * @DateTime 2019年12月16日 下午3:15:56
 */
@Transactional
@RestController
@RequestMapping("/api/newsCategory")
public class NewsCategoryController extends BaseController {

	@Autowired
	private NewsCategoryService newsCategoryService;
	@Autowired
	private TokenParam tokenParam;
	
	private Logger logger = LoggerFactory.getLogger(NewsCategoryController.class);
	
	/**
	 * 
	 * @Title: list
	 * @Description: 获取新闻宣传栏目列表
	 * @Author tangsh
	 * @DateTime 2019年12月16日 下午3:32:05
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(NewsCategory form) {
		CODEMSG = ERROR;
		try {
			form.setPager_list(newsCategoryService.findByList(form));
			form.setPager_count(newsCategoryService.findByCount(form));
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
	 * @Title: list
	 * @Description: 获取所有有效的新闻宣传栏目
	 * @Author tangsh
	 * @DateTime 2019年12月16日 下午3:32:05
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/all")
	public Object all(NewsCategory form) {
		CODEMSG = ERROR;
		List<NewsCategoryReturn> returnList = new ArrayList<>();
		try {
			form.setNews_category_state(1);
			List<NewsCategory> list = newsCategoryService.findByList(form);
			for (NewsCategory nc: list) {
				NewsCategoryReturn ncr = new NewsCategoryReturn();
				BeanUtils.copyProperties(nc,ncr);
				returnList.add(ncr);
			}
			CODEMSG=SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, returnList);
	}
	
    /**
     * 
     * @Title: single
     * @Description: 查询单个新闻宣传栏目
     * @Author tangsh
     * @DateTime 2019年12月16日 下午3:22:00
     * @param form
     * @return
     */
    @GetMapping(value="/single")
    public Object single(NewsCategory form){
		NewsCategoryReturn ncr = new NewsCategoryReturn();
		try {
			form=newsCategoryService.findByCode(form);
    		BeanUtils.copyProperties(form,ncr);
    		CODEMSG = SUCCESS;
    	}catch (Exception ex) {
    		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
    	}
    	return finish(CODEMSG,ncr);
	}
    
    /**
     * 
     * @Title: save
     * @Description: 新增单个新闻宣传栏目
     * @Author tangsh
     * @DateTime 2019年12月16日 下午3:23:01
     * @param form
     * @return
     */
	@PostMapping(value="/save")
    public Object save(@RequestBody NewsCategory form, HttpServletRequest request) {
			CODEMSG = SUCCESS;
    	try {
			User user = TokenUserUtil.generateUser(request, tokenParam);
			if(user==null){
				CODEMSG = USER_UNDEFINED;
				return finish(CODEMSG,null);
			}
			form.setUser_code(user.getUser_code());
			form.setUser_name(user.getUser_name());
			form.setNews_category_code(CodeUtil.getSystemCode("newsCategory"));
	    	Integer result = newsCategoryService.save(form);
	    	if(result<=0){
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
    
    /**
     * 
     * @Title: update
     * @Description: 更新单个新闻宣传栏目
     * @Author tangsh
     * @DateTime 2019年12月16日 下午3:28:01
     * @param form
     * @return
     */
	@PutMapping(value="/update")
    public Object update(@RequestBody NewsCategory form,HttpServletRequest request) {
    	CODEMSG = SUCCESS;
    	try {
			User user = TokenUserUtil.generateUser(request, tokenParam);
			if(user==null){
				CODEMSG = USER_UNDEFINED;
				return finish(CODEMSG,null);
			}
			form.setUser_code(user.getUser_code());
			form.setUser_name(user.getUser_name());
    		Integer result = newsCategoryService.update(form);
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
