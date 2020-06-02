package com.rs.service;
import com.rs.dao.ISensorDao;
import com.rs.po.Sensor;
import com.rs.po.returnPo.SensorReturn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 传感器服务层接口
 * @ClassName: SensorService
 * @Description: 
 * @Author sven
 * @DateTime 2020年1月14日 上午11:52:25
 */
@Service
public class SensorService extends BaseService<Sensor>{
	@Autowired
	private ISensorDao sensorDao;
	
	public int updateStatus(Sensor form) {
		return sensorDao.updateStatus(form);
	}
	
	public List<SensorReturn> findByList_join(Sensor form) {
		return sensorDao.findByList_join(form);
	}
	
	public Integer findByCount_join(Sensor form) {
		return sensorDao.findByCount_join(form);
	}
	
	public SensorReturn findByArea(String company_area_code) {
		return sensorDao.findByArea(company_area_code);
	}
	
}
