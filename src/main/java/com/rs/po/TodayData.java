package com.rs.po;

/**
 * 
 * @ClassName: TodayData
 * @Description: 报备信息实体类
 * @Author tangsh
 * @DateTime 2020年5月25日 下午3:03:37
 */
public class TodayData extends BasePo<TodayData>{

	private static final long serialVersionUID = 1L;
	private Integer addReportCount;
	private String province_conduct;
	private String city_conduct;
	private String area_conduct;
	private String town_conduct;
	private String vill_conduct;
	private String address_conduct;
	private String user_name_conduct;
	private String report_state;
	private String user_level;
	private Integer mp2outcount;
	private Integer mq4outcount;
	private Integer mq5outcount;
	private Integer mq9outcount;
	private Integer tdsoutcount;
	private Integer phoutcount;
	private Integer deviceoutcount;
	private Integer devicecount;
	private Integer todaybanquetcount;
	private Integer todaypasscheckcount;
	private Integer todaynopasscheckcount;
	private Integer todaywaitcheckcount;
	private String check_risk;
	private String check_risk_suggestion;
	private String user_name;
	private String user_mobilephone;
	private Integer missCardChefCount;
	private Integer allChefCount;
	private Integer datechefcount;// 有健康证的人数
	private Integer dateoutchefcount;// 健康证过期人数
	private Integer datewilloutchefcount;// 健康证即将过期的人数
	private Integer hg_state;// 培训 合格状态 (1合格 2不合格)
	private String addtime;
	private Integer banquet_type;// 办宴类型(1：红事、2：白事、3：生日、4：状元、5：乔迁、6：其他)
	private String  banquet_people;//就餐人数
	private String device_name;// 设备名称
	private String device_user_name;//设备用户名
	private Integer device_state;//设备状态
	private String device_out_state;//设备异常状态
	private String device_number;
	private String device_code;
	private String user_name_check;//检查人
	private String pxYear;//培训年度
	
	private Integer week_add_report_count;
	private Integer all_report_count;
	
	private Integer week_add_chef_count;
	private Integer all_chef_count;
	
	private Integer week_add_gl_count;
	private Integer all_gl_count;
	
	private String user_name_chef;//乡厨名称
	
	private Integer  url_out_day;
	private String serachState;

	public Integer getAddReportCount() {
		return addReportCount;
	}

	public void setAddReportCount(Integer addReportCount) {
		this.addReportCount = addReportCount;
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

	public String getUser_name_conduct() {
		return user_name_conduct;
	}

	public void setUser_name_conduct(String user_name_conduct) {
		this.user_name_conduct = user_name_conduct;
	}

	public String getReport_state() {
		return report_state;
	}

	public void setReport_state(String report_state) {
		this.report_state = report_state;
	}

	public String getUser_level() {
		return user_level;
	}

	public void setUser_level(String user_level) {
		this.user_level = user_level;
	}

	public Integer getMp2outcount() {
		return mp2outcount;
	}

	public void setMp2outcount(Integer mp2outcount) {
		this.mp2outcount = mp2outcount;
	}

	public Integer getMq4outcount() {
		return mq4outcount;
	}

	public void setMq4outcount(Integer mq4outcount) {
		this.mq4outcount = mq4outcount;
	}

	public Integer getMq5outcount() {
		return mq5outcount;
	}

	public void setMq5outcount(Integer mq5outcount) {
		this.mq5outcount = mq5outcount;
	}

	public Integer getMq9outcount() {
		return mq9outcount;
	}

	public void setMq9outcount(Integer mq9outcount) {
		this.mq9outcount = mq9outcount;
	}

	public Integer getTdsoutcount() {
		return tdsoutcount;
	}

	public void setTdsoutcount(Integer tdsoutcount) {
		this.tdsoutcount = tdsoutcount;
	}

	public Integer getPhoutcount() {
		return phoutcount;
	}

	public void setPhoutcount(Integer phoutcount) {
		this.phoutcount = phoutcount;
	}

	public Integer getDeviceoutcount() {
		return deviceoutcount;
	}

	public void setDeviceoutcount(Integer deviceoutcount) {
		this.deviceoutcount = deviceoutcount;
	}

	public Integer getDevicecount() {
		return devicecount;
	}

	public void setDevicecount(Integer devicecount) {
		this.devicecount = devicecount;
	}

	public Integer getTodaybanquetcount() {
		return todaybanquetcount;
	}

	public void setTodaybanquetcount(Integer todaybanquetcount) {
		this.todaybanquetcount = todaybanquetcount;
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

	public Integer getTodaywaitcheckcount() {
		return todaywaitcheckcount;
	}

	public void setTodaywaitcheckcount(Integer todaywaitcheckcount) {
		this.todaywaitcheckcount = todaywaitcheckcount;
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

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_mobilephone() {
		return user_mobilephone;
	}

	public void setUser_mobilephone(String user_mobilephone) {
		this.user_mobilephone = user_mobilephone;
	}

	public Integer getMissCardChefCount() {
		return missCardChefCount;
	}

	public void setMissCardChefCount(Integer missCardChefCount) {
		this.missCardChefCount = missCardChefCount;
	}

	public Integer getAllChefCount() {
		return allChefCount;
	}

	public void setAllChefCount(Integer allChefCount) {
		this.allChefCount = allChefCount;
	}

	public Integer getDatechefcount() {
		return datechefcount;
	}

	public void setDatechefcount(Integer datechefcount) {
		this.datechefcount = datechefcount;
	}

	public Integer getDateoutchefcount() {
		return dateoutchefcount;
	}

	public void setDateoutchefcount(Integer dateoutchefcount) {
		this.dateoutchefcount = dateoutchefcount;
	}

	public Integer getDatewilloutchefcount() {
		return datewilloutchefcount;
	}

	public void setDatewilloutchefcount(Integer datewilloutchefcount) {
		this.datewilloutchefcount = datewilloutchefcount;
	}

	public Integer getHg_state() {
		return hg_state;
	}

	public void setHg_state(Integer hg_state) {
		this.hg_state = hg_state;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
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

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public String getDevice_user_name() {
		return device_user_name;
	}

	public void setDevice_user_name(String device_user_name) {
		this.device_user_name = device_user_name;
	}

	public Integer getDevice_state() {
		return device_state;
	}

	public void setDevice_state(Integer device_state) {
		this.device_state = device_state;
	}

	public String getDevice_out_state() {
		return device_out_state;
	}

	public void setDevice_out_state(String device_out_state) {
		this.device_out_state = device_out_state;
	}

	public String getDevice_number() {
		return device_number;
	}

	public void setDevice_number(String device_number) {
		this.device_number = device_number;
	}

	public String getDevice_code() {
		return device_code;
	}

	public void setDevice_code(String device_code) {
		this.device_code = device_code;
	}

	public String getUser_name_check() {
		return user_name_check;
	}

	public void setUser_name_check(String user_name_check) {
		this.user_name_check = user_name_check;
	}

	public String getPxYear() {
		return pxYear;
	}

	public void setPxYear(String pxYear) {
		this.pxYear = pxYear;
	}

	public Integer getWeek_add_report_count() {
		return week_add_report_count;
	}

	public void setWeek_add_report_count(Integer week_add_report_count) {
		this.week_add_report_count = week_add_report_count;
	}

	public Integer getAll_report_count() {
		return all_report_count;
	}

	public void setAll_report_count(Integer all_report_count) {
		this.all_report_count = all_report_count;
	}

	public Integer getWeek_add_chef_count() {
		return week_add_chef_count;
	}

	public void setWeek_add_chef_count(Integer week_add_chef_count) {
		this.week_add_chef_count = week_add_chef_count;
	}

	public Integer getAll_chef_count() {
		return all_chef_count;
	}

	public void setAll_chef_count(Integer all_chef_count) {
		this.all_chef_count = all_chef_count;
	}

	public Integer getWeek_add_gl_count() {
		return week_add_gl_count;
	}

	public void setWeek_add_gl_count(Integer week_add_gl_count) {
		this.week_add_gl_count = week_add_gl_count;
	}

	public Integer getAll_gl_count() {
		return all_gl_count;
	}

	public void setAll_gl_count(Integer all_gl_count) {
		this.all_gl_count = all_gl_count;
	}

	public String getUser_name_chef() {
		return user_name_chef;
	}

	public void setUser_name_chef(String user_name_chef) {
		this.user_name_chef = user_name_chef;
	}

	public Integer getUrl_out_day() {
		return url_out_day;
	}

	public void setUrl_out_day(Integer url_out_day) {
		this.url_out_day = url_out_day;
	}

	public String getSerachState() {
		return serachState;
	}

	public void setSerachState(String serachState) {
		this.serachState = serachState;
	}
}
