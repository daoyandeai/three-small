package com.rs.controller;
import com.rs.po.Category;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.service.CategoryService;
import com.rs.util.CodeUtil;
import com.rs.util.RegularUtil;
import com.rs.util.TokenUserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
/**
 * 品种控制层
 * @ClassName: OpenCategoryController
 * @Description: 
 * @Author sven
 * @DateTime 2020年4月1日 上午9:57:42
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/open/category")
public class OpenCategoryController extends BaseController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private TokenParam tokenParam;
	
	private Logger logger = LoggerFactory.getLogger(OpenCategoryController.class);

	/**
	 * 查询分类_分页
	 * @Title: list
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月1日 上午11:38:55
	 * @param form
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(Category form,HttpServletRequest request) {
		Map<String,Object> map = new HashMap<>();
		try {
			int owner_type = form.getOwner_type();
			User user = TokenUserUtil.generateUser(request, tokenParam);
			switch (owner_type) {
			case 1:
				//全部
				form.setUser_code_add(null);
				break;
			case 2:
				//自己
				form.setUser_code_add(user.getUser_code());
				break;
			}
			form.setState(1);
			List<Category> category_list = categoryService.findByList(form);
			//重新组装返回json格式
			List<Object> cate_list = new ArrayList<Object>();
			Map<String, Object> cate_map = null;
			for (Category category : category_list) {
				cate_map = new HashMap<String, Object>();
				cate_map.put("category_code", category.getCategory_code());
				cate_map.put("category_name", category.getCategory_name());
				cate_list.add(cate_map);
			}
			map.put("category_list",cate_list);
			map.put("pager_count",categoryService.findByCount(form));
			CODEMSG = SUCCESS ;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.error(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, map);
	}
	
	/**
	 * 新增品种
	 * @Title: save
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:35:40
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody Category form ,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String category_name = form.getCategory_name();
			if(StringUtils.isEmpty(category_name) ) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(RegularUtil.isSpecialChar(category_name)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_code_add(user.getUser_code());
			form.setUser_name_add(user.getUser_name());
			form.setCategory_code(CodeUtil.getSystemCode("category"));
			int count = categoryService.findByExist(form);
			if (count > 0) {
				CODEMSG = CATEGORY_NAME_EXIST;
				return finish(CODEMSG, null);
			}
			int result = categoryService.save(form);
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
	 * 查询品种
	 * @Title: single
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月1日 上午11:40:09
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/single")
	public Object single(Category form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String category_code = form.getCategory_code();
			if (StringUtils.isEmpty(category_code) || RegularUtil.isSpecialChar(category_code) || RegularUtil.matchLength(category_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			form = categoryService.findByCode(form);
			if (!StringUtils.isEmpty(form)) {
				Map<String, Object> cate_map = new HashMap<String, Object>();
				cate_map.put("category_code", form.getCategory_code());
				cate_map.put("category_name", form.getCategory_name());
				map.put("category", cate_map);
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
	 * 编辑品种
	 * @Title: update
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月1日 上午11:40:18
	 * @param form
	 * @param request
	 * @return
	 */
	@PutMapping(value = "/update")
	public Object update(@RequestBody Category form, HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String category_code = form.getCategory_code();
			if (StringUtils.isEmpty(category_code) || RegularUtil.isSpecialChar(category_code) || RegularUtil.matchLength(category_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String category_name = form.getCategory_name();
			if(StringUtils.isEmpty(category_name) ) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(RegularUtil.isSpecialChar(category_name)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			int count = categoryService.findByExist(form);
			if (count > 0) {
				CODEMSG = PAGE_CONFIG_NAME_EXIST;
				return finish(CODEMSG, null);
			}
			form.setUser_code_update(user.getUser_code());
			form.setUser_name_update(user.getUser_name());
			int result = categoryService.update(form);
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
	 * 查询品种是否存在
	 * @Title: exist
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月1日 上午11:40:30
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/exist")
	public Object exist(Category form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String category_code = form.getCategory_code();
			if (StringUtils.isEmpty(category_code) || RegularUtil.isSpecialChar(category_code) || RegularUtil.matchLength(category_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String category_name = form.getCategory_name();
			if(StringUtils.isEmpty(category_name) ) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(RegularUtil.isSpecialChar(category_name)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			int count = categoryService.findByExist(form);
			if (count == 0) {
				CODEMSG = SUCCESS;
			}else {
				map = null;
				CODEMSG = CATEGORY_NAME_EXIST;
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
	 * 更新品种状态
	 * @Title: updateState
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月1日 上午11:40:43
	 * @param form
	 * @return
	 */
	@PutMapping(value = "/state/update")
	public Object updateState(@RequestBody Category form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String category_code = form.getCategory_code();
			int state = form.getState();
			if (StringUtils.isEmpty(category_code) || RegularUtil.isSpecialChar(category_code) || RegularUtil.matchLength(category_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if (!(state == 1 || state == 2)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int result = categoryService.update(form);
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
