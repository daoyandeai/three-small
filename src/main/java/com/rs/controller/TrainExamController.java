package com.rs.controller;
import com.rs.po.TokenParam;
import com.rs.po.TrainExam;
import com.rs.po.TrainExamTopic;
import com.rs.po.User;
import com.rs.po.returnPo.TrainExamTopicReturn;
import com.rs.service.TrainExamService;
import com.rs.service.TrainExamTopicService;
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
import org.springframework.web.bind.annotation.DeleteMapping;
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
 * 培训试题控制层
 * @ClassName: TrainExamController
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月17日 上午10:42:15
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/trainExam")
public class TrainExamController extends BaseController {

	@Autowired
	private TrainExamService trainExamService;
	@Autowired
	private TrainExamTopicService trainExamTopicService;
	@Autowired
	private TokenParam tokenParam;
	
	private Logger logger = LoggerFactory.getLogger(TrainExamController.class);


	/**
	 * 查询培训试题
	 * @Title: single
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月17日 上午11:06:47
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/single")
	public Object single(TrainExam form,TrainExamTopic trainExamTopic) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String train_exam_code = form.getTrain_exam_code();
			if(RegularUtil.isSpecialChar(train_exam_code) || RegularUtil.matchLength(train_exam_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			//试题
			form = trainExamService.findByCode(form);
			if(!StringUtils.isEmpty(form)) {
				//map.put("train_code",form.getTrain_code());
				map.put("train_exam_title",form.getTrain_exam_title());
				map.put("train_exam_standard",form.getTrain_exam_standard());
				map.put("train_exam_code",form.getTrain_exam_code());
			}
			//题目
			List<TrainExamTopic> tetList = 	trainExamTopicService.findByAll(trainExamTopic);
			List<TrainExamTopicReturn> tetrList = new ArrayList<TrainExamTopicReturn>();
			if(!StringUtils.isEmpty(tetList) && tetList.size() > 0) {
				TrainExamTopicReturn trtr = null;
				for (TrainExamTopic tet : tetList) {
					trtr = new TrainExamTopicReturn();
					BeanUtils.copyProperties(tet, trtr);
					tetrList.add(trtr);
				}
				map.put("train_exam_topic_list", tetrList);
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
	 * 添加培训试题
	 * @Title: save
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月16日 上午11:27:46
	 * @param form
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody TrainExam form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String train_code = form.getTrain_code();
			String train_exam_code =  form.getTrain_exam_code();
			String train_exam_title = form.getTrain_exam_title();
			int train_exam_standard = form.getTrain_exam_standard();
			
			if(RegularUtil.isSpecialChar(train_code) || RegularUtil.matchLength(train_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(RegularUtil.isSpecialChar(train_exam_code) || RegularUtil.matchLength(train_exam_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(RegularUtil.isSpecialChar(train_exam_title) || !RegularUtil.isLength(train_exam_title, 1,128)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(!RegularUtil.isNumber(train_exam_standard+"")){
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(form.getTrain_exam_topic_list().size() < 1){
				CODEMSG = TRAIN_EXAM_TOPIC_SIZE_UNVALID;
				return finish(CODEMSG, null);
			}
			
			User user = TokenUserUtil.generateUser(request, tokenParam);
			
			form.setUser_code(user.getUser_code());
			form.setUser_name(user.getUser_name());
			int result = trainExamService.save(form);
			if (result > 0) {
				TrainExamTopic trainExamTopic = new TrainExamTopic();
				trainExamTopic.setPager_list(form.getTrain_exam_topic_list());
				trainExamTopicService.saveBatch(trainExamTopic);
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
	 * 编辑培训试题
	 * @Title: update
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月17日 下午2:24:29
	 * @param form
	 * @param request
	 * @return
	 */
	@PutMapping(value = "/update")
	public Object update(@RequestBody TrainExam form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String train_exam_code = form.getTrain_exam_code();
			String train_exam_title = form.getTrain_exam_title();
			int train_exam_standard = form.getTrain_exam_standard();
			
			if(RegularUtil.isSpecialChar(train_exam_code) || RegularUtil.matchLength(train_exam_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(RegularUtil.isSpecialChar(train_exam_title) || !RegularUtil.isLength(train_exam_title, 1,128)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(!RegularUtil.isNumber(train_exam_standard+"")){
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(form.getTrain_exam_topic_list().size() < 1){
				CODEMSG = TRAIN_EXAM_TOPIC_SIZE_UNVALID;
				return finish(CODEMSG, null);
			}
			
			User user = TokenUserUtil.generateUser(request, tokenParam);
			
			form.setUser_code(user.getUser_code());
			form.setUser_name(user.getUser_name());
			int result = trainExamService.update(form);
			if (result > 0) {
				//删除试题题目
				TrainExamTopic trainExamTopic = new TrainExamTopic();
				trainExamTopic.setTrain_exam_code(train_exam_code);
				trainExamTopicService.delete(trainExamTopic);
				//批量新增试题题目
				trainExamTopic.setPager_list(form.getTrain_exam_topic_list());
				trainExamTopicService.saveBatch(trainExamTopic);
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
	 * 删除培训试题
	 * @Title: delete
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月17日 下午2:24:14
	 * @param form
	 * @return
	 */
	@DeleteMapping(value="/delete")
	public Object delete(TrainExam form){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String train_exam_code = form.getTrain_exam_code();
			if(RegularUtil.isSpecialChar(train_exam_code) || RegularUtil.matchLength(train_exam_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int result = trainExamService.delete(form);
			if (result > 0) {
				//删除试题题目
				TrainExamTopic trainExamTopic = new TrainExamTopic();
				trainExamTopic.setTrain_exam_code(train_exam_code);
				trainExamTopicService.delete(trainExamTopic);
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
