package com.rs.controller;
import com.rs.po.PatrolConfig;
import com.rs.service.PatrolConfigService;
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
 * @ClassName: PatrolConfigController
 * @Description: 巡查配置控制层
 * @Author tangsh
 * @DateTime 2020年1月20日 上午9:37:08
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/app/patrolConfig")
public class AppPatrolConfigController extends BaseController {

	@Autowired
	private PatrolConfigService patrolConfigService;
	
	private Logger logger = LoggerFactory.getLogger(AppPatrolConfigController.class);

	/**
	 * 
	 * @Title: list
	 * @Description: 查询列表
	 * @Author tangsh
	 * @DateTime 2020年1月20日 上午9:40:14
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list() {
		PatrolConfig patrolConfig = null;
		try {
			patrolConfig = patrolConfigService.findByCompanyCodeToPatrolConfig("1001577953680843230686825");
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, patrolConfig);
	}
	
	/**
	 * 
	 * @Title: single
	 * @Description: 根据类型查询配置
	 * @Author tangsh
	 * @DateTime 2020年5月9日 下午4:18:29
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/single")
	public Object single(PatrolConfig form) {
		try {
			form = patrolConfigService.findByObj(form);
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, form);
	}
	
}
