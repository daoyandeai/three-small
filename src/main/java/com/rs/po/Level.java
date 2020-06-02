package com.rs.po;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @ClassName: Level
 * @Description: 工具实体类
 * @Author tangsh
 * @DateTime 2019年11月25日 下午3:27:42
 */
public class Level implements Serializable {

	private static final long serialVersionUID = 1L;
	private String city;	 		   //行政区域编码
	private String area;			  //区域名称
	private String user_level;			  //区域名称
	private String count_num;			  //区域名称
	private String region_name;			  //区域名称

	private List<Level> levelList;

	public String getRegion_name() {
		return region_name;
	}

	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}

	public String getCount_num() {
		return count_num;
	}

	public void setCount_num(String count_num) {
		this.count_num = count_num;
	}

	public List<Level> getLevelList() {
		return levelList;
	}

	public void setLevelList(List<Level> levelList) {
		this.levelList = levelList;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getUser_level() {
		return user_level;
	}

	public void setUser_level(String user_level) {
		this.user_level = user_level;
	}
}
