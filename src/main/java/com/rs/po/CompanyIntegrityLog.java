package com.rs.po;
/**
 * 企业诚信评价日志实体类
 * @ClassName: CompanyIntegrityLog
 * @Description: 
 * @Author sven
 * @DateTime 2020年2月12日 上午11:50:30
 */
public class CompanyIntegrityLog extends BasePo<CompanyIntegrityLog>{
	/**
	  * @Fields serialVersionUID : 
	  */
	
	private static final long serialVersionUID = 1L;
	/**
	 * 企业诚信评价日志编码	
	 */
	private String company_integrity_log_code;
	/**
	 * 企业诚信评价结果编码
	 */
	private String company_integrity_code;
	/**
	 * 企业编码
	 */
	private String company_code;
	/**
	 * 诚信经营等级
	 */
	private float integrity_lvl;
	/**
	 * 评定类型（1：公众评分、2：监管评分）
	 */
	private Integer mete_type;
	/**
	 * 评分理由
	 */
	private String remark;
	/**
	 * 添加用户系统编码
	 */
	private String user_code_add;
	/**
	 * 添加用户姓名
	 */
	private String user_name_add;
	/**
	 * 添加时间
	 */
	private String add_time;
	/*****************数据库辅助字段******************/
	/**
	 * 结束时间
	 */
	private String end_time;
	public String getCompany_integrity_log_code() {
		return company_integrity_log_code;
	}
	public void setCompany_integrity_log_code(String company_integrity_log_code) {
		this.company_integrity_log_code = company_integrity_log_code;
	}
	public String getCompany_integrity_code() {
		return company_integrity_code;
	}
	public void setCompany_integrity_code(String company_integrity_code) {
		this.company_integrity_code = company_integrity_code;
	}
	public Integer getMete_type() {
		return mete_type;
	}
	public void setMete_type(Integer mete_type) {
		this.mete_type = mete_type;
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
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getCompany_code() {
		return company_code;
	}
	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public float getIntegrity_lvl() {
		return integrity_lvl;
	}
	public void setIntegrity_lvl(float integrity_lvl) {
		this.integrity_lvl = integrity_lvl;
	}
}
