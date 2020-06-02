package com.rs.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rs.enums.HttpEnum;
import com.rs.po.Accessory;
import com.rs.po.Advice;
import com.rs.po.Company;
import com.rs.po.Device;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.po.returnPo.CompanyReturn;
import com.rs.service.AccessoryService;
import com.rs.service.AdviceService;
import com.rs.service.CompanyService;
import com.rs.service.DeviceService;
import com.rs.service.UserService;
import com.rs.util.CodeUtil;
import com.rs.util.HttpUtil;
import com.rs.util.RegularUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 餐食安平台对接控制层
 * 
 * @ClassName: CSACompanyController
 * @Description:
 * @Author sven
 * @DateTime 2019年12月23日 上午10:58:57
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/app/company")
public class CSACompanyController extends BaseController {

	@Autowired
	private CompanyService companyService;
	@Autowired
	private AccessoryService accessoryService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private AdviceService adviceService;
	@Autowired
	private TokenParam tokenParam;
	@Autowired
	private UserService userService;

	private Logger logger = LoggerFactory.getLogger(CSACompanyController.class);

	/**
	 * 查询企业列表
	 * 
	 * @Title: list
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年12月23日 上午10:58:48
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(Company form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			int pager_offset = form.getPager_offset();
			if (StringUtils.isEmpty(pager_offset) || !RegularUtil.isNumber(pager_offset + "")) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int pager_openset = form.getPager_openset();
			if (StringUtils.isEmpty(pager_openset) || !RegularUtil.isNumber(pager_openset + "")) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String user_code = form.getUser_code();
			if (StringUtils.isEmpty(user_code) || RegularUtil.matchLength(user_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			// 后续根据登陆用户权限查询对应企业列表
			User user = new User();
			user.setUser_code(user_code);
			user = userService.findByCode(user);
			
			form.setQuery_param("state not equal 3");
			map.put("pager_count", companyService.findByUserCompanyCount(form, user));
			List<CompanyReturn> companyList = companyService.findByUserCompanyList(form, user);
			List<Object> cList = new ArrayList<Object>();
			Map<String, Object> m = null;
			Accessory accessory = null;
			String company_code = null;
			for (CompanyReturn company : companyList) {
				m = new HashMap<String, Object>();
				company_code = company.getCompany_code();
				m.put("id", company_code);
				m.put("company_name", company.getCompany_name());
				m.put("company_address", company.getProvince() + company.getCity() + company.getArea()
						+ company.getTown() + company.getVill() + company.getContact_address());
				accessory = new Accessory();
				accessory.setCompany_code(company_code);
				accessory.setAccessory_type("营业执照");
				accessory = accessoryService.findByOne(accessory);
				if (!StringUtils.isEmpty(accessory)) {
					m.put("logo_img", accessory.getAccessory_url());
				} else {
					m.put("logo_img", "");
				}
				cList.add(m);
			}
			map.put("company_list", cList);
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
	 * 查询企业
	 * 
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
			if (StringUtils.isEmpty(company_code) || RegularUtil.matchLength(company_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			form = companyService.findByCode(form);
			if (!StringUtils.isEmpty(form)) {
				map.put("company_type", form.getCompanytag_code());
				
				
				// 查询企业所有上传附件
				Accessory accessory = new Accessory();
				accessory.setCompany_code(company_code);
				map.put("accessory_list", accessoryService.findByAll_app(accessory));
				
				// 直播视频列表
				Device device = new Device();
				device.setDevice_state(1);
				device.setCompany_code(company_code);
				List<Device> dList = deviceService.findByAll(device);
				List<Object> drList = new ArrayList<Object>();
				Map<Object, Object> dr = null;
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
	 * 
	 * @Title: getAccessoryUrl
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年12月23日 下午6:19:58
	 * @param accessory
	 * @param accessoryList
	 */
	/*private void getAccessoryUrl(Accessory accessory, List<String> accessoryList) {
		accessory = accessoryService.findByOne(accessory);
		if (!StringUtils.isEmpty(accessory)) {
			accessoryList.add(accessory.getAccessory_url());
		}
	}*/

	/**
	 * 如果是阿里的直播,则需要请求阿里直播地址
	 * 
	 * @Title: aLiPlay
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年12月24日 下午4:25:33
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/ali/video")
	public Object aLiPlay(Device form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String device_id = form.getDevice_id();
			if (StringUtils.isEmpty(device_id) || RegularUtil.isSpecialChar(device_id)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String device_number = form.getDevice_number();
			if (StringUtils.isEmpty(device_number) || RegularUtil.isSpecialChar(device_number)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String device_name = form.getDevice_name();
			if (StringUtils.isEmpty(device_name) || RegularUtil.isSpecialChar(device_name)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}

			analysisVideo(map, form);

		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, map);
	}

	/**
	 * 解析直播+抓拍播放地址
	 * 
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
	void analysisVideo(Map<Object, Object> map, Device form) throws InterruptedException {
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
	 * 
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
			if (StringUtils.isEmpty(device_number) || RegularUtil.isSpecialChar(device_number)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String result = HttpUtil.sendGet(
					tokenParam.getBos_url() + tokenParam.getBos_capture_pic() + "?areaId=" + device_number,
					HttpEnum.UTF8.getValue(), null);
			if (!StringUtils.isEmpty(result)) {
				JSONObject jsonObject = JSON.parseObject(result);
				String pic_url = jsonObject.getJSONObject("data").getString("picUrl");
				map.put("pic_url", pic_url);
				CODEMSG = SUCCESS;
			} else {
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
	 * 留影回放
	 * 
	 * @Title: review
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年12月27日 下午3:45:06
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/ali/pics/review")
	public Object review(Device form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String img_index = form.getImg_index();
			/*
			 * if(StringUtils.isEmpty(img_index)) { CODEMSG = PARAMETER_ERROR; return
			 * finish(CODEMSG, null); }
			 */
			String first_post = form.getFirst_post();
			/*
			 * if(StringUtils.isEmpty(first_post)) { CODEMSG = PARAMETER_ERROR; return
			 * finish(CODEMSG, null); }
			 */
			String device_id = form.getDevice_id();
			if (StringUtils.isEmpty(device_id)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String device_serial = form.getDevice_serial();
			if (StringUtils.isEmpty(device_serial)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String time = form.getTime();
			if (StringUtils.isEmpty(time)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String param = "imgIndex=" + img_index + "&firstPost=" + first_post + "&siteId=" + device_id
					+ "&equipmentId=" + device_serial;
			// 时间有空格,需要编码
			time = URLEncoder.encode(time, HttpEnum.UTF8.getValue());
			param = param + "&time=" + time;
			String result = HttpUtil.sendGet(tokenParam.getBos_url() + tokenParam.getBos_review_pics() + "?" + param,
					HttpEnum.UTF8.getValue(), null);
			if (!StringUtils.isEmpty(result)) {
				JSONObject jsonObject = JSON.parseObject(result);
				JSONArray pic_urls = jsonObject.getJSONObject("data").getJSONArray("results");
				map.put("pic_urls", pic_urls);
				CODEMSG = SUCCESS;
			} else {
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
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			Integer advice_type = form.getAdvice_type();
			if (!RegularUtil.isNumber(advice_type + "")) {
				CODEMSG = PARAMETER_ERROR;
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

}
