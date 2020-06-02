package com.rs.service;
import com.rs.dao.IConfigDao;
import com.rs.po.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 
 * @ClassName: ConfigService
 * @Description: 群宴全局配置服务层接口
 * @Author tangsh
 * @DateTime 2020年4月8日 下午3:02:14
 */
@Service
public class ConfigService extends BaseService<Config>{
	@Autowired
	private IConfigDao configDao;
	
	public Integer findByInclude(String form) {
		return configDao.findByInclude(form);
	}
}
