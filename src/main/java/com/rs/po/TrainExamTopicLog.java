package com.rs.po;
import java.util.List;
/**
 * 用户考试试题题目日志实体类
 * @ClassName: TrainExamTopicLog
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月10日 下午2:42:52
 */
public class TrainExamTopicLog extends BasePo<TrainExamTopicLog> {
	private static final long serialVersionUID = 1L;
	/**
	 * 用户考试试题题目日志系统编码
	 */
	private String train_exam_topic_log_code;
	/**
	 * 培训试题题目系统编码
	 */
	private String train_exam_topic_code;
	/**
	 * 培训试题题目标题
	 */
	private String train_exam_topic_title;
	/**
	 * 用户培训试题题目标准答案
	 */
	private String train_exam_topic_standard;
	/**
	 * 用户培训试题题目用户答案
	 */
	private String train_exam_topic_answer;
	/**
	 * 培训试题题目a选项
	 */
	private String train_exam_topic_option_a;
	/**
	 * 培训试题题目b选项
	 */
	private String train_exam_topic_option_b;
	/**
	 * 培训试题题目c选项
	 */
	private String train_exam_topic_option_c;
	/**
	 * 培训试题题目d选项
	 */
	private String train_exam_topic_option_d;
	/**
	 * 培训试题系统编码
	 */
	private String train_exam_code;
	/**
	 * 培训试题日志系统编码
	 */
	private String train_exam_log_code;
	/**
	 * 操作用户系统编码
	 */
	private String user_code;
	/**
	 * 操作用户姓名
	 */
	private String user_name;
	/******************数据库辅助字段*************************/
	private TrainExamTopicLog trainexamtopicLog;
	private List<TrainExamTopicLog> trainexamtopicLoglist;
	public String getTrain_exam_topic_log_code() {
		return train_exam_topic_log_code;
	}
	public void setTrain_exam_topic_log_code(String train_exam_topic_log_code) {
		this.train_exam_topic_log_code = train_exam_topic_log_code;
	}
	public String getTrain_exam_topic_code() {
		return train_exam_topic_code;
	}
	public void setTrain_exam_topic_code(String train_exam_topic_code) {
		this.train_exam_topic_code = train_exam_topic_code;
	}
	public String getTrain_exam_topic_title() {
		return train_exam_topic_title;
	}
	public void setTrain_exam_topic_title(String train_exam_topic_title) {
		this.train_exam_topic_title = train_exam_topic_title;
	}
	public String getTrain_exam_topic_standard() {
		return train_exam_topic_standard;
	}
	public void setTrain_exam_topic_standard(String train_exam_topic_standard) {
		this.train_exam_topic_standard = train_exam_topic_standard;
	}
	public String getTrain_exam_topic_answer() {
		return train_exam_topic_answer;
	}
	public void setTrain_exam_topic_answer(String train_exam_topic_answer) {
		this.train_exam_topic_answer = train_exam_topic_answer;
	}
	public String getTrain_exam_topic_option_a() {
		return train_exam_topic_option_a;
	}
	public void setTrain_exam_topic_option_a(String train_exam_topic_option_a) {
		this.train_exam_topic_option_a = train_exam_topic_option_a;
	}
	public String getTrain_exam_topic_option_b() {
		return train_exam_topic_option_b;
	}
	public void setTrain_exam_topic_option_b(String train_exam_topic_option_b) {
		this.train_exam_topic_option_b = train_exam_topic_option_b;
	}
	public String getTrain_exam_topic_option_c() {
		return train_exam_topic_option_c;
	}
	public void setTrain_exam_topic_option_c(String train_exam_topic_option_c) {
		this.train_exam_topic_option_c = train_exam_topic_option_c;
	}
	public String getTrain_exam_topic_option_d() {
		return train_exam_topic_option_d;
	}
	public void setTrain_exam_topic_option_d(String train_exam_topic_option_d) {
		this.train_exam_topic_option_d = train_exam_topic_option_d;
	}
	public String getTrain_exam_code() {
		return train_exam_code;
	}
	public void setTrain_exam_code(String train_exam_code) {
		this.train_exam_code = train_exam_code;
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
	public TrainExamTopicLog getTrainexamtopicLog() {
		return trainexamtopicLog;
	}
	public void setTrainexamtopicLog(TrainExamTopicLog trainexamtopicLog) {
		this.trainexamtopicLog = trainexamtopicLog;
	}
	public List<TrainExamTopicLog> getTrainexamtopicLoglist() {
		return trainexamtopicLoglist;
	}
	public void setTrainexamtopicLoglist(List<TrainExamTopicLog> trainexamtopicLoglist) {
		this.trainexamtopicLoglist = trainexamtopicLoglist;
	}
	public String getTrain_exam_log_code() {
		return train_exam_log_code;
	}
	public void setTrain_exam_log_code(String train_exam_log_code) {
		this.train_exam_log_code = train_exam_log_code;
	}	
}
