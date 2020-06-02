package com.rs.service;

import com.rs.dao.IDepartmentUserDao;
import com.rs.po.DepartmentUser;
import com.rs.po.returnPo.UserReturn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 部门管理员服务层
 * @ClassName: DepartmentUserService
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月31日 下午4:19:47
 */
@Service
public class DepartmentUserService extends BaseService<DepartmentUser> {
	
	@Autowired
	private IDepartmentUserDao departmentUserDao;
	
	public String findByUserCode(String user_code) {
		return departmentUserDao.findByUserCode(user_code);
	}
	
	public List<UserReturn> findListByDeptAndUser(DepartmentUser form){
		return departmentUserDao.findListByDeptAndUser(form);
	}
	
	public Integer findCountByDeptAndUser(DepartmentUser form){
		return departmentUserDao.findCountByDeptAndUser(form);
	}
	
	public List<UserReturn> findListByDeptAllUser(DepartmentUser form){
		return departmentUserDao.findListByDeptAllUser(form);
	}
}
