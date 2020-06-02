package com.rs.controller;
import com.rs.po.Report;
import com.rs.po.ReportCheck;
import com.rs.po.ReportProcess;
import com.rs.po.ReportSubChef;
import com.rs.po.User;
import com.rs.service.ReportCheckService;
import com.rs.service.ReportProcessService;
import com.rs.service.ReportService;
import com.rs.service.ReportSubChefService;
import com.rs.service.RoleService;
import com.rs.service.UserService;
import com.rs.util.CalcUtil;
import com.rs.util.CharacterUtil;
import com.rs.util.CodeUtil;
import com.rs.util.DateUtil;
import com.rs.util.RegularUtil;
import com.rs.util.TokenUserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信端报备控制层
 * @ClassName: AppReportController
 * @Description: 
 * @Author sven
 * @DateTime 2020年4月13日 下午5:19:20
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/app/report")
public class AppReportController extends BaseController {

	@Autowired
	private ReportService reportService;
	@Autowired
	private ReportCheckService reportCheckService;
	@Autowired
	private ReportSubChefService reportSubChefService;
	@Autowired
	private ReportProcessService reportProcessService;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	private Logger logger = LoggerFactory.getLogger(AppReportController.class);

	/**
	 * 报备列表
	 * @Title: list
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月14日 上午10:49:04
	 * @param form
	 * @return
	 */
	@GetMapping(value="/list")
	public Object list(Report form,User user){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String user_code = user.getUser_code();
			if(StringUtils.isEmpty(user_code) || RegularUtil.isSpecialChar(user_code) || RegularUtil.matchLength(user_code,25)) {
				CODEMSG = USER_CODE_UNVALID;
				return finish(CODEMSG, null);
			}	
			user = TokenUserUtil.findByCode(userService, user);
			if(StringUtils.isEmpty(user)) {
				CODEMSG = USER_UNDEFINED;
				return finish(CODEMSG, null);
			}
			//用户类型
			String user_type = user.getUser_type();
			
			logger.info("user_code:{},user_type:{},user_name:{}",user.getUser_code(),user.getUser_type(),user.getUser_name());
			form.setUser_code(null);
			if(("举办者").equals(user_type)) {
				form.setUser_code_conduct(user_code);
			}else if(("乡厨").equals(user_type) || ("农家乐").equals(user_type) || ("乡村酒店").equals(user_type)) {
				form.setUser_code_mainchef(user_code);
			}else if(("平台管理员").equals(user_type) || ("协管员").equals(user_type)) {
				//管理员必须是报备完整才能查看
				form.setReport_full(2);
				if(StringUtils.isEmpty(form.getProvince_conduct())) {
					form.setProvince_conduct(user.getUser_province());
				}
				if(StringUtils.isEmpty(form.getCity_conduct())) {
					form.setCity_conduct(user.getUser_city());
				}
				if(StringUtils.isEmpty(form.getArea_conduct())) {
					form.setArea_conduct(user.getUser_area());
				}
				if(StringUtils.isEmpty(form.getTown_conduct())) {
					form.setTown_conduct(user.getUser_town());
				}
				if(StringUtils.isEmpty(form.getVill_conduct())) {
					form.setVill_conduct(user.getUser_vill());
				}
				Integer report_state = form.getReport_state();
				if (StringUtils.isEmpty(report_state)) {
					form.setReport_state(1);
					form.setReport_timeout_state(1);
				}
				
				if(report_state == 1 || report_state == 3) {
					form.setReport_timeout_state(1);
				}else if (report_state == 45){
					form.setReturninfo("4and5");
					form.setReport_timeout_state(null);
				}else if (report_state == -1){
					form.setReturninfo("-1");
					form.setReport_timeout_state(2);
				}
				
				Report report = new Report();
				BeanUtils.copyProperties(form, report);
				report.setReturninfo(null);
				//待审核数量
				report.setReport_state(1);
				report.setReport_timeout_state(1);
				map.put("wait_audit_count",reportService.findByCount(report));
				//待检查数量
				report.setReport_state(3);
				report.setReport_timeout_state(1);
				map.put("wait_check_count",reportService.findByCount(report));
				//已检查数量
				report.setReport_state(null);
				report.setReturninfo("4and5");
				report.setReport_timeout_state(null);
				map.put("finish_audit_count",reportService.findByCount(report));
				//过期/失效数量
				report.setReturninfo("-1");
				report.setReport_timeout_state(2);
				map.put("expire_count",reportService.findByCount(report));
			}
			map.put("pager_count", reportService.findByCount(form));
			
			List<Object> report_list = new ArrayList<Object>();
			List<Report> query_list = reportService.findByList(form);
			Map<String,Object> result_map = null;
			for (Report r : query_list) {
				result_map = new HashMap<String,Object>();
				result_map.put("report_code", r.getReport_code());
				result_map.put("user_name_conduct", r.getUser_name_conduct());
				result_map.put("user_name_mainchef", r.getUser_name_mainchef());
				result_map.put("province_conduct", r.getProvince_conduct());
				result_map.put("city_conduct", r.getCity_conduct());
				result_map.put("area_conduct", r.getArea_conduct());
				result_map.put("town_conduct", r.getTown_conduct());
				result_map.put("vill_conduct", r.getVill_conduct());
				result_map.put("address_conduct", r.getAddress_conduct());
				result_map.put("report_timeout_state", r.getReport_timeout_state());
				result_map.put("report_state", r.getReport_state());
				result_map.put("banquet_time", r.getBanquet_time());
				result_map.put("banquet_expiretime", r.getBanquet_expiretime());
				result_map.put("report_full", r.getReport_full());
				result_map.put("addtime", r.getAddtime());
				
				report_list.add(result_map);
			}
			map.put("report_list", report_list);
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.error(ex.toString());
		}
		return finish(CODEMSG, map);
	}
	
	
	/**
	 * 举办者新增报备
	 * @Title: saveConductReport
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月15日 下午3:07:16
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/conduct/save")
	public Object saveConductReport(@RequestBody Report form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String user_code = form.getUser_code();
			if(StringUtils.isEmpty(user_code) || RegularUtil.isSpecialChar(user_code) || RegularUtil.matchLength(user_code,25)) {
				CODEMSG = USER_CODE_UNVALID;
				return finish(CODEMSG, null);
			}	
			User user = new User();
			user.setUser_code(user_code);
			user = TokenUserUtil.findByCode(userService, user);
			if(StringUtils.isEmpty(user)) {
				CODEMSG = USER_UNDEFINED;
				return finish(CODEMSG, null);
			}
			String user_name_conduct =  form.getUser_name_conduct();
			if (StringUtils.isEmpty(user_name_conduct) || RegularUtil.isSpecialChar(user_name_conduct) || !RegularUtil.isLength(user_name_conduct, 1, 50)) {
				CODEMSG = USER_NAME_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String user_mobilephone_conduct = form.getUser_mobilephone_conduct();
			if (StringUtils.isEmpty(user_mobilephone_conduct) || !RegularUtil.isMobilePhone(user_mobilephone_conduct)|| RegularUtil.matchLength(user_mobilephone_conduct, 11)) {
				CODEMSG = USER_MOBILEPHONE_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String province_conduct = form.getProvince_conduct();
			if (StringUtils.isEmpty(province_conduct) || RegularUtil.isSpecialChar(province_conduct)) {
				CODEMSG = PROVINCE_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String city_conduct = form.getCity_conduct();
			if (StringUtils.isEmpty(city_conduct) || RegularUtil.isSpecialChar(city_conduct)) {
				CODEMSG = CITY_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String area_conduct = form.getArea_conduct();
			if (StringUtils.isEmpty(area_conduct) || RegularUtil.isSpecialChar(area_conduct)) {
				CODEMSG = AREA_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String town_conduct = form.getTown_conduct();
			if (StringUtils.isEmpty(town_conduct) || RegularUtil.isSpecialChar(town_conduct)) {
				CODEMSG = TOWN_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String vill_conduct = form.getVill_conduct();
			if (StringUtils.isEmpty(vill_conduct) || RegularUtil.isSpecialChar(vill_conduct)) {
				CODEMSG = VILL_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String address_conduct = form.getAddress_conduct();
			if (StringUtils.isEmpty(address_conduct) || RegularUtil.isSpecialChar(address_conduct)) {
				CODEMSG = ADDRESS_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String banquet_time = form.getBanquet_time();
			if (StringUtils.isEmpty(banquet_time) ||(banquet_time.compareTo(DateUtil.getDateYearMonthDate())<0) || !DateUtil.isValidDate(banquet_time,3)) {
				CODEMSG = BANQUET_TIME_UNVALID;
				return finish(CODEMSG, null);
			}
			String banquet_day = form.getBanquet_day();
			if (StringUtils.isEmpty(banquet_day) || !RegularUtil.OneToAnyNumber(banquet_day)) {
				CODEMSG = BANQUET_DAY_UNVALID;
				return finish(CODEMSG, null);
			}
			Integer banquet_type = form.getBanquet_type();
			if (StringUtils.isEmpty(banquet_type) || !RegularUtil.OneToAnyNumber(banquet_type+"")) {
				CODEMSG = BANQUET_TYPE_UNVALID;
				return finish(CODEMSG, null);
			}
			String banquet_people = form.getBanquet_people();
			if (StringUtils.isEmpty(banquet_people) || !RegularUtil.OneToAnyNumber(banquet_people+"")) {
				CODEMSG = BANQUET_PEOPLE_UNVALID;
				return finish(CODEMSG, null);
			}
			String user_code_mainchef = form.getUser_code_mainchef();
			if (StringUtils.isEmpty(user_code_mainchef) || RegularUtil.isSpecialChar(user_code_mainchef) || RegularUtil.matchLength(user_code_mainchef, 25)) {
				CODEMSG = USER_CODE_MAINCHEF_UNVALID;
				return finish(CODEMSG, null);
			}
			String user_name_mainchef = form.getUser_name_mainchef();
			if (StringUtils.isEmpty(user_name_mainchef) || RegularUtil.isSpecialChar(user_name_mainchef) || !RegularUtil.isLength(user_code_mainchef,1,50)) {
				CODEMSG = USER_NAME_MAINCHEF_UNVALID;
				return finish(CODEMSG, null);
			}
			String user_mobilephone_mainchef = form.getUser_mobilephone_mainchef();
			if (StringUtils.isEmpty(user_mobilephone_mainchef) || !RegularUtil.isMobilePhone(user_mobilephone_mainchef)) {
				CODEMSG = USER_MOBILEPHONE_MAINCHEF_UNVALID;
				return finish(CODEMSG, null);
			}
			form.setUser_code(user.getUser_code());
			form.setUser_name(user.getUser_name());
			form.setUser_code_conduct(user.getUser_code());
			form.setUser_idcard_conduct(user.getUser_idcard());
			
			//判断特殊字符====================================================结束
			form.setReport_code(CodeUtil.getSystemCode("report"));
			form.setReport_type(1);
			form.setReport_mode(1);
			form.setReport_full(1);
			//计算办宴过期时间
			form.setBanquet_expiretime(DateUtil.calcDate(DateUtil.YYYY_MM_DD_FORMAT,"DATE",banquet_time,Integer.valueOf(CalcUtil.subtr(form.getBanquet_day(),"1"))));
			Integer result = reportService.save(form);
			if(result != 0){
				CODEMSG = SUCCESS;
			}else{
				map = null;
				CODEMSG = ERROR;
			}
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.error(ex.toString());
		}
		return finish(CODEMSG, map);
	}
	
	
	/**
	 * 查询报备明细
	 * @Title: single
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月15日 下午3:31:12
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/single")
	public Object single(Report form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String report_code = form.getReport_code();
			if (StringUtils.isEmpty(report_code) || RegularUtil.matchLength(report_code, 25)) {
				CODEMSG = REPORT_CODE_UNVALID;
				return finish(CODEMSG, null);
			}
			//报备主体
			map.put("report", reportService.findByCode_app(form));
			//场地信息
			map.put("report_process", reportProcessService.findByReportCode_app(report_code));
			//报备检查
			map.put("report_check", reportCheckService.findByReportCode(report_code));
			//帮厨
			ReportSubChef sub_chef = new ReportSubChef();
			sub_chef.setReport_code(report_code);
			map.put("sub_chef_list", reportSubChefService.findByAll_app(sub_chef));
			
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, map);
	}
	
	/**
	 * 举办者编辑报备
	 * @Title: updateConductReport
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月15日 下午4:36:39
	 * @param form
	 * @return
	 */
	@PutMapping(value = "/conduct/update")
	public Object updateConductReport(@RequestBody Report form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String report_code = form.getReport_code();
			if(StringUtils.isEmpty(report_code) || RegularUtil.isSpecialChar(report_code) || RegularUtil.matchLength(report_code,25)) {
				CODEMSG = REPORT_CODE_UNVALID;
				return finish(CODEMSG, null);
			}	
			String province_conduct = form.getProvince_conduct();
			if (StringUtils.isEmpty(province_conduct) || RegularUtil.isSpecialChar(province_conduct)) {
				CODEMSG = PROVINCE_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String city_conduct = form.getCity_conduct();
			if (StringUtils.isEmpty(city_conduct) || RegularUtil.isSpecialChar(city_conduct)) {
				CODEMSG = CITY_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String area_conduct = form.getArea_conduct();
			if (StringUtils.isEmpty(area_conduct) || RegularUtil.isSpecialChar(area_conduct)) {
				CODEMSG = AREA_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String town_conduct = form.getTown_conduct();
			if (StringUtils.isEmpty(town_conduct) || RegularUtil.isSpecialChar(town_conduct)) {
				CODEMSG = TOWN_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String vill_conduct = form.getVill_conduct();
			if (StringUtils.isEmpty(vill_conduct) || RegularUtil.isSpecialChar(vill_conduct)) {
				CODEMSG = VILL_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String address_conduct = form.getAddress_conduct();
			if (StringUtils.isEmpty(address_conduct) || RegularUtil.isSpecialChar(address_conduct)) {
				CODEMSG = ADDRESS_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String banquet_time = form.getBanquet_time();
			if (StringUtils.isEmpty(banquet_time) ||(banquet_time.compareTo(DateUtil.getDateYearMonthDate())<0) || !DateUtil.isValidDate(banquet_time,3)) {
				CODEMSG = BANQUET_TIME_UNVALID;
				return finish(CODEMSG, null);
			}
			String banquet_day = form.getBanquet_day();
			if (StringUtils.isEmpty(banquet_day) || !RegularUtil.OneToAnyNumber(banquet_day)) {
				CODEMSG = BANQUET_DAY_UNVALID;
				return finish(CODEMSG, null);
			}
			Integer banquet_type = form.getBanquet_type();
			if (StringUtils.isEmpty(banquet_type) || !RegularUtil.OneToAnyNumber(banquet_type+"")) {
				CODEMSG = BANQUET_TYPE_UNVALID;
				return finish(CODEMSG, null);
			}
			String banquet_people = form.getBanquet_people();
			if (StringUtils.isEmpty(banquet_people) || !RegularUtil.OneToAnyNumber(banquet_people+"")) {
				CODEMSG = BANQUET_PEOPLE_UNVALID;
				return finish(CODEMSG, null);
			}
			String user_code_mainchef = form.getUser_code_mainchef();
			if (StringUtils.isEmpty(user_code_mainchef) || RegularUtil.isSpecialChar(user_code_mainchef) || RegularUtil.matchLength(user_code_mainchef, 25)) {
				CODEMSG = USER_CODE_MAINCHEF_UNVALID;
				return finish(CODEMSG, null);
			}
			String user_name_mainchef = form.getUser_name_mainchef();
			if (StringUtils.isEmpty(user_name_mainchef) || RegularUtil.isSpecialChar(user_name_mainchef) || !RegularUtil.isLength(user_code_mainchef,1,50)) {
				CODEMSG = USER_NAME_MAINCHEF_UNVALID;
				return finish(CODEMSG, null);
			}
			String user_mobilephone_mainchef = form.getUser_mobilephone_mainchef();
			if (StringUtils.isEmpty(user_mobilephone_mainchef) || !RegularUtil.isMobilePhone(user_mobilephone_mainchef)) {
				CODEMSG = USER_MOBILEPHONE_MAINCHEF_UNVALID;
				return finish(CODEMSG, null);
			}
			//计算办宴过期时间
			form.setBanquet_expiretime(DateUtil.calcDate(DateUtil.YYYY_MM_DD_FORMAT,"DATE",banquet_time,Integer.valueOf(CalcUtil.subtr(form.getBanquet_day(),"1"))));
			int result = reportService.update(form);
			if (result > 0) {
				CODEMSG = SUCCESS;
			} else {
				map = null;
				CODEMSG = ERROR;
			}
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.error(ex.toString());
		}
		return finish(CODEMSG, map);
	}
	
	/**
	 * 乡厨选择报备
	 * @Title: choose
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月16日 上午10:11:20
	 * @param form
	 * @param user
	 * @return
	 */
	@GetMapping(value="/choose")
	public Object choose(Report form,User user){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String user_code = user.getUser_code();
			if(StringUtils.isEmpty(user_code) || RegularUtil.isSpecialChar(user_code) || RegularUtil.matchLength(user_code,25)) {
				CODEMSG = USER_CODE_UNVALID;
				return finish(CODEMSG, null);
			}	
			user = TokenUserUtil.findByCode(userService, user);
			if(StringUtils.isEmpty(user)) {
				CODEMSG = USER_UNDEFINED;
				return finish(CODEMSG, null);
			}
			form.setUser_code_mainchef(user.getUser_code());
			form.setReport_full(1);//报备未完善
			form.setReport_timeout_state(1);//未过期
			form.setUser_code(null);//查询乡厨编码为自己
			map.put("pager_count", reportService.findByCount(form));
			List<Object> report_list = new ArrayList<Object>();
			List<Report> query_list = reportService.findByList(form);
			Map<String,Object> result_map = null;
			for (Report r : query_list) {
				result_map = new HashMap<String,Object>();
				result_map.put("report_code", r.getReport_code());
				result_map.put("user_code_conduct", r.getUser_code_conduct());
				result_map.put("user_name_conduct", r.getUser_name_conduct());
				result_map.put("user_name_mainchef", r.getUser_name_mainchef());
				result_map.put("user_mobilephone_conduct", r.getUser_mobilephone_conduct());
				result_map.put("province_conduct", r.getProvince_conduct());
				result_map.put("city_conduct", r.getCity_conduct());
				result_map.put("area_conduct", r.getArea_conduct());
				result_map.put("town_conduct", r.getTown_conduct());
				result_map.put("vill_conduct", r.getVill_conduct());
				result_map.put("address_conduct", r.getAddress_conduct());
				result_map.put("banquet_time", r.getBanquet_time());
				result_map.put("banquet_day", r.getBanquet_day());
				result_map.put("banquet_type", r.getBanquet_type());
				result_map.put("banquet_people", r.getBanquet_people());
				result_map.put("addtime", r.getAddtime());
				report_list.add(result_map);
			}
			map.put("report_list", report_list);
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.error(ex.toString());
		}
		return finish(CODEMSG, map);
	}
	
	
	/**
	 * 乡厨报备_选择举办者这个人进行报备,则是新增一条报备
	 * @Title: saveChefReport
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月16日 下午2:43:32
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/chef/save")
	public Object saveChefReport(@RequestBody Report form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String user_code = form.getUser_code();
			if(StringUtils.isEmpty(user_code) || RegularUtil.isSpecialChar(user_code) || RegularUtil.matchLength(user_code,25)) {
				CODEMSG = USER_CODE_UNVALID;
				return finish(CODEMSG, null);
			}	
			User user = new User();
			user.setUser_code(user_code);
			user = TokenUserUtil.findByCode(userService, user);
			if(StringUtils.isEmpty(user)) {
				CODEMSG = USER_UNDEFINED;
				return finish(CODEMSG, null);
			}
			String report_code = form.getReport_code();
			if(StringUtils.isEmpty(report_code) || RegularUtil.isSpecialChar(report_code) || RegularUtil.matchLength(report_code,25)) {
				CODEMSG = REPORT_CODE_UNVALID;
				return finish(CODEMSG, null);
			}	
			String user_name_conduct =  form.getUser_name_conduct();
			if (StringUtils.isEmpty(user_name_conduct) || RegularUtil.isSpecialChar(user_name_conduct) || !RegularUtil.isLength(user_name_conduct, 1, 50)) {
				CODEMSG = USER_NAME_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String user_mobilephone_conduct = form.getUser_mobilephone_conduct();
			if (StringUtils.isEmpty(user_mobilephone_conduct) || !RegularUtil.isMobilePhone(user_mobilephone_conduct)|| RegularUtil.matchLength(user_mobilephone_conduct, 11)) {
				CODEMSG = USER_MOBILEPHONE_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String province_conduct = form.getProvince_conduct();
			if (StringUtils.isEmpty(province_conduct) || RegularUtil.isSpecialChar(province_conduct)) {
				CODEMSG = PROVINCE_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String city_conduct = form.getCity_conduct();
			if (StringUtils.isEmpty(city_conduct) || RegularUtil.isSpecialChar(city_conduct)) {
				CODEMSG = CITY_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String area_conduct = form.getArea_conduct();
			if (StringUtils.isEmpty(area_conduct) || RegularUtil.isSpecialChar(area_conduct)) {
				CODEMSG = AREA_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String town_conduct = form.getTown_conduct();
			if (StringUtils.isEmpty(town_conduct) || RegularUtil.isSpecialChar(town_conduct)) {
				CODEMSG = TOWN_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String vill_conduct = form.getVill_conduct();
			if (StringUtils.isEmpty(vill_conduct) || RegularUtil.isSpecialChar(vill_conduct)) {
				CODEMSG = VILL_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String address_conduct = form.getAddress_conduct();
			if (StringUtils.isEmpty(address_conduct) || RegularUtil.isSpecialChar(address_conduct)) {
				CODEMSG = ADDRESS_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String banquet_time = form.getBanquet_time();
			if (StringUtils.isEmpty(banquet_time) ||(banquet_time.compareTo(DateUtil.getDateYearMonthDate())<0) || !DateUtil.isValidDate(banquet_time,3)) {
				CODEMSG = BANQUET_TIME_UNVALID;
				return finish(CODEMSG, null);
			}
			String banquet_day = form.getBanquet_day();
			if (StringUtils.isEmpty(banquet_day) || !RegularUtil.OneToAnyNumber(banquet_day)) {
				CODEMSG = BANQUET_DAY_UNVALID;
				return finish(CODEMSG, null);
			}
			Integer banquet_type = form.getBanquet_type();
			if (StringUtils.isEmpty(banquet_type) || !RegularUtil.OneToAnyNumber(banquet_type+"")) {
				CODEMSG = BANQUET_TYPE_UNVALID;
				return finish(CODEMSG, null);
			}
			String banquet_people = form.getBanquet_people();
			if (StringUtils.isEmpty(banquet_people) || !RegularUtil.OneToAnyNumber(banquet_people+"")) {
				CODEMSG = BANQUET_PEOPLE_UNVALID;
				return finish(CODEMSG, null);
			}
			form.setUser_code_mainchef(user.getUser_code());
			form.setUser_name_mainchef(user.getUser_name());
			form.setUser_mobilephone_mainchef(user.getUser_mobilephone());
			
			form.setUser_code(user.getUser_code());
			form.setUser_name(user.getUser_name());
			
			form.setReport_type(1);
			form.setReport_mode(2);
			form.setReport_full(2);
			
			//计算办宴过期时间
			form.setBanquet_expiretime(DateUtil.calcDate(DateUtil.YYYY_MM_DD_FORMAT,"DATE",banquet_time,Integer.valueOf(CalcUtil.subtr(form.getBanquet_day(),"1"))));
			Integer result = reportService.save(form);
			if(result != 0){
				//1:更新乡厨报备次数
				user.setReport_count(user.getReport_count()+1);
				userService.update(user);
				
				//2:根据举办者手机号查询是否存在,如果不存在则默认新增一条举办者记录
				User user_conduct = new User();
				user_conduct.setUser_mobilephone(form.getUser_mobilephone_conduct());
				user_conduct = userService.findByColumn(user_conduct);
				if(!StringUtils.isEmpty(user_conduct)) {
					form.setUser_code_conduct(user_conduct.getUser_code());
				}else {
					user_conduct = new User();
					user_conduct.setUser_name(form.getUser_name_conduct());
					user_conduct.setUser_code(CodeUtil.getSystemCode("user"));
					user_conduct.setUser_enname_short(CharacterUtil.getAllpySpell(form.getUser_name()));
					user_conduct.setUser_loginname(user_conduct.getUser_mobilephone());
					user_conduct.setUser_loginpass(user_conduct.getUser_mobilephone().substring(5,user_conduct.getUser_mobilephone().length()));
					user_conduct.setUser_type("举办者");
					user_conduct.setUser_province(form.getProvince_conduct());
					user_conduct.setUser_city(form.getCity_conduct());
					user_conduct.setUser_area(form.getArea_conduct());
					user_conduct.setUser_town(form.getTown_conduct());
					user_conduct.setUser_vill(form.getVill_conduct());
					user_conduct.setUser_address(form.getAddress_conduct());
					user_conduct.setUser_logo_url("images/user_logo.png");
					user_conduct.setUser_idcard_logo_front("images/user_idcard_logo_front.png");
					user_conduct.setUser_idcard_logo_back("images/user_idcard_logo_back.png");
					form.setUser_code_conduct(user_conduct.getUser_code());
					user_conduct.setUser_registersource(1);
					user_conduct.setUser_audit_state(2);
					userService.save(user_conduct);
					Map<String,String> role_map = CodeUtil.setUserRole(user_conduct);
					roleService.saveRoleUser(role_map);
				}
				
				//3:新增报备场地信息
				ReportProcess reportProcess = form.getReport_process();
				reportProcess.setReport_process_code(CodeUtil.getSystemCode("reportProcess"));
				reportProcessService.save(reportProcess);
				
				//4:新增报备帮厨信息
				ReportSubChef reportSubChef = new ReportSubChef();
				List<ReportSubChef> report_sub_chef_list = form.getReport_sub_chef_list();
				if(!StringUtils.isEmpty(report_sub_chef_list) && report_sub_chef_list.size() > 0) {
					reportSubChef.setPager_list(report_sub_chef_list);
					reportSubChefService.saveBatch(reportSubChef);
				}
				CODEMSG = SUCCESS;
			}else{
				map = null;
				CODEMSG = ERROR;
			}
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.error(ex.toString());
		}
		return finish(CODEMSG, map);
	}
	
	
	/**
	 * 乡厨报备_选择举办者已经添加的报备进行完善
	 * @Title: updateChefReport
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月16日 下午3:14:35
	 * @param form
	 * @return
	 */
	@PutMapping(value = "/chef/complete")
	public Object completeChefReport(@RequestBody Report form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String user_code = form.getUser_code();
			if(StringUtils.isEmpty(user_code) || RegularUtil.isSpecialChar(user_code) || RegularUtil.matchLength(user_code,25)) {
				CODEMSG = USER_CODE_UNVALID;
				return finish(CODEMSG, null);
			}	
			User user = new User();
			user.setUser_code(user_code);
			user = TokenUserUtil.findByCode(userService, user);
			if(StringUtils.isEmpty(user)) {
				CODEMSG = USER_UNDEFINED;
				return finish(CODEMSG, null);
			}
			String report_code = form.getReport_code();
			if(StringUtils.isEmpty(report_code) || RegularUtil.isSpecialChar(report_code) || RegularUtil.matchLength(report_code,25)) {
				CODEMSG = REPORT_CODE_UNVALID;
				return finish(CODEMSG, null);
			}
			String user_mobilephone_conduct = form.getUser_mobilephone_conduct();
			if (StringUtils.isEmpty(user_mobilephone_conduct) || !RegularUtil.isMobilePhone(user_mobilephone_conduct)|| RegularUtil.matchLength(user_mobilephone_conduct, 11)) {
				CODEMSG = USER_MOBILEPHONE_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String province_conduct = form.getProvince_conduct();
			if (StringUtils.isEmpty(province_conduct) || RegularUtil.isSpecialChar(province_conduct)) {
				CODEMSG = PROVINCE_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String city_conduct = form.getCity_conduct();
			if (StringUtils.isEmpty(city_conduct) || RegularUtil.isSpecialChar(city_conduct)) {
				CODEMSG = CITY_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String area_conduct = form.getArea_conduct();
			if (StringUtils.isEmpty(area_conduct) || RegularUtil.isSpecialChar(area_conduct)) {
				CODEMSG = AREA_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String town_conduct = form.getTown_conduct();
			if (StringUtils.isEmpty(town_conduct) || RegularUtil.isSpecialChar(town_conduct)) {
				CODEMSG = TOWN_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String vill_conduct = form.getVill_conduct();
			if (StringUtils.isEmpty(vill_conduct) || RegularUtil.isSpecialChar(vill_conduct)) {
				CODEMSG = VILL_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String address_conduct = form.getAddress_conduct();
			if (StringUtils.isEmpty(address_conduct) || RegularUtil.isSpecialChar(address_conduct)) {
				CODEMSG = ADDRESS_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String banquet_time = form.getBanquet_time();
			if (StringUtils.isEmpty(banquet_time) ||(banquet_time.compareTo(DateUtil.getDateYearMonthDate())<0) || !DateUtil.isValidDate(banquet_time,3)) {
				CODEMSG = BANQUET_TIME_UNVALID;
				return finish(CODEMSG, null);
			}
			String banquet_day = form.getBanquet_day();
			if (StringUtils.isEmpty(banquet_day) || !RegularUtil.OneToAnyNumber(banquet_day)) {
				CODEMSG = BANQUET_DAY_UNVALID;
				return finish(CODEMSG, null);
			}
			Integer banquet_type = form.getBanquet_type();
			if (StringUtils.isEmpty(banquet_type) || !RegularUtil.OneToAnyNumber(banquet_type+"")) {
				CODEMSG = BANQUET_TYPE_UNVALID;
				return finish(CODEMSG, null);
			}
			String banquet_people = form.getBanquet_people();
			if (StringUtils.isEmpty(banquet_people) || !RegularUtil.OneToAnyNumber(banquet_people+"")) {
				CODEMSG = BANQUET_PEOPLE_UNVALID;
				return finish(CODEMSG, null);
			}
			
			form.setReport_full(2);
			
			//计算办宴过期时间
			form.setBanquet_expiretime(DateUtil.calcDate(DateUtil.YYYY_MM_DD_FORMAT,"DATE",banquet_time,Integer.valueOf(CalcUtil.subtr(form.getBanquet_day(),"1"))));
			Integer result = reportService.update(form);
			if(result != 0){
				//1:更新乡厨报备次数
				user.setReport_count(user.getReport_count()+1);
				userService.update(user);
				
				//2:根据举办者手机号查询是否存在,如果不存在则默认新增一条举办者记录
				User user_conduct = new User();
				user_conduct.setUser_mobilephone(form.getUser_mobilephone_conduct());
				user_conduct = userService.findByColumn(user_conduct);
				if(StringUtils.isEmpty(user_conduct)) {
					user_conduct = new User();
					user_conduct.setUser_name(form.getUser_name_conduct());
					user_conduct.setUser_code(CodeUtil.getSystemCode("user"));
					user_conduct.setUser_enname_short(CharacterUtil.getAllpySpell(form.getUser_name()));
					user_conduct.setUser_loginname(user_conduct.getUser_mobilephone());
					user_conduct.setUser_loginpass(user_conduct.getUser_mobilephone().substring(5,user_conduct.getUser_mobilephone().length()));
					user_conduct.setUser_type("举办者");
					user_conduct.setUser_province(form.getProvince_conduct());
					user_conduct.setUser_city(form.getCity_conduct());
					user_conduct.setUser_area(form.getArea_conduct());
					user_conduct.setUser_town(form.getTown_conduct());
					user_conduct.setUser_vill(form.getVill_conduct());
					user_conduct.setUser_address(form.getAddress_conduct());
					user_conduct.setUser_logo_url("images/user_logo.png");
					user_conduct.setUser_idcard_logo_front("images/user_idcard_logo_front.png");
					user_conduct.setUser_idcard_logo_back("images/user_idcard_logo_back.png");
					form.setUser_code_conduct(user_conduct.getUser_code());
					user_conduct.setUser_registersource(1);
					user_conduct.setUser_audit_state(2);
					userService.save(user_conduct);
					Map<String,String> role_map = CodeUtil.setUserRole(user_conduct);
					roleService.saveRoleUser(role_map);
				}
				
				//3:新增报备场地信息
				ReportProcess reportProcess = form.getReport_process();
				reportProcess.setReport_process_code(CodeUtil.getSystemCode("reportProcess"));
				reportProcessService.save(reportProcess);
				
				//4:新增报备帮厨信息
				ReportSubChef reportSubChef = new ReportSubChef();
				List<ReportSubChef> report_sub_chef_list = form.getReport_sub_chef_list();
				if(!StringUtils.isEmpty(report_sub_chef_list) && report_sub_chef_list.size() > 0) {
					reportSubChef.setPager_list(report_sub_chef_list);
					reportSubChefService.saveBatch(reportSubChef);
				}
				CODEMSG = SUCCESS;
			}else{
				map = null;
				CODEMSG = ERROR;
			}
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.error(ex.toString());
		}
		return finish(CODEMSG, map);
	}
	
	
	/**
	 * 乡厨更新报备
	 * @Title: updateChefReport
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月16日 下午3:14:35
	 * @param form
	 * @return
	 */
	@PutMapping(value = "/chef/update")
	public Object updateChefReport(@RequestBody Report form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String user_code = form.getUser_code();
			if(StringUtils.isEmpty(user_code) || RegularUtil.isSpecialChar(user_code) || RegularUtil.matchLength(user_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}	
			User user = new User();
			user.setUser_code(user_code);
			user = TokenUserUtil.findByCode(userService, user);
			if(StringUtils.isEmpty(user)) {
				CODEMSG = USER_UNDEFINED;
				return finish(CODEMSG, null);
			}
			String report_code = form.getReport_code();
			if(StringUtils.isEmpty(report_code) || RegularUtil.isSpecialChar(report_code) || RegularUtil.matchLength(report_code,25)) {
				CODEMSG = REPORT_CODE_UNVALID;
				return finish(CODEMSG, null);
			}	
			String province_conduct = form.getProvince_conduct();
			if (StringUtils.isEmpty(province_conduct) || RegularUtil.isSpecialChar(province_conduct)) {
				CODEMSG = PROVINCE_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String city_conduct = form.getCity_conduct();
			if (StringUtils.isEmpty(city_conduct) || RegularUtil.isSpecialChar(city_conduct)) {
				CODEMSG = CITY_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String area_conduct = form.getArea_conduct();
			if (StringUtils.isEmpty(area_conduct) || RegularUtil.isSpecialChar(area_conduct)) {
				CODEMSG = AREA_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String town_conduct = form.getTown_conduct();
			if (StringUtils.isEmpty(town_conduct) || RegularUtil.isSpecialChar(town_conduct)) {
				CODEMSG = TOWN_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String vill_conduct = form.getVill_conduct();
			if (StringUtils.isEmpty(vill_conduct) || RegularUtil.isSpecialChar(vill_conduct)) {
				CODEMSG = VILL_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String address_conduct = form.getAddress_conduct();
			if (StringUtils.isEmpty(address_conduct) || RegularUtil.isSpecialChar(address_conduct)) {
				CODEMSG = ADDRESS_CONDUCT_UNVALID;
				return finish(CODEMSG, null);
			}
			String banquet_time = form.getBanquet_time();
			if (StringUtils.isEmpty(banquet_time) ||(banquet_time.compareTo(DateUtil.getDateYearMonthDate())<0) || !DateUtil.isValidDate(banquet_time,3)) {
				CODEMSG = BANQUET_TIME_UNVALID;
				return finish(CODEMSG, null);
			}
			String banquet_day = form.getBanquet_day();
			if (StringUtils.isEmpty(banquet_day) || !RegularUtil.OneToAnyNumber(banquet_day)) {
				CODEMSG = BANQUET_DAY_UNVALID;
				return finish(CODEMSG, null);
			}
			Integer banquet_type = form.getBanquet_type();
			if (StringUtils.isEmpty(banquet_type) || !RegularUtil.OneToAnyNumber(banquet_type+"")) {
				CODEMSG = BANQUET_TYPE_UNVALID;
				return finish(CODEMSG, null);
			}
			String banquet_people = form.getBanquet_people();
			if (StringUtils.isEmpty(banquet_people) || !RegularUtil.OneToAnyNumber(banquet_people+"")) {
				CODEMSG = BANQUET_PEOPLE_UNVALID;
				return finish(CODEMSG, null);
			}
			
			//计算办宴过期时间
			form.setBanquet_expiretime(DateUtil.calcDate(DateUtil.YYYY_MM_DD_FORMAT,"DATE",banquet_time,Integer.valueOf(CalcUtil.subtr(form.getBanquet_day(),"1"))));
			Integer result = reportService.update(form);
			if(result != 0){
				//1:更新报备场地信息
				ReportProcess reportProcess = form.getReport_process();
				reportProcessService.update(reportProcess);
				//2:删除帮厨后,批量更新
				reportSubChefService.deleteByReport(report_code);
				//3:新增报备帮厨信息
				ReportSubChef reportSubChef = new ReportSubChef();
				List<ReportSubChef> report_sub_chef_list = form.getReport_sub_chef_list();
				if(!StringUtils.isEmpty(report_sub_chef_list) && report_sub_chef_list.size() > 0) {
					reportSubChef.setPager_list(report_sub_chef_list);
					reportSubChefService.saveBatch(reportSubChef);
				}
				CODEMSG = SUCCESS;
			}else{
				map = null;
				CODEMSG = ERROR;
			}
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.error(ex.toString());
		}
		return finish(CODEMSG, map);
	}
	
	
	/**
	 * 管理员、协管员报备审核,检查
	 * @Title: updateReportState
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月16日 下午4:10:53
	 * @param form
	 * @param reportProcess
	 * @return
	 */
	@PutMapping(value = "/state/update")
	public Object updateReportState(@RequestBody Report form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String user_code = form.getUser_code();
			if(StringUtils.isEmpty(user_code) || RegularUtil.isSpecialChar(user_code) || RegularUtil.matchLength(user_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}	
			User user = new User();
			user.setUser_code(user_code);
			user = TokenUserUtil.findByCode(userService, user);
			if(StringUtils.isEmpty(user)) {
				CODEMSG = USER_UNDEFINED;
				return finish(CODEMSG, null);
			}
			String report_code = form.getReport_code();
			if(StringUtils.isEmpty(report_code) || RegularUtil.isSpecialChar(report_code) || RegularUtil.matchLength(report_code,25)) {
				CODEMSG = REPORT_CODE_UNVALID;
				return finish(CODEMSG, null);
			}	
			Integer report_state = form.getReport_state();
			if(report_state != 2 && report_state != 3 && report_state != 4 && report_state != 5) {
				CODEMSG = REPORT_STATE_UNVALID;
				return finish(CODEMSG, null);
			}
			if(report_state == 2 || report_state == 3) {
				form.setUser_code_qualified(user.getUser_code());
				form.setUser_name_qualified(user.getUser_name());
				form.setQualified_time(DateUtil.getCurrentTime());
			}else if (report_state == 4|| report_state == 5) {
				form.setUser_code_check(user.getUser_code());
				form.setUser_name_check(user.getUser_name());
				form.setCheck_time(DateUtil.getCurrentTime());
			}
			Integer result = reportService.update(form);
			if(result != 0){
				if(report_state == 3) {//审核通过,则乡厨办宴次数+1
					Report report = reportService.findByCode(form);
					User chef = new User();
					chef.setUser_code(report.getUser_code_mainchef());
					chef = userService.findByCode(chef);
					chef.setBanquet_count(chef.getBanquet_count()+1);
					userService.update(chef);
				}
				ReportCheck reportCheck = form.getReport_check();
				if (report_state > 3) {
					result = reportCheckService.findByExist(reportCheck);
					if (result > 0){
						reportCheckService.update(reportCheck);
					}else {
						if(StringUtils.isEmpty(reportCheck.getUser_sign_logo())) {
							reportCheck.setUser_sign_logo(null);
						}
						reportCheck.setCheck_code(CodeUtil.getSystemCode("reportCheck"));
						reportCheckService.save(reportCheck);
					}
				}
				CODEMSG = SUCCESS;
			}else{
				map = null;
				CODEMSG = ERROR;
			}
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.error(ex.toString());
		}
		return finish(CODEMSG, map);
	}
	
}
