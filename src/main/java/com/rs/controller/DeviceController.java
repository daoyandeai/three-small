package com.rs.controller;
import com.rs.po.Accessory;
import com.rs.po.Advice;
import com.rs.po.Company;
import com.rs.po.CompanyTag;
import com.rs.po.CompanyType;
import com.rs.po.Device;
import com.rs.po.Sensor;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.po.returnPo.CompanyReturn;
import com.rs.po.returnPo.DeviceReturn;
import com.rs.service.AccessoryService;
import com.rs.service.AdviceService;
import com.rs.service.CompanyService;
import com.rs.service.CompanyTagService;
import com.rs.service.CompanyTypeService;
import com.rs.service.DeviceService;
import com.rs.service.SensorService;
import com.rs.util.CodeUtil;
import com.rs.util.RegularUtil;
import com.rs.util.TokenUserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
/**
 * 摄像头控制层
 * @ClassName: DeviceController
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月23日 下午5:41:04
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/device")
public class DeviceController extends BaseController {

	@Autowired
	private DeviceService deviceService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CompanyTypeService companyTypeService;
	@Autowired
	private CompanyTagService companyTagService;
	@Autowired
	private AdviceService adviceService;
	@Autowired
	private AccessoryService accessoryService;
	@Autowired
	private TokenParam tokenParam;
	@Autowired
	private SensorService sensorService;
	
	private Logger logger = LoggerFactory.getLogger(DeviceController.class);

	/**
	 * 查询摄像头列表
	 * @Title: list
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月23日 下午5:43:20
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(Device form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String device_name = form.getDevice_name();
			if(!StringUtils.isEmpty(device_name) && RegularUtil.isSpecialChar(device_name)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			int pager_offset = form.getPager_offset();
			if(StringUtils.isEmpty(pager_offset) || !RegularUtil.isNumber(pager_offset+"")) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int pager_openset = form.getPager_openset();
			if(StringUtils.isEmpty(pager_openset) || !RegularUtil.isNumber(pager_openset+"")) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			Integer device_state = form.getDevice_state();
			if(!StringUtils.isEmpty(device_state) && !RegularUtil.isNumber(device_state+"")) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			map.put("pager_count", deviceService.findByCount(form));
			List<Device> dList = deviceService.findByList(form);
			List<DeviceReturn> drList = new ArrayList<DeviceReturn>();
			DeviceReturn dr = null;
			for (Device d : dList) {
				dr = new DeviceReturn();
				BeanUtils.copyProperties(d, dr);
				drList.add(dr);
			}
			map.put("device_list",drList);
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
	 * 添加摄像头
	 * @Title: save
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月23日 下午6:15:56
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody Device form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String device_name = form.getDevice_name();
			if(StringUtils.isEmpty(device_name) || RegularUtil.trimZero(device_name) || RegularUtil.isSpecialChar(device_name)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String device_number = form.getDevice_number();
			if(StringUtils.isEmpty(device_number) || RegularUtil.trimZero(device_number) || RegularUtil.isSpecialChar(device_number)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int device_type = form.getDevice_type();
			if(!RegularUtil.isNumber(device_type+"")) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String company_code = form.getCompany_code();
			if(StringUtils.isEmpty(company_code) || RegularUtil.matchLength(company_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String company_name = form.getCompany_name();
			if(StringUtils.isEmpty(company_name) || !RegularUtil.isLength(company_name, 1, 50)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String company_area_code = form.getCompany_area_code();
			if(StringUtils.isEmpty(company_area_code) || RegularUtil.matchLength(company_area_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String company_area_name = form.getCompany_area_name();
			if(StringUtils.isEmpty(company_area_name) || !RegularUtil.isLength(company_area_name, 1, 50)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String device_desc = form.getDevice_desc();
			if(StringUtils.isEmpty(device_desc) || RegularUtil.trimZero(device_desc) || RegularUtil.isSpecialChar(device_desc)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			//如果该区域下已经有摄像头,则返回
			Device device = new Device();
			device.setCompany_area_code(company_area_code);
			int count = deviceService.findByExist(device);
			if (count > 0) {
				CODEMSG = DEVICE_BIND_EXIST;
				return finish(CODEMSG, null);
			}
			//如果摄像头已存在,则返回
			device.setCompany_area_code(null);
			device.setDevice_number(device_number);
			count = deviceService.findByExist(device);
			if (count > 0) {
				CODEMSG = DEVICE_NUMBER_EXIST;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_code_add(user.getUser_code());
			form.setUser_name_add(user.getUser_name());
			form.setDevice_code(CodeUtil.getSystemCode("device"));
			int result = deviceService.save(form);
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
	 * 查询摄像头
	 * @Title: single
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月23日 下午6:15:50
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/single")
	public Object single(Device form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String device_code = form.getDevice_code();
			if(StringUtils.isEmpty(device_code) || RegularUtil.matchLength(device_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			
			form = deviceService.findByCode(form);
			if(!StringUtils.isEmpty(form)) {
				DeviceReturn dr = new DeviceReturn();
				BeanUtils.copyProperties(form, dr);
				map.put("device", dr);
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
	 * 编辑摄像头
	 * @Title: save
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月23日 下午6:15:56
	 * @param form
	 * @return
	 */
	@PutMapping(value = "/update")
	public Object update(@RequestBody Device form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String device_code = form.getDevice_code();
			if(StringUtils.isEmpty(device_code) || RegularUtil.matchLength(device_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String device_name = form.getDevice_name();
			if(StringUtils.isEmpty(device_name) || RegularUtil.trimZero(device_name) || RegularUtil.isSpecialChar(device_name)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String device_number = form.getDevice_number();
			if(StringUtils.isEmpty(device_number) || RegularUtil.trimZero(device_number) || RegularUtil.isSpecialChar(device_number)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int device_type = form.getDevice_type();
			if(!RegularUtil.isNumber(device_type+"")) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String company_code = form.getCompany_code();
			if(StringUtils.isEmpty(company_code) || RegularUtil.matchLength(company_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String company_name = form.getCompany_name();
			if(StringUtils.isEmpty(company_name) || !RegularUtil.isLength(company_name, 1, 50)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String company_area_code = form.getCompany_area_code();
			if(StringUtils.isEmpty(company_area_code) || RegularUtil.matchLength(company_area_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String company_area_name = form.getCompany_area_name();
			if(StringUtils.isEmpty(company_area_name) || !RegularUtil.isLength(company_area_name, 1, 50)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String device_desc = form.getDevice_desc();
			if(StringUtils.isEmpty(device_desc) || RegularUtil.trimZero(device_desc) || RegularUtil.isSpecialChar(device_desc)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			//如果该区域下已经有摄像头,则返回
			Device device = new Device();
			device.setDevice_code(device_code);
			device.setCompany_area_code(company_area_code);
			int count = deviceService.findByExist(device);
			if (count > 0) {
				CODEMSG = DEVICE_BIND_EXIST;
				return finish(CODEMSG, null);
			}
			//如果摄像头已存在,则返回
			device.setCompany_area_code(null);
			device.setDevice_number(device_number);
			count = deviceService.findByExist(device);
			if (count > 0) {
				CODEMSG = DEVICE_NUMBER_EXIST;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_code_update(user.getUser_code());
			form.setUser_name_update(user.getUser_name());
			int result = deviceService.update(form);
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
	 * 用于摄像头  查询企业列表
	 * @Title: list
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月23日 上午10:58:48
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/company/list")
	public Object choose(Company form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			int pager_offset = form.getPager_offset();
			int pager_openset = form.getPager_openset();
			if(StringUtils.isEmpty(pager_offset) || !RegularUtil.isNumber(pager_offset+"")) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(StringUtils.isEmpty(pager_openset) || !RegularUtil.isNumber(pager_openset+"")) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			//根据登陆用户权限查询对应企业列表
			//form.setBusiness_state(1);
			User user = TokenUserUtil.generateUser(request, tokenParam);
			map.put("pager_count", companyService.findByUserCompanyCount(form,user));
			List<CompanyReturn> cList = companyService.findByUserCompanyList(form,user);
			//返回封装对象集合
			List<Object> crList = new ArrayList<Object>();
			List<Object> acList = null;
			//返回封装对象
			Map<String, Object> m_company = null;
			Map<String, Object> m_accessory = null;
			CompanyType cType = null;
			CompanyTag cTag = null;
			Advice advice = null;
			Accessory accessory = null;
			List<Accessory> accessory_list = null;
			Device device = null;
			Sensor sensor = null;
			String company_code = "";
			for (CompanyReturn company : cList) {
				m_company = new HashMap<String, Object>();
				company_code = company.getCompany_code();
				m_company.put("company_code", company_code);
				m_company.put("company_name", company.getCompany_name());
				m_company.put("mobile_phone", company.getMobilephone());
				m_company.put("company_address", company.getProvince()+company.getCity()+company.getArea()+company.getTown()+company.getVill()+(StringUtils.isEmpty(company.getContact_address())?"":company.getContact_address()));
				m_company.put("credit_code", company.getCredit_code());
				m_company.put("record_code", company.getRecord_code());
				m_company.put("operator", company.getOperator());
				//最近一次评价等级
				advice = adviceService.findLastByCompany(company.getCompany_code());
				if(StringUtils.isEmpty(advice)) {
					m_company.put("advice_rate",null);
				}else {
					m_company.put("advice_rate",advice.getAdvice_rate());
				}
				cType = new CompanyType();
				cType.setCompanytype_code(company.getCompanytype_code());
				m_company.put("companytype_name", companyTypeService.findByCode(cType).getCompanytype_name());
				cTag = new CompanyTag();
				cTag.setCompanytag_code(company.getCompanytag_code());
				m_company.put("companytag_name", companyTagService.findByCode(cTag).getCompanytag_name());
				m_company.put("longitude", company.getLongitude());
				m_company.put("latitude", company.getLatitude());
				m_company.put("state", company.getState());
				m_company.put("business_type", company.getBusiness_type());
				m_company.put("filing_state", company.getFiling_state());
				m_company.put("examine", company.getExamine());
				//查询附件
				acList = new ArrayList<>();
				accessory = new Accessory();
				accessory.setCompany_code(company.getCompany_code());
				accessory_list = accessoryService.findByAll(accessory);
				if(!StringUtils.isEmpty(accessory_list) && accessory_list.size() > 0) {
					for (Accessory acce : accessory_list) {
						m_accessory = new HashMap<String, Object>();
						m_accessory.put("accessory_type", acce.getAccessory_type());
						m_accessory.put("accessory_url", acce.getAccessory_url());
						acList.add(m_accessory);
					}
				}
				m_company.put("accessory_list", acList);
				//摄像头
				device = new Device();
				device.setCompany_code(company_code);
				m_company.put("device_count", deviceService.findByCount(device));
				//传感器
				sensor = new Sensor();
				sensor.setCompany_code(company_code);
				m_company.put("sensor_count", sensorService.findByCount(sensor));
				crList.add(m_company);
			}
			map.put("company_list",crList);
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
	 * 更新摄像头状态
	 * @Title: updateState
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月24日 下午6:26:12
	 * @param form
	 * @return
	 */
	@PutMapping(value="/state/update")
	public Object updateState(@RequestBody Device form){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String device_code = form.getDevice_code();
			int device_state = form.getDevice_state();
			if(StringUtils.isEmpty(device_code) || RegularUtil.isSpecialChar(device_code) || RegularUtil.matchLength(device_code,25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(!(device_state == 1 || device_state == 2)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int result = deviceService.updateStatus(form);
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
	 * 根据区域查询监控
	 * @Title: findByArea
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月17日 下午2:55:19
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/area")
	public Object findByArea(Device form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String company_area_code = form.getCompany_area_code();
			if(StringUtils.isEmpty(company_area_code) || RegularUtil.matchLength(company_area_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			DeviceReturn dr = deviceService.findByArea(company_area_code);
			if(!StringUtils.isEmpty(dr)) {
				map.put("device", dr);
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
}
