package com.rs.po.returnPo;
import java.io.Serializable;
/**
 * 培训试题题目
 * @ClassName: TrainExamTopicReturn
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月10日 下午2:36:57
 */
public class TrainExamTopicReturn implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 培训试题题目系统编码
	 */
	private String train_exam_topic_code;
	/**
	 * 培训试题题目标题
	 */
	private String train_exam_topic_title;
	/**
	 * 培训试题题目标准答案
	 */
	private String train_exam_topic_standard;
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
	/******************数据库辅助字段*************************/
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
}
