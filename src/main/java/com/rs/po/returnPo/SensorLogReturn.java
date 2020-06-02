package com.rs.po.returnPo;

import java.io.Serializable;
/**
 * 传感器数据日志
 * @ClassName: SensorLogReturn
 * @Description: 
 * @Author sven
 * @DateTime 2020年3月17日 上午11:03:02
 */
public class SensorLogReturn implements Serializable{

	/**
	  * @Fields serialVersionUID : 
	  */
	
	private static final long serialVersionUID = 1L;
	/**
	 * 传感器编号
	 */
	private String sensor_number;
	/**
	 * 主板连接状态(1：正常、2：不正常)
	 */
	private Integer mainboard_connect_state;
	/**
	 * 水质检测板连接状态(1：正常、2：不正常)
	 */
	private Integer water_connect_state;
	/**
	 * 气体检测板连接状态(1：正常、2：不正常)
	 */
	private Integer gases_connect_state;
	/**
	 * 温度监测值
	 */
	private String temp;
	/**
	 * 平台设置的温度预警范围
	 */
	private String temp_system_range;
	/**
	 * 设备传入的温度采集范围
	 */
	private String temp_monitor_range;
	/**
	 * 温度报警状态(1：正常、2：报警)
	 */
	private Integer temp_state;
	/**
	 * 烟雾监测值
	 */
	private String mq2;
	/**
	 * 平台设置的烟雾预警范围
	 */
	private String mq2_system_range;
	/**
	 * 设备传入的烟雾采集范围
	 */
	private String mq2_monitor_range;
	/**
	 * 烟雾报警状态(1：正常、2：报警)
	 */
	private Integer mq2_state;
	/**
	 * 甲烷监测值
	 */
	private String mq4;
	/**
	 * 平台设置的甲烷预警范围
	 */
	private String mq4_system_range;
	/**
	 * 设备传入的甲烷采集范围
	 */
	private String mq4_monitor_range;
	/**
	 * 甲烷报警状态(1：正常、2：报警)
	 */
	private Integer mq4_state;
	/**
	 * 液化气监测值
	 */
	private String mq5;
	/**
	 * 平台设置的液化气预警范围
	 */
	private String mq5_system_range;
	/**
	 * 设备传入的液化气采集范围
	 */
	private String mq5_monitor_range;
	/**
	 * 液化气报警状态(1：正常、2：报警)
	 */
	private Integer mq5_state;
	/**
	 * 一氧化碳监测值
	 */
	private String mq9;
	/**
	 * 平台设置的一氧化碳预警范围
	 */
	private String mq9_system_range;
	/**
	 * 设备传入的一氧化碳采集范围
	 */
	private String mq9_monitor_range;
	/**
	 * 一氧化碳报警状态(1：正常、2：报警)
	 */
	private Integer mq9_state;
	/**
	 * 水质监测值
	 */
	private String tds;
	/**
	 * 平台设置的水质预警范围
	 */
	private String tds_system_range;
	/**
	 * 设备传入的水质采集范围
	 */
	private String tds_monitor_range;
	/**
	 * 水质报警状态(1：正常、2：报警)
	 */
	private Integer tds_state;
	/**
	 * 酸碱度监测值
	 */
	private String ph;
	/**
	 * 平台设置的酸碱度预警范围
	 */
	private String ph_system_range;
	/**
	 * 设备传入的酸碱度采集范围
	 */
	private String ph_monitor_range;
	/**
	 * 酸碱度报警状态(1：正常、2：报警)
	 */
	private Integer ph_state;
	/**
	 * 湿度监测值
	 */
	private String humidity;
	/**
	 * 平台设置的湿度预警范围
	 */
	private String humidity_system_range;
	/**
	 * 设备传入的湿度采集范围
	 */
	private String humidity_monitor_range;
	/**
	 * 湿度报警状态(1：正常、2：报警)
	 */
	private Integer humidity_state;
	/**
	 * 添加时间
	 */
	private String addtime;
	public String getSensor_number() {
		return sensor_number;
	}
	public void setSensor_number(String sensor_number) {
		this.sensor_number = sensor_number;
	}
	public Integer getMainboard_connect_state() {
		return mainboard_connect_state;
	}
	public void setMainboard_connect_state(Integer mainboard_connect_state) {
		this.mainboard_connect_state = mainboard_connect_state;
	}
	public Integer getWater_connect_state() {
		return water_connect_state;
	}
	public void setWater_connect_state(Integer water_connect_state) {
		this.water_connect_state = water_connect_state;
	}
	public Integer getGases_connect_state() {
		return gases_connect_state;
	}
	public void setGases_connect_state(Integer gases_connect_state) {
		this.gases_connect_state = gases_connect_state;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getTemp_system_range() {
		return temp_system_range;
	}
	public void setTemp_system_range(String temp_system_range) {
		this.temp_system_range = temp_system_range;
	}
	public String getTemp_monitor_range() {
		return temp_monitor_range;
	}
	public void setTemp_monitor_range(String temp_monitor_range) {
		this.temp_monitor_range = temp_monitor_range;
	}
	public Integer getTemp_state() {
		return temp_state;
	}
	public void setTemp_state(Integer temp_state) {
		this.temp_state = temp_state;
	}
	public String getMq2() {
		return mq2;
	}
	public void setMq2(String mq2) {
		this.mq2 = mq2;
	}
	public String getMq2_system_range() {
		return mq2_system_range;
	}
	public void setMq2_system_range(String mq2_system_range) {
		this.mq2_system_range = mq2_system_range;
	}
	public String getMq2_monitor_range() {
		return mq2_monitor_range;
	}
	public void setMq2_monitor_range(String mq2_monitor_range) {
		this.mq2_monitor_range = mq2_monitor_range;
	}
	public Integer getMq2_state() {
		return mq2_state;
	}
	public void setMq2_state(Integer mq2_state) {
		this.mq2_state = mq2_state;
	}
	public String getMq4() {
		return mq4;
	}
	public void setMq4(String mq4) {
		this.mq4 = mq4;
	}
	public String getMq4_system_range() {
		return mq4_system_range;
	}
	public void setMq4_system_range(String mq4_system_range) {
		this.mq4_system_range = mq4_system_range;
	}
	public String getMq4_monitor_range() {
		return mq4_monitor_range;
	}
	public void setMq4_monitor_range(String mq4_monitor_range) {
		this.mq4_monitor_range = mq4_monitor_range;
	}
	public Integer getMq4_state() {
		return mq4_state;
	}
	public void setMq4_state(Integer mq4_state) {
		this.mq4_state = mq4_state;
	}
	public String getMq5() {
		return mq5;
	}
	public void setMq5(String mq5) {
		this.mq5 = mq5;
	}
	public String getMq5_system_range() {
		return mq5_system_range;
	}
	public void setMq5_system_range(String mq5_system_range) {
		this.mq5_system_range = mq5_system_range;
	}
	public String getMq5_monitor_range() {
		return mq5_monitor_range;
	}
	public void setMq5_monitor_range(String mq5_monitor_range) {
		this.mq5_monitor_range = mq5_monitor_range;
	}
	public Integer getMq5_state() {
		return mq5_state;
	}
	public void setMq5_state(Integer mq5_state) {
		this.mq5_state = mq5_state;
	}
	public String getMq9() {
		return mq9;
	}
	public void setMq9(String mq9) {
		this.mq9 = mq9;
	}
	public String getMq9_system_range() {
		return mq9_system_range;
	}
	public void setMq9_system_range(String mq9_system_range) {
		this.mq9_system_range = mq9_system_range;
	}
	public String getMq9_monitor_range() {
		return mq9_monitor_range;
	}
	public void setMq9_monitor_range(String mq9_monitor_range) {
		this.mq9_monitor_range = mq9_monitor_range;
	}
	public Integer getMq9_state() {
		return mq9_state;
	}
	public void setMq9_state(Integer mq9_state) {
		this.mq9_state = mq9_state;
	}
	public String getTds() {
		return tds;
	}
	public void setTds(String tds) {
		this.tds = tds;
	}
	public String getTds_system_range() {
		return tds_system_range;
	}
	public void setTds_system_range(String tds_system_range) {
		this.tds_system_range = tds_system_range;
	}
	public String getTds_monitor_range() {
		return tds_monitor_range;
	}
	public void setTds_monitor_range(String tds_monitor_range) {
		this.tds_monitor_range = tds_monitor_range;
	}
	public Integer getTds_state() {
		return tds_state;
	}
	public void setTds_state(Integer tds_state) {
		this.tds_state = tds_state;
	}
	public String getPh() {
		return ph;
	}
	public void setPh(String ph) {
		this.ph = ph;
	}
	public String getPh_system_range() {
		return ph_system_range;
	}
	public void setPh_system_range(String ph_system_range) {
		this.ph_system_range = ph_system_range;
	}
	public String getPh_monitor_range() {
		return ph_monitor_range;
	}
	public void setPh_monitor_range(String ph_monitor_range) {
		this.ph_monitor_range = ph_monitor_range;
	}
	public Integer getPh_state() {
		return ph_state;
	}
	public void setPh_state(Integer ph_state) {
		this.ph_state = ph_state;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getHumidity_system_range() {
		return humidity_system_range;
	}
	public void setHumidity_system_range(String humidity_system_range) {
		this.humidity_system_range = humidity_system_range;
	}
	public String getHumidity_monitor_range() {
		return humidity_monitor_range;
	}
	public void setHumidity_monitor_range(String humidity_monitor_range) {
		this.humidity_monitor_range = humidity_monitor_range;
	}
	public Integer getHumidity_state() {
		return humidity_state;
	}
	public void setHumidity_state(Integer humidity_state) {
		this.humidity_state = humidity_state;
	}
}
