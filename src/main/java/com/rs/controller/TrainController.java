package com.rs.controller;
import com.rs.po.TokenParam;
import com.rs.po.Train;
import com.rs.po.User;
import com.rs.po.returnPo.TrainReturn;
import com.rs.service.TrainService;
import com.rs.util.CodeUtil;
import com.rs.util.RegularUtil;
import com.rs.util.TokenUserUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
/**
 * 培训控制层
 * @ClassName: TrainController
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月12日 下午4:31:00
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/train")
public class TrainController extends BaseController {

	@Autowired
	private TrainService trainService;
	@Autowired
	private TokenParam tokenParam;
	
	private Logger logger = LoggerFactory.getLogger(TrainController.class);

	/**
	 * 分页查询培训列表
	 * @Title: list
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月12日 下午4:37:17
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(Train form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String train_year = form.getTrain_year();
			String train_title = form.getTrain_title();
			int owner_type = form.getOwner_type();
			if(!StringUtils.isEmpty(train_year) && !RegularUtil.isYear(train_year)) {
				CODEMSG = YEAR_UNVALID;
				return finish(CODEMSG, null);
			}
			if(!StringUtils.isEmpty(train_title) && RegularUtil.isSpecialChar(train_title)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			switch (owner_type) {
			case 1:
				//全部
				form.setUser_code(null);
				break;
			case 2:
				//自己
				form.setUser_code(user.getUser_code());
				break;
			}
			
			//培训区域
			String train_province = form.getTrain_province();
			String train_city = form.getTrain_city();
			String train_area = form.getTrain_area();
			String train_town = form.getTrain_town();
			String train_vill = form.getTrain_vill();
			String addrInfo = "";
			//登录用户区域
			String user_province = user.getUser_province();
			String user_city = user.getUser_city();
			String user_area = user.getUser_area();
			String user_town = user.getUser_town(); 
			String user_vill = user.getUser_vill();
			
			if(StringUtils.isEmpty(train_province)) {
				form.setTrain_province(user_province);
				addrInfo = addrInfo+(StringUtils.isEmpty(user_province)?"":user_province);
			}else {
				addrInfo = addrInfo + train_province;
			}
			
			if(StringUtils.isEmpty(train_city)) {
				form.setTrain_city(user_city);
				addrInfo = addrInfo+(StringUtils.isEmpty(user_city)?"":user_city);
			}else {
				addrInfo = addrInfo + train_city;
			}
			
			if(StringUtils.isEmpty(train_area)) {
				form.setTrain_area(user_area);
				addrInfo = addrInfo+(StringUtils.isEmpty(user_area)?"":user_area);
			}else {
				addrInfo = addrInfo + train_area;
			}
			
			if(StringUtils.isEmpty(train_town)) {
				form.setTrain_town(user_town);
				addrInfo = addrInfo+(StringUtils.isEmpty(user_town)?"":user_town);
			}else {
				addrInfo = addrInfo + train_town;
			}
			
			if(StringUtils.isEmpty(train_vill)) {
				form.setTrain_vill(user_vill);
				addrInfo = addrInfo+(StringUtils.isEmpty(user_vill)?"":user_vill);
			}else {
				addrInfo = addrInfo + train_vill;
			}
			
			form.setAddr_info(addrInfo);
			if(user.getUser_type().equals("超级管理人员")) {
				map.put("train_list", trainService.findByList(form));
				map.put("pager_count", trainService.findByCount(form));
			}else{
				map.put("train_list", trainService.findListByAuth(form));
				map.put("pager_count", trainService.findCountByAuth(form));
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
	 * 添加培训
	 * @Title: save
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月16日 上午11:27:46
	 * @param form
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody Train form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String train_year = form.getTrain_year();
			String train_title = form.getTrain_title();
			String train_quarter =  form.getTrain_quarter();
			if(StringUtils.isEmpty(train_year) || !RegularUtil.isYear(train_year)) {
				CODEMSG = YEAR_UNVALID;
				return finish(CODEMSG, null);
			}
			if(StringUtils.isEmpty(train_title) || RegularUtil.isSpecialChar(train_title) || !RegularUtil.isLength(train_title, 1, 128)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(StringUtils.isEmpty(train_quarter) || RegularUtil.isSpecialChar(train_quarter) || !RegularUtil.isLength(train_quarter, 1,50)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			
			User user = TokenUserUtil.generateUser(request, tokenParam);
			//培训区域
			String train_province = form.getTrain_province();
			String train_city = form.getTrain_city();
			String train_area = form.getTrain_area();
			String train_town = form.getTrain_town();
			String train_vill = form.getTrain_vill();
			//登录用户区域
			String user_province = user.getUser_province();
			String user_city = user.getUser_city();
			String user_area = user.getUser_area();
			String user_town = user.getUser_town(); 
			String user_vill = user.getUser_vill();
			
			if(StringUtils.isEmpty(train_province)) {
				form.setTrain_province(user_province);
			}
			
			if(StringUtils.isEmpty(train_city)) {
				form.setTrain_city(user_city);
			}
			
			if(StringUtils.isEmpty(train_area)) {
				form.setTrain_area(user_area);
			}
			
			if(StringUtils.isEmpty(train_town)) {
				form.setTrain_town(user_town);
			}
			
			if(StringUtils.isEmpty(train_vill)) {
				form.setTrain_vill(user_vill);
			}
			
			form.setUser_code(user.getUser_code());
			form.setUser_name(user.getUser_name());
			form.setTrain_code(CodeUtil.getSystemCode("train"));
			
			int result = trainService.save(form);
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
	 * 查询单条培训
	 * @Title: save
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月16日 上午11:27:46
	 * @param form
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/single")
	public Object single(Train form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String train_code = form.getTrain_code();
			if(StringUtils.isEmpty(train_code) || RegularUtil.isSpecialChar(train_code) || RegularUtil.matchLength(train_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			form = trainService.findByCode(form);
			TrainReturn tr = new TrainReturn();
			if(!StringUtils.isEmpty(form)) {
				BeanUtils.copyProperties(form, tr);
			}
			map.put("train", tr);
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, map);
	}
	
	/**
	 * 编辑培训
	 * @Title: save
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月16日 上午11:27:46
	 * @param form
	 * @param request
	 * @return
	 */
	@PutMapping(value = "/update")
	public Object update(@RequestBody Train form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String train_code = form.getTrain_code();
			String train_year = form.getTrain_year();
			String train_title = form.getTrain_title();
			String train_quarter =  form.getTrain_quarter();
			if(StringUtils.isEmpty(train_code) || RegularUtil.isSpecialChar(train_code) || RegularUtil.matchLength(train_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(StringUtils.isEmpty(train_year) || !RegularUtil.isYear(train_year)) {
				CODEMSG = YEAR_UNVALID;
				return finish(CODEMSG, null);
			}
			if(StringUtils.isEmpty(train_title) || RegularUtil.isSpecialChar(train_title) || !RegularUtil.isLength(train_title, 1, 128)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(StringUtils.isEmpty(train_quarter) || RegularUtil.isSpecialChar(train_quarter) || !RegularUtil.isLength(train_quarter, 1,50)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			
			//培训区域
			String train_province = form.getTrain_province();
			String train_city = form.getTrain_city();
			String train_area = form.getTrain_area();
			String train_town = form.getTrain_town();
			String train_vill = form.getTrain_vill();
			//登录用户区域
			String user_province = user.getUser_province();
			String user_city = user.getUser_city();
			String user_area = user.getUser_area();
			String user_town = user.getUser_town(); 
			String user_vill = user.getUser_vill();
			
			if(StringUtils.isEmpty(train_province)) {
				form.setTrain_province(user_province);
			}
			
			if(StringUtils.isEmpty(train_city)) {
				form.setTrain_city(user_city);
			}
			
			if(StringUtils.isEmpty(train_area)) {
				form.setTrain_area(user_area);
			}
			
			if(StringUtils.isEmpty(train_town)) {
				form.setTrain_town(user_town);
			}
			
			if(StringUtils.isEmpty(train_vill)) {
				form.setTrain_vill(user_vill);
			}
			
			form.setUser_code(user.getUser_code());
			form.setUser_name(user.getUser_name());
			
			int result = trainService.update(form);
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
	 * 更新培训状态
	 * @Title: updateState
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月16日 下午2:47:52
	 * @param form
	 * @return
	 */
	@PutMapping(value="/state/update")
	public Object updateState(@RequestBody Train form){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String train_code = form.getTrain_code();
			int train_state = form.getTrain_state();
			if(StringUtils.isEmpty(train_code) || RegularUtil.isSpecialChar(train_code) || RegularUtil.matchLength(train_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(!(train_state == 1 || train_state == 2)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			
			int result = trainService.updateStatus(form);
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
