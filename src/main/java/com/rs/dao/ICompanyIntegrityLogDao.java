package com.rs.dao;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.rs.po.CompanyIntegrityLog;
import com.rs.po.returnPo.CompanyIntegrityLogReturn;
/**
 * 企业诚信评价日志数据接口层
 * @ClassName: ICompanyIntegrityLogDao
 * @Description: 
 * @Author sven
 * @DateTime 2020年2月12日 下午12:02:44
 */
@Repository
public interface ICompanyIntegrityLogDao extends IBaseDao<CompanyIntegrityLog> {
	/**
	 * 
	 * @Title: findByList_app
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月12日 下午2:28:06
	 * @param form
	 * @return
	 */
	List<CompanyIntegrityLogReturn> findByList_app(CompanyIntegrityLog form);
}