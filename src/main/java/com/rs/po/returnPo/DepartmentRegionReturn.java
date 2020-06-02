package com.rs.po.returnPo;

import java.io.Serializable;

/**
 * 部门管理区域实体类
 * 
 * @ClassName: DepartmentRegion
 * @Description:
 * @Author sven
 * @DateTime 2019年12月30日 下午3:51:25
 */
public class DepartmentRegionReturn implements Serializable {
	/**
	 * @Fields serialVersionUID :
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 部门编码
	 */
	private String department_code;
	/**
	 * 区域编码
	 */
	private String region_code;

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
}