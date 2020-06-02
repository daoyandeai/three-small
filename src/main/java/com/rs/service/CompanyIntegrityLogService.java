package com.rs.service;
import com.rs.dao.ICompanyIntegrityLogDao;
import com.rs.po.CompanyIntegrityLog;
import com.rs.po.returnPo.CompanyIntegrityLogReturn;

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
public class CompanyIntegrityLogService extends BaseService<CompanyIntegrityLog> {
	@Autowired
	private ICompanyIntegrityLogDao companyIntegrityLogDao;
	
	public List<CompanyIntegrityLogReturn> findByList_app(CompanyIntegrityLog form){
		return companyIntegrityLogDao.findByList_app(form);
	}
}
