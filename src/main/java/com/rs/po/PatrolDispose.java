package com.rs.po;

/**
 * 
 * @ClassName: PatrolDispose
 * @Description: 处理方式实体
 * @Author tangsh
 * @DateTime 2020年5月12日 上午9:54:02
 */
public class PatrolDispose extends BasePo<PatrolDispose>{
	
	private static final long serialVersionUID = 1L;
	/**
     *处理方式编码
     * */
    private String dispose_code;
	/**
     *处理方式名称
     * */
    private String dispose_name;
    /**
     *添加用户系统编码
     * */
    private String user_code_add;
    /**
     *添加用户系统名称
     * */
    private String user_name_add;
    
    /**
	 * 状态（1：启用、2：禁用）
	 */
	private Integer state;
    
    /**
	 * 更新用户系统编码
	 */
	private String user_code_update;
	/**
	 * 更新用户姓名
	 */
	private String user_name_update;
	/**
	 * 更新时间
	 */
	private String update_time;

    public String getDispose_code() {
        return dispose_code;
    }

    public void setDispose_code(String dispose_code) {
        this.dispose_code = dispose_code;
    }
    
    public String getDispose_name() {
        return dispose_name;
    }

    public void setDispose_name(String dispose_name) {
        this.dispose_name = dispose_name;
    }

	public String getUser_code_add() {
		return user_code_add;
	}

	public void setUser_code_add(String user_code_add) {
		this.user_code_add = user_code_add;
	}

	public String getUser_name_add() {
		return user_name_add;
	}

	public void setUser_name_add(String user_name_add) {
		this.user_name_add = user_name_add;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getUser_code_update() {
		return user_code_update;
	}

	public void setUser_code_update(String user_code_update) {
		this.user_code_update = user_code_update;
	}

	public String getUser_name_update() {
		return user_name_update;
	}

	public void setUser_name_update(String user_name_update) {
		this.user_name_update = user_name_update;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
}