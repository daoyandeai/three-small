package com.rs.dao;

import com.rs.po.Category;
import org.springframework.stereotype.Repository;

/**
 * 
 * @ClassName: ICategoryDao
 * @Description: 分类数据接口层 
 * @Author tangsh
 * @DateTime 2019年12月18日 下午1:17:38
 */
@Repository
public interface ICategoryDao extends IBaseDao<Category> {
}