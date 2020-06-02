package com.rs.controller;

import com.rs.po.Department;
import com.rs.po.DepartmentRegion;
import com.rs.po.User;
import com.rs.po.returnPo.DepartmentReturn;
import com.rs.service.DepartmentRegionService;
import com.rs.service.DepartmentService;
import com.rs.service.UserService;
import com.rs.util.RegularUtil;
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
 * 
 * @ClassName: AppDepartmentController
 * @Description: 部门控制层
 * @Author tangsh
 * @DateTime 2020年5月29日 下午2:10:25
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/app/department")
public class AppDepartmentController extends BaseController {

	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private DepartmentRegionService departmentRegionService;
	@Autowired
	private UserService userService;

	private Logger logger = LoggerFactory.getLogger(AppDepartmentController.class);

	/**
	 * 
	 * @Title: list
	 * @Description: 分页查询部门列表
	 * @Author tangsh
	 * @DateTime 2020年5月29日 下午2:11:28
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(Department form) {
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
		    User user=new User();
		    if(!StringUtils.isEmpty(form.getUser_code())) {
				user.setUser_code(form.getUser_code());
				user=userService.single(user);
				if(user==null) {
					user=new User();
				}
			}
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
	 * 
	 * @Title: single
	 * @Description: 查询部门
	 * @Author tangsh
	 * @DateTime 2020年5月29日 下午2:12:37
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
	 * 
	 * @Title: exist
	 * @Description: 查询部门是否存在
	 * @Author tangsh
	 * @DateTime 2020年5月29日 下午2:12:52
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
}
