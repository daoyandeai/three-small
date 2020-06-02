package com.rs.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rs.enums.HttpEnum;
import com.rs.po.Accessory;
import com.rs.po.Advice;
import com.rs.po.Company;
import com.rs.po.Department;
import com.rs.po.Device;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.po.returnPo.CompanyReturn;
import com.rs.po.returnPo.DepartmentReturn;
import com.rs.service.AccessoryService;
import com.rs.service.AdviceService;
import com.rs.service.CompanyService;
import com.rs.service.DepartmentService;
import com.rs.service.DeviceService;
import com.rs.util.CodeUtil;
import com.rs.util.HttpUtil;
import com.rs.util.RegularUtil;
import com.rs.util.TokenUserUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @ClassName: CompanyController
 * @Description: 三小档案控制层
 * @Author tangsh
 * @DateTime 2019年12月31日 下午3:38:07
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/company")
public class CompanyController extends BaseController {

	@Autowired
	private CompanyService companyService;
	@Autowired
	private AccessoryService accessoryService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private AdviceService adviceService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private TokenParam tokenParam;

	private Logger logger = LoggerFactory.getLogger(CompanyController.class);

	/**
	 * 
	 * @Title: detail
	 * @Description: 查询三小档案详情接口
	 * @Author tangsh
	 * @DateTime 2019年12月31日 下午3:38:00
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/detail")
	public Object detail(Company form) {
		CompanyReturn cr = new CompanyReturn();
		try {
			cr = companyService.detail(form);
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG,cr);
	}

	/**
	 * 
	 * @Title: drafts
	 * @Description: 查询草稿箱
	 * @Author tangsh
	 * @DateTime 2019年12月26日 上午10:25:07
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/drafts")
	public Object drafts(Company form) {
		CompanyReturn cr = null;
		try {
			cr = companyService.drafts(form);
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG,cr);
	}

	/**
	 * 
	 * @Title: examine
	 * @Description: 三小档案操作接口（操作状态 examine【1：注销、2：驳回、3：通过】） 
	 * @Author tangsh
	 * @DateTime 2019年12月31日 下午3:38:27
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/examine")
	public Object examine(@RequestBody Company form,HttpServletRequest request) {
		CODEMSG = ERROR;
		try {
			if(StringUtils.isEmpty(form.getExamine())) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null); 
			}
			if("2".equals(form.getExamine())&&StringUtils.isEmpty(form.getUnpass_cause())) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null); 
			}
			
			User user = TokenUserUtil.generateUser(request, tokenParam);
			if(companyService.examine(form,user)){
				CODEMSG = SUCCESS;
			}
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, null);
	}

	/**
	 * 
	 * @Title: save
	 * @Description: 三小档案保存接口
	 * @Author tangsh
	 * @DateTime 2019年12月31日 下午3:39:05
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody Company form,HttpServletRequest request) {
		CODEMSG = ERROR;
		try {
			if(RegularUtil.isSpecialChar(form.getCompany_name())){
				return finish(PARAMETER_ERROR, null);
			}
			if(!RegularUtil.isMobilePhone(form.getMobilephone())){
				return finish(PARAMETER_ERROR, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			if(companyService.saveCompany(form,user)>0){
				CODEMSG = SUCCESS;
			}
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, null);
	}

	/**
	 * 
	 * @Title: change
	 * @Description: 三小档案变更
	 * @Author tangsh
	 * @DateTime 2019年12月31日 下午3:39:13
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/change")
	public Object change(@RequestBody Company form,HttpServletRequest request) {
		CODEMSG = ERROR;
		try {
			if(form.getCompany_code()==null||form.getCompany_code()==""){
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG,null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			if(companyService.change(form,user)){
				CODEMSG = SUCCESS;
			}
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, null);
	}
	
	/**
	 * 
	 * @Title: continueTime
	 * @Description: 延续
	 * @Author tangsh
	 * @DateTime 2020年3月25日 下午3:36:10
	 * @param form
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/continueTime")
	public Object continueTime(@RequestBody Company form,HttpServletRequest request) {
		CODEMSG = ERROR;
		try {
			if(StringUtils.isEmpty(form.getCompany_code())){
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG,null);
			}
			if(StringUtils.isEmpty(form.getUnuseful_time())){
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG,null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			if(companyService.continueTime(form,user)){
				CODEMSG = SUCCESS;
			}
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, null);
	}
	
	
	/**
	 * 
	 * @Title: update
	 * @Description: 更新管理员接口
	 * @Author tangsh
	 * @DateTime 2020年1月4日 上午11:29:27
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/update")
	public Object update(@RequestBody Company form) {
		CODEMSG = ERROR;
		try {
			if(StringUtils.isEmpty(form.getCompany_code())){
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG,null);
			}
			form.setUser_code_manage(RegularUtil.getListToString(form.getUser_code_manage_list(),","));
	        form.setUser_moblephone_manage(RegularUtil.getListToString(form.getUser_moblephone_manage_list(),","));
	        form.setUser_code_info(RegularUtil.getListToString(form.getUser_code_info_list(),","));
	        form.setUser_moblephone_info(RegularUtil.getListToString(form.getUser_moblephone_info_list(),","));
			if(companyService.update(form)>0){
				CODEMSG = SUCCESS;
			}
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, null);
	}
	
	/**
	 * 
	 * @Title: search
	 * @Description: 条件查询三小档案（列表）
	 * @Author tangsh
	 * @DateTime 2019年12月27日 下午3:07:23
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/search")
	public Object search(Company form,HttpServletRequest request) {
		Map<String,Object> map = new HashMap<>();
		try {
			User user = TokenUserUtil.generateUser(request, tokenParam);
			map.put("pager_count",companyService.findByUserCompanyCount(form,user));
			map.put("companyList",companyService.findByUserCompanyList(form,user));
			//即将过期
			form.setReturncode("1");//filing_state>2
			form.setState(4);
			map.put("will_expire",companyService.findByUserCompanyCount(form,user));
			//已过期
			form.setState(2);
			map.put("expired",companyService.findByUserCompanyCount(form,user));
			
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, map);
	}
	
	
	/**
	 * 查询企业
	 * @Title: single
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月23日 下午6:19:44
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/single")
	public Object single(Company form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String company_code = form.getCompany_code();
			if(StringUtils.isEmpty(company_code) || RegularUtil.matchLength(company_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			form = companyService.findByCode(form);
			if(!StringUtils.isEmpty(form)) {
				map.put("company_type", form.getCompanytag_code());
				
				/*List<String> accessoryList = new ArrayList<String>();
				Accessory accessory = new Accessory();
				accessory.setCompany_code(company_code);
				
				//营业执照
				accessory.setAccessory_type("营业执照副本");
				getAccessoryUrl(accessory,accessoryList);
				map.put("certificate_imgs", accessoryList);
				//健康证
				accessoryList = new ArrayList<String>();
				accessory.setAccessory_type("健康证");
				getAccessoryUrl(accessory,accessoryList);
				map.put("health_certificate_imgs", accessoryList);*/
				
				// 查询企业所有上传附件
				Accessory accessory = new Accessory();
				accessory.setCompany_code(company_code);
				map.put("accessory_list", accessoryService.findByAll_app(accessory));
				
				//直播视频列表
				Device device = new Device();
				device.setDevice_state(1);
				device.setCompany_code(company_code);
				List<Device> dList = deviceService.findByAll(device);
				List<Object> drList = new ArrayList<Object>();
				Map<Object,Object> dr = null;
				int device_type = 1;
				for (Device d : dList) {
					dr = new HashMap<Object,Object>();
					dr.put("device_code", d.getDevice_code());
					dr.put("device_id", d.getDevice_id());
					dr.put("device_name", d.getDevice_name());
					dr.put("device_number", d.getDevice_number());
					dr.put("device_serial", d.getDevice_serial());
					dr.put("device_desc", d.getDevice_desc());
					dr.put("device_type", d.getDevice_type());
					dr.put("company_area_code", d.getCompany_area_code());
					dr.put("company_area_name", d.getCompany_area_name());
					device_type = d.getDevice_type();
					dr.put("device_type", device_type);
					//如果device_type=1,需要请求阿里接口返回播放地址
					if(device_type == 1) {
						analysisVideo(dr,d);
					}else {
						dr.put("play_video_url", d.getPlay_video_url());
					}
					drList.add(dr);
				}
				map.put("live_cams", drList);
			}
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
	 * 读取企业附件
	 * @Title: getAccessoryUrl
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月23日 下午6:19:58
	 * @param accessory
	 * @param accessoryList
	 */
	/*private void getAccessoryUrl(Accessory accessory,List<String> accessoryList) {
		accessory = accessoryService.findByOne(accessory);
		if(!StringUtils.isEmpty(accessory)) {
			accessoryList.add(accessory.getAccessory_url());
		}
	}*/
	
	/**
	 * 解析直播+抓拍播放地址
	 * @Title: analysisVideo
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月31日 下午3:51:41
	 * @param map
	 * @param device_id
	 * @param device_number
	 * @param device_name
	 * @throws InterruptedException
	 */
	void analysisVideo(Map<Object, Object> map,Device form){
		try {
			// 请求阿里直播视频源
			// 3秒进行循环,如果有响应,则退出,反复请求3次,如果一直无响应,则返回
			for (int i = 1; i <= 3; i++) {
				String result = HttpUtil.sendGet(
						tokenParam.getBos_url() + tokenParam.getBos_video_uri() + "?siteId=" + form.getDevice_id()
								+ "&areaId=" + form.getDevice_number() + "&areaName=" + form.getDevice_name(),
						HttpEnum.UTF8.getValue(), null);
				if (!StringUtils.isEmpty(result)) {
					JSONObject jsonObject = JSON.parseObject(result);
					String video_url = jsonObject.getJSONObject("data").getString("videoUrl");
					map.put("play_video_url", video_url);
					CODEMSG = SUCCESS;
					break;
				} else {
					logger.error("请求抓拍视频地址错误：{}",result);
					CODEMSG = ERROR;
				}
				Thread.sleep(3000);
			}
		} catch (Exception ex) {
			map.put("play_video_url", "");
		}
	}
	
	/**
	 * 抓拍
	 * @Title: capture
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月26日 上午10:07:35
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/ali/video/capture")
	public Object capture(Device form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String device_number = form.getDevice_number();
			if(StringUtils.isEmpty(device_number) || RegularUtil.isSpecialChar(device_number)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String result = HttpUtil.sendGet(tokenParam.getBos_url()+tokenParam.getBos_capture_pic()+"?areaId="+device_number, HttpEnum.UTF8.getValue(), null);
			if(!StringUtils.isEmpty(result)) {
				JSONObject jsonObject = JSON.parseObject(result);
				String pic_url = jsonObject.getJSONObject("data").getString("picUrl");
				map.put("pic_url", pic_url);
				CODEMSG = SUCCESS;
			}else {
				CODEMSG = ERROR;
			}
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, map);
	}
	
	/**
	 * 
	 * @Title: cflist
	 * @Description: 条件查询三小溯源企业（列表）
	 * @Author tangsh
	 * @DateTime 2019年12月30日 下午2:10:32
	 * @param form
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/cflist")
	public Object cflist(Company form,HttpServletRequest request) {
		Map<String,Object> map = new HashMap<>();
		try {
			User user = TokenUserUtil.generateUser(request, tokenParam);
			map.put("pager_count",companyService.findByUserCompanyFCCount(form,user));
			map.put("companyList",companyService.findByUserCompanyFCList(form,user));
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, map);
	}
	
	/**
	 * 新增执法
	 * 
	 * @Title: saveAdvice
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年12月26日 上午11:06:05
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/advice/save")
	public Object saveAdvice(@RequestBody Advice form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String company_code = form.getCompany_code();
			if (StringUtils.isEmpty(company_code) || RegularUtil.matchLength(company_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			Integer type = form.getType();
			if (!RegularUtil.isNumber(type + "")) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if (type == 2) {
				String device_code = form.getDevice_code();
				if (StringUtils.isEmpty(device_code) || RegularUtil.matchLength(device_code, 25)) {
					CODEMSG = PARAMETER_ERROR;
					return finish(CODEMSG, null);
				}
			}
			Integer advice_rate = form.getAdvice_rate();
			if (!RegularUtil.isNumber(advice_rate + "")) {
				CODEMSG = ADVICE_RATE_UNVALID;
				return finish(CODEMSG, null);
			}
			Integer advice_type = form.getAdvice_type();
			if (!RegularUtil.isNumber(advice_type + "")) {
				CODEMSG = ADVICE_TYPE_UNVALID;
				return finish(CODEMSG, null);
			}
			form.setAdvice_code(CodeUtil.getSystemCode("advice"));
			form.setImgs_url(JSON.toJSONString(form.getImgs_url()));
			int result = adviceService.save(form);
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
			logger.info(ex.toString());
		}
		return finish(CODEMSG, map);
	}
	
	/**
	 * 
	 * @Title: blacklist
	 * @Description: 企业黑白名单接口
	 * @Author tangsh
	 * @DateTime 2020年2月10日 下午4:05:08
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/blacklist")
	public Object updateblacklist(@RequestBody Company form,HttpServletRequest request) {
		CODEMSG = ERROR;
		try {
			if (StringUtils.isEmpty(form.getCompany_code()) || RegularUtil.matchLength(form.getCompany_code(), 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(StringUtils.isEmpty(form.getIsblacklist())) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			if(companyService.updateBlacklist(form,user)){
				CODEMSG = SUCCESS;
			}
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, null);
	}
	
	/**
	 * 餐厨垃圾企业列表
	 * @Title: search
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月9日 下午3:25:26
	 * @param form
	 * @param request
	 * @return
	 */
	@GetMapping(value = "cclj/list")
	public Object ccljList(Company form,HttpServletRequest request) {
		Map<String,Object> map = new HashMap<>();
		try {
			if(!StringUtils.isEmpty(form.getCclj_time_last())) {
				form.setCclj_time_last(form.getCclj_time_last()+" 00:00:00");
			}
			if(!StringUtils.isEmpty(form.getCclj_time_last_end())) {
				form.setCclj_time_last_end(form.getCclj_time_last_end()+" 23:59:59");
			}
			
			User user = TokenUserUtil.generateUser(request, tokenParam);
			map.put("pager_count",companyService.findByUserCompanyCount(form,user));
			map.put("company_list",companyService.findCandCCLJList(form,user));
			
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, map);
	}
	
	
	/**
	 * 根据地址逆解析坐标
	 * @Title: geocoder
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月24日 上午11:16:40
	 * @param form
	 * @param request
	 * @return
	 */
	@GetMapping(value = "geocoder")
	public Object geocoder(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<>();
		try {
			boolean flag = companyService.geocoder();
			if(flag) {
				CODEMSG = SUCCESS;
			}else {
				CODEMSG = ERROR;
			}
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, map);
	}
	
	/**
	 * 分页查询部门列表
	 * 管理员登录,如果自己在监管部门,则查询对应的监管部门,如果不在则根据区域查询
	 * 网格员登录,则根据乡/镇区域匹配
	 * @Title: list
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年12月30日 上午9:50:39
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/department/list")
	public Object departmentList(Department form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String department_name = form.getDepartment_name();
			if (!StringUtils.isEmpty(department_name) && RegularUtil.isSpecialChar(department_name)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			form.setState(1);
			User user = TokenUserUtil.generateUser(request, tokenParam);
			
			//平台管理员
			String user_type = user.getUser_type();
			List<DepartmentReturn> drList = null;
			if("平台管理员".equals(user_type)) {
				form.setUser_code(user.getUser_code());
				drList = departmentService.findAllByUserCode(form);
				if(!StringUtils.isEmpty(drList) && drList.size() > 0) {
					map.put("department_list", drList);
				}else {
					drList = findDepartment(form,user);
					map.put("department_list", drList);
				}
			}else if("协管员".equals(user_type)) {
				drList = findDepartment(form,user);
				map.put("department_list", drList);
			}
			
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, map);
	}
	
	/**
	 * 权限区域查询
	 * @Title: search
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年5月29日 上午10:03:26
	 * @param form
	 * @param user
	 * @param drList
	 */
	private List<DepartmentReturn> findDepartment(Department form,User user) {
		//部门所在区域
		String province = form.getProvince();
		String city = form.getCity();
		String area = form.getArea();
		String town = form.getTown();
		String addrInfo = "";
		//登录用户区域
		String user_province = user.getUser_province();
		String user_city = user.getUser_city();
		String user_area = user.getUser_area();
		String user_town = user.getUser_town(); 
		
		if(StringUtils.isEmpty(province)) {
			form.setProvince(user_province);
			addrInfo = addrInfo+(StringUtils.isEmpty(user_province)?"":user_province);
		}else {
			addrInfo = addrInfo + province;
		}
		
		if(StringUtils.isEmpty(city)) {
			form.setCity(user_city);
			addrInfo = addrInfo+(StringUtils.isEmpty(user_city)?"":user_city);
		}else {
			addrInfo = addrInfo + city;
		}
		
		if(StringUtils.isEmpty(area)) {
			form.setArea(user_area);
			addrInfo = addrInfo+(StringUtils.isEmpty(user_area)?"":user_area);
		}else {
			addrInfo = addrInfo + area;
		}
		
		if(StringUtils.isEmpty(town)) {
			form.setTown(user_town);
			addrInfo = addrInfo+(StringUtils.isEmpty(user_town)?"":user_town);
		}else {
			addrInfo = addrInfo + town;
		}
		form.setAddr_info(addrInfo);
		return departmentService.findAll_app(form);
	}
}
