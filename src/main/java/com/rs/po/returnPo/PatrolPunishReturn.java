package com.rs.po.returnPo;

import java.io.Serializable;

/**
 * 
 * @ClassName: PatrolPunish
 * @Description: 处罚方式实体
 * @Author tangsh
 * @DateTime 2020年5月12日 上午9:54:26
 */
public class PatrolPunishReturn implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
    * 处罚方式编码
    * */
    private String punish_code;
    /**
     *处罚方式名称
     * */
    private String punish_name;
    /**
     *添加人员编码
     * */
    private String user_code_add;
    /**
     *添加用户系统名称
     * */
    private String user_name_add;
    /**
     *添加时间
     * */
    private String add_time;
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

    public String getPunish_code() {
        return punish_code;
    }

    public void setPunish_code(String punish_code) {
        this.punish_code = punish_code;
    }

    public String getPunish_name() {
        return punish_name;
    }

    public void setPunish_name(String punish_name) {
        this.punish_name = punish_name;
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

	public String getAdd_time() {
		return add_time;
	}

	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
}