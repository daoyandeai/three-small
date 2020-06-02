package com.rs.service;
import com.rs.dao.IMainSubChefDao;
import com.rs.po.MainSubChef;
import com.rs.po.returnPo.MainSubChefReturn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 
 * @ClassName: MainSubChefService
 * @Description: 帮厨服务层接口
 * @Author tangsh
 * @DateTime 2020年4月8日 上午9:54:46
 */
@Service
public class MainSubChefService extends BaseService<MainSubChef>{
	
	@Autowired
	private IMainSubChefDao mainSubChefDao;
	
	public List<MainSubChefReturn> findByList_app(MainSubChef form){
		return mainSubChefDao.findByList_app(form);
	}
	
	public MainSubChefReturn findByCode_app(MainSubChef form){
		return mainSubChefDao.findByCode_app(form);
	}
}
