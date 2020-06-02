package com.rs.controller;
import com.rs.po.Department;
import com.rs.po.DepartmentRegion;
import com.rs.po.InfoRegion;
import com.rs.po.Region;
import com.rs.po.TokenParam;
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
 * 网格信息员控制层
 * @ClassName: InfoRegionController
 * @Description: 
 * @Author sven
 * @DateTime 2020年1月2日 下午3:06:10
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/infoRegion")
public class InfoRegionController extends BaseController {

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
	@Autowired
	private TokenParam tokenParam;
	
	private Logger logger = LoggerFactory.getLogger(InfoRegionController.class);

	/**
	 * 分页查询部门监管人员列表
	 * 
	 * @Title: list
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年12月30日 上午9:50:39
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(InfoRegion form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			User user = TokenUserUtil.generateUser(request, tokenParam);
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
	 * 新增网格信息员
	 * @Title: save
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月31日 下午5:21:46
	 * @param form
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody InfoRegion form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			List<InfoRegion> info_region_list =  form.getInfo_region_list();
			if(StringUtils.isEmpty(info_region_list) || info_region_list.size() == 0) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			form.setPager_list(info_region_list);
			int result = infoRegionService.saveBatch(form);
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
	 * 更新网格信息员状态
	 * 
	 * @Title: updateState
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年12月30日 上午11:08:56
	 * @param form
	 * @return
	 */
	@PutMapping(value = "/state/update")
	public Object updateState(@RequestBody InfoRegion form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String region_code = form.getRegion_code();
			if (StringUtils.isEmpty(region_code) || RegularUtil.isSpecialChar(region_code)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String user_code = form.getUser_code();
			if (StringUtils.isEmpty(user_code) || RegularUtil.isSpecialChar(user_code) || RegularUtil.matchLength(user_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			Integer state = form.getState();
			if (!(state == 1 || state == 2)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int result = infoRegionService.update(form);
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
	 * 分页查询部门监管人员列表
	 * 
	 * @Title: list
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年12月30日 上午9:50:39
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/region/user")
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
	 * 根据部门查询信息员
	 * @Title: department
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年1月9日 下午5:28:50
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
	
	
	/**
	 * 删除网格信息员
	 * @Title: delete
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年1月11日 下午3:26:23
	 * @param form
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Object delete(InfoRegion form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String region_code = form.getRegion_code();
			if (StringUtils.isEmpty(region_code) || RegularUtil.isSpecialChar(region_code)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String user_code = form.getUser_code();
			if (StringUtils.isEmpty(user_code) || RegularUtil.isSpecialChar(user_code) || RegularUtil.matchLength(user_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int result = infoRegionService.delete(form);
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
