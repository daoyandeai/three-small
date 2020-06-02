package com.rs.dao;

import com.rs.po.Dictionary;
import com.rs.po.returnPo.DictionaryReturn;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * @ClassName: IDictionaryDao
 * @Description: 字典表数据接口层
 * @Author tangsh
 * @DateTime 2019年12月18日 下午1:21:03
 */
@Repository
public interface IDictionaryDao extends IBaseDao<Dictionary> {
	/**
	 * 
	 * @Title: findBySearch
	 * @Description: 根据字典信息查询数据
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:12:21
	 * @param form
	 * @return
	 */
    List<Dictionary> findBySearch(Dictionary form);
    /**
     * 查询字典列表
     * @Title: findByList_app
     * @Description: 
     * @Author sven
     * @DateTime 2020年3月9日 上午10:57:42
     * @param form
     * @return
     */
	List<DictionaryReturn> findByList_app(Dictionary form);
	/**
	 * 查询单条字段
	 * @Title: findByCode_app
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月10日 下午2:43:02
	 * @param form
	 * @return
	 */
	DictionaryReturn findByCode_app(Dictionary form);
}