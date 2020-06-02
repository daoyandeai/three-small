package com.rs.po;
/**
 * 
 * @ClassName: ReportCheck
 * @Description: 报备检查实体类
 * @Author tangsh
 * @DateTime 2020年4月13日 上午10:24:47
 */
public class ReportCheck extends BasePo<ReportCheck>{

	private static final long serialVersionUID = 1L;
	/**
	 * 检查系统编码
	 */
	private String check_code;                      
	/**
	 * 主厨个人卫生是否合格(1：合格、2：不合格)
	 */
	private Integer check_mainchef_health;          
	/**
	 * 帮厨个人卫生是否合格(1：合格、2：不合格)
	 */
	private Integer check_subchef_health;           
	/**
	 * 办宴场所是否符合要求(1：合格、2：不合格)
	 */
	private Integer check_place_standard;           
	/**
	 * 加工场地是否符合要求(1：合格、2：不合格)
	 */
	private Integer check_process_standard;         
	/**
	 * 食物存储(1：合格、2：不合格)
	 */
	private Integer check_store;                    
	/**
	 * 餐具消毒(1：合格、2：不合格)
	 */
	private Integer check_disinfection;             
	/**
	 * 饮用水(1：合格、2：不合格)
	 */
	private Integer check_water;                    
	/**
	 * 食品留样(1：合格、2：不合格)
	 */
	private Integer check_reserved_sample;          
	/**
	 * 禁用食材(1：合格、2：不合格)
	 */
	private Integer check_forbidden_food;           
	/**
	 * 食品发票(1：合格、2：不合格)
	 */
	private Integer check_invoice;                  
	/**
	 * 有毒有害物质(1：合格、2：不合格)
	 */
	private Integer check_poison;                   
	/**
	 * 风险点
	 */
	private String check_risk;                      
	/**
	 * 风险点整改情况
	 */
	private String check_risk_suggestion;           
	/**
	 * 现场照片
	 */
	private String check_imgurls;					
	/**
	 * 检查人员系统编码
	 */
	private String user_code;                       
	/**
	 * 检查人员系统名称
	 */
	private String user_name;                       
	/**
	 * 检查人员电话号码
	 */
	private String user_mobilephone;                
	/**
	 * 主方签字电子图
	 */
	private String user_sign_logo;                
	/**
	 * 报备系统编码
	 */
	private String report_code;                     
	/**
	 * 检查时的地理位置
	 */
	private String check_location;
	public String getCheck_code() {
		return check_code;
	}
	public void setCheck_code(String check_code) {
		this.check_code = check_code;
	}
	public Integer getCheck_mainchef_health() {
		return check_mainchef_health;
	}
	public void setCheck_mainchef_health(Integer check_mainchef_health) {
		this.check_mainchef_health = check_mainchef_health;
	}
	public Integer getCheck_subchef_health() {
		return check_subchef_health;
	}
	public void setCheck_subchef_health(Integer check_subchef_health) {
		this.check_subchef_health = check_subchef_health;
	}
	public Integer getCheck_place_standard() {
		return check_place_standard;
	}
	public void setCheck_place_standard(Integer check_place_standard) {
		this.check_place_standard = check_place_standard;
	}
	public Integer getCheck_process_standard() {
		return check_process_standard;
	}
	public void setCheck_process_standard(Integer check_process_standard) {
		this.check_process_standard = check_process_standard;
	}
	public Integer getCheck_store() {
		return check_store;
	}
	public void setCheck_store(Integer check_store) {
		this.check_store = check_store;
	}
	public Integer getCheck_disinfection() {
		return check_disinfection;
	}
	public void setCheck_disinfection(Integer check_disinfection) {
		this.check_disinfection = check_disinfection;
	}
	public Integer getCheck_water() {
		return check_water;
	}
	public void setCheck_water(Integer check_water) {
		this.check_water = check_water;
	}
	public Integer getCheck_reserved_sample() {
		return check_reserved_sample;
	}
	public void setCheck_reserved_sample(Integer check_reserved_sample) {
		this.check_reserved_sample = check_reserved_sample;
	}
	public Integer getCheck_forbidden_food() {
		return check_forbidden_food;
	}
	public void setCheck_forbidden_food(Integer check_forbidden_food) {
		this.check_forbidden_food = check_forbidden_food;
	}
	public Integer getCheck_invoice() {
		return check_invoice;
	}
	public void setCheck_invoice(Integer check_invoice) {
		this.check_invoice = check_invoice;
	}
	public Integer getCheck_poison() {
		return check_poison;
	}
	public void setCheck_poison(Integer check_poison) {
		this.check_poison = check_poison;
	}
	public String getCheck_risk() {
		return check_risk;
	}
	public void setCheck_risk(String check_risk) {
		this.check_risk = check_risk;
	}
	public String getCheck_risk_suggestion() {
		return check_risk_suggestion;
	}
	public void setCheck_risk_suggestion(String check_risk_suggestion) {
		this.check_risk_suggestion = check_risk_suggestion;
	}
	public String getCheck_imgurls() {
		return check_imgurls;
	}
	public void setCheck_imgurls(String check_imgurls) {
		this.check_imgurls = check_imgurls;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_mobilephone() {
		return user_mobilephone;
	}
	public void setUser_mobilephone(String user_mobilephone) {
		this.user_mobilephone = user_mobilephone;
	}
	public String getUser_sign_logo() {
		return user_sign_logo;
	}
	public void setUser_sign_logo(String user_sign_logo) {
		this.user_sign_logo = user_sign_logo;
	}
	public String getReport_code() {
		return report_code;
	}
	public void setReport_code(String report_code) {
		this.report_code = report_code;
	}
	public String getCheck_location() {
		return check_location;
	}
	public void setCheck_location(String check_location) {
		this.check_location = check_location;
	}					
}
