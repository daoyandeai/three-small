package com.rs.dao;
import com.rs.po.DeviceSetting;

import org.springframework.stereotype.Repository;
/**
 * 
 * @ClassName: IDeviceSettingDao
 * @Description: 设备管理数据层接口
 * @Author tangsh
 * @DateTime 2020年5月25日 下午2:40:26
 */
@Repository
public interface IDeviceSettingDao extends IBaseDao<DeviceSetting> {
}
