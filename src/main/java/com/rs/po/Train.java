package com.rs.po;

import java.util.List;

/**
 * 培训实体类
 * @ClassName: Train
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月10日 下午2:21:22
 */
public class Train extends BasePo<Train> {
	private static final long serialVersionUID = 1L;
	/**
	 * 培训系统编码
	 */
	private String train_code;
	/**
	 * 培训标题
	 */
	private String train_title;
	/**
	 * 培训省
	 */
	private String train_province;
	/**
	 * 培训市
	 */
	private String train_city;
	/**
	 * 培训区
	 */
	private String train_area;
	/**
	 * 培训镇
	 */
	private String train_town;
	/**
	 * 培训村（社区）
	 */
	private String train_vill;
	/**
	 * 培训年度
	 */
	private String train_year;
	/**
	 * 培训季度【第一季度、第二季度、第三季度、第四季度】
	 */
	private String train_quarter;
	/**
	 * 培训状态
	 */
	private Integer train_state;
	/**
	 * 操作用户系统编码
	 */
	private String user_code;
	/**
	 * 操作用户姓名
	 */
	private String user_name;
	/**
	 * 添加时间
	 */
	private String addtime;				
	/**
	 * 结束时间
	 */
	private String endtime;             
	/******************数据库辅助字段*************************/
	/**
	 * 
	 */
	private int is_pass;
	/**
	 * 
	 */
	private String addr_info;
	/**
	 * 查询类型
	 */
	private int owner_type;
	
	private List<Train> trainlist;
	public String getTrain_code() {
		return train_code;
	}
	public void setTrain_code(String train_code) {
		this.train_code = train_code;
	}
	public String getTrain_title() {
		return train_title;
	}
	public void setTrain_title(String train_title) {
		this.train_title = train_title;
	}
	public String getTrain_province() {
		return train_province;
	}
	public void setTrain_province(String train_province) {
		this.train_province = train_province;
	}
	public String getTrain_city() {
		return train_city;
	}
	public void setTrain_city(String train_city) {
		this.train_city = train_city;
	}
	public String getTrain_area() {
		return train_area;
	}
	public void setTrain_area(String train_area) {
		this.train_area = train_area;
	}
	public String getTrain_town() {
		return train_town;
	}
	public void setTrain_town(String train_town) {
		this.train_town = train_town;
	}
	public String getTrain_vill() {
		return train_vill;
	}
	public void setTrain_vill(String train_vill) {
		this.train_vill = train_vill;
	}
	public String getTrain_year() {
		return train_year;
	}
	public void setTrain_year(String train_year) {
		this.train_year = train_year;
	}
	public String getTrain_quarter() {
		return train_quarter;
	}
	public void setTrain_quarter(String train_quarter) {
		this.train_quarter = train_quarter;
	}
	public Integer getTrain_state() {
		return train_state;
	}
	public void setTrain_state(Integer train_state) {
		this.train_state = train_state;
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
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public int getIs_pass() {
		return is_pass;
	}
	public void setIs_pass(int is_pass) {
		this.is_pass = is_pass;
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
	public List<Train> getTrainlist() {
		return trainlist;
	}
	public void setTrainlist(List<Train> trainlist) {
		this.trainlist = trainlist;
	}
}
