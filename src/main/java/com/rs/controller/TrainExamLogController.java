package com.rs.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rs.po.Train;
import com.rs.po.TrainCourse;
import com.rs.po.TrainCourseLog;
import com.rs.po.TrainExam;
import com.rs.po.TrainExamLog;
import com.rs.po.TrainExamTopic;
import com.rs.po.TrainExamTopicLog;
import com.rs.po.User;
import com.rs.po.returnPo.TrainCourseReturn;
import com.rs.po.returnPo.TrainExamLogReturn;
import com.rs.po.returnPo.TrainExamReturn;
import com.rs.po.returnPo.TrainExamTopicLogReturn;
import com.rs.po.returnPo.TrainExamTopicReturn;
import com.rs.service.MessLogService;
import com.rs.service.TrainCourseLogService;
import com.rs.service.TrainCourseService;
import com.rs.service.TrainExamLogService;
import com.rs.service.TrainExamService;
import com.rs.service.TrainExamTopicLogService;
import com.rs.service.TrainExamTopicService;
import com.rs.service.TrainService;
import com.rs.service.UserService;
import com.rs.util.CodeUtil;
import com.rs.util.RegularUtil;
/**
 * 培训考试日志控制层
 * @ClassName: TrainExamLogController
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月18日 上午11:33:38
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/app/trainExamLog")
public class TrainExamLogController extends BaseController{
	
	@Autowired
	private TrainExamLogService trainExamLogService;
	@Autowired
	private TrainService trainService;
	@Autowired
	private TrainCourseService trainCourseService;
	@Autowired
	private TrainExamService trainExamService;
	@Autowired
	private TrainExamTopicService trainExamTopicService;
	@Autowired
	private TrainExamTopicLogService trainExamTopicLogService;
	@Autowired
	private TrainCourseLogService trainCourseLogService;
	@Autowired
	private UserService userService;
	@Autowired
	private MessLogService messLogService;
	
	private Logger logger = LoggerFactory.getLogger(TrainExamLogController.class);
	
	
	/**
	 * 根据用户查询培训列表
	 * @Title: findByList
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月18日 下午2:46:17
	 * @param form
	 * @param trainForm
	 * @param httpSession
	 * @param model
	 * @param request
	 * @return
	 */
	@GetMapping(value="/train/list")
	public Object findTrainlist(TrainExamLog form,User user){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String user_code = user.getUser_code();
			if(StringUtils.isEmpty(user_code) || RegularUtil.isSpecialChar(user_code) || RegularUtil.matchLength(user_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			//查询
			form.setUser_code_chef(user_code);
			form.setTrain_exam_standard(0);
			Train trai = new Train();
			List<Train> yearTrainList = trainService.findAllGroupByYear(trai);
			
			//重新组合对象
			List<Object> trianList = new ArrayList<Object>();
			Map<String, Object> trainMap = null;
			for(Train train : yearTrainList) {
				train.setTrain_state(1);
				
				//培训区域
				String train_province = train.getTrain_province();
				String train_city = train.getTrain_city();
				String train_area = train.getTrain_area();
				String train_town = train.getTrain_town();
				String train_vill = train.getTrain_vill();
				String addrInfo = "";
				//登录用户区域
				user = userService.findByCode(user);
				String user_province = user.getUser_province();
				String user_city = user.getUser_city();
				String user_area = user.getUser_area();
				String user_town = user.getUser_town(); 
				String user_vill = user.getUser_vill();
				
				if(StringUtils.isEmpty(train_province)) {
					train.setTrain_province(user_province);
					addrInfo = addrInfo+(StringUtils.isEmpty(user_province)?"":user_province);
				}else {
					addrInfo = addrInfo + train_province;
				}
				
				if(StringUtils.isEmpty(train_city)) {
					train.setTrain_city(user_city);
					addrInfo = addrInfo+(StringUtils.isEmpty(user_city)?"":user_city);
				}else {
					addrInfo = addrInfo + train_city;
				}
				
				if(StringUtils.isEmpty(train_area)) {
					train.setTrain_area(user_area);
					addrInfo = addrInfo+(StringUtils.isEmpty(user_area)?"":user_area);
				}else {
					addrInfo = addrInfo + train_area;
				}
				
				if(StringUtils.isEmpty(train_town)) {
					train.setTrain_town(user_town);
					addrInfo = addrInfo+(StringUtils.isEmpty(user_town)?"":user_town);
				}else {
					addrInfo = addrInfo + train_town;
				}
				
				if(StringUtils.isEmpty(train_vill)) {
					train.setTrain_vill(user_vill);
					addrInfo = addrInfo+(StringUtils.isEmpty(user_vill)?"":user_vill);
				}else {
					addrInfo = addrInfo + train_vill;
				}
				
				train.setAddr_info(addrInfo);
				trainMap = new HashMap<String, Object>();
				trainMap.put("train_province", train.getTrain_province());
				trainMap.put("train_city", train.getTrain_city());
				trainMap.put("train_area", train.getTrain_area());
				trainMap.put("train_town", train.getTrain_town());
				trainMap.put("train_vill", train.getTrain_vill());
				trainMap.put("train_year", train.getTrain_year());
				
				List<Train> trainSubList = trainService.findAllByAuth(train);
				TrainExamLog childTrainExam = null;
				List<Object> trianLogList = new ArrayList<Object>();
				Map<String, Object> trainLogMap = null;
				for(Train subTrain : trainSubList) {
					childTrainExam = new TrainExamLog();
					childTrainExam.setTrain_code(subTrain.getTrain_code());
					childTrainExam.setUser_code_chef(user_code);
					if(trainExamLogService.findIsPass(childTrainExam) > 0) {
						childTrainExam.setTrain_exam_standard(2);
						if(trainExamLogService.findIsPass(childTrainExam) > 0) {
							subTrain.setIs_pass(2);//通过
						}else {
							subTrain.setIs_pass(1);//未通过
						}
					}else {
						subTrain.setIs_pass(0);//未考试
					}
					
					trainLogMap = new HashMap<String, Object>();
					trainLogMap.put("train_code", subTrain.getTrain_code());
					trainLogMap.put("train_title", subTrain.getTrain_title());
					trainLogMap.put("train_province", subTrain.getTrain_province());
					trainLogMap.put("train_city", subTrain.getTrain_city());
					trainLogMap.put("train_area", subTrain.getTrain_area());
					trainLogMap.put("train_town", subTrain.getTrain_town());
					trainLogMap.put("train_vill", subTrain.getTrain_vill());
					trainLogMap.put("train_year", subTrain.getTrain_year());
					trainLogMap.put("train_quarter", subTrain.getTrain_quarter());
					trainLogMap.put("is_pass", subTrain.getIs_pass());
					trianLogList.add(trainLogMap);
					trainMap.put("train_log_list", trianLogList);
				}
				
				trianList.add(trainMap);
				map.put("train_list", trianList);
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
	 * 根据用户查询考试记录
	 * @Title: trainCode
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月18日 上午11:47:50
	 * @param form
	 * @param request
	 * @return
	 */
	@GetMapping(value="/user")
	public Object findByUserCode(TrainExamLog form,User user){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String user_code = user.getUser_code();
			if(StringUtils.isEmpty(user_code) || RegularUtil.isSpecialChar(user_code) || RegularUtil.matchLength(user_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			form.setUser_code_chef(user_code);
			List<TrainExamLog> telList = trainExamLogService.findByAll(form);
			List<TrainExamLogReturn> telrList = new ArrayList<TrainExamLogReturn>();
			if(!StringUtils.isEmpty(telList) && telList.size() > 0) {
				TrainExamLogReturn telr = null;
				for (TrainExamLog tel : telList) {
					telr = new TrainExamLogReturn();
					BeanUtils.copyProperties(tel, telr);
					telrList.add(telr);
				}
				map.put("train_exam_log_list", telrList);
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
	 * 查看考试明细
	 * @Title: trainExamCode
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月18日 下午3:33:02
	 * @param form
	 * @param trainExamTopicLog
	 * @param request
	 * @return
	 */
	@GetMapping(value="/single")
	public Object findByTrainExamLogCode(TrainExam trainExam,TrainExamTopicLog trainExamTopicLog,User user){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String train_code = trainExam.getTrain_code();
			if(StringUtils.isEmpty(train_code) || RegularUtil.isSpecialChar(train_code) || RegularUtil.matchLength(train_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			//train_exam_log_code页面传入
			String train_exam_log_code = trainExamTopicLog.getTrain_exam_log_code();
			if(StringUtils.isEmpty(train_exam_log_code) || RegularUtil.isSpecialChar(train_exam_log_code) || RegularUtil.matchLength(train_exam_log_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String user_code = user.getUser_code();
			if(StringUtils.isEmpty(user_code) || RegularUtil.isSpecialChar(user_code) || RegularUtil.matchLength(user_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			trainExamTopicLog.setUser_code(user_code);
			trainExam = trainExamService.findByCode(trainExam);
			if(!StringUtils.isEmpty(trainExam)) {
				TrainExamReturn ter = new TrainExamReturn();
				BeanUtils.copyProperties(trainExam, ter);
				map.put("train_exam", ter);
			}
			List<TrainExamTopicLog> tetlList = trainExamTopicLogService.findByAll(trainExamTopicLog);
			List<TrainExamTopicLogReturn> tetlrList = new ArrayList<TrainExamTopicLogReturn>();
			if(!StringUtils.isEmpty(tetlList) && tetlList.size() > 0) {
				TrainExamTopicLogReturn tetlr = null;
				for (TrainExamTopicLog tetl : tetlList) {
					tetlr = new TrainExamTopicLogReturn();
					BeanUtils.copyProperties(tetl, tetlr);
					tetlrList.add(tetlr);
				}
				map.put("train_exam_topic_log_list", tetlrList);
			}
			//培训标识code
			map.put("train_code", trainExam.getTrain_code());
			
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
	 * 根据trainCode查询课程列表和试题
	 * @Title: findByTrainCode
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月19日 下午7:12:29
	 * @param form
	 * @param trainCourse
	 * @param trainExam
	 * @return
	 */
	@GetMapping(value="/trainCode")
	public Object findByTrainCode(TrainExamLog form,TrainCourse trainCourse,TrainExam trainExam){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String train_code = trainExam.getTrain_code();
			if(StringUtils.isEmpty(train_code) || RegularUtil.isSpecialChar(train_code) || RegularUtil.matchLength(train_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			//培训资料列表
			List<TrainCourse> tclist = trainCourseService.findByAll(trainCourse);
			List<TrainCourseReturn> tcrlist = new ArrayList<TrainCourseReturn>();
			if(!StringUtils.isEmpty(tclist) && tclist.size() > 0) {
				TrainCourseReturn tcr = null;
				for (TrainCourse tc : tclist) {
					tcr = new TrainCourseReturn();
					BeanUtils.copyProperties(tc, tcr);
					tcrlist.add(tcr);
				}
				map.put("train_course_list",tcrlist);
			}
			//试卷
			trainExam = trainExamService.findByCode(trainExam);
			if(!StringUtils.isEmpty(trainExam)) {
				TrainExamReturn ter = new TrainExamReturn();
				BeanUtils.copyProperties(trainExam, ter);
				map.put("train_exam",ter);
			}
			//培训code
			map.put("train_code", form.getTrain_code());
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
	 * 去学习
	 * @Title: trainDetail
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月18日 下午5:21:07
	 * @param form
	 * @param trainCourseLog
	 * @param request
	 * @return
	 */
	@GetMapping(value="course/single")
	public Object findCourseDetail(TrainCourse form, TrainCourseLog trainCourseLog, User user) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String train_course_code = form.getTrain_course_code();
			if(StringUtils.isEmpty(train_course_code) || RegularUtil.isSpecialChar(train_course_code) || RegularUtil.matchLength(train_course_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String user_code = user.getUser_code();
			if(StringUtils.isEmpty(user_code) || RegularUtil.isSpecialChar(user_code) || RegularUtil.matchLength(user_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			trainCourseLog.setUser_code_chef(user_code);
			form = trainCourseService.findByCode(form);
			int count = trainCourseLogService.findByExist(trainCourseLog);
			if(count > 0){
				form.setIs_study(1); //已学习
			}else {
				form.setIs_study(0); //未学习
			}
			TrainCourseReturn tcr = new TrainCourseReturn();
			BeanUtils.copyProperties(form, tcr);
			map.put("train_course",tcr);
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
	 * 试卷初始化
	 * @Title: saveInit
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月18日 下午6:01:59
	 * @param form
	 * @param trainExam
	 * @param trainExamTopic
	 * @param model
	 * @param request
	 * @param httpSession
	 * @return
	 */
	@GetMapping(value="/exam/single")
	public Object findTrainExam(TrainExamLog form,TrainExam trainExam,TrainExamTopic trainExamTopic){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String train_code = trainExam.getTrain_code();
			if(StringUtils.isEmpty(train_code) || RegularUtil.isSpecialChar(train_code) || RegularUtil.matchLength(train_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String train_exam_code = form.getTrain_exam_code();
			if(StringUtils.isEmpty(train_exam_code) || RegularUtil.isSpecialChar(train_exam_code) || RegularUtil.matchLength(train_exam_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			map.put("train_code", train_code);
			map.put("train_exam_code", train_exam_code);
			map.put("train_exam_log_code", CodeUtil.getSystemCode("trainExamLog"));
			//试题
			trainExam = trainExamService.findByCode(trainExam);
			if(!StringUtils.isEmpty(trainExam)) {
				TrainExamReturn ter = new TrainExamReturn();
				BeanUtils.copyProperties(trainExam, ter);
				map.put("train_exam",ter);
			}
			//试题题目
			List<TrainExamTopic> tetList =  trainExamTopicService.findByAll(trainExamTopic);
			List<TrainExamTopicReturn> tetrList = new ArrayList<TrainExamTopicReturn>();
			if(!StringUtils.isEmpty(tetList) && tetList.size() > 0) {
				TrainExamTopicReturn tetr = null;
				for (TrainExamTopic tet : tetList) {
					tetr = new TrainExamTopicReturn();
					BeanUtils.copyProperties(tet, tetr);
					tetrList.add(tetr);
				}
				map.put("train_exam_topic_list",tetrList);
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
	 * 提交试卷
	 * @Title: save
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月19日 上午10:57:33
	 * @param form
	 * @param attr
	 * @param httpSession
	 * @param request
	 * @return
	 */
	@PostMapping(value="/exam/save")
	public Object saveExam(@RequestBody TrainExamLog form){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String train_code = form.getTrain_code();
			if(StringUtils.isEmpty(train_code) || RegularUtil.isSpecialChar(train_code) || RegularUtil.matchLength(train_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String train_exam_log_code = form.getTrain_exam_log_code();
			if(StringUtils.isEmpty(train_exam_log_code) || RegularUtil.isSpecialChar(train_exam_log_code) || RegularUtil.matchLength(train_exam_log_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String train_exam_code = form.getTrain_exam_code();
			if(StringUtils.isEmpty(train_exam_code) || RegularUtil.isSpecialChar(train_exam_code) || RegularUtil.matchLength(train_exam_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int train_exam_standard = form.getTrain_exam_standard();
			if(!(train_exam_standard == 1 || train_exam_standard == 2)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int error_num = form.getError_num();
			if(!RegularUtil.isNumber(error_num+"")) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int right_num = form.getRight_num();
			if(!RegularUtil.isNumber(right_num+"")) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int sum_num = form.getSum_num();
			if(!RegularUtil.isNumber(sum_num+"")) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(StringUtils.isEmpty(train_exam_code) || RegularUtil.isSpecialChar(train_exam_code) || RegularUtil.matchLength(train_exam_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			List<TrainExamTopicLog> tetlList = form.getTrain_exam_topic_log_list();
			if(StringUtils.isEmpty(tetlList) || tetlList.size() < 1){
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String user_code_chef = form.getUser_code_chef();
			if(StringUtils.isEmpty(user_code_chef) || RegularUtil.isSpecialChar(user_code_chef) || RegularUtil.matchLength(user_code_chef,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String user_name_chef = form.getUser_name_chef();
			if(StringUtils.isEmpty(user_name_chef) || RegularUtil.isSpecialChar(user_name_chef) || !RegularUtil.isLength(user_name_chef, 1, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			
			form.setUser_code_chef(user_code_chef);
			form.setUser_name_chef(user_name_chef);
			int result = trainExamLogService.save(form);
			if (result > 0) {
				//批量保存题目日志
				TrainExamTopicLog tetl = new TrainExamTopicLog();
				tetl.setPager_list(tetlList);
				trainExamTopicLogService.saveBatch(tetl);
				//返回答题结果
				map.put("error_num", error_num);
				map.put("right_num", right_num);
				map.put("sum_num", sum_num);
				if(train_exam_standard == 2) {
					map.put("exam_result", "pass");
				}else {
					//企业不合格需要写入消息日志,进行消息发送
					User user = new User();  
					user.setUser_code(user_code_chef);
					user = userService.findByCode(user);
					if(!StringUtils.isEmpty(user.getCompany_code())) {
						messLogService.saveMessLogByOtherCode(4, user.getCompany_code(), train_exam_log_code, user);
					}
					map.put("exam_result", "unpass");
				}
				CODEMSG = SUCCESS;
			}else{
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
