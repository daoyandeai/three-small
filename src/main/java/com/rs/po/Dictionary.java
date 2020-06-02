package com.rs.po;

public class Dictionary extends BasePo<Dictionary>{
	
	private static final long serialVersionUID = 1L;
	/**
    字典编码
    * */
    private String dictionary_code;
    /**
    字典字段名称
    * */
    private String dictionary_name;
    /**
    字典表类型（1：小经营店（餐饮）2：小经营店（销售）3：小作坊 4：小摊贩）
    * */
    private String companytag_code;
    /**
    字典模块名称
    * */
    private String dictionary_module;
    /**
    字典字段名称
    * */
    private String dictionary_field;
    /**
    字典状态
    * */
    private Integer state;
    /**
    分组字段
    * */
    private String dictionary_group;
    
    private String dictionary_codes;
    
    /**
	 * 食品监管分类编码集合
	 */
	private String companytype_codes;
	/**
	 * 食品监管分类名称集合
	 */
	private String companytype_names;
	/**
	 * 字典图片url
	 */
	private String dictionary_logo_url;
	/**
	 * 新增用户系统编码
	 */
	private String user_code_add;
	/**
	 * 新增用户姓名
	 */
	private String user_name_add;
	/**
	 * 更新用户系统编码
	 */
	private String user_code_update;
	/**
	 * 更新用户姓名
	 */
	private String user_name_update;
	/*******************数据库辅助字段********************/
	/**
	 * 结束时间
	 */
	private String end_time;
    public String getCompanytag_code() {
        return companytag_code;
    }

    public void setCompanytag_code(String companytag_code) {
        this.companytag_code = companytag_code;
    }

    public String getDictionary_module() {
        return dictionary_module;
    }

    public void setDictionary_module(String dictionary_module) {
        this.dictionary_module = dictionary_module;
    }

    public String getDictionary_field() {
		return dictionary_field;
	}

	public void setDictionary_field(String dictionary_field) {
		this.dictionary_field = dictionary_field;
	}

	public String getDictionary_code() {
        return dictionary_code;
    }

    public void setDictionary_code(String dictionary_code) {
        this.dictionary_code = dictionary_code;
    }

    public String getDictionary_name() {
        return dictionary_name;
    }

    public void setDictionary_name(String dictionary_name) {
        this.dictionary_name = dictionary_name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

	public String getDictionary_group() {
		return dictionary_group;
	}

	public void setDictionary_group(String dictionary_group) {
		this.dictionary_group = dictionary_group;
	}

	public String getDictionary_codes() {
		return dictionary_codes;
	}

	public void setDictionary_codes(String dictionary_codes) {
		this.dictionary_codes = dictionary_codes;
	}

	public String getCompanytype_codes() {
		return companytype_codes;
	}

	public void setCompanytype_codes(String companytype_codes) {
		this.companytype_codes = companytype_codes;
	}

	public String getCompanytype_names() {
		return companytype_names;
	}

	public void setCompanytype_names(String companytype_names) {
		this.companytype_names = companytype_names;
	}

	public String getDictionary_logo_url() {
		return dictionary_logo_url;
	}

	public void setDictionary_logo_url(String dictionary_logo_url) {
		this.dictionary_logo_url = dictionary_logo_url;
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

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
}