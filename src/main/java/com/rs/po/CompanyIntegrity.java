package com.rs.po;
/**
 * 企业诚信评价结果实体类
 * @ClassName: CompanyIntegrity
 * @Description: 
 * @Author sven
 * @DateTime 2020年2月12日 下午4:42:02
 */
public class CompanyIntegrity extends BasePo<CompanyIntegrity>{
	/**
	  * @Fields serialVersionUID : 
	  */
	
	private static final long serialVersionUID = 1L;
	/**
	 * 企业诚信评价结果编码
	 */
	private String company_integrity_code;
	/**
	 * 企业编码
	 */
	private String company_code;
	/**
     * 上次诚信经营等级
     */
    private float integrity_lvl_last;
    /**
     * 上次等级评定时间
     */
    private String mete_time_last;
    /**
     * 最新诚信经营等级
     */
    private float integrity_lvl_new;
    /**
     * 最近两次诚信评价变化趋势（1：一致、2：上升、3：下降）
     */
    private Integer integrity_lvl_change_trend;
	/**
	 * 评定类型（1：系统分析、2：监管更新）
	 */
	private Integer mete_type;
	/**
	 * 评分理由（如果是监管更改结果,需要填写理由）
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
	public String getCompany_integrity_code() {
		return company_integrity_code;
	}
	public void setCompany_integrity_code(String company_integrity_code) {
		this.company_integrity_code = company_integrity_code;
	}
	public String getCompany_code() {
		return company_code;
	}
	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}
	public String getMete_time_last() {
		return mete_time_last;
	}
	public void setMete_time_last(String mete_time_last) {
		this.mete_time_last = mete_time_last;
	}
	public Integer getIntegrity_lvl_change_trend() {
		return integrity_lvl_change_trend;
	}
	public void setIntegrity_lvl_change_trend(Integer integrity_lvl_change_trend) {
		this.integrity_lvl_change_trend = integrity_lvl_change_trend;
	}
	public Integer getMete_type() {
		return mete_type;
	}
	public void setMete_type(Integer mete_type) {
		this.mete_type = mete_type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public float getIntegrity_lvl_last() {
		return integrity_lvl_last;
	}
	public void setIntegrity_lvl_last(float integrity_lvl_last) {
		this.integrity_lvl_last = integrity_lvl_last;
	}
	public float getIntegrity_lvl_new() {
		return integrity_lvl_new;
	}
	public void setIntegrity_lvl_new(float integrity_lvl_new) {
		this.integrity_lvl_new = integrity_lvl_new;
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
}
