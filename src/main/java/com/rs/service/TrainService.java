package com.rs.service;
import com.rs.dao.ITrainDao;
import com.rs.po.Train;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 培训数据服务层
 * @ClassName: TrainService
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月12日 下午4:10:31
 */
@Service
public class TrainService extends BaseService<Train>{
	@Autowired
	private ITrainDao trainDao;
	
	public List<Train> findAllByAuth(Train form){
		return trainDao.findAllByAuth(form);
	}
	
	public List<Train> findListByAuth(Train form){
		return trainDao.findListByAuth(form);
	}
	
	public int findCountByAuth(Train form){
		return trainDao.findCountByAuth(form);
	}
	
	public List<Train> findAllGroupByYear(Train form){
		return trainDao.findAllGroupByYear(form);
	}
	
	public int updateStatus(Train form){
		return trainDao.updateStatus(form);
	}
}
