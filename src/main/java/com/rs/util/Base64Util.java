package com.rs.util;

import java.util.Base64;
/**
 * Base64加解密算法
 * @ClassName: Base64Util
 * @Description: 
 * @Author sven
 * @DateTime 2019年8月30日 上午10:22:48
 */
public class Base64Util {
	
//	private String text="bodymon";
	
	/**
	 * 加密
	 * @Title: encode
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年8月30日 上午11:29:15
	 * @param text
	 * @return
	 */
	public static String encode(String text){
		String msg=Base64.getEncoder().encodeToString(text.getBytes());
		//System.out.println("加密："+msg);
		return msg;
	}
	
	/**
	 * 解密
	 * @Title: decode
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年8月30日 上午11:29:32
	 * @return
	 */
	public static String decode(String text){
		byte[] byteArr=Base64.getDecoder().decode(text);
		String msg = new String(byteArr);
		//System.out.println("解密:"+msg);
		return msg;
	}
	
	public static void main(String[] args) {
		String msg = encode("123");
		decode(msg);
	}
}
