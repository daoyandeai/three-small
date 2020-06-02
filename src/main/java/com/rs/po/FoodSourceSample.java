package com.rs.po;

import com.rs.po.returnPo.FoodSourceSampleReturn;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @ClassName: FoodSourceSample
 * @Description: 留样实体
 * @Author tangsh
 * @DateTime 2020年3月9日 上午11:09:44
 */
public class FoodSourceSample extends BasePo<FoodSourceSample> implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
    留样ID
    * */
    private String sample_code;
    /**
    溯源编码
     * */
    private String foodsource_code;
    /**
     留样样品名
    * */
    private String sample_name;
    /**
     留样数量 单位是(g)
    * */
    private String sample_num;
    /**
     留样人
    * */
    private String sample_person;
    /**
    留样时间
    * */
    private String sample_time;
    /**
    留样图片
    * */
    private String sample_img ;
    /**
    三小企业编码(溯源对象）
    * */
    private String company_code;
    private String add_time;

    private List<FoodSourceSample> foodSourceSampleList;
    private List<FoodSourceSampleReturn> foodSourceSampleReturnList;

    public List<FoodSourceSample> getFoodSourceSampleList() {
        return foodSourceSampleList;
    }

    public void setFoodSourceSampleList(List<FoodSourceSample> foodSourceSampleList) {
        this.foodSourceSampleList = foodSourceSampleList;
    }

    public List<FoodSourceSampleReturn> getFoodSourceSampleReturnList() {
        return foodSourceSampleReturnList;
    }

    public void setFoodSourceSampleReturnList(List<FoodSourceSampleReturn> foodSourceSampleReturnList) {
        this.foodSourceSampleReturnList = foodSourceSampleReturnList;
    }

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

	public String getSample_code() {
		return sample_code;
	}

	public void setSample_code(String sample_code) {
		this.sample_code = sample_code;
	}

	public String getAdd_time() {
		return add_time;
	}

	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
}