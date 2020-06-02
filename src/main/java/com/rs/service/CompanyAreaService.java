package com.rs.service;
import com.rs.dao.ICompanyAreaDao;
import com.rs.po.CompanyArea;
import com.rs.po.returnPo.CompanyAreaReturn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 企业区域服务层接口
 * @ClassName: CompanyAreaService
 * @Description: 
 * @Author sven
 * @DateTime 2020年3月16日 上午11:11:38
 */
@Service
public class CompanyAreaService extends BaseService<CompanyArea>{
	@Autowired
	private ICompanyAreaDao companyAreaDao;
	
	public int updateStatus(CompanyArea form) {
		return companyAreaDao.updateStatus(form);
	}
	
	public List<CompanyAreaReturn> findByList_app(CompanyArea form){
		return companyAreaDao.findByList_app(form);
	}
	
	public CompanyAreaReturn findByCode_app(CompanyArea form){
		return companyAreaDao.findByCode_app(form);
	}
}
