package com.rs.controller;

import com.rs.po.*;
import com.rs.po.returnPo.FoodSourceDetailReturn;
import com.rs.service.CompanyService;
import com.rs.service.FoodSourceDetailService;
import com.rs.service.FoodSourceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: AppFoodSourceController
 * @Description: 溯源报备控制层
 * @Author tangsh
 * @DateTime 2020年5月25日 下午4:59:33
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/app/foodsource")
public class AppFoodSourceController extends BaseController {

	@Autowired
	private FoodSourceService foodSourceServer;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private FoodSourceDetailService foodSourceDetailService;
	
	private Logger logger = LoggerFactory.getLogger(AppFoodSourceController.class);

	/**
	 * 
	 * @Title: detail
	 * @Description: 查询溯源详情
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:28:00
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/detail")
	public Object detail(FoodSource form) {
		try {
			form = foodSourceServer.findByCode(form);
			FoodSourceDetail fsd=new FoodSourceDetail();
			fsd.setCompany_code(form.getCompany_code());
			fsd.setFoodsource_code(form.getFoodsource_code());
			form.setFoodSourceDetailList(foodSourceDetailService.findByAll(fsd));
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, form);
	}

	/**
	 * 
	 * @Title: fsdetail
	 * @Description: 查询溯源子单详情
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:28:05
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/fsdetail")
	public Object fsdetail(FoodSourceDetail form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			FoodSourceDetailReturn fsdr =new FoodSourceDetailReturn();
			FoodSourceDetail fsd = foodSourceDetailService.findByCode(form);
			if(fsd!=null) {
				FoodSource fs=new FoodSource();
				fs.setFoodsource_code(fsd.getFoodsource_code());
				map.put("foodSource", foodSourceServer.findByCode(fs));
				Company c=new Company();
				c.setCompany_code(fsd.getCompany_code());
				map.put("company", companyService.findByCode(c));
			}
			BeanUtils.copyProperties(fsd, fsdr);
			map.put("foodSourceDetail", fsdr);
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, map);
	}

	/**
	 * 
	 * @Title: save
	 * @Description: 溯源报备提交保存
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:28:20
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody FoodSource form) {
		CODEMSG = ERROR;
		try {
			if(StringUtils.isEmpty(form.getCompany_code())) {
				return finish(PARAMETER_ERROR, null);
			}
			Company company = new Company();
			company.setCompany_code(form.getCompany_code());
			company = companyService.findById(company);
			if(company==null) {
				return finish(PARAMETER_ERROR, null);
			}
			foodSourceServer.saveFs(form, company);
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, null);
	}
	
	/**
	 * 
	 * @Title: delete
	 * @Description: 删除溯源信息
	 * @Author tangsh
	 * @DateTime 2020年6月1日 下午5:02:54
	 * @param form
	 * @return
	 */
	@DeleteMapping(value="/delete")
	public Object delete(FoodSource form) {
		CODEMSG = SUCCESS;
	  	try {
	  		if(StringUtils.isEmpty(form.getFoodsource_code())){
				return finish(PARAMETER_ERROR,null);
			}
	  		foodSourceServer.deleteByFs(form);
	  	}catch(Exception ex) {
	  		logger.info(ex.toString());
			CODEMSG = EXCEPTION;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return finish(CODEMSG,null);
		}
	  	return finish(CODEMSG,null);
	}

	/**
	 * 
	 * @Title: findManageFoodSource
	 * @Description: 查询企业溯源列表及（详情）
	 * @Author tangsh
	 * @DateTime 2020年5月22日 下午2:36:46
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/manage/fslist")
	public Object findManageFoodSource(FoodSource form) {
		CODEMSG = ERROR;
		if(StringUtils.isEmpty(form.getCompany_code())){
			return finish(PARAMETER_ERROR, null);
		}
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			List<FoodSource> fslist = foodSourceServer.findByList(form);
			fslist.forEach(fs ->{
				FoodSourceDetail fsd=new FoodSourceDetail();
				fsd.setFoodsource_code(fs.getFoodsource_code());
				fs.setFoodSourceDetailList(foodSourceDetailService.findByAll(fsd));
			});
			map.put("pager_list", fslist);
			map.put("pager_count", foodSourceServer.findByCount(form));
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.info(ex.toString());
			return finish(EXCEPTION, null);
		}
		return finish(CODEMSG, map);
	}
	
	/**
	 * 
	 * @Title: findYearMonthFoodSource
	 * @Description: 根据企业编码和查询年月查询该企业当月有溯源的时间
	 * @Author tangsh
	 * @DateTime 2020年5月22日 下午1:58:10
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/yearMonth")
	public Object findYearMonthFoodSource(FoodSource form) {
		CODEMSG = ERROR;
		Map<String,Object> map=new HashMap<String,Object>();
		if(StringUtils.isEmpty(form.getCompany_code())||StringUtils.isEmpty(form.getYear_month())){
			return finish(PARAMETER_ERROR, null);
		}
		try {
			map.put("foodSourceList", foodSourceServer.findByAddTime(form));
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.info(ex.toString());
			return finish(EXCEPTION, null);
		}
		return finish(CODEMSG, map);
	}
}
