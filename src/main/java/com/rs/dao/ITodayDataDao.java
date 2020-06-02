package com.rs.dao;
import com.rs.po.TodayData;
import com.rs.po.User;

import java.util.List;

import org.springframework.stereotype.Repository;
/**
 * 
 * @ClassName: ITodayDataDao
 * @Description: 统计数据层接口
 * @Author tangsh
 * @DateTime 2020年5月25日 下午3:12:22
 */
@Repository
public interface ITodayDataDao extends IBaseDao<TodayData> {
	Integer findAddReport(TodayData form);
	int findReportCount(TodayData form);
	List<TodayData> findReportList(TodayData form);
	TodayData findCountByDevice();
	TodayData findTodayDiffCount(TodayData form);
	List<TodayData> findCheckList(TodayData form);
	TodayData findMissCardChefCount(TodayData form);
	TodayData findMissHealthChefCount(TodayData form);
	List<TodayData> findTrainUserList(TodayData form);
	int findTrainUserCount(TodayData form);
	List<TodayData> findBanquetMapByAjax(TodayData form);
	int findWeekDayReoprtDataCount(TodayData form);
	List<TodayData> findWeekDayReoprtData(TodayData form);
	List<TodayData> findWeekDayCountGroupByConduct(TodayData form);
	List<TodayData> findWeekDayCountGroupByType(TodayData form);
	List<TodayData> findReportListNoPass(TodayData form);
	List<TodayData> findDeviceList(TodayData form);
	List<User> findUserListAjax(TodayData form);
	List<User> findWillOutUserListAjax(TodayData form);
	List<User> findOutUserListAjax(TodayData form);
	List<TodayData> findOutCheckList(TodayData form);
	List<TodayData> findTrainOutUserList(TodayData form);
	TodayData findAllAndWeekAddReportCount(TodayData form);
	TodayData findAllAndWeekAddChefCount(TodayData form);
	TodayData findAllAndWeekAddGlCount(TodayData form);
	Integer findAllDeviceCount();
}
