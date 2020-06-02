package com.rs.po;

import java.io.Serializable;

/**
 * 
 * @ClassName: WxTemplateXcx
 * @Description: 微信消息推送关联小程序实体类
 * @Author tangsh
 * @DateTime 2020年3月4日 下午5:51:50
 */
public class WxTemplateXcx  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String appid;  
    private String pagepath;
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getPagepath() {
		return pagepath;
	}
	public void setPagepath(String pagepath) {
		this.pagepath = pagepath;
	}
}
