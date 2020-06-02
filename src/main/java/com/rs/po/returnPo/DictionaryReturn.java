package com.rs.po.returnPo;

import java.io.Serializable;
/**
 * 字典实体类（餐厨垃圾）
 * @ClassName: DictionaryReturn
 * @Description: 
 * @Author sven
 * @DateTime 2020年3月9日 上午10:56:47
 */
public class DictionaryReturn implements Serializable {
	/**
	  * @Fields serialVersionUID : 
	  */
	
	private static final long serialVersionUID = 1L;
	/**
	 * 字典编码
	 */
	private String dictionary_code;
	/**
	 * 字典模块名称
	 */
	private String dictionary_module;
	/**
	 * 食品监管分类编码集合
	 */
	private String companytype_codes;
	/**
	 * 食品监管分类名称集合
	 */
	private String companytype_names;
	/**
	 * 字典图片url
	 */
	private String dictionary_logo_url;
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
	
	/*
	 * 分类
	 */
	private String dictionary_group;
	
	
	public String getDictionary_group() {
		return dictionary_group;
	}
	public void setDictionary_group(String dictionary_group) {
		this.dictionary_group = dictionary_group;
	}
	public String getDictionary_code() {
		return dictionary_code;
	}
	public void setDictionary_code(String dictionary_code) {
		this.dictionary_code = dictionary_code;
	}
	public String getDictionary_module() {
		return dictionary_module;
	}
	public void setDictionary_module(String dictionary_module) {
		this.dictionary_module = dictionary_module;
	}
	public String getCompanytype_codes() {
		return companytype_codes;
	}
	public void setCompanytype_codes(String companytype_codes) {
		this.companytype_codes = companytype_codes;
	}
	public String getCompanytype_names() {
		return companytype_names;
	}
	public void setCompanytype_names(String companytype_names) {
		this.companytype_names = companytype_names;
	}
	public String getDictionary_logo_url() {
		return dictionary_logo_url;
	}
	public void setDictionary_logo_url(String dictionary_logo_url) {
		this.dictionary_logo_url = dictionary_logo_url;
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
