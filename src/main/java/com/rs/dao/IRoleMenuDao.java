package com.rs.dao;

import org.springframework.stereotype.Repository;

import com.rs.po.RoleMenu;

/**
 * 
 * @ClassName: IRoleMenuDao
 * @Description: 角色菜单数据接口层
 * @Author tangsh
 * @DateTime 2019年12月18日 上午10:25:56
 */
@Repository
public interface IRoleMenuDao extends IBaseDao<RoleMenu> {
	/**
	 * 
	 * @Title: findButtonByTwoCode
	 * @Description: 查询角色菜单按钮编码
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:21:04
	 * @param form
	 * @return
	 */
	String findButtonByTwoCode(RoleMenu form);
}