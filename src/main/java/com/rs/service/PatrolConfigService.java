package com.rs.service;
import com.rs.dao.IPatrolConfigDao;
import com.rs.po.PatrolConfig;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 
 * @ClassName: PatrolConfigService
 * @Description: 巡查配置服务层接口
 * @Author tangsh
 * @DateTime 2020年1月20日 上午9:31:30
 */
@Service
public class PatrolConfigService extends BaseService<PatrolConfig>{
	
	@Autowired
	private IPatrolConfigDao patrolConfigDao;
	
	/**
	 * 微信端 通过备案号查询巡查配置项
	 * @param companytag_code
	 * @return
	 */
	public PatrolConfig findByCompanyCodeToPatrolConfig(String companytag_code){
		return patrolConfigDao.findByCompanyCodeToPatrolConfig(companytag_code);
	}
	
	/**
	 * 
	 * @Title: findByObj
	 * @Description: 查询单个对象
	 * @Author tangsh
	 * @DateTime 2020年5月9日 下午4:17:59
	 * @param form
	 * @return
	 */
	public PatrolConfig findByObj(PatrolConfig form){
		return patrolConfigDao.findByObj(form);
	}
}
