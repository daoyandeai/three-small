package com.rs.dao;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.rs.po.DepartmentRegion;
import com.rs.po.returnPo.RegionReturn;
/**
 * 部门管理区域数据接口层
 * @ClassName: IDepartmentRegionDao
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月30日 下午4:03:47
 */
@Repository
public interface IDepartmentRegionDao extends IBaseDao<DepartmentRegion> {
	/**
	 * 批量插入第四级区域下的所有区域编码
	 * @Title: saveSelect
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月30日 下午4:03:51
	 * @param form
	 * @return
	 */
	int saveByRegionCodes(DepartmentRegion form);
	/**
	 * 根据部门查询第四级区域
	 * @Title: findByDeptCode
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月30日 下午5:42:45
	 * @return
	 */
	List<RegionReturn> findByDeptCode(DepartmentRegion form);
}