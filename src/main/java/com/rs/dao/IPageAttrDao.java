package com.rs.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.rs.po.PageAttr;
import com.rs.po.returnPo.PageAttrReturn;
/**
 * 页面属性数据接口层
 * @ClassName: IPageAttrDao
 * @Description: 
 * @Author sven
 * @DateTime 2020年2月11日 上午10:13:45
 */
@Repository
public interface IPageAttrDao extends IBaseDao<PageAttr> {
	/**
	 * 
	 * @Title: findByList_app
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月10日 下午2:42:27
	 * @param form
	 * @return
	 */
	List<PageAttrReturn> findByList_app(PageAttr form);
	/**
	 * 
	 * @Title: findByAll_app
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月10日 下午2:42:39
	 * @param form
	 * @return
	 */
	List<PageAttrReturn> findByAll_app(PageAttr form);
	/**
	 * 
	 * @Title: findByCode_app
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月10日 下午2:43:02
	 * @param form
	 * @return
	 */
	PageAttrReturn findByCode_app(PageAttr form);
}