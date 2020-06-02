package com.rs.po.returnPo;

import java.io.Serializable;

/**
 * 
 * @ClassName: OpenCompanyTagReturn
 * @Description: 主体类型实体类
 * @Author tangsh
 * @DateTime 2020年4月1日 上午11:17:27
 */
public class OpenCompanyTagReturn implements Serializable {

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
}