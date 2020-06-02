package com.rs.po.returnPo;


/**
 * 用户实体类
 * @author sven
 *
 */
public class UserReturn{
	
	private String user_code;				//用户系统编码
	private String user_loginname;			//用户登录账号
	private String user_type;				//用户类型(超级管理人员、举办者、乡厨、平台管理员、协管员、农家乐、酒店、食堂)
	private String user_level;              //管理员级别（1 省，2 市，3 县，4 乡，5 村/协管员）
	private String user_name;				//用户姓名
	private String user_mobilephone;		//用户手机号
	private String user_logo_url;			//用户头像
	private String user_province;
	private String user_city;
	private String user_area;
	private String user_town;
	private String user_vill;
	private String user_state;
	private String department_names;

	private String  manage_type;//管理人员type（1.群宴报备管理员；2.三小报备管理员；3.群宴三小均管报备管理员）
	private Integer user_patrol_state;//用户巡查状态 1.未指派 2.待巡查 3.已巡查
	private String longitude; //经度
	private String latitude; //纬度
	
    private String patrol_code;	//巡查编码
	private String p_longitude; //巡查经度
	private String p_latitude; //巡查纬度

	public String getManage_type() {
		return manage_type;
	}

	public void setManage_type(String manage_type) {
		this.manage_type = manage_type;
	}

	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public String getUser_loginname() {
		return user_loginname;
	}

	public void setUser_loginname(String user_loginname) {
		this.user_loginname = user_loginname;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getUser_level() {
		return user_level;
	}

	public void setUser_level(String user_level) {
		this.user_level = user_level;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_mobilephone() {
		return user_mobilephone;
	}

	public void setUser_mobilephone(String user_mobilephone) {
		this.user_mobilephone = user_mobilephone;
	}

	public String getUser_logo_url() {
		return user_logo_url;
	}

	public void setUser_logo_url(String user_logo_url) {
		this.user_logo_url = user_logo_url;
	}

	public String getUser_province() {
		return user_province;
	}

	public void setUser_province(String user_province) {
		this.user_province = user_province;
	}

	public String getUser_city() {
		return user_city;
	}

	public void setUser_city(String user_city) {
		this.user_city = user_city;
	}

	public String getUser_area() {
		return user_area;
	}

	public void setUser_area(String user_area) {
		this.user_area = user_area;
	}

	public String getUser_town() {
		return user_town;
	}

	public void setUser_town(String user_town) {
		this.user_town = user_town;
	}

	public String getUser_vill() {
		return user_vill;
	}

	public void setUser_vill(String user_vill) {
		this.user_vill = user_vill;
	}

	public String getDepartment_names() {
		return department_names;
	}

	public void setDepartment_names(String department_names) {
		this.department_names = department_names;
	}

	public String getUser_state() {
		return user_state;
	}

	public void setUser_state(String user_state) {
		this.user_state = user_state;
	}

	public Integer getUser_patrol_state() {
		return user_patrol_state;
	}

	public void setUser_patrol_state(Integer user_patrol_state) {
		this.user_patrol_state = user_patrol_state;
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

	public String getPatrol_code() {
		return patrol_code;
	}

	public void setPatrol_code(String patrol_code) {
		this.patrol_code = patrol_code;
	}

	public String getP_longitude() {
		return p_longitude;
	}

	public void setP_longitude(String p_longitude) {
		this.p_longitude = p_longitude;
	}

	public String getP_latitude() {
		return p_latitude;
	}

	public void setP_latitude(String p_latitude) {
		this.p_latitude = p_latitude;
	}
}