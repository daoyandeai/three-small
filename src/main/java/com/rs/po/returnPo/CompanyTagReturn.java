package com.rs.po.returnPo;

import java.io.Serializable;

/**
 * 主体类型实体类
 * 
 * @ClassName: CompanyTagReturn
 * @Description:
 * @Author sven
 * @DateTime 2020年2月6日 下午3:30:44
 */
public class CompanyTagReturn implements Serializable {

	/**
	 * @Fields serialVersionUID :
	 */

	private static final long serialVersionUID = 1L;
	/**
	 * 主体分类编码
	 */
	private String companytag_code;
	/**
	 * 主体分类名称
	 */
	private String companytag_name;
	/**
	 * 食品监管分类编码
	 */
	private String companytype_code;
	/**
	 * 状态（1：启用、2：禁用）
	 */
	private Integer state;
	/**
	 * 添加时间
	 */
	private String add_time;
	/**
	 * 更新时间
	 */
	private String update_time;

	public String getCompanytag_code() {
		return companytag_code;
	}

	public void setCompanytag_code(String companytag_code) {
		this.companytag_code = companytag_code;
	}

	public String getCompanytag_name() {
		return companytag_name;
	}

	public void setCompanytag_name(String companytag_name) {
		this.companytag_name = companytag_name;
	}

	public String getCompanytype_code() {
		return companytype_code;
	}

	public void setCompanytype_code(String companytype_code) {
		this.companytype_code = companytype_code;
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
	
}