package com.rs.dao;
import com.rs.po.SensorLog;
import com.rs.po.returnPo.SensorLogReturn;
import org.springframework.stereotype.Repository;
/**
 * 传感器监控值数据层接口
 * @ClassName: ISensorLogDao
 * @Description: 
 * @Author sven
 * @DateTime 2020年3月17日 上午11:00:32
 */
@Repository
public interface ISensorLogDao extends IBaseDao<SensorLog> {
	/**
	 * 根据传感器编号查询最新一次监控日志
	 * @Title: findBySensorNumber
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月17日 上午11:03:15
	 * @param sensorNumber
	 * @return
	 */
	SensorLogReturn findBySensorNumber(String sensorNumber);
	/**
	 * 根據預警傳感器編號查詢對應的預警項數量
	 * @Title: findWarnItemBySensorNumber
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月18日 上午11:29:47
	 * @param form
	 * @return
	 */
	Integer findWarnItemBySensorNumber(SensorLog form);
}
