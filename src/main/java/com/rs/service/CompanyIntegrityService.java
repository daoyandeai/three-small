package com.rs.service;
import com.rs.dao.ICompanyIntegrityDao;
import com.rs.po.CompanyIntegrity;
import com.rs.po.returnPo.CompanyIntegrityReturn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 企业诚信评价日志服务层
 * @ClassName: CompanyIntegrityLogService
 * @Description: 
 * @Author sven
 * @DateTime 2020年2月12日 下午12:04:03
 */
@Service
public class CompanyIntegrityService extends BaseService<CompanyIntegrity> {
	@Autowired
	private ICompanyIntegrityDao companyIntegrityDao;
	
	public List<CompanyIntegrityReturn> findByList_app(CompanyIntegrity form){
		return companyIntegrityDao.findByList_app(form);
	}
	
	public CompanyIntegrity findLast(CompanyIntegrity form){
		return companyIntegrityDao.findLast(form);
	}
	
}
