package com.rs.po;

/**
 * 
 * @ClassName: Log
 * @Description: 
 * @Author tangsh
 * @DateTime 2020年2月12日 上午10:44:48
 */
public class Log extends BasePo<Log>{
	
	private static final long serialVersionUID = 1L;

	private String log_code;

    private String log_type;

    private String log_result;

    private String log_remark;

    private String log_user_code;

    private String log_user_name;
    
    private String company_code;
    
    private String add_time;

    public String getLog_code() {
        return log_code;
    }

    public void setLog_code(String log_code) {
        this.log_code = log_code;
    }

    public String getLog_type() {
        return log_type;
    }

    public void setLog_type(String log_type) {
        this.log_type = log_type;
    }

    public String getLog_result() {
        return log_result;
    }

    public void setLog_result(String log_result) {
        this.log_result = log_result;
    }

    public String getLog_remark() {
        return log_remark;
    }

    public void setLog_remark(String log_remark) {
        this.log_remark = log_remark;
    }

    public String getLog_user_code() {
        return log_user_code;
    }

    public void setLog_user_code(String log_user_code) {
        this.log_user_code = log_user_code;
    }

    public String getLog_user_name() {
        return log_user_name;
    }

    public void setLog_user_name(String log_user_name) {
        this.log_user_name = log_user_name;
    }

	public String getCompany_code() {
		return company_code;
	}

	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}

	public String getAdd_time() {
		return add_time;
	}

	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}

}