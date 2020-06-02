package com.rs.po;

import java.util.List;

/**
 * 
 * @ClassName: Patrol
 * @Description: 巡查实体类
 * @Author tangsh
 * @DateTime 2020年3月17日 上午11:28:11
 */
public class Patrol extends BasePo<Patrol> {
	
	private static final long serialVersionUID = 1L;
	/**
    *巡查编码
    * */
    private String patrol_code;
    /**
     *巡查人员编码
     * */
    private String patrol_user_code;
    /**
     *巡查人员名称
     * */
    private String patrol_user_name;
    /**
     *巡查结果
     * */
    private String patrol_result;
    /**
     *巡查时间
     * */
    private String patrol_time;
    /**
     *巡查图片
     * */
    private String patrol_imgs;
    /**
     *处理方式编码
     * */
    private String dispose_code;
    /**
     *处理方式名称
     * */
    private String dispose_name;
    
    private List<String> punish_name_list;
    /**
     *处罚方式编码
     * */
    private String punish_code;
    /**
     *工单号
     * */
    private String work_order;
    /**
     *处罚方式名称
     * */
    private String punish_name;
    /**
     *备注
     * */
    private String note;
    /**
     *三小档案编码
     * */
    private String company_code;
    /**
     *三小档案名称
     * */
    private String company_name;
    /**
     *派发人系统编码
     * */
    private String user_code;
    /**
     *派发人
     * */
    private String user_name;
    /**
     *备案证明（合格、不合格）
     * */
    private String record_proof;
    /**
     *生产的首批食品是否进行检验（合格，不合格）
     * */
    private String food_inspection;
    /**
     *每年对其生产的食品检验次数（合格，不合格）
     * */
    private String food_inspection_count;
    /**
     *进货查验、保存查验记录及相关凭证（合格，不合格）
     * */
    private String related_credentials;
    /**
     *生产经营（合格，不合格）
     * */
    private String production_operation;
    /**
     *禁止生产经营（合格，不合格）
     * */
    private String ban_production_operation;
    /**
     *废物处理（合格，不合格）
     * */
    private String waste_disposal;
    /**
     *小作坊应具备条件（合格，不合格）
     * */
    private String requirements;
    /**
     *禁用食品（合格，不合格）
     * */
    private String ban_food;
    /**
     *食品标签内容（合格，不合格）
     * */
    private String food_label_content;
    /**
     *风险评估（“危险”，“警告”，“正常”，“优秀”）
     * */
    private String risk_assessment;
    /**
     *添加时间
     * */
    private String add_time;
    
    /**
     * 巡查状态
     */
    private Integer patrol_state;
    
    /**
     * 巡查内容 json
     */
    private String config_content;
    
    private List<String> patrol_imgs_list;
    
    private String companytag_code;
    private String business_type;
    private String search_time_bigen;
    private String search_time_end;
    private String province;
    private String city;
    private String area;
    private String vill;
    private String town;
    private String address;
    private String business_form;
    private String department_code;
    private String department_name;
    private Integer examine;
    private String search_code;
    private Integer patrol_type;
    
    private String longitude;
    private String latitude;
    private String location;
    
    private Integer isblacklist;
    private String isblacklist_remark;
    private String timesearch;
    private String monthstr;
    
    
    

	public String getIsblacklist_remark() {
		return isblacklist_remark;
	}

	public void setIsblacklist_remark(String isblacklist_remark) {
		this.isblacklist_remark = isblacklist_remark;
	}

	public Integer getIsblacklist() {
		return isblacklist;
	}

	public void setIsblacklist(Integer isblacklist) {
		this.isblacklist = isblacklist;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<String> getPunish_name_list() {
        return punish_name_list;
    }

    public void setPunish_name_list(List<String> punish_name_list) {
        this.punish_name_list = punish_name_list;
    }

    public String getBan_production_operation() {
        return ban_production_operation;
    }

    public void setBan_production_operation(String ban_production_operation) {
        this.ban_production_operation = ban_production_operation;
    }

    public String getRecord_proof() {
        return record_proof;
    }

    public void setRecord_proof(String record_proof) {
        this.record_proof = record_proof;
    }

    public String getFood_inspection() {
        return food_inspection;
    }

    public void setFood_inspection(String food_inspection) {
        this.food_inspection = food_inspection;
    }

    public String getFood_inspection_count() {
        return food_inspection_count;
    }

    public void setFood_inspection_count(String food_inspection_count) {
        this.food_inspection_count = food_inspection_count;
    }

    public String getRelated_credentials() {
        return related_credentials;
    }

    public void setRelated_credentials(String related_credentials) {
        this.related_credentials = related_credentials;
    }

    public String getProduction_operation() {
        return production_operation;
    }

    public void setProduction_operation(String production_operation) {
        this.production_operation = production_operation;
    }

    public String getWaste_disposal() {
        return waste_disposal;
    }

    public void setWaste_disposal(String waste_disposal) {
        this.waste_disposal = waste_disposal;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getFood_label_content() {
        return food_label_content;
    }

    public void setFood_label_content(String food_label_content) {
        this.food_label_content = food_label_content;
    }

    public String getRisk_assessment() {
        return risk_assessment;
    }

    public void setRisk_assessment(String risk_assessment) {
        this.risk_assessment = risk_assessment;
    }

    public String getBan_food() {
        return ban_food;
    }

    public void setBan_food(String ban_food) {
        this.ban_food = ban_food;
    }

    public String getPatrol_code() {
        return patrol_code;
    }

    public List<String> getPatrol_imgs_list() {
        return patrol_imgs_list;
    }

    public void setPatrol_imgs_list(List<String> patrol_imgs_list) {
        this.patrol_imgs_list = patrol_imgs_list;
    }

    public void setPatrol_code(String patrol_code) {
        this.patrol_code = patrol_code;
    }

    public String getPatrol_user_name() {
        return patrol_user_name;
    }

    public void setPatrol_user_name(String patrol_user_name) {
        this.patrol_user_name = patrol_user_name;
    }

    public String getPatrol_result() {
        return patrol_result;
    }

    public void setPatrol_result(String patrol_result) {
        this.patrol_result = patrol_result;
    }

    public String getPatrol_time() {
        return patrol_time;
    }

    public void setPatrol_time(String patrol_time) {
        this.patrol_time = patrol_time;
    }

    public String getPatrol_imgs() {
        return patrol_imgs;
    }

    public void setPatrol_imgs(String patrol_imgs) {
        this.patrol_imgs = patrol_imgs;
    }

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    @Override
    public String getAdd_time() {
        return add_time;
    }

    @Override
    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

	public String getPatrol_user_code() {
		return patrol_user_code;
	}

	public void setPatrol_user_code(String patrol_user_code) {
		this.patrol_user_code = patrol_user_code;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getCompanytag_code() {
		return companytag_code;
	}

	public void setCompanytag_code(String companytag_code) {
		this.companytag_code = companytag_code;
	}

	public String getBusiness_type() {
		return business_type;
	}

	public void setBusiness_type(String business_type) {
		this.business_type = business_type;
	}

	public String getSearch_time_bigen() {
		return search_time_bigen;
	}

	public void setSearch_time_bigen(String search_time_bigen) {
		this.search_time_bigen = search_time_bigen;
	}

	public String getSearch_time_end() {
		return search_time_end;
	}

	public void setSearch_time_end(String search_time_end) {
		this.search_time_end = search_time_end;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getVill() {
		return vill;
	}

	public void setVill(String vill) {
		this.vill = vill;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBusiness_form() {
		return business_form;
	}

	public void setBusiness_form(String business_form) {
		this.business_form = business_form;
	}

	public String getDepartment_code() {
		return department_code;
	}

	public void setDepartment_code(String department_code) {
		this.department_code = department_code;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public Integer getExamine() {
		return examine;
	}

	public void setExamine(Integer examine) {
		this.examine = examine;
	}

	public String getWork_order() {
		return work_order;
	}

	public void setWork_order(String work_order) {
		this.work_order = work_order;
	}

	public String getSearch_code() {
		return search_code;
	}

	public void setSearch_code(String search_code) {
		this.search_code = search_code;
	}

	public Integer getPatrol_type() {
		return patrol_type;
	}

	public void setPatrol_type(Integer patrol_type) {
		this.patrol_type = patrol_type;
	}

	public Integer getPatrol_state() {
		return patrol_state;
	}

	public void setPatrol_state(Integer patrol_state) {
		this.patrol_state = patrol_state;
	}

	public String getTimesearch() {
		return timesearch;
	}

	public void setTimesearch(String timesearch) {
		this.timesearch = timesearch;
	}

	public String getMonthstr() {
		return monthstr;
	}

	public void setMonthstr(String monthstr) {
		this.monthstr = monthstr;
	}

	public String getConfig_content() {
		return config_content;
	}

	public void setConfig_content(String config_content) {
		this.config_content = config_content;
	}
}