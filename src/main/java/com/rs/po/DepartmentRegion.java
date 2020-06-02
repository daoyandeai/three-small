package com.rs.po;

/**
 * 部门管理区域实体类
 * 
 * @ClassName: DepartmentRegion
 * @Description:
 * @Author sven
 * @DateTime 2019年12月30日 下午3:53:49
 */
public class DepartmentRegion extends BasePo<DepartmentRegion>{
	/**
	  * @Fields serialVersionUID : 
	  */
	
	private static final long serialVersionUID = 1L;
	/**
	 * 部门编码
	 */
	private String department_code;
	/**
	 * 管理第五级区域
	 */
	private String region_code;
	/**
	 * 区域code字符串 '123','234'
	 */
	private String region_codes;
	
	private String region_name;
	
	public String getDepartment_code() {
		return department_code;
	}

	public void setDepartment_code(String department_code) {
		this.department_code = department_code;
	}

	public String getRegion_code() {
		return region_code;
	}

	public void setRegion_code(String region_code) {
		this.region_code = region_code;
	}

	public String getRegion_codes() {
		return region_codes;
	}

	public void setRegion_codes(String region_codes) {
		this.region_codes = region_codes;
	}

	public String getRegion_name() {
		return region_name;
	}

	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}
}