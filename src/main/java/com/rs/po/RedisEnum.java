package com.rs.po;

public enum RedisEnum {
	//用户token
	TOKEN("user_token_"),

	//短信验证码
	MESSCODE("messcode_"),REGION("region");

	private String key;

	private RedisEnum(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
