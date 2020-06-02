package com.rs.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rs.po.PageConfig;
import com.rs.po.returnPo.PageConfigReturn;
/**
 * 页面读取参数配置数据接口层
 * @ClassName: IPageConfigDao
 * @Description: 
 * @Author sven
 * @DateTime 2020年2月10日 上午11:57:29
 */
@Repository
public interface IPageConfigDao extends IBaseDao<PageConfig> {
	/**
	 * 
	 * @Title: findByList_app
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月10日 下午2:42:27
	 * @param form
	 * @return
	 */
	List<PageConfigReturn> findByList_app(PageConfig form);
	/**
	 * 
	 * @Title: findByAll_app
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月10日 下午2:42:39
	 * @param form
	 * @return
	 */
	List<PageConfigReturn> findByAll_app(PageConfig form);
	/**
	 * 
	 * @Title: findByCode_app
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月10日 下午2:43:02
	 * @param form
	 * @return
	 */
	PageConfigReturn findByCode_app(PageConfig form);
}