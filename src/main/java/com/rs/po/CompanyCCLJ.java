package com.rs.po;

/**
 * 餐厨垃圾处理实体类
 * @ClassName: CompanyCCLJ
 * @Description: 
 * @Author sven
 * @DateTime 2020年3月9日 下午3:52:34
 */
public class CompanyCCLJ extends BasePo<CompanyCCLJ>{

	/**
	  * @Fields serialVersionUID : 
	  */
	
	private static final long serialVersionUID = 1L;
	/**
	 * 餐厨垃圾回收系统编码
	 */
	private String cclj_code;
	/**
	 * 企业系统编码
	 */
	private String company_code;
	/**
	 * 餐厨垃圾类型编码
	 */
	private String dictionary_code;
	/**
	 * 餐厨垃圾类型名称
	 */
	private String dictionary_module;
	/**
	 * 处理量（kg）
	 */
	private String cclj_count;
	/**
	 * 处理人
	 */
	private String cclj_handler;
	/**
	 * 处理时间
	 */
	private String cclj_time;
	public String getCclj_code() {
		return cclj_code;
	}
	public void setCclj_code(String cclj_code) {
		this.cclj_code = cclj_code;
	}
	public String getDictionary_code() {
		return dictionary_code;
	}
	public void setDictionary_code(String dictionary_code) {
		this.dictionary_code = dictionary_code;
	}
	public String getDictionary_module() {
		return dictionary_module;
	}
	public void setDictionary_module(String dictionary_module) {
		this.dictionary_module = dictionary_module;
	}
	public String getCclj_count() {
		return cclj_count;
	}
	public void setCclj_count(String cclj_count) {
		this.cclj_count = cclj_count;
	}
	public String getCclj_handler() {
		return cclj_handler;
	}
	public void setCclj_handler(String cclj_handler) {
		this.cclj_handler = cclj_handler;
	}
	public String getCclj_time() {
		return cclj_time;
	}
	public void setCclj_time(String cclj_time) {
		this.cclj_time = cclj_time;
	}
	public String getCompany_code() {
		return company_code;
	}
	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}
}
