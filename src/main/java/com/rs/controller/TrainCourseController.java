package com.rs.controller;
import com.rs.po.TokenParam;
import com.rs.po.Train;
import com.rs.po.TrainCourse;
import com.rs.po.TrainExam;
import com.rs.po.User;
import com.rs.po.returnPo.TrainCourseReturn;
import com.rs.po.returnPo.TrainExamReturn;
import com.rs.service.TrainCourseService;
import com.rs.service.TrainExamService;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
/**
 * 培训课程控制层
 * @ClassName: TrainCourseController
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月16日 下午6:01:16
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/trainCourse")
public class TrainCourseController extends BaseController {

	@Autowired
	private TrainService trainService;
	@Autowired
	private TrainCourseService trainCourseService;
	@Autowired
	private TrainExamService trainExamService;
	@Autowired
	private TokenParam tokenParam;
	
	private Logger logger = LoggerFactory.getLogger(TrainCourseController.class);

	/**
	 * 培训课程|培训试题
	 * @Title: list
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月12日 下午4:37:17
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(Train train,TrainCourse trainCourse,TrainExam trainExam) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String train_code = trainCourse.getTrain_code();
			if(RegularUtil.isSpecialChar(train_code) || RegularUtil.matchLength(train_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			//培训
			train = trainService.findByCode(train);
			map.put("user_code", train.getUser_code());
			//培训资料列表
			List<TrainCourse> tcList = trainCourseService.findByAll(trainCourse);
			List<TrainCourseReturn> tcrList = new ArrayList<TrainCourseReturn>();
			TrainCourseReturn tcr = null;
			if(!StringUtils.isEmpty(tcList) && tcList.size() > 0) {
				for (TrainCourse course : tcList) {
					tcr = new TrainCourseReturn();
					BeanUtils.copyProperties(course, tcr);
					tcrList.add(tcr);
				}
				map.put("train_course_list",tcrList);
			}
			//试卷
			trainExam = trainExamService.findByCode(trainExam); 
			TrainExamReturn trr = new TrainExamReturn();
			if(!StringUtils.isEmpty(trainExam)) {
				BeanUtils.copyProperties(trainExam, trr);
				map.put("train_exam",trr);
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
	 * 新增培训课程
	 * @Title: save
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月17日 下午3:39:52
	 * @param form
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody TrainCourse form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String train_code = form.getTrain_code();
			String train_course_title = form.getTrain_course_title();
			String train_course_content = form.getTrain_course_content();
			String train_course_period = form.getTrain_course_period();
			
			if(RegularUtil.isSpecialChar(train_code) || RegularUtil.matchLength(train_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			
			if(RegularUtil.isSpecialChar(train_course_title) || !RegularUtil.isLength(train_course_title,1,128)){
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(!RegularUtil.isLength(train_course_period,1,2) || !RegularUtil.isNumber(train_course_period)){
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(!RegularUtil.isLength(train_course_content,1,65535)){
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			
			form.setUser_code(user.getUser_code());
			form.setUser_name(user.getUser_name());
			form.setTrain_course_code(CodeUtil.getSystemCode("trainCourse"));
			int result = trainCourseService.save(form);
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
	 * 查询单条培训课程
	 * @Title: single
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月17日 下午3:40:14
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/single")
	public Object single(TrainCourse form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String train_course_code = form.getTrain_course_code();
			if(RegularUtil.isSpecialChar(train_course_code) || RegularUtil.matchLength(train_course_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			form = trainCourseService.findByCode(form);
			TrainCourseReturn tcr = new TrainCourseReturn();
			if(!StringUtils.isEmpty(form)) {
				BeanUtils.copyProperties(form, tcr);
			}
			//单条培训资料
			map.put("train_course", tcr);
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, map);
	}
	
	/**
	 * 更新培训课程
	 * @Title: update
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月17日 下午3:40:20
	 * @param form
	 * @param request
	 * @return
	 */
	@PutMapping(value="/update")
	public Object update(@RequestBody TrainCourse form,HttpServletRequest request){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String train_course_code = form.getTrain_course_code();
			String train_course_title = form.getTrain_course_title();
			String train_course_content = form.getTrain_course_content();
			String Train_course_period = form.getTrain_course_period();
			
			if(RegularUtil.isSpecialChar(train_course_code) || RegularUtil.matchLength(train_course_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			
			if(RegularUtil.isSpecialChar(train_course_title)||!RegularUtil.isLength(train_course_title,1,128)){
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(!RegularUtil.isLength(Train_course_period,1,2) || !RegularUtil.isNumber(Train_course_period)){
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(!RegularUtil.isLength(train_course_content,1,65535)){
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			
			User user = TokenUserUtil.generateUser(request, tokenParam);
			
			form.setUser_code(user.getUser_code());
			form.setUser_name(user.getUser_name());
			int result = trainCourseService.update(form);
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
	 * 更新培训课程状态
	 * @Title: updateState
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月17日 上午10:29:00
	 * @param form
	 * @return
	 */
	@PutMapping(value="/state/update")
	public Object updateState(@RequestBody TrainCourse form){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String train_course_code = form.getTrain_course_code();
			int train_course_state = form.getTrain_course_state();
			if(RegularUtil.isSpecialChar(train_course_code) || RegularUtil.matchLength(train_course_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(!(train_course_state == 1 || train_course_state == 2)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int result = trainCourseService.update(form);
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
