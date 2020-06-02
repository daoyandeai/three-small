package com.rs.controller;
import com.rs.po.*;
import com.rs.po.returnPo.FoodSourceDetailReturn;
import com.rs.po.returnPo.FoodSourceReturn;
import com.rs.service.FoodSourceAccessoryService;
import com.rs.service.FoodSourceDetailService;
import com.rs.service.FoodSourceService;
import com.rs.util.CodeUtil;
import com.rs.util.TokenUserUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
/**
 * 食材溯源控制层
 * @ClassName: OpenFoodSourceController
 * @Description: 
 * @Author sven
 * @DateTime 2020年4月2日 上午10:19:55
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/open/food/source")
public class OpenFoodSourceController extends BaseController {
	@Autowired
	private FoodSourceService foodSourceService;
	@Autowired
	private FoodSourceDetailService foodSourceDetailService;
	@Autowired
	private FoodSourceAccessoryService foodSourceAccessoryService;
	@Autowired
	private TokenParam tokenParam;
	
	private Logger logger = LoggerFactory.getLogger(FoodSourceController.class);

	/**
	 * 查询溯源_分页
	 * @Title: list
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月2日 下午2:53:42
	 * @param form
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(FoodSource form,HttpServletRequest request) {
		Map<String,Object> map = new HashMap<>();
		try {
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setCompany_code(user.getCompany_code());
			//根据企业编码查询对应溯源列表
			List<FoodSourceReturn> fs_list = foodSourceService.findByList_app(form);
			//溯源明细集合
			List<FoodSourceDetailReturn> fsdr_list = null;
			for (FoodSourceReturn fs : fs_list) {
				//查询溯源明细
				fsdr_list = foodSourceDetailService.findByAll_app(fs.getFoodsource_code());
				//查询溯源明细附件
				for (FoodSourceDetailReturn foodSourceDetailReturn : fsdr_list) {
					foodSourceDetailReturn.setFood_source_accessory_list(foodSourceAccessoryService.findByAll_app(foodSourceDetailReturn.getFoodsourcedetail_code()));
				}
				fs.setFood_source_detail_list(fsdr_list);
			}
			map.put("food_source_list",fs_list);
			map.put("pager_count",foodSourceService.findByCount(form));
			CODEMSG = SUCCESS ;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.error(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, map);
	}
	
	/**
	 * 新增溯源、溯源明细、溯源明细附件
	 * @Title: save
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年4月2日 上午11:26:38
	 * @param form
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody FoodSource form ,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			User user = TokenUserUtil.generateUser(request, tokenParam);
			String company_code = user.getCompany_code();
			String user_name = user.getUser_name();
			//新增溯源主表
			String foodsource_code = CodeUtil.getSystemCode("foodSource");
			form.setFoodsource_code(foodsource_code);
			form.setCompany_code(company_code);
			form.setSell_person(user_name);
			Integer result = foodSourceService.save(form);
			
			if(result > 0) {
				
				FoodSourceDetail fsd_batch = new FoodSourceDetail();
				FoodSourceAccessory fsa_batch = new FoodSourceAccessory();
				
				//新增溯源明细
				List<FoodSourceDetail> food_source_detail_list = form.getFood_source_detail_list();
				//溯源明细附件集合
				List<FoodSourceAccessory> food_source_accessory_list = null;
				
				if(!StringUtils.isEmpty(food_source_detail_list) && food_source_detail_list.size() > 0) {
					String foodsourcedetail_code = "";
					for (FoodSourceDetail foodSourceDetail : food_source_detail_list) {
						foodsourcedetail_code = CodeUtil.getSystemCode("foodSourceDetail");
						foodSourceDetail.setFoodsourcedetail_code(foodsourcedetail_code);
						foodSourceDetail.setCompany_code(company_code);
						foodSourceDetail.setFoodsource_code(foodsource_code);
						foodSourceDetail.setProduce_name(user_name);
						
						food_source_accessory_list = foodSourceDetail.getFood_source_accessory_list();
						if(!StringUtils.isEmpty(food_source_accessory_list) && food_source_accessory_list.size() > 0) {
							for (FoodSourceAccessory foodSourceAccessory : food_source_accessory_list) {
								foodSourceAccessory.setFoodsource_code(foodsource_code);
								foodSourceAccessory.setCompany_code(company_code);
								foodSourceAccessory.setFoodsourcedetail_code(foodsourcedetail_code);
								foodSourceAccessory.setAccessory_code(CodeUtil.getSystemCode("foodSourceAccessory"));
							}
							fsa_batch.setPager_list(food_source_accessory_list);
							foodSourceAccessoryService.saveBatch(fsa_batch);
						}
					}
					fsd_batch.setPager_list(food_source_detail_list);
					foodSourceDetailService.saveBatch(fsd_batch);
				}
				CODEMSG = SUCCESS;
			} else {
				map = null;
				CODEMSG = ERROR;
			}
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.error(ex.toString());
			return finish(CODEMSG, map);
		}
		return finish(CODEMSG, map);
	}

}
