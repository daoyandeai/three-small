package com.rs.dao;

import java.util.List;
import com.rs.po.News;
import org.springframework.stereotype.Repository;

/**
 * 
 * @ClassName: INewsDao
 * @Description: 新闻宣传数据层接口
 * @Author tangsh
 * @DateTime 2019年12月16日 下午3:06:29
 */
@Repository
public interface INewsDao extends IBaseDao<News>{
	/**
	 * 
	 * @Title: findListByAuth
	 * @Description: 
	 * @Author tangsh
	 * @DateTime 2019年12月16日 下午3:06:35
	 * @param form
	 * @return
	 */
	List<News> findListByAuth(News form);
	
	/**
	 * 
	 * @Title: findCountByAuth
	 * @Description: 
	 * @Author tangsh
	 * @DateTime 2019年12月16日 下午3:07:06
	 * @param form
	 * @return
	 */
	Integer findCountByAuth(News form);
	/**
	 * 
	 * @Title: findBySearch
	 * @Description: 根据新闻信息查询新闻列表数据
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:15:15
	 * @param form
	 * @return
	 */
	List<News> findBySearch(News form);
	/**
	 * 
	 * @Title: findBySearchCount
	 * @Description: 根据新闻信息查询新闻总数
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:15:28
	 * @param form
	 * @return
	 */
	int findBySearchCount(News form);
}
