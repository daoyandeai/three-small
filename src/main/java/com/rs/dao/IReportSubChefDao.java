package com.rs.dao;
import com.rs.po.ReportSubChef;
import com.rs.po.returnPo.ReportSubChefReturn;

import java.util.List;

import org.springframework.stereotype.Repository;
/**
 * 
 * @ClassName: IReportSubChefDao
 * @Description: 群宴报备帮厨数据层接口
 * @Author tangsh
 * @DateTime 2020年4月13日 上午10:46:44
 */
@Repository
public interface IReportSubChefDao extends IBaseDao<ReportSubChef> {
	/**
	 * 查询满足条件的报备
	 * @Title: findByAll_app
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月15日 下午4:21:54
	 * @return
	 */
	List<ReportSubChefReturn> findByAll_app(ReportSubChef form);
	/**
	 * 根据报备删除帮厨
	 * @Title: deleteByReport
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月17日 下午5:02:06
	 * @param report_code
	 * @return
	 */
	Integer deleteByReport(String report_code);
}
