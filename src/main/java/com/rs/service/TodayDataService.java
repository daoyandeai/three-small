package com.rs.service;
import com.rs.dao.ITodayDataDao;
import com.rs.po.TodayData;
import com.rs.po.User;
import com.rs.util.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 
 * @ClassName: TodayDataService
 * @Description: 大数据统计服务层接口
 * @Author tangsh
 * @DateTime 2020年5月25日 下午3:14:30
 */
@Service
public class TodayDataService extends BaseService<TodayData>{
	@Autowired
	private ITodayDataDao todayDataDao;
	
	public TodayData findTodatUsr(TodayData form,User user) {
		Integer user_level = Integer.valueOf(user.getUser_level());
		form.setProvince_conduct(user.getUser_province());
		if(user_level>=2) {
			form.setCity_conduct(user.getUser_city());
		}
		if(user_level>=3) {
			form.setArea_conduct(user.getUser_area());
		}
		if(user_level>=4) {
			form.setTown_conduct(user.getUser_town());
		}
		if(user_level>=5) {
			form.setVill_conduct(user.getUser_vill());
		}
		form.setUser_level(user.getUser_level());
		
		//查询证件不齐人数   以及总乡厨人数
		TodayData missCardChefData=todayDataDao.findMissCardChefCount(form);
		form.setMissCardChefCount(missCardChefData.getMissCardChefCount());		
		form.setAllChefCount(missCardChefData.getAllChefCount());
		
		//查询健康证  过期  即将过期    总数
		TodayData healthChefCountData=todayDataDao.findMissHealthChefCount(form);
		form.setDatechefcount(healthChefCountData.getDatechefcount());
		form.setDatewilloutchefcount(healthChefCountData.getDatewilloutchefcount());
		form.setDateoutchefcount(healthChefCountData.getDateoutchefcount());
		
		form.setPxYear(DateUtil.getDateYear());
		form.setPager_list(todayDataDao.findTrainUserList(form));
		return form;
	}
	
	public TodayData findTodatBanquet(TodayData form,User user) {
		Integer user_level = Integer.valueOf(user.getUser_level());
		TodayData todayData = new TodayData();
		form.setProvince_conduct(user.getUser_province());
		if(user_level>=2) {
			form.setCity_conduct(user.getUser_city());
			todayData.setCity_conduct(user.getUser_city());
		}
		if(user_level>=3) {
			form.setArea_conduct(user.getUser_area());
			todayData.setArea_conduct(user.getUser_area());
		}
		if(user_level>=4) {
			form.setTown_conduct(user.getUser_town());
			todayData.setTown_conduct(user.getUser_town());
		}
		if(user_level>=5) {
			form.setVill_conduct(user.getUser_vill());
			todayData.setVill_conduct(user.getUser_vill());
		}
		form.setUser_level(user.getUser_level());
		// 查询 count --------------------------开始
		TodayData todayDiffCount = todayDataDao.findTodayDiffCount(todayData);
		if(todayDiffCount!=null) {
			form.setTodaybanquetcount(todayDiffCount.getTodaybanquetcount());
		}
		form.setPager_list(todayDataDao.findCheckList(form));
		return form;
	}
	
	public TodayData findTodatNoPassReport(TodayData form,User user) {
		Integer user_level = Integer.valueOf(user.getUser_level());
		form.setProvince_conduct(user.getUser_province());
		if(user_level>=2) {
			form.setCity_conduct(user.getUser_city());
		}
		if(user_level>=3) {
			form.setArea_conduct(user.getUser_area());
		}
		if(user_level>=4) {
			form.setTown_conduct(user.getUser_town());
		}
		if(user_level>=5) {
			form.setVill_conduct(user.getUser_vill());
		}
		form.setUser_level(user.getUser_level());
		
		form.setPager_list(todayDataDao.findReportListNoPass(form));
		return form;
	}
	
	public TodayData findTodatReport(TodayData form,User user) {
		Integer user_level = Integer.valueOf(user.getUser_level());
		form.setProvince_conduct(user.getUser_province());
		if(user_level>=2) {
			form.setCity_conduct(user.getUser_city());
		}
		if(user_level>=3) {
			form.setArea_conduct(user.getUser_area());
		}
		if(user_level>=4) {
			form.setTown_conduct(user.getUser_town());
		}
		if(user_level>=5) {
			form.setVill_conduct(user.getUser_vill());
		}
		form.setUser_level(user.getUser_level());
		
		form.setAddReportCount(todayDataDao.findAddReport(form));// 今日新增加报备数
		form.setPager_list(todayDataDao.findReportList(form));
		return form;
	}
	
	public TodayData findOutCheckList(TodayData form,User user) {
		Integer user_level = Integer.valueOf(user.getUser_level());
		TodayData todayData = new TodayData();
		form.setProvince_conduct(user.getUser_province());
		if(user_level>=2) {
			form.setCity_conduct(user.getUser_city());
			todayData.setCity_conduct(user.getUser_city());
		}
		if(user_level>=3) {
			form.setArea_conduct(user.getUser_area());
			todayData.setArea_conduct(user.getUser_area());
		}
		if(user_level>=4) {
			form.setTown_conduct(user.getUser_town());
			todayData.setTown_conduct(user.getUser_town());
		}
		if(user_level>=5) {
			form.setVill_conduct(user.getUser_vill());
			todayData.setVill_conduct(user.getUser_vill());
		}
		form.setUser_level(user.getUser_level());
		
		form.setPager_list(todayDataDao.findOutCheckList(form));
		return form;
	}
	
	
	public TodayData findTodatOutUsr(TodayData form,User user) {
		Integer user_level = Integer.valueOf(user.getUser_level());
		form.setProvince_conduct(user.getUser_province());
		if(user_level>=2) {
			form.setCity_conduct(user.getUser_city());
		}
		if(user_level>=3) {
			form.setArea_conduct(user.getUser_area());
		}
		if(user_level>=4) {
			form.setTown_conduct(user.getUser_town());
		}
		if(user_level>=5) {
			form.setVill_conduct(user.getUser_vill());
		}
		form.setUser_level(user.getUser_level());
		form.setPxYear(DateUtil.getDateYear());
		form.setPager_list(todayDataDao.findTrainOutUserList(form));
		return form;
	}
	
	public User findUserList(TodayData form,User user) {
		Integer user_level = Integer.valueOf(user.getUser_level());
		form.setProvince_conduct(user.getUser_province());
		if(user_level>=2) {
			form.setCity_conduct(user.getUser_city());
		}
		if(user_level>=3) {
			form.setArea_conduct(user.getUser_area());
		}
		if(user_level>=4) {
			form.setTown_conduct(user.getUser_town());
		}
		if(user_level>=5) {
			form.setVill_conduct(user.getUser_vill());
		}
		form.setUser_level(user.getUser_level());
		if("1".equals(form.getSerachState())) {
			user.setUserlist(todayDataDao.findUserListAjax(form));
		}
		if("2".equals(form.getSerachState())) {
			user.setUserlist(todayDataDao.findWillOutUserListAjax(form));
		}
		if("3".equals(form.getSerachState())) {
			user.setUserlist(todayDataDao.findOutUserListAjax(form));
		}
		user.setSerachState(form.getSerachState());
		return user;
	}
	
	public TodayData findDeviceList(TodayData form) {
		form.setPager_list(todayDataDao.findDeviceList(form));
		form.setDevicecount(todayDataDao.findAllDeviceCount());
		return form;
	}
}
