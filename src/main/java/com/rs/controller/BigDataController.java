package com.rs.controller;

import com.rs.po.Company;
import com.rs.po.FoodSource;
import com.rs.po.FoodSourceDetail;
import com.rs.po.Region;
import com.rs.po.Sensor;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.po.returnPo.BigData;
import com.rs.service.BigDataService;
import com.rs.util.RegularUtil;
import com.rs.util.TokenUserUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @ClassName: BigDataController
 * @Description: 云大数据统计控制层
 * @Author tangsh
 * @DateTime 2020年1月9日 下午2:40:32
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/bigData")
public class BigDataController extends BaseController {

	@Autowired
	private BigDataService bigDataService;
	@Autowired
	private TokenParam tokenParam;

	private Logger logger = LoggerFactory.getLogger(BigDataController.class);

	/**
	 * 
	 * @Title: userTypeCount
	 * @Description: 统计用户/企业类型数据
	 * @Author tangsh
	 * @DateTime 2020年1月9日 下午3:35:35
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/userTypeCount")
	public Object userTypeCount(HttpServletRequest request) {
		List<BigData> bdlist=null;
		try {
			User user = TokenUserUtil.generateUser(request, tokenParam);
			bdlist=bigDataService.getUserTypeCount(user);
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, bdlist);
	}
	
	/**
	 * 
	 * @Title: cpList
	 * @Description: 备案巡查统计
	 * @Author tangsh
	 * @DateTime 2020年3月26日 上午9:48:18
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/cpList")
	public Object cpList(HttpServletRequest request) {
		List<BigData> bdlist=null;
		try {
			User user = TokenUserUtil.generateUser(request, tokenParam);
			bdlist=bigDataService.cpList(user);
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, bdlist);
	}
	
	/**
	 * 
	 * @Title: companyByCity
	 * @Description: 统计各区域（市、区）三小备案数据
	 * @Author tangsh
	 * @DateTime 2020年1月9日 下午5:33:05
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/companyByCity")
	public Object companyByCity(Company form,HttpServletRequest request) {
		List<BigData> clist=null;
		try {
			User user = TokenUserUtil.generateUser(request, tokenParam);
			clist=bigDataService.companyByCity(user,form);
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, clist);
	}
	
	/**
	 * 
	 * @Title: mapCompany
	 * @Description: 备案地图数据
	 * @Author tangsh
	 * @DateTime 2020年1月9日 下午5:41:17
	 * @param form
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/mapCompany")
	public Object mapCompany(Company form,HttpServletRequest request) {
		List<BigData> clist=null;
		try {
			User user = TokenUserUtil.generateUser(request, tokenParam);
			clist=bigDataService.mapCompany(user,form);
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, clist);
	}
	
	/**
	 * 监控设备统计
	 * @Title: monitorDevice
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月18日 下午2:46:38
	 * @param form
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/monitorDevice")
	public Object monitorDevice(Sensor form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			User user = TokenUserUtil.generateUser(request, tokenParam);
			List<BigData> monitor_list  = bigDataService.findSensorByUser(user,form);
			map.put("monitor_list",monitor_list);
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
	 * 培训统计
	 * @Title: train
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月23日 上午11:24:30
	 * @param form
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/train")
	public Object train(Region form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			User user = TokenUserUtil.generateUser(request, tokenParam);
			List<Object> train_list  = bigDataService.findTrainByUser(user,form);
			map.put("train_list",train_list);
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
	 * 大数据-溯源统计
	 * @Title: foodSourceCompanyList
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月20日 下午5:51:14
	 * @param form
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/company/food/source")
	public Object foodSourceCompanyList(Company form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			User user = TokenUserUtil.generateUser(request, tokenParam);
			List<Object> food_source_list  = bigDataService.findFoodSourceByUser(form,user,map);
			map.put("company_food_source_list",food_source_list);
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
	 * 大数据-溯源列表
	 * @Title: foodSourceList
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月20日 下午5:52:21
	 * @param form
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/food/source/list")
	public Object foodSourceList(FoodSource form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String company_code = form.getCompany_code();
			if(StringUtils.isEmpty(company_code) || RegularUtil.matchLength(company_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			List<Object> food_source_list  = bigDataService.findFoodSourceByCompany(form,map);
			map.put("food_source_list",food_source_list);
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
	 * 大数据-溯源列表
	 * @Title: foodSourceList
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月20日 下午5:52:21
	 * @param form
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/food/source/detail")
	public Object foodSourceDetail(FoodSourceDetail form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String foodsource_code = form.getFoodsource_code();
			if(StringUtils.isEmpty(foodsource_code) || RegularUtil.matchLength(foodsource_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			List<Object> food_source_deital_list  = bigDataService.findFoodSourceDetail(foodsource_code);
			map.put("food_source_deital_list",food_source_deital_list);
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, map);
	}
}
