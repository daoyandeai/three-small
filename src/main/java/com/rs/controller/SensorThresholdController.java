package com.rs.controller;
import com.rs.po.SensorThreshold;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.po.returnPo.SensorThresholdReturn;
import com.rs.service.SensorThresholdService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
/**
 * 传感器预警项控制层
 * @ClassName: SensorController
 * @Description: 
 * @Author sven
 * @DateTime 2020年1月14日 上午11:54:11
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/sensorThreshold")
public class SensorThresholdController extends BaseController {

	@Autowired
	private SensorThresholdService sensorThresholdService;
	@Autowired
	private TokenParam tokenParam;
	
	private Logger logger = LoggerFactory.getLogger(SensorThresholdController.class);

	/**
	 * 查询传感器预警项列表
	 * @Title: list
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月23日 下午5:43:20
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(SensorThreshold form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String sensor_name = form.getSensor_name();
			if(!StringUtils.isEmpty(sensor_name) && RegularUtil.isSpecialChar(sensor_name)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String sensor_enname = form.getSensor_enname();
			if(!StringUtils.isEmpty(sensor_enname) && RegularUtil.isSpecialChar(sensor_enname)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int pager_offset = form.getPager_offset();
			if(StringUtils.isEmpty(pager_offset) || !RegularUtil.isNumber(pager_offset+"")) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int pager_openset = form.getPager_openset();
			if(StringUtils.isEmpty(pager_openset) || !RegularUtil.isNumber(pager_openset+"")) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			map.put("pager_count", sensorThresholdService.findByCount(form));
			List<SensorThreshold> dList = sensorThresholdService.findByList(form);
			List<SensorThresholdReturn> srList = new ArrayList<SensorThresholdReturn>();
			SensorThresholdReturn sr = null;
			for (SensorThreshold d : dList) {
				sr = new SensorThresholdReturn();
				BeanUtils.copyProperties(d, sr);
				srList.add(sr);
			}
			map.put("sensor_threshold_list",srList);
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
	 * 添加传感器预警项
	 * @Title: save
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月23日 下午6:15:56
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody SensorThreshold form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String sensor_name = form.getSensor_name();
			if(!StringUtils.isEmpty(sensor_name) && RegularUtil.isSpecialChar(sensor_name)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String sensor_enname = form.getSensor_enname();
			if(!StringUtils.isEmpty(sensor_enname) && RegularUtil.isSpecialChar(sensor_enname)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			Double max_value = form.getMax_value();
			if(!StringUtils.isEmpty(max_value) && !RegularUtil.isDouble(max_value+"")) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			Double min_value = form.getMin_value();
			if(!StringUtils.isEmpty(min_value) && !RegularUtil.isDouble(min_value+"")) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			Integer sync_interval = form.getSync_interval();
			if(!StringUtils.isEmpty(sync_interval) && !RegularUtil.isNumber(sync_interval+"")) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			int count = sensorThresholdService.findByExist(form);
			if (count > 0) {
				CODEMSG = SENSOR_THRESHOLD_EXIST;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_code_add(user.getUser_code());
			form.setUser_name_add(user.getUser_name());
			form.setSensor_threshold_code(CodeUtil.getSystemCode("sensorThreshold"));
			int result = sensorThresholdService.save(form);
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
	 * 查询传感器预警项
	 * @Title: single
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月23日 下午6:15:50
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/single")
	public Object single(SensorThreshold form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String sensor_threshold_code = form.getSensor_threshold_code();
			if(StringUtils.isEmpty(sensor_threshold_code) || RegularUtil.matchLength(sensor_threshold_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			
			form = sensorThresholdService.findByCode(form);
			if(!StringUtils.isEmpty(form)) {
				SensorThresholdReturn sr = new SensorThresholdReturn();
				BeanUtils.copyProperties(form, sr);
				map.put("sensor_threshold", sr);
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
	 * 编辑传感器预警项
	 * @Title: save
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月23日 下午6:15:56
	 * @param form
	 * @return
	 */
	@PutMapping(value = "/update")
	public Object update(@RequestBody SensorThreshold form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String sensor_threshold_code = form.getSensor_threshold_code();
			if(StringUtils.isEmpty(sensor_threshold_code) || RegularUtil.matchLength(sensor_threshold_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String sensor_name = form.getSensor_name();
			if(!StringUtils.isEmpty(sensor_name) && RegularUtil.isSpecialChar(sensor_name)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String sensor_enname = form.getSensor_enname();
			if(!StringUtils.isEmpty(sensor_enname) && RegularUtil.isSpecialChar(sensor_enname)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			Double max_value = form.getMax_value();
			if(!StringUtils.isEmpty(max_value) && !RegularUtil.isDouble(max_value+"")) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			Double min_value = form.getMin_value();
			if(!StringUtils.isEmpty(min_value) && !RegularUtil.isDouble(min_value+"")) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			Integer sync_interval = form.getSync_interval();
			if(!StringUtils.isEmpty(sync_interval) && !RegularUtil.isNumber(sync_interval+"")) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			int count = sensorThresholdService.findByExist(form);
			if (count > 0) {
				CODEMSG = SENSOR_THRESHOLD_EXIST;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_code_update(user.getUser_code());
			form.setUser_name_update(user.getUser_name());
			int result = sensorThresholdService.update(form);
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
	 * 查询传感器预警项是否存在
	 * 
	 * @Title: single
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年12月30日 上午10:47:27
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/exist")
	public Object exist(SensorThreshold form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String sensor_name = form.getSensor_name();
			if (!StringUtils.isEmpty(sensor_name) && RegularUtil.isSpecialChar(sensor_name)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String sensor_enname = form.getSensor_enname();
			if (!StringUtils.isEmpty(sensor_enname) && RegularUtil.isSpecialChar(sensor_enname)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int count = sensorThresholdService.findByExist(form);
			if (count == 0) {
				CODEMSG = SUCCESS;
			}else {
				map = null;
				CODEMSG = SENSOR_THRESHOLD_EXIST;
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
