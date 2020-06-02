package com.rs.po;

/**
 * 
 * @ClassName: Log
 * @Description: 
 * @Author tangsh
 * @DateTime 2020年2月12日 上午10:44:48
 */
public class Lonlat extends BasePo<Lonlat>{
	
	private static final long serialVersionUID = 1L;

	private String table_code;
	private Integer table_type;
	private String longitude;
	private String latitude;
	public String getTable_code() {
		return table_code;
	}
	public void setTable_code(String table_code) {
		this.table_code = table_code;
	}
	public Integer getTable_type() {
		return table_type;
	}
	public void setTable_type(Integer table_type) {
		this.table_type = table_type;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	
}