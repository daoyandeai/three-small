package com.rs.po;

import java.util.List;

/**
 * 区域页面参数模板实体类
 * @ClassName: PageConfigRegion
 * @Description: 
 * @Author sven
 * @DateTime 2020年2月10日 下午4:53:09
 */
public class PageConfigRegion extends BasePo<PageConfigRegion> {
	
	/**
	  * @Fields serialVersionUID : 
	  */
	
	private static final long serialVersionUID = 1L;
	/**
	 * 区域页面参数模板编码
	 */
	private String page_config_region_code;
	/**
	 * 页面参数模板编码
	 */
	private String page_config_code;
	/**
	 * 页面参数模板名称
	 */
	private String page_config_name;
	/**
	 * 省
	 */
	private String province;
	/**
	 * 市
	 */
	private String city;
	/**
	 * 区
	 */
	private String area;
	/**
	 * 镇
	 */
	private String town;
	/**
	 * 村（社区）
	 */
	private String vill;
	/**
	 * 区域等级（省1、市2、区3、镇4、村（社区）5）
	 */
	private Integer level;
	/**
	 * 状态（1：启用、2：禁用）
	 */
	private Integer state;
	/**
	 * 诚信评价结果计算周期
	 */
	private Integer calc_period;
	/**
	 * 配置内容json
	 */
	private Object page_region_content;
	/**
	 * 新增用户系统编码
	 */
	private String user_code_add;
	/**
	 * 新增用户姓名
	 */
	private String user_name_add;
	/**
	 * 添加时间
	 */
	private String add_time;
	/**
	 * 更新用户系统编码
	 */
	private String user_code_update;
	/**
	 * 更新用户姓名
	 */
	private String user_name_update;
	/**
	 * 更新时间
	 */
	private String update_time;
	/******************数据库辅助字段*************************/
	/**
	 * 
	 */
	private String addr_info;
	/**
	 * 查询类型
	 */
	private int owner_type;
	/**
	 * 页面模块（1：诚信等级、2：信息公示、3：自查自纠）
	 */
	private Integer page_module; 
	/**
	 * 自查自纠配置
	 */
	private List<PageConfigRegion> check_self_list;
	/**
	 * 食品监管分类编码
	 */
	private String companytype_code;
	/**
	 * 区域页面参数模板编码拼接串
	 */
	private String page_config_region_codes;
	public String getPage_config_region_code() {
		return page_config_region_code;
	}
	public void setPage_config_region_code(String page_config_region_code) {
		this.page_config_region_code = page_config_region_code;
	}
	public String getPage_config_code() {
		return page_config_code;
	}
	public void setPage_config_code(String page_config_code) {
		this.page_config_code = page_config_code;
	}
	public String getPage_config_name() {
		return page_config_name;
	}
	public void setPage_config_name(String page_config_name) {
		this.page_config_name = page_config_name;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
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
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getVill() {
		return vill;
	}
	public void setVill(String vill) {
		this.vill = vill;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getUser_code_add() {
		return user_code_add;
	}
	public void setUser_code_add(String user_code_add) {
		this.user_code_add = user_code_add;
	}
	public String getUser_name_add() {
		return user_name_add;
	}
	public void setUser_name_add(String user_name_add) {
		this.user_name_add = user_name_add;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getUser_code_update() {
		return user_code_update;
	}
	public void setUser_code_update(String user_code_update) {
		this.user_code_update = user_code_update;
	}
	public String getUser_name_update() {
		return user_name_update;
	}
	public void setUser_name_update(String user_name_update) {
		this.user_name_update = user_name_update;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getAddr_info() {
		return addr_info;
	}
	public void setAddr_info(String addr_info) {
		this.addr_info = addr_info;
	}
	public int getOwner_type() {
		return owner_type;
	}
	public void setOwner_type(int owner_type) {
		this.owner_type = owner_type;
	}
	public Integer getCalc_period() {
		return calc_period;
	}
	public void setCalc_period(Integer calc_period) {
		this.calc_period = calc_period;
	}
	public Integer getPage_module() {
		return page_module;
	}
	public void setPage_module(Integer page_module) {
		this.page_module = page_module;
	}
	public Object getPage_region_content() {
		return page_region_content;
	}
	public void setPage_region_content(Object page_region_content) {
		this.page_region_content = page_region_content;
	}
	public List<PageConfigRegion> getCheck_self_list() {
		return check_self_list;
	}
	public void setCheck_self_list(List<PageConfigRegion> check_self_list) {
		this.check_self_list = check_self_list;
	}
	public String getCompanytype_code() {
		return companytype_code;
	}
	public void setCompanytype_code(String companytype_code) {
		this.companytype_code = companytype_code;
	}
	public String getPage_config_region_codes() {
		return page_config_region_codes;
	}
	public void setPage_config_region_codes(String page_config_region_codes) {
		this.page_config_region_codes = page_config_region_codes;
	}
}
