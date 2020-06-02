package com.rs.service;
import com.rs.dao.IReportCheckDao;
import com.rs.po.ReportCheck;
import com.rs.po.returnPo.ReportCheckReturn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 
 * @ClassName: ReportCheckService
 * @Description: 群宴报备检查服务层接口
 * @Author tangsh
 * @DateTime 2020年4月13日 上午10:32:37
 */
@Service
public class ReportCheckService extends BaseService<ReportCheck>{
	@Autowired
    private IReportCheckDao reportCheckDao;
	
	public ReportCheckReturn findByReportCode(String report_code) {
		return reportCheckDao.findByReportCode(report_code);
	}
}
