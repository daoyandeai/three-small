package com.rs.po.returnPo;
import java.io.Serializable;
/**
 * 传感器阈值实体类
 * @ClassName: SensorThresholdReturn
 * @Description: 
 * @Author sven
 * @DateTime 2020年1月14日 下午3:43:21
 */
public class SensorThresholdReturn implements Serializable{
	/**
	  * @Fields serialVersionUID : 
	  */
	
	private static final long serialVersionUID = 1L;
	/**
	 * 传感器阈值系统编码
	 */
	private String sensor_threshold_code;
	/**
	 * 传感器中文名称
	 */
	private String sensor_name;
	/**
	 * 传感器英文描述
	 */
	private String sensor_enname;
	/**
	 * 正常最高值
	 */
	private Double max_value;
	/**
	 * 正常最低值
	 */
	private Double min_value;
	/**
	 * 同步间隔时长
	 */
	private Integer sync_interval;
	/**
	 * 添加用户编码
	 */
	private String user_code_add;
	/**
	 * 添加用户名称
	 */
	private String user_name_add;
	/**
	 * 添加时间
	 */
	private String add_time;
	/**
	 * 更新用户编码
	 */
	private String user_code_update;
	/**
	 * 更新用户名称
	 */
	private String user_name_update;
	/**
	 * 更新时间
	 */
	private String update_time;
	public String getSensor_threshold_code() {
		return sensor_threshold_code;
	}
	public void setSensor_threshold_code(String sensor_threshold_code) {
		this.sensor_threshold_code = sensor_threshold_code;
	}
	public String getSensor_name() {
		return sensor_name;
	}
	public void setSensor_name(String sensor_name) {
		this.sensor_name = sensor_name;
	}
	public String getSensor_enname() {
		return sensor_enname;
	}
	public void setSensor_enname(String sensor_enname) {
		this.sensor_enname = sensor_enname;
	}
	public Double getMax_value() {
		return max_value;
	}
	public void setMax_value(Double max_value) {
		this.max_value = max_value;
	}
	public Double getMin_value() {
		return min_value;
	}
	public void setMin_value(Double min_value) {
		this.min_value = min_value;
	}
	public Integer getSync_interval() {
		return sync_interval;
	}
	public void setSync_interval(Integer sync_interval) {
		this.sync_interval = sync_interval;
	}
	public String getUser_code_add() {
		return user_code_add;
	}
	public void setUser_code_add(String user_code_add) {
		this.user_code_add = user_code_add;
	}
	public String getUser_name_add() {
		return user_name_add;
	}
	public void setUser_name_add(String user_name_add) {
		this.user_name_add = user_name_add;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getUser_code_update() {
		return user_code_update;
	}
	public void setUser_code_update(String user_code_update) {
		this.user_code_update = user_code_update;
	}
	public String getUser_name_update() {
		return user_name_update;
	}
	public void setUser_name_update(String user_name_update) {
		this.user_name_update = user_name_update;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
}
