package com.rs.controller;

import com.rs.po.NewsCategory;
import com.rs.po.returnPo.NewsCategoryReturn;
import com.rs.service.NewsCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/app/newsCategory")
public class NewsCategoryAppController extends BaseController {

	@Autowired
	private NewsCategoryService newsCategoryService;

	private Logger logger = LoggerFactory.getLogger(NewsCategoryAppController.class);
	
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
			for (NewsCategory nc:
				 list) {
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
	

	
}
