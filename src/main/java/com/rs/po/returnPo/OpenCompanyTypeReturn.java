package com.rs.po.returnPo;

import java.io.Serializable;
/**
 * 
 * @ClassName: OpenCompanyTypeReturn
 * @Description: 食品监管分类
 * @Author tangsh
 * @DateTime 2020年4月1日 上午11:04:52
 */
public class OpenCompanyTypeReturn implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 食品监管分类编码
	 */
    private String companytype_code;
    /**
     * 食品监管分类名称
     */
    private String companytype_name;
    
	public String getCompanytype_code() {
		return companytype_code;
	}
	public void setCompanytype_code(String companytype_code) {
		this.companytype_code = companytype_code;
	}
	public String getCompanytype_name() {
		return companytype_name;
	}
	public void setCompanytype_name(String companytype_name) {
		this.companytype_name = companytype_name;
	}
    
}