package com.rs.po.returnPo;

import java.io.Serializable;

/**
 * 溯源留样实体类
 * 
 * @ClassName: FoodSourceSampleReturn
 * @Description:
 * @Author sven
 * @DateTime 2020年4月2日 下午3:04:12
 */
public class FoodSourceSampleReturn implements Serializable {
	/**
	  * @Fields serialVersionUID : 
	  */
	
	private static final long serialVersionUID = 1L;
	/**
	 * 溯源编码
	 */
	private String foodsource_code;
	/**
	 * 留样样品名
	 */
	private String sample_name;
	/**
	 * 留样数量 单位是(g)
	 */
	private String sample_num;
	/**
	 * 留样人
	 */
	private String sample_person;
	/**
	 * 留样时间
	 */
	private String sample_time;
	/**
	 * 留样图片
	 */
	private String sample_img;
	/**
	 * 三小企业编码(溯源对象）
	 */
	private String company_code;

	public String getSample_name() {
		return sample_name;
	}

	public void setSample_name(String sample_name) {
		this.sample_name = sample_name;
	}

	public String getSample_num() {
		return sample_num;
	}

	public void setSample_num(String sample_num) {
		this.sample_num = sample_num;
	}

	public String getSample_person() {
		return sample_person;
	}

	public void setSample_person(String sample_person) {
		this.sample_person = sample_person;
	}

	public String getSample_time() {
		return sample_time;
	}

	public void setSample_time(String sample_time) {
		this.sample_time = sample_time;
	}

	public String getSample_img() {
		return sample_img;
	}

	public void setSample_img(String sample_img) {
		this.sample_img = sample_img;
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