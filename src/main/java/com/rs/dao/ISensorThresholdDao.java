package com.rs.dao;
import com.rs.po.SensorThreshold;
import org.springframework.stereotype.Repository;
/**
 * 传感器预警值数据层接口
 * @ClassName: ISensorThresholdDao
 * @Description: 
 * @Author sven
 * @DateTime 2020年1月14日 下午3:34:30
 */
@Repository
public interface ISensorThresholdDao extends IBaseDao<SensorThreshold> {
	
	
}
