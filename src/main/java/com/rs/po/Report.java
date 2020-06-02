package com.rs.po;

import java.util.List;

/**
 * 
 * @ClassName: Report
 * @Description: 群宴报备实体类
 * @Author tangsh
 * @DateTime 2020年4月13日 上午10:00:42
 */
public class Report extends BasePo<Report> {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 报备系统编码
	 */
	private String report_code;              
	/**
	 * 报备场所（1：流动场所、2：固定场所）
	 */
	private Integer report_type;             
	/**
	 * 报备状态（1：待审核、2：审核不通过、3：待检验、4：检验合格、5：检验不合格）
	 */
	private Integer report_state;            
	/**
	 * 报备方式（1：主家、2：乡厨、3：农家乐、4：乡村酒店）
	 */
	private Integer report_mode;             
	/**
	 * 报备完整（1：未完整、2：已完整）
	 */
	private Integer report_full;			 
	/**
	 * 举办者系统编码
	 */
	private String user_code_conduct;		 
	/**
	 * 举办者姓名
	 */
	private String user_name_conduct;        
	/**
	 * 举办者身份证号
	 */
	private String user_idcard_conduct;      
	/**
	 * 举办者手机号
	 */
	private String user_mobilephone_conduct; 
	/**
	 * 举办者省
	 */
	private String province_conduct;         
	/**
	 * 举办者市
	 */
	private String city_conduct;             
	/**
	 * 举办者区
	 */
	private String area_conduct;             
	/**
	 * 举办者镇
	 */
	private String town_conduct;             
	/**
	 * 举办者村（社区）
	 */
	private String vill_conduct;             
	/**
	 * 举办者详细地址
	 */
	private String address_conduct;          
	/**
	 * 办宴时间
	 */
	private String banquet_time;             
	/**
	 * 办宴天数
	 */
	private String banquet_day;              
	/**
	 * 过期时间=办宴时间+办宴天数-1
	 */
	private String banquet_expiretime;		 
	/**
	 * 办宴类型(1：红事、2：白事、3：生日、4：状元、5：乔迁、6：其他)
	 */
	private Integer banquet_type;            
	/**
	 * 就餐人数
	 */
	private String banquet_people;           
	/**
	 * 就餐桌数
	 */
	private String banquet_table;           
	/**
	 * 主厨用户系统编码
	 */
	private String user_code_mainchef;       
	/**
	 * 主厨用户姓名
	 */
	private String user_name_mainchef;       
	/**
	 * 主厨手机号
	 */
	private String user_mobilephone_mainchef;
	/**
	 * 审核人员系统编码
	 */
	private String user_code_qualified;       
	/**
	 * 审核人员姓名
	 */
	private String user_name_qualified;       
	/**
	 * 审核时间
	 */
	private String qualified_time;            
	/**
	 * 检查人员系统编码
	 */
	private String user_code_check;           
	/**
	 * 检查人员姓名
	 */
	private String user_name_check;           
	/**
	 * 检查时间
	 */
	private String check_time;                
	/**
	 * 检查时间开始
	 */
	private String check_time_start;                
	/**
	 * 检查时间结束
	 */
	private String check_time_end;                
	/**
	 * 添加用户系统编码
	 */
	private String user_code;                
	/**
	 * 添加用户姓名
	 */
	private String user_name;                
	/**
	 * 审核无效原因
	 */
	private String invalid_reason;			 
	/**
	 * 添加时间
	 */
	private String addtime; 
    
    
	/******************数据库辅助字段*************************/
	/**
	 * 多选还是单选
	 */
	private String report_choose_singlemultiple; 
	/**
	 * 多选数量
	 */
	private String multiple_count; 				
	/**
	 * 视频播放地址
	 */
	private String play_video_url; 				
	/**
	 * 是否检测（1：是，2：否）
	 */
	private Integer report_state_n;              
	/**
	 * 办宴场数
	 */
	private Integer banquet_number;              
	/**
	 * 办宴检查次数
	 */
	private Integer banquet_check;               
	/**
	 * 加工场地卫生设施系统编码
	 */
	private String report_process_code;          
	/**
	 * 使用水源
	 */
	private String water_source;                 
	/**
	 * 餐具消毒方式
	 */
	private String tablewater_disinfect;         
	/**
	 * 留样设施(1：有、2：无)
	 */
	private Integer reservedsample_state;        
	/**
	 * 有无贮存或使用有毒有害物品(1：无、2：有)
	 */
	private Integer narcotics_state;             
	/**
	 * 餐具保洁柜数量
	 */
	private Integer cleancontainer_count;        
	/**
	 * 冰箱数量
	 */
	private Integer refrigerator_count;          
	/**
	 * 专用消毒柜数量
	 */
	private Integer disinfectioncabinet_count;   
	/**
	 * 食品留样柜数量
	 */
	private Integer reservedsample_count;        
	/**
	 * 垃圾桶数量
	 */
	private Integer garbage_count;               
	/**
	 * 防鼠、防蝇、防尘设施数量
	 */
	private Integer ratproof_count;              
	/**
	 * 专用洗菜、洗肉、洗鱼池数量
	 */
	private Integer washvegetable_count;         
	/**
	 * 报备办宴状态（1：正常、2：过期）
	 */
	private Integer report_timeout_state;   	 
	/**
	 * 待审核未过期
	 */
	private Integer audit_intime;				
	/**
	 * 待审核过期
	 */
	private Integer audi_outtime;				
	/**
	 * 审核未通过
	 */
	private Integer unapprove;					
	/**
	 * 待检验未过期
	 */
	private Integer inspection_intime;			
	/**
	 * 待检验未过期
	 */
	private Integer inspection_outtime;			
	/**
	 * 检验合格
	 */
	private Integer success_inspection;			
	/**
	 * 检验不合格
	 */
	private Integer failed_inspection;			
	/**
	 * 查询区域
	 */
	private Integer zone_num;					
	/**
	 * 待审核数
	 */
	private Integer notaudit;					
	/**
	 * 报备总数
	 */
	private Integer all_report;					
	/**
	 * 今日总办宴数
	 */
	private Integer today_allbanquet;			
	/**
	 * 新增报备数
	 */
	private Integer new_report;					
	/**
	 * 检查数
	 */
	private Integer check;						
	/**
	 * 审核率
	 */
	private Double audi;						
	/**
	 * 审核环比率
	 */
	private Double audisequential;				
	/**
	 * 环比状态审核
	 */
	private Integer sequentialaudi;				
	/**
	 * 环比状态检查
	 */
	private Integer sequentialcheck;			
	/**
	 * 检查率
	 */
	private Double checkrate;					
	/**
	 * 检查环比率
	 */
	private Double checkratecheckrate;			
	/**
	 * 本月审核率
	 */
	private Double thisaudi;					
	/**
	 * 本月检查率
	 */
	private Double thischeckrate;				
	/**
	 * 今日乡厨报备数
	 */
	private Integer today_chef_report;			
	/**
	 * 今日农家乐报备数
	 */
	private Integer today_agritainment_report;	
	/**
	 * 今日乡村酒店报备数
	 */
	private Integer today_hotel_report;			
	/**
	 * 今日红事报备数
	 */
	private Integer today_red_report;			
	/**
	 * 今日白事报备数
	 */
	private Integer today_white_report;			
	/**
	 * 今日生日报备数
	 */
	private Integer today_birthday_report;		
	/**
	 * 今日状元报备数
	 */
	private Integer today_top_report;			
	/**
	 * 今日乔迁报备数
	 */
	private Integer today_move_report;			
	/**
	 * 今日其他报备数
	 */
	private Integer today_other_report;			
	/**
	 * 今日办宴数
	 */
	private Integer today_banquet;				
	/**
	 * 今日检查数
	 */
	private Integer today_check;				
	/**
	 * 累计就餐人数
	 */
	private String total_banquet_people;		
	/**
	 * 今日就餐人数
	 */
	private Integer today_banquet_people;		
	/**
	 * 累计总办宴数
	 */
	private String total_banquet_number;		
	/**
	 * 累计办宴桌数
	 */
	private String total_banquet_table;			
	/**
	 * 累计检查数
	 */
	private String total_check;					
	/**
	 * 今日审核
	 */
	private Integer today_audi;					
	/**
	 * 办宴开始时间区间
	 */
	private String 	startbanquet_time;			
	/**
	 * 办宴结束时间区间
	 */
	private String 	endbanquet_time;			
	/**
	 * 上月时间
	 */
	private String 	lastmonth;					
	/**
	 * 本月时间
	 */
	private String  thismonth;					
	/**
	 * 下月时间
	 */
	private String  nextmonth;					
	/**
	 * 时间查询条件
	 */
	private String time_condition;				
	/**
	 * 上月报备数
	 */
	private Integer lastmonth_report;			
	/**
	 * 上月审核数
	 */
	private	Integer lastmonth_audi;				
	/**
	 * 上月办宴数
	 */
	private Integer lastmonth_banquet;			
	/**
	 * 上月检查数
	 */
	private Integer lastmonth_check;			
	/**
	 * 本月报备数
	 */
	private Integer thismonth_report;			
	/**
	 * 本月审核数
	 */
	private	Integer thismonth_audi;				
	/**
	 * 本月办宴数
	 */
	private Integer thismonth_banquet;			
	/**
	 * 本月检查数
	 */
	private Integer	thismonth_check;			
	/**
	 * 累计乡厨报备
	 */
	private Integer all_chefreport;				
	/**
	 * 累计农家乐报备
	 */
	private Integer all_agritainmentreport;		
	/**
	 * 累计酒店报备
	 */
	private Integer all_hotelreport;			
	/**
	 * 排序集合
	 */
	private List<Report> sortReportlist; 		
	/**
	 * 办宴点类型
	 */
	private String report_types;                
	/**
	 * 辅助字段
	 */
	private String table_bind;                  
	/**
	 * 查询
	 */
	private String searchtxt;                   
	/**
	 * 用户集合
	 */
	private List<User> userlist;				
	private List<ReportSubChef> reportsubcheflist;
	/**
	 * app端接收集合
	 */
	private List<ReportSubChef> report_sub_chef_list;
	/**
	 * 加工场地
	 */
	private ReportProcess report_process;
	
	private String endtime;
	/**
	 * 待审核数量
	 */
	private Integer wait_audit_count;			
	/**
	 * 待检查数量
	 */
	private Integer wait_check_count;			
	/**
	 * 已检查数量
	 */
	private Integer finish_audit_count;			
	/**
	 * 过期/失效数量
	 */
	private Integer expire_count;				
	/**
	 * 办宴类型中文
	 */
	private String banquet_type_str;			
	/**
	 * 留样设施中文
	 */
	private String reservedsample_str;			
	/**
	 * 有无贮存或使用有毒有害物品中文
	 */
	private String narcotics_str;				
	/**
	 * 主厨个人卫生是否合格中文
	 */
	private String check_mainchef_health_str;	
	/**
	 * 帮厨个人卫生是否合格中文
	 */
	private String check_subchef_health_str;	
	/**
	 * 办宴场所是否符合要求中文
	 */
	private String check_place_standard_str;	
	/**
	 * 加工场地是否符合要求中文
	 */
	private String check_process_standard_str;	
	/**
	 * 食物存储中文
	 */
	private String check_store_str;				
	/**
	 * 餐具消毒中文
	 */
	private String check_disinfection_str;		
	/**
	 * 饮用水
	 */
	private String check_water_str;				
	/**
	 * 食品留样
	 */
	private String check_reserved_sample_str;	
	/**
	 * 禁用食材
	 */
	private String check_forbidden_food_str;	
	/**
	 * 食品发票
	 */
	private String check_invoice_str;			
	/**
	 * 有毒有害物质
	 */
	private String check_poison_str;			
	/**
	 * 风险点
	 */
	private String check_risk;					
	/**
	 * 风险点整改情况
	 */
	private String check_risk_suggestion;		
	/**
	 * 健康证
	 */
	private String user_health_logo;			
	/**
	 * 办宴时间-开始
	 */
	private String banquet_time_start;			
	/**
	 * 办宴时间-结束
	 */
	private String banquet_time_end;			
	
	/*************合计区域******************/
	/**
	 * 合计待审核未过期
	 */
	private String total_audit_intime;				
	/**
	 * 合计待审核过期
	 */
	private String total_audi_outtime;				
	/**
	 * 审核未通过
	 */
	private String total_unapprove;					
	/**
	 * 待检验未过期
	 */
	private String total_inspection_intime;			
	/**
	 * 待检验过期
	 */
	private String total_inspection_outtime;		
	/**
	 * 检验合格
	 */
	private String total_success_inspection;		
	/**
	 * 检验不合格
	 */
	private String total_failed_inspection;			
	/**
	 * 区域总报备
	 */
	private String total_report_count;				
	/**
	 * 审核率
	 */
	private String total_audi_rate;					
	/**
	 * 检查通过+待检查
	 */
	private String total_success_intime_inspection;	
	/**
	 * 区域字段
	 */
	private String area_conducts;
	/**
	 * 区域一周办宴总数
	 */
	private String week_banquet_count_conduct;		
	/**
	 * 今日待检数
	 */
	private Integer	todaywaitcheckcount;			 
	/**
	 * 今日检查通过数
	 */
	private Integer	todaypasscheckcount;			
	/**
	 * 今日检查不合格数
	 */
	private Integer	todaynopasscheckcount;
	/**
	 * 辅助参数
	 */
	private String returninfo;
	/**
	 * 报备检查
	 */
	private ReportCheck report_check;
	
	public String getReport_code() {
		return report_code;
	}
	public void setReport_code(String report_code) {
		this.report_code = report_code;
	}
	public Integer getReport_type() {
		return report_type;
	}
	public void setReport_type(Integer report_type) {
		this.report_type = report_type;
	}
	public Integer getReport_state() {
		return report_state;
	}
	public void setReport_state(Integer report_state) {
		this.report_state = report_state;
	}
	public Integer getReport_mode() {
		return report_mode;
	}
	public void setReport_mode(Integer report_mode) {
		this.report_mode = report_mode;
	}
	public Integer getReport_full() {
		return report_full;
	}
	public void setReport_full(Integer report_full) {
		this.report_full = report_full;
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
	public String getUser_idcard_conduct() {
		return user_idcard_conduct;
	}
	public void setUser_idcard_conduct(String user_idcard_conduct) {
		this.user_idcard_conduct = user_idcard_conduct;
	}
	public String getUser_mobilephone_conduct() {
		return user_mobilephone_conduct;
	}
	public void setUser_mobilephone_conduct(String user_mobilephone_conduct) {
		this.user_mobilephone_conduct = user_mobilephone_conduct;
	}
	public String getProvince_conduct() {
		return province_conduct;
	}
	public void setProvince_conduct(String province_conduct) {
		this.province_conduct = province_conduct;
	}
	public String getCity_conduct() {
		return city_conduct;
	}
	public void setCity_conduct(String city_conduct) {
		this.city_conduct = city_conduct;
	}
	public String getArea_conduct() {
		return area_conduct;
	}
	public void setArea_conduct(String area_conduct) {
		this.area_conduct = area_conduct;
	}
	public String getTown_conduct() {
		return town_conduct;
	}
	public void setTown_conduct(String town_conduct) {
		this.town_conduct = town_conduct;
	}
	public String getVill_conduct() {
		return vill_conduct;
	}
	public void setVill_conduct(String vill_conduct) {
		this.vill_conduct = vill_conduct;
	}
	public String getAddress_conduct() {
		return address_conduct;
	}
	public void setAddress_conduct(String address_conduct) {
		this.address_conduct = address_conduct;
	}
	public String getBanquet_time() {
		return banquet_time;
	}
	public void setBanquet_time(String banquet_time) {
		this.banquet_time = banquet_time;
	}
	public String getBanquet_day() {
		return banquet_day;
	}
	public void setBanquet_day(String banquet_day) {
		this.banquet_day = banquet_day;
	}
	public String getBanquet_expiretime() {
		return banquet_expiretime;
	}
	public void setBanquet_expiretime(String banquet_expiretime) {
		this.banquet_expiretime = banquet_expiretime;
	}
	public Integer getBanquet_type() {
		return banquet_type;
	}
	public void setBanquet_type(Integer banquet_type) {
		this.banquet_type = banquet_type;
	}
	public String getBanquet_people() {
		return banquet_people;
	}
	public void setBanquet_people(String banquet_people) {
		this.banquet_people = banquet_people;
	}
	public String getBanquet_table() {
		return banquet_table;
	}
	public void setBanquet_table(String banquet_table) {
		this.banquet_table = banquet_table;
	}
	public String getUser_code_mainchef() {
		return user_code_mainchef;
	}
	public void setUser_code_mainchef(String user_code_mainchef) {
		this.user_code_mainchef = user_code_mainchef;
	}
	public String getUser_name_mainchef() {
		return user_name_mainchef;
	}
	public void setUser_name_mainchef(String user_name_mainchef) {
		this.user_name_mainchef = user_name_mainchef;
	}
	public String getUser_mobilephone_mainchef() {
		return user_mobilephone_mainchef;
	}
	public void setUser_mobilephone_mainchef(String user_mobilephone_mainchef) {
		this.user_mobilephone_mainchef = user_mobilephone_mainchef;
	}
	public String getUser_code_qualified() {
		return user_code_qualified;
	}
	public void setUser_code_qualified(String user_code_qualified) {
		this.user_code_qualified = user_code_qualified;
	}
	public String getUser_name_qualified() {
		return user_name_qualified;
	}
	public void setUser_name_qualified(String user_name_qualified) {
		this.user_name_qualified = user_name_qualified;
	}
	public String getQualified_time() {
		return qualified_time;
	}
	public void setQualified_time(String qualified_time) {
		this.qualified_time = qualified_time;
	}
	public String getUser_code_check() {
		return user_code_check;
	}
	public void setUser_code_check(String user_code_check) {
		this.user_code_check = user_code_check;
	}
	public String getUser_name_check() {
		return user_name_check;
	}
	public void setUser_name_check(String user_name_check) {
		this.user_name_check = user_name_check;
	}
	public String getCheck_time() {
		return check_time;
	}
	public void setCheck_time(String check_time) {
		this.check_time = check_time;
	}
	public String getCheck_time_start() {
		return check_time_start;
	}
	public void setCheck_time_start(String check_time_start) {
		this.check_time_start = check_time_start;
	}
	public String getCheck_time_end() {
		return check_time_end;
	}
	public void setCheck_time_end(String check_time_end) {
		this.check_time_end = check_time_end;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getInvalid_reason() {
		return invalid_reason;
	}
	public void setInvalid_reason(String invalid_reason) {
		this.invalid_reason = invalid_reason;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getReport_choose_singlemultiple() {
		return report_choose_singlemultiple;
	}
	public void setReport_choose_singlemultiple(String report_choose_singlemultiple) {
		this.report_choose_singlemultiple = report_choose_singlemultiple;
	}
	public String getMultiple_count() {
		return multiple_count;
	}
	public void setMultiple_count(String multiple_count) {
		this.multiple_count = multiple_count;
	}
	public String getPlay_video_url() {
		return play_video_url;
	}
	public void setPlay_video_url(String play_video_url) {
		this.play_video_url = play_video_url;
	}
	public Integer getReport_state_n() {
		return report_state_n;
	}
	public void setReport_state_n(Integer report_state_n) {
		this.report_state_n = report_state_n;
	}
	public Integer getBanquet_number() {
		return banquet_number;
	}
	public void setBanquet_number(Integer banquet_number) {
		this.banquet_number = banquet_number;
	}
	public Integer getBanquet_check() {
		return banquet_check;
	}
	public void setBanquet_check(Integer banquet_check) {
		this.banquet_check = banquet_check;
	}
	public String getReport_process_code() {
		return report_process_code;
	}
	public void setReport_process_code(String report_process_code) {
		this.report_process_code = report_process_code;
	}
	public String getWater_source() {
		return water_source;
	}
	public void setWater_source(String water_source) {
		this.water_source = water_source;
	}
	public String getTablewater_disinfect() {
		return tablewater_disinfect;
	}
	public void setTablewater_disinfect(String tablewater_disinfect) {
		this.tablewater_disinfect = tablewater_disinfect;
	}
	public Integer getReservedsample_state() {
		return reservedsample_state;
	}
	public void setReservedsample_state(Integer reservedsample_state) {
		this.reservedsample_state = reservedsample_state;
	}
	public Integer getNarcotics_state() {
		return narcotics_state;
	}
	public void setNarcotics_state(Integer narcotics_state) {
		this.narcotics_state = narcotics_state;
	}
	public Integer getCleancontainer_count() {
		return cleancontainer_count;
	}
	public void setCleancontainer_count(Integer cleancontainer_count) {
		this.cleancontainer_count = cleancontainer_count;
	}
	public Integer getRefrigerator_count() {
		return refrigerator_count;
	}
	public void setRefrigerator_count(Integer refrigerator_count) {
		this.refrigerator_count = refrigerator_count;
	}
	public Integer getDisinfectioncabinet_count() {
		return disinfectioncabinet_count;
	}
	public void setDisinfectioncabinet_count(Integer disinfectioncabinet_count) {
		this.disinfectioncabinet_count = disinfectioncabinet_count;
	}
	public Integer getReservedsample_count() {
		return reservedsample_count;
	}
	public void setReservedsample_count(Integer reservedsample_count) {
		this.reservedsample_count = reservedsample_count;
	}
	public Integer getGarbage_count() {
		return garbage_count;
	}
	public void setGarbage_count(Integer garbage_count) {
		this.garbage_count = garbage_count;
	}
	public Integer getRatproof_count() {
		return ratproof_count;
	}
	public void setRatproof_count(Integer ratproof_count) {
		this.ratproof_count = ratproof_count;
	}
	public Integer getWashvegetable_count() {
		return washvegetable_count;
	}
	public void setWashvegetable_count(Integer washvegetable_count) {
		this.washvegetable_count = washvegetable_count;
	}
	public Integer getReport_timeout_state() {
		return report_timeout_state;
	}
	public void setReport_timeout_state(Integer report_timeout_state) {
		this.report_timeout_state = report_timeout_state;
	}
	public Integer getAudit_intime() {
		return audit_intime;
	}
	public void setAudit_intime(Integer audit_intime) {
		this.audit_intime = audit_intime;
	}
	public Integer getAudi_outtime() {
		return audi_outtime;
	}
	public void setAudi_outtime(Integer audi_outtime) {
		this.audi_outtime = audi_outtime;
	}
	public Integer getUnapprove() {
		return unapprove;
	}
	public void setUnapprove(Integer unapprove) {
		this.unapprove = unapprove;
	}
	public Integer getInspection_intime() {
		return inspection_intime;
	}
	public void setInspection_intime(Integer inspection_intime) {
		this.inspection_intime = inspection_intime;
	}
	public Integer getInspection_outtime() {
		return inspection_outtime;
	}
	public void setInspection_outtime(Integer inspection_outtime) {
		this.inspection_outtime = inspection_outtime;
	}
	public Integer getSuccess_inspection() {
		return success_inspection;
	}
	public void setSuccess_inspection(Integer success_inspection) {
		this.success_inspection = success_inspection;
	}
	public Integer getFailed_inspection() {
		return failed_inspection;
	}
	public void setFailed_inspection(Integer failed_inspection) {
		this.failed_inspection = failed_inspection;
	}
	public Integer getZone_num() {
		return zone_num;
	}
	public void setZone_num(Integer zone_num) {
		this.zone_num = zone_num;
	}
	public Integer getNotaudit() {
		return notaudit;
	}
	public void setNotaudit(Integer notaudit) {
		this.notaudit = notaudit;
	}
	public Integer getAll_report() {
		return all_report;
	}
	public void setAll_report(Integer all_report) {
		this.all_report = all_report;
	}
	public Integer getToday_allbanquet() {
		return today_allbanquet;
	}
	public void setToday_allbanquet(Integer today_allbanquet) {
		this.today_allbanquet = today_allbanquet;
	}
	public Integer getNew_report() {
		return new_report;
	}
	public void setNew_report(Integer new_report) {
		this.new_report = new_report;
	}
	public Integer getCheck() {
		return check;
	}
	public void setCheck(Integer check) {
		this.check = check;
	}
	public Double getAudi() {
		return audi;
	}
	public void setAudi(Double audi) {
		this.audi = audi;
	}
	public Double getAudisequential() {
		return audisequential;
	}
	public void setAudisequential(Double audisequential) {
		this.audisequential = audisequential;
	}
	public Integer getSequentialaudi() {
		return sequentialaudi;
	}
	public void setSequentialaudi(Integer sequentialaudi) {
		this.sequentialaudi = sequentialaudi;
	}
	public Integer getSequentialcheck() {
		return sequentialcheck;
	}
	public void setSequentialcheck(Integer sequentialcheck) {
		this.sequentialcheck = sequentialcheck;
	}
	public Double getCheckrate() {
		return checkrate;
	}
	public void setCheckrate(Double checkrate) {
		this.checkrate = checkrate;
	}
	public Double getCheckratecheckrate() {
		return checkratecheckrate;
	}
	public void setCheckratecheckrate(Double checkratecheckrate) {
		this.checkratecheckrate = checkratecheckrate;
	}
	public Double getThisaudi() {
		return thisaudi;
	}
	public void setThisaudi(Double thisaudi) {
		this.thisaudi = thisaudi;
	}
	public Double getThischeckrate() {
		return thischeckrate;
	}
	public void setThischeckrate(Double thischeckrate) {
		this.thischeckrate = thischeckrate;
	}
	public Integer getToday_chef_report() {
		return today_chef_report;
	}
	public void setToday_chef_report(Integer today_chef_report) {
		this.today_chef_report = today_chef_report;
	}
	public Integer getToday_agritainment_report() {
		return today_agritainment_report;
	}
	public void setToday_agritainment_report(Integer today_agritainment_report) {
		this.today_agritainment_report = today_agritainment_report;
	}
	public Integer getToday_hotel_report() {
		return today_hotel_report;
	}
	public void setToday_hotel_report(Integer today_hotel_report) {
		this.today_hotel_report = today_hotel_report;
	}
	public Integer getToday_red_report() {
		return today_red_report;
	}
	public void setToday_red_report(Integer today_red_report) {
		this.today_red_report = today_red_report;
	}
	public Integer getToday_white_report() {
		return today_white_report;
	}
	public void setToday_white_report(Integer today_white_report) {
		this.today_white_report = today_white_report;
	}
	public Integer getToday_birthday_report() {
		return today_birthday_report;
	}
	public void setToday_birthday_report(Integer today_birthday_report) {
		this.today_birthday_report = today_birthday_report;
	}
	public Integer getToday_top_report() {
		return today_top_report;
	}
	public void setToday_top_report(Integer today_top_report) {
		this.today_top_report = today_top_report;
	}
	public Integer getToday_move_report() {
		return today_move_report;
	}
	public void setToday_move_report(Integer today_move_report) {
		this.today_move_report = today_move_report;
	}
	public Integer getToday_other_report() {
		return today_other_report;
	}
	public void setToday_other_report(Integer today_other_report) {
		this.today_other_report = today_other_report;
	}
	public Integer getToday_banquet() {
		return today_banquet;
	}
	public void setToday_banquet(Integer today_banquet) {
		this.today_banquet = today_banquet;
	}
	public Integer getToday_check() {
		return today_check;
	}
	public void setToday_check(Integer today_check) {
		this.today_check = today_check;
	}
	public String getTotal_banquet_people() {
		return total_banquet_people;
	}
	public void setTotal_banquet_people(String total_banquet_people) {
		this.total_banquet_people = total_banquet_people;
	}
	public Integer getToday_banquet_people() {
		return today_banquet_people;
	}
	public void setToday_banquet_people(Integer today_banquet_people) {
		this.today_banquet_people = today_banquet_people;
	}
	public String getTotal_banquet_number() {
		return total_banquet_number;
	}
	public void setTotal_banquet_number(String total_banquet_number) {
		this.total_banquet_number = total_banquet_number;
	}
	public String getTotal_banquet_table() {
		return total_banquet_table;
	}
	public void setTotal_banquet_table(String total_banquet_table) {
		this.total_banquet_table = total_banquet_table;
	}
	public String getTotal_check() {
		return total_check;
	}
	public void setTotal_check(String total_check) {
		this.total_check = total_check;
	}
	public Integer getToday_audi() {
		return today_audi;
	}
	public void setToday_audi(Integer today_audi) {
		this.today_audi = today_audi;
	}
	public String getStartbanquet_time() {
		return startbanquet_time;
	}
	public void setStartbanquet_time(String startbanquet_time) {
		this.startbanquet_time = startbanquet_time;
	}
	public String getEndbanquet_time() {
		return endbanquet_time;
	}
	public void setEndbanquet_time(String endbanquet_time) {
		this.endbanquet_time = endbanquet_time;
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
	public String getTime_condition() {
		return time_condition;
	}
	public void setTime_condition(String time_condition) {
		this.time_condition = time_condition;
	}
	public Integer getLastmonth_report() {
		return lastmonth_report;
	}
	public void setLastmonth_report(Integer lastmonth_report) {
		this.lastmonth_report = lastmonth_report;
	}
	public Integer getLastmonth_audi() {
		return lastmonth_audi;
	}
	public void setLastmonth_audi(Integer lastmonth_audi) {
		this.lastmonth_audi = lastmonth_audi;
	}
	public Integer getLastmonth_banquet() {
		return lastmonth_banquet;
	}
	public void setLastmonth_banquet(Integer lastmonth_banquet) {
		this.lastmonth_banquet = lastmonth_banquet;
	}
	public Integer getLastmonth_check() {
		return lastmonth_check;
	}
	public void setLastmonth_check(Integer lastmonth_check) {
		this.lastmonth_check = lastmonth_check;
	}
	public Integer getThismonth_report() {
		return thismonth_report;
	}
	public void setThismonth_report(Integer thismonth_report) {
		this.thismonth_report = thismonth_report;
	}
	public Integer getThismonth_audi() {
		return thismonth_audi;
	}
	public void setThismonth_audi(Integer thismonth_audi) {
		this.thismonth_audi = thismonth_audi;
	}
	public Integer getThismonth_banquet() {
		return thismonth_banquet;
	}
	public void setThismonth_banquet(Integer thismonth_banquet) {
		this.thismonth_banquet = thismonth_banquet;
	}
	public Integer getThismonth_check() {
		return thismonth_check;
	}
	public void setThismonth_check(Integer thismonth_check) {
		this.thismonth_check = thismonth_check;
	}
	public Integer getAll_chefreport() {
		return all_chefreport;
	}
	public void setAll_chefreport(Integer all_chefreport) {
		this.all_chefreport = all_chefreport;
	}
	public Integer getAll_agritainmentreport() {
		return all_agritainmentreport;
	}
	public void setAll_agritainmentreport(Integer all_agritainmentreport) {
		this.all_agritainmentreport = all_agritainmentreport;
	}
	public Integer getAll_hotelreport() {
		return all_hotelreport;
	}
	public void setAll_hotelreport(Integer all_hotelreport) {
		this.all_hotelreport = all_hotelreport;
	}
	public List<Report> getSortReportlist() {
		return sortReportlist;
	}
	public void setSortReportlist(List<Report> sortReportlist) {
		this.sortReportlist = sortReportlist;
	}
	public String getReport_types() {
		return report_types;
	}
	public void setReport_types(String report_types) {
		this.report_types = report_types;
	}
	public String getTable_bind() {
		return table_bind;
	}
	public void setTable_bind(String table_bind) {
		this.table_bind = table_bind;
	}
	public String getSearchtxt() {
		return searchtxt;
	}
	public void setSearchtxt(String searchtxt) {
		this.searchtxt = searchtxt;
	}
	public List<User> getUserlist() {
		return userlist;
	}
	public void setUserlist(List<User> userlist) {
		this.userlist = userlist;
	}
	public List<ReportSubChef> getReportsubcheflist() {
		return reportsubcheflist;
	}
	public void setReportsubcheflist(List<ReportSubChef> reportsubcheflist) {
		this.reportsubcheflist = reportsubcheflist;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public Integer getWait_audit_count() {
		return wait_audit_count;
	}
	public void setWait_audit_count(Integer wait_audit_count) {
		this.wait_audit_count = wait_audit_count;
	}
	public Integer getWait_check_count() {
		return wait_check_count;
	}
	public void setWait_check_count(Integer wait_check_count) {
		this.wait_check_count = wait_check_count;
	}
	public Integer getFinish_audit_count() {
		return finish_audit_count;
	}
	public void setFinish_audit_count(Integer finish_audit_count) {
		this.finish_audit_count = finish_audit_count;
	}
	public Integer getExpire_count() {
		return expire_count;
	}
	public void setExpire_count(Integer expire_count) {
		this.expire_count = expire_count;
	}
	public String getBanquet_type_str() {
		return banquet_type_str;
	}
	public void setBanquet_type_str(String banquet_type_str) {
		this.banquet_type_str = banquet_type_str;
	}
	public String getReservedsample_str() {
		return reservedsample_str;
	}
	public void setReservedsample_str(String reservedsample_str) {
		this.reservedsample_str = reservedsample_str;
	}
	public String getNarcotics_str() {
		return narcotics_str;
	}
	public void setNarcotics_str(String narcotics_str) {
		this.narcotics_str = narcotics_str;
	}
	public String getCheck_mainchef_health_str() {
		return check_mainchef_health_str;
	}
	public void setCheck_mainchef_health_str(String check_mainchef_health_str) {
		this.check_mainchef_health_str = check_mainchef_health_str;
	}
	public String getCheck_subchef_health_str() {
		return check_subchef_health_str;
	}
	public void setCheck_subchef_health_str(String check_subchef_health_str) {
		this.check_subchef_health_str = check_subchef_health_str;
	}
	public String getCheck_place_standard_str() {
		return check_place_standard_str;
	}
	public void setCheck_place_standard_str(String check_place_standard_str) {
		this.check_place_standard_str = check_place_standard_str;
	}
	public String getCheck_process_standard_str() {
		return check_process_standard_str;
	}
	public void setCheck_process_standard_str(String check_process_standard_str) {
		this.check_process_standard_str = check_process_standard_str;
	}
	public String getCheck_store_str() {
		return check_store_str;
	}
	public void setCheck_store_str(String check_store_str) {
		this.check_store_str = check_store_str;
	}
	public String getCheck_disinfection_str() {
		return check_disinfection_str;
	}
	public void setCheck_disinfection_str(String check_disinfection_str) {
		this.check_disinfection_str = check_disinfection_str;
	}
	public String getCheck_water_str() {
		return check_water_str;
	}
	public void setCheck_water_str(String check_water_str) {
		this.check_water_str = check_water_str;
	}
	public String getCheck_reserved_sample_str() {
		return check_reserved_sample_str;
	}
	public void setCheck_reserved_sample_str(String check_reserved_sample_str) {
		this.check_reserved_sample_str = check_reserved_sample_str;
	}
	public String getCheck_forbidden_food_str() {
		return check_forbidden_food_str;
	}
	public void setCheck_forbidden_food_str(String check_forbidden_food_str) {
		this.check_forbidden_food_str = check_forbidden_food_str;
	}
	public String getCheck_invoice_str() {
		return check_invoice_str;
	}
	public void setCheck_invoice_str(String check_invoice_str) {
		this.check_invoice_str = check_invoice_str;
	}
	public String getCheck_poison_str() {
		return check_poison_str;
	}
	public void setCheck_poison_str(String check_poison_str) {
		this.check_poison_str = check_poison_str;
	}
	public String getCheck_risk() {
		return check_risk;
	}
	public void setCheck_risk(String check_risk) {
		this.check_risk = check_risk;
	}
	public String getCheck_risk_suggestion() {
		return check_risk_suggestion;
	}
	public void setCheck_risk_suggestion(String check_risk_suggestion) {
		this.check_risk_suggestion = check_risk_suggestion;
	}
	public String getUser_health_logo() {
		return user_health_logo;
	}
	public void setUser_health_logo(String user_health_logo) {
		this.user_health_logo = user_health_logo;
	}
	public String getBanquet_time_start() {
		return banquet_time_start;
	}
	public void setBanquet_time_start(String banquet_time_start) {
		this.banquet_time_start = banquet_time_start;
	}
	public String getBanquet_time_end() {
		return banquet_time_end;
	}
	public void setBanquet_time_end(String banquet_time_end) {
		this.banquet_time_end = banquet_time_end;
	}
	public String getTotal_audit_intime() {
		return total_audit_intime;
	}
	public void setTotal_audit_intime(String total_audit_intime) {
		this.total_audit_intime = total_audit_intime;
	}
	public String getTotal_audi_outtime() {
		return total_audi_outtime;
	}
	public void setTotal_audi_outtime(String total_audi_outtime) {
		this.total_audi_outtime = total_audi_outtime;
	}
	public String getTotal_unapprove() {
		return total_unapprove;
	}
	public void setTotal_unapprove(String total_unapprove) {
		this.total_unapprove = total_unapprove;
	}
	public String getTotal_inspection_intime() {
		return total_inspection_intime;
	}
	public void setTotal_inspection_intime(String total_inspection_intime) {
		this.total_inspection_intime = total_inspection_intime;
	}
	public String getTotal_inspection_outtime() {
		return total_inspection_outtime;
	}
	public void setTotal_inspection_outtime(String total_inspection_outtime) {
		this.total_inspection_outtime = total_inspection_outtime;
	}
	public String getTotal_success_inspection() {
		return total_success_inspection;
	}
	public void setTotal_success_inspection(String total_success_inspection) {
		this.total_success_inspection = total_success_inspection;
	}
	public String getTotal_failed_inspection() {
		return total_failed_inspection;
	}
	public void setTotal_failed_inspection(String total_failed_inspection) {
		this.total_failed_inspection = total_failed_inspection;
	}
	public String getTotal_report_count() {
		return total_report_count;
	}
	public void setTotal_report_count(String total_report_count) {
		this.total_report_count = total_report_count;
	}
	public String getTotal_audi_rate() {
		return total_audi_rate;
	}
	public void setTotal_audi_rate(String total_audi_rate) {
		this.total_audi_rate = total_audi_rate;
	}
	public String getTotal_success_intime_inspection() {
		return total_success_intime_inspection;
	}
	public void setTotal_success_intime_inspection(String total_success_intime_inspection) {
		this.total_success_intime_inspection = total_success_intime_inspection;
	}
	public String getArea_conducts() {
		return area_conducts;
	}
	public void setArea_conducts(String area_conducts) {
		this.area_conducts = area_conducts;
	}
	public String getWeek_banquet_count_conduct() {
		return week_banquet_count_conduct;
	}
	public void setWeek_banquet_count_conduct(String week_banquet_count_conduct) {
		this.week_banquet_count_conduct = week_banquet_count_conduct;
	}
	public Integer getTodaywaitcheckcount() {
		return todaywaitcheckcount;
	}
	public void setTodaywaitcheckcount(Integer todaywaitcheckcount) {
		this.todaywaitcheckcount = todaywaitcheckcount;
	}
	public Integer getTodaypasscheckcount() {
		return todaypasscheckcount;
	}
	public void setTodaypasscheckcount(Integer todaypasscheckcount) {
		this.todaypasscheckcount = todaypasscheckcount;
	}
	public Integer getTodaynopasscheckcount() {
		return todaynopasscheckcount;
	}
	public void setTodaynopasscheckcount(Integer todaynopasscheckcount) {
		this.todaynopasscheckcount = todaynopasscheckcount;
	}
	public String getReturninfo() {
		return returninfo;
	}
	public void setReturninfo(String returninfo) {
		this.returninfo = returninfo;
	}
	public List<ReportSubChef> getReport_sub_chef_list() {
		return report_sub_chef_list;
	}
	public void setReport_sub_chef_list(List<ReportSubChef> report_sub_chef_list) {
		this.report_sub_chef_list = report_sub_chef_list;
	}
	public ReportCheck getReport_check() {
		return report_check;
	}
	public void setReport_check(ReportCheck report_check) {
		this.report_check = report_check;
	}
	public ReportProcess getReport_process() {
		return report_process;
	}
	public void setReport_process(ReportProcess report_process) {
		this.report_process = report_process;
	}	
}