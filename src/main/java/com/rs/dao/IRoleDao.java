package com.rs.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rs.po.User;
import com.rs.po.Menu;
import com.rs.po.RoleMenu;
import com.rs.po.Role;

/**
 * 
 * @ClassName: IRoleDao
 * @Description: 角色数据接口层
 * @Author tangsh
 * @DateTime 2019年12月18日 上午10:24:57
 */
@Repository
public interface IRoleDao extends IBaseDao<Role> {
	/**
	 * 
	 * @Title: deleteUserRole
	 * @Description: 删除用户角色
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:19:15
	 * @param form
	 * @return
	 */
	Integer deleteUserRole(Role form);
	/**
	 * 
	 * @Title: deleteRoleMenu
	 * @Description: 删除角色菜单
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:19:25
	 * @param form
	 * @return
	 */
	Integer deleteRoleMenu(Role form);
	/**
	 * 
	 * @Title: deleteByUser
	 * @Description: 删除指定角色用户
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:19:34
	 * @param form
	 * @return
	 */
	Integer deleteByUser(User form);
	/**
	 * 
	 * @Title: deleteRoleUser
	 * @Description: 删除角色用户
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:19:42
	 * @param map
	 * @return
	 */
	Integer deleteRoleUser(Map<String,String> map);
	/**
	 * 
	 * @Title: findByRoleUserList
	 * @Description: 查询角色用户列表
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:19:58
	 * @param form
	 * @return
	 */
	List<User> findByRoleUserList(Role form);
	/**
	 * 
	 * @Title: findByRoleUserCount
	 * @Description: 查询角色用户总数
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:20:09
	 * @param form
	 * @return
	 */
	Integer findByRoleUserCount(Role form);
	/**
	 * 
	 * @Title: saveRoleUser
	 * @Description: 新增角色用户
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:20:18
	 * @param map
	 * @return
	 */
	Integer saveRoleUser(Map<String,String> map);
	/**
	 * 
	 * @Title: findByMenu
	 * @Description: 查询所有菜单
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:20:27
	 * @return
	 */
	List<Menu> findByMenu();
	/**
	 * 
	 * @Title: findByRoleMenu
	 * @Description: 查询指定角色菜单
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:20:34
	 * @param role_code
	 * @return
	 */
	List<RoleMenu> findByRoleMenu(String role_code);
	/**
	 * 
	 * @Title: saveRoleMenu
	 * @Description: 新增角色菜单
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:20:48
	 * @param map
	 * @return
	 */
	Integer saveRoleMenu(Map<String,String> map);
}