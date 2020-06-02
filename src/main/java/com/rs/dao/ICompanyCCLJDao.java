package com.rs.dao;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.rs.po.CompanyCCLJ;
import com.rs.po.returnPo.CompanyCCLJReturn;
/**
 * 餐厨垃圾处理数据接口层
 * @ClassName: ICompanyCCLJDao
 * @Description: 
 * @Author sven
 * @DateTime 2020年3月9日 下午4:04:49
 */
@Repository
public interface ICompanyCCLJDao extends IBaseDao<CompanyCCLJ> {
	/**
	 * 
	 * @Title: findByList_app
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月10日 下午2:42:27
	 * @param form
	 * @return
	 */
	List<CompanyCCLJReturn> findByList_app(CompanyCCLJ form);
	/**
	 * 
	 * @Title: findByCode_app
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月10日 下午2:43:02
	 * @param form
	 * @return
	 */
	CompanyCCLJReturn findByCode_app(CompanyCCLJ form);
}