package com.rs.po;

import java.io.Serializable;
import java.util.Map;
/**
 * 
 * @ClassName: WxTemplate
 * @Description: 微信消息推送实体类
 * @Author tangsh
 * @DateTime 2020年3月4日 下午5:51:36
 */
public class WxTemplate implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String template_id;  
    private String touser;  
    private String url;  
    private String color;  
    private WxTemplateXcx miniprogram;
    private Map<String,WxTemplateData> data;
    
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public WxTemplateXcx getMiniprogram() {
		return miniprogram;
	}
	public void setMiniprogram(WxTemplateXcx miniprogram) {
		this.miniprogram = miniprogram;
	}
	public Map<String, WxTemplateData> getData() {
		return data;
	}
	public void setData(Map<String, WxTemplateData> data) {
		this.data = data;
	} 
}
