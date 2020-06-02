package com.rs.po;
import java.util.List;
/**
 * 培训试题日志实体类
 * @ClassName: TrainExamLog
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月10日 下午2:33:03
 */
public class TrainExamLog extends BasePo<TrainExamLog> {
	private static final long serialVersionUID = 1L;
	/**
	 * 用户培训试题日志系统编码
	 */
	private String train_exam_log_code;
	/**
	 * 培训试题系统
	 */
	private String train_exam_code;
	/**
	 * 培训试题标题
	 */
	private String train_exam_title;
	/**
	 * 培训考试状态(1：不通过、2：通过 )
	 */
	private Integer train_exam_standard;
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
	/**
	 * 添加时间
	 */
	private String addtime;
	/**
	 * 试题题目集合
	 */
	private List<TrainExamTopicLog> train_exam_topic_log_list;
	/******************数据库辅助字段*************************/
	private TrainExamLog trainexamlog;
	/**
	 * 试题日志集合
	 */
	private List<TrainExamLog> train_exam_log_list;
	/**
	 * 总题目数
	 */
	private int sum_num;					
	/**
	 * 错误数
	 */
	private int error_num;					
	/**
	 * 正确数
	 */
	private int right_num;					
	/**
	 * 是否通过考试
	 */
	private int is_pass;			
	/**
	 * 结束时间
	 */
	private String end_time;
	/**
	 * 字段
	 */
	private String param_filed;
	/**
	 * 用户省
	 */
	private String user_province;			
	/**
	 * 用户市
	 */
	private String user_city;				
	/**
	 * 用户区
	 */
	private String user_area;				
	/**
	 * 管理镇
	 */
	private String user_town;			
	/**
	 * 管理村（社区）
	 */
	private String user_vill;		
	
	public int getIs_pass() {
		return is_pass;
	}
	public void setIs_pass(int is_pass) {
		this.is_pass = is_pass;
	}
	private List<TrainExam> trainexamlist;
	private List<TrainCourse> traincourselist;

	
	public List<TrainExam> getTrainexamlist() {
		return trainexamlist;
	}
	public void setTrainexamlist(List<TrainExam> trainexamlist) {
		this.trainexamlist = trainexamlist;
	}
	public List<TrainCourse> getTraincourselist() {
		return traincourselist;
	}
	public void setTraincourselist(List<TrainCourse> traincourselist) {
		this.traincourselist = traincourselist;
	}
	public int getSum_num() {
		return sum_num;
	}
	public void setSum_num(int sum_num) {
		this.sum_num = sum_num;
	}
	public int getError_num() {
		return error_num;
	}
	public void setError_num(int error_num) {
		this.error_num = error_num;
	}
	public int getRight_num() {
		return right_num;
	}
	public void setRight_num(int right_num) {
		this.right_num = right_num;
	}
	public List<TrainExamTopicLog> getTrain_exam_topic_log_list() {
		return train_exam_topic_log_list;
	}
	public void setTrain_exam_topic_log_list(List<TrainExamTopicLog> train_exam_topic_log_list) {
		this.train_exam_topic_log_list = train_exam_topic_log_list;
	}
	public String getTrain_exam_log_code() {
		return train_exam_log_code;
	}
	public void setTrain_exam_log_code(String train_exam_log_code) {
		this.train_exam_log_code = train_exam_log_code;
	}
	public String getTrain_exam_code() {
		return train_exam_code;
	}
	public void setTrain_exam_code(String train_exam_code) {
		this.train_exam_code = train_exam_code;
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
	public TrainExamLog getTrainexamlog() {
		return trainexamlog;
	}
	public void setTrainexamlog(TrainExamLog trainexamlog) {
		this.trainexamlog = trainexamlog;
	}
	public String getTrain_exam_title() {
		return train_exam_title;
	}
	public void setTrain_exam_title(String train_exam_title) {
		this.train_exam_title = train_exam_title;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public List<TrainExamLog> getTrain_exam_log_list() {
		return train_exam_log_list;
	}
	public void setTrain_exam_log_list(List<TrainExamLog> train_exam_log_list) {
		this.train_exam_log_list = train_exam_log_list;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getParam_filed() {
		return param_filed;
	}
	public void setParam_filed(String param_filed) {
		this.param_filed = param_filed;
	}
	public String getUser_province() {
		return user_province;
	}
	public void setUser_province(String user_province) {
		this.user_province = user_province;
	}
	public String getUser_city() {
		return user_city;
	}
	public void setUser_city(String user_city) {
		this.user_city = user_city;
	}
	public String getUser_area() {
		return user_area;
	}
	public void setUser_area(String user_area) {
		this.user_area = user_area;
	}
	public String getUser_town() {
		return user_town;
	}
	public void setUser_town(String user_town) {
		this.user_town = user_town;
	}
	public String getUser_vill() {
		return user_vill;
	}
	public void setUser_vill(String user_vill) {
		this.user_vill = user_vill;
	}
}
