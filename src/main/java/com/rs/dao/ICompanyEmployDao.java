package com.rs.dao;

import com.rs.po.CompanyEmploy;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * 
 * @ClassName: ICompanyEmployDao
 * @Description: 从业人员数据接口层
 * @Author tangsh
 * @DateTime 2019年12月18日 下午1:18:13
 */
@Repository
public interface ICompanyEmployDao extends IBaseDao<CompanyEmploy> {
	/**
	 * 
	 * @Title: deleteByCompany
	 * @Description: 根据Company删除
	 * @Author tangsh
	 * @DateTime 2019年12月26日 下午2:51:50
	 * @param form
	 * @return
	 */
	Integer deleteByCompany(String company_code);
	
	/**
	 * 
	 * @Title: findBySearchList
	 * @Description: 联查企业用户列表信息
	 * @Author tangsh
	 * @DateTime 2020年3月31日 上午10:38:11
	 * @param form
	 * @return
	 */
	List<CompanyEmploy> findBySearchList(CompanyEmploy form);
	/**
	 * 
	 * @Title: findBySearchCount
	 * @Description: 联查企业用户信息总数
	 * @Author tangsh
	 * @DateTime 2020年3月31日 上午10:38:48
	 * @param form
	 * @return
	 */
	int findBySearchCount(CompanyEmploy form);
}