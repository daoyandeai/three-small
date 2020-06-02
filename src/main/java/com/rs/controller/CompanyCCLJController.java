package com.rs.controller;
import com.rs.po.Accessory;
import com.rs.po.CompanyCCLJ;
import com.rs.po.returnPo.CompanyCCLJReturn;
import com.rs.service.AccessoryService;
import com.rs.service.CompanyCCLJService;
import com.rs.util.RegularUtil;
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
 * @Author sven
 * @DateTime 2020年3月9日 下午4:05:59
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/companyCCLJ")
public class CompanyCCLJController extends BaseController {

	@Autowired
	private CompanyCCLJService companyCCLJService;
	@Autowired
	private AccessoryService accessoryService;
	
	private Logger logger = LoggerFactory.getLogger(CompanyCCLJController.class);

	/**
	 * 查询餐厨垃圾处理_分页
	 * @Title: list
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月9日 下午4:07:27
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(CompanyCCLJ form) {
		Map<String,Object> map = new HashMap<>();
		try {
			String company_code = form.getCompany_code();
			if(StringUtils.isEmpty(company_code) || RegularUtil.matchLength(company_code, 25)) {
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG, null);
			}
			if(!StringUtils.isEmpty(form.getCclj_time())) {
				form.setCclj_time(form.getCclj_time()+" 00:00:00");
			}
			if(!StringUtils.isEmpty(form.getEnd_time())) {
				form.setEnd_time(form.getEnd_time()+" 23:59:59");
			}
			List<CompanyCCLJReturn> ccjlList = companyCCLJService.findByList_app(form);
			Accessory accessory = null;
			for (CompanyCCLJReturn companyCCLJReturn : ccjlList) {
				accessory = new Accessory();
				accessory.setOther_code(companyCCLJReturn.getCclj_code());
				companyCCLJReturn.setAccessory_list(accessoryService.findByAll_app(accessory));
			}
			map.put("company_cclj_list",ccjlList);
			map.put("pager_count",companyCCLJService.findByCount(form));
			CODEMSG = SUCCESS ;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, map);
	}

}
