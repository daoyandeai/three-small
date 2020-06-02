package com.rs.dao;

import com.rs.po.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * @ClassName: IPatrolDao
 * @Description: 巡查数据接口层
 * @Author tangsh
 * @DateTime 2019年12月18日 下午1:22:47
 */
@Repository
public interface IPatrolDao extends IBaseDao<Patrol>{
    /**
     * 
     * @Title: findLeftJoinCompanyList
     * @Description: 查询企业巡查列表
     * @Author tangsh
     * @DateTime 2020年3月17日 上午10:26:55
     * @param form
     * @return
     */
    List<Patrol> findLeftJoinCompanyList(Patrol form);
    /**
     * 
     * @Title: findLeftJoinCompanyCount
     * @Description: 查询企业巡查数
     * @Author tangsh
     * @DateTime 2020年3月17日 上午10:27:12
     * @param form
     * @return
     */
    int findLeftJoinCompanyCount(Patrol form);
    
    /**
     * 
     * @Title: findLeftJoinCompanyListApp
     * @Description: 微信端根据企业巡查列表
     * @Author tangsh
     * @DateTime 2020年3月17日 上午10:27:24
     * @param form
     * @return
     */
    List<Patrol> findLeftJoinCompanyListApp(Patrol form);

    /**
     * 
     * @Title: findLeftJoinCompanyCountApp
     * @Description: 微信端根据企业巡查数量
     * @Author tangsh
     * @DateTime 2020年3月17日 上午10:27:37
     * @param form
     * @return
     */
    int findLeftJoinCompanyCountApp(Patrol form);
    
    /**
     * 
     * @Title: findTodayUserPatrolSum
     * @Description: 查询当日指定用户是否存在巡查工单
     * @Author tangsh
     * @DateTime 2020年3月17日 上午10:24:57
     * @param patrol_user_code
     * @return
     */
    Integer findTodayUserPatrolSum(String patrol_user_code);
    /**
     * 
     * @Title: findLeftJoinCompanyAll
     * @Description: 根据条件联查企业表地址信息
     * @Author tangsh
     * @DateTime 2020年3月17日 上午11:32:37
     * @param form
     * @return
     */
    List<Patrol> findLeftJoinCompanyAll(Patrol form);
}