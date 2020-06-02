package com.rs.po;

import java.io.Serializable;

public class FoodSourceAccessory extends BasePo<FoodSourceAccessory> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
    * 溯源附件编码
    * */
    private String accessory_code;
    /**
     * 溯源附件地址
     * */
    private String accessory_url;
    /**
     * 溯源编码
     * */
    private String foodsource_code;
    /**
     * 溯源详情编码
     * */
    private String foodsourcedetail_code;
    /**
     * 三小档案编码
     * */
    private String company_code;
    /**
     * 添加时间
     * */
    private String add_time;

    public String getAccessory_code() {
        return accessory_code;
    }

    public void setAccessory_code(String accessory_code) {
        this.accessory_code = accessory_code;
    }

    public String getAccessory_url() {
        return accessory_url;
    }

    public void setAccessory_url(String accessory_url) {
        this.accessory_url = accessory_url;
    }

    public String getFoodsource_code() {
        return foodsource_code;
    }

    public void setFoodsource_code(String foodsource_code) {
        this.foodsource_code = foodsource_code;
    }

    public String getFoodsourcedetail_code() {
        return foodsourcedetail_code;
    }

    public void setFoodsourcedetail_code(String foodsourcedetail_code) {
        this.foodsourcedetail_code = foodsourcedetail_code;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    @Override
    public String getAdd_time() {
        return add_time;
    }

    @Override
    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }
}