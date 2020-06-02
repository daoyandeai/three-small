package com.rs.service;
import com.rs.dao.IDepartmentRegionDao;
import com.rs.po.DepartmentRegion;
import com.rs.po.returnPo.RegionReturn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 部门区域数据服务层
 * @ClassName: DepartmentRegionService
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月30日 下午4:15:05
 */
@Service
public class DepartmentRegionService extends BaseService<DepartmentRegion> {
	@Autowired
	private IDepartmentRegionDao departmentRegionDao;
	
	public int saveByRegionCodes(DepartmentRegion form) {
		return departmentRegionDao.saveByRegionCodes(form);
	}
	
	public List<RegionReturn> findByDeptCode(DepartmentRegion form){
		return departmentRegionDao.findByDeptCode(form);
	}
}
