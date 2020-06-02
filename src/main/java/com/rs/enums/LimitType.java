package com.rs.enums;
/**
 * 限流类型
 * @ClassName: LimitType
 * @Description: 
 * @Author sven
 * @DateTime 2019年7月13日 下午10:15:38
 */
public enum LimitType {
	/**
	 * 自定义key
	 */
	CUSTOMER,
	/**
	 * 根据请求者IP
	 */
	IP;
}
