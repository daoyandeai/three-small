package com.rs.po;
/**
 * 
 * @ClassName: DeviceSetting
 * @Description: 设备管理实体类
 * @Author tangsh
 * @DateTime 2020年5月25日 下午2:38:44
 */
public class DeviceSetting extends BasePo<DeviceSetting>{

	private static final long serialVersionUID = 1L;
	/**
	 * 传感器名称
	 */
	private String sensor_name;
	/**
	 * 最小值
	 */
	private String min_value;
	 /**
	  * 最大值
	  */
	private String max_value;
	/**
	 * 中文名称
	 */
	private String ch_name;
	public String getSensor_name() {
		return sensor_name;
	}
	public void setSensor_name(String sensor_name) {
		this.sensor_name = sensor_name;
	}
	public String getMin_value() {
		return min_value;
	}
	public void setMin_value(String min_value) {
		this.min_value = min_value;
	}
	public String getMax_value() {
		return max_value;
	}
	public void setMax_value(String max_value) {
		this.max_value = max_value;
	}
	public String getCh_name() {
		return ch_name;
	}
	public void setCh_name(String ch_name) {
		this.ch_name = ch_name;
	}
}
