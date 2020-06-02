package com.rs.dao;
import com.rs.po.Config;

import org.springframework.stereotype.Repository;
/**
 * 
 * @ClassName: IConfigDao
 * @Description: 群宴全局配置数据层接口
 * @Author tangsh
 * @DateTime 2020年4月8日 下午3:01:28
 */
@Repository
public interface IConfigDao extends IBaseDao<Config> {
	/**
	 * 
	 * @Title: findByInclude
	 * @Description: 全局配置对象是否包括查询
	 * @Author tangsh
	 * @DateTime 2020年4月8日 下午3:01:20
	 * @param form
	 * @return
	 */
	Integer findByInclude(String form);
}
