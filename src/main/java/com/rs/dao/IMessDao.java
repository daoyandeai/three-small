package com.rs.dao;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.rs.po.Mess;
import com.rs.po.returnPo.MessReturn;
/**
 * 平台消息推送数据接口层
 * @ClassName: IMessDao
 * @Description: 
 * @Author sven
 * @DateTime 2020年2月25日 下午5:51:02
 */
@Repository
public interface IMessDao extends IBaseDao<Mess> {
	/**
	 * 根据消息事件查询
	 * @Title: findByType
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月25日 下午5:50:39
	 * @param form
	 * @return
	 */
	MessReturn findByEvent(Mess form);
	/**
	 * 
	 * @Title: findByList_app
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月10日 下午2:42:27
	 * @param form
	 * @return
	 */
	List<MessReturn> findByList_app(Mess form);
	/**
	 * 
	 * @Title: findByAll_app
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月10日 下午2:42:39
	 * @param form
	 * @return
	 */
	List<MessReturn> findByAll_app(Mess form);
	/**
	 * 
	 * @Title: findByCode_app
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月10日 下午2:43:02
	 * @param form
	 * @return
	 */
	MessReturn findByCode_app(Mess form);
}