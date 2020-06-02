package com.rs.service;
import com.rs.dao.IDeviceDao;
import com.rs.po.Device;
import com.rs.po.returnPo.DeviceReturn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 设备服务层接口
 * @ClassName: DeviceService
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月23日 下午5:38:04
 */
@Service
public class DeviceService extends BaseService<Device>{
	@Autowired
	private IDeviceDao deviceDao;
	
	public int updateStatus(Device form) {
		return deviceDao.updateStatus(form);
	}
	
	public DeviceReturn findByArea(String company_area_code) {
		return deviceDao.findByArea(company_area_code);
	}
	public Device findByQyCode(String device_code) {
		return deviceDao.findByQyCode(device_code);
	}
}
