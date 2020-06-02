package com.rs.dao;
import com.rs.po.ReportCheck;
import com.rs.po.returnPo.ReportCheckReturn;

import org.springframework.stereotype.Repository;
/**
 * 
 * @ClassName: IReportCheckDao
 * @Description: 群宴报备检查数据层接口
 * @Author tangsh
 * @DateTime 2020年4月13日 上午10:31:57
 */
@Repository
public interface IReportCheckDao extends IBaseDao<ReportCheck> {
	/**
	 * 
	 * @Title: findByReportCode
	 * @Description: 根据群宴报备编码查询检查数据
	 * @Author tangsh
	 * @DateTime 2020年4月13日 上午10:42:48
	 * @param report_code
	 * @return
	 */
	ReportCheckReturn findByReportCode(String report_code);
}
