package com.rs.dao;

import com.rs.po.FoodSourceDetail;
import com.rs.po.returnPo.FoodSourceDetailReturn;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * 
 * @ClassName: IFoodSourceDetailDao
 * @Description: 溯源详情数据接口层
 * @Author tangsh
 * @DateTime 2019年12月18日 下午1:21:39
 */
@Repository
public interface IFoodSourceDetailDao extends IBaseDao<FoodSourceDetail>{
	/**
	 * 根据溯源编码查询明细
	 * @Title: findByList_app
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月2日 下午3:11:20
	 * @param form
	 * @return
	 */
	List<FoodSourceDetailReturn> findByAll_app(String foodsource_code);
	
	/**
	 * 
	 * @Title: deleteByFoodSource
	 * @Description: 根据溯源编码删除溯源明细内容
	 * @Author tangsh
	 * @DateTime 2020年6月1日 下午5:09:12
	 * @param foodsource_code
	 * @return
	 */
	Integer deleteByFoodSource(String foodsource_code);
}