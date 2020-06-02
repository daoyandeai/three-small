package com.rs.service;
import com.rs.dao.IPageConfigDao;
import com.rs.po.PageConfig;
import com.rs.po.returnPo.PageConfigReturn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 页面读取参数配置服务层
 * @ClassName: PageConfigService
 * @Description: 
 * @Author sven
 * @DateTime 2020年2月10日 上午11:58:57
 */
@Service
public class PageConfigService extends BaseService<PageConfig> {
	@Autowired
	private IPageConfigDao pageConfigDao;
	
	public List<PageConfigReturn> findByList_app(PageConfig form){
		return pageConfigDao.findByList_app(form);
	}
	
	public List<PageConfigReturn> findByAll_app(PageConfig form){
		return pageConfigDao.findByList_app(form);
	}
	
	public PageConfigReturn findByCode_app(PageConfig form){
		return pageConfigDao.findByCode_app(form);
	}
}
