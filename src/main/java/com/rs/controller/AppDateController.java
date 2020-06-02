package com.rs.controller;
import com.rs.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
/**
 * 微信端时间控制层
 * @ClassName: AppDateController
 * @Description: 
 * @Author sven
 * @DateTime 2020年4月17日 上午9:28:33
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/app/date")
public class AppDateController extends BaseController {

	
	private Logger logger = LoggerFactory.getLogger(AppDateController.class);

	/**
	 * 请求服务器时间
	 * @Title: single
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月17日 上午9:30:27
	 * @param format
	 * @return
	 */
	@GetMapping(value = "/")
	public Object single(String format) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			if(StringUtils.isEmpty(format)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(format.equals(DateUtil.YYYY_MM_DD_FORMAT)) {
				map.put("server_time", DateUtil.getDateYearMonthDate());
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
