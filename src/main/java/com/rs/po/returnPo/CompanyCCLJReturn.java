package com.rs.po.returnPo;

import java.io.Serializable;
import java.util.List;
/**
 * 餐厨垃圾处理实体类
 * @ClassName: CompanyCCLJReturn
 * @Description: 
 * @Author sven
 * @DateTime 2020年3月9日 下午3:54:52
 */
public class CompanyCCLJReturn implements Serializable{

	/**
	  * @Fields serialVersionUID : 
	  */
	
	private static final long serialVersionUID = 1L;
	/**
	 * 餐厨垃圾回收系统编码
	 */
	private String cclj_code;
	/**
	 * 餐厨垃圾类型编码
	 */
	private String dictionary_code;
	/**
	 * 餐厨垃圾类型名称
	 */
	private String dictionary_module;
	/**
	 * 处理量（kg）
	 */
	private String cclj_count;
	/**
	 * 处理人
	 */
	private String cclj_handler;
	/**
	 * 处理时间
	 */
	private String cclj_time;
	/**
	 * 添加时间
	 */
	private String add_time;
	
	/****************数据库辅助字段****************/
	private List<AccessoryReturn> accessory_list;
	
	public String getCclj_code() {
		return cclj_code;
	}
	public void setCclj_code(String cclj_code) {
		this.cclj_code = cclj_code;
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
	public String getCclj_count() {
		return cclj_count;
	}
	public void setCclj_count(String cclj_count) {
		this.cclj_count = cclj_count;
	}
	public String getCclj_handler() {
		return cclj_handler;
	}
	public void setCclj_handler(String cclj_handler) {
		this.cclj_handler = cclj_handler;
	}
	public String getCclj_time() {
		return cclj_time;
	}
	public void setCclj_time(String cclj_time) {
		this.cclj_time = cclj_time;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public List<AccessoryReturn> getAccessory_list() {
		return accessory_list;
	}
	public void setAccessory_list(List<AccessoryReturn> accessory_list) {
		this.accessory_list = accessory_list;
	}
}
