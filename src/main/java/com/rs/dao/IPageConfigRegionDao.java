package com.rs.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rs.po.PageConfigRegion;
import com.rs.po.returnPo.PageConfigRegionReturn;
/**
 * 页面参数模板区域编码数据接口层
 * @ClassName: IPageConfigRegionDao
 * @Description: 
 * @Author sven
 * @DateTime 2020年2月10日 上午11:57:29
 */
@Repository
public interface IPageConfigRegionDao extends IBaseDao<PageConfigRegion> {
	/**
	 * 分页查询
	 * @Title: findByList_app
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月10日 下午2:42:27
	 * @param form
	 * @return
	 */
	List<PageConfigRegionReturn> findByList_app(PageConfigRegion form);
	/**
	 * 查询所有
	 * @Title: findByAll_app
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月10日 下午2:42:39
	 * @param form
	 * @return
	 */
	List<PageConfigRegionReturn> findByAll_app(PageConfigRegion form);
	/**
	 * 根据编码返回
	 * @Title: findByCode_app
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月10日 下午2:43:02
	 * @param form
	 * @return
	 */
	PageConfigRegionReturn findByCode_app(PageConfigRegion form);
	/**
	 * 联合查询
	 * @Title: findJoin
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月2日 下午5:08:52
	 * @param form
	 * @return
	 */
	List<PageConfigRegionReturn> findJoin(PageConfigRegion form);
	/**
	 * 复合更新
	 * @Title: updateJoin
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月4日 上午10:15:50
	 * @param form
	 * @return
	 */
	int updateJoin(PageConfigRegion form);
}