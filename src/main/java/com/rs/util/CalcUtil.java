package com.rs.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
/**
 * 计算辅助类
 * @ClassName: CalcUtil
 * @Description: 
 * @Author sven
 * @DateTime 2019年7月25日 上午10:49:28
 */
public class CalcUtil {
	
	/**
	 * 加
	 */
	public static String addtr(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.add(b2).toString();
	}
	
	/**
	 * 减
	 */
	public static String subtr(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.subtract(b2).toString();
	}

	/**
	 * 乘
	 */
	public static String multiply(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.multiply(b2).toString();
	}

	/**
	 * 除
	 */
	public static String division(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		if("0.00".equals(v1)||"0.00".equals(v2)) {
			return "0.00";
		}else {
		return b1.divide(b2, 10, RoundingMode.HALF_DOWN).toString();
		}
	}

	/**
	 * 乘
	 */
	public static String multiply(String v1, String v2, String v3) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		BigDecimal b3 = new BigDecimal(v3);
		return b1.multiply(b2).multiply(b3).toString();
	}
	
	/**
	 * 
	 * @Title: reserve
	 * @Description: 保留小数位
	 * @Author tangsh
	 * @DateTime 2020年1月9日 下午5:52:41
	 * @param v1
	 * @param places
	 * @return
	 */
	public static String reserve(String v1, Integer places) {
		BigDecimal bg = new BigDecimal(v1);
		return bg.setScale(places, BigDecimal.ROUND_HALF_UP).toString();
	}
}
