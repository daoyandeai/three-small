package com.rs.dao;
import com.rs.po.CompanyArea;
import com.rs.po.returnPo.CompanyAreaReturn;

import java.util.List;

import org.springframework.stereotype.Repository;
/**
 * 企业区域数据层接口
 * @ClassName: ICompanyAreaDao
 * @Description: 
 * @Author sven
 * @DateTime 2020年3月16日 上午11:08:18
 */
@Repository
public interface ICompanyAreaDao extends IBaseDao<CompanyArea> {
	/**
	 * 
	 * @Title: updateStatus
	 * @Description: 更新状态
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:02:48
	 * @param form
	 * @return
	 */
	int updateStatus(CompanyArea form);
	
	/**
	 * findByList_app
	 * @Title: findByList_app
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月16日 上午11:17:28
	 * @param form
	 * @return
	 */
	List<CompanyAreaReturn> findByList_app(CompanyArea form);
	/**
	 * findByCode_app
	 * @Title: findByCode_app
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月16日 上午11:17:34
	 * @param form
	 * @return
	 */
	CompanyAreaReturn findByCode_app(CompanyArea form);
}
