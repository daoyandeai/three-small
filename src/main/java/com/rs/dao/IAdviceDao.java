package com.rs.dao;
import com.rs.po.Advice;
import org.springframework.stereotype.Repository;
/**
 * 执法建议数据层接口
 * @ClassName: IAdviceDao
 * @Description: 
 * @Author sven
 * @DateTime 2019年12月26日 上午11:03:08
 */
@Repository
public interface IAdviceDao extends IBaseDao<Advice>{
	/**
	 * 查询企业最近一次执法结果
	 * @Title: findLastByCompany
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年1月7日 下午2:32:35
	 * @return
	 */
	Advice findLastByCompany(String company_code);
}