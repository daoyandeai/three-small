package com.rs.po;

import java.util.List;

/**
 * 
 * @ClassName: Menu
 * @Description: 菜单实体类
 * @Author tangsh
 * @DateTime 2019年12月18日 上午10:15:48
 */
public class Menu extends BasePo<Menu> {
	private static final long serialVersionUID = 1L;

	/**
	 * 菜单系统编码
	 */
	private String menu_code;		
	/**
	 * 菜单父编码
	 */
	private String menu_parentcode; 
	/**
	 * 菜单名称
	 */
	private String menu_title; 		
	/**
	 * 菜单图片地址
	 */
	private String menu_img;		
	/**
	 * 菜单url地址
	 */
	private String menu_url;		
	/**
	 * 菜单类型  1为一级菜单 2为二级菜单
	 */
	private Integer menu_type;		
	/**
	 * 菜单排序
	 */
	private Integer menu_order;		
	/**
	 * 添加时间
	 */
	private String addtime;			
	
	/******************数据库辅助字段*************************/
	
	/**
	 * 用户角色
	 */
	private String role_code;		
	
	private List<Menu> menu_list;
	
	private String menu_btns;	
	private List<String> menu_btns_list;	

	private List<MenuButton> menubutton_list;
	
	public String getMenu_code() {
		return menu_code;
	}
	public void setMenu_code(String menu_code) {
		this.menu_code = menu_code;
	}
	public String getMenu_parentcode() {
		return menu_parentcode;
	}
	public void setMenu_parentcode(String menu_parentcode) {
		this.menu_parentcode = menu_parentcode;
	}
	public String getMenu_title() {
		return menu_title;
	}
	public void setMenu_title(String menu_title) {
		this.menu_title = menu_title;
	}
	public String getMenu_img() {
		return menu_img;
	}
	public void setMenu_img(String menu_img) {
		this.menu_img = menu_img;
	}
	public String getMenu_url() {
		return menu_url;
	}
	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}
	public Integer getMenu_type() {
		return menu_type;
	}
	public void setMenu_type(Integer menu_type) {
		this.menu_type = menu_type;
	}
	public Integer getMenu_order() {
		return menu_order;
	}
	public void setMenu_order(Integer menu_order) {
		this.menu_order = menu_order;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getRole_code() {
		return role_code;
	}
	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}
	public List<Menu> getMenu_list() {
		return menu_list;
	}
	public void setMenu_list(List<Menu> menu_list) {
		this.menu_list = menu_list;
	}
	public String getMenu_btns() {
		return menu_btns;
	}
	public void setMenu_btns(String menu_btns) {
		this.menu_btns = menu_btns;
	}
	public List<MenuButton> getMenubutton_list() {
		return menubutton_list;
	}
	public void setMenubutton_list(List<MenuButton> menubutton_list) {
		this.menubutton_list = menubutton_list;
	}
	public List<String> getMenu_btns_list() {
		return menu_btns_list;
	}
	public void setMenu_btns_list(List<String> menu_btns_list) {
		this.menu_btns_list = menu_btns_list;
	}
}
