package com.rs.po;

/**
 * 
 * @ClassName: NewsCategory
 * @Description: 宣传栏目实体类
 * @Author tangsh
 * @DateTime 2019年12月16日 下午3:03:04
 */
public class NewsCategory extends BasePo<NewsCategory> {

	private static final long serialVersionUID = 1L;
	private String news_category_code;		//新闻栏目系统编码
	private String news_category_name;		//新闻栏目名称
	private Integer news_category_state;	//审核状态(1：生效、2：失效)
	private String user_code;				//操作用户系统编码
	private String user_name;				//操作用户姓名
	private String addtime;				//添加时间
	
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
	public Integer getNews_category_state() {
		return news_category_state;
	}
	public void setNews_category_state(Integer news_category_state) {
		this.news_category_state = news_category_state;
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
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String add_time) {
		this.addtime = add_time;
	}
}
