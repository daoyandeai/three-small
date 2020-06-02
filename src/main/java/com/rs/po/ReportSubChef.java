package com.rs.po;
/**
 * 
 * @ClassName: ReportSubChef
 * @Description: 群宴报备帮厨实体类
 * @Author tangsh
 * @DateTime 2020年4月13日 上午9:45:19
 */
public class ReportSubChef extends BasePo<ReportSubChef>{

	private static final long serialVersionUID = 1L;
	/**
	 * 帮厨系统编码
	 */
	private String report_subchef_code;            
	/**
	 * 帮厨系统编码
	 */
	private String user_code_subchef;       
	/**
	 * 帮厨姓名
	 */
	private String user_name_subchef;       
	/**
	 * 报备系统编码
	 */
	private String report_code;             
	/**
	 * 健康证
	 */
	private String user_health_logo_subchef;          
	/*******************************************/
	/**
	 * 身份证号
	 */
	private String user_idcard_subchef;
	public String getReport_subchef_code() {
		return report_subchef_code;
	}
	public void setReport_subchef_code(String report_subchef_code) {
		this.report_subchef_code = report_subchef_code;
	}
	public String getUser_code_subchef() {
		return user_code_subchef;
	}
	public void setUser_code_subchef(String user_code_subchef) {
		this.user_code_subchef = user_code_subchef;
	}
	public String getUser_name_subchef() {
		return user_name_subchef;
	}
	public void setUser_name_subchef(String user_name_subchef) {
		this.user_name_subchef = user_name_subchef;
	}
	public String getReport_code() {
		return report_code;
	}
	public void setReport_code(String report_code) {
		this.report_code = report_code;
	}
	public String getUser_health_logo_subchef() {
		return user_health_logo_subchef;
	}
	public void setUser_health_logo_subchef(String user_health_logo_subchef) {
		this.user_health_logo_subchef = user_health_logo_subchef;
	}
	public String getUser_idcard_subchef() {
		return user_idcard_subchef;
	}
	public void setUser_idcard_subchef(String user_idcard_subchef) {
		this.user_idcard_subchef = user_idcard_subchef;
	}               
}
