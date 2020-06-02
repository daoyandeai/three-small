package com.rs.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rs.po.MessLog;
import com.rs.po.returnPo.MessLogReturn;
/**
 * 
 * @ClassName: IMessLogDao
 * @Description: 消息日志数据接口层
 * @Author tangsh
 * @DateTime 2020年3月2日 上午10:55:38
 */
@Repository
public interface IMessLogDao extends IBaseDao<MessLog> {
	
	/**
	 * 
	 * @Title: findByList_app
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月10日 下午2:42:27
	 * @param form
	 * @return
	 */
	List<MessLogReturn> findByList_app(MessLog form);
}