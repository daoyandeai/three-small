package com.rs.po;
/**
 * 
 * @ClassName: Config
 * @Description: 群宴全局配置实体类
 * @Author tangsh
 * @DateTime 2020年4月8日 下午3:00:23
 */
public class Config extends BasePo<Config>{

	private static final long serialVersionUID = 1L;
	/**
	 * 全局配置系统编码
	 */
	private String config_code;			
	/**
	 * 省
	 */
	private String config_province;		
	/**
	 * 市
	 */
	private String config_city;			
	/**
	 * 区
	 */
	private String config_area;			
	/**
	 * 镇
	 */
	private String config_town;			
	/**
	 * 村（社区）
	 */
	private String config_vill;			
	/**
	 * 操作用户系统编码
	 */
	private String user_code;			
	/**
	 * 操作用户姓名
	 */
	private String user_name;			
	/**
	 * 添加时间
	 */
	private String addtime;				
	/******************数据库辅助字段*************************/
	/**
	 * 全部地址
	 */
	private String all_address;
	public String getConfig_code() {
		return config_code;
	}
	public void setConfig_code(String config_code) {
		this.config_code = config_code;
	}
	public String getConfig_province() {
		return config_province;
	}
	public void setConfig_province(String config_province) {
		this.config_province = config_province;
	}
	public String getConfig_city() {
		return config_city;
	}
	public void setConfig_city(String config_city) {
		this.config_city = config_city;
	}
	public String getConfig_area() {
		return config_area;
	}
	public void setConfig_area(String config_area) {
		this.config_area = config_area;
	}
	public String getConfig_town() {
		return config_town;
	}
	public void setConfig_town(String config_town) {
		this.config_town = config_town;
	}
	public String getConfig_vill() {
		return config_vill;
	}
	public void setConfig_vill(String config_vill) {
		this.config_vill = config_vill;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getAll_address() {
		return all_address;
	}
	public void setAll_address(String all_address) {
		this.all_address = all_address;
	}	
}
