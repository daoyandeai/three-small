package com.rs.dao;

import com.rs.po.FoodSourceAccessory;
import com.rs.po.returnPo.FoodSourceAccessoryReturn;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * 
 * @ClassName: IFoodSourceAccessoryDao
 * @Description: 溯源附件
 * @Author tangsh
 * @DateTime 2019年12月18日 下午1:21:15
 */
@Repository
public interface IFoodSourceAccessoryDao extends IBaseDao<FoodSourceAccessory>{
	/**
	 * 根据溯源明细编码查询附件
	 * @Title: findByAll_app
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月2日 下午3:26:12
	 * @param foodsourcedetail_code
	 * @return
	 */
	List<FoodSourceAccessoryReturn> findByAll_app(String foodsourcedetail_code);
	
	/**
	 * 
	 * @Title: deleteByFoodSource
	 * @Description: 根据溯源编码删除溯源附件内容
	 * @Author tangsh
	 * @DateTime 2020年6月1日 下午5:14:17
	 * @param foodsource_code
	 * @return
	 */
	Integer deleteByFoodSource(String foodsource_code);
	
	/**
	 * 
	 * @Title: findByFoodSource
	 * @Description: 根据溯源编码查询溯源附件内容
	 * @Author tangsh
	 * @DateTime 2020年6月1日 下午5:22:23
	 * @param foodsource_code
	 * @return
	 */
	List<FoodSourceAccessoryReturn> findByFoodSource(String foodsource_code);
}