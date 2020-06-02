package com.rs.service;

import com.rs.dao.IFoodSourceAccessoryDao;
import com.rs.po.FoodSourceAccessory;
import com.rs.po.returnPo.FoodSourceAccessoryReturn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodSourceAccessoryService extends BaseService<FoodSourceAccessory> {
	@Autowired
	private IFoodSourceAccessoryDao foodSourceAccessoryDao;
	
	public List<FoodSourceAccessoryReturn> findByAll_app(String foodsourcedetail_code){
		return foodSourceAccessoryDao.findByAll_app(foodsourcedetail_code);
	}
	
	public List<FoodSourceAccessoryReturn> findByFoodSource(String foodsource_code){
		return foodSourceAccessoryDao.findByFoodSource(foodsource_code);
	}
	
	public Integer deleteByFoodSource(String foodsource_code){
		return foodSourceAccessoryDao.deleteByFoodSource(foodsource_code);
	}
}
