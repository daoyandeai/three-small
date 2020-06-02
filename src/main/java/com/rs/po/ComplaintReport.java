package com.rs.po;

/**
 * 
 * @ClassName: ComplaintReport
 * @Description: 投诉举报实体
 * @Author tangsh
 * @DateTime 2020年3月9日 上午10:31:41
 */
public class ComplaintReport extends BasePo<ComplaintReport>{
	
	private static final long serialVersionUID = 1L;
	/**
     * 投诉举报系统编码
     */
	private String complaintreport_code;
    /**
     * 三小档案编码
     */
	private String company_code;
    /**
     * 三小档案名称
     */
    private String company_name;
    /**
     * 投诉举报标题
     */
    private String complaintreport_title;
    /**
     * 投诉举报内容
     */
    private String complaintreport_content;
    /**
     * 投诉举报图片地址(最多5张)
     */
    private String complaintreport_img;
    /**
     *  投诉举报人联系电话
     */
    private String complaintreport_telephone;
    /**
     *  投诉举报人openid
     */
    private String complaintreport_openid;
    /**
     * 投诉举报状态（1、待处理 2.已处理）
     */
    private Integer complaintreport_state;
    /**
     * 投诉举报用户系统编码
     */
    private String complaintreport_user_code;
    /**
     * 处理人员系统编码
     */
    private String result_user_code;
    /**
     * 处理人员名称
     */
    private String result_user_name;
    /**
     * 处理结果内容
     */
    private String result_content;
    /**
     * 处理结果图片地址(最多5张)
     */
    private String result_img;
    
	public String getComplaintreport_code() {
		return complaintreport_code;
	}
	public void setComplaintreport_code(String complaintreport_code) {
		this.complaintreport_code = complaintreport_code;
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
	public String getComplaintreport_title() {
		return complaintreport_title;
	}
	public void setComplaintreport_title(String complaintreport_title) {
		this.complaintreport_title = complaintreport_title;
	}
	public String getComplaintreport_content() {
		return complaintreport_content;
	}
	public void setComplaintreport_content(String complaintreport_content) {
		this.complaintreport_content = complaintreport_content;
	}
	public String getComplaintreport_img() {
		return complaintreport_img;
	}
	public void setComplaintreport_img(String complaintreport_img) {
		this.complaintreport_img = complaintreport_img;
	}
	public String getComplaintreport_telephone() {
		return complaintreport_telephone;
	}
	public void setComplaintreport_telephone(String complaintreport_telephone) {
		this.complaintreport_telephone = complaintreport_telephone;
	}
	public String getComplaintreport_openid() {
		return complaintreport_openid;
	}
	public void setComplaintreport_openid(String complaintreport_openid) {
		this.complaintreport_openid = complaintreport_openid;
	}
	public Integer getComplaintreport_state() {
		return complaintreport_state;
	}
	public void setComplaintreport_state(Integer complaintreport_state) {
		this.complaintreport_state = complaintreport_state;
	}
	public String getComplaintreport_user_code() {
		return complaintreport_user_code;
	}
	public void setComplaintreport_user_code(String complaintreport_user_code) {
		this.complaintreport_user_code = complaintreport_user_code;
	}
	public String getResult_user_code() {
		return result_user_code;
	}
	public void setResult_user_code(String result_user_code) {
		this.result_user_code = result_user_code;
	}
	public String getResult_user_name() {
		return result_user_name;
	}
	public void setResult_user_name(String result_user_name) {
		this.result_user_name = result_user_name;
	}
	public String getResult_content() {
		return result_content;
	}
	public void setResult_content(String result_content) {
		this.result_content = result_content;
	}
	public String getResult_img() {
		return result_img;
	}
	public void setResult_img(String result_img) {
		this.result_img = result_img;
	}
}