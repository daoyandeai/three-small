package com.rs.po;

/**
 * 
 * @ClassName: RoleMenu
 * @Description: 角色菜单关联实体类
 * @Author tangsh
 * @DateTime 2019年12月18日 上午10:17:07
 */
public class RoleMenu extends BasePo<RoleMenu> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 菜单系统编码
	 */
	private String menu_code;
	/**
	 * 角色系统编码
	 */
	private String role_code;
	/**
	 * 页面按钮权限数据
	 */
	private String menu_btns;
	
	public String getMenu_code() {
		return menu_code;
	}
	public void setMenu_code(String menu_code) {
		this.menu_code = menu_code;
	}
	public String getRole_code() {
		return role_code;
	}
	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}
	public String getMenu_btns() {
		return menu_btns;
	}
	public void setMenu_btns(String menu_btns) {
		this.menu_btns = menu_btns;
	}
}
