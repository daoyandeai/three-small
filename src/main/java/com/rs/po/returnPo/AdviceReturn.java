package com.rs.po.returnPo;

import java.io.Serializable;
/**
 * 执法建议实体类
 * @ClassName: AdviceReturn
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月28日 下午2:12:44
 */
public class AdviceReturn implements Serializable{
	
	/**
	  * @Fields serialVersionUID : 
	  */
	
	private static final long serialVersionUID = 1L;
	/**
	 * 执法建议系统编码
	 */
	private String advice_code;
	/**
	 * 摄像头系统编码
	 */
	private String device_code;
	/**
	 * 企业系统编码
	 */
	private String company_code;
	/**
	 * 图片类型（1：现场、2：抓拍 ）
	 */
	private Integer type;
	/**
	 * openid
	 */
	private String openid;
	/**
	 * 图片json字符串
	 */
	private Object imgs_url;
	/**
	 * 评定等级（1：差、2：一般、3：良好、4：优秀）
	 */
	private Integer advice_rate;
	/**
	 * 评定类型（1：资证不齐、2：现场卫生不规范、3：人员卫生不规范、4：追溯记录不符合要求、5：无违规）
	 */
	private Integer advice_type;
	/**
	 * 补充意见
	 */
	private String advice_remark;
	/**
	 * 添加时间
	 */
	private String add_time;
	public String getAdvice_code() {
		return advice_code;
	}
	public void setAdvice_code(String advice_code) {
		this.advice_code = advice_code;
	}
	public String getDevice_code() {
		return device_code;
	}
	public void setDevice_code(String device_code) {
		this.device_code = device_code;
	}
	public String getCompany_code() {
		return company_code;
	}
	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public Object getImgs_url() {
		return imgs_url;
	}
	public void setImgs_url(Object imgs_url) {
		this.imgs_url = imgs_url;
	}
	public Integer getAdvice_rate() {
		return advice_rate;
	}
	public void setAdvice_rate(Integer advice_rate) {
		this.advice_rate = advice_rate;
	}
	public Integer getAdvice_type() {
		return advice_type;
	}
	public void setAdvice_type(Integer advice_type) {
		this.advice_type = advice_type;
	}
	public String getAdvice_remark() {
		return advice_remark;
	}
	public void setAdvice_remark(String advice_remark) {
		this.advice_remark = advice_remark;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
}
