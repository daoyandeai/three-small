package com.rs.po.returnPo;

import java.io.Serializable;
/**
 * 设备实体类
 * @ClassName: DeviceReturn
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月23日 下午5:53:04
 */
public class DeviceReturn implements Serializable{

	/**
	  * @Fields serialVersionUID : 
	  */
	
	private static final long serialVersionUID = 1L;
	/**
	 * 设备系统编码
	 */
	private String device_code;
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
	 * 设备唯一标识
	 */
	private String device_id;
	/**
	 * 设备名称
	 */
	private String device_name;
	/**
	 * 设备编号
	 */
	private String device_number;
	/**
	 * 设备序列号
	 */
	private String device_serial;
	/**
	 * 设备状态（1：已启动、2：未启动、3：禁用）
	 */
	private int device_state;
	/**
	 * 直播地址
	 */
	private String play_video_url;
	/**
	 * 设备描述
	 */
	private String device_desc;
	/**
	 * 设备接入类型（1：阿里、2：乐橙）
	 */
	private int device_type;
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
	 * 更新时间
	 */
	private String update_time;
	
	public String getDevice_code() {
		return device_code;
	}
	public void setDevice_code(String device_code) {
		this.device_code = device_code;
	}
	public String getCompany_code() {
		return company_code;
	}
	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}
	public String getDevice_name() {
		return device_name;
	}
	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}
	public String getDevice_number() {
		return device_number;
	}
	public void setDevice_number(String device_number) {
		this.device_number = device_number;
	}
	public int getDevice_state() {
		return device_state;
	}
	public void setDevice_state(int device_state) {
		this.device_state = device_state;
	}
	public String getPlay_video_url() {
		return play_video_url;
	}
	public void setPlay_video_url(String play_video_url) {
		this.play_video_url = play_video_url;
	}
	public String getDevice_desc() {
		return device_desc;
	}
	public void setDevice_desc(String device_desc) {
		this.device_desc = device_desc;
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
	public int getDevice_type() {
		return device_type;
	}
	public void setDevice_type(int device_type) {
		this.device_type = device_type;
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
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public String getDevice_serial() {
		return device_serial;
	}
	public void setDevice_serial(String device_serial) {
		this.device_serial = device_serial;
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
}
