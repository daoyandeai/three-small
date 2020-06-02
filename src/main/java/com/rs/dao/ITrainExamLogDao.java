package com.rs.dao;
import com.rs.po.TrainExamLog;
import org.springframework.stereotype.Repository;

/**
 * 培训试题日志数据层接口
 * @ClassName: ITrainExamLogDao
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月10日 下午3:12:01
 */
@Repository
public interface ITrainExamLogDao extends IBaseDao<TrainExamLog>{
	/**
	 * 查询是否通过考试
	 * @Title: findIsPass
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月10日 下午3:12:39
	 * @param form
	 * @return
	 */
	int findIsPass(TrainExamLog form);
	/**
	 * 查询网格员下企业培训未通过数量
	 * @Title: findUnPassCountInInfo
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月5日 下午8:10:41
	 * @param form
	 * @return
	 */
	int findUnPassCount(TrainExamLog form);
	/**
	 * 查询考试|通过人数
	 * @Title: findExamUserCount
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月23日 下午5:35:39
	 * @param form
	 * @return
	 */
	int findExamUserCount(TrainExamLog form);
}