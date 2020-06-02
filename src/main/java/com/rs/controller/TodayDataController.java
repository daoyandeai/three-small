package com.rs.controller;
import com.rs.po.Device;
import com.rs.po.DeviceLog;
import com.rs.po.DeviceSetting;
import com.rs.po.Report;
import com.rs.po.TodayData;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.service.DeviceLogService;
import com.rs.service.DeviceService;
import com.rs.service.DeviceSettingService;
import com.rs.service.ReportService;
import com.rs.service.TodayDataService;
import com.rs.util.TokenUserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
/**
 * 
 * @ClassName: TodayDataController
 * @Description: 群宴今日大数据统计控制层
 * @Author tangsh
 * @DateTime 2020年5月25日 下午2:34:45
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/todayData")
public class TodayDataController extends BaseController {

	@Autowired
	private DeviceService deviceService;
	@Autowired
	private TodayDataService todayDataService;
	@Autowired
	private DeviceSettingService deviceSettingService;
	@Autowired
	private DeviceLogService deviceLogService;
	@Autowired
	private ReportService reportService;
	@Autowired
	private TokenParam tokenParam;
	
	private Logger logger = LoggerFactory.getLogger(TodayDataController.class);

	/**
	 * 
	 * @Title: findDeviceSetting
	 * @Description: 获取所有的传感器列表
	 * @Author tangsh
	 * @DateTime 2020年5月25日 下午3:01:16
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/deviceSetting/all")
	public Object findDeviceSetting(DeviceSetting form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			map.put("pager_list", deviceSettingService.findByAll(form));
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
	 * 
	 * @Title: findTodatUsr
	 * @Description: 查询报备统计数据
	 * @Author tangsh
	 * @DateTime 2020年5月25日 下午3:29:09
	 * @param form
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/todatUsr")
	public Object findTodatUsr(TodayData form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			User user = TokenUserUtil.generateUser(request, tokenParam);
			if(user==null) {
				return finish(USER_UNDEFINED, null);
			}
			map.put("todayData", todayDataService.findTodatUsr(form,user));
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
	 * 
	 * @Title: findTodatBanquet
	 * @Description: 统计报备办宴类型数据
	 * @Author tangsh
	 * @DateTime 2020年5月25日 下午3:31:25
	 * @param form
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/todatBanquet")
	public Object findTodatBanquet(TodayData form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			User user = TokenUserUtil.generateUser(request, tokenParam);
			if(user==null) {
				return finish(USER_UNDEFINED, null);
			}
			map.put("todayData", todayDataService.findTodatBanquet(form,user));
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
	 * 
	 * @Title: findTodatNoPassReport
	 * @Description: 未通过的报备数据统计
	 * @Author tangsh
	 * @DateTime 2020年5月25日 下午3:32:14
	 * @param form
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/todatNoPassReport")
	public Object findTodatNoPassReport(TodayData form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			User user = TokenUserUtil.generateUser(request, tokenParam);
			if(user==null) {
				return finish(USER_UNDEFINED, null);
			}
			map.put("todayData", todayDataService.findTodatNoPassReport(form,user));
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
	 * 
	 * @Title: findTodatReport
	 * @Description: 今日新增加报备数统计
	 * @Author tangsh
	 * @DateTime 2020年5月25日 下午3:39:11
	 * @param form
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/todatReport")
	public Object findTodatReport(TodayData form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			User user = TokenUserUtil.generateUser(request, tokenParam);
			if(user==null) {
				return finish(USER_UNDEFINED, null);
			}
			map.put("todayData", todayDataService.findTodatReport(form,user));
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
	 * 
	 * @Title: findDeviceList
	 * @Description: 查询社保列表
	 * @Author tangsh
	 * @DateTime 2020年5月25日 下午3:42:56
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/device/list")
	public Object findDeviceList(TodayData form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			map.put("todayData", todayDataService.findDeviceList(form));
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
	 * 
	 * @Title: findDeviceLog
	 * @Description: 查询设备日志数据
	 * @Author tangsh
	 * @DateTime 2020年5月25日 下午3:45:07
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/devicelog")
	public Object findDeviceLog(DeviceLog form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			map.put("deviceLog", deviceLogService.findByNumber(form));
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
	 * 
	 * @Title: findPalyVideo
	 * @Description: 查询设备播放地址
	 * @Author tangsh
	 * @DateTime 2020年5月25日 下午4:01:11
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/palyVideo")
	public Object findPalyVideo(Device form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			map.put("device", deviceService.findByQyCode(form.getDevice_code()));
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
	 * 
	 * @Title: findUserList
	 * @Description: 查询用户列表数据
	 * @Author tangsh
	 * @DateTime 2020年5月25日 下午4:05:01
	 * @param form
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/user/list")
	public Object findUserList(TodayData form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			User user = TokenUserUtil.generateUser(request, tokenParam);
			if(user==null) {
				return finish(USER_UNDEFINED, null);
			}
			map.put("user", todayDataService.findUserList(form,user));
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
	 * 
	 * @Title: findOutCheckList
	 * @Description: 
	 * @Author tangsh
	 * @DateTime 2020年5月25日 下午4:05:43
	 * @param form
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/outCheck/list")
	public Object findOutCheckList(TodayData form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			User user = TokenUserUtil.generateUser(request, tokenParam);
			if(user==null) {
				return finish(USER_UNDEFINED, null);
			}
			map.put("todayData", todayDataService.findOutCheckList(form,user));
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
	 * 
	 * @Title: findTodatOutUsr
	 * @Description: 根据检查用户获取数据
	 * @Author tangsh
	 * @DateTime 2020年5月25日 下午4:08:08
	 * @param form
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/todatOutUsr")
	public Object findTodatOutUsr(TodayData form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			User user = TokenUserUtil.generateUser(request, tokenParam);
			if(user==null) {
				return finish(USER_UNDEFINED, null);
			}
			map.put("todayData", todayDataService.findTodatOutUsr(form,user));
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, map);
	}
	
	@GetMapping(value = "/dayBanquet")
	public Object findCountByToDayBanquet(Report form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			User user = TokenUserUtil.generateUser(request, tokenParam);
			if(user==null) {
				return finish(USER_UNDEFINED, null);
			}
			map.put("report", reportService.findCountByToDayBanquet(form,user));
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
