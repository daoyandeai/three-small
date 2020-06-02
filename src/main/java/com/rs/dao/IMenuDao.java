package com.rs.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rs.po.Menu;

/**
 * 
 * @ClassName: IMenuDao
 * @Description: 菜单数据接口层
 * @Author tangsh
 * @DateTime 2019年12月18日 上午10:24:18
 */
@Repository
public interface IMenuDao extends IBaseDao<Menu> {
	/**
	 * 
	 * @Title: findMenuByRole
	 * @Description: 根据角色查询菜单数据
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:14:45
	 * @param role_code
	 * @return
	 */
	List<Menu> findMenuByRole(String role_code);
}