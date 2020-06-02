package com.rs.po;

import java.util.List;

/**
 * 
 * @ClassName: Role
 * @Description: 角色实体类
 * @Author tangsh
 * @DateTime 2019年12月18日 上午10:16:35
 */
public class Role extends BasePo<Role> {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 角色系统编码
	 */
	private String role_code;	
	/**
	 * 角色名称
	 */
	private String role_name;	
	/**
	 * 添加时间
	 */
	private String addtime;		
	
	/******************数据库辅助字段*************************/
	
	/**
	 * 权限里删除用户
	 */
	private String del_userid_str;		
	/**
	 * 权限里添加用户
	 */
	private String add_userid_str;		
	/**
	 * 添加还是删除用户  值：adduser添加  deluser删除
	 */
	private String handle_userid_type;  
	/**
	 * 菜单
	 */
	private String menu_str;	
	
	private List<Menu> menu_list;
	
	private String user_name;
	
	public String getRole_code() {
		return role_code;
	}
	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getDel_userid_str() {
		return del_userid_str;
	}
	public void setDel_userid_str(String del_userid_str) {
		this.del_userid_str = del_userid_str;
	}
	public String getAdd_userid_str() {
		return add_userid_str;
	}
	public void setAdd_userid_str(String add_userid_str) {
		this.add_userid_str = add_userid_str;
	}
	public String getHandle_userid_type() {
		return handle_userid_type;
	}
	public void setHandle_userid_type(String handle_userid_type) {
		this.handle_userid_type = handle_userid_type;
	}
	public String getMenu_str() {
		return menu_str;
	}
	public void setMenu_str(String menu_str) {
		this.menu_str = menu_str;
	}
	public List<Menu> getMenu_list() {
		return menu_list;
	}
	public void setMenu_list(List<Menu> menu_list) {
		this.menu_list = menu_list;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
}
