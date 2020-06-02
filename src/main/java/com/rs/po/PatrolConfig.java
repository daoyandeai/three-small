package com.rs.po;
/**
 * 
 * @ClassName: PatrolConfig
 * @Description: 巡查配置实体类
 * @Author tangsh
 * @DateTime 2020年1月20日 上午9:27:52
 */
public class PatrolConfig extends BasePo<PatrolConfig>{

	/**
	  * @Fields serialVersionUID : 
	  */
	
	private static final long serialVersionUID = 1L;
	/**
	 * 巡查配置表系统编码
	 */
	private String config_code;
	/**
	 * 巡查配置关联类型
	 */
	private String companytag_code;
	 /**
	  * 配置内容 json
	  */
	private Object config_content;
	/**
	 * 添加时间
	 */
	private String add_time;
	/**
	 * 更新时间
	 */
	private String update_time;
	/*******************************************/
	/**
	 * 巡查配置关联类型
	 */
	private String companytag_name;
	
	public String getConfig_code() {
		return config_code;
	}
	public void setConfig_code(String config_code) {
		this.config_code = config_code;
	}
	public String getCompanytag_code() {
		return companytag_code;
	}
	public void setCompanytag_code(String companytag_code) {
		this.companytag_code = companytag_code;
	}
	public Object getConfig_content() {
		return config_content;
	}
	public void setConfig_content(Object config_content) {
		this.config_content = config_content;
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
	public String getCompanytag_name() {
		return companytag_name;
	}
	public void setCompanytag_name(String companytag_name) {
		this.companytag_name = companytag_name;
	}
}
