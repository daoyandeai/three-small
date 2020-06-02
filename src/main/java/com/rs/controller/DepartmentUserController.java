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
 * 部门监管人员控制层
 * @ClassName: DepartmentUserController
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月31日 下午4:26:36
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/departmentUser")
public class DepartmentUserController extends BaseController {
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private DepartmentUserService departmentUserService;

	private Logger logger = LoggerFactory.getLogger(DepartmentUserController.class);

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
	 * 新增部门监管人员
	 * @Title: save
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月31日 下午5:21:46
	 * @param form
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody DepartmentUser form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			List<DepartmentUser> department_user_list =  form.getDepartment_user_list();
			if(StringUtils.isEmpty(department_user_list) || department_user_list.size() == 0) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			form.setPager_list(department_user_list);
			int result = departmentUserService.saveBatch(form);
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
	 * 更新部门监管人员状态
	 * 
	 * @Title: updateState
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年12月30日 上午11:08:56
	 * @param form
	 * @return
	 */
	@PutMapping(value = "/state/update")
	public Object updateState(@RequestBody DepartmentUser form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String department_code = form.getDepartment_code();
			if (StringUtils.isEmpty(department_code) || RegularUtil.isSpecialChar(department_code) || RegularUtil.matchLength(department_code, 25)) {
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
			int result = departmentUserService.update(form);
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
	 * 删除部门监管人员
	 * @Title: delete
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年1月11日 下午3:24:46
	 * @param form
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Object delete(DepartmentUser form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String department_code = form.getDepartment_code();
			if (StringUtils.isEmpty(department_code) || RegularUtil.isSpecialChar(department_code) || RegularUtil.matchLength(department_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String user_code = form.getUser_code();
			if (StringUtils.isEmpty(user_code) || RegularUtil.isSpecialChar(user_code) || RegularUtil.matchLength(user_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int result = departmentUserService.delete(form);
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
