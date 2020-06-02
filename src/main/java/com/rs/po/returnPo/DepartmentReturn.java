package com.rs.po.returnPo;

import java.io.Serializable;
/**
 * 部门实体类
 * @ClassName: DepartmentReturn
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月30日 上午10:34:30
 */
public class DepartmentReturn implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 部门编码
	 */
	private String department_code;
	/**
	 * 部门名称
	 */
	private String department_name;
	/**
	 * 状态（1：启用、2：禁用）
	 */
	private Integer state;
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
	 * 镇
	 */
	private String town;
	/**
	 * 村（社区）
	 */
	private String vill;
	/**
	 * 新增用户系统编码
	 */
	private String user_code_add;
	/**
	 * 新增用户姓名
	 */
	private String user_name_add;
	/**
	 * 添加时间
	 */
	private String add_time;	
	/**
	 * 更新用户系统编码
	 */
	private String user_code_update;
	/**
	 * 更新用户姓名
	 */
	private String user_name_update;
	/**
	 * 更新时间
	 */
	private String update_time;
	/**
	 * 经度
	 */
	private String longitude;
	/**
	 * 纬度
	 */
	private String latitude; 
	public String getDepartment_code() {
		return department_code;
	}
	public void setDepartment_code(String department_code) {
		this.department_code = department_code;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
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
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
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
	public String getVill() {
		return vill;
	}
	public void setVill(String vill) {
		this.vill = vill;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
}