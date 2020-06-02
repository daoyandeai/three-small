package com.rs.controller;
import com.rs.po.Department;
import com.rs.po.DepartmentUser;
import com.rs.po.returnPo.DepartmentReturn;
import com.rs.po.returnPo.UserReturn;
import com.rs.service.DepartmentService;
import com.rs.service.DepartmentUserService;
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

import javax.servlet.http.HttpServletRequest;
/**
 * 
 * @ClassName: AppDepartmentUserController
 * @Description: 监管人员控制层
 * @Author tangsh
 * @DateTime 2020年6月1日 上午10:18:04
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/app/departmentUser")
public class AppDepartmentUserController extends BaseController {
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private DepartmentUserService departmentUserService;

	private Logger logger = LoggerFactory.getLogger(AppDepartmentUserController.class);

	/**
	 * 
	 * @Title: list
	 * @Description: 查询部门监管人员列表
	 * @Author tangsh
	 * @DateTime 2020年6月1日 上午10:18:21
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(DepartmentUser form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String department_code = form.getDepartment_code();
			if (StringUtils.isEmpty(department_code) || RegularUtil.matchLength(department_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			//食药所
			Department dept = new Department();
			dept.setDepartment_code(form.getDepartment_code());
			dept = departmentService.findByCode(dept);
			DepartmentReturn dr = new DepartmentReturn();
			BeanUtils.copyProperties(dept, dr);
			map.put("department", dr);
			//食药所监管人员
			List<UserReturn> uList = departmentUserService.findListByDeptAndUser(form);
			List<Object> drList = new ArrayList<Object>();
			Map<String, Object> uMap = null;
			for (UserReturn u : uList) {
				uMap = new HashMap<String, Object>();
				uMap.put("user_code", u.getUser_code());
				uMap.put("user_name", u.getUser_name());
				uMap.put("user_mobilephone", u.getUser_mobilephone());
				uMap.put("user_town", u.getUser_town());
				uMap.put("state", u.getUser_state());
				drList.add(uMap);
			}
			map.put("department_user_list", drList);
			map.put("pager_count", departmentUserService.findCountByDeptAndUser(form));

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
	 * @Title: deptUserAll
	 * @Description: 查询指定部门下的所有的人员
	 * @Author tangsh
	 * @DateTime 2020年3月16日 下午4:55:20
	 * @param form
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/deptUserAll")
	public Object deptUserAll(DepartmentUser form,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String department_code = form.getDepartment_code();
			if (StringUtils.isEmpty(department_code) || RegularUtil.isSpecialChar(department_code)
					|| RegularUtil.matchLength(department_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			Integer state = form.getState();
			if (!StringUtils.isEmpty(state) && !RegularUtil.isNumber(state + "")) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			
			map.put("departmentUser_list", departmentUserService.findListByDeptAllUser(form));
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
