package com.rs.controller;
import com.rs.po.*;
import com.rs.service.FoodSourceSampleService;
import com.rs.util.CodeUtil;
import com.rs.util.TokenUserUtil;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
/**
 * 食材留样控制层
 * @ClassName: OpenFoodSourceSampleController
 * @Description: 
 * @Author sven
 * @DateTime 2020年4月2日 下午3:31:59
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/open/food/sample")
public class OpenFoodSourceSampleController extends BaseController {
	@Autowired
	private FoodSourceSampleService foodSourceSampleService;
	@Autowired
	private TokenParam tokenParam;
	
	private Logger logger = LoggerFactory.getLogger(FoodSourceController.class);

	/**
	 * 新增留样
	 * @Title: save
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月2日 上午11:26:38
	 * @param form
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody FoodSourceSample form ,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			User user = TokenUserUtil.generateUser(request, tokenParam);
			String company_code = user.getCompany_code();
			String user_name = user.getUser_name();
			String sample_person = form.getSample_person();
			//新增留样
			String sample_code = CodeUtil.getSystemCode("foodSourceSample");
			form.setSample_code(sample_code);
			form.setCompany_code(company_code);
			form.setSample_person(StringUtils.isEmpty(sample_person)?user_name:sample_person);
			Integer result = foodSourceSampleService.save(form);
			if(result > 0) {
				CODEMSG = SUCCESS;
			} else {
				map = null;
				CODEMSG = ERROR;
			}
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.error(ex.toString());
			return finish(CODEMSG, map);
		}
		return finish(CODEMSG, map);
	}

}
