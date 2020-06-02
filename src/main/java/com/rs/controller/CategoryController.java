package com.rs.controller;

import com.rs.po.returnPo.CategoryReturn;
import com.rs.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 
 * @ClassName: CategoryController
 * @Description:品种明细控制层
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/category")
public class CategoryController extends BaseController {

	@Autowired
	private CategoryService categoryService;

	private Logger logger = LoggerFactory.getLogger(CategoryController.class);

	/**
	 * 
	 * @Title: all
	 * @Description: 查询分类所有明细
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:24:27
	 * @return
	 */
	@GetMapping(value = "/all")
	public Object all() {
		List<CategoryReturn> list = null;
		try {
			list = categoryService.all();
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, list);
	}

}
