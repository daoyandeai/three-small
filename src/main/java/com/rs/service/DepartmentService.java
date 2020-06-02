package com.rs.service;
import com.rs.dao.IDepartmentDao;
import com.rs.po.Department;
import com.rs.po.returnPo.DepartmentReturn;
import com.rs.util.BaiDuMapUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 部门数据服务层
 * @ClassName: DepartmentService
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月30日 上午10:49:46
 */
@Service
public class DepartmentService extends BaseService<Department> {
	@Autowired
	private IDepartmentDao departmentDao;
	
	
	public DepartmentReturn findByUserCode(Department form) {
		return departmentDao.findByUserCode(form);
	}
	
	public List<DepartmentReturn> findAllByUserCode(Department form) {
		return departmentDao.findAllByUserCode(form);
	}
	
	public List<DepartmentReturn> findAll_app(Department form) {
		return departmentDao.findAll_app(form);
	}
	
	/**
     * 根据企业地址逆解析坐标
     * @Title: geocoder
     * @Description: 
     * @Author sven
     * @DateTime 2020年3月24日 上午11:31:16
     * @return
     */
    public boolean geocoder() {
    	Department d = new Department();
    	d.setQuery_param("lngLat is null");
    	List<Department> departmentList = departmentDao.findByAll(d);
    	String address = "";
    	String[] arr = new String[2];
    	for (Department department : departmentList) {
    		address = department.getProvince()+department.getCity()+department.getArea()+department.getTown()+department.getVill();
    		arr = BaiDuMapUtil.getLngAndLatByLoc(address);
    		department.setLongitude(arr[0]);
    		department.setLatitude(arr[1]);
		}
    	int result = departmentDao.updateBatch(departmentList);
    	if(result == 0) {
    		return false;
    	}else {
    		return true;
    	}
    }
}
