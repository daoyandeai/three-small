package com.rs.service;
import com.rs.dao.ITrainExamLogDao;
import com.rs.po.TrainExamLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 培训试题日志数据服务层
 * @ClassName: TrainExamLogService
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月18日 上午11:06:56
 */
@Service
public class TrainExamLogService extends BaseService<TrainExamLog>{
	@Autowired
	private ITrainExamLogDao trainExamLogDao;
	
	public int findIsPass(TrainExamLog form) {
		return trainExamLogDao.findIsPass(form);
	}
	
}
