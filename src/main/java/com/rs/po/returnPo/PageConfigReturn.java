package com.rs.po.returnPo;

import java.io.Serializable;

/**
 * 页面读取参数配置实体类
 * @ClassName: PageConfig
 * @Description: 
 * @Author sven
 * @DateTime 2020年2月10日 上午11:22:25
 */
public class PageConfigReturn implements Serializable{
	/**
	  * @Fields serialVersionUID : 
	  */
	
	private static final long serialVersionUID = 1L;
	/**
	 * 页面读取参数配置编码
	 */
	private String page_config_code;
	/**
	 * 食品监管分类编码
	 */
	private String companytype_code;
	/**
	 * 主体类型编码
	 */
	private String companytag_code;
	/**
	 * 配置名称
	 */
	private String page_config_name;
	/**
	 * 页面模块（1：诚信等级、2：信息公示）
	 */
	private Integer page_module; 
	/**
	 * 配置内容json
	 */
	private String page_config_content;
	/**
	 * 状态（1：启用、2：禁用）
	 */
	private Integer state;
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
	public String getPage_config_code() {
		return page_config_code;
	}
	public void setPage_config_code(String page_config_code) {
		this.page_config_code = page_config_code;
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
	public String getPage_config_content() {
		return page_config_content;
	}
	public void setPage_config_content(String page_config_content) {
		this.page_config_content = page_config_content;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
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
	public Integer getPage_module() {
		return page_module;
	}
	public void setPage_module(Integer page_module) {
		this.page_module = page_module;
	}
	public String getPage_config_name() {
		return page_config_name;
	}
	public void setPage_config_name(String page_config_name) {
		this.page_config_name = page_config_name;
	}
}
