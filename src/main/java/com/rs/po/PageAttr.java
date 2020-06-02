package com.rs.po;
/**
 * 页面属性实体类
 * @ClassName: PageAttr
 * @Description: 
 * @Author sven
 * @DateTime 2020年2月11日 上午9:55:48
 */
public class PageAttr extends BasePo<PageAttr>{
	/**
	  * @Fields serialVersionUID : 
	  */
	
	private static final long serialVersionUID = 1L;
	/**
	 * 页面属性编码
	 */
	private String page_attr_code;
	/**
	 * 属性英文名
	 */
	private String attr_en;
	/**
	 * 属性中文名
	 */
	private String attr_ch;
	/**
	 * 页面模块（1：诚信等级、2：信息公示、3：自查自纠）
	 */
	private Integer page_module; 
	/**
	 * 状态（1：启用、2：禁用）
	 */
	private Integer state;
	/**
	 * 新增用户系统编码
	 */
	private String user_code_add;
	/**
	 * 新增用户姓名
	 */
	private String user_name_add;
	/**
	 * 添加时间
	 */
	private String add_time;
	/**
	 * 更新用户系统编码
	 */
	private String user_code_update;
	/**
	 * 更新用户姓名
	 */
	private String user_name_update;
	/**
	 * 更新时间
	 */
	private String update_time;
	public String getPage_attr_code() {
		return page_attr_code;
	}
	public void setPage_attr_code(String page_attr_code) {
		this.page_attr_code = page_attr_code;
	}
	public String getAttr_en() {
		return attr_en;
	}
	public void setAttr_en(String attr_en) {
		this.attr_en = attr_en;
	}
	public String getAttr_ch() {
		return attr_ch;
	}
	public void setAttr_ch(String attr_ch) {
		this.attr_ch = attr_ch;
	}
	public Integer getPage_module() {
		return page_module;
	}
	public void setPage_module(Integer page_module) {
		this.page_module = page_module;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getUser_code_add() {
		return user_code_add;
	}
	public void setUser_code_add(String user_code_add) {
		this.user_code_add = user_code_add;
	}
	public String getUser_name_add() {
		return user_name_add;
	}
	public void setUser_name_add(String user_name_add) {
		this.user_name_add = user_name_add;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getUser_code_update() {
		return user_code_update;
	}
	public void setUser_code_update(String user_code_update) {
		this.user_code_update = user_code_update;
	}
	public String getUser_name_update() {
		return user_name_update;
	}
	public void setUser_name_update(String user_name_update) {
		this.user_name_update = user_name_update;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
}
