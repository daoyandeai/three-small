package com.rs.po;

import com.rs.po.returnPo.AccessoryReturn;
import com.rs.po.returnPo.CompanyEmployReturn;
import com.rs.po.returnPo.CompanyReturn;

import java.util.List;

/**
 * 
 * @ClassName: CompanySnap
 * @Description: 三小企业档案注销、变更记录实体
 * @Author tangsh
 * @DateTime 2020年5月27日 上午11:14:43
 */
public class CompanySnap extends BasePo<CompanySnap>{
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	/**
     * 用戶编码
     */
	private String user_code;
    /**
     * 三小档案编码
     */
	private String company_code;
    /**
     * 档案巡查人员名称
     */
    private String patrol_user_name;
    /**
     * 档案主体类型编码
     */
    private String companytype_code;
    /**
     * 档案类型编码
     */
    private String companytag_code;
    /**
     * 操作状态（1：注销、2：驳回、3：通过）
     */
    private String examine;
    /**
     *  三小档案名称
     */
    private String company_name;
    /**
     * 统一社会信用代码
     */
    private String credit_code;
    /**
     * 备案时间
     */
    private String record_time;
    /**
     * 备案号
     */
    private String record_code;
    /**
     * 备案有效期
     */
    private String useful_life;
    /**
     * 备案失效期
     */
    private String unuseful_time;
    /**
     *   经营者名字
     */
    private String operator;
    /**
     * 经营者手机号
     */
    private String mobilephone;
    /**
     * 经营者身份证号
     */
    private String idcard;
    /**
     * 户籍地址
     */
    private String residence_address;
    /**
     * 通讯地址
     */
    private String contact_address;
    /**
     *  经营形态
     */
    private String business_form;
    /**
     * 从业人数
     */
    private Integer employ_num;
    /**
     * 营业状态(1：正常营业、暂停营业、停止营业)
     */
    private Integer business_state;
    /**
     * 主营项目
     */
    private String main_subject;
    /**
     * 兼营项目
     */
    private String sub_subject;
    
    /**
     * 详细经营信息编码
     */
    private String subjectdetail_codes;
    /**
     * 生产形式
     */
    private String produce_form;
    /**
     * 生产类别编码
     */
    private String producetype_codes;
    /**
     * 品种明细编码
     */
    private String category_names;
    /**
     * 食品销售
     */
    private String foodsell_names;
    /**
     * 食品制造
     */
    private String foodmake_names;
    /**
     * 场地信息（1：自有、2：租赁）
     */
    private Integer owner_type;
    /**
     * 房东名字
     */
    private String owner_name;
    /**
     * 房东电话
     */
    private String owner_phone;
    /**
     * 场地分布
     */
    private String places;
    /**
     * 经营场所面积（单位：㎡）
     */
    private String place_area;
    /**
     * 证照地址
     */
    private String place_business;
    /**
     * 经营地址
     */
    private String place_operate;
    /**
     * 摊贩类型（1：流动摊贩、2：固定摊贩）
     */
    private Integer stall_type;
    /**
     * 加工地址
     */
    private String process_place;
    /**
     * 经营位置
     */
    private String operate_location;
    /**
     * 经营开始时间
     */
    private String operate_begin;
    /**
     * 经营结束时间
     */
    private String operate_end;
    /**
     * 部门编码
     */
    private String department_code;
    /**
     * 区域代码
     */
    private String region_code;
    /**
     * 监管人员编码
     */
    private String user_code_manage;
    /**
     *  信息人员编码
     */
    private String user_code_info;
    /**
     * 驳回原因
     */
    private String unpass_cause;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String area;
    /**
     * 乡
     */
    private String town;
    /**
     * 村
     */
    private String vill;
    
    /**
     * 网格员电话
     */
    private String user_moblephone_manage;
    /**
     * 信息员电话
     */
    private String user_moblephone_info;
    /**
     * 工单状态【1.保存草稿、2.提交申请、3.已归档、4.已驳回】
     */
    private Integer filing_state;
    /**
     * 业务状态(1：首次、2：延续、3：注销、4：变更)
     */
    private Integer business_type;
    /**
     * 主体状态(1：有效、2：过期、3：注销、4：即将过期【提前一个月】)
     */
    private Integer state;
    /**
     * 工单状态更新时间
     */
    private String filing_state_time;
    /**
     * 经营范围 
     */
    private String business_range;
    /**
     * 发证日期
     */
    private String issue_time;
    /**
     * 食品经营许可证号
     */
    private String business_code;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 企业是否是黑名单状态（1.否、2.是）
     */
    private Integer isblacklist;
    
    private String isblacklist_remark;
    private String isblacklist_time;
    
    /**
     * 上次诚信经营等级
     */
    private float integrity_lvl_last;
    /**
     * 上次等级评定时间
     */
    private String mete_time_last;
    /**
     * 最新诚信经营等级
     */
    private float integrity_lvl_new;
    /**
     * 最新等级评定时间
     */
    private String mete_time_new;
    /**
     * 最近两次诚信评价变化趋势（1：一致、2：上升、3：下降）
     */
    private Integer integrity_lvl_change_trend;
    /**
     * 数据来源（1.PC端 2.微信端）
     */
    private Integer date_source;
    /**
     * 餐厨垃圾最近一次处理时间
     */
    private String cclj_time_last;
    /**
     * 餐厨垃圾处理记录数
     */
    private String cclj_count;
    /**
     * 溯源次数
     */
    private String food_source_count;
    /**
     * 最新一次溯源时间
     */
    private String food_source_time_last;
    /**
     * 留样次数
     */
    private String food_sample_count;
    /**
     * 最近一次留样时间
     */
    private String food_sample_time_last;
    /******************************业务处理辅助字段*********************************/
    
    /**
     * 工单状态
     */
    private String filing_state_str;
    
    private List<String> main_subject_list;
    private List<String> sub_subject_list;
    private List<String> places_list;
    private List<String> produce_form_list;
    private List<String> subjectdetail_codes_list;
    private List<String> category_names_list;
    private List<String> foodsell_names_list;
    private List<String> foodmake_names_list;
    private List<String> producetype_codes_list;
    private List<String> user_code_manage_list;
    private List<String> user_name_manage_list;
    private List<String> user_moblephone_manage_list;
    private List<String> user_code_info_list;
    private List<String> user_name_info_list;
    private List<String> user_moblephone_info_list;
    private List<String> business_range_list;
    private List<CompanyReturn> companyList;
    private List<CompanyEmployReturn> companyEmployReturnList;
    private List<AccessoryReturn> accessoryReturnList;
    private List<CompanyEmploy> companyEmployList;
    private List<Accessory> accessoryList;
    private List<Log> loglist;
    private String search_time_bigen;
    private String search_time_end;
    private String region_name;
    private String department_name;
    private String user_name_manage;
    private String user_name_info;
    private Integer is_manage_or_info;	//1.信息员 2.所管理员
    private String business_forms;			//经营形态集合
	private String companytype_codes;		//食品监管分类编码集合
	private String companytag_codes;		//备案类型编码集合
	
	private String s_num;
	private String returncode;
	private String address;
	private Integer mess_event;
	private String other_code; //关键时间的
	private Integer employ_state; //健康证过期状态
	private String monthstr;
	
	/**
	 * 餐厨垃圾处理查询结束时间
	 */
	private String cclj_time_last_end;
	
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<String> getProducetype_codes_list() {
        return producetype_codes_list;
    }

    public void setProducetype_codes_list(List<String> producetype_codes_list) {
        this.producetype_codes_list = producetype_codes_list;
    }

    public List<String> getCategory_names_list() {
        return category_names_list;
    }

    public void setCategory_names_list(List<String> category_names_list) {
        this.category_names_list = category_names_list;
    }

    public String getCategory_names() {
        return category_names;
    }

    public void setCategory_names(String category_names) {
        this.category_names = category_names;
    }

    public List<String> getProduce_form_list() {
        return produce_form_list;
    }

    public void setProduce_form_list(List<String> produce_form_list) {
        this.produce_form_list = produce_form_list;
    }

    public List<String> getFoodsell_names_list() {
        return foodsell_names_list;
    }

    public void setFoodsell_names_list(List<String> foodsell_names_list) {
        this.foodsell_names_list = foodsell_names_list;
    }

    public List<String> getFoodmake_names_list() {
        return foodmake_names_list;
    }

    public void setFoodmake_names_list(List<String> foodmake_names_list) {
        this.foodmake_names_list = foodmake_names_list;
    }

    public String getPatrol_user_name() {
        return patrol_user_name;
    }

    public void setPatrol_user_name(String patrol_user_name) {
        this.patrol_user_name = patrol_user_name;
    }
	public String getExamine() {
		return examine;
	}

	public void setExamine(String examine) {
		this.examine = examine;
	}

	public String getUnpass_cause() {
        return unpass_cause;
    }

    public void setUnpass_cause(String unpass_cause) {
        this.unpass_cause = unpass_cause;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getBusiness_type() {
        return business_type;
    }

    public void setBusiness_type(Integer business_type) {
        this.business_type = business_type;
    }

    public String getFiling_state_time() {
        return filing_state_time;
    }

    public void setFiling_state_time(String filing_state_time) {
        this.filing_state_time = filing_state_time;
    }

    public List<CompanyReturn> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<CompanyReturn> companyList) {
        this.companyList = companyList;
    }

    public String getCompanytype_code() {
        return companytype_code;
    }

    public void setCompanytype_code(String companytype_code) {
        this.companytype_code = companytype_code;
    }

    public List<String> getSubjectdetail_codes_list() {
        return subjectdetail_codes_list;
    }

    public void setSubjectdetail_codes_list(List<String> subjectdetail_codes_list) {
        this.subjectdetail_codes_list = subjectdetail_codes_list;
    }

    public List<String> getMain_subject_list() {
        return main_subject_list;
    }

    public void setMain_subject_list(List<String> main_subject_list) {
        this.main_subject_list = main_subject_list;
    }

    public List<String> getSub_subject_list() {
        return sub_subject_list;
    }

    public void setSub_subject_list(List<String> sub_subject_list) {
        this.sub_subject_list = sub_subject_list;
    }

    public List<String> getPlaces_list() {
        return places_list;
    }

    public void setPlaces_list(List<String> places_list) {
        this.places_list = places_list;
    }

    public List<CompanyEmployReturn> getCompanyEmployReturnList() {
        return companyEmployReturnList;
    }

    public void setCompanyEmployReturnList(List<CompanyEmployReturn> companyEmployReturnList) {
        this.companyEmployReturnList = companyEmployReturnList;
    }

    public List<AccessoryReturn> getAccessoryReturnList() {
        return accessoryReturnList;
    }

    public void setAccessoryReturnList(List<AccessoryReturn> accessoryReturnList) {
        this.accessoryReturnList = accessoryReturnList;
    }

    public List<CompanyEmploy> getCompanyEmployList() {
        return companyEmployList;
    }

    public void setCompanyEmployList(List<CompanyEmploy> companyEmployList) {
        this.companyEmployList = companyEmployList;
    }

    public List<Accessory> getAccessoryList() {
        return accessoryList;
    }

    public void setAccessoryList(List<Accessory> accessoryList) {
        this.accessoryList = accessoryList;
    }

    public String getUser_moblephone_manage() {
		return user_moblephone_manage;
	}

	public void setUser_moblephone_manage(String user_moblephone_manage) {
		this.user_moblephone_manage = user_moblephone_manage;
	}

	public String getUser_moblephone_info() {
		return user_moblephone_info;
	}

	public void setUser_moblephone_info(String user_moblephone_info) {
		this.user_moblephone_info = user_moblephone_info;
	}

	public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public String getCompanytag_code() {
        return companytag_code;
    }

    public void setCompanytag_code(String companytag_code) {
        this.companytag_code = companytag_code;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCredit_code() {
        return credit_code;
    }

    public void setCredit_code(String credit_code) {
        this.credit_code = credit_code;
    }

    public String getRecord_time() {
        return record_time;
    }

    public void setRecord_time(String record_time) {
        this.record_time = record_time;
    }

    public String getRecord_code() {
        return record_code;
    }

    public void setRecord_code(String record_code) {
        this.record_code = record_code;
    }

    public String getUseful_life() {
        return useful_life;
    }

    public void setUseful_life(String useful_life) {
        this.useful_life = useful_life;
    }

    public String getUnuseful_time() {
        return unuseful_time;
    }

    public void setUnuseful_time(String unuseful_time) {
        this.unuseful_time = unuseful_time;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getResidence_address() {
        return residence_address;
    }

    public void setResidence_address(String residence_address) {
        this.residence_address = residence_address;
    }

    public String getContact_address() {
        return contact_address;
    }

    public void setContact_address(String contact_address) {
        this.contact_address = contact_address;
    }

    public String getBusiness_form() {
        return business_form;
    }

    public void setBusiness_form(String business_form) {
        this.business_form = business_form;
    }

    public Integer getEmploy_num() {
        return employ_num;
    }

    public void setEmploy_num(Integer employ_num) {
        this.employ_num = employ_num;
    }

    public Integer getBusiness_state() {
        return business_state;
    }

    public void setBusiness_state(Integer business_state) {
        this.business_state = business_state;
    }

    public String getMain_subject() {
        return main_subject;
    }

    public void setMain_subject(String main_subject) {
        this.main_subject = main_subject;
    }

    public String getSub_subject() {
        return sub_subject;
    }

    public void setSub_subject(String sub_subject) {
        this.sub_subject = sub_subject;
    }

    public String getSubjectdetail_codes() {
        return subjectdetail_codes;
    }

    public void setSubjectdetail_codes(String subjectdetail_codes) {
        this.subjectdetail_codes = subjectdetail_codes;
    }

    public String getProduce_form() {
        return produce_form;
    }

    public void setProduce_form(String produce_form) {
        this.produce_form = produce_form;
    }

    public String getProducetype_codes() {
        return producetype_codes;
    }

    public void setProducetype_codes(String producetype_codes) {
        this.producetype_codes = producetype_codes;
    }


    public String getFoodsell_names() {
        return foodsell_names;
    }

    public void setFoodsell_names(String foodsell_names) {
        this.foodsell_names = foodsell_names;
    }

    public String getFoodmake_names() {
        return foodmake_names;
    }

    public void setFoodmake_names(String foodmake_names) {
        this.foodmake_names = foodmake_names;
    }

    public Integer getOwner_type() {
        return owner_type;
    }

    public void setOwner_type(Integer owner_type) {
        this.owner_type = owner_type;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getOwner_phone() {
        return owner_phone;
    }

    public void setOwner_phone(String owner_phone) {
        this.owner_phone = owner_phone;
    }

    public String getPlaces() {
        return places;
    }

    public void setPlaces(String places) {
        this.places = places;
    }

    public String getPlace_area() {
        return place_area;
    }

    public void setPlace_area(String place_area) {
        this.place_area = place_area;
    }

    public String getPlace_business() {
        return place_business;
    }

    public void setPlace_business(String place_business) {
        this.place_business = place_business;
    }

    public String getPlace_operate() {
        return place_operate;
    }

    public void setPlace_operate(String place_operate) {
        this.place_operate = place_operate;
    }

    public Integer getStall_type() {
        return stall_type;
    }

    public void setStall_type(Integer stall_type) {
        this.stall_type = stall_type;
    }

    public String getProcess_place() {
        return process_place;
    }

    public void setProcess_place(String process_place) {
        this.process_place = process_place;
    }

    public String getOperate_location() {
        return operate_location;
    }

    public void setOperate_location(String operate_location) {
        this.operate_location = operate_location;
    }

    public String getOperate_begin() {
        return operate_begin;
    }

    public void setOperate_begin(String operate_begin) {
        this.operate_begin = operate_begin;
    }

    public String getOperate_end() {
        return operate_end;
    }

    public void setOperate_end(String operate_end) {
        this.operate_end = operate_end;
    }

    public String getDepartment_code() {
        return department_code;
    }

    public void setDepartment_code(String department_code) {
        this.department_code = department_code;
    }

    public String getRegion_code() {
        return region_code;
    }

    public void setRegion_code(String region_code) {
        this.region_code = region_code;
    }

    public String getUser_code_manage() {
        return user_code_manage;
    }

    public void setUser_code_manage(String user_code_manage) {
        this.user_code_manage = user_code_manage;
    }

    public String getUser_code_info() {
        return user_code_info;
    }

    public void setUser_code_info(String user_code_info) {
        this.user_code_info = user_code_info;
    }

    public Integer getFiling_state() {
        return filing_state;
    }

    public void setFiling_state(Integer filing_state) {
        this.filing_state = filing_state;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

	public String getRegion_name() {
		return region_name;
	}

	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getUser_name_manage() {
		return user_name_manage;
	}

	public void setUser_name_manage(String user_name_manage) {
		this.user_name_manage = user_name_manage;
	}

	public String getUser_name_info() {
		return user_name_info;
	}

	public void setUser_name_info(String user_name_info) {
		this.user_name_info = user_name_info;
	}

	public Integer getIs_manage_or_info() {
		return is_manage_or_info;
	}

	public void setIs_manage_or_info(Integer is_manage_or_info) {
		this.is_manage_or_info = is_manage_or_info;
	}

	public String getFiling_state_str() {
		return filing_state_str;
	}

	public void setFiling_state_str(String filing_state_str) {
		this.filing_state_str = filing_state_str;
	}

	public String getBusiness_range() {
		return business_range;
	}

	public void setBusiness_range(String business_range) {
		this.business_range = business_range;
	}

	public String getIssue_time() {
		return issue_time;
	}

	public void setIssue_time(String issue_time) {
		this.issue_time = issue_time;
	}

	public String getBusiness_code() {
		return business_code;
	}

	public void setBusiness_code(String business_code) {
		this.business_code = business_code;
	}

	public List<String> getUser_code_manage_list() {
		return user_code_manage_list;
	}

	public void setUser_code_manage_list(List<String> user_code_manage_list) {
		this.user_code_manage_list = user_code_manage_list;
	}

	public List<String> getUser_code_info_list() {
		return user_code_info_list;
	}

	public void setUser_code_info_list(List<String> user_code_info_list) {
		this.user_code_info_list = user_code_info_list;
	}

	public List<String> getUser_moblephone_manage_list() {
		return user_moblephone_manage_list;
	}

	public void setUser_moblephone_manage_list(List<String> user_moblephone_manage_list) {
		this.user_moblephone_manage_list = user_moblephone_manage_list;
	}

	public List<String> getUser_moblephone_info_list() {
		return user_moblephone_info_list;
	}

	public void setUser_moblephone_info_list(List<String> user_moblephone_info_list) {
		this.user_moblephone_info_list = user_moblephone_info_list;
	}

	public List<String> getBusiness_range_list() {
		return business_range_list;
	}

	public void setBusiness_range_list(List<String> business_range_list) {
		this.business_range_list = business_range_list;
	}

	public List<String> getUser_name_manage_list() {
		return user_name_manage_list;
	}

	public void setUser_name_manage_list(List<String> user_name_manage_list) {
		this.user_name_manage_list = user_name_manage_list;
	}

	public List<String> getUser_name_info_list() {
		return user_name_info_list;
	}

	public void setUser_name_info_list(List<String> user_name_info_list) {
		this.user_name_info_list = user_name_info_list;
	}

	public String getBusiness_forms() {
		return business_forms;
	}

	public void setBusiness_forms(String business_forms) {
		this.business_forms = business_forms;
	}

	public String getCompanytype_codes() {
		return companytype_codes;
	}

	public void setCompanytype_codes(String companytype_codes) {
		this.companytype_codes = companytype_codes;
	}

	public String getCompanytag_codes() {
		return companytag_codes;
	}

	public void setCompanytag_codes(String companytag_codes) {
		this.companytag_codes = companytag_codes;
	}

	public String getS_num() {
		return s_num;
	}

	public void setS_num(String s_num) {
		this.s_num = s_num;
	}

	public String getReturncode() {
		return returncode;
	}

	public void setReturncode(String returncode) {
		this.returncode = returncode;
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

	public Integer getIsblacklist() {
		return isblacklist;
	}

	public void setIsblacklist(Integer isblacklist) {
		this.isblacklist = isblacklist;
	}

	public String getIsblacklist_remark() {
		return isblacklist_remark;
	}

	public void setIsblacklist_remark(String isblacklist_remark) {
		this.isblacklist_remark = isblacklist_remark;
	}

	public String getIsblacklist_time() {
		return isblacklist_time;
	}

	public void setIsblacklist_time(String isblacklist_time) {
		this.isblacklist_time = isblacklist_time;
	}
	public List<Log> getLoglist() {
		return loglist;
	}
	public void setLoglist(List<Log> loglist) {
		this.loglist = loglist;
	}
	public String getMete_time_last() {
		return mete_time_last;
	}
	public void setMete_time_last(String mete_time_last) {
		this.mete_time_last = mete_time_last;
	}
	public float getIntegrity_lvl_last() {
		return integrity_lvl_last;
	}
	public void setIntegrity_lvl_last(float integrity_lvl_last) {
		this.integrity_lvl_last = integrity_lvl_last;
	}
	public float getIntegrity_lvl_new() {
		return integrity_lvl_new;
	}
	public void setIntegrity_lvl_new(float integrity_lvl_new) {
		this.integrity_lvl_new = integrity_lvl_new;
	}

	public String getMete_time_new() {
		return mete_time_new;
	}

	public void setMete_time_new(String mete_time_new) {
		this.mete_time_new = mete_time_new;
	}

	public Integer getIntegrity_lvl_change_trend() {
		return integrity_lvl_change_trend;
	}

	public void setIntegrity_lvl_change_trend(Integer integrity_lvl_change_trend) {
		this.integrity_lvl_change_trend = integrity_lvl_change_trend;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getMess_event() {
		return mess_event;
	}

	public void setMess_event(Integer mess_event) {
		this.mess_event = mess_event;
	}

	public String getOther_code() {
		return other_code;
	}

	public void setOther_code(String other_code) {
		this.other_code = other_code;
	}

	public Integer getDate_source() {
		return date_source;
	}

	public void setDate_source(Integer date_source) {
		this.date_source = date_source;
	}

	public String getCclj_time_last() {
		return cclj_time_last;
	}

	public void setCclj_time_last(String cclj_time_last) {
		this.cclj_time_last = cclj_time_last;
	}

	public String getCclj_count() {
		return cclj_count;
	}

	public void setCclj_count(String cclj_count) {
		this.cclj_count = cclj_count;
	}

	public String getCclj_time_last_end() {
		return cclj_time_last_end;
	}

	public void setCclj_time_last_end(String cclj_time_last_end) {
		this.cclj_time_last_end = cclj_time_last_end;
	}

	public Integer getEmploy_state() {
		return employ_state;
	}

	public void setEmploy_state(Integer employ_state) {
		this.employ_state = employ_state;
	}

	public String getMonthstr() {
		return monthstr;
	}

	public void setMonthstr(String monthstr) {
		this.monthstr = monthstr;
	}

	public String getFood_source_count() {
		return food_source_count;
	}

	public void setFood_source_count(String food_source_count) {
		this.food_source_count = food_source_count;
	}

	public String getFood_source_time_last() {
		return food_source_time_last;
	}

	public void setFood_source_time_last(String food_source_time_last) {
		this.food_source_time_last = food_source_time_last;
	}

	public String getFood_sample_count() {
		return food_sample_count;
	}

	public void setFood_sample_count(String food_sample_count) {
		this.food_sample_count = food_sample_count;
	}

	public String getFood_sample_time_last() {
		return food_sample_time_last;
	}

	public void setFood_sample_time_last(String food_sample_time_last) {
		this.food_sample_time_last = food_sample_time_last;
	}
}