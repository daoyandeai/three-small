package com.rs.po.returnPo;

import java.io.Serializable;

/**
 * 
 * @ClassName: AccessoryReturn
 * @Description: 附件信息
 * @Author tangsh
 * @DateTime 2019年12月26日 上午10:17:13
 */
public class AccessoryReturn implements Serializable{
    /**
	  * @Fields serialVersionUID : 
	  */
	
	private static final long serialVersionUID = 1L;
	/*
     * 附件地址
     */
    private String name;
    /*
     * 附件类型
     */
    private String url;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}