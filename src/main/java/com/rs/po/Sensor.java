package com.rs.po;
/**
 * 传感器实体类
 * @ClassName: Sensor
 * @Description: 
 * @Author sven
 * @DateTime 2020年1月9日 上午10:07:10
 */
public class Sensor extends BasePo<Sensor>{

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
	 * 传感器状态（1：启用、2：禁用）
	 */
	private Integer sensor_state;
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
	/*******************数据库辅助字段************************/
	  /**
     * 工单状态【1.保存草稿、2.提交申请、3.已归档、4.已驳回】
     */
    private Integer filing_state;
    /**
     * 食品监管分类编码
     */
    private String companytype_code;
    /**
     * 主体类型编码
     */
    private String companytag_code;
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
     * 乡
     */
    private String town;
    /**
     * 村
     */
    private String vill;
   
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
	public Integer getSensor_state() {
		return sensor_state;
	}
	public void setSensor_state(Integer sensor_state) {
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
	public Integer getFiling_state() {
		return filing_state;
	}
	public void setFiling_state(Integer filing_state) {
		this.filing_state = filing_state;
	}
	public String getCompanytype_code() {
		return companytype_code;
	}
	public void setCompanytype_code(String companytype_code) {
		this.companytype_code = companytype_code;
	}
	public String getCompanytag_code() {
		return companytag_code;
	}
	public void setCompanytag_code(String companytag_code) {
		this.companytag_code = companytag_code;
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
	public String getVill() {
		return vill;
	}
	public void setVill(String vill) {
		this.vill = vill;
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
