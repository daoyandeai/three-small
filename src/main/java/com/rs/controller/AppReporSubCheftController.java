package com.rs.controller;
import com.rs.po.ReportSubChef;
import com.rs.service.ReportSubChefService;
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
 * 微信端报备帮厨控制层
 * @ClassName: AppReporSubCheftController
 * @Description: 
 * @Author sven
 * @DateTime 2020年4月15日 下午5:01:56
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/app/report/subChef")
public class AppReporSubCheftController extends BaseController {

	@Autowired
	private ReportSubChefService reportSubChefService;

	private Logger logger = LoggerFactory.getLogger(AppReporSubCheftController.class);

	/**
	 * 报备帮厨列表
	 * @Title: list
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月16日 上午9:26:30
	 * @param form
	 * @return
	 */
	@GetMapping(value="/list")
	public Object list(ReportSubChef form){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String report_code = form.getReport_code();
			if(StringUtils.isEmpty(report_code) || RegularUtil.isSpecialChar(report_code) || RegularUtil.matchLength(report_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}	
			logger.info("report_code:{}",report_code);
			map.put("report_sub_chef_list", reportSubChefService.findByAll_app(form));
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.error(ex.toString());
		}
		return finish(CODEMSG, map);
	}
	
	
}
