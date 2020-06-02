package com.rs.service;
import com.rs.dao.IPageConfigRegionDao;
import com.rs.po.PageConfigRegion;
import com.rs.po.returnPo.PageConfigRegionReturn;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 页面参数模板区域服务层
 * @ClassName: PageConfigRegionService
 * @Description: 
 * @Author sven
 * @DateTime 2020年2月10日 下午5:23:17
 */
@Service
public class PageConfigRegionService extends BaseService<PageConfigRegion> {
	@Autowired
	private IPageConfigRegionDao pageConfigRegionDao;
	
	public List<PageConfigRegionReturn> findByList_app(PageConfigRegion form){
		return pageConfigRegionDao.findByList_app(form);
	}
	
	public List<PageConfigRegionReturn> findByAll_app(PageConfigRegion form){
		return pageConfigRegionDao.findByAll_app(form);
	}
	
	public PageConfigRegionReturn findByCode_app(PageConfigRegion form){
		return pageConfigRegionDao.findByCode_app(form);
	}
	
	public List<PageConfigRegionReturn> findJoin(PageConfigRegion form){
		return pageConfigRegionDao.findJoin(form);
	}
	
	public int updateJoin(PageConfigRegion form) {
		return pageConfigRegionDao.updateJoin(form);
	}
}
