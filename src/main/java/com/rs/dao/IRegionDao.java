package com.rs.dao;

import java.util.List;

import com.rs.po.Region;
import org.springframework.stereotype.Repository;

/**
 * 
 * @ClassName: IRegionDao
 * @Description: 行政区域数据层接口
 * @Author tangsh
 * @DateTime 2019年11月25日 下午3:28:16
 */
@Repository
public interface IRegionDao extends IBaseDao<Region> {
	
	/**
	 * 按名字查询下级所有行政区域
	 * @param form
	 * @return
	 */
	public List<Region> findByName_ReportStatistics(Region form);
	/**
	 * 
	 * @Title: findByName
	 * @Description: 根据名称查询区域数据
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:17:04
	 * @param form
	 * @return
	 */
    Region findByName(String form);
    /**
     * 
     * @Title: findOneByRegion
     * @Description: 根据信息查询区域数据
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:17:16
     * @param form
     * @return
     */
    Region findOneByRegion(Region form);
    /**
     * 
     * @Title: findLastByHcode
     * @Description: 根据上级编码查询最后一个区域信息
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:17:35
     * @param region_high_code
     * @return
     */
    String findLastByHcode(String region_high_code);
    /**
     * 
     * @Title: updateByHighCode
     * @Description: 更新上级编码
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:17:53
     * @param form
     * @return
     */
    Integer updateByHighCode(Region form);
    /**
     * 
     * @Title: updateUserRegion
     * @Description: 更新用户区域
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:18:02
     * @param form
     * @return
     */
    Integer updateUserRegion(Region form);
    /**
     * 
     * @Title: updateReportRegion
     * @Description: 更新报备区域
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:18:10
     * @param form
     * @return
     */
    Integer updateReportRegion(Region form);
    /**
     * 
     * @Title: updateQYTrainRegion
     * @Description: 更新考试区域
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:18:18
     * @param form
     * @return
     */
    Integer updateQYTrainRegion(Region form);
    /**
     * 
     * @Title: updateQYNewsRegion
     * @Description: 更新新闻区域
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:18:27
     * @param form
     * @return
     */
    Integer updateQYNewsRegion(Region form);
    /**
     * 
     * @Title: updateCompanyRegion
     * @Description: 更新企业备案区域
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:18:34
     * @param form
     * @return
     */
    Integer updateCompanyRegion(Region form);
    /**
     * 
     * @Title: updateDepartmentRegion
     * @Description: 更新部门区域
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:18:42
     * @param form
     * @return
     */
    Integer updateDepartmentRegion(Region form);
    /**
     * 
     * @Title: updateSXNewsRegion
     * @Description: 更新三小新闻区域
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:18:51
     * @param form
     * @return
     */
    Integer updateSXNewsRegion(Region form);
    /**
     * 
     * @Title: updateSXTrainRegion
     * @Description: 更新三小考试区域
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:18:59
     * @param form
     * @return
     */
    Integer updateSXTrainRegion(Region form);
    /**
     * 
     * @Title: updateSXCaseCenterRegion
     * @Description: 更新三小案件中心区域
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:18:59
     * @param form
     * @return
     */
    Integer updateSXCaseCenterRegion(Region form);
}
