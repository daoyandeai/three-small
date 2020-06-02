package com.rs.po;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * 用户实体类
 * @author sven
 *
 */
public class User extends BasePo<User> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String user_code;				//用户系统编码
	private String user_loginname;			//用户登录账号
	private String user_loginpass;			//用户登录密码
	private String user_type;				//用户类型(超级管理人员、举办者、乡厨、平台管理员、协管员、农家乐、酒店、食堂、食品经营者)
	private String user_level;              //管理员级别（1 省，2 市，3 县，4 乡，5 村/协管员）
	private String user_name;				//用户姓名
	private String user_enname_short;		//用户姓名拼音首字母简写
	private String user_idcard;				//身份证号
	private String user_idcard_logo_front;	//身份证附件正面
	private String user_idcard_logo_back;	//身份证附件背面
	private String user_health_logo;		//健康证
	private String user_health_datedue;		//健康证到期时间
	private int    user_health_count;		//健康证过期数量
	private String user_registcard_logo;	//登记卡
	private String user_business_logo_url;	//营业执照
	private String user_food_logo_url;		//卫生许可证
	private String user_sex;				//用户性别(男、女)
	private String user_nation;				//民族
	private String user_birthday;			//生日
	private String user_mobilephone;		//用户手机号
	private String user_province;			//用户省
	private String user_city;				//用户市
	private String user_area;				//用户区
	private String user_town;				//管理镇
	private String user_vill;				//管理村（社区）
	private String user_address;			//用户详细地址
	private String user_logo_url;			//用户头像
	private String user_train_logo;			//培训合格证
	private String company_name;			//企业名称
	private Integer user_state;				//用户状态(1：启用、2：禁用)
	private Integer user_audit_state;		//用户审核状态(1：待审、2：审核通过，3：审核不通过)
	private Integer user_registersource;	//用户注册方式(1：微信、2：PC)
	private Integer vip_state;				//1 非会员 2会员
	private String addtime;					//添加时间
	private String user_idcard_government;	//发证机关
	private String user_idcard_expirationtime;	//身份证过期时间
	private String business_forms;			//经营形态集合
	private String dictionary_codes;		//经营形态编码集合
	private String companytype_codes;		//食品监管分类编码集合
	private String companytag_codes;		//备案类型编码集合
	private String company_codes_manage;		//管理的三小企业编码集合
	private String company_code;			//
	private String openid;			//
	private User user;
	private List<User> userlist;
	/******************数据库辅助字段*************************/
	private int rember;							//是否记住密码
	private String role_code;					//角色系统编码
	private String user_choose_singlemultiple;
	private String endtime;						//结束时间
	private String user_types;					//类型集合
	private String messcode;					//验证码
	private String messtype;					//验证码类型（验证码模板名称）
	private String idcardside;					//身份证 正反面
	private Integer zone_num;					//行政级别
	private Integer approved;     				//已审核数
	private Integer all_chef;					//所有乡厨数
	private Integer notaudit;					//已审核厨师
	private Integer operate_type;					

	private String token;					//已审核厨师

	
	private Integer all_User;                   //所有厨师数量
	private Integer px_User;                    //参加培训的厨师数量
	private Integer hg_User;                    //参加培训合格的厨师数量
	private Integer user_number;                //总数
	private Integer train_state;                //培训状态（1：不通过、2：通过）
	private Integer train_exam_standard;        //考试状态（1：不通过、2：通过）
	
	private Integer city_num;					//市级管理员数
	private Integer area_num;					//区级
	private Integer town_num;					//镇级
	private Integer vill_num;					//乡级
	private Integer all_manager;				//所有管理员
	private Integer new_manager;				//新增管理员
	
	private String  thisyear;					//本年
	private String 	lastmonth;					//上月时间
	private String  thismonth;					//本月时间
	private String  thistime;					//本月时间
	private String  nextmonth;					//下月时间
	
	private Integer lastmonth_px_User;			//上月培训乡厨
	private	Integer lastmonth_hg_User;			//上月通过乡厨
	private Integer thismonth_px_User;			//本月培训乡厨
	private Integer thismonth_hg_User;			//本月通过乡厨
	private Integer usertypeinfo;				//用户类型辅助字段
	private String user_code_conduct;		 //举办者系统编码
	private String user_name_conduct;        //举办者姓名
	private String user_mobilephone_conduct; //举办者手机号
	
	private String user_code_mainchef;       //主厨用户系统编码
	private Integer user_level_eq;			//这个用户等级只能查询相等
	private Integer report_count;			//这个用户报备次数
	private Integer banquet_count;			//这个用户办宴次数
	
	
	private	String	 addr_info;			//
	private	String	 train_year;			//用户培训年度
	private Integer  first_quarter;		//用户第一季度考核情况(1:不合格   2：合格)
	private Integer  second_quarter;	//用户第二季度考核情况(1:不合格   2：合格)
	private Integer  third_quarter;		//用户第三季度考核情况(1:不合格   2：合格)
	private Integer  fourth_quarter;	//用户第四季度考核情况(1:不合格   2：合格)
	
	
	private String total_all_chef;			//总计乡厨数
	private String total_approved;			//总计审核数
	
	private Integer user_health_state; 		//健康证状态(1:正常，2:过期)
	private String sort_column;					//办宴排序字段
	private String sort_type;					//办宴排序类型
	private Integer flag;
	
	
	private String serachState;
	private String user_address_show;
	private Integer url_willout_day;//健康证即将过期（天数）
	private String  url_out_day;//健康证过期日期
	private String  manage_type;//管理人员type（1.群宴报备管理员；2.三小报备管理员；3.群宴三小均管报备管理员）
	private String  returninfo;
	private String  returncode;
	private String  search_time_bigen;
	private String  search_time_end;
	private String  department_names;
	/**
	 * 区域名称
	 */
	private String region_name;
	/**
	 * 镇组合,格式：'A镇','B镇'
	 */
	private String user_towns;
	/**
	 * 村组合,格式：'A村','B村'
	 */
	private String user_vills;
	
	private String user_codes;
	private String user_screens; //参数筛选
	private String menu_parentcode;
	
	private List<String> business_forms_list;		//经营形态集合
	private List<String> companytype_codes_list;	//食品监管分类编码集合
	private List<String> companytag_codes_list;		//备案类型编码集合
	private List<String> dictionary_codes_list;		//备案类型编码集合
	private List<Dictionary> dictionaryList;
	private String qyurl;
	private String user_region_code; //网格员村级code
	private String user_levels; //用户等级区域
	
	public String getManage_type() {
		return manage_type;
	}

	public void setManage_type(String manage_type) {
		this.manage_type = manage_type;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	private Integer del_state;
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getSort_type() {
		return sort_type;
	}
	public void setSort_type(String sort_type) {
		this.sort_type = sort_type;
	}
	public String getSort_column() {
		return sort_column;
	}
	public void setSort_column(String sort_column) {
		this.sort_column = sort_column;
	}
	public int getUser_health_count() {
		return user_health_count;
	}
	public void setUser_health_count(int user_health_count) {
		this.user_health_count = user_health_count;
	}
	public Integer getBanquet_count() {
		return banquet_count;
	}
	public void setBanquet_count(Integer banquet_count) {
		this.banquet_count = banquet_count;
	}
	public Integer getUser_health_state() {
		return user_health_state;
	}
	public void setUser_health_state(Integer user_health_state) {
		this.user_health_state = user_health_state;
	}
	public String getUser_health_datedue() {
		return user_health_datedue;
	}
	public void setUser_health_datedue(String user_health_datedue) {
		this.user_health_datedue = user_health_datedue;
	}
	private HashMap<String,Object> totalmap=new HashMap<>(); //存放总计数据
	
	public HashMap<String, Object> getTotalmap() {
		return totalmap;
	}
	public void addTotalmap(String totalName,Object totalData) {
		totalmap.put(totalName, totalData);
	}
	public String getTotal_all_chef() {
		return total_all_chef;
	}
	public void setTotal_all_chef(String total_all_chef) {
		this.total_all_chef = total_all_chef;
	}
	public String getTotal_approved() {
		return total_approved;
	}
	public void setTotal_approved(String total_approved) {
		this.total_approved = total_approved;
	}
	public Integer getReport_count() {
		return report_count;
	}
	public void setReport_count(Integer report_count) {
		this.report_count = report_count;
	}
	public Integer getUser_audit_state() {
		return user_audit_state;
	}
	public void setUser_audit_state(Integer user_audit_state) {
		this.user_audit_state = user_audit_state;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getUser_code() {
		return user_code;
	}
	public String getUser_business_logo_url() {
		return user_business_logo_url;
	}
	public void setUser_business_logo_url(String user_business_logo_url) {
		this.user_business_logo_url = user_business_logo_url;
	}
	public String getUser_food_logo_url() {
		return user_food_logo_url;
	}
	public void setUser_food_logo_url(String user_food_logo_url) {
		this.user_food_logo_url = user_food_logo_url;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public String getUser_loginname() {
		return user_loginname;
	}
	public void setUser_loginname(String user_loginname) {
		this.user_loginname = user_loginname;
	}
	public String getUser_loginpass() {
		return user_loginpass;
	}
	public void setUser_loginpass(String user_loginpass) {
		this.user_loginpass = user_loginpass;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_enname_short() {
		return user_enname_short;
	}
	public void setUser_enname_short(String user_enname_short) {
		this.user_enname_short = user_enname_short;
	}
	public String getUser_idcard() {
		return user_idcard;
	}
	public void setUser_idcard(String user_idcard) {
		this.user_idcard = user_idcard;
	}
	public String getUser_idcard_logo_front() {
		return user_idcard_logo_front;
	}
	public void setUser_idcard_logo_front(String user_idcard_logo_front) {
		this.user_idcard_logo_front = user_idcard_logo_front;
	}
	public String getUser_idcard_logo_back() {
		return user_idcard_logo_back;
	}
	public void setUser_idcard_logo_back(String user_idcard_logo_back) {
		this.user_idcard_logo_back = user_idcard_logo_back;
	}
	public String getUser_health_logo() {
		return user_health_logo;
	}
	public void setUser_health_logo(String user_health_logo) {
		this.user_health_logo = user_health_logo;
	}
	public String getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}
	public String getUser_nation() {
		return user_nation;
	}
	public void setUser_nation(String user_nation) {
		this.user_nation = user_nation;
	}
	public String getUser_birthday() {
		return user_birthday;
	}
	public void setUser_birthday(String user_birthday) {
		this.user_birthday = user_birthday;
	}
	public String getUser_mobilephone() {
		return user_mobilephone;
	}
	public void setUser_mobilephone(String user_mobilephone) {
		this.user_mobilephone = user_mobilephone;
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
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public String getUser_logo_url() {
		return user_logo_url;
	}
	public void setUser_logo_url(String user_logo_url) {
		this.user_logo_url = user_logo_url;
	}
	public Integer getUser_state() {
		return user_state;
	}
	public void setUser_state(Integer user_state) {
		this.user_state = user_state;
	}
	public Integer getUser_registersource() {
		return user_registersource;
	}
	public void setUser_registersource(Integer user_registersource) {
		this.user_registersource = user_registersource;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<User> getUserlist() {
		return userlist;
	}
	public void setUserlist(List<User> userlist) {
		this.userlist = userlist;
	}
	public int getRember() {
		return rember;
	}
	public void setRember(int rember) {
		this.rember = rember;
	}
	public String getRole_code() {
		return role_code;
	}
	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}
	public String getUser_choose_singlemultiple() {
		return user_choose_singlemultiple;
	}
	public void setUser_choose_singlemultiple(String user_choose_singlemultiple) {
		this.user_choose_singlemultiple = user_choose_singlemultiple;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getUser_registcard_logo() {
		return user_registcard_logo;
	}
	public void setUser_registcard_logo(String user_registcard_logo) {
		this.user_registcard_logo = user_registcard_logo;
	}
	public String getUser_types() {
		return user_types;
	}
	public void setUser_types(String user_types) {
		this.user_types = user_types;
	}
	public String getUser_idcard_government() {
		return user_idcard_government;
	}
	public void setUser_idcard_government(String user_idcard_government) {
		this.user_idcard_government = user_idcard_government;
	}
	public String getUser_idcard_expirationtime() {
		return user_idcard_expirationtime;
	}
	public void setUser_idcard_expirationtime(String user_idcard_expirationtime) {
		this.user_idcard_expirationtime = user_idcard_expirationtime;
	}
	public String getUser_level() {
		return user_level;
	}
	public void setUser_level(String user_level) {
		this.user_level = user_level;
	}
	public String getMesscode() {
		return messcode;
	}
	public void setMesscode(String messcode) {
		this.messcode = messcode;
	}
	public String getIdcardside() {
		return idcardside;
	}
	public void setIdcardside(String idcardside) {
		this.idcardside = idcardside;
	}
	public Integer getZone_num() {
		return zone_num;
	}
	public void setZone_num(Integer zone_num) {
		this.zone_num = zone_num;
	}
	public Integer getApproved() {
		return approved;
	}
	public void setApproved(Integer approved) {
		this.approved = approved;
	}
	public Integer getAll_chef() {
		return all_chef;
	}
	public void setAll_chef(Integer all_chef) {
		this.all_chef = all_chef;
	}
	public Integer getNotaudit() {
		return notaudit;
	}
	public void setNotaudit(Integer notaudit) {
		this.notaudit = notaudit;
	}
	public Integer getAll_User() {
		return all_User;
	}
	public void setAll_User(Integer all_User) {
		this.all_User = all_User;
	}
	public Integer getPx_User() {
		return px_User;
	}
	public void setPx_User(Integer px_User) {
		this.px_User = px_User;
	}
	public Integer getHg_User() {
		return hg_User;
	}
	public void setHg_User(Integer hg_User) {
		this.hg_User = hg_User;
	}
	public Integer getUser_number() {
		return user_number;
	}
	public void setUser_number(Integer user_number) {
		this.user_number = user_number;
	}
	public Integer getTrain_state() {
		return train_state;
	}
	public void setTrain_state(Integer train_state) {
		this.train_state = train_state;

	}
	public Integer getCity_num() {
		return city_num;
	}
	public void setCity_num(Integer city_num) {
		this.city_num = city_num;
	}
	public Integer getArea_num() {
		return area_num;
	}
	public void setArea_num(Integer area_num) {
		this.area_num = area_num;
	}
	public Integer getTown_num() {
		return town_num;
	}
	public void setTown_num(Integer town_num) {
		this.town_num = town_num;
	}
	public Integer getVill_num() {
		return vill_num;
	}
	public void setVill_num(Integer vill_num) {
		this.vill_num = vill_num;
	}
	public Integer getNew_manager() {
		return new_manager;
	}
	public void setNew_manager(Integer new_manager) {
		this.new_manager = new_manager;
	}
	public String getLastmonth() {
		return lastmonth;
	}
	public void setLastmonth(String lastmonth) {
		this.lastmonth = lastmonth;
	}
	public String getThismonth() {
		return thismonth;
	}
	public void setThismonth(String thismonth) {
		this.thismonth = thismonth;
	}
	public String getNextmonth() {
		return nextmonth;
	}
	public void setNextmonth(String nextmonth) {
		this.nextmonth = nextmonth;
	}
	public Integer getLastmonth_px_User() {
		return lastmonth_px_User;
	}
	public void setLastmonth_px_User(Integer lastmonth_px_User) {
		this.lastmonth_px_User = lastmonth_px_User;
	}
	public Integer getLastmonth_hg_User() {
		return lastmonth_hg_User;
	}
	public void setLastmonth_hg_User(Integer lastmonth_hg_User) {
		this.lastmonth_hg_User = lastmonth_hg_User;
	}
	public Integer getThismonth_px_User() {
		return thismonth_px_User;
	}
	public void setThismonth_px_User(Integer thismonth_px_User) {
		this.thismonth_px_User = thismonth_px_User;
	}
	public Integer getThismonth_hg_User() {
		return thismonth_hg_User;
	}
	public void setThismonth_hg_User(Integer thismonth_hg_User) {
		this.thismonth_hg_User = thismonth_hg_User;
	}
	public String getThistime() {
		return thistime;
	}
	public void setThistime(String thistime) {
		this.thistime = thistime;

	}
	public Integer getAll_manager() {
		return all_manager;
	}
	public void setAll_manager(Integer all_manager) {
		this.all_manager = all_manager;

	}
	public String getUser_code_conduct() {
		return user_code_conduct;
	}
	public void setUser_code_conduct(String user_code_conduct) {
		this.user_code_conduct = user_code_conduct;
	}
	public String getUser_name_conduct() {
		return user_name_conduct;
	}
	public void setUser_name_conduct(String user_name_conduct) {
		this.user_name_conduct = user_name_conduct;
	}
	public String getUser_mobilephone_conduct() {
		return user_mobilephone_conduct;
	}
	public void setUser_mobilephone_conduct(String user_mobilephone_conduct) {
		this.user_mobilephone_conduct = user_mobilephone_conduct;
	}
	public String getUser_code_mainchef() {
		return user_code_mainchef;
	}
	public void setUser_code_mainchef(String user_code_mainchef) {
		this.user_code_mainchef = user_code_mainchef;

	}
	public Integer getUsertypeinfo() {
		return usertypeinfo;
	}
	public void setUsertypeinfo(Integer usertypeinfo) {
		this.usertypeinfo = usertypeinfo;
	}
	public Integer getUser_level_eq() {
		return user_level_eq;
	}
	public void setUser_level_eq(Integer user_level_eq) {
		this.user_level_eq = user_level_eq;
	}
	public Integer getTrain_exam_standard() {
		return train_exam_standard;
	}
	public void setTrain_exam_standard(Integer train_exam_standard) {
		this.train_exam_standard = train_exam_standard;
	}
	public String getThisyear() {
		return thisyear;
	}
	public void setThisyear(String thisyear) {
		this.thisyear = thisyear;
	}
	public String getUser_train_logo() {
		return user_train_logo;
	}
	public void setUser_train_logo(String user_train_logo) {
		this.user_train_logo = user_train_logo;
	}
	public Integer getFirst_quarter() {
		return first_quarter;
	}
	public void setFirst_quarter(Integer first_quarter) {
		this.first_quarter = first_quarter;
	}
	public Integer getSecond_quarter() {
		return second_quarter;
	}
	public void setSecond_quarter(Integer second_quarter) {
		this.second_quarter = second_quarter;
	}
	public Integer getThird_quarter() {
		return third_quarter;
	}
	public void setThird_quarter(Integer third_quarter) {
		this.third_quarter = third_quarter;
	}
	public Integer getFourth_quarter() {
		return fourth_quarter;
	}
	public void setFourth_quarter(Integer fourth_quarter) {
		this.fourth_quarter = fourth_quarter;
	}
	public String getTrain_year() {
		return train_year;
	}
	public void setTrain_year(String train_year) {
		this.train_year = train_year;
	}
	public String getAddr_info() {
		return addr_info;
	}
	public void setAddr_info(String addr_info) {
		this.addr_info = addr_info;
	}
	public Integer getVip_state() {
		return vip_state;
	}
	public void setVip_state(Integer vip_state) {
		this.vip_state = vip_state;
	}
	public String getUser_address_show() {
		return user_address_show;
	}
	public void setUser_address_show(String user_address_show) {
		this.user_address_show = user_address_show;
	}
	public String getSerachState() {
		return serachState;
	}
	public void setSerachState(String serachState) {
		this.serachState = serachState;
	}
	public Integer getUrl_willout_day() {
		return url_willout_day;
	}
	public void setUrl_willout_day(Integer url_willout_day) {
		this.url_willout_day = url_willout_day;
	}
	public String getUrl_out_day() {
		return url_out_day;
	}
	public void setUrl_out_day(String url_out_day) {
		this.url_out_day = url_out_day;
	}
	public Integer getDel_state() {
		return del_state;
	}
	public void setDel_state(Integer del_state) {
		this.del_state = del_state;
	}

	public Integer getOperate_type() {
		return operate_type;
	}

	public void setOperate_type(Integer operate_type) {
		this.operate_type = operate_type;
	}

	public String getReturninfo() {
		return returninfo;
	}

	public void setReturninfo(String returninfo) {
		this.returninfo = returninfo;
	}

	public String getReturncode() {
		return returncode;
	}

	public void setReturncode(String returncode) {
		this.returncode = returncode;
	}

	public String getRegion_name() {
		return region_name;
	}

	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}

	public void setTotalmap(HashMap<String, Object> totalmap) {
		this.totalmap = totalmap;
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

	public String getDepartment_names() {
		return department_names;
	}

	public void setDepartment_names(String department_names) {
		this.department_names = department_names;
	}

	public String getUser_towns() {
		return user_towns;
	}

	public void setUser_towns(String user_towns) {
		this.user_towns = user_towns;
	}

	public String getUser_vills() {
		return user_vills;
	}

	public void setUser_vills(String user_vills) {
		this.user_vills = user_vills;
	}

	public String getUser_codes() {
		return user_codes;
	}

	public void setUser_codes(String user_codes) {
		this.user_codes = user_codes;
	}

	public String getUser_screens() {
		return user_screens;
	}

	public void setUser_screens(String user_screens) {
		this.user_screens = user_screens;
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

	public List<String> getBusiness_forms_list() {
		return business_forms_list;
	}

	public void setBusiness_forms_list(List<String> business_forms_list) {
		this.business_forms_list = business_forms_list;
	}

	public List<String> getCompanytype_codes_list() {
		return companytype_codes_list;
	}

	public void setCompanytype_codes_list(List<String> companytype_codes_list) {
		this.companytype_codes_list = companytype_codes_list;
	}

	public List<String> getCompanytag_codes_list() {
		return companytag_codes_list;
	}

	public void setCompanytag_codes_list(List<String> companytag_codes_list) {
		this.companytag_codes_list = companytag_codes_list;
	}

	public String getDictionary_codes() {
		return dictionary_codes;
	}

	public void setDictionary_codes(String dictionary_codes) {
		this.dictionary_codes = dictionary_codes;
	}

	public List<String> getDictionary_codes_list() {
		return dictionary_codes_list;
	}

	public void setDictionary_codes_list(List<String> dictionary_codes_list) {
		this.dictionary_codes_list = dictionary_codes_list;
	}

	public List<Dictionary> getDictionaryList() {
		return dictionaryList;
	}

	public void setDictionaryList(List<Dictionary> dictionaryList) {
		this.dictionaryList = dictionaryList;
	}

	public String getMenu_parentcode() {
		return menu_parentcode;
	}

	public void setMenu_parentcode(String menu_parentcode) {
		this.menu_parentcode = menu_parentcode;
	}

	public String getQyurl() {
		return qyurl;
	}

	public void setQyurl(String qyurl) {
		this.qyurl = qyurl;
	}

	public String getCompany_code() {
		return company_code;
	}

	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}

	public String getMesstype() {
		return messtype;
	}

	public void setMesstype(String messtype) {
		this.messtype = messtype;
	}

	public String getUser_region_code() {
		return user_region_code;
	}

	public void setUser_region_code(String user_region_code) {
		this.user_region_code = user_region_code;
	}

	public String getUser_levels() {
		return user_levels;
	}

	public void setUser_levels(String user_levels) {
		this.user_levels = user_levels;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getCompany_codes_manage() {
		return company_codes_manage;
	}

	public void setCompany_codes_manage(String company_codes_manage) {
		this.company_codes_manage = company_codes_manage;
	}
}
