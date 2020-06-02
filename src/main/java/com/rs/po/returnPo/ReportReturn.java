package com.rs.po.returnPo;

import java.io.Serializable;
/**
 * 群宴报备实体类
 * @ClassName: ReportReturn
 * @Description: 
 * @Author sven
 * @DateTime 2020年4月15日 下午4:11:11
 */
public class ReportReturn implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 报备系统编码
	 */
	private String report_code;              
	/**
	 * 报备场所（1：流动场所、2：固定场所）
	 */
	private Integer report_type;             
	/**
	 * 报备状态（1：待审核、2：审核不通过、3：待检验、4：检验合格、5：检验不合格）
	 */
	private Integer report_state;            
	/**
	 * 报备方式（1：主家、2：乡厨、3：农家乐、4：乡村酒店）
	 */
	private Integer report_mode;             
	/**
	 * 报备完整（1：未完整、2：已完整）
	 */
	private Integer report_full;			 
	/**
	 * 举办者系统编码
	 */
	private String user_code_conduct;		 
	/**
	 * 举办者姓名
	 */
	private String user_name_conduct;        
	/**
	 * 举办者身份证号
	 */
	private String user_idcard_conduct;      
	/**
	 * 举办者手机号
	 */
	private String user_mobilephone_conduct; 
	/**
	 * 举办者省
	 */
	private String province_conduct;         
	/**
	 * 举办者市
	 */
	private String city_conduct;             
	/**
	 * 举办者区
	 */
	private String area_conduct;             
	/**
	 * 举办者镇
	 */
	private String town_conduct;             
	/**
	 * 举办者村（社区）
	 */
	private String vill_conduct;             
	/**
	 * 举办者详细地址
	 */
	private String address_conduct;          
	/**
	 * 办宴时间
	 */
	private String banquet_time;             
	/**
	 * 办宴天数
	 */
	private String banquet_day;              
	/**
	 * 过期时间=办宴时间+办宴天数-1
	 */
	private String banquet_expiretime;		 
	/**
	 * 办宴类型(1：红事、2：白事、3：生日、4：状元、5：乔迁、6：其他)
	 */
	private Integer banquet_type;            
	/**
	 * 就餐人数
	 */
	private String banquet_people;           
	/**
	 * 就餐桌数
	 */
	private String banquet_table;           
	/**
	 * 主厨用户系统编码
	 */
	private String user_code_mainchef;       
	/**
	 * 主厨用户姓名
	 */
	private String user_name_mainchef;       
	/**
	 * 主厨手机号
	 */
	private String user_mobilephone_mainchef;
	/**
	 * 审核人员系统编码
	 */
	private String user_code_qualified;       
	/**
	 * 审核人员姓名
	 */
	private String user_name_qualified;       
	/**
	 * 审核时间
	 */
	private String qualified_time;            
	/**
	 * 检查人员系统编码
	 */
	private String user_code_check;           
	/**
	 * 检查人员姓名
	 */
	private String user_name_check;           
	/**
	 * 检查时间
	 */
	private String check_time;                
	/**
	 * 检查时间开始
	 */
	private String check_time_start;                
	/**
	 * 检查时间结束
	 */
	private String check_time_end;                
	/**
	 * 添加用户系统编码
	 */
	private String user_code;                
	/**
	 * 添加用户姓名
	 */
	private String user_name;                
	/**
	 * 审核无效原因
	 */
	private String invalid_reason;			 
	/**
	 * 添加时间
	 */
	private String addtime;
	public String getReport_code() {
		return report_code;
	}
	public void setReport_code(String report_code) {
		this.report_code = report_code;
	}
	public Integer getReport_type() {
		return report_type;
	}
	public void setReport_type(Integer report_type) {
		this.report_type = report_type;
	}
	public Integer getReport_state() {
		return report_state;
	}
	public void setReport_state(Integer report_state) {
		this.report_state = report_state;
	}
	public Integer getReport_mode() {
		return report_mode;
	}
	public void setReport_mode(Integer report_mode) {
		this.report_mode = report_mode;
	}
	public Integer getReport_full() {
		return report_full;
	}
	public void setReport_full(Integer report_full) {
		this.report_full = report_full;
	}
	public String getUser_code_conduct() {
		return user_code_conduct;
	}
	public void setUser_code_conduct(String user_code_conduct) {
		this.user_code_conduct = user_code_conduct;
	}
	public String getUser_name_conduct() {
		return user_name_conduct;
	}
	public void setUser_name_conduct(String user_name_conduct) {
		this.user_name_conduct = user_name_conduct;
	}
	public String getUser_idcard_conduct() {
		return user_idcard_conduct;
	}
	public void setUser_idcard_conduct(String user_idcard_conduct) {
		this.user_idcard_conduct = user_idcard_conduct;
	}
	public String getUser_mobilephone_conduct() {
		return user_mobilephone_conduct;
	}
	public void setUser_mobilephone_conduct(String user_mobilephone_conduct) {
		this.user_mobilephone_conduct = user_mobilephone_conduct;
	}
	public String getProvince_conduct() {
		return province_conduct;
	}
	public void setProvince_conduct(String province_conduct) {
		this.province_conduct = province_conduct;
	}
	public String getCity_conduct() {
		return city_conduct;
	}
	public void setCity_conduct(String city_conduct) {
		this.city_conduct = city_conduct;
	}
	public String getArea_conduct() {
		return area_conduct;
	}
	public void setArea_conduct(String area_conduct) {
		this.area_conduct = area_conduct;
	}
	public String getTown_conduct() {
		return town_conduct;
	}
	public void setTown_conduct(String town_conduct) {
		this.town_conduct = town_conduct;
	}
	public String getVill_conduct() {
		return vill_conduct;
	}
	public void setVill_conduct(String vill_conduct) {
		this.vill_conduct = vill_conduct;
	}
	public String getAddress_conduct() {
		return address_conduct;
	}
	public void setAddress_conduct(String address_conduct) {
		this.address_conduct = address_conduct;
	}
	public String getBanquet_time() {
		return banquet_time;
	}
	public void setBanquet_time(String banquet_time) {
		this.banquet_time = banquet_time;
	}
	public String getBanquet_day() {
		return banquet_day;
	}
	public void setBanquet_day(String banquet_day) {
		this.banquet_day = banquet_day;
	}
	public String getBanquet_expiretime() {
		return banquet_expiretime;
	}
	public void setBanquet_expiretime(String banquet_expiretime) {
		this.banquet_expiretime = banquet_expiretime;
	}
	public Integer getBanquet_type() {
		return banquet_type;
	}
	public void setBanquet_type(Integer banquet_type) {
		this.banquet_type = banquet_type;
	}
	public String getBanquet_people() {
		return banquet_people;
	}
	public void setBanquet_people(String banquet_people) {
		this.banquet_people = banquet_people;
	}
	public String getBanquet_table() {
		return banquet_table;
	}
	public void setBanquet_table(String banquet_table) {
		this.banquet_table = banquet_table;
	}
	public String getUser_code_mainchef() {
		return user_code_mainchef;
	}
	public void setUser_code_mainchef(String user_code_mainchef) {
		this.user_code_mainchef = user_code_mainchef;
	}
	public String getUser_name_mainchef() {
		return user_name_mainchef;
	}
	public void setUser_name_mainchef(String user_name_mainchef) {
		this.user_name_mainchef = user_name_mainchef;
	}
	public String getUser_mobilephone_mainchef() {
		return user_mobilephone_mainchef;
	}
	public void setUser_mobilephone_mainchef(String user_mobilephone_mainchef) {
		this.user_mobilephone_mainchef = user_mobilephone_mainchef;
	}
	public String getUser_code_qualified() {
		return user_code_qualified;
	}
	public void setUser_code_qualified(String user_code_qualified) {
		this.user_code_qualified = user_code_qualified;
	}
	public String getUser_name_qualified() {
		return user_name_qualified;
	}
	public void setUser_name_qualified(String user_name_qualified) {
		this.user_name_qualified = user_name_qualified;
	}
	public String getQualified_time() {
		return qualified_time;
	}
	public void setQualified_time(String qualified_time) {
		this.qualified_time = qualified_time;
	}
	public String getUser_code_check() {
		return user_code_check;
	}
	public void setUser_code_check(String user_code_check) {
		this.user_code_check = user_code_check;
	}
	public String getUser_name_check() {
		return user_name_check;
	}
	public void setUser_name_check(String user_name_check) {
		this.user_name_check = user_name_check;
	}
	public String getCheck_time() {
		return check_time;
	}
	public void setCheck_time(String check_time) {
		this.check_time = check_time;
	}
	public String getCheck_time_start() {
		return check_time_start;
	}
	public void setCheck_time_start(String check_time_start) {
		this.check_time_start = check_time_start;
	}
	public String getCheck_time_end() {
		return check_time_end;
	}
	public void setCheck_time_end(String check_time_end) {
		this.check_time_end = check_time_end;
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
	public String getInvalid_reason() {
		return invalid_reason;
	}
	public void setInvalid_reason(String invalid_reason) {
		this.invalid_reason = invalid_reason;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	} 
}