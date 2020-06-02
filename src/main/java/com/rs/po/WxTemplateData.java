package com.rs.po;

import java.io.Serializable;

/**
 * 
 * @ClassName: WxTemplateData
 * @Description: 微信消息推送键值实体类
 * @Author tangsh
 * @DateTime 2020年3月4日 下午5:51:42
 */
public class WxTemplateData implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String value;  
    private String color;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}  
}
