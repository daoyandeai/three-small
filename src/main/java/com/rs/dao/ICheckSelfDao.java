package com.rs.dao;
import com.rs.po.CheckSelf;
import com.rs.po.PageConfigRegion;
import com.rs.po.User;

import java.util.List;

import org.springframework.stereotype.Repository;
/**
 * 
 * @ClassName: ICheckSelfDao
 * @Description: 自查自纠数据层接口
 * @Author tangsh
 * @DateTime 2020年3月2日 上午11:47:38
 */
@Repository
public interface ICheckSelfDao extends IBaseDao<CheckSelf> {
	/**
	 * 
	 * @Title: findListByCompany
	 * @Description: 根据企业查询自查自纠列表
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:01:04
	 * @param form
	 * @return
	 */
	List<CheckSelf> findListByCompany(CheckSelf form);
	/**
	 * 
	 * @Title: findCountByCompany
	 * @Description: 根据企业查询自查自纠总数
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:01:24
	 * @param form
	 * @return
	 */
	Integer findCountByCompany(CheckSelf form);
	
	/**
	 * 
	 * @Title: findListByNotCSCompany
	 * @Description: 查询没有自查自纠的企业列表
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:01:32
	 * @param form
	 * @return
	 */
	List<CheckSelf> findListByNotCSCompany(CheckSelf form);
	/**
	 * 
	 * @Title: findCountByNotCSCompany
	 * @Description: 查询没有自查自纠的企业总数
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:01:47
	 * @param form
	 * @return
	 */
	Integer findCountByNotCSCompany(CheckSelf form);
	
	/**
	 * 
	 * @Title: findByPageConfig
	 * @Description: 查询配置项
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:02:08
	 * @param user
	 * @return
	 */
	List<PageConfigRegion> findByPageConfig(User user);
	/**
	 * 
	 * @Title: findByCompanyLast
	 * @Description: 查询企业最后一条自查自纠数据
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:02:19
	 * @param company_code
	 * @return
	 */
	CheckSelf findByCompanyLast(String company_code);
}
