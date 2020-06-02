package com.rs.dao;

import com.rs.po.Accessory;
import com.rs.po.returnPo.AccessoryReturn;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * 
 * @ClassName: IAccessoryDao
 * @Description:   附件数据接口层
 * @Author tangsh
 * @DateTime 2019年12月18日 下午1:16:52
 */
@Repository
public interface IAccessoryDao extends IBaseDao<Accessory>{
	/**
	 * 根据企业编码和类型查询附件
	 * @Title: findByOne
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月23日 上午11:28:16
	 * @param form
	 * @return
	 */
	Accessory findByOne(Accessory form);
	
	/**
	 * 
	 * @Title: deleteByCompany
	 * @Description: 根据Company删除
	 * @Author tangsh
	 * @DateTime 2019年12月26日 下午2:51:50
	 * @param form
	 * @return
	 */
	Integer deleteByCompany(String company_code);
	/**
	 * 根据附件归属表编码查询
	 * @Title: findByAll_app
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月9日 下午4:20:32
	 * @param form
	 * @return
	 */
	List<AccessoryReturn> findByAll_app(Accessory form);
}