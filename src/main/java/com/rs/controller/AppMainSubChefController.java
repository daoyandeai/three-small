package com.rs.controller;
import com.rs.po.CodeMsgEnum;
import com.rs.po.MainSubChef;
import com.rs.po.returnPo.MainSubChefReturn;
import com.rs.service.MainSubChefService;
import com.rs.util.CodeUtil;
import com.rs.util.RegularUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
/**
 * 微信端帮厨控制层
 * @ClassName: AppMainSubChefController
 * @Description: 
 * @Author sven
 * @DateTime 2020年4月15日 下午5:11:14
 */
@Transactional
@RestController
@RequestMapping("/app/chef/helper")
public class AppMainSubChefController extends BaseController {

	@Autowired
	private MainSubChefService mainSubChefService;
	
	private Logger logger = LoggerFactory.getLogger(AppMainSubChefController.class);

	
	/**
	 * 根据乡厨编码查询对应的帮厨
	 * @Title: list
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月16日 上午10:37:31
	 * @param form
	 * @return
	 */
	@GetMapping(value="/list")
	public Object list(MainSubChef form){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String user_code_mainchef = form.getUser_code_mainchef();
			if(StringUtils.isEmpty(user_code_mainchef) || RegularUtil.isSpecialChar(user_code_mainchef) || RegularUtil.matchLength(user_code_mainchef,25)) {
				CODEMSG = USER_CODE_UNVALID;
				return finish(CODEMSG, null);
			}	
			map.put("pager_count", mainSubChefService.findByCount(form));
			map.put("sub_chef_list", mainSubChefService.findByList_app(form));
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.error(ex.toString());
		}
		return finish(CODEMSG, map);
	}
	
	/**
	 * 新增帮厨
	 * @Title: save
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月16日 上午10:39:16
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody MainSubChef form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String user_code_mainchef = form.getUser_code_mainchef();
			if(StringUtils.isEmpty(user_code_mainchef) || RegularUtil.isSpecialChar(user_code_mainchef) || RegularUtil.matchLength(user_code_mainchef,25)) {
				CODEMSG = USER_CODE_UNVALID;
				return finish(CODEMSG, null);
			}	
			String user_name_subchef = form.getUser_name_subchef();
			if(StringUtils.isEmpty(user_name_subchef) || RegularUtil.isSpecialChar(user_name_subchef) || !RegularUtil.isLength(user_name_subchef,1,50)) {
				CODEMSG = CodeMsgEnum.USER_NAME_SUBCHEF_UNVALID;
				return finish(CODEMSG, null);
			}	
			String user_health_logo_subchef = form.getUser_health_logo_subchef();
			if(StringUtils.isEmpty(user_health_logo_subchef) || !RegularUtil.isImg(user_health_logo_subchef)) {
				CODEMSG = USER_HEALTH_LOGO_SUBCHEF_UNVALID;
				return finish(CODEMSG, null);
			}
			form.setUser_code_subchef(CodeUtil.getSystemCode("mainSubChef"));
			int result = mainSubChefService.save(form);
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
			logger.error(ex.toString());
		}
		return finish(CODEMSG, map);
	}
	
	/**
	 * 单条帮厨
	 * @Title: single
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月16日 上午10:46:07
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/single")
	public Object single(MainSubChef form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String user_code_subchef = form.getUser_code_subchef();
			if(StringUtils.isEmpty(user_code_subchef) || RegularUtil.isSpecialChar(user_code_subchef) || RegularUtil.matchLength(user_code_subchef,25)) {
				CODEMSG = USER_CODE_UNVALID;
				return finish(CODEMSG, null);
			}	
			MainSubChefReturn mscr = mainSubChefService.findByCode_app(form);
			if(!StringUtils.isEmpty(mscr)) {
				map.put("sub_chef", mscr);
			}
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.error(ex.toString());
		}
		return finish(CODEMSG, map);
	}
	
	
	/**
	 * 编辑帮厨
	 * @Title: update
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月16日 上午10:50:18
	 * @param form
	 * @return
	 */
	@PutMapping(value = "/update")
	public Object update(@RequestBody MainSubChef form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String user_code_subchef = form.getUser_code_subchef();
			if(StringUtils.isEmpty(user_code_subchef) || RegularUtil.isSpecialChar(user_code_subchef) || RegularUtil.matchLength(user_code_subchef,25)) {
				CODEMSG = USER_CODE_UNVALID;
				return finish(CODEMSG, null);
			}	
			String user_code_mainchef = form.getUser_code_mainchef();
			if(StringUtils.isEmpty(user_code_mainchef) || RegularUtil.isSpecialChar(user_code_mainchef) || RegularUtil.matchLength(user_code_mainchef,25)) {
				CODEMSG = USER_CODE_MAINCHEF_UNVALID;
				return finish(CODEMSG, null);
			}	
			String user_name_subchef = form.getUser_name_subchef();
			if(StringUtils.isEmpty(user_name_subchef) || RegularUtil.isSpecialChar(user_name_subchef) || !RegularUtil.isLength(user_name_subchef,1,50)) {
				CODEMSG = USER_NAME_SUBCHEF_UNVALID;
				return finish(CODEMSG, null);
			}	
			String user_health_logo_subchef = form.getUser_health_logo_subchef();
			if(StringUtils.isEmpty(user_health_logo_subchef) || !RegularUtil.isImg(user_health_logo_subchef)) {
				CODEMSG = USER_HEALTH_LOGO_SUBCHEF_UNVALID;
				return finish(CODEMSG, null);
			}
			int result = mainSubChefService.update(form);
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
	 * 删除帮厨
	 * @Title: delete
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月16日 上午11:02:17
	 * @param form
	 * @return
	 */
	@DeleteMapping(value="/delete")
	public Object delete(MainSubChef form){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String user_code_subchef = form.getUser_code_subchef();
			if(StringUtils.isEmpty(user_code_subchef) || RegularUtil.isSpecialChar(user_code_subchef) || RegularUtil.matchLength(user_code_subchef,25)) {
				CODEMSG = USER_CODE_UNVALID;
				return finish(CODEMSG, null);
			}	
			int result = mainSubChefService.delete(form);
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
			logger.error(ex.toString());
		}
		return finish(CODEMSG, map);
	}
}
