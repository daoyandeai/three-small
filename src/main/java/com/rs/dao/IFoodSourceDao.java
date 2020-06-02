package com.rs.dao;

import com.rs.po.FoodSource;
import com.rs.po.FoodSourceSample;
import com.rs.po.returnPo.FoodSourceReturn;
import com.rs.po.returnPo.FoodSourceSampleReturn;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * @ClassName: IFoodSourceDao
 * @Description: 溯源数据接口层
 * @Author tangsh
 * @DateTime 2019年12月18日 下午1:21:29
 */
@Repository
public interface IFoodSourceDao extends IBaseDao<FoodSource>{
    /**
     * 
     * @Title: findByFoodSourceSample
     * @Description: 根据留样查询数据
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:13:53
     * @param form
     * @return
     */
    List<FoodSourceSampleReturn> findByFoodSourceSample(FoodSourceSample form);
    /**
     * 
     * @Title: findByFoodSourceSampleCount
     * @Description: 根据留样信息查询总数
     * @Author tangsh
     * @DateTime 2020年3月17日 下午3:14:06
     * @param form
     * @return
     */
    int findByFoodSourceSampleCount(FoodSourceSample form);
    /**
     * 根据企业查询对应的溯源_分页
     * @Title: findByList_app
     * @Description: 
     * @Author sven
     * @DateTime 2020年4月2日 下午3:07:32
     * @param form
     * @return
     */
    List<FoodSourceReturn>  findByList_app(FoodSource form);
    
    /**
     * 
     * @Title: findByAddTime
     * @Description: 根据条件统计时间
     * @Author tangsh
     * @DateTime 2020年5月22日 下午1:56:46
     * @param form
     * @return
     */
    List<FoodSource> findByAddTime(FoodSource form);
}