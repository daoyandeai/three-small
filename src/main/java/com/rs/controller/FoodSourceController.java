package com.rs.controller;

import com.rs.po.*;
import com.rs.po.returnPo.FoodSourceDetailReturn;
import com.rs.service.CompanyService;
import com.rs.service.FoodSourceDetailService;
import com.rs.service.FoodSourceSampleService;
import com.rs.service.FoodSourceService;
import com.rs.util.TokenUserUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @ClassName: FoodSourceController
 * @Description:溯源报备控制层
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/foodsource")
public class FoodSourceController extends BaseController {

	@Autowired
	private FoodSourceService foodSourceServer;
	@Autowired
	private FoodSourceSampleService foodSourceSampleServers;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private FoodSourceDetailService foodSourceDetailService;
	@Autowired
	private TokenParam tokenParam;
	
	private Logger logger = LoggerFactory.getLogger(FoodSourceController.class);

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
			FoodSourceDetailReturn fsdr=new FoodSourceDetailReturn();
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
	 * @Title: sample
	 * @Description: 查询溯源下的菜品留样
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:28:10
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/sample")
	public Object sample(FoodSourceSample form) {
		Map<String,Object> map = new HashMap<>();
		try {
			if(StringUtils.isEmpty(form.getCompany_code())){
				return finish(CODEMSG,PARAMETER_ERROR);
			}
			map.put("pager_count",foodSourceSampleServers.findByCount(form));
			map.put("list",foodSourceSampleServers.findByList(form));
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
	 * @Title: company
	 * @Description: 查询溯源下的菜品留样
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:28:15
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/company")
	public Object company(Company form,HttpServletRequest request) {
		Map<String,Object> map = new HashMap<>();
		try {
			User user = TokenUserUtil.generateUser(request, tokenParam);
			map.put("pager_count",companyService.findByUserCompanyFCCount(form,user));
			map.put("companyList",companyService.findByUserCompanyFCList(form,user));
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
	 * @Title: search
	 * @Description: 条件查询溯源
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:28:25
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/search")
	public Object search(FoodSource form) {
		CODEMSG = ERROR;
		FoodSource foodSource = new FoodSource();
		try {
			foodSource.setPager_count(foodSourceServer.findByCount(form));
			foodSource.setFoodSourceList(foodSourceServer.findByList(form));
			//添加溯源详情
			FoodSourceDetail fsd=null;
			for (FoodSource fs : foodSource.getFoodSourceList()) {
				fsd=new FoodSourceDetail();
				fsd.setCompany_code(fs.getCompany_code());
				fsd.setFoodsource_code(fs.getFoodsource_code());
				fs.setFoodSourceDetailList(foodSourceDetailService.findByAll(fsd));
			}
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, foodSource);
	}
}
