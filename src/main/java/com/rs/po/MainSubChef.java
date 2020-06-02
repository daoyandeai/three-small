package com.rs.po;

/**
 * 
 * @ClassName: MainSubChef
 * @Description: 
 * @Author tangsh
 * @DateTime 2020年4月8日 上午9:51:55
 */
public class MainSubChef extends BasePo<MainSubChef>{
	private static final long serialVersionUID = 1L;
	/**
	 * 主厨系统编码
	 */
	private String user_code_mainchef;        
	/**
	 * 帮厨系统编码
	 */
	private String user_code_subchef;         
	/**
	 * 帮厨姓名
	 */
	private String user_name_subchef;         
	/**
	 * 帮厨健康证
	 */
	private String user_health_logo_subchef;  
	/**
	 * 身份证号
	 */
	private String user_idcard_subchef;       
	
	/******************数据库辅助字段*************************/
	
	private String user_choose_singlemultiple;

	public String getUser_code_mainchef() {
		return user_code_mainchef;
	}

	public void setUser_code_mainchef(String user_code_mainchef) {
		this.user_code_mainchef = user_code_mainchef;
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

	public String getUser_choose_singlemultiple() {
		return user_choose_singlemultiple;
	}

	public void setUser_choose_singlemultiple(String user_choose_singlemultiple) {
		this.user_choose_singlemultiple = user_choose_singlemultiple;
	}
}
