package com.rs.dao;

import com.rs.po.MainSubChef;
import com.rs.po.returnPo.MainSubChefReturn;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * 
 * @ClassName: IMainSubChefDao
 * @Description: 帮厨数据层接口
 * @Author tangsh
 * @DateTime 2020年4月8日 上午9:53:44
 */
@Repository
public interface IMainSubChefDao extends IBaseDao<MainSubChef>{
	/**
	 * 根据条件查询帮厨列表
	 * @Title: findByList_app
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月15日 下午5:18:42
	 * @param form
	 * @return
	 */
	List<MainSubChefReturn> findByList_app(MainSubChef form);
	/**
	 * 查询单条帮厨
	 * @Title: findByCode_app
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月16日 上午10:47:43
	 * @param form
	 * @return
	 */
	MainSubChefReturn findByCode_app(MainSubChef form);
}
