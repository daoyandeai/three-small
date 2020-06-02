package com.rs.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rs.po.PageConfigRegion;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.po.returnPo.PageConfigRegionReturn;
import com.rs.service.PageConfigRegionService;
import com.rs.util.CodeUtil;
import com.rs.util.LevelUtil;
import com.rs.util.RegularUtil;
import com.rs.util.TokenUserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
/**
 * 区域页面参数模板控制层
 * @ClassName: PageConfigRegionController
 * @Description: 
 * @Author sven
 * @DateTime 2020年2月11日 下午4:34:37
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/pageConfigRegion")
public class PageConfigRegionController extends BaseController {

	@Autowired
	private PageConfigRegionService pageConfigRegionService;
	@Autowired
	private TokenParam tokenParam;
	
	private Logger logger = LoggerFactory.getLogger(PageConfigRegionController.class);


	/**
	 * 查询区域页面参数模板_分页
	 * @Title: list
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:34:44
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(PageConfigRegion form ,HttpServletRequest request) {
		Map<String,Object> map = new HashMap<>();
		try {
			User user = TokenUserUtil.generateUser(request, tokenParam);
			int owner_type = form.getOwner_type();
			switch (owner_type) {
			case 1:
				//全部
				form.setUser_code_add(null);
				break;
			case 2:
				//自己
				form.setUser_code_add(user.getUser_code());
				break;
			}
			
			//模板区域
			String province = form.getProvince();
			String city = form.getCity();
			String area = form.getArea();
			String town = form.getTown();
			String vill = form.getVill();
			String addrInfo = "";
			//登录用户区域
			String user_province = user.getUser_province();
			String user_city = user.getUser_city();
			String user_area = user.getUser_area();
			String user_town = user.getUser_town(); 
			String user_vill = user.getUser_vill();
			
			if(StringUtils.isEmpty(province)) {
				form.setProvince(user_province);
				addrInfo = addrInfo+(StringUtils.isEmpty(user_province)?"":user_province);
			}else {
				addrInfo = addrInfo + province;
			}
			
			if(StringUtils.isEmpty(city)) {
				form.setCity(user_city);
				addrInfo = addrInfo+(StringUtils.isEmpty(user_city)?"":user_city);
			}else {
				addrInfo = addrInfo + city;
			}
			
			if(StringUtils.isEmpty(area)) {
				form.setArea(user_area);
				addrInfo = addrInfo+(StringUtils.isEmpty(user_area)?"":user_area);
			}else {
				addrInfo = addrInfo + area;
			}
			
			if(StringUtils.isEmpty(town)) {
				form.setTown(user_town);
				addrInfo = addrInfo+(StringUtils.isEmpty(user_town)?"":user_town);
			}else {
				addrInfo = addrInfo + town;
			}
			
			if(StringUtils.isEmpty(vill)) {
				form.setVill(user_vill);
				addrInfo = addrInfo+(StringUtils.isEmpty(user_vill)?"":user_vill);
			}else {
				addrInfo = addrInfo + vill;
			}
			
			map.put("page_config_region_list",pageConfigRegionService.findByList_app(form));
			map.put("pager_count",pageConfigRegionService.findByCount(form));
			CODEMSG = SUCCESS ;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, map);
	}

	/**
	 * 新增区域页面参数模板
	 * @Title: save
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:35:40
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody PageConfigRegion form ,HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String page_config_code = form.getPage_config_code();
			if (StringUtils.isEmpty(page_config_code) || RegularUtil.matchLength(page_config_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String page_config_name = form.getPage_config_name();
			if(!StringUtils.isEmpty(page_config_name) && RegularUtil.isSpecialChar(page_config_name)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			String province = form.getProvince();
			if(StringUtils.isEmpty(province)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_code_add(user.getUser_code());
			form.setUser_name_add(user.getUser_name());
			form.setLevel(LevelUtil.AreaLevel(form));
			form.setPage_config_region_code(CodeUtil.getSystemCode("pageConfigRegion"));
			int count = pageConfigRegionService.findByExist(form);
			if (count > 0) {
				CODEMSG = PAGE_CONFIG_REGION_NAME_EXIST;
				return finish(CODEMSG, null);
			}
			int result = pageConfigRegionService.save(form);
			if (result > 0) {
				CODEMSG = SUCCESS;
			} else {
				map = null;
				CODEMSG = ERROR;
			}
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, map);
	}

	/**
	 * 查询单条区域页面参数模板
	 * @Title: single
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:35:51
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/single")
	public Object single(PageConfigRegion form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String page_config_region_code = form.getPage_config_region_code();
			if (StringUtils.isEmpty(page_config_region_code) || RegularUtil.matchLength(page_config_region_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			PageConfigRegionReturn pcr = pageConfigRegionService.findByCode_app(form);
			if (!StringUtils.isEmpty(pcr)) {
				map.put("page_config_region", pcr);
			}
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, map);
	}

	/**
	 * 编辑区域页面参数模板
	 * @Title: update
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:36:07
	 * @param form
	 * @return
	 */
	@PutMapping(value = "/update")
	public Object update(@RequestBody PageConfigRegion form, HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String page_config_region_code = form.getPage_config_region_code();
			if (StringUtils.isEmpty(page_config_region_code) || RegularUtil.matchLength(page_config_region_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String page_config_code = form.getPage_config_code();
			if (StringUtils.isEmpty(page_config_code) || RegularUtil.matchLength(page_config_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			String page_config_name = form.getPage_config_name();
			if(!StringUtils.isEmpty(page_config_name) && RegularUtil.isSpecialChar(page_config_name)) {
				CODEMSG = SPECIAL_CHAR;
				return finish(CODEMSG, null);
			}
			String province = form.getProvince();
			if(StringUtils.isEmpty(province)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			form.setUser_code_update(user.getUser_code());
			form.setUser_name_update(user.getUser_name());
			form.setLevel(LevelUtil.AreaLevel(form));
			int count = pageConfigRegionService.findByExist(form);
			if (count > 0) {
				CODEMSG = PAGE_CONFIG_REGION_NAME_EXIST;
				return finish(CODEMSG, null);
			}
			int result = pageConfigRegionService.update(form);
			if (result > 0) {
				CODEMSG = SUCCESS;
			} else {
				map = null;
				CODEMSG = ERROR;
			}
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, map);
	}
	
	/**
	 * 更新区域页面参数模板状态
	 * @Title: updateState
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月7日 下午3:12:37
	 * @param form
	 * @return
	 */
	@PutMapping(value = "/state/update")
	public Object updateState(@RequestBody PageConfigRegion form) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			String page_config_region_code = form.getPage_config_region_code();
			int state = form.getState();
			if (StringUtils.isEmpty(page_config_region_code) || RegularUtil.isSpecialChar(page_config_region_code) || RegularUtil.matchLength(page_config_region_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if (!(state == 1 || state == 2)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			int result = pageConfigRegionService.update(form);
			if (result > 0) {
				CODEMSG = SUCCESS;
			} else {
				map = null;
				CODEMSG = ERROR;
			}
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
		}
		return finish(CODEMSG, map);
	}
	
	
	
	/**
	 * 根据登陆用户查询对应的自查自纠区域模板
	 * @Title: list
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:34:44
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/checkSelf/single")
	public Object findJoin(PageConfigRegion form ,HttpServletRequest request) {
		Map<String,Object> map = new HashMap<>();
		try {
			String companytype_code = form.getCompanytype_code();
			if (StringUtils.isEmpty(companytype_code) || RegularUtil.matchLength(companytype_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			
			User user = TokenUserUtil.generateUser(request, tokenParam);
			//自查自纠
			form.setPage_module(3);
			//启用状态
			form.setState(1);
			//用户登录地区,自查项只到省
			form.setProvince(user.getUser_province());
		/*	form.setCity(user.getUser_city());
			form.setArea(user.getUser_area());
			form.setTown(user.getUser_town());
			form.setVill(user.getUser_vill());*/
			
			List<Object> check_self_list = new ArrayList<Object>();
			Map<String,Object> item = null;
			List<PageConfigRegionReturn> page_config_region_list = pageConfigRegionService.findJoin(form);
			for (PageConfigRegionReturn pageConfigRegionReturn : page_config_region_list) {
				item = new HashMap<String,Object>();
				item.put("page_config_region_code",pageConfigRegionReturn.getPage_config_region_code());
				item.put("page_config_code",pageConfigRegionReturn.getPage_config_code());
				item.put("page_config_name",pageConfigRegionReturn.getPage_config_name());
				item.put("page_region_content", JSONObject.parse(String.valueOf(pageConfigRegionReturn.getPage_region_content())));
				item.put("page_config_content", JSONObject.parse(String.valueOf(pageConfigRegionReturn.getPage_config_content())));
				item.put("calc_period", pageConfigRegionReturn.getCalc_period());
				check_self_list.add(item);
			}
			map.put("page_config_region_list",check_self_list);
			
			CODEMSG = SUCCESS ;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, map);
	}
	
	/**
	 * 根据监管分类下自查自纠配置
	 * @Title: list
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年2月5日 下午4:34:44
	 * @param form
	 * @return
	 */
	@PutMapping(value = "/checkSelf/update")
	public Object checkSelfUpdate(@RequestBody PageConfigRegion form ,HttpServletRequest request) {
		Map<String,Object> map = new HashMap<>();
		try {
			String companytype_code = form.getCompanytype_code();
			if (StringUtils.isEmpty(companytype_code) || RegularUtil.matchLength(companytype_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			List<PageConfigRegion> check_self_list = form.getCheck_self_list();
			if(StringUtils.isEmpty(check_self_list) || check_self_list.size() == 0) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			User user = TokenUserUtil.generateUser(request, tokenParam);
			//自查自纠
			form.setPage_module(3);
			//启用状态
			form.setState(1);
			//用户登录地区,自查项只到省
			form.setProvince(user.getUser_province());
			
			
			StringBuffer sb = new StringBuffer();
			//处理批量提交的自查自纠配置
			for (PageConfigRegion pageConfigRegion : check_self_list) {
				form.setCalc_period(pageConfigRegion.getCalc_period());
				pageConfigRegion.setUser_code_update(user.getUser_code());
				pageConfigRegion.setUser_name_update(user.getUser_name());
				pageConfigRegion.setPage_region_content(JSON.toJSONString(pageConfigRegion.getPage_region_content()));
				sb.append("'").append(pageConfigRegion.getPage_config_region_code()).append("',");
			}
			
			if(!RegularUtil.OneToAnyNumber(form.getCalc_period()+"")) {
				CODEMSG = CALC_PERIOD_UNVALID;
				return finish(CODEMSG, null);
			}
			
			form.setPager_list(check_self_list);
			int result = pageConfigRegionService.updateBatch(form);
			
			if (result > 0) {
				//将未选中的配置内容设置为null
				String page_config_region_codes = sb.toString();
				if(!StringUtils.isEmpty(page_config_region_codes) && page_config_region_codes.length() > 1) {
					page_config_region_codes = page_config_region_codes.substring(0, page_config_region_codes.length()-1);
					form.setPage_config_region_codes(page_config_region_codes);
					form.setPage_region_content("");
					pageConfigRegionService.updateJoin(form);
				}
				CODEMSG = SUCCESS;
			} else {
				map = null;
				CODEMSG = ERROR;
			}
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, map);
	}
}
