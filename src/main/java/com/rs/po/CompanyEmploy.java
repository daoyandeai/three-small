package com.rs.po;

/**
 * 
 * @ClassName: CompanyEmploy
 * @Description: 从业人员实体
 * @Author tangsh
 * @DateTime 2020年3月9日 上午11:08:58
 */
public class CompanyEmploy extends BasePo<CompanyEmploy>{
	
	private static final long serialVersionUID = 1L;
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
     * 健康证图片
     */
    private String employ_health_url;
    
    /***************************业务字段********************************/
    /**
     * 企业名称
     */
    private String company_name;
    /**
     * 证件状态 1.正常 2.过期  4.即将过期
     */
    private Integer e_state;
    /**
     * 企业状态 1.有效 2.过期  3.注销
     */
    private Integer c_state;
    
    /**
     * 1.信息员 2.所管理员
     */
    private Integer c_is_manage_or_info;	
    /**
     * 监管人员编码
     */
    private String c_user_code_manage;
    /**
     *  信息人员编码
     */
    private String c_user_code_info;
    /**
     *  经营形态集合
     */
    private String c_business_forms;			
    /**
     *  食品监管分类编码集合
     */
	private String c_companytype_codes;		
	/**
     *  备案类型编码集合
     */
	private String c_companytag_codes;	
	/**
     * 省
     */
    private String c_province;
    /**
     * 市
     */
    private String c_city;
    /**
     * 区
     */
    private String c_area;
    /**
     * 乡
     */
    private String c_town;
    /**
     * 村
     */
    private String c_vill;
    

    public CompanyEmploy() {
    }

    public CompanyEmploy(String company_code) {
        this.company_code = company_code;
    }

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

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public Integer getE_state() {
		return e_state;
	}

	public void setE_state(Integer e_state) {
		this.e_state = e_state;
	}

	public Integer getC_state() {
		return c_state;
	}

	public void setC_state(Integer c_state) {
		this.c_state = c_state;
	}

	public Integer getC_is_manage_or_info() {
		return c_is_manage_or_info;
	}

	public void setC_is_manage_or_info(Integer c_is_manage_or_info) {
		this.c_is_manage_or_info = c_is_manage_or_info;
	}

	public String getC_user_code_manage() {
		return c_user_code_manage;
	}

	public void setC_user_code_manage(String c_user_code_manage) {
		this.c_user_code_manage = c_user_code_manage;
	}

	public String getC_user_code_info() {
		return c_user_code_info;
	}

	public void setC_user_code_info(String c_user_code_info) {
		this.c_user_code_info = c_user_code_info;
	}

	public String getC_business_forms() {
		return c_business_forms;
	}

	public void setC_business_forms(String c_business_forms) {
		this.c_business_forms = c_business_forms;
	}

	public String getC_companytype_codes() {
		return c_companytype_codes;
	}

	public void setC_companytype_codes(String c_companytype_codes) {
		this.c_companytype_codes = c_companytype_codes;
	}

	public String getC_companytag_codes() {
		return c_companytag_codes;
	}

	public void setC_companytag_codes(String c_companytag_codes) {
		this.c_companytag_codes = c_companytag_codes;
	}

	public String getC_province() {
		return c_province;
	}

	public void setC_province(String c_province) {
		this.c_province = c_province;
	}

	public String getC_city() {
		return c_city;
	}

	public void setC_city(String c_city) {
		this.c_city = c_city;
	}

	public String getC_area() {
		return c_area;
	}

	public void setC_area(String c_area) {
		this.c_area = c_area;
	}

	public String getC_town() {
		return c_town;
	}

	public void setC_town(String c_town) {
		this.c_town = c_town;
	}

	public String getC_vill() {
		return c_vill;
	}

	public void setC_vill(String c_vill) {
		this.c_vill = c_vill;
	}
}