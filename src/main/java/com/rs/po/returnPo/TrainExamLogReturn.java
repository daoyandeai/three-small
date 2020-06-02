package com.rs.po.returnPo;
import java.io.Serializable;
/**
 * 培训试题日志实体类
 * @ClassName: TrainExamLogReturn
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月18日 上午11:42:36
 */
public class TrainExamLogReturn implements Serializable {
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
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
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
	public int getIs_pass() {
		return is_pass;
	}
	public void setIs_pass(int is_pass) {
		this.is_pass = is_pass;
	}	
}
