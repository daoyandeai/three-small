package com.rs.controller;
import com.rs.po.Accessory;
import com.rs.po.Company;
import com.rs.po.CompanyCCLJ;
import com.rs.po.User;
import com.rs.po.returnPo.CompanyCCLJReturn;
import com.rs.service.AccessoryService;
import com.rs.service.CompanyCCLJService;
import com.rs.service.CompanyService;
import com.rs.service.UserService;
import com.rs.util.CalcUtil;
import com.rs.util.CodeUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 餐厨垃圾处理控制层
 * @ClassName: CompanyCCLJController
 * @Description: 
 * @Author wsc
 * @DateTime 2020年3月9日 下午4:05:59
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/app/companyCCLJ")
public class AppCompanyCCLJController extends BaseController {

	@Autowired
	private CompanyCCLJService companyCCLJService;
	@Autowired
	private AccessoryService accessoryService;
	@Autowired
	private UserService userService;
	@Autowired
	private CompanyService companyService;
	
	
	
	private Logger logger = LoggerFactory.getLogger(AppCompanyCCLJController.class);

	/**
	 * 查询餐厨垃圾处理_分页
	 * @Title: list
	 * @Description: 
	 * @Author wsc
	 * @DateTime 2020年3月9日 下午4:07:27
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/save")
	public Object save(@RequestBody Map<String,String> map) {
		try {
			CompanyCCLJ cclj = new CompanyCCLJ();
			cclj.setCclj_code(CodeUtil.getSystemCode("companyCCLJ"));
			cclj.setDictionary_code(map.get("dictionary_code"));
			cclj.setCclj_count(map.get("cclj_count"));
			cclj.setCclj_time(map.get("cclj_time"));
			cclj.setCclj_handler(map.get("cclj_handler"));
			cclj.setDictionary_module(map.get("dictionary_module"));
			
			User user = new User();
			user.setUser_code(map.get("user_code"));
			user = userService.findByCode(user);
			cclj.setCompany_code(user.getCompany_code());
			companyCCLJService.save(cclj);
			
			//更新企业餐厨垃圾处理数次
			Company company = new Company();
			company.setCompany_code(user.getCompany_code());
			company = companyService.findByCode(company);
			company.setCclj_time_last(map.get("cclj_time"));
			company.setCclj_count(CalcUtil.addtr(company.getCclj_count(),"1"));
			companyService.update(company);
			
			//添加餐厨处理素材
			String images = map.get("images");
			if (!StringUtils.isEmpty(images)) {
				String[] arrImg = images.split(",");
				for(Object image: arrImg) {
					Accessory accessory = new Accessory();
					accessory.setAccessory_code(CodeUtil.getSystemCode("accessory"));
					accessory.setAccessory_type("餐厨垃圾");
					accessory.setAccessory_url((String)image);
					accessory.setCompany_code(user.getCompany_code());
					accessory.setOther_code(cclj.getCclj_code());
					accessoryService.save(accessory);
				}
			}
			
			CODEMSG = SUCCESS ;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, null);
	}
	
	
	@GetMapping(value = "/findByList_app")
	public Object findByList_app(@RequestParam String user_code, @RequestParam Integer pager_offset, @RequestParam Integer pager_openset) {
		
		Map<String, List<CompanyCCLJReturn>> newMap = new HashMap<>();
	
		try {
			User user = new User();
			CompanyCCLJ cclj = new CompanyCCLJ();
			user.setUser_code(user_code);
			User newUser = userService.findByCode(user);
			cclj.setCompany_code(newUser.getCompany_code());
			cclj.setPager_offset(pager_offset);
			cclj.setPager_openset(pager_openset);
			
			List<CompanyCCLJReturn> list = companyCCLJService.findByList_app(cclj);
			for (CompanyCCLJReturn c: list) {
				Accessory accessory = new Accessory();
				accessory.setOther_code(c.getCclj_code());
				c.setAccessory_list(accessoryService.findByAll_app(accessory));
			}
			newMap.put("pager_list",list);
			CODEMSG = SUCCESS ;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, newMap);
	}

}
