package com.rs.dao;
import com.rs.po.CaseCenter;
import com.rs.po.User;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 
 * @ClassName: ICaseCenterDao
 * @Description: 案件中心数据层接口
 * @Author tangsh
 * @DateTime 2020年3月16日 上午10:50:39
 */
@Repository
public interface ICaseCenterDao extends IBaseDao<CaseCenter> {
	
	List<User> findByUser(@Param(value = "user_codes_secondary") String user_codes_secondary);
}
