package com.rs.po.returnPo;

import java.io.Serializable;
/**
 * 传感器实体类
 * @ClassName: SensorReturn
 * @Description: 
 * @Author sven
 * @DateTime 2020年1月14日 上午11:57:52
 */
public class SensorReturn implements Serializable{

	/**
	  * @Fields serialVersionUID : 
	  */
	
	private static final long serialVersionUID = 1L;
	/**
	 * 传感器系统编码
	 */
	private String sensor_code;
	/**
	 * 企业编码
	 */
	private String company_code;
	 /**
	  * 企业名称
	  */
	private String company_name;
	/**
	 * 企业区域编码
	 */
	private String company_area_code;
	/**
	 * 企业区域名称
	 */
	private String company_area_name;
	/**
	 * 传感器名称
	 */
	private String sensor_name;
	/**
	 * 传感器编号
	 */
	private String sensor_number;
	/**
	 * 传感器状态（1：已启动、2：未启动、3：禁用）
	 */
	private int sensor_state;
	/**
	 * 传感器描述
	 */
	private String sensor_desc;
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
	/**
	 * 传感器图标url
	 */
	private String sensor_logo_url;
	/**
	 * 最新预警状态（1：正常、2：预警）
	 */
	private Integer warn_state;
	/**
	 * 在线状态（1：是、2：否）
	 */
	private Integer online_state;
	/**
	 * 传感器预警项配置json内容
	 */
	private Object threshold_content;
	/*******************数据库辅助字段*********************/
	/**
	 * 食品监管分类名称
	 */
	private String companytype_name;
	/**
	 * 主体类型名称
	 */
	private String companytag_name;
	/**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String area;
    /**
     * 村
     */
    private String vill;
    /**
     * 乡
     */
    private String town;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 传感器监控值
     */
    private SensorLogReturn sensor_log;
    
	public String getSensor_code() {
		return sensor_code;
	}
	public void setSensor_code(String sensor_code) {
		this.sensor_code = sensor_code;
	}
	public String getCompany_code() {
		return company_code;
	}
	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getSensor_name() {
		return sensor_name;
	}
	public void setSensor_name(String sensor_name) {
		this.sensor_name = sensor_name;
	}
	public String getSensor_number() {
		return sensor_number;
	}
	public void setSensor_number(String sensor_number) {
		this.sensor_number = sensor_number;
	}
	public int getSensor_state() {
		return sensor_state;
	}
	public void setSensor_state(int sensor_state) {
		this.sensor_state = sensor_state;
	}
	public String getSensor_desc() {
		return sensor_desc;
	}
	public void setSensor_desc(String sensor_desc) {
		this.sensor_desc = sensor_desc;
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
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
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
	public String getSensor_logo_url() {
		return sensor_logo_url;
	}
	public void setSensor_logo_url(String sensor_logo_url) {
		this.sensor_logo_url = sensor_logo_url;
	}
	public String getCompany_area_code() {
		return company_area_code;
	}
	public void setCompany_area_code(String company_area_code) {
		this.company_area_code = company_area_code;
	}
	public String getCompany_area_name() {
		return company_area_name;
	}
	public void setCompany_area_name(String company_area_name) {
		this.company_area_name = company_area_name;
	}
	public String getCompanytype_name() {
		return companytype_name;
	}
	public void setCompanytype_name(String companytype_name) {
		this.companytype_name = companytype_name;
	}
	public String getCompanytag_name() {
		return companytag_name;
	}
	public void setCompanytag_name(String companytag_name) {
		this.companytag_name = companytag_name;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getVill() {
		return vill;
	}
	public void setVill(String vill) {
		this.vill = vill;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public SensorLogReturn getSensor_log() {
		return sensor_log;
	}
	public void setSensor_log(SensorLogReturn sensor_log) {
		this.sensor_log = sensor_log;
	}
	public Integer getWarn_state() {
		return warn_state;
	}
	public void setWarn_state(Integer warn_state) {
		this.warn_state = warn_state;
	}
	public Integer getOnline_state() {
		return online_state;
	}
	public void setOnline_state(Integer online_state) {
		this.online_state = online_state;
	}
	public Object getThreshold_content() {
		return threshold_content;
	}
	public void setThreshold_content(Object threshold_content) {
		this.threshold_content = threshold_content;
	}
}
