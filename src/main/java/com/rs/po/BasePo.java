package com.rs.po;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
/**
 * 公用基础PO
 * @ClassName: BasePo
 * @Description: 
 * @Author sven
 * @DateTime 2019年7月25日 上午10:12:17
 * @param <T>
 */
public class BasePo<T extends BasePo<T>> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<T> pager_list;						//公用的list  谁继承就是谁
	private T data;									//公用的bean  谁继承就是谁
	private int pager_count;						//数据条数
	private int pager_offset;						//查询的起始数
	private int pager_openset=10;					//查询条数
	private int param_type;
	private int pager_page;							//当前页
	private int next_page;							//下一页（0.没有下一页）
	private String update_time;
	private String add_time;
	private String sort_file;							//排序参数
	private String down_name;							//下载名称
	private String query_param;
	/**
	 * 结束时间
	 */
	private String end_time;
	
	{
		sort_file = "add_time";
	}
	public int getPager_count() {
		return pager_count;
	}
	public void setPager_count(int pager_count) {
		this.pager_count = pager_count;
	}
	public int getPager_offset() {
		return pager_offset;
	}
	public void setPager_offset(int pager_offset) {
		this.pager_offset = pager_offset;
	}
	public int getPager_openset() {
		return pager_openset;
	}
	public void setPager_openset(int pager_openset) {
		this.pager_openset = pager_openset;
	}
	public List<T> getPager_list() {
		return pager_list;
	}
	public void setPager_list(List<T> pager_list) {
		this.pager_list = pager_list;
	}
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	@Override
	public String toString() {
		Class<?> clz = this.getClass();
		Field[] declaredFields = clz.getDeclaredFields();
		StringBuilder sb = new StringBuilder(100);
		sb.append(clz.getName()+"[");
		try {
			for (Field f : declaredFields) {
				f.setAccessible(true);
				sb.append(f.getName()+"="+f.get(this)+", ");
			} 
		}catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return sb.substring(0, sb.length()-2)+"]";
	}
	public int getParam_type() {
		return param_type;
	}
	public void setParam_type(int param_type) {
		this.param_type = param_type;
	}
	public int getPager_page() {
		return pager_page;
	}
	public void setPager_page(int pager_page) {
		this.pager_page = pager_page;
	}
	public int getNext_page() {
		return next_page;
	}
	public void setNext_page(int next_page) {
		this.next_page = next_page;
	}
	public String getSort_file() {
		return sort_file;
	}
	public void setSort_file(String sort_file) {
		this.sort_file = sort_file;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public String getAdd_time() {
		return add_time;
	}

	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getDown_name() {
		return down_name;
	}
	public void setDown_name(String down_name) {
		this.down_name = down_name;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getQuery_param() {
		return query_param;
	}
	public void setQuery_param(String query_param) {
		this.query_param = query_param;
	}
	
}
