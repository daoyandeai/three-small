package com.rs.service;
import com.rs.dao.ICaseCenterDao;
import com.rs.dao.IDepartmentDao;
import com.rs.po.CaseCenter;
import com.rs.po.Department;
import com.rs.po.User;
import com.rs.util.CodeUtil;
import com.rs.util.RegularUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 
 * @ClassName: CaseCenterService
 * @Description: 案件中心服务层接口
 * @Author tangsh
 * @DateTime 2020年3月16日 上午10:51:21
 */
@Service
public class CaseCenterService extends BaseService<CaseCenter>{
	
	@Autowired
	private ICaseCenterDao caseCenterDao;
	@Autowired
	private IDepartmentDao departmentDao;
	
	public List<User> findByUser(String user_codes_secondary){
		return caseCenterDao.findByUser(user_codes_secondary);
	}
	
	public int saveCaseCenter(CaseCenter form,User user){
		form.setUser_code_add(user.getUser_code());
		form.setUser_name_add(user.getUser_name());
		form.setProvince(user.getUser_province());
		form.setCity(user.getUser_city());
		form.setArea(user.getUser_area());
		form.setTown(user.getUser_town());
		form.setVill(user.getUser_vill());
		form.setCasecenter_code(CodeUtil.getSystemCode("caseCenter"));
		if(form.getUser_code_secondary_list()!=null&&form.getUser_code_secondary_list().size()>0) {
			form.setUser_codes_secondary(RegularUtil.getListToString(form.getUser_code_secondary_list(),","));
		}
		if(form.getUser_name_secondary_list()!=null&&form.getUser_name_secondary_list().size()>0) {
			form.setUser_names_secondary(RegularUtil.getListToString(form.getUser_name_secondary_list(),","));
		}
		return caseCenterDao.save(form);
	}
	
	public CaseCenter findCaseCenter(CaseCenter form) {
		form=caseCenterDao.findByCode(form);
		if(form!=null) {
			form.setUser_code_secondary_list(RegularUtil.getStringToList(form.getUser_codes_secondary(),","));
			form.setUser_name_secondary_list(RegularUtil.getStringToList(form.getUser_names_secondary(),","));
			Department dtm=new Department();
			dtm.setDepartment_code(form.getDepartment_code_main());
			dtm=departmentDao.findByCode(dtm);
			if(dtm!=null) {
				form.setDepartment_name_main(dtm.getDepartment_name());
			}
			Department dts=new Department();
			dts.setDepartment_code(form.getDepartment_code_main());
			dts=departmentDao.findByCode(dts);
			if(dts!=null) {
				form.setDepartment_name_secondary(dts.getDepartment_name());
			}
		}
		return form;
	}
	
	public int updateCaseCenter(CaseCenter form){
		if(form.getUser_code_secondary_list()!=null&&form.getUser_code_secondary_list().size()>0) {
			form.setUser_codes_secondary(RegularUtil.getListToString(form.getUser_code_secondary_list(),","));
		}
		if(form.getUser_name_secondary_list()!=null&&form.getUser_name_secondary_list().size()>0) {
			form.setUser_names_secondary(RegularUtil.getListToString(form.getUser_name_secondary_list(),","));
		}
		return caseCenterDao.update(form);
	}
}
