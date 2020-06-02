package com.rs.service;
import com.rs.dao.IPageAttrDao;
import com.rs.po.PageAttr;
import com.rs.po.returnPo.PageAttrReturn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 页面属性服务层
 * @ClassName: PageAttrService
 * @Description: 
 * @Author sven
 * @DateTime 2020年2月11日 上午10:15:32
 */
@Service
public class PageAttrService extends BaseService<PageAttr> {
	@Autowired
	private IPageAttrDao pageAttrDao;
	
	public List<PageAttrReturn> findByList_app(PageAttr form){
		return pageAttrDao.findByList_app(form);
	}
	
	public List<PageAttrReturn> findByAll_app(PageAttr form){
		return pageAttrDao.findByList_app(form);
	}
	
	public PageAttrReturn findByCode_app(PageAttr form){
		return pageAttrDao.findByCode_app(form);
	}
}
