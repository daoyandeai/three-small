package com.rs.service;
import com.rs.dao.ICompanyCCLJDao;
import com.rs.po.CompanyCCLJ;
import com.rs.po.returnPo.CompanyCCLJReturn;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 餐厨垃圾推送服务层
 * @ClassName: CompanyCCLJService
 * @Description: 
 * @Author sven
 * @DateTime 2020年3月9日 下午4:08:52
 */
@Service
public class CompanyCCLJService extends BaseService<CompanyCCLJ> {
	@Autowired
	private ICompanyCCLJDao companyCCLJDao;
	
	public List<CompanyCCLJReturn> findByList_app(CompanyCCLJ form){
		return companyCCLJDao.findByList_app(form);
	}
	
	public CompanyCCLJReturn findByCode_app(CompanyCCLJ form){
		return companyCCLJDao.findByCode_app(form);
	}
}
