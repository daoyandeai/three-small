package com.rs.controller;

import com.rs.po.SubjectDetail;
import com.rs.po.returnPo.SubjectDetailReturn;
import com.rs.service.SubjectDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: SubjectDetailController
 * @Description:详细经营信息控制层
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/subjectdetail")
public class SubjectDetailController extends BaseController {

	@Autowired
	private SubjectDetailService subjectDetailService;

	private Logger logger = LoggerFactory.getLogger(SubjectDetailController.class);

	/**
	 * 
	 * @Title: all
	 * @Description: 查询所有详细经营信息
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:30:35
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/all")
	public Object all(SubjectDetail form) {
		List<SubjectDetailReturn> all = new ArrayList<>();
		try {
			all = subjectDetailService.all(form);
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, all);
	}

}
