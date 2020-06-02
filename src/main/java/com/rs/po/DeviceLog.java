package com.rs.po;

import java.util.List;

/**
 * 
 * @ClassName: DeviceSetting
 * @Description: 设备管理实体类
 * @Author tangsh
 * @DateTime 2020年5月25日 下午2:38:44
 */
public class DeviceLog extends BasePo<DeviceLog>{

	private static final long serialVersionUID = 1L;
	/**
	 * 唯一编号
	 */
	private String device_number;
	/**
	 * 温度
	 */
	private String temp;
	 /**
	  * 湿度
	  */
	private Integer hum;
	/**
	 * 烟雾传感器
	 */
	private String mq2;
	/**
	 * 甲烷
	 */
	private String mq4;
	/**
	 * 液化气
	 */
	private String mq5;
	/**
	 * 一氧化碳
	 */
	private String mq9;
	/**
	 * 水质
	 */
	private String tds;
	/**
	 * 酸碱度
	 */
	private String ph;
	
	/**
	 * 办宴人
	 */
	private String user_name_conduct;
	/**
	 * 一氧化碳
	 */
	private String address_conduct;
	/**
	 * 一氧化碳
	 */
	private String banquet_time;
	private String user_mobilephone_mainchef;
	private String user_type;
	private List<DeviceSetting> deviceSettinglist;
	
	public String getDevice_number() {
		return device_number;
	}
	public void setDevice_number(String device_number) {
		this.device_number = device_number;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public Integer getHum() {
		return hum;
	}
	public void setHum(Integer hum) {
		this.hum = hum;
	}
	public String getMq2() {
		return mq2;
	}
	public void setMq2(String mq2) {
		this.mq2 = mq2;
	}
	public String getMq4() {
		return mq4;
	}
	public void setMq4(String mq4) {
		this.mq4 = mq4;
	}
	public String getMq5() {
		return mq5;
	}
	public void setMq5(String mq5) {
		this.mq5 = mq5;
	}
	public String getMq9() {
		return mq9;
	}
	public void setMq9(String mq9) {
		this.mq9 = mq9;
	}
	public String getTds() {
		return tds;
	}
	public void setTds(String tds) {
		this.tds = tds;
	}
	public String getPh() {
		return ph;
	}
	public void setPh(String ph) {
		this.ph = ph;
	}
	public String getUser_name_conduct() {
		return user_name_conduct;
	}
	public void setUser_name_conduct(String user_name_conduct) {
		this.user_name_conduct = user_name_conduct;
	}
	public String getAddress_conduct() {
		return address_conduct;
	}
	public void setAddress_conduct(String address_conduct) {
		this.address_conduct = address_conduct;
	}
	public String getBanquet_time() {
		return banquet_time;
	}
	public void setBanquet_time(String banquet_time) {
		this.banquet_time = banquet_time;
	}
	public String getUser_mobilephone_mainchef() {
		return user_mobilephone_mainchef;
	}
	public void setUser_mobilephone_mainchef(String user_mobilephone_mainchef) {
		this.user_mobilephone_mainchef = user_mobilephone_mainchef;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public List<DeviceSetting> getDeviceSettinglist() {
		return deviceSettinglist;
	}
	public void setDeviceSettinglist(List<DeviceSetting> deviceSettinglist) {
		this.deviceSettinglist = deviceSettinglist;
	}
}
