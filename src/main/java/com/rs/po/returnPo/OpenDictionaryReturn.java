package com.rs.po.returnPo;

import java.io.Serializable;

/**
 * 
 * @ClassName: OpenDictionaryReturn
 * @Description: 数据字典表
 * @Author tangsh
 * @DateTime 2020年4月1日 下午2:37:04
 */
public class OpenDictionaryReturn implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 *  字典编码
	 */
    private String dictionary_code;
    /**
     *  字典字段名称
     */
    private String dictionary_name;
    /**
     * 字典表类型（1：小经营店（餐饮）2：小经营店（销售）3：小作坊 4：小摊贩）
     */
    private String companytag_code;
    /**
     *  字典模块名称
     */
    private String dictionary_module;
    /**
     * 字典字段名称
     */
    private String dictionary_field;
	public String getDictionary_code() {
		return dictionary_code;
	}
	public void setDictionary_code(String dictionary_code) {
		this.dictionary_code = dictionary_code;
	}
	public String getDictionary_name() {
		return dictionary_name;
	}
	public void setDictionary_name(String dictionary_name) {
		this.dictionary_name = dictionary_name;
	}
	public String getCompanytag_code() {
		return companytag_code;
	}
	public void setCompanytag_code(String companytag_code) {
		this.companytag_code = companytag_code;
	}
	public String getDictionary_module() {
		return dictionary_module;
	}
	public void setDictionary_module(String dictionary_module) {
		this.dictionary_module = dictionary_module;
	}
	public String getDictionary_field() {
		return dictionary_field;
	}
	public void setDictionary_field(String dictionary_field) {
		this.dictionary_field = dictionary_field;
	}
}