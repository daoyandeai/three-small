package com.rs.po;
/**
 * 
 * @ClassName: MenuButton
 * @Description: 页面按钮基础表
 * @Author tangsh
 * @DateTime 2020年2月25日 上午10:05:45
 */
public class MenuButton extends BasePo<MenuButton>{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 页面按钮code
	 */
	private String menubtn_code;
	/**
	 * 页面按钮ID
	 */
	private String menubtn_id;
	 /**
	  * 页面按钮标题
	  */
	private String menubtn_title;
	/**
	 * 页面按钮图片
	 */
	private String menubtn_img;
	/**
	 * 页面code
	 */
	private String menu_code;
	/**
	 * 默认添加时间
	 */
	private String add_time;
	
	public String getMenubtn_code() {
		return menubtn_code;
	}
	public void setMenubtn_code(String menubtn_code) {
		this.menubtn_code = menubtn_code;
	}
	public String getMenubtn_id() {
		return menubtn_id;
	}
	public void setMenubtn_id(String menubtn_id) {
		this.menubtn_id = menubtn_id;
	}
	public String getMenubtn_title() {
		return menubtn_title;
	}
	public void setMenubtn_title(String menubtn_title) {
		this.menubtn_title = menubtn_title;
	}
	public String getMenubtn_img() {
		return menubtn_img;
	}
	public void setMenubtn_img(String menubtn_img) {
		this.menubtn_img = menubtn_img;
	}
	public String getMenu_code() {
		return menu_code;
	}
	public void setMenu_code(String menu_code) {
		this.menu_code = menu_code;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
}
