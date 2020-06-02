package com.rs.controller;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.rs.service.UploadService;
import com.rs.util.RegularUtil;

/**
 * 
 * @ClassName: AppUploadController
 * @Description: 文件上传
 * @Author tangsh
 * @DateTime 2020年3月17日 下午3:23:49
 */
@Transactional
@Controller
@RequestMapping("/app/file")
public class AppUploadController extends BaseController {
	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	private UploadService uploadService;

	public static final String ROOT = "upload";
	
	private Logger logger = LoggerFactory.getLogger(AppUploadController.class);
	
	/**
	 * 上传附件
	 * @Title: upload
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月26日 下午4:39:30
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@PostMapping("/upload")
    public Object upload(HttpServletRequest request) throws IllegalStateException, IOException {
		Map<Object, Object> map = new HashMap<>();
		try {
			LinkedHashMap<String, Object> fileMap = (LinkedHashMap<String, Object>)request.getAttribute("file");
			CommonsMultipartFile file = (CommonsMultipartFile)fileMap.get("uploadFile");
			if(file==null) {
				file = (CommonsMultipartFile)fileMap.get("file");
			}
			String return_url = uploadService.upload(file, request);
			if(!StringUtils.isEmpty(return_url)) {
				map.put("visit_url", return_url);
				CODEMSG = SUCCESS;
			}else {
				CODEMSG = ERROR;
			}
    	}catch(Exception ex) {
    		logger.info(ex.getMessage());
    		CODEMSG=EXCEPTION;
		}
		return finish(CODEMSG,map);
    }
	
	
	/**
	 * 查询附件
	 * @Title: show
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年7月28日 下午8:07:41
	 * @param file_type
	 * @param file_date
	 * @param filename
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/show/upload/{file_type}/{file_date}/{filename}")
	public void show(@PathVariable("file_type") String file_type,@PathVariable("file_date") String file_date,@PathVariable("filename") String filename, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BufferedInputStream in = null;
		ByteArrayOutputStream bos = null;
		try {
			File file = resourceLoader.getResource("file:" + Paths.get(ROOT, file_type+File.separator+file_date+File.separator+filename).toString()).getFile();
			bos = new ByteArrayOutputStream();
			in = new BufferedInputStream(new FileInputStream(file));
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while (-1 != (len = in.read(buffer, 0, buf_size))) {
				bos.write(buffer, 0, len);
			}
			if (RegularUtil.isImg(filename)) {
				response.setContentType("image/png");
			} else {
				response.setHeader("content-type", "application/octet-stream");
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment;filename=" + filename);
			}
			response.getOutputStream().write(bos.toByteArray());
		} catch (IOException e) {
			logger.info(e.getMessage());
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
