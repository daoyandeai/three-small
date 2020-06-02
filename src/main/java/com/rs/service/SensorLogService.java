package com.rs.service;
import com.rs.dao.ISensorLogDao;
import com.rs.po.SensorLog;
import com.rs.po.returnPo.SensorLogReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 传感器监控值服务层接口
 * @ClassName: SensorLogService
 * @Description: 
 * @Author sven
 * @DateTime 2020年3月17日 上午11:05:22
 */
@Service
public class SensorLogService extends BaseService<SensorLog>{
	@Autowired
	private ISensorLogDao sensorLogDao;
	
	public SensorLogReturn findBySensorNumber(String sensorNumber) {
		return sensorLogDao.findBySensorNumber(sensorNumber);
	}
	
}
