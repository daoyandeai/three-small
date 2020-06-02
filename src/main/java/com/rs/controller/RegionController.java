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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: RegionController
 * @Description: 地域查询控制层
 * @Author tangsh
 * @DateTime 2020年6月1日 下午4:20:55
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/api/region")
public class RegionController extends BaseController {

	@Autowired
	private RegionService regionService;

	private Logger logger = LoggerFactory.getLogger(RegionController.class);

	/**
	 * 
	 * @Title: list
	 * @Description: 地域查询，通过high_code查询下级所有的地域name和code
	 * @Author tangsh
	 * @DateTime 2020年3月17日 下午3:30:13
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
	
	
	/**
	 * 
	 * @Title: save
	 * @Description: 新增区域
	 * @Author tangsh
	 * @DateTime 2020年2月4日 下午2:12:34
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/addArea")
	public Object save(@RequestBody Region form) {
		CODEMSG = ERROR;
		try {
			if(StringUtils.isEmpty(form.getRegion_name())){
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG,null);
			}
			if(StringUtils.isEmpty(form.getRegion_type())){
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG,null);
			}
			if(StringUtils.isEmpty(form.getRegion_high_code())){
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG,null);
			}
			if(regionService.save(form)>0){
				CODEMSG = SUCCESS;
			}
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
	 * @Title: updateName
	 * @Description: 变更名称  （注意：变更名称需要页面提供变更级别和之前的节点名称 例如变更乡镇镇 需要提供XX省XX市XX县XX乡镇以及type）
	 * @Author tangsh
	 * @DateTime 2020年2月4日 下午2:56:46
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/changeName")
	public Object updateName(@RequestBody Region form) {
		CODEMSG = ERROR;
		try {
			if(StringUtils.isEmpty(form.getRegion_name())){
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG,null);
			}
			form.setRegion_code("'"+form.getRegion_code()+"'");
	    	if(!StringUtils.isEmpty(form.getRegion_vill())) {
	    		form.setRegion_vill("'"+form.getRegion_vill()+"'");
	    	}
			if(regionService.update(form)>0){
				CODEMSG = SUCCESS;
			}
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
	 * @Title: updateArea
	 * @Description: 把指定区域迁出并入
	 * @Author tangsh
	 * @DateTime 2020年2月4日 下午4:17:02
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/changeArea")
	public Object updateArea(@RequestBody Region form) {
		CODEMSG = ERROR;
		try {
			if(StringUtils.isEmpty(form.getRegion_high_code())){
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG,null);
			}
			if(regionService.updateArea(form)>0){
				CODEMSG = SUCCESS;
			}
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
	 * @Description: 删除区域
	 * @Author tangsh
	 * @DateTime 2020年2月4日 下午6:05:42
	 * @param form
	 * @return
	 */
	@DeleteMapping(value="/delete")
	public Object delete(Region form) {
		CODEMSG = ERROR;
		try {
			if(StringUtils.isEmpty(form.getRegion_code())){
				CODEMSG = PARAMETER_ERROR;
				return finish(CODEMSG,null);
			}
			if(regionService.delete(form)>0){
				CODEMSG = SUCCESS;
			}
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.info(ex.toString());
			return finish(CODEMSG, null);
		}
		return finish(CODEMSG, null);
	}

}
