package com.rs.po.returnPo;

import java.io.Serializable;
import java.util.List;
/**
 * 溯源实体类
 * @ClassName: FoodSourceReturn
 * @Description: 
 * @Author sven
 * @DateTime 2020年4月2日 下午3:02:54
 */
public class FoodSourceReturn implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 溯源编码
	 */
	private String foodsource_code;
	/**
	 * 购买时间（采购时间）
	 */
	private String sell_time;
	/**
	 * 购买人员
	 */
	private String sell_person;
	/**
	 * 供应商、店铺名称
	 */
	private String supplier_name;
	/**
	 * 购买地址
	 */
	private String supplier_address;
	/**
	 * 三小企业编码(溯源对象）
	 */
	private String company_code;
	/**
	 * 添加时间
	 */
	private String add_time;

	/*********************** 数据库辅助字段 *****************************/
	/**
	 * 溯源明细集合
	 */
	private List<FoodSourceDetailReturn> food_source_detail_list;

	public String getFoodsource_code() {
		return foodsource_code;
	}

	public void setFoodsource_code(String foodsource_code) {
		this.foodsource_code = foodsource_code;
	}

	public String getSell_time() {
		return sell_time;
	}

	public void setSell_time(String sell_time) {
		this.sell_time = sell_time;
	}

	public String getSell_person() {
		return sell_person;
	}

	public void setSell_person(String sell_person) {
		this.sell_person = sell_person;
	}

	public String getSupplier_name() {
		return supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}

	public String getSupplier_address() {
		return supplier_address;
	}

	public void setSupplier_address(String supplier_address) {
		this.supplier_address = supplier_address;
	}

	public String getCompany_code() {
		return company_code;
	}

	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}

	public String getAdd_time() {
		return add_time;
	}

	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}

	public List<FoodSourceDetailReturn> getFood_source_detail_list() {
		return food_source_detail_list;
	}

	public void setFood_source_detail_list(List<FoodSourceDetailReturn> food_source_detail_list) {
		this.food_source_detail_list = food_source_detail_list;
	}
	
}