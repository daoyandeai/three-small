package com.rs.po.returnPo;

import java.io.Serializable;
/**
 * 食品监管分类
 * @ClassName: CompanyTypeReturn
 * @Description: 
 * @Author sven
 * @DateTime 2020年2月6日 上午9:47:16
 */
public class CompanyTypeReturn implements Serializable{
	
	/**
	  * @Fields serialVersionUID : 
	  */
	
	private static final long serialVersionUID = 1L;
	/**
	 * 食品监管分类编码
	 */
    private String companytype_code;
    /**
     * 食品监管分类名称
     */
    private String companytype_name;
    /**
	 * 状态（1：启用、2：禁用）
	 */
	private Integer state;
	/**
	 * 添加时间
	 */
	private String add_time;
	/**
	 * 更新时间
	 */
	private String update_time;
	/**
	 * 复选状态
	 */
	private boolean checked ;
	/**
	 * 非静态变量初始化块
	 */
    {
		checked = false;
	}
    public String getCompanytype_code() {
        return companytype_code;
    }

    public void setCompanytype_code(String companytype_code) {
        this.companytype_code = companytype_code;
    }

    public String getCompanytype_name() {
        return companytype_name;
    }

    public void setCompanytype_name(String companytype_name) {
        this.companytype_name = companytype_name;
    }

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getAdd_time() {
		return add_time;
	}

	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
}