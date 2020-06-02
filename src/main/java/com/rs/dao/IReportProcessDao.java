package com.rs.dao;
import com.rs.po.ReportProcess;
import com.rs.po.returnPo.ReportProcessReturn;

import org.springframework.stereotype.Repository;
/**
 * 
 * @ClassName: IReportProcessDao
 * @Description: 加工场地卫生设施数据层接口
 * @Author tangsh
 * @DateTime 2020年4月13日 下午5:59:31
 */
@Repository
public interface IReportProcessDao extends IBaseDao<ReportProcess> {
	/**
	 * 
	 * @Title: findByReportCode
	 * @Description: 根据报备系统编码查询加工场地卫生设施数据
	 * @Author tangsh
	 * @DateTime 2020年4月13日 下午6:01:44
	 * @param report_code
	 * @return
	 */
	ReportProcess findByReportCode(String report_code);
	/**
	 * 微信端根据报备系统编码查询加工场地卫生设施数据
	 * @Title: findByReportCode_app
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月17日 下午4:53:12
	 * @param report_code
	 * @return
	 */
	ReportProcessReturn findByReportCode_app(String report_code);
}
