package com.rs.service;

import com.rs.dao.IFoodSourceDetailDao;
import com.rs.po.FoodSourceDetail;
import com.rs.po.returnPo.FoodSourceDetailReturn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 溯源明细服务层
 * @ClassName: FoodSourceDetailService
 * @Description: 
 * @Author sven
 * @DateTime 2020年4月2日 下午3:12:14
 */
@Service
public class FoodSourceDetailService extends BaseService<FoodSourceDetail>{
	@Autowired
	private IFoodSourceDetailDao foodSourceDetailDao;
	
	public List<FoodSourceDetailReturn> findByAll_app(String foodsource_code){
		return foodSourceDetailDao.findByAll_app(foodsource_code);
	}
	
	public Integer deleteByFoodSource(String foodsource_code){
		return foodSourceDetailDao.deleteByFoodSource(foodsource_code);
	}
}
