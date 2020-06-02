package com.rs.po;
import java.util.List;
/**
 * 用户培训课程日志实体类
 * @ClassName: TrainCourseLog
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月10日 下午2:29:18
 */
public class TrainCourseLog extends BasePo<TrainCourseLog>{
	private static final long serialVersionUID = 1L;
	/**
	 * 用户培训课程日志系统编码
	 */
	private String train_course_log_code;
	/**
	 * 培训课程系统编码
	 */
	private String train_course_code;
	/**
	 * 培训系统编码
	 */
	private String train_code;
	/**
	 * 乡厨系统编码
	 */
	private String user_code_chef;
	/**
	 * 乡厨姓名
	 */
	private String user_name_chef;
	/******************数据库辅助字段*************************/
	private TrainCourseLog traincourselog;
	private List<TrainCourseLog> traincourseloglist;
	public String getTrain_course_log_code() {
		return train_course_log_code;
	}
	public void setTrain_course_log_code(String train_course_log_code) {
		this.train_course_log_code = train_course_log_code;
	}
	public String getTrain_course_code() {
		return train_course_code;
	}
	public void setTrain_course_code(String train_course_code) {
		this.train_course_code = train_course_code;
	}
	public String getTrain_code() {
		return train_code;
	}
	public void setTrain_code(String train_code) {
		this.train_code = train_code;
	}
	public String getUser_code_chef() {
		return user_code_chef;
	}
	public void setUser_code_chef(String user_code_chef) {
		this.user_code_chef = user_code_chef;
	}
	public String getUser_name_chef() {
		return user_name_chef;
	}
	public void setUser_name_chef(String user_name_chef) {
		this.user_name_chef = user_name_chef;
	}
	public TrainCourseLog getTraincourselog() {
		return traincourselog;
	}
	public void setTraincourselog(TrainCourseLog traincourselog) {
		this.traincourselog = traincourselog;
	}
	public List<TrainCourseLog> getTraincourseloglist() {
		return traincourseloglist;
	}
	public void setTraincourseloglist(List<TrainCourseLog> traincourseloglist) {
		this.traincourseloglist = traincourseloglist;
	}
}
