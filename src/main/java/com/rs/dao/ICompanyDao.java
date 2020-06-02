package com.rs.dao;

import com.rs.po.Company;
import com.rs.po.Level;
import com.rs.po.returnPo.BigData;
import com.rs.po.returnPo.CompanyReturn;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * @ClassName: ICompanyDao
 * @Description: 企业数据接口层
 * @Author tangsh
 * @DateTime 2019年12月18日 下午1:17:55
 */
@Repository
public interface ICompanyDao extends IBaseDao<Company>{
    /**
     * 
     * @Title: findByLast
     * @Description: 查询最后一个备案号的最后6位
     * @Author tangsh
     * @DateTime 2019年11月25日 下午5:41:16
     * @return
     */
    String findByLast();
    /**
     * 
     * @Title: findBySearchCount
     * @Description: 查询企业备案数据总数
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:03:09
     * @param form
     * @return
     */
    int findBySearchCount(Company form);
    /**
     * 
     * @Title: findRegionLevel
     * @Description: 根据用户查询对应的行政区域信息
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:03:21
     * @param user_code
     * @return
     */
    Level findRegionLevel(String user_code);
    /**
     * 
     * @Title: findAreaCount
     * @Description: 统计指定区域的报备数
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:04:35
     * @param region_code
     * @return
     */
    List<Level> findAreaCount(String region_code);
    /**
     * 
     * @Title: findCityCount
     * @Description: 统计指定区域的报备数
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:04:52
     * @param region_code
     * @return
     */
    List<Level> findCityCount(String region_code);
    /**
     * 
     * @Title: findMap
     * @Description: 根据区域统计对应数据
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:05:23
     * @param form
     * @return
     */
    List<String> findMap(Level form);
    /**
     * 
     * @Title: findByFsSearch
     * @Description: 查询企业溯源数据
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:05:38
     * @param form
     * @return
     */
    List<CompanyReturn> findByFsSearch(Company form);
    /**
     * 
     * @Title: findByJoinFsList
     * @Description: 查询溯源企业列表
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:05:55
     * @param form
     * @return
     */
    List<Company> findByJoinFsList(Company form);
    /**
     * 
     * @Title: findByJoinFsCount
     * @Description: 查询溯源企业总数
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:06:08
     * @param form
     * @return
     */
    Integer findByJoinFsCount(Company form);
    /**
     * 
     * @Title: findEmployByCompanyCount
     * @Description: 查询企业从业人员总数
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:06:26
     * @param form
     * @return
     */
    Integer findEmployByCompanyCount(Company form);
    /**
     * 
     * @Title: findCompanyByCityStatistics
     * @Description: 大数据统计区域备案信息
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:06:41
     * @param form
     * @return
     */
    List<BigData> findCompanyByCityStatistics(Company form);
    /**
     * 
     * @Title: findStateByCode
     * @Description: 根据企业code查询过期状态
     * @Author tangsh
     * @DateTime 2020年3月3日 下午4:48:45
     * @param company_code
     * @return
     */
    Integer findStateByCode(String company_code);
    /**
     * 
     * @Title: findByCompanyEmployCount
     * @Description: 联合查询企业人员条件总数
     * @Author tangsh
     * @DateTime 2020年3月24日 下午4:28:01
     * @param form
     * @return
     */
    Integer findByCompanyEmployCount(Company form);
}