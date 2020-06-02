package com.rs.po;

import java.util.List;

/**
 * 部门管理员实体
 * @ClassName: DepartmentUser
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月31日 下午4:01:43
 */
public class DepartmentUser extends BasePo<DepartmentUser> {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 部门编码
	 */
    private String department_code;
    /**
     * 用户编码
     */
    private String user_code;
    /**
     * 状态（1：启用、2：禁用）
     */
    private Integer state;
    /**
     * 部门监管人员集合
     */
    private List<DepartmentUser> department_user_list;
    
    /*************数据库辅助字段***************/
    /**
     * 用户姓名
     */
    private String user_name;				
    /**
     * 用户手机号
     */
    private String user_mobilephone;		
	public String getDepartment_code() {
		return department_code;
	}
	public void setDepartment_code(String department_code) {
		this.department_code = department_code;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public List<DepartmentUser> getDepartment_user_list() {
		return department_user_list;
	}
	public void setDepartment_user_list(List<DepartmentUser> department_user_list) {
		this.department_user_list = department_user_list;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_mobilephone() {
		return user_mobilephone;
	}
	public void setUser_mobilephone(String user_mobilephone) {
		this.user_mobilephone = user_mobilephone;
	}
}