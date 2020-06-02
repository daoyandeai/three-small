package com.rs.controller;
import com.alibaba.fastjson.JSON;
import com.rs.po.Company;
import com.rs.po.CompanyTag;
import com.rs.po.CompanyType;
import com.rs.po.Sensor;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.po.returnPo.CompanyReturn;
import com.rs.po.returnPo.SensorReturn;
import com.rs.service.CompanyService;
import com.rs.service.CompanyTagService;
import com.rs.service.CompanyTypeService;
import com.rs.service.SensorLogService;
import com.rs.service.SensorService;
import com.rs.util.CodeUtil;
import com.rs.util.RegularUtil;
import com.rs.util.TokenUserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
 * 传感器控制层
 * @ClassName: SensorController
 * @Description: 
 * @Author sven
 * @DateTime 2020年1月14日 上午11:54:11
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/sensor")
public class SensorController extends BaseController {

	@Autowired
	private SensorService sensorService;
	@Autowired
	private SensorLogService sensorLogService;
	@Autowired
	private TokenParam tokenParam;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CompanyTypeService companyTypeService;
	@Autowired
	private CompanyTagService companyTagService;
	
	private Logger logger = LoggerFactory.getLogger(SensorController.class);

	/**
	 * 查询传感器列表
	 * @Title: list
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月23日 下午5:43:20
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(Sensor form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String sensor_name = form.getSensor_name();
			if(!StringUtils.isEmpty(sensor_name) && RegularUtil.isSpecialChar(sensor_name)) {
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
			Integer sensor_state = form.getSensor_state();
			if(!StringUtils.isEmpty(sensor_state) && !RegularUtil.isNumber(sensor_state+"")) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			map.put("pager_count", sensorService.findByCount(form));
			List<Sensor> dList = sensorService.findByList(form);
			List<SensorReturn> srList = new ArrayList<SensorReturn>();
			SensorReturn sr = null;
			for (Sensor d : dList) {
				sr = new SensorReturn();
				BeanUtils.copyProperties(d, sr);
				sr.setThreshold_content(JSON.parse(String.valueOf(sr.getThreshold_content())));
				srList.add(sr);
			}
			map.put("sensor_list",srList);
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
	 * 添加传感器
	 * @Title: save
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月23日 下午6:15:56
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody Sensor form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String company_code = form.getCompany_code();
			if(StringUtils.isEmpty(company_code) || RegularUtil.matchLength(company_code, 25)) {
				CODEMSG = COMPANY_CODE_UNVALID;
				return finish(CODEMSG, null);
			}
			String company_name = form.getCompany_name();
			if(StringUtils.isEmpty(company_name) || !RegularUtil.isLength(company_name, 1, 50)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String company_area_code = form.getCompany_area_code();
			if(StringUtils.isEmpty(company_area_code) || RegularUtil.matchLength(company_area_code, 25)) {
				CODEMSG = COMPANY_AREA_CODE_UNVALID;
				return finish(CODEMSG, null);
			}
			String company_area_name = form.getCompany_area_name();
			if(StringUtils.isEmpty(company_area_name) || !RegularUtil.isLength(company_area_name, 1, 50)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String sensor_name = form.getSensor_name();
			if(StringUtils.isEmpty(sensor_name) || RegularUtil.trimZero(sensor_name) || RegularUtil.isSpecialChar(sensor_name)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			String sensor_number = form.getSensor_number();
			if(StringUtils.isEmpty(sensor_number) || RegularUtil.trimZero(sensor_number) || RegularUtil.isSpecialChar(sensor_number)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			String sensor_desc = form.getSensor_desc();
			if(StringUtils.isEmpty(sensor_desc) || RegularUtil.trimZero(sensor_desc) || RegularUtil.isSpecialChar(sensor_desc)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			//如果该区域下已经有传感器,则返回
			Sensor sensor = new Sensor();
			sensor.setCompany_area_code(company_area_code);
			int count = sensorService.findByExist(sensor);
			if (count > 0) {
				CODEMSG = SENSOR_BIND_EXIST;
				return finish(CODEMSG, null);
			}
			//如果传感器已存在,则返回
			sensor.setCompany_area_code(null);
			sensor.setSensor_number(sensor_number);
			count = sensorService.findByExist(sensor);
			if (count > 0) {
				CODEMSG = SENSOR_NUMBER_EXIST;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_code_add(user.getUser_code());
			form.setUser_name_add(user.getUser_name());
			form.setSensor_code(CodeUtil.getSystemCode("sensor"));
			int result = sensorService.save(form);
			if (result > 0) {
				syncSensor(form);
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
	 * 查询传感器
	 * @Title: single
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月23日 下午6:15:50
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/single")
	public Object single(Sensor form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String sensor_code = form.getSensor_code();
			if(StringUtils.isEmpty(sensor_code) || RegularUtil.matchLength(sensor_code, 25)) {
				CODEMSG = SENSOR_CODE_UNVALID;
				return finish(CODEMSG, null);
			}
			
			form = sensorService.findByCode(form);
			if(!StringUtils.isEmpty(form)) {
				SensorReturn sr = new SensorReturn();
				BeanUtils.copyProperties(form, sr);
				sr.setThreshold_content(JSON.parse(String.valueOf(sr.getThreshold_content())));
				map.put("sensor", sr);
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
	 * 编辑传感器
	 * @Title: save
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月23日 下午6:15:56
	 * @param form
	 * @return
	 */
	@PutMapping(value = "/update")
	public Object update(@RequestBody Sensor form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String sensor_code = form.getSensor_code();
			if(StringUtils.isEmpty(sensor_code) || RegularUtil.matchLength(sensor_code, 25)) {
				CODEMSG = SENSOR_CODE_UNVALID;
				return finish(CODEMSG, null);
			}
			String company_code = form.getCompany_code();
			if(StringUtils.isEmpty(company_code) || RegularUtil.matchLength(company_code, 25)) {
				CODEMSG = COMPANY_CODE_UNVALID;
				return finish(CODEMSG, null);
			}
			String company_name = form.getCompany_name();
			if(StringUtils.isEmpty(company_name) || !RegularUtil.isLength(company_name, 1, 50)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String company_area_code = form.getCompany_area_code();
			if(StringUtils.isEmpty(company_area_code) || RegularUtil.matchLength(company_area_code, 25)) {
				CODEMSG = COMPANY_AREA_CODE_UNVALID;
				return finish(CODEMSG, null);
			}
			String company_area_name = form.getCompany_area_name();
			if(StringUtils.isEmpty(company_area_name) || !RegularUtil.isLength(company_area_name, 1, 50)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String sensor_name = form.getSensor_name();
			if(StringUtils.isEmpty(sensor_name) || RegularUtil.trimZero(sensor_name) || RegularUtil.isSpecialChar(sensor_name)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			String sensor_number = form.getSensor_number();
			if(StringUtils.isEmpty(sensor_number) || RegularUtil.trimZero(sensor_number) || RegularUtil.isSpecialChar(sensor_number)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			String sensor_desc = form.getSensor_desc();
			if(StringUtils.isEmpty(sensor_desc) || RegularUtil.trimZero(sensor_desc) || RegularUtil.isSpecialChar(sensor_desc)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			//如果该区域下已经有传感器,则返回
			Sensor sensor = new Sensor();
			sensor.setSensor_code(sensor_code);
			sensor.setCompany_area_code(company_area_code);
			int count = sensorService.findByExist(sensor);
			if (count > 0) {
				CODEMSG = SENSOR_BIND_EXIST;
				return finish(CODEMSG, null);
			}
			//如果传感器已存在,则返回
			sensor.setCompany_area_code(null);
			sensor.setSensor_number(sensor_number);
			count = sensorService.findByExist(sensor);
			if (count > 0) {
				CODEMSG = SENSOR_NUMBER_EXIST;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_code_update(user.getUser_code());
			form.setUser_name_update(user.getUser_name());
			int result = sensorService.update(form);
			if (result > 0) {
				syncSensor(form);
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
	 * 更新传感器状态
	 * @Title: updateState
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月24日 下午6:26:12
	 * @param form
	 * @return
	 */
	@PutMapping(value="/state/update")
	public Object updateState(@RequestBody Sensor form){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String sensor_code = form.getSensor_code();
			int sensor_state = form.getSensor_state();
			if(StringUtils.isEmpty(sensor_code) || RegularUtil.isSpecialChar(sensor_code) || RegularUtil.matchLength(sensor_code,25)) {
				CODEMSG = SENSOR_CODE_UNVALID;
				return finish(CODEMSG, null);
			}
			if(!(sensor_state == 1 || sensor_state == 2)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int result = sensorService.updateStatus(form);
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
	 * 查询传感器是否存在
	 * 
	 * @Title: single
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年12月30日 上午10:47:27
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/exist")
	public Object exist(Sensor form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String sensor_number = form.getSensor_number();
			if (!StringUtils.isEmpty(sensor_number) && RegularUtil.isSpecialChar(sensor_number)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			int count = sensorService.findByExist(form);
			if (count == 0) {
				CODEMSG = SUCCESS;
			}else {
				map = null;
				CODEMSG = SENSOR_NUMBER_EXIST;
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
	 * 查询传感器列表
	 * @Title: list
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月23日 下午5:43:20
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/warn/list")
	public Object warnList(Sensor form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
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
			form.setSensor_state(1);
			form.setFiling_state(3);
			map.put("pager_count", sensorService.findByCount_join(form));
			List<SensorReturn> srList =  sensorService.findByList_join(form);
			for (SensorReturn sensorReturn : srList) {
				sensorReturn.setSensor_log(sensorLogService.findBySensorNumber(sensorReturn.getSensor_number()));
			}
			map.put("sensor_warn_list",srList);
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
	 * 查询企业传感器列表
	 * @Title: list
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月23日 下午5:43:20
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/company/list")
	public Object companyList(Company form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
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
			form.setFiling_state(3);
			//根据登陆用户权限查询对应企业列表
			User user = TokenUserUtil.generateUser(request, tokenParam);
			map.put("pager_count", companyService.findByUserCompanyCount(form,user));
			List<CompanyReturn> cList = companyService.findByUserCompanyList(form,user);
			//返回封装对象集合
			List<Object> crList = new ArrayList<Object>();
			//返回封装对象
			Map<String, Object> m_company = null;
			CompanyType cType = null;
			CompanyTag cTag = null;
			String company_code = "";
			for (CompanyReturn company : cList) {
				m_company = new HashMap<String, Object>();
				company_code = company.getCompany_code();
				m_company.put("company_code", company_code);
				m_company.put("company_name", company.getCompany_name());
				m_company.put("province", company.getProvince());
				m_company.put("city", company.getCity());
				m_company.put("area", company.getArea());
				m_company.put("town", company.getTown());
				m_company.put("vill", company.getVill());
				m_company.put("address",company.getContact_address());
				
				cType = new CompanyType();
				cType.setCompanytype_code(company.getCompanytype_code());
				m_company.put("companytype_name", companyTypeService.findByCode(cType).getCompanytype_name());
				cTag = new CompanyTag();
				cTag.setCompanytag_code(company.getCompanytag_code());
				m_company.put("companytag_name", companyTagService.findByCode(cTag).getCompanytag_name());
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
	 * 根据区域查询传感器及最近一次监控值
	 * @Title: findByArea
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月17日 下午3:04:06
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/area")
	public Object findByArea(Sensor form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String company_area_code = form.getCompany_area_code();
			if(StringUtils.isEmpty(company_area_code) || RegularUtil.matchLength(company_area_code, 25)) {
				CODEMSG = COMPANY_AREA_CODE_UNVALID;
				return finish(CODEMSG, null);
			}
			SensorReturn sr = sensorService.findByArea(company_area_code);
			if(!StringUtils.isEmpty(sr)) {
				sr.setSensor_log(sensorLogService.findBySensorNumber(sr.getSensor_number()));
				map.put("sensor", sr);
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
	 * 编辑传感器
	 * @Title: save
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月23日 下午6:15:56
	 * @param form
	 * @return
	 */
	@PutMapping(value = "/threshold/update")
	public Object updateThreshold(@RequestBody Sensor form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String sensor_code = form.getSensor_code();
			if(StringUtils.isEmpty(sensor_code) || RegularUtil.matchLength(sensor_code, 25)) {
				CODEMSG = SENSOR_CODE_UNVALID;
				return finish(CODEMSG, null);
			}
			Object threshold_content = form.getThreshold_content();
			if(StringUtils.isEmpty(threshold_content)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_code_update(user.getUser_code());
			form.setUser_name_update(user.getUser_name());
			form.setThreshold_content(JSON.toJSONString(threshold_content));
			int result = sensorService.update(form);
			if (result > 0) {
				syncSensor(form);
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
	 * 根据传感器编号查询监测值
	 * @Title: warnSingle
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月22日 上午11:08:27
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/warn/single")
	public Object warnSingle(Sensor form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String sensor_number = form.getSensor_number();
			if (StringUtils.isEmpty(sensor_number)) {
				CODEMSG = SENSOR_NUMBER_UNVALID;
				return finish(CODEMSG, null);
			}
			map.put("sensor_log",sensorLogService.findBySensorNumber(sensor_number));
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
	 * 同步传感器到MQ
	 * @Title: syncSensor
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月25日 下午5:24:11
	 * @param form
	 * @throws Exception
	 */
	public synchronized void syncSensor(Sensor form) throws Exception {
		// 通过实现 ConfirmCallback 接口，消息发送到 Broker 后触发回调，确认消息是否到达 Broker 服务器，也就是只确认是否正确到达Exchange 中
		rabbitTemplate.setConfirmCallback(confirmCallback);
		// 消息达到exchange,但未能到达queue 回调returnCallback
		rabbitTemplate.setReturnCallback(returnCallback);
		//必须设置Mandatory=true，才会回调returnCallback
		rabbitTemplate.setMandatory(true);
		// 消息唯一ID
		CorrelationData correlationData = new CorrelationData("sensor_" + form.getSensor_code());
		// 是否需要处理同步状态,后续根据需求来
		// 发送消息
		String mq_prefix = tokenParam.getMq_prefix();
		String sensor_exchange = tokenParam.getSensor_exchange();
		String sensor_routing_key = tokenParam.getSensor_routing_key();
		logger.info("mq_prefix：{},sensor_routing_key：{},sensor_exchange：{}",mq_prefix,sensor_routing_key,sensor_exchange);
		rabbitTemplate.convertAndSend(mq_prefix+"_"+sensor_exchange,mq_prefix+"_"+sensor_routing_key, form,correlationData); 
	}
	
	/**
	 * Broker应答后，如果消息没有到exchange,则confirm回调,ack=false,如果消息到达exchange,则confirm回调,ack=tru
	 */
	final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
		@Override
		public void confirm(CorrelationData correlationData, boolean ack, String cause) {
			logger.info("correlationData：" + correlationData);
			String messageId = correlationData.getId();
			logger.info("消息确认返回值:{}",ack);
			logger.info("消息确认返回ID:{}" , messageId.replace("sensor_", ""));
			logger.info("消息失败原因:{}" ,cause);
		}
	};
	
	
	/**
	 * Broker应答后，exchange到queue成功,则不回调return。exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)
	 */
	final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
		@Override
		public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
			logger.info("replyText：{}" , replyText);
			logger.info("replyCode：{}" ,replyCode);
			logger.info("exchange：{}" ,exchange);
			logger.info("routingKey：{}" ,routingKey);
		}
	};
}
