package com.rs.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rs.po.InfoRegion;
import com.rs.po.returnPo.UserReturn;
/**
 * 信息员区域表数据接口层
 * @ClassName: IInfoRegionDao
 * @Description: 
 * @Author sven
 * @DateTime 2020年1月2日 下午3:03:20
 */
@Repository
public interface IInfoRegionDao extends IBaseDao<InfoRegion> {
	/**
	 * 新增
	 * @Title: insert
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年1月2日 下午7:10:27
	 * @param form
	 * @return
	 */
    int insert(InfoRegion form);
    /**
     * 
     * @Title: findByUserCode
     * @Description: 
     * @Author sven
     * @DateTime 2020年1月2日 下午7:10:38
     * @param user_code
     * @return
     */
    String findByUserCode(String user_code);
    /**
     * 分页_根据监管人员管理区域查询第五级所有网格信息员列表
     * @Title: findListByRegionAndUser
     * @Description: 
     * @Author sven
     * @DateTime 2020年1月2日 下午3:02:17
     * @param form
     * @return
     */
    List<UserReturn> findListByRegionAndUser(InfoRegion form);
    /**
     * 所有_根据监管人员管理区域查询第五级所有网格信息员列表
     * @Title: findListByRegionAndUser
     * @Description: 
     * @Author sven
     * @DateTime 2020年1月2日 下午3:02:17
     * @param form
     * @return
     */
    List<UserReturn> findAllByRegionAndUser(InfoRegion form);
    /**
     * 根据监管人员管理区域查询第五级所有网格信息员总和
     * @Title: findCountByRegionAndUser
     * @Description: 
     * @Author sven
     * @DateTime 2020年1月2日 下午3:02:20
     * @param form
     * @return
     */
    Integer findCountByRegionAndUser(InfoRegion form);
}