package com.rs.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * 
 * @ClassName: IBaseDao
 * @Description: 基础数据接口层
 * @Author tangsh
 * @DateTime 2019年12月18日 下午1:17:26
 * @param <T>
 */
@Repository
public interface IBaseDao<T> {
	/**
	 * 
	 * @Title: save
	 * @Description: 保存
	 * @Author tangsh
	 * @param t
	 * @return
	 */
	Integer save(T t);
	/**
	 * 
	 * @Title: save
	 * @Description: 批量保存
	 * @Author tangsh
	 * @param t
	 * @return
	 */
	Integer saveBatch(List<T> t);
	/**
	 * 
	 * @Title: delete
	 * @Description: 删除
	 * @Author tangsh
	 * @param t
	 * @return
	 */
	Integer delete(T t);
	/**
	 * 
	 * @Title: update
	 * @Description: 修改
	 * @Author tangsh
	 * @param t
	 * @return
	 */
	Integer update(T t);
	/**
	 * 批量修改
	 * @Title: updateBatch
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年8月1日 下午4:19:56
	 * @param t
	 * @return
	 */
	Integer updateBatch(List<T> t);
	/**
	 * 
	 * @Title: findByCode
	 * @Description: 查询一个
	 * @Author tangsh
	 * @param id
	 * @return
	 */
	T findByCode(Object obj);
	/**
	 * 
	 * @Title: findByAll
	 * @Description: 查询所有
	 * @Author tangsh
	 * @param t
	 * @return
	 */
	List<T> findByAll(T t);
	/**
	 * 
	 * @Title: findByList
	 * @Description: 查询数据列表
	 * @Author tangsh
	 * @param t
	 * @return
	 */
	List<T> findByList(T t);
	/**
	 * 
	 * @Title: findByCount
	 * @Description: 查询数据量
	 * @Author tangsh
	 * @param t
	 * @return
	 */
	Integer findByCount(T t);
	/**
	 * 查询是否存在
	 * @Title: findByExist
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月10日 下午3:03:35
	 * @param t
	 * @return
	 */
	int findByExist(T t);
}
