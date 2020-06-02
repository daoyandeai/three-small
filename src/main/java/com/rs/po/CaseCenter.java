package com.rs.po;

import java.util.List;

/**
 * 
 * @ClassName: CaseCenter
 * @Description: 案件中心实体类
 * @Author tangsh
 * @DateTime 2020年3月16日 上午10:43:49
 */
public class CaseCenter extends BasePo<CaseCenter>{

	private static final long serialVersionUID = 1L;
	/**
	 * 案件中心系统编码
	 */
	private String casecenter_code;
	/**
	 * 案件标题
	 */
	private String casecenter_title;
	 /**
	  * 案件中心内容
	  */
	private String casecenter_content;
	/**
	 * 主要办案人编码
	 */
	private String user_code_main;
	/**
	 * 主要办案人名称
	 */
	private String user_name_main;
	/**
	 * 协同办案人编码集合
	 */
	private String user_codes_secondary;
	/**
	 * 协同办案人名称集合
	 */
	private String user_names_secondary;
	/**
	 * 案件状态（1.待审核 2.审核通过 3.审核不通过）
	 */
	private Integer casecenter_audit_state;
	/**
	 * 审核时间
	 */
	private String casecenter_audit_time;
	/**
	 * 审核不通过原因
	 */
	private String casecenter_audit_note;
	/**
	 * 审核人编码
	 */
	private String user_code_audit;
	/**
	 * 审核人名称
	 */
	private String user_name_audit;
	/**
	 * 处理结果
	 */
	private String casecenter_result;
	/**
	 * 操作用户系统编码
	 */
	private String user_code_add;
	/**
	 * 操作用户姓名
	 */
	private String user_name_add;
	 /**
     * 操作用户省
     */
    private String province;
    /**
     * 操作用户市
     */
    private String city;
    /**
     * 操作用户区
     */
    private String area;
    /**
     * 操作用户村
     */
    private String vill;
    /**
     * 操作用户乡
     */
    private String town;
	/**
	 * 更新时间
	 */
	private String update_time;
	
	/**
	 * 处理状态(必须审核通过才能处理)1未处理 2已处理
	 */
	private Integer result_state;
	/**
	 * 案件工单号
	 */
	private String work_order;
	/**
	 * 主办案人部门编码
	 */
	private String department_code_main;
	/**
	 * 协同办案人部门编码
	 */
	private String department_code_secondary;
	
	
	/*******************************************/
	private List<String> user_code_secondary_list;
	private List<String> user_name_secondary_list;
	/**
	 * 主办案人部门名称
	 */
	private String department_name_main;
	/**
	 * 协同办案人部门名称
	 */
	private String department_name_secondary;
	
	public String getCasecenter_code() {
		return casecenter_code;
	}
	public Integer getResult_state() {
		return result_state;
	}
	public void setResult_state(Integer result_state) {
		this.result_state = result_state;
	}
	public void setCasecenter_code(String casecenter_code) {
		this.casecenter_code = casecenter_code;
	}
	public String getCasecenter_title() {
		return casecenter_title;
	}
	public void setCasecenter_title(String casecenter_title) {
		this.casecenter_title = casecenter_title;
	}
	public String getCasecenter_content() {
		return casecenter_content;
	}
	public void setCasecenter_content(String casecenter_content) {
		this.casecenter_content = casecenter_content;
	}
	public String getUser_code_main() {
		return user_code_main;
	}
	public void setUser_code_main(String user_code_main) {
		this.user_code_main = user_code_main;
	}
	public String getUser_name_main() {
		return user_name_main;
	}
	public void setUser_name_main(String user_name_main) {
		this.user_name_main = user_name_main;
	}
	public String getUser_codes_secondary() {
		return user_codes_secondary;
	}
	public void setUser_codes_secondary(String user_codes_secondary) {
		this.user_codes_secondary = user_codes_secondary;
	}
	public String getUser_names_secondary() {
		return user_names_secondary;
	}
	public void setUser_names_secondary(String user_names_secondary) {
		this.user_names_secondary = user_names_secondary;
	}
	public Integer getCasecenter_audit_state() {
		return casecenter_audit_state;
	}
	public void setCasecenter_audit_state(Integer casecenter_audit_state) {
		this.casecenter_audit_state = casecenter_audit_state;
	}
	public String getCasecenter_audit_time() {
		return casecenter_audit_time;
	}
	public void setCasecenter_audit_time(String casecenter_audit_time) {
		this.casecenter_audit_time = casecenter_audit_time;
	}
	public String getCasecenter_audit_note() {
		return casecenter_audit_note;
	}
	public void setCasecenter_audit_note(String casecenter_audit_note) {
		this.casecenter_audit_note = casecenter_audit_note;
	}
	public String getUser_code_audit() {
		return user_code_audit;
	}
	public void setUser_code_audit(String user_code_audit) {
		this.user_code_audit = user_code_audit;
	}
	public String getUser_name_audit() {
		return user_name_audit;
	}
	public void setUser_name_audit(String user_name_audit) {
		this.user_name_audit = user_name_audit;
	}
	public String getCasecenter_result() {
		return casecenter_result;
	}
	public void setCasecenter_result(String casecenter_result) {
		this.casecenter_result = casecenter_result;
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
	public String getVill() {
		return vill;
	}
	public void setVill(String vill) {
		this.vill = vill;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getWork_order() {
		return work_order;
	}
	public void setWork_order(String work_order) {
		this.work_order = work_order;
	}
	public List<String> getUser_code_secondary_list() {
		return user_code_secondary_list;
	}
	public void setUser_code_secondary_list(List<String> user_code_secondary_list) {
		this.user_code_secondary_list = user_code_secondary_list;
	}
	public List<String> getUser_name_secondary_list() {
		return user_name_secondary_list;
	}
	public void setUser_name_secondary_list(List<String> user_name_secondary_list) {
		this.user_name_secondary_list = user_name_secondary_list;
	}
	public String getDepartment_code_main() {
		return department_code_main;
	}
	public void setDepartment_code_main(String department_code_main) {
		this.department_code_main = department_code_main;
	}
	public String getDepartment_code_secondary() {
		return department_code_secondary;
	}
	public void setDepartment_code_secondary(String department_code_secondary) {
		this.department_code_secondary = department_code_secondary;
	}
	public String getDepartment_name_main() {
		return department_name_main;
	}
	public void setDepartment_name_main(String department_name_main) {
		this.department_name_main = department_name_main;
	}
	public String getDepartment_name_secondary() {
		return department_name_secondary;
	}
	public void setDepartment_name_secondary(String department_name_secondary) {
		this.department_name_secondary = department_name_secondary;
	}
}
