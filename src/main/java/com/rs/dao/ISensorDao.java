package com.rs.dao;
import com.rs.po.Sensor;
import com.rs.po.returnPo.SensorReturn;

import java.util.List;

import org.springframework.stereotype.Repository;
/**
 * 传感器数据层接口
 * @ClassName: ISensorDao
 * @Description: 
 * @Author sven
 * @DateTime 2020年1月14日 上午11:51:40
 */
@Repository
public interface ISensorDao extends IBaseDao<Sensor> {
	/**
	 * 
	 * @Title: updateStatus
	 * @Description: 更新状态
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:21:35
	 * @param form
	 * @return
	 */
	int updateStatus(Sensor form);
	
	/**
	 * 联合查询列表
	 * @Title: findByList_join
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月17日 上午10:43:48
	 * @param form
	 * @return
	 */
	List<SensorReturn> findByList_join(Sensor form);
	/**
	 * 联合查询总数
	 * @Title: findByCount_join
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月17日 上午10:44:33
	 * @param form
	 * @return
	 */
	Integer findByCount_join(Sensor form);
	/**
	 * 根据企业区域查询传感器
	 * @Title: findByArea
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月17日 下午3:01:31
	 * @param company_area_code
	 * @return
	 */
	SensorReturn findByArea(String company_area_code);
	/**
	 * 查詢在線數量
	 * @Title: findOnLineCount
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月18日 上午11:22:22
	 * @param form
	 * @return
	 */
	Integer findOnLineCount(Sensor form);
	/**
	 * 查詢預警數量
	 * @Title: findWarnCount
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月18日 上午11:22:52
	 * @param form
	 * @return
	 */
	Integer findWarnCount(Sensor form);
	/**
	 * 查詢預警編號
	 * @Title: findWarnSensorNumber
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月18日 上午11:23:09
	 * @param form
	 * @return
	 */
	String findWarnSensorNumber(Sensor form);
}
