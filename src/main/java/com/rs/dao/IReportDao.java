package com.rs.dao;
import com.rs.po.Report;
import com.rs.po.returnPo.ReportReturn;

import java.util.List;

import org.springframework.stereotype.Repository;
/**
 * 
 * @ClassName: IReportDao
 * @Description: 群宴报备数据层接口
 * @Author tangsh
 * @DateTime 2020年4月13日 上午10:01:04
 */
@Repository
public interface IReportDao extends IBaseDao<Report> {
	List<Report> findReportUser_Join(Report form);
	Report findByCode_detail(Report form);
	
	/**
	 * 微信端根据编码查询
	 * @Title: findByCode_app
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月16日 上午10:24:31
	 * @param form
	 * @return
	 */
	ReportReturn findByCode_app(Report form);
	
	/**
	 * 
	 * @Title: findToDayBanquet
	 * @Description: 根据条件查询报备
	 * @Author tangsh
	 * @DateTime 2020年5月25日 下午3:51:58
	 * @param report
	 * @return
	 */
	Report findToDayBanquet(Report report);
	
	/**
	 * 
	 * @Title: findCountByToDayBanquet
	 * @Description: 报备统计
	 * @Author tangsh
	 * @DateTime 2020年5月25日 下午4:11:49
	 * @param form
	 * @return
	 */
	List<Report> findCountByToDayBanquet(Report form);
}
