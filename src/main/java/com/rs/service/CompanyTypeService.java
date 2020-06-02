package com.rs.service;

import com.rs.dao.ICompanyTypeDao;
import com.rs.po.CompanyType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @ClassName: CompanyTypeService
 * @Description: 企业类型服务层
 * @Author tangsh
 * @DateTime 2019年12月18日 下午1:26:21
 */
@Service
public class CompanyTypeService extends BaseService<CompanyType> {
	@Autowired
	private ICompanyTypeDao companyTypeDao;
	
	public CompanyType findByOne(String companytype_code){
		CompanyType ct = new CompanyType();
		ct.setCompanytype_code(companytype_code);
		return companyTypeDao.findByCode(ct);
	};
}
