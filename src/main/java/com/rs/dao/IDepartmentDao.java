package com.rs.dao;


import com.rs.po.Department;
import com.rs.po.Region;
import com.rs.po.User;
import com.rs.po.returnPo.DepartmentReturn;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * @ClassName: IDepartmentDao
 * @Description: 部门数据接口层
 * @Author tangsh
 * @DateTime 2019年12月18日 下午1:20:19
 */
@Repository
public interface IDepartmentDao extends IBaseDao<Department>{
	/**
	 * 
	 * @Title: findByRegion
	 * @Description: 根据部门信息查询区域
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:07:43
	 * @param form
	 * @return
	 */
    List<Region> findByRegion(Department form);
    /**
     * 
     * @Title: fingByInfo
     * @Description: 根据区域查询网格员
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:07:53
     * @param form
     * @return
     */
    List<User> fingByInfo(Region form);
    /**
     * 
     * @Title: saveRegion
     * @Description: 保存部门区域
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:08:08
     * @param form
     * @return
     */
    Integer saveRegion(Department form);
    /**
     * 
     * @Title: saveRegionUser
     * @Description: 保存部门区域管理员
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:08:16
     * @param form
     * @return
     */
    Integer saveRegionUser(Department form);
    /**
     * 
     * @Title: saveInfoUser
     * @Description: 保存部门信息员
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:08:29
     * @param form
     * @return
     */
    Integer saveInfoUser(Department form);
    /**
     * 
     * @Title: fingByUserList
     * @Description: 根据区域查询用户列表
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:08:40
     * @param form
     * @return
     */
    List<User> fingByUserList(Region form);
    /**
     * 
     * @Title: fingByInfoList
     * @Description: 根据用户信息查询信息员列表
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:08:50
     * @param form
     * @return
     */
    List<User> fingByInfoList(User form);
    /**
     * 
     * @Title: fingByRegion
     * @Description: 根据部门信息查询区域用户列表
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:09:10
     * @param form
     * @return
     */
    List<User> fingByRegion(Department form);
    /**
     * 
     * @Title: findBySearch
     * @Description: 根据部门信息查询列表
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:09:25
     * @param form
     * @return
     */
    List<Department> findBySearch(Department form);
    /**
     * 
     * @Title: fingByDetailUser
     * @Description: 根据部门信息查询用户列表
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:09:37
     * @param form
     * @return
     */
    List<User> fingByDetailUser(Department form);
    /**
     * 
     * @Title: findBySearchCount
     * @Description: 根据部门信息查询数据总数
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:09:54
     * @param form
     * @return
     */
    int findBySearchCount(Department form);
    /**
     * 
     * @Title: findJoin
     * @Description: 根据部门联合查询数据
     * @Author sven
     * @DateTime 2020年3月17日 下午3:10:44
     * @param form
     * @return
     */
    Department findJoin(Department form);
    /**
     * 
     * @Title: fingByDetailInfo
     * @Description: 根据部门信息查询网格员数据
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:11:06
     * @param form
     * @return
     */
    List<User> fingByDetailInfo(Department form);
    /**
     * 
     * @Title: findByUserCode
     * @Description: 根据用户信息查询部门信息
     * @Author sven
     * @DateTime 2020年3月17日 下午3:11:21
     * @param form
     * @return
     */
    DepartmentReturn findByUserCode(Department form);
    /**
     * 查询用户归属的所有监管部门
     * @Title: findAllByUserCode
     * @Description: 
     * @Author sven
     * @DateTime 2020年5月29日 上午9:37:13
     * @param form
     * @return
     */
     List<DepartmentReturn> findAllByUserCode(Department form);
     /**
      * 查询所有的监管部门
      * @Title: findAll_app
      * @Description: 
      * @Author sven
      * @DateTime 2020年5月29日 上午10:00:23
      * @return
      */
     List<DepartmentReturn> findAll_app(Department form);
}