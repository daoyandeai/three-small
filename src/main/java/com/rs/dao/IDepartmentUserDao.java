package com.rs.dao;
import com.rs.po.DepartmentUser;
import com.rs.po.returnPo.UserReturn;

import java.util.List;
import org.springframework.stereotype.Repository;
/**
 * 部门管理员数据接口层
 * @ClassName: IDepartmentUserDao
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月31日 下午4:17:56
 */
@Repository
public interface IDepartmentUserDao extends IBaseDao<DepartmentUser>{
	
	/**
	 * 
	 * @Title: findByUserCode
	 * @Description: 根据用户编码查询数据
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:11:54
	 * @param user_code
	 * @return
	 */
	String findByUserCode(String user_code);
	/**
	 * 根据部门和用户信息查询用户集合
	 * @Title: findByDeptAndUser
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月31日 下午4:16:04
	 * @param form
	 * @return
	 */
	List<UserReturn> findListByDeptAndUser(DepartmentUser form);
	/**
	 * 根据部门和用户信息查询用户总数
	 * @Title: findByDeptAndUser
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月31日 下午4:16:04
	 * @param form
	 * @return
	 */
	Integer findCountByDeptAndUser(DepartmentUser form);
	/**
	 * 
	 * @Title: findListByDeptAllUser
	 * @Description: 根据部门编码查询所有的用户（管理员和协管员）
	 * @Author tangsh
	 * @DateTime 2020年3月16日 下午4:53:40
	 * @param form
	 * @return
	 */
	List<UserReturn> findListByDeptAllUser(DepartmentUser form);
}