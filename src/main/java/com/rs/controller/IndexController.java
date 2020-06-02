package com.rs.controller;
import com.rs.po.Patrol;
import com.rs.po.TokenParam;
import com.rs.po.TrainExamLog;
import com.rs.po.User;
import com.rs.service.IndexService;
import com.rs.util.TokenUserUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @ClassName: IndexController
 * @Description:首页数据展示控制层
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/index")
public class IndexController extends BaseController {

	@Autowired
	private IndexService indexService;
	@Autowired
	private TokenParam tokenParam;
	
	private Logger logger = LoggerFactory.getLogger(IndexController.class);

	/**
	 * 
	 * @Title: count
	 * @Description: 查询首页数据统计
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:28:36
	 * @param user
	 * @return
	 */
	@GetMapping(value = "/count")
	public Object count(HttpServletRequest request) {
		User user = TokenUserUtil.generateUser(request, tokenParam);
		CODEMSG = ERROR;
		String json = "";
		try {
			json=indexService.count(user);
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, json);
	}
	
	/**
	 * 
	 * @Title: todolist
	 * @Description: 今天待办事项
	 * @Author tangsh
	 * @DateTime 2020年3月24日 下午5:20:41
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/todoList")
	public Object todoList(HttpServletRequest request) {
		User user = TokenUserUtil.generateUser(request, tokenParam);
		CODEMSG = ERROR;
		Map<String,Integer> map=null;
		try {
			map=indexService.todoList(user);
			CODEMSG = SUCCESS;
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
	 * @Title: willexpirelist
	 * @Description: 即将到期预警
	 * @Author tangsh
	 * @DateTime 2020年3月24日 下午5:24:11
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/willExpireList")
	public Object willExpireList(HttpServletRequest request) {
		User user = TokenUserUtil.generateUser(request, tokenParam);
		CODEMSG = ERROR;
		Map<String,Integer> map=null;
		try {
			map=indexService.willExpireList(user);
			CODEMSG = SUCCESS;
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
	 * @Title: expiredlist
	 * @Description: 过期预警
	 * @Author tangsh
	 * @DateTime 2020年3月24日 下午5:27:38
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/expiredList")
	public Object expiredList(HttpServletRequest request) {
		User user = TokenUserUtil.generateUser(request, tokenParam);
		CODEMSG = ERROR;
		Map<String,Integer> map=null;
		try {
			map=indexService.expiredList(user);
			CODEMSG = SUCCESS;
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
	 * @Title: newsavelist
	 * @Description: 今日新增食品经营责任人
	 * @Author tangsh
	 * @DateTime 2020年3月24日 下午5:42:18
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/newSaveList")
	public Object newSaveList(HttpServletRequest request) {
		User user = TokenUserUtil.generateUser(request, tokenParam);
		CODEMSG = ERROR;
		Map<String,Integer> map=null;
		try {
			map=indexService.newSaveList(user);
			CODEMSG = SUCCESS;
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
	 * @Title: todaycompanylist
	 * @Description: 今日备案管理情况
	 * @Author tangsh
	 * @DateTime 2020年3月25日 上午9:16:38
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/todayCompanyList")
	public Object todayCompanyList(HttpServletRequest request) {
		User user = TokenUserUtil.generateUser(request, tokenParam);
		CODEMSG = ERROR;
		Map<String,Integer> map=null;
		try {
			map=indexService.todayCompanyList(user);
			CODEMSG = SUCCESS;
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
	 * @Title: gismaplist
	 * @Description: 今日GIS地图
	 * @Author tangsh
	 * @DateTime 2020年3月25日 上午9:22:01
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/gisMapList")
	public Object gisMapList(Patrol form,HttpServletRequest request) {
		User user = TokenUserUtil.generateUser(request, tokenParam);
		CODEMSG = ERROR;
		Map<String,Object> map=null;
		try {
			map=indexService.gisMapList(form,user);
			CODEMSG = SUCCESS;
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
	 * @Title: companyCheckRate
	 * @Description: 备案审核率环比
	 * @Author tangsh
	 * @DateTime 2020年3月25日 上午9:57:18
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/companyCheckRate")
	public Object companyCheckRate(HttpServletRequest request) {
		User user = TokenUserUtil.generateUser(request, tokenParam);
		CODEMSG = ERROR;
		Map<String,Object> map=null;
		try {
			map=indexService.companyCheckRate(user);
			CODEMSG = SUCCESS;
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
	 * @Title: patrolCheckRate
	 * @Description: 巡查合格率环比
	 * @Author tangsh
	 * @DateTime 2020年3月25日 下午2:31:10
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/patrolCheckRate")
	public Object patrolCheckRate(HttpServletRequest request) {
		User user = TokenUserUtil.generateUser(request, tokenParam);
		CODEMSG = ERROR;
		Map<String,Object> map=null;
		try {
			map=indexService.patrolCheckRate(user);
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, map);
	}
	
	/**
	 * 查询考试环比
	 * @Title: findExamUserCount
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月23日 下午6:03:29
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/exam")
	public Object findExamUserCount(TrainExamLog trainExamLog,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			User form = TokenUserUtil.generateUser(request, tokenParam);
			List<Object> exam = indexService.findExamUserCount(form,trainExamLog);
			map.put("exam",exam);
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
