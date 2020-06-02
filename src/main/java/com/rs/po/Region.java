package com.rs.po;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @ClassName: Region
 * @Description: 行政区域实体类
 * @Author tangsh
 * @DateTime 2019年11月25日 下午3:27:42
 */
public class Region extends BasePo<Region> implements Serializable {
	private static final long serialVersionUID = 1L;
	private String region_code;	 		   //行政区域编码				
	private String region_name;			  //区域名称				
	private Integer region_type;	      //区域类型(1：省（直辖市）、2：市（州）、3：县（市、区）、4：乡（镇）、5：村（社区）)				
	private String region_high_code;      //父节点编码			
	private String note;			     //区域备注		
	private String addtime;		   		 //添加时间			
	private Region region;		         //行政区域对象		
	private List<Region> regionlist; 	//行政区域集合
	/******************数据库辅助字段*************************/
	private String region_province;		//行政级别
	private String region_city;			//行政级别
	private String region_area;			//行政级别
	private String region_town;			//行政级别
	private String region_vill;			//行政级别
	
	private String region_province_new;		//行政级别
	private String region_city_new;			//行政级别
	private String region_area_new;			//行政级别
	private String region_town_new;			//行政级别
	
	public String getRegion_code() {
		return region_code;
	}
	public void setRegion_code(String region_code) {
		this.region_code = region_code;
	}
	public String getRegion_name() {
		return region_name;
	}
	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}
	public Integer getRegion_type() {
		return region_type;
	}
	public void setRegion_type(Integer region_type) {
		this.region_type = region_type;
	}
	public String getRegion_high_code() {
		return region_high_code;
	}
	public void setRegion_high_code(String region_high_code) {
		this.region_high_code = region_high_code;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public List<Region> getRegionlist() {
		return regionlist;
	}
	public void setRegionlist(List<Region> regionlist) {
		this.regionlist = regionlist;
	}
	public String getRegion_province() {
		return region_province;
	}
	public void setRegion_province(String region_province) {
		this.region_province = region_province;
	}
	public String getRegion_city() {
		return region_city;
	}
	public void setRegion_city(String region_city) {
		this.region_city = region_city;
	}
	public String getRegion_area() {
		return region_area;
	}
	public void setRegion_area(String region_area) {
		this.region_area = region_area;
	}
	public String getRegion_town() {
		return region_town;
	}
	public void setRegion_town(String region_town) {
		this.region_town = region_town;
	}
	public String getRegion_vill() {
		return region_vill;
	}
	public void setRegion_vill(String region_vill) {
		this.region_vill = region_vill;
	}
	public String getRegion_province_new() {
		return region_province_new;
	}
	public void setRegion_province_new(String region_province_new) {
		this.region_province_new = region_province_new;
	}
	public String getRegion_city_new() {
		return region_city_new;
	}
	public void setRegion_city_new(String region_city_new) {
		this.region_city_new = region_city_new;
	}
	public String getRegion_area_new() {
		return region_area_new;
	}
	public void setRegion_area_new(String region_area_new) {
		this.region_area_new = region_area_new;
	}
	public String getRegion_town_new() {
		return region_town_new;
	}
	public void setRegion_town_new(String region_town_new) {
		this.region_town_new = region_town_new;
	}
}
