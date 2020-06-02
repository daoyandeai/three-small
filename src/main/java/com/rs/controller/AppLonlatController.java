package com.rs.controller;
import com.rs.po.Lonlat;
import com.rs.service.LonlatService;
import com.rs.util.RegularUtil;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @ClassName: AppLonlatController
 * @Description: 坐标记录控制层
 * @Author tangsh
 * @DateTime 2020年5月9日 下午5:44:22
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/app/lonlat")
public class AppLonlatController extends BaseController {

	@Autowired
	private LonlatService lonlatService;
	
	private Logger logger = LoggerFactory.getLogger(AppLonlatController.class);

	/**
	 * 
	 * @Title: save
	 * @Description: 新增坐标数据
	 * @Author tangsh
	 * @DateTime 2020年5月9日 下午5:44:33
	 * @param lonlat
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody Lonlat lonlat) {
		try {
			lonlatService.save(lonlat);
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, null);
	}
	
	/**
	 * 
	 * @Title: list
	 * @Description: 查询坐标列表
	 * @Author tangsh
	 * @DateTime 2020年5月9日 下午5:52:15
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(Lonlat form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			if(StringUtils.isEmpty(form.getTable_code()) || RegularUtil.matchLength(form.getTable_code(), 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			map.put("lonlat_list", lonlatService.findByList(form));
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
