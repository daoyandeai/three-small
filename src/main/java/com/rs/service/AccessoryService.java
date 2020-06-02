package com.rs.service;

import com.rs.dao.IAccessoryDao;
import com.rs.po.Accessory;
import com.rs.po.returnPo.AccessoryReturn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessoryService extends BaseService<Accessory> {
	@Autowired
	private IAccessoryDao accessoryDao;
	
	public Accessory findByOne(Accessory form) {
		return accessoryDao.findByOne(form);
	}
	
	public List<AccessoryReturn> findByAll_app(Accessory form) {
		return accessoryDao.findByAll_app(form);
	}
	
}
