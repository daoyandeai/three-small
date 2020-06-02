package com.rs.service;
import com.rs.dao.IDepartmentRegionDao;
import com.rs.dao.IInfoRegionDao;
import com.rs.po.DepartmentRegion;
import com.rs.po.InfoRegion;
import com.rs.po.returnPo.UserReturn;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
/**
 * 网格信息员服务层
 * @ClassName: InfoRegionService
 * @Description: 
 * @Author sven
 * @DateTime 2020年1月2日 下午3:05:02
 */
@Service
public class InfoRegionService extends BaseService<InfoRegion> {
	@Autowired
	private IInfoRegionDao infoRegionDao;
	@Autowired
	private IDepartmentRegionDao departmentRegionDao;
	
	public String findByUserCode(String user_code) {
		return infoRegionDao.findByUserCode(user_code);
	}
	
	public List<UserReturn> findListByRegionAndUser(InfoRegion form){
		return infoRegionDao.findListByRegionAndUser(form);
	}
	
	public Integer findCountByRegionAndUser(InfoRegion form){
		return infoRegionDao.findCountByRegionAndUser(form);
	}
	
	public List<UserReturn> findAllByRegionAndUser(InfoRegion form){
		return infoRegionDao.findAllByRegionAndUser(form);
	}
	
	public Integer saveByUser(String user_code,String region_code) {
		if(StringUtils.isEmpty(user_code)||StringUtils.isEmpty(region_code)) {
			return 0;
		}
		//判断该区域是否存在管理部门
		DepartmentRegion dr=new DepartmentRegion();
		dr.setRegion_code(region_code);
		if(departmentRegionDao.findByCount(dr)<=0) {
			return 0;
		}
		InfoRegion ir=new InfoRegion();
		ir.setUser_code(user_code);		
		//判断用户数据是否存在
		if(infoRegionDao.findByCount(ir)>0) {
			infoRegionDao.delete(ir);
		}
		ir.setRegion_code(region_code);
		return infoRegionDao.save(ir);
	}
	
}
