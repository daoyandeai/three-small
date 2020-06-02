package com.rs.po;
/**
 * 附件实体类
 * @ClassName: Accessory
 * @Description: 
 * @Author sven
 * @DateTime 2020年3月9日 下午3:41:30
 */
public class Accessory extends BasePo<Accessory> {

	private static final long serialVersionUID = 1L;
	/**
	 * 附件编码
	 */
	private String accessory_code;
	/**
	 * 附件地址
	 */
	private String accessory_url;
	/**
	 * 附加类型（备案申请书、营业执照副本、场地证明、健康证、身份证）
	 */
	private String accessory_type;
	/**
	 * 企业编码
	 */
	private String company_code;
	/**
	 * 附件归属表编码
	 */
	private String other_code;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String url;
	public String getAccessory_code() {
		return accessory_code;
	}
	public void setAccessory_code(String accessory_code) {
		this.accessory_code = accessory_code;
	}
	public String getAccessory_url() {
		return accessory_url;
	}
	public void setAccessory_url(String accessory_url) {
		this.accessory_url = accessory_url;
	}
	public String getAccessory_type() {
		return accessory_type;
	}
	public void setAccessory_type(String accessory_type) {
		this.accessory_type = accessory_type;
	}
	public String getCompany_code() {
		return company_code;
	}
	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}
	public String getOther_code() {
		return other_code;
	}
	public void setOther_code(String other_code) {
		this.other_code = other_code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}