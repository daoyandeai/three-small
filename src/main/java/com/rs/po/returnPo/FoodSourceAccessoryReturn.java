package com.rs.po.returnPo;

import java.io.Serializable;
/**
 * 溯源明细附件实体类
 * @ClassName: FoodSourceAccessoryReturn
 * @Description: 
 * @Author sven
 * @DateTime 2020年4月2日 下午4:08:50
 */
public class FoodSourceAccessoryReturn implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 溯源附件地址
	 */
	private String accessory_url;
	public String getAccessory_url() {
		return accessory_url;
	}
	public void setAccessory_url(String accessory_url) {
		this.accessory_url = accessory_url;
	}
}