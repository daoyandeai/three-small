package com.rs.controller;

import com.rs.po.Region;
import com.rs.po.returnPo.RegionReturn;
import com.rs.service.RegionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: AppRegionController
 * @Description: 地域查询控制层
 * @Author tangsh
 * @DateTime 2020年6月1日 下午4:21:34
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/app/region")
public class AppRegionController extends BaseController {

	@Autowired
	private RegionService regionService;

	private Logger logger = LoggerFactory.getLogger(AppRegionController.class);

	/**
	 * 
	 * @Title: list
	 * @Description: 地域查询，通过high_code查询下级所有的地域name和code
	 * @Author tangsh
	 * @DateTime 2020年6月1日 下午4:21:55
	 * @param form
	 * @return
	 */
	@GetMapping(value = "/list")
	public Object list(Region form) {
		CODEMSG = ERROR;
		List<Region> all = new ArrayList<>();
		List<RegionReturn> list = new ArrayList<>();
		try {
			if(StringUtils.isEmpty(form.getRegion_high_code())){
				form.setRegion_high_code("C0");
				form.setRegion_type(1);
			}
			all = regionService.findByAll(form);
			for (Region region:all) {
				list.add(new RegionReturn(region.getRegion_code(),region.getRegion_name()));
			}
			CODEMSG=SUCCESS;
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, list);
	}
}
