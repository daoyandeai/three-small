package com.rs.dao;
import com.rs.po.PatrolConfig;


import org.springframework.stereotype.Repository;
/**
 * 
 * @ClassName: IPatrolConfigDao
 * @Description: 巡查配置数据层接口
 * @Author tangsh
 * @DateTime 2020年1月20日 上午9:30:40
 */
@Repository
public interface IPatrolConfigDao extends IBaseDao<PatrolConfig> {
	/**
	 * 
	 * @Title: findByCompanyCodeToPatrolConfig
	 * @Description: 根据类型编码查询配置
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:16:01
	 * @param companytag_code
	 * @return
	 */
	public PatrolConfig findByCompanyCodeToPatrolConfig(String companytag_code);
	
	/**
	 * 
	 * @Title: findByObj
	 * @Description: 查询一个对象
	 * @Author tangsh
	 * @DateTime 2020年5月9日 下午4:17:29
	 * @param form
	 * @return
	 */
	public PatrolConfig findByObj(PatrolConfig form);
}
