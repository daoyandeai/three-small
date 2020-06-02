package com.rs.controller;
import com.alibaba.fastjson.JSON;
import com.rs.po.Advice;
import com.rs.po.returnPo.AdviceReturn;
import com.rs.po.returnPo.DeviceReturn;
import com.rs.service.AdviceService;
import com.rs.util.RegularUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 执法记录控制层
 * @ClassName: AdvideController
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月28日 下午2:10:31
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/advice")
public class AdvideController extends BaseController {

	@Autowired
	private AdviceService adviceService;
	
	private Logger logger = LoggerFactory.getLogger(AdvideController.class);

	/**
	 * 执法建议列表
	 * @Title: list
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月28日 下午2:14:57
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(Advice form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
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
			String company_code = form.getCompany_code();
			if(StringUtils.isEmpty(company_code) || RegularUtil.matchLength(company_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			map.put("pager_count", adviceService.findByCount(form));
			List<Advice> dList = adviceService.findByList(form);
			List<AdviceReturn> drList = new ArrayList<AdviceReturn>();
			AdviceReturn dr = null;
			for (Advice a : dList) {
				dr = new AdviceReturn();
				BeanUtils.copyProperties(a, dr);
				dr.setImgs_url(JSON.toJSON(dr.getImgs_url()));
				drList.add(dr);
			}
			map.put("advice_list",drList);
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
	 * 单条执法建议
	 * @Title: single
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月28日 下午2:15:56
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/single")
	public Object single(Advice form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String advice_code = form.getAdvice_code();
			if(StringUtils.isEmpty(advice_code) || RegularUtil.matchLength(advice_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			form = adviceService.findByCode(form);
			if(!StringUtils.isEmpty(form)) {
				DeviceReturn dr = new DeviceReturn();
				BeanUtils.copyProperties(form, dr);
				map.put("advice", dr);
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
	
	
}
