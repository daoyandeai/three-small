package com.rs.service;

import com.rs.dao.IFoodSourceAccessoryDao;
import com.rs.dao.IFoodSourceDao;
import com.rs.dao.IFoodSourceDetailDao;
import com.rs.po.Company;
import com.rs.po.FoodSource;
import com.rs.po.FoodSourceAccessory;
import com.rs.po.FoodSourceDetail;
import com.rs.po.FoodSourceSample;
import com.rs.po.TokenParam;
import com.rs.po.returnPo.FoodSourceAccessoryReturn;
import com.rs.po.returnPo.FoodSourceReturn;
import com.rs.po.returnPo.FoodSourceSampleReturn;
import com.rs.util.CodeUtil;
import com.rs.util.FileUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * @ClassName: FoodSourceService
 * @Description: 溯源服务层
 * @Author tangsh
 * @DateTime 2019年12月18日 下午1:26:36
 */
@Service
public class FoodSourceService extends BaseService<FoodSource>{
    @Autowired
    private IFoodSourceDao foodSourceDao;
    @Autowired
    private IFoodSourceDetailDao foodSourceDetailDao;
    @Autowired
    private IFoodSourceAccessoryDao foodSourceAccessoryDao;
    @Autowired
	private TokenParam tokenParam;
    
    public int saveFs(FoodSource form,Company company) {
    	String foodSourceCode = CodeUtil.getSystemCode("foodSource");
		form.setFoodsource_code(foodSourceCode);
		int i = foodSourceDao.save(form);
		if(i!=0){
			String foodSourceDetailCode = null;
			
			FoodSourceAccessory foodSourceAccessory = new FoodSourceAccessory();
			foodSourceAccessory.setFoodsource_code(foodSourceCode);
			for (FoodSourceDetail foodSourceDetail:
				 form.getFoodSourceDetailList()) {

				//保存溯源明细
				foodSourceDetail.setProduce_name(company.getOperator());
				foodSourceDetail.setFoodsource_code(foodSourceCode);
				foodSourceDetailCode = CodeUtil.getSystemCode("foodSourceDetail");
				foodSourceDetail.setFoodsourcedetail_code(foodSourceDetailCode);
				int k = foodSourceDetailDao.save(foodSourceDetail);

				//溯源明细保存成功后保存上传的附件
				if(k!=0){
					foodSourceAccessory.setFoodsource_code(foodSourceCode);
					foodSourceAccessory.setCompany_code(form.getCompany_code());
					foodSourceAccessory.setFoodsourcedetail_code(foodSourceDetailCode);
					foodSourceAccessory.setAccessory_code(CodeUtil.getSystemCode("foodSourceAccessory"));
					foodSourceAccessory.setAccessory_url(foodSourceDetail.getAccessory_url());
					foodSourceAccessoryDao.save(foodSourceAccessory);
				}
			}
		}
		return i;
    }

    public List<FoodSourceSampleReturn> findByFoodSourceSample(FoodSourceSample form) {
        return foodSourceDao.findByFoodSourceSample(form);
    }

    public int findByFoodSourceSampleCount(FoodSourceSample form) {
        return foodSourceDao.findByFoodSourceSampleCount(form);
    }
    
    public List<FoodSourceReturn> findByList_app(FoodSource form){
        return foodSourceDao.findByList_app(form);
    }
    
    public List<FoodSource> findByAddTime(FoodSource form){
    	return foodSourceDao.findByAddTime(form);
    }
    
    public int deleteByFs(FoodSource form) {
    	String beginurl=tokenParam.getFile_url().replace("resources/", "");
  		List<FoodSourceAccessoryReturn> fsalist=foodSourceAccessoryDao.findByFoodSource(form.getFoodsource_code());
  		if(fsalist!=null&&fsalist.size()>0) {
  			fsalist.forEach(fsa ->{
  				FileUtil.deleteFile(beginurl+fsa.getAccessory_url());
  			});
  		}
  		foodSourceAccessoryDao.deleteByFoodSource(form.getFoodsource_code());
  		foodSourceDetailDao.deleteByFoodSource(form.getFoodsource_code());
  		return foodSourceDao.delete(form);
    }
}
