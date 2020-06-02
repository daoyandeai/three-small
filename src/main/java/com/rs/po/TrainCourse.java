package com.rs.po;
/**
 * 培训课程实体类
 * @ClassName: TrainCourse
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月10日 下午2:25:32
 */
public class TrainCourse extends BasePo<TrainCourse> {
	private static final long serialVersionUID = 1L;
	/**
	 * 培训课程系统编码
	 */
	private String train_course_code;
	/**
	 * 培训课程标题
	 */
	private String train_course_title;
	/**
	 * 培训内容
	 */
	private String train_course_content;
	/**
	 * 培训学时
	 */
	private String train_course_period;
	/**
	 * 培训课程状态(1：启用、2：禁用)
	 */
	private int train_course_state;
	/**
	 * 培训系统编码
	 */
	private String train_code;
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
	/******************数据库辅助字段*************************/
	private int is_study;	//是否已学习
	public int getIs_study() {
		return is_study;
	}
	public void setIs_study(int is_study) {
		this.is_study = is_study;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getTrain_course_code() {
		return train_course_code;
	}
	public void setTrain_course_code(String train_course_code) {
		this.train_course_code = train_course_code;
	}
	public String getTrain_course_title() {
		return train_course_title;
	}
	public void setTrain_course_title(String train_course_title) {
		this.train_course_title = train_course_title;
	}
	public String getTrain_course_content() {
		return train_course_content;
	}
	public void setTrain_course_content(String train_course_content) {
		this.train_course_content = train_course_content;
	}
	public String getTrain_course_period() {
		return train_course_period;
	}
	public void setTrain_course_period(String train_course_period) {
		this.train_course_period = train_course_period;
	}
	public String getTrain_code() {
		return train_code;
	}
	public void setTrain_code(String train_code) {
		this.train_code = train_code;
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
	public int getTrain_course_state() {
		return train_course_state;
	}
	public void setTrain_course_state(int train_course_state) {
		this.train_course_state = train_course_state;
	}
}
