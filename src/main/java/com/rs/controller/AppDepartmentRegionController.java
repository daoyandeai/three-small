package com.rs.controller;
import com.rs.po.DepartmentRegion;
import com.rs.service.DepartmentRegionService;
import com.rs.util.RegularUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @ClassName: AppDepartmentRegionController
 * @Description: 部门监管区域控制层
 * @Author tangsh
 * @DateTime 2020年6月1日 上午10:08:22
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/app/departmentRegion")
public class AppDepartmentRegionController extends BaseController {

	@Autowired
	private DepartmentRegionService departmentRegionService;

	private Logger logger = LoggerFactory.getLogger(AppDepartmentRegionController.class);

	/**
	 * 
	 * @Title: list
	 * @Description: 根据部门编码查询其监管区域
	 * @Author tangsh
	 * @DateTime 2020年6月1日 上午10:08:38
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(DepartmentRegion form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String department_code = form.getDepartment_code();
			if (StringUtils.isEmpty(department_code) || RegularUtil.matchLength(department_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			map.put("department_region_list", departmentRegionService.findByDeptCode(form));
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
