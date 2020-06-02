package com.rs.controller;
import com.rs.po.Department;
import com.rs.po.DepartmentRegion;
import com.rs.po.InfoRegion;
import com.rs.po.Region;
import com.rs.po.User;
import com.rs.po.returnPo.DepartmentReturn;
import com.rs.po.returnPo.RegionReturn;
import com.rs.po.returnPo.UserReturn;
import com.rs.service.DepartmentRegionService;
import com.rs.service.DepartmentService;
import com.rs.service.InfoRegionService;
import com.rs.service.RegionService;
import com.rs.service.UserService;
import com.rs.util.RegularUtil;
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
 * @ClassName: AppDepartmentInfoRegionController
 * @Description: 部门管理区域网格信息员控制层
 * @Author tangsh
 * @DateTime 2020年6月1日 上午10:25:12
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/app/infoRegion")
public class AppDepartmentInfoRegionController extends BaseController {

	@Autowired
	private InfoRegionService infoRegionService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private DepartmentRegionService departmentRegionService;
	@Autowired
	private UserService userService;
	@Autowired
	private RegionService regionService;
	
	private Logger logger = LoggerFactory.getLogger(AppDepartmentInfoRegionController.class);

	/**
	 * 
	 * @Title: list
	 * @Description: 部门信息人员列表
	 * @Author tangsh
	 * @DateTime 2020年6月1日 上午10:25:54
	 * @param form
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(InfoRegion form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			if(StringUtils.isEmpty(form.getUser_code())) {
				CODEMSG = USER_NOT_IN_DEPART;
				return finish(CODEMSG, map);
			}
			User user=new User();
			user.setUser_code(form.getUser_code());
			user=userService.findByCode(user);
			if(user==null) {
				CODEMSG = USER_NOT_IN_DEPART;
				return finish(CODEMSG, map);
			}
			//查询当前监管人员所在食药所
			Department d = new Department();
			d.setUser_code(user.getUser_code());
			DepartmentReturn dr = departmentService.findByUserCode(d);
			if(StringUtils.isEmpty(dr)) {
				CODEMSG = USER_NOT_IN_DEPART;
				return finish(CODEMSG, map);
			}
			map.put("department", dr);
			//查询该食药所下所有第五级区域
			DepartmentRegion dRegion = new DepartmentRegion();
			dRegion.setDepartment_code(dr.getDepartment_code());
			dRegion.setParam_type(2);
			List<RegionReturn> regionList = departmentRegionService.findByDeptCode(dRegion);
			//查询网格信息员
			List<Object> drList = new ArrayList<Object>();
			if(!StringUtils.isEmpty(regionList) && regionList.size() > 0) {
				StringBuffer sb = new StringBuffer();
				String region_codes = "";
				for(RegionReturn r : regionList) {
					sb.append("'").append(r.getRegion_code()).append("',");
				}
				if(sb.length() > 0) {
					region_codes = sb.substring(0, sb.length()-1);
				}
				form.setRegion_codes(region_codes);
				List<UserReturn> uList = infoRegionService.findListByRegionAndUser(form);
				Map<String, Object> uMap = null;
				Region region = null;
				if(uList!=null&&uList.size()>0) {
					for (UserReturn u : uList) {
						uMap = new HashMap<String, Object>();
						uMap.put("user_code", u.getUser_code());
						uMap.put("user_name", u.getUser_name());
						uMap.put("user_mobilephone", u.getUser_mobilephone());
						uMap.put("user_vill", u.getUser_vill());
						//查询当前用户的区域编码
						region = new Region();
						region.setRegion_province(u.getUser_province());
						region.setRegion_city(u.getUser_city());
						region.setRegion_area(u.getUser_area());
						region.setRegion_town(u.getUser_town());
						region.setRegion_vill(u.getUser_vill());
						region = regionService.findOneByRegion(region);
						String r_code="";
						if(region!=null) {
							r_code = region.getRegion_code();
						}
						uMap.put("region_code", r_code);
						uMap.put("state", u.getUser_state());
						drList.add(uMap);
					}
				}
			}
			map.put("info_region_list", drList);
			map.put("pager_count", infoRegionService.findCountByRegionAndUser(form));
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
	 * @Title: findByRegion
	 * @Description: 查询部门监管区域信息人员列表
	 * @Author tangsh
	 * @DateTime 2020年6月1日 上午10:27:47
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/user")
	public Object findByRegion(InfoRegion form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String region_code = form.getRegion_code();
			if (StringUtils.isEmpty(region_code) || RegularUtil.isSpecialChar(region_code)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			//查询网格信息员
			List<UserReturn> uList = infoRegionService.findAllByRegionAndUser(form);
			List<Object> drList = new ArrayList<Object>();
			Map<String, Object> uMap = null;
			for (UserReturn u : uList) {
				uMap = new HashMap<String, Object>();
				uMap.put("user_code", u.getUser_code());
				uMap.put("user_name", u.getUser_name());
				uMap.put("user_mobilephone", u.getUser_mobilephone());
				uMap.put("user_vill", u.getUser_vill());
				uMap.put("state", u.getUser_state());
				drList.add(uMap);
			}
			map.put("info_region_list", drList);
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
	 * @Title: department
	 * @Description: 根据部门查询信息员
	 * @Author tangsh
	 * @DateTime 2020年6月1日 上午10:28:34
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/department")
	public Object department(Department form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String department_code = form.getDepartment_code();
			if (StringUtils.isEmpty(department_code) || RegularUtil.isSpecialChar(department_code)
					|| RegularUtil.matchLength(department_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			//查询网格信息员
			List<UserReturn> uList = userService.findByDept(department_code);
			List<Object> drList = new ArrayList<Object>();
			Map<String, Object> uMap = null;
			for (UserReturn u : uList) {
				uMap = new HashMap<String, Object>();
				uMap.put("user_code", u.getUser_code());
				uMap.put("user_name", u.getUser_name());
				uMap.put("user_mobilephone", u.getUser_mobilephone());
				uMap.put("user_vill", u.getUser_vill());
				uMap.put("state", u.getUser_state());
				drList.add(uMap);
			}
			map.put("info_region_list", drList);
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, map);
	}
}
