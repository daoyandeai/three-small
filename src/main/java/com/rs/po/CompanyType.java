package com.rs.po;

/**
 * 食品监管分类实体类
 * 
 * @ClassName: CompanyType
 * @Description:
 * @Author sven
 * @DateTime 2020年2月6日 上午9:59:10
 */
public class CompanyType extends BasePo<CompanyType> {

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