package com.rs.dao;

import org.springframework.stereotype.Repository;
import com.rs.po.UserRole;

/**
 * 
 * @ClassName: IUserRoleDao
 * @Description: 用户角色数据接口层
 * @Author tangsh
 * @DateTime 2019年12月18日 上午10:24:57
 */
@Repository
public interface IUserRoleDao extends IBaseDao<UserRole> {
}