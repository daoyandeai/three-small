package com.rs.dao;

import org.springframework.stereotype.Repository;

import com.rs.po.CompanyType;
/**
 * 食品监管分类数据接口层
 * @ClassName: ICompanyTypeDao
 * @Description: 
 * @Author sven
 * @DateTime 2020年2月10日 上午11:57:52
 */
@Repository
public interface ICompanyTypeDao extends IBaseDao<CompanyType> {
}