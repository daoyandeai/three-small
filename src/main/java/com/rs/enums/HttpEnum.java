package com.rs.enums;

public enum HttpEnum {
	/**
	 * POST请求
	 */
	POST("POST", "POST"),
	/**
	 * GET请求
	 */
	GET("GET", "GET"),
	/**
	 * DELETE请求
	 */
	DELETE("DELETE", "DELETE"),
	/**
	 * PUT请求
	 */
	PUT("PUT", "PUT"),
	/**
	 * 字符集编码UTF-8
	 */
	UTF8("UTF8", "UTF8"),
	/**
	 * application/json
	 */
	CONTENTTYPE_JSON("JSON", "application/json"),
	/**
	 * text/plain
	 */
	CONTENTTYPE_TEXT("text", "text/plain");

	/**
	 * 返回代码
	 */
	private String key;
	/**
	 * 返回值
	 */
	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private HttpEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

}
