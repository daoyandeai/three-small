package com.rs.po.returnPo;

public class CompanyEmployReturn{
    /**
    * 人员编码
    * */
    private String employ_code;
    /**
     * 人员名称
     * */
    private String employ_name;
    /**
     * 人员身份证
     * */
    private String employ_idcard;
    /**
     * 人员是否有健康证
     * */
    private Integer employ_ishealth;
    /**
     * 人员健康证url地址
     * */
    private String employ_health;
    /**
     * 人员备注
     * */
    private String remark;
    /**
     * 人员绑定小三档案
     * */
    private String company_code;
    /**
     * 健康证到期时间
     */
    private String user_health_datedue;
    /**
     * 健康证到期时间
     */
    private String employ_health_url;
    /**
     * 页面参数需要
     */
    private Boolean employ_flag;

    public String getEmploy_code() {
        return employ_code;
    }

    public void setEmploy_code(String employ_code) {
        this.employ_code = employ_code;
    }

    public String getEmploy_name() {
        return employ_name;
    }

    public void setEmploy_name(String employ_name) {
        this.employ_name = employ_name;
    }

    public String getEmploy_idcard() {
        return employ_idcard;
    }

    public void setEmploy_idcard(String employ_idcard) {
        this.employ_idcard = employ_idcard;
    }

    public Integer getEmploy_ishealth() {
        return employ_ishealth;
    }

    public void setEmploy_ishealth(Integer employ_ishealth) {
        this.employ_ishealth = employ_ishealth;
    }

    public String getEmploy_health() {
        return employ_health;
    }

    public void setEmploy_health(String employ_health) {
        this.employ_health = employ_health;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

	public String getUser_health_datedue() {
		return user_health_datedue;
	}

	public void setUser_health_datedue(String user_health_datedue) {
		this.user_health_datedue = user_health_datedue;
	}

	public String getEmploy_health_url() {
		return employ_health_url;
	}

	public void setEmploy_health_url(String employ_health_url) {
		this.employ_health_url = employ_health_url;
	}

	public Boolean getEmploy_flag() {
		return employ_flag;
	}

	public void setEmploy_flag(Boolean employ_flag) {
		this.employ_flag = employ_flag;
	}
}