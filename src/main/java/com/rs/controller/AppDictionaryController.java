package com.rs.controller;

import com.rs.po.Dictionary;
import com.rs.po.returnPo.DictionaryReturn;
import com.rs.service.DictionaryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: DictionaryController
 * @Description: 业务字典控制层
 * @Author tangsh
 * @DateTime 2020年1月7日 上午10:50:17
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/app/dictionary")
public class AppDictionaryController extends BaseController {

	@Autowired
	private DictionaryService dictionaryService;
	
	private Logger logger = LoggerFactory.getLogger(AppDictionaryController.class);
	
	/**
	 * 
	 * @Title: list
	 * @Description: 查询列表信息
	 * @Author tangsh
	 * @DateTime 2020年6月2日 上午10:49:32
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(Dictionary form) {
		CODEMSG = ERROR;
		Map<String ,Map<String,List<Dictionary>>> map = new HashMap<>();
		try {
			map = dictionaryService.search(form);
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
	 * @Title: search
	 * @Description: 条件查询字典
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:23:41
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/search")
	public Object search(Dictionary form) {
		CODEMSG = ERROR;
		Map<String ,List<DictionaryReturn>> map = new HashMap<>();
		try {
			form.setPager_openset(1000);
			List<DictionaryReturn> dictionaryReturn  = dictionaryService.findByList_app(form);
			map.put("dictionary", dictionaryReturn);
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
	 * 查询办宴类型
	 * @Title: banquetType
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月15日 上午11:48:36
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/banquet/type")
	public Object banquetType(Dictionary form){
		Map<Object, Object> map = new HashMap<>();
		try {
			form.setState(1);
			form.setDictionary_group("办宴类型");
			
			List<Object> banquet_type_list = new ArrayList<Object>();
			List<Dictionary> query_list = dictionaryService.findByAll(form);
			Map<String,Object> result_map = null;
			for (Dictionary r : query_list) {
				result_map = new HashMap<String,Object>();
				result_map.put("banquet_type_key", r.getDictionary_module());
				result_map.put("banquet_type_val", r.getDictionary_name());
				banquet_type_list.add(result_map);
			}
			map.put("banquet_type_list", banquet_type_list);
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.error(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, map);
	}
	
	/**
	 * 查询加工场地卫生条件和卫生设施
	 * @Title: banquetType
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月15日 上午11:48:36
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/report/process")
	public Object reportProcess(Dictionary form){
		Map<Object, Object> map = new HashMap<>();
		try {
			form.setState(1);
			form.setDictionary_group("加工场地卫生条件和卫生设施");
			
			List<Object> report_process_list = new ArrayList<Object>();
			List<Dictionary> query_list = dictionaryService.findByAll(form);
			Map<String,Object> result_map = null;
			for (Dictionary r : query_list) {
				result_map = new HashMap<String,Object>();
				result_map.put("report_process_key", r.getDictionary_module());
				result_map.put("report_process_val", r.getDictionary_name());
				report_process_list.add(result_map);
			}
			map.put("report_process_list", report_process_list);
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.error(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, map);
	}
}
