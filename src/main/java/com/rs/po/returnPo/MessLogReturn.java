package com.rs.po.returnPo;

import java.io.Serializable;
/**
 * 消息日志实体类
 * @ClassName: MessLogReturn
 * @Description: 
 * @Author sven
 * @DateTime 2020年3月5日 下午2:47:56
 */
public class MessLogReturn implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 消息日志id
	 */
	private Integer messlog_id;
	/**
	 * 短信消息发送状态：1.未发送 2.已发送
	 */
	private Integer messlog_ms_state;
	/**
	 * 微信消息发送状态：1.未发送 2.已发送
	 */
	private Integer messlog_wx_state;
	/**
	 * 短信发送失败原因
	 */
	private String messlog_ms_errnote;
	/**
	 * 微信发送失败原因
	 */
	private String messlog_wx_errnote;
	/**
	 * 短信发送时间
	 */
	private String messlog_ms_sendtime;
	/**
	 * 微信发送时间
	 */
	private String messlog_wx_sendtime;
	/**
	 * 消息推送事件（1：备案通过、2：备案驳回、3：巡查不合格、4：培训不合格、5：自查自纠、6：投诉举报待处理、7:投诉举报已处理、8：应急通告、9：备案待审核、10：待巡查工单）
	 */
	private Integer mess_event;
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
	 *消息推送方式(1：全部、2：短信、3：微信)
	 */
	private Integer mess_type;
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
	 * 新增用户系统编码
	 */
	private String user_code_add;
	/**
	 * 新增用户姓名
	 */
	private String user_name_add;
	
	/**
	 * 接收用户系统编码
	 */
	private String user_code;
	/**
	 * 接收用户电话号码
	 */
	private String user_mobilephone;
	/**
	 * 接收用户openid
	 */
	private String openid;
	/**
	 * 添加时间
	 */
	private String add_time;
	
	
	public Integer getMesslog_id() {
		return messlog_id;
	}

	public void setMesslog_id(Integer messlog_id) {
		this.messlog_id = messlog_id;
	}

	public Integer getMesslog_ms_state() {
		return messlog_ms_state;
	}

	public void setMesslog_ms_state(Integer messlog_ms_state) {
		this.messlog_ms_state = messlog_ms_state;
	}

	public Integer getMesslog_wx_state() {
		return messlog_wx_state;
	}

	public void setMesslog_wx_state(Integer messlog_wx_state) {
		this.messlog_wx_state = messlog_wx_state;
	}

	public String getMesslog_ms_errnote() {
		return messlog_ms_errnote;
	}

	public void setMesslog_ms_errnote(String messlog_ms_errnote) {
		this.messlog_ms_errnote = messlog_ms_errnote;
	}

	public String getMesslog_wx_errnote() {
		return messlog_wx_errnote;
	}

	public void setMesslog_wx_errnote(String messlog_wx_errnote) {
		this.messlog_wx_errnote = messlog_wx_errnote;
	}

	public String getMesslog_ms_sendtime() {
		return messlog_ms_sendtime;
	}

	public void setMesslog_ms_sendtime(String messlog_ms_sendtime) {
		this.messlog_ms_sendtime = messlog_ms_sendtime;
	}

	public String getMesslog_wx_sendtime() {
		return messlog_wx_sendtime;
	}

	public void setMesslog_wx_sendtime(String messlog_wx_sendtime) {
		this.messlog_wx_sendtime = messlog_wx_sendtime;
	}

	public Integer getMess_event() {
		return mess_event;
	}

	public void setMess_event(Integer mess_event) {
		this.mess_event = mess_event;
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

	public Integer getMess_type() {
		return mess_type;
	}

	public void setMess_type(Integer mess_type) {
		this.mess_type = mess_type;
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

	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public String getUser_mobilephone() {
		return user_mobilephone;
	}

	public void setUser_mobilephone(String user_mobilephone) {
		this.user_mobilephone = user_mobilephone;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getAdd_time() {
		return add_time;
	}

	public void setAdd_time(String add_time) {
		this.add_time = add_time;
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
