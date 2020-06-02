package com.rs.dao;
import java.util.List;
import com.rs.po.Train;
import org.springframework.stereotype.Repository;
/**
 * 培训数据层接口
 * @ClassName: ITrainDao
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月10日 下午2:55:46
 */
@Repository
public interface ITrainDao extends IBaseDao<Train>{
	/**
	 * 更新状态
	 * @Title: updateStatus
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月10日 下午2:57:12
	 * @param form
	 * @return
	 */
	 int updateStatus(Train form);
	 /**
	  * 根据权限查询所有记录_不分页
	  * @Title: findAllByAuth
	  * @Description: 
	  * @Author sven
	  * @DateTime 2019年12月10日 下午2:57:16
	  * @param form
	  * @return
	  */
	 List<Train> findAllByAuth(Train form);
	 /**
	  * 分组查询培训年度
	  * @Title: findAllGroupByYear
	  * @Description: 
	  * @Author sven
	  * @DateTime 2019年12月10日 下午2:57:35
	  * @param form
	  * @return
	  */
	 List<Train> findAllGroupByYear(Train form);
	 /**
	  * 根据权限查询所有记录_分页
	  * @Title: findListByAuth
	  * @Description: 
	  * @Author sven
	  * @DateTime 2019年12月10日 下午2:57:38
	  * @param form
	  * @return
	  */
	 List<Train> findListByAuth(Train form);
	 /**
	  * 据权限查询所有记录数_分页
	  * @Title: findCountByAuth
	  * @Description: 
	  * @Author sven
	  * @DateTime 2019年12月10日 下午2:57:46
	  * @param form
	  * @return
	  */
	 int findCountByAuth(Train form);
}