package com.rs.service;
import com.rs.dao.IReportDao;
import com.rs.po.Report;
import com.rs.po.User;
import com.rs.po.returnPo.ReportReturn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 
 * @ClassName: ReportService
 * @Description: 群宴报备服务层接口
 * @Author tangsh
 * @DateTime 2020年4月13日 上午10:05:22
 */
@Service
public class ReportService extends BaseService<Report>{
	@Autowired
    private IReportDao reportDao;
	
	public List<Report> findReportUser_Join(Report form){
		return reportDao.findReportUser_Join(form);
	}
	
	public Report findByCode_detail(Report form) {
		return reportDao.findByCode_detail(form);
	}
	
	public ReportReturn findByCode_app(Report form) {
		return reportDao.findByCode_app(form);
	}
	
	public Report findCountByToDayBanquet(Report form,User user) {
		Integer user_level = Integer.valueOf(user.getUser_level());
		form.setProvince_conduct(user.getUser_province());
		if (user_level >= 2) {
			form.setCity_conduct(user.getUser_city());
		}
		if (user_level >= 3) {
			form.setArea_conduct(user.getUser_area());
		}
		if (user_level >= 4) {
			form.setTown_conduct(user.getUser_town());
		}
		if (user_level >= 5) {
			form.setVill_conduct(user.getUser_vill());
		}
		StringBuffer sb = new StringBuffer();
		sb.append("'锦江区',").append("'青羊区',").append("'金牛区',").append("'武侯区',").append("'成华区',").append("'龙泉驿区',")
		.append("'青白江区',").append("'新都区',").append("'温江区',").append("'金堂县',").append("'双流区',")
		.append("'郫都区',").append("'大邑县',").append("'新津县',").append("'都江堰市',").append("'彭州市',").append("'邛崃市',")
		.append("'崇州市',").append("'高新区',").append("'天府新区',").append("'简阳市',").append("'蒲江县'");
		form.setArea_conducts(sb.toString());
		form.setPager_list(reportDao.findCountByToDayBanquet(form));
		return form;
	}
}
