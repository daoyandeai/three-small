package com.rs.service;
import com.rs.dao.IReportProcessDao;
import com.rs.po.ReportProcess;
import com.rs.po.returnPo.ReportProcessReturn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 
 * @ClassName: ReportProcessService
 * @Description: 加工场地卫生设施服务层接口
 * @Author tangsh
 * @DateTime 2020年4月13日 下午6:00:37
 */
@Service
public class ReportProcessService extends BaseService<ReportProcess>{
	
	@Autowired
	private IReportProcessDao reportProcessDao;
	
	public ReportProcess findByReportCode(String report_code) {
		return reportProcessDao.findByReportCode(report_code);
	}
	
	public ReportProcessReturn findByReportCode_app(String report_code) {
		return reportProcessDao.findByReportCode_app(report_code);
	}
}
