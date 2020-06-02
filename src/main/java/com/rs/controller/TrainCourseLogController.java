package com.rs.controller;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rs.po.TrainCourseLog;
import com.rs.po.User;
import com.rs.service.TrainCourseLogService;
import com.rs.service.UserService;
import com.rs.util.CodeUtil;
import com.rs.util.RegularUtil;
/**
 * 培训课程日志控制层
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/app/trainCourseLog")
public class TrainCourseLogController extends BaseController {
	
	@Autowired
	private TrainCourseLogService trainCourseLogService;
	@Autowired
	private UserService userService;
	private Logger logger = LoggerFactory.getLogger(TrainController.class);
	
	/**
	 * 新增课程学习日志
	 * @Title: save
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月18日 下午5:53:18
	 * @param form
	 * @param request
	 * @return
	 */
	@PostMapping(value="/save")
	public Object save(@RequestBody TrainCourseLog form){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			
			String train_code = form.getTrain_code();
			if(StringUtils.isEmpty(train_code) || RegularUtil.isSpecialChar(train_code) || RegularUtil.matchLength(train_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String train_course_code = form.getTrain_course_code();
			if(StringUtils.isEmpty(train_course_code) || RegularUtil.isSpecialChar(train_course_code) || RegularUtil.matchLength(train_course_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String user_code = form.getUser_code_chef();
			if(StringUtils.isEmpty(user_code) || RegularUtil.isSpecialChar(user_code) || RegularUtil.matchLength(user_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			User user = new User();
			user.setUser_code(user_code);
			user = userService.findByCode(user);
			form.setUser_code_chef(user.getUser_code());
			form.setUser_name_chef(user.getUser_name());
			form.setTrain_code(form.getTrain_code());
			form.setTrain_course_code(form.getTrain_course_code());
			form.setTrain_course_log_code(CodeUtil.getSystemCode("trainCourseLog"));
			int result = trainCourseLogService.save(form);
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
