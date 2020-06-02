package com.rs.controller;

import com.rs.po.Department;
import com.rs.po.DepartmentRegion;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.po.returnPo.DepartmentReturn;
import com.rs.service.DepartmentRegionService;
import com.rs.service.DepartmentService;
import com.rs.util.BaiDuMapUtil;
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
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * 部门控制层
 * 
 * @ClassName: DepartmentController
 * @Description:
 * @Author sven
 * @DateTime 2019年12月30日 上午9:49:05
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/department")
public class DepartmentController extends BaseController {

	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private DepartmentRegionService departmentRegionService;
	@Autowired
	private TokenParam tokenParam;

	private Logger logger = LoggerFactory.getLogger(DepartmentController.class);

	/**
	 * 分页查询部门列表
	 * @Title: list
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年12月30日 上午9:50:39
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(Department form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String department_name = form.getDepartment_name();
			if (!StringUtils.isEmpty(department_name) && RegularUtil.isSpecialChar(department_name)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			Integer state = form.getState();
			if (!StringUtils.isEmpty(state) && !RegularUtil.isNumber(state + "")) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
		    int owner_type = form.getOwner_type();
			User user = TokenUserUtil.generateUser(request, tokenParam);
			switch (owner_type) {
			case 1:
				//全部
				form.setUser_code_add(null);
				break;
			case 2:
				//自己
				form.setUser_code_add(user.getUser_code());
				break;
			}
			
			//部门所在区域
			String province = form.getProvince();
			String city = form.getCity();
			String area = form.getArea();
			String town = form.getTown();
			String vill = form.getVill();
			String addrInfo = "";
			//登录用户区域
			String user_province = user.getUser_province();
			String user_city = user.getUser_city();
			String user_area = user.getUser_area();
			String user_town = user.getUser_town(); 
			String user_vill = user.getUser_vill();
			
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
			
			if(StringUtils.isEmpty(vill)) {
				form.setVill(user_vill);
				addrInfo = addrInfo+(StringUtils.isEmpty(user_vill)?"":user_vill);
			}else {
				addrInfo = addrInfo + vill;
			}
			form.setAddr_info(addrInfo);
			
			List<Department> dList = departmentService.findByList(form);
			List<DepartmentReturn> drList = new ArrayList<DepartmentReturn>();
			DepartmentReturn dr = null;
			for (Department department : dList) {
				dr = new DepartmentReturn();
				BeanUtils.copyProperties(department, dr);
				drList.add(dr);
			}
			map.put("department_list", drList);
			map.put("pager_count", departmentService.findByCount(form));

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
	 * 新增部门
	 * 
	 * @Title: save
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年12月30日 上午10:47:34
	 * @param form
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody Department form, HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String department_name = form.getDepartment_name();
			if (StringUtils.isEmpty(department_name) || RegularUtil.trimZero(department_name)) {
				CODEMSG = DEPARTMENT_NAME_EMPTY;
				return finish(CODEMSG, null);
			}
			if (RegularUtil.isSpecialChar(department_name)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			String province = form.getProvince();
			if (StringUtils.isEmpty(province)) {
				CODEMSG = PROVINCE_UNVALID;
				return finish(CODEMSG, null);
			}
			String city = form.getCity();
			if (StringUtils.isEmpty(city)) {
				CODEMSG = CITY_UNVALID;
				return finish(CODEMSG, null);
			}
			String area = form.getArea();
			if (StringUtils.isEmpty(area)) {
				CODEMSG = AREA_UNVALID;
				return finish(CODEMSG, null);
			}
			String town = form.getTown();
			if (StringUtils.isEmpty(town)) {
				CODEMSG = TOWN_UNVALID;
				return finish(CODEMSG, null);
			}
			String vill = form.getVill();
			if (StringUtils.isEmpty(vill)) {
				CODEMSG = VILL_UNVALID;
				return finish(CODEMSG, null);
			}
			String region_codes = form.getRegion_codes();
			if (StringUtils.isEmpty(region_codes)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			int count = departmentService.findByExist(form);
			if (count > 0) {
				CODEMSG = NAME_EXIST;
				return finish(CODEMSG, null);
			}
			form.setUser_code_add(user.getUser_code());
			form.setUser_name_add(user.getUser_name());
			String department_code = CodeUtil.getSystemCode("department");
			form.setDepartment_code(department_code);
			
			//逆解析地址
			String address = province + city + area + town + vill;
			String[] arr = new String[2];
    		arr = BaiDuMapUtil.getLngAndLatByLoc(address);
    		form.setLongitude(arr[0]);
    		form.setLatitude(arr[1]);
    		
			int result = departmentService.save(form);
			if (result > 0) {
				//根据页面新增食药所管理的第四级区域,插入其所有的第五级区域
				DepartmentRegion region = new DepartmentRegion();
				region.setDepartment_code(form.getDepartment_code());
				region.setRegion_codes(form.getRegion_codes());
				departmentRegionService.saveByRegionCodes(region);
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
	 * 查询部门
	 * 
	 * @Title: single
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年12月30日 上午10:47:27
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/single")
	public Object single(Department form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String department_code = form.getDepartment_code();
			if (StringUtils.isEmpty(department_code) || RegularUtil.matchLength(department_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			form = departmentService.findByCode(form);
			if (!StringUtils.isEmpty(form)) {
				DepartmentReturn dr = new DepartmentReturn();
				BeanUtils.copyProperties(form, dr);
				map.put("department", dr);
				//查询部门管理的第四级区域
				DepartmentRegion region = new DepartmentRegion();
				region.setDepartment_code(department_code);
				region.setParam_type(1);
				map.put("region_list", departmentRegionService.findByDeptCode(region));
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
	 * 编辑部门
	 * @Title: update
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月30日 上午11:25:54
	 * @param form
	 * @param request
	 * @return
	 */
	@PutMapping(value = "/update")
	public Object update(@RequestBody Department form, HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String department_code = form.getDepartment_code();
			if (StringUtils.isEmpty(department_code) || RegularUtil.isSpecialChar(department_code)
					|| RegularUtil.matchLength(department_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String department_name = form.getDepartment_name();
			if (StringUtils.isEmpty(department_name) || RegularUtil.trimZero(department_name)) {
				CODEMSG = DEPARTMENT_NAME_EMPTY;
				return finish(CODEMSG, null);
			}
			if (RegularUtil.isSpecialChar(department_name)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			String province = form.getProvince();
			if (StringUtils.isEmpty(province)) {
				CODEMSG = PROVINCE_UNVALID;
				return finish(CODEMSG, null);
			}
			String city = form.getCity();
			if (StringUtils.isEmpty(city)) {
				CODEMSG = CITY_UNVALID;
				return finish(CODEMSG, null);
			}
			String area = form.getArea();
			if (StringUtils.isEmpty(area)) {
				CODEMSG = AREA_UNVALID;
				return finish(CODEMSG, null);
			}
			String town = form.getTown();
			if (StringUtils.isEmpty(town)) {
				CODEMSG = TOWN_UNVALID;
				return finish(CODEMSG, null);
			}
			String vill = form.getVill();
			if (StringUtils.isEmpty(vill)) {
				CODEMSG = VILL_UNVALID;
				return finish(CODEMSG, null);
			}
			String region_codes = form.getRegion_codes();
			if (StringUtils.isEmpty(region_codes)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			int count = departmentService.findByExist(form);
			if (count > 0) {
				CODEMSG = NAME_EXIST;
				return finish(CODEMSG, null);
			}
			form.setUser_code_update(user.getUser_code());
			form.setUser_name_update(user.getUser_name());
			
			//逆解析地址
			String address = province + city + area + town + vill;
			String[] arr = new String[2];
    		arr = BaiDuMapUtil.getLngAndLatByLoc(address);
    		form.setLongitude(arr[0]);
    		form.setLatitude(arr[1]);
    		
			int result = departmentService.update(form);
			if (result > 0) {
				//根据当前食药所解绑
				DepartmentRegion region = new DepartmentRegion();
				region.setDepartment_code(form.getDepartment_code());
				departmentRegionService.delete(region);
				//根据页面新增食药所管理的第四级区域,插入其所有的第五级区域
				region.setRegion_codes(form.getRegion_codes());
				departmentRegionService.saveByRegionCodes(region);
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
	 * 更新部门状态
	 * 
	 * @Title: updateState
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年12月30日 上午11:08:56
	 * @param form
	 * @return
	 */
	@PutMapping(value = "/state/update")
	public Object updateState(@RequestBody Department form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String department_code = form.getDepartment_code();
			int state = form.getState();
			if (StringUtils.isEmpty(department_code) || RegularUtil.isSpecialChar(department_code)
					|| RegularUtil.matchLength(department_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if (!(state == 1 || state == 2)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int result = departmentService.update(form);
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
	 * 查询部门是否存在
	 * 
	 * @Title: single
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年12月30日 上午10:47:27
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/exist")
	public Object exist(Department form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String province = form.getProvince();
			if (StringUtils.isEmpty(province)) {
				CODEMSG = PROVINCE_UNVALID;
				return finish(CODEMSG, null);
			}
			String city = form.getCity();
			if (StringUtils.isEmpty(city)) {
				CODEMSG = CITY_UNVALID;
				return finish(CODEMSG, null);
			}
			String area = form.getArea();
			if (StringUtils.isEmpty(area)) {
				CODEMSG = AREA_UNVALID;
				return finish(CODEMSG, null);
			}
			String town = form.getTown();
			if (StringUtils.isEmpty(town)) {
				CODEMSG = TOWN_UNVALID;
				return finish(CODEMSG, null);
			}
			String vill = form.getVill();
			if (StringUtils.isEmpty(vill)) {
				CODEMSG = VILL_UNVALID;
				return finish(CODEMSG, null);
			}
			String department_name = form.getDepartment_name();
			if (StringUtils.isEmpty(department_name) || RegularUtil.trimZero(department_name)) {
				CODEMSG = DEPARTMENT_NAME_EMPTY;
				return finish(CODEMSG, null);
			}
			if (RegularUtil.isSpecialChar(department_name)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			
			int count = departmentService.findByExist(form);
			if (count == 0) {
				CODEMSG = SUCCESS;
			}else {
				map = null;
				CODEMSG = NAME_EXIST;
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
			boolean flag = departmentService.geocoder();
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
}
