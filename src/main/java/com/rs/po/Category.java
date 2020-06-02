package com.rs.po;
/**
 * 品种分类实体类
 * @ClassName: Category
 * @Description: 
 * @Author sven
 * @DateTime 2020年4月1日 上午10:09:13
 */
public class Category extends BasePo<Category> {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 分类编码
	 */
    private String category_code;
    /**
     * 分类名称
     */
    private String category_name;
    /**
     * 状态（1：启用、2：禁用）
     */
    private Integer state;
    /**
	 * 新增用户系统编码
	 */
	private String user_code_add;
	/**
	 * 新增用户姓名
	 */
	private String user_name_add;
	/**
	 * 添加时间
	 */
	private String add_time;
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
	/*****************数据库辅助字段*******************/
    /**
	 * 查询归属（1：全部、2：自己）
	 */
	private int owner_type;
    public String getCategory_code() {
        return category_code;
    }

    public void setCategory_code(String category_code) {
        this.category_code = category_code;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

	public String getAdd_time() {
		return add_time;
	}

	public void setAdd_time(String add_time) {
		this.add_time = add_time;
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

	public int getOwner_type() {
		return owner_type;
	}

	public void setOwner_type(int owner_type) {
		this.owner_type = owner_type;
	}
}