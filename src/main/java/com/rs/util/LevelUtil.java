package com.rs.util;

import org.springframework.util.StringUtils;

import com.rs.po.PageConfigRegion;

/**
 * 级别辅助类
 * @ClassName: LevelUtil
 * @Description: 
 * @Author sven
 * @DateTime 2020年2月11日 下午3:04:18
 */
public class LevelUtil {
	
	/**
	 * 根据传入的对象身上地区判断对应地区级别
	 * @Title: AreaLevel
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月11日 下午3:05:29
	 * @param obj
	 * @param level
	 * @return
	 */
	public static int AreaLevel(Object obj) {
		String city = "";
		String area = "";
		String town = "";
		String vill = "";
		if(obj instanceof PageConfigRegion) {
			PageConfigRegion pcr = (PageConfigRegion)obj;
			city = pcr.getCity();
			area = pcr.getArea();
			town = pcr.getTown();
			vill = pcr.getVill();
		}
		return AreaToLevel(city,area,town,vill);
	}
	
	
	private static int AreaToLevel(String city,String area,String town,String vill) {
		int level = 1;
		if (StringUtils.isEmpty(city)) {
			level = 1;
		}else if (StringUtils.isEmpty(area)) {
			level = 2;
		}else if (StringUtils.isEmpty(town)) {
			level = 3;
		}else if (StringUtils.isEmpty(vill)) {
			level = 4;
		}else if(!StringUtils.isEmpty(vill)) {
			level = 5;
		}
		return level;
	}
}
