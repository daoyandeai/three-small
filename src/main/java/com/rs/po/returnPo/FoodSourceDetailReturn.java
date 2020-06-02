package com.rs.po.returnPo;

import java.io.Serializable;
import java.util.List;

/**
 * 溯源明细实体类
 * 
 * @ClassName: FoodSourceDetailReturn
 * @Description:
 * @Author sven
 * @DateTime 2020年4月2日 下午3:03:44
 */
public class FoodSourceDetailReturn implements Serializable {
	/**
	 * @Fields serialVersionUID :
	 */

	private static final long serialVersionUID = 1L;
	/**
	 * 溯源详情编码
	 */
	private String foodsourcedetail_code;
	/**
	 * 购买总数
	 */
	private String sell_count;
	/**
	 * 采购人员名称
	 */
	private String userset_name;
	/**
	 * 品种明细编码
	 */
	private String category_code;
	/**
	 * 品种明细名称
	 */
	private String category_name;
	/**
	 * 商品code（非必须字段，供应链保留字段）
	 */
	private String product_code;
	/**
	 * 食材图片（手动填报选用默认图片）
	 */
	private String product_logo;
	/**
	 * 食材名称
	 */
	private String product_name;
	/**
	 * 食材品牌
	 */
	private String product_brand;
	/**
	 * 食材规格
	 */
	private String product_spec;
	/**
	 * 食材单位
	 */
	private String product_unit;
	/**
	 * 生产人员（默认为报备用户的名称）
	 */
	private String produce_name;
	/**
	 * 添加时间
	 */
	private String add_time;
	/**
	 * 供应商、店铺名称
	 */
	private String supplier_name;
	/**
	 * 供应商地址
	 */
	private String supplier_address;
	/**
	 * 供应商电话
	 */
	private String supplier_phone;
	/**
	 * 订单编号
	 */
	private String order_number;
	/**
	 * 下单时间
	 */
	private String order_create_time;
	/**
	 * 配送时间
	 */
	private String order_send_time;
	/**
	 * 配送地址
	 */
	private String order_send_address;
	/**
	 * 配送厨师
	 */
	private String order_send_person;
	/**
	 * 采购时间
	 */
	private String purchase_time;
	/**
	 * 采购联系电话
	 */
	private String purchase_phone;
	/**
	 * 分拣人员
	 */
	private String sort_user_name;
	/**
	 * 分拣联系电话
	 */
	private String sort_user_phone;
	/**
	 * 食材溯源系统编码
	 */
	private String foodsource_code;
	/**
	 * 三小企业编码(溯源对象）
	 */
	private String company_code;
	/*********************** 数据库辅助字段 *****************************/
	/**
	 * 溯源明细附近集合
	 */
	private List<FoodSourceAccessoryReturn> food_source_accessory_list;

	public String getSell_count() {
		return sell_count;
	}

	public void setSell_count(String sell_count) {
		this.sell_count = sell_count;
	}

	public String getUserset_name() {
		return userset_name;
	}

	public void setUserset_name(String userset_name) {
		this.userset_name = userset_name;
	}

	public String getCategory_code() {
		return category_code;
	}

	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getProduct_logo() {
		return product_logo;
	}

	public void setProduct_logo(String product_logo) {
		this.product_logo = product_logo;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_brand() {
		return product_brand;
	}

	public void setProduct_brand(String product_brand) {
		this.product_brand = product_brand;
	}

	public String getProduct_spec() {
		return product_spec;
	}

	public void setProduct_spec(String product_spec) {
		this.product_spec = product_spec;
	}

	public String getProduct_unit() {
		return product_unit;
	}

	public void setProduct_unit(String product_unit) {
		this.product_unit = product_unit;
	}

	public String getProduce_name() {
		return produce_name;
	}

	public void setProduce_name(String produce_name) {
		this.produce_name = produce_name;
	}

	public String getFoodsourcedetail_code() {
		return foodsourcedetail_code;
	}

	public void setFoodsourcedetail_code(String foodsourcedetail_code) {
		this.foodsourcedetail_code = foodsourcedetail_code;
	}

	public String getAdd_time() {
		return add_time;
	}

	public void setAdd_time(String add_time) {
		this.add_time = add_time;
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

	public String getSupplier_phone() {
		return supplier_phone;
	}

	public void setSupplier_phone(String supplier_phone) {
		this.supplier_phone = supplier_phone;
	}

	public String getOrder_number() {
		return order_number;
	}

	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}

	public String getOrder_create_time() {
		return order_create_time;
	}

	public void setOrder_create_time(String order_create_time) {
		this.order_create_time = order_create_time;
	}

	public String getOrder_send_time() {
		return order_send_time;
	}

	public void setOrder_send_time(String order_send_time) {
		this.order_send_time = order_send_time;
	}

	public String getOrder_send_address() {
		return order_send_address;
	}

	public void setOrder_send_address(String order_send_address) {
		this.order_send_address = order_send_address;
	}

	public String getOrder_send_person() {
		return order_send_person;
	}

	public void setOrder_send_person(String order_send_person) {
		this.order_send_person = order_send_person;
	}

	public String getPurchase_time() {
		return purchase_time;
	}

	public void setPurchase_time(String purchase_time) {
		this.purchase_time = purchase_time;
	}

	public String getPurchase_phone() {
		return purchase_phone;
	}

	public void setPurchase_phone(String purchase_phone) {
		this.purchase_phone = purchase_phone;
	}

	public String getSort_user_name() {
		return sort_user_name;
	}

	public void setSort_user_name(String sort_user_name) {
		this.sort_user_name = sort_user_name;
	}

	public String getSort_user_phone() {
		return sort_user_phone;
	}

	public void setSort_user_phone(String sort_user_phone) {
		this.sort_user_phone = sort_user_phone;
	}

	public List<FoodSourceAccessoryReturn> getFood_source_accessory_list() {
		return food_source_accessory_list;
	}

	public void setFood_source_accessory_list(List<FoodSourceAccessoryReturn> food_source_accessory_list) {
		this.food_source_accessory_list = food_source_accessory_list;
	}

	public String getFoodsource_code() {
		return foodsource_code;
	}

	public void setFoodsource_code(String foodsource_code) {
		this.foodsource_code = foodsource_code;
	}

	public String getCompany_code() {
		return company_code;
	}

	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}
}