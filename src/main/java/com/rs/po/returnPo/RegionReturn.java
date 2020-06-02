package com.rs.po.returnPo;

/**
 * 
 * @ClassName: Region
 * @Description: 行政区域实体类
 * @Author tangsh
 * @DateTime 2019年11月25日 下午3:27:42
 */
public class RegionReturn{
	private String region_code;	 		   //行政区域编码
	private String region_name;			  //区域名称

	public RegionReturn()  {
	}

	public RegionReturn(String region_code, String region_name) {
		this.region_code = region_code;
		this.region_name = region_name;
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
	public String getRegion_code() {
		return region_code;
	}
}
