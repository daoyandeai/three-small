package com.rs.dao;
import com.rs.po.Device;
import com.rs.po.returnPo.DeviceReturn;

import org.springframework.stereotype.Repository;
/**
 * 摄像头数据层接口
 * @ClassName: IDeviceDao
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月23日 下午5:35:49
 */
@Repository
public interface IDeviceDao extends IBaseDao<Device> {
	/**
	 * 
	 * @Title: updateStatus
	 * @Description: 更新状态
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:12:09
	 * @param form
	 * @return
	 */
	int updateStatus(Device form);
	/**
	 * 根据企业区域查询摄像头
	 * @Title: findByArea
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月17日 下午2:58:17
	 * @param company_area_code
	 * @return
	 */
	DeviceReturn findByArea(String company_area_code);
	
	/**
	 * 
	 * @Title: findByNumber
	 * @Description: 根据编号查询设备
	 * @Author tangsh
	 * @DateTime 2020年5月25日 下午3:49:03
	 * @return
	 */
	Device findByNumber(String device_number);
	/**
	 * 
	 * @Title: findByQyCode
	 * @Description: 查询群宴表的设备
	 * @Author tangsh
	 * @DateTime 2020年5月25日 下午3:58:32
	 * @param device_code
	 * @return
	 */
	Device findByQyCode(String device_code);
}
