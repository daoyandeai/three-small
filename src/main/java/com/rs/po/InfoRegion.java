package com.rs.po;

import java.util.List;

/**
 * 信息员实体类
 * @ClassName: InfoRegion
 * @Description: 
 * @Author sven
 * @DateTime 2020年1月2日 下午3:46:00
 */
public class InfoRegion extends BasePo<InfoRegion> {
    /**
	  * @Fields serialVersionUID : 
	  */
	
	private static final long serialVersionUID = 1L;
	/**
	 * 信息员编码
	 */
    private String user_code;
   /**
    * 地域编码
    */
    private String region_code;
    /**
     * 状态（1：启用、2：禁用）
     */
    private Integer state;
    /****************数据库辅助字段******************/
    /**
     * 地域编码 格式:'123','456'
     */
    private String region_codes;
    /**
     * 
     */
    private List<InfoRegion> info_region_list;
    /**
     * 
     */
    private String user_name;
    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getRegion_code() {
        return region_code;
    }

    public void setRegion_code(String region_code) {
        this.region_code = region_code;
    }

	public String getRegion_codes() {
		return region_codes;
	}

	public void setRegion_codes(String region_codes) {
		this.region_codes = region_codes;
	}

	public List<InfoRegion> getInfo_region_list() {
		return info_region_list;
	}

	public void setInfo_region_list(List<InfoRegion> info_region_list) {
		this.info_region_list = info_region_list;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
}