package com.rs.po.returnPo;

import java.io.Serializable;
/**
 * 企业区域实体类
 * @ClassName: CompanyAreaReturn
 * @Description: 
 * @Author sven
 * @DateTime 2020年3月16日 上午10:56:35
 */
public class CompanyAreaReturn implements Serializable{
	
	/**
	  * @Fields serialVersionUID : 
	  */
	
	private static final long serialVersionUID = 1L;
	/**
	 * 企业区域编码
	 */
	private String company_area_code;
	/**
	 * 企业区域名称
	 */
	private String company_area_name;
	/**
	 * 企业编码
	 */
	private String company_code;
	/**
	 * 企业名称
	 */
	private String company_name;
	/**
	 * 区域状态（1：启用、2：禁用）
	 */
	private Integer state;
	/**
	 * 添加用户姓名
	 */
	private String user_name_add;
	/**
	 * 添加用户系统编码
	 */
	private String user_code_add;
	/**
	 * 添加时间
	 */
	private String add_time;
	/**
	 * 更新用户姓名
	 */
	private String user_name_update;
	/**
	 * 更新用户系统编码
	 */
	private String user_code_update;
	/**
	 * 更新时间
	 */
	private String update_time;
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
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getUser_name_add() {
		return user_name_add;
	}
	public void setUser_name_add(String user_name_add) {
		this.user_name_add = user_name_add;
	}
	public String getUser_code_add() {
		return user_code_add;
	}
	public void setUser_code_add(String user_code_add) {
		this.user_code_add = user_code_add;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getUser_name_update() {
		return user_name_update;
	}
	public void setUser_name_update(String user_name_update) {
		this.user_name_update = user_name_update;
	}
	public String getUser_code_update() {
		return user_code_update;
	}
	public void setUser_code_update(String user_code_update) {
		this.user_code_update = user_code_update;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_code() {
		return company_code;
	}
	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}
}
