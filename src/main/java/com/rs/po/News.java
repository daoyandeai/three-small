package com.rs.po;
/**
 * 
 * @ClassName: News
 * @Description: 宣传实体类
 * @Author tangsh
 * @DateTime 2019年12月16日 下午3:03:15
 */
public class News extends BasePo<News> {
	
	private static final long serialVersionUID = 1L;
	private String news_code;			//新闻系统编码
	private String news_title;			//新闻标题
	private String news_imgurl;			//新闻封面图片url
	private String news_content;		//新闻内容
	private	String news_province;		//省
	private String news_city;			//市
	private String news_area;			//区
	private String news_town;			//镇
	private String news_vill;			//村
	private String user_code;			//操作用户系统编码
	private String user_name;			//操作用户姓名
	private Integer news_state; 		//审核状态(1：生效、2：失效)
	private String news_summary;		//新闻简介
	private String news_category_code;	//新闻栏目编码
	private String news_category_name;	//新闻栏目名称
	private String addtime;			//添加时间
	private	String companytype_codes; // 分类
	
	/******************数据库辅助字段*************************/
	private String news_update_state; //判断是否只更新状态
	private String addr_info;
	private String search_tag;
	private String user_type;				//用户类型(超级管理人员、举办者、乡厨、平台管理员、协管员、农家乐、乡村酒店、食堂)
	
	
	public String getCompanytype_codes() {
		return companytype_codes;
	}
	public void setCompanytype_codes(String companytype_codes) {
		this.companytype_codes = companytype_codes;
	}
	public String getSearch_tag() {
		return search_tag;
	}
	public void setSearch_tag(String search_tag) {
		this.search_tag = search_tag;
	}
	public String getNews_code() {
		return news_code;
	}
	public void setNews_code(String news_code) {
		this.news_code = news_code;
	}
	public String getNews_title() {
		return news_title;
	}
	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}
	public String getNews_imgurl() {
		return news_imgurl;
	}
	public void setNews_imgurl(String news_imgurl) {
		this.news_imgurl = news_imgurl;
	}
	public String getNews_content() {
		return news_content;
	}
	public void setNews_content(String news_content) {
		this.news_content = news_content;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Integer getNews_state() {
		return news_state;
	}
	public void setNews_state(Integer news_state) {
		this.news_state = news_state;
	}
	public String getNews_category_code() {
		return news_category_code;
	}
	public void setNews_category_code(String news_category_code) {
		this.news_category_code = news_category_code;
	}
	public String getNews_category_name() {
		return news_category_name;
	}
	public void setNews_category_name(String news_category_name) {
		this.news_category_name = news_category_name;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String add_time) {
		this.addtime = add_time;
	}
	public String getNews_province() {
		return news_province;
	}
	public void setNews_province(String news_province) {
		this.news_province = news_province;
	}
	public String getNews_city() {
		return news_city;
	}
	public void setNews_city(String news_city) {
		this.news_city = news_city;
	}
	public String getNews_area() {
		return news_area;
	}
	public void setNews_area(String news_area) {
		this.news_area = news_area;
	}
	public String getNews_town() {
		return news_town;
	}
	public void setNews_town(String news_town) {
		this.news_town = news_town;
	}
	public String getNews_vill() {
		return news_vill;
	}
	public void setNews_vill(String news_vill) {
		this.news_vill = news_vill;
	}
	public String getNews_summary() {
		return news_summary;
	}
	public void setNews_summary(String news_summary) {
		this.news_summary = news_summary;
	}
	public String getNews_update_state() {
		return news_update_state;
	}
	public void setNews_update_state(String news_update_state) {
		this.news_update_state = news_update_state;
	}
	public String getAddr_info() {
		return addr_info;
	}
	public void setAddr_info(String addr_info) {
		this.addr_info = addr_info;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
}
