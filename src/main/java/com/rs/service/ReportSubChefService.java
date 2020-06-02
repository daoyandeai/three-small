package com.rs.service;
import com.rs.dao.IReportSubChefDao;
import com.rs.po.ReportSubChef;
import com.rs.po.returnPo.ReportSubChefReturn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 
 * @ClassName: ReportSubChefService
 * @Description: 群宴报备帮厨服务层接口
 * @Author tangsh
 * @DateTime 2020年4月13日 上午10:47:25
 */
@Service
public class ReportSubChefService extends BaseService<ReportSubChef>{
	@Autowired
	private IReportSubChefDao reportSubChefDao;
	
	public List<ReportSubChefReturn> findByAll_app(ReportSubChef form){
		return reportSubChefDao.findByAll_app(form);
	}
	
	public Integer deleteByReport(String report_code) {
		return reportSubChefDao.deleteByReport(report_code);
	}
}
