package com.rs.dao;

import org.springframework.stereotype.Repository;

import com.rs.po.Producetype;

/**
 * 
 * @ClassName: IProducetypeDao
 * @Description: 生产类别数据接口层
 * @Author tangsh
 * @DateTime 2019年12月18日 下午1:23:02
 */
@Repository
public interface IProducetypeDao extends IBaseDao<Producetype> {
	/**
	 * 
	 * @Title: insert
	 * @Description: 新增数据
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:16:26
	 * @param record
	 * @return
	 */
    int insert(Producetype record);
    /**
     * 
     * @Title: insertSelective
     * @Description: 新增数据
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:16:52
     * @param record
     * @return
     */
    int insertSelective(Producetype record);
}