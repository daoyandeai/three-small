package com.rs.po;

import java.util.List;

/**
 * 
 * @ClassName: CheckSelf
 * @Description: 自查自纠实体类
 * @Author tangsh
 * @DateTime 2020年3月2日 上午11:44:26
 */
public class CheckSelf extends BasePo<CheckSelf>{

	private static final long serialVersionUID = 1L;
	/**
	 * 自查自纠系统编码
	 */
	private String checkself_code;
	/**
	 * 企业编码
	 */
	private String company_code;
	 /**
	  * 企业名称
	  */
	private String company_name;
	/**
	 * 企业类型编码
	 */
	private String companytype_code;
	/**
	 * 三小主体类型（小经营店（餐饮）、小经营店（销售）、小作坊、小摊贩）
	 */
	private String companytag_code;
	/**
	 * 经营者姓名
	 */
	private String operator;
	/**
	 * 自查自纠内容json
	 */
	private String checkself_content;
	/**
	 * 添加用户编码
	 */
	private String user_code_add;
	/**
	 * 添加用户名称
	 */
	private String user_name_add;
	/**
	 * 设备描述
	 */
	private String add_time;
	/*******************************************/
	
	private Integer ischeckself;	//1.已自查自纠，2.未自查自纠
	private String business_forms;			//经营形态集合
	private String companytype_codes;		//食品监管分类编码集合
	private String companytag_codes;		//备案类型编码集合
	private String page_config_name;		//自查自纠
	private Integer spsc_days; //食品生成自查自纠间隔时间
	private Integer spcy_days; //食品餐饮自查自纠间隔时间
	private Integer splt_days; //食品流通自查自纠间隔时间
	private List<String> checkself_code_list;
	
	public String getCheckself_code() {
		return checkself_code;
	}
	public void setCheckself_code(String checkself_code) {
		this.checkself_code = checkself_code;
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
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getCheckself_content() {
		return checkself_content;
	}
	public void setCheckself_content(String checkself_content) {
		this.checkself_content = checkself_content;
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
	public Integer getIscheckself() {
		return ischeckself;
	}
	public void setIscheckself(Integer ischeckself) {
		this.ischeckself = ischeckself;
	}
	public String getBusiness_forms() {
		return business_forms;
	}
	public void setBusiness_forms(String business_forms) {
		this.business_forms = business_forms;
	}
	public String getCompanytype_codes() {
		return companytype_codes;
	}
	public void setCompanytype_codes(String companytype_codes) {
		this.companytype_codes = companytype_codes;
	}
	public String getCompanytag_codes() {
		return companytag_codes;
	}
	public void setCompanytag_codes(String companytag_codes) {
		this.companytag_codes = companytag_codes;
	}
	public String getPage_config_name() {
		return page_config_name;
	}
	public void setPage_config_name(String page_config_name) {
		this.page_config_name = page_config_name;
	}
	public Integer getSpsc_days() {
		return spsc_days;
	}
	public void setSpsc_days(Integer spsc_days) {
		this.spsc_days = spsc_days;
	}
	public Integer getSpcy_days() {
		return spcy_days;
	}
	public void setSpcy_days(Integer spcy_days) {
		this.spcy_days = spcy_days;
	}
	public Integer getSplt_days() {
		return splt_days;
	}
	public void setSplt_days(Integer splt_days) {
		this.splt_days = splt_days;
	}
	public List<String> getCheckself_code_list() {
		return checkself_code_list;
	}
	public void setCheckself_code_list(List<String> checkself_code_list) {
		this.checkself_code_list = checkself_code_list;
	}
}
