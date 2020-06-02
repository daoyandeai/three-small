package com.rs.po;

/**
 * 
 * @ClassName: UserRole
 * @Description: 用户角色关联实体类
 * @Author tangsh
 * @DateTime 2019年12月18日 上午10:17:07
 */
public class UserRole extends BasePo<UserRole> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户系统编码
	 */
	private String user_code;
	/**
	 * 角色系统编码
	 */
	private String role_code;
	
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public String getRole_code() {
		return role_code;
	}
	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}
}
