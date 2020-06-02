package com.rs.controller;

import java.util.HashMap;
import java.util.Map;
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

import com.rs.po.TokenParam;
import com.rs.util.BaiDuOCRUtil;
/**
 * 百度OCR图像识别控制层
 * @ClassName: BaiduOCRController
 * @Description: 
 * @Author sven
 * @DateTime 2020年6月1日 下午3:42:52
 */
@Transactional
@RestController
@CrossOrigin
@RequestMapping("/app/baidu/orc")
public class BaiduOCRController extends BaseController{
	
	@Autowired
	private TokenParam tokenParam;
	
	private Logger logger = LoggerFactory.getLogger(BaiduOCRController.class);
	
	/**
	 * 
	 * @Title: discern
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年6月1日 上午10:15:16
	 * @param file_path   识别的附件地址
	 * @param discern_type 识别的附件类型  1：身份证  2：营业执照 3：菜品
	 * @param id_card_side front：身份证含照片的一面；back：身份证带国徽的一面
	 * @return
	 */
	@GetMapping(value = "/discern")
	public Object discern(String file_path,Integer discern_type,String id_card_side) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		Object discern_result = new Object();
		try {
			if(StringUtils.isEmpty(file_path)) {
				CODEMSG = FILE_PATH_UNVALID;
				return finish(CODEMSG, null);
			}
			if(StringUtils.isEmpty(discern_type)) {
				CODEMSG = DISCERN_TYPE_UNVALID;
				return finish(CODEMSG, null);
			}
			String file_url = tokenParam.getFile_url();
			file_path = file_path.substring(file_path.indexOf("images"),file_path.length());
			switch (discern_type) {
			case 1:
				discern_result = BaiDuOCRUtil.parseIdCard(file_url+file_path,id_card_side);
				break;
			case 2:
				discern_result = BaiDuOCRUtil.parseLicence(file_url+file_path);
				break;
			case 3:
				discern_result = BaiDuOCRUtil.parseDish(file_url+file_path);
				break;
			default:
				break;
			}
			map.put("discern_result",discern_result);
			CODEMSG = SUCCESS;
		} catch (Exception ex) {
			map = null;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CODEMSG = EXCEPTION;
			logger.error(ex.toString());
		}
		return finish(CODEMSG, map);
	}
}
