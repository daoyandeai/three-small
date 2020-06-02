package com.rs.po;
/**
 * 平台消息推送实体类
 * @ClassName: Mess
 * @Description: 
 * @Author sven
 * @DateTime 2020年2月25日 下午5:20:44
 */
public class Mess extends BasePo<Mess>{
	/**
	  * @Fields serialVersionUID : 
	  */
	
	private static final long serialVersionUID = 1L;
	/**
	 * 消息推送编码
	 */
	private String mess_code;
	/**
	 * 消息推送事件（1：备案通过、2：备案驳回、3：巡查不合格、4：培训不合格、5：自查自纠、6：投诉举报待处理、7:投诉举报已处理、8：应急通告、9：备案待审核、10：待巡查工单）
	 */
	private Integer mess_event;
	/**
	 *消息推送方式(1：全部、2：短信、3：微信)
	 */
	private Integer mess_type;
	/**
	 * 接收用户等级（省（1）、市（2）、县（3）、乡（4）、村（5））
	 */
	private String mess_receive_person;
	/**
	 * 接收用户区域（省、市、县、乡、村）
	 */
	private String mess_receive_person_name;
	/**
	 * 食品监管分类编码集合
	 */
	private String companytype_codes;
	/**
	 * 食品监管分类名称集合
	 */
	private String companytype_names;
	/**
	 * 主体类型编码集合
	 */
	private String companytag_codes;
	/**
	 * 主体类型名称集合
	 */
	private String companytag_names;
	/**
	 * 短信模板id
	 */
	private String mess_sms_code;
	/**
	 * 短信内容
	 */
	private String mess_sms_content;
	/**
	 * 微信模板id
	 */
	private String mess_wx_code;
	/**
	 * 微信内容
	 */
	private String mess_wx_content;
	/**
	 * 自动提醒（1：是、2：否）
	 */
	private Integer mess_warn_state;
	/**
	 * 新增用户系统编码
	 */
	private String user_code_add;
	/**
	 * 新增用户姓名
	 */
	private String user_name_add;
	/**
	 * 添加时间
	 */
	private String add_time;	
	/**
	 * 更新用户系统编码
	 */
	private String user_code_update;
	/**
	 * 更新用户姓名
	 */
	private String user_name_update;
	/**
	 * 更新时间
	 */
	private String update_time;
	/******************数据库辅助字段**********************/
	/**
	 * 结束时间
	 */
	private String end_time;
	public String getMess_code() {
		return mess_code;
	}
	public void setMess_code(String mess_code) {
		this.mess_code = mess_code;
	}
	public Integer getMess_event() {
		return mess_event;
	}
	public void setMess_event(Integer mess_event) {
		this.mess_event = mess_event;
	}
	public Integer getMess_type() {
		return mess_type;
	}
	public void setMess_type(Integer mess_type) {
		this.mess_type = mess_type;
	}
	public String getMess_receive_person() {
		return mess_receive_person;
	}
	public void setMess_receive_person(String mess_receive_person) {
		this.mess_receive_person = mess_receive_person;
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
	public String getMess_sms_code() {
		return mess_sms_code;
	}
	public void setMess_sms_code(String mess_sms_code) {
		this.mess_sms_code = mess_sms_code;
	}
	public String getMess_sms_content() {
		return mess_sms_content;
	}
	public void setMess_sms_content(String mess_sms_content) {
		this.mess_sms_content = mess_sms_content;
	}
	public String getMess_wx_code() {
		return mess_wx_code;
	}
	public void setMess_wx_code(String mess_wx_code) {
		this.mess_wx_code = mess_wx_code;
	}
	public String getMess_wx_content() {
		return mess_wx_content;
	}
	public void setMess_wx_content(String mess_wx_content) {
		this.mess_wx_content = mess_wx_content;
	}
	public Integer getMess_warn_state() {
		return mess_warn_state;
	}
	public void setMess_warn_state(Integer mess_warn_state) {
		this.mess_warn_state = mess_warn_state;
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
	public String getUser_code_update() {
		return user_code_update;
	}
	public void setUser_code_update(String user_code_update) {
		this.user_code_update = user_code_update;
	}
	public String getUser_name_update() {
		return user_name_update;
	}
	public void setUser_name_update(String user_name_update) {
		this.user_name_update = user_name_update;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getMess_receive_person_name() {
		return mess_receive_person_name;
	}
	public void setMess_receive_person_name(String mess_receive_person_name) {
		this.mess_receive_person_name = mess_receive_person_name;
	}
	public String getCompanytype_names() {
		return companytype_names;
	}
	public void setCompanytype_names(String companytype_names) {
		this.companytype_names = companytype_names;
	}
	public String getCompanytag_names() {
		return companytag_names;
	}
	public void setCompanytag_names(String companytag_names) {
		this.companytag_names = companytag_names;
	}
}
