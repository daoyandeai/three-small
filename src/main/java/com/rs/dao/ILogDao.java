package com.rs.dao;

import org.springframework.stereotype.Repository;
import com.rs.po.Log;

/**
 * 
 * @ClassName: ILogDao
 * @Description: 日志数据接口层
 * @Author tangsh
 * @DateTime 2019年12月18日 下午1:22:29
 */
@Repository
public interface ILogDao extends IBaseDao<Log> {
	/**
	 * 
	 * @Title: findLastByCompanyCode
	 * @Description: genuine企业编码查询日志
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:14:28
	 * @param company_code
	 * @return
	 */
	Log findLastByCompanyCode(String company_code);
}