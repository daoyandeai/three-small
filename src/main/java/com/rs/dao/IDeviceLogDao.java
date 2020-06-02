package com.rs.dao;
import com.rs.po.DeviceLog;

import org.springframework.stereotype.Repository;

/**
 * 
 * @ClassName: IDeviceLogDao
 * @Description: 传感器日志数据层接口
 * @Author tangsh
 * @DateTime 2020年5月25日 下午2:52:45
 */
@Repository
public interface IDeviceLogDao extends IBaseDao<DeviceLog> {
	DeviceLog findByNumber(String deviceNumber);
}
