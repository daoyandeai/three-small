package com.rs.po.returnPo;

/**
 * 
 * @ClassName: NewsCategory
 * @Description: 宣传栏目实体类
 * @Author tangsh
 * @DateTime 2019年12月16日 下午3:03:04
 */
public class NewsCategoryReturn {

	private String news_category_code;		//新闻栏目系统编码
	private String news_category_name;		//新闻栏目名称
	private String user_code;				//操作用户系统编码
	private String user_name;				//操作用户姓名

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
}
