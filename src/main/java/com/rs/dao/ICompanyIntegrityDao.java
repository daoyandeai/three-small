package com.rs.dao;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.rs.po.CompanyIntegrity;
import com.rs.po.returnPo.CompanyIntegrityReturn;
/**
 * 企业诚信评价结果数据接口层
 * @ClassName: ICompanyIntegrityDao
 * @Description: 
 * @Author sven
 * @DateTime 2020年2月12日 下午4:47:54
 */
@Repository
public interface ICompanyIntegrityDao extends IBaseDao<CompanyIntegrity> {
	/**
	 * 
	 * @Title: findByList_app
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月12日 下午2:28:06
	 * @param form
	 * @return
	 */
	List<CompanyIntegrityReturn> findByList_app(CompanyIntegrity form);
	/**
	 * 查询企业最新一次评价结果
	 * @Title: findLast
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月13日 下午3:26:21
	 * @param form
	 * @return
	 */
	CompanyIntegrity findLast(CompanyIntegrity form);
}