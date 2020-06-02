package com.rs.service;
import com.rs.dao.IAdviceDao;
import com.rs.po.Advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 执法建议数据服务层
 * @ClassName: TrainService
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月12日 下午4:10:31
 */
@Service
public class AdviceService extends BaseService<Advice>{
	
	@Autowired
	private IAdviceDao adviceDao;
	
	public Advice findLastByCompany(String company_code) {
		return adviceDao.findLastByCompany(company_code);
	}
	
}



