package com.rs.service;
import com.rs.dao.IDeviceDao;
import com.rs.dao.IDeviceLogDao;
import com.rs.dao.IReportDao;
import com.rs.po.Device;
import com.rs.po.DeviceLog;
import com.rs.po.Report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
/**
 * 
 * @ClassName: DeviceLogService
 * @Description: 传感器日志服务层接口
 * @Author tangsh
 * @DateTime 2020年5月25日 下午2:54:37
 */
@Service
public class DeviceLogService extends BaseService<DeviceLog>{
	
	@Autowired
	private IDeviceLogDao deviceLogDao;
	@Autowired
	private IDeviceDao deviceDao;
	@Autowired
	private IReportDao reportDao;
	
	public DeviceLog findByNumber(DeviceLog form) {
		DeviceLog dl = deviceLogDao.findByNumber(form.getDevice_number());
		if(dl!=null) {
			form=dl;
		}
		Device device =deviceDao.findByNumber(form.getDevice_number());
		if(device==null) {
			return form;
		}
		Report report = new Report();
		report.setUser_code_mainchef(device.getUser_code_add());
		form.setUser_type(device.getUser_type());
		form.setUser_name_conduct(device.getUser_name_add());
		
		Report findToDayBanquet = reportDao.findToDayBanquet(report);
		if(!StringUtils.isEmpty(findToDayBanquet)) {
			form.setBanquet_time(findToDayBanquet.getBanquet_time());
			form.setAddress_conduct(findToDayBanquet.getAddress_conduct());
			form.setUser_mobilephone_mainchef(findToDayBanquet.getUser_mobilephone_mainchef());
		}
		return form;
	}
}
