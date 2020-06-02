package com.rs.po;
import java.util.List;
/**
 * 培训试题实体类
 * @ClassName: TrainExam
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月10日 下午2:30:57
 */
public class TrainExam extends BasePo<TrainExam> {
	private static final long serialVersionUID = 1L;
	/**
	 * 培训试题系统编码
	 */
	private String train_exam_code;
	/**
	 * 培训试题标题
	 */
	private String train_exam_title;
	/**
	 * 合格题数
	 */
	private Integer train_exam_standard;
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
	private List<TrainExamTopic> train_exam_topic_list;
	public String getTrain_exam_code() {
		return train_exam_code;
	}
	public void setTrain_exam_code(String train_exam_code) {
		this.train_exam_code = train_exam_code;
	}
	public String getTrain_exam_title() {
		return train_exam_title;
	}
	public void setTrain_exam_title(String train_exam_title) {
		this.train_exam_title = train_exam_title;
	}
	public Integer getTrain_exam_standard() {
		return train_exam_standard;
	}
	public void setTrain_exam_standard(Integer train_exam_standard) {
		this.train_exam_standard = train_exam_standard;
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
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public List<TrainExamTopic> getTrain_exam_topic_list() {
		return train_exam_topic_list;
	}
	public void setTrain_exam_topic_list(List<TrainExamTopic> train_exam_topic_list) {
		this.train_exam_topic_list = train_exam_topic_list;
	}
}
