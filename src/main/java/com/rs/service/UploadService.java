package com.rs.service;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.rs.po.TokenParam;
import com.rs.util.DateUtil;
import com.rs.util.RegularUtil;
/**
 * 移动端上传服务类
 * @ClassName: UploadService
 * @Description: 
 * @Author sven
 * @DateTime 2020年6月1日 上午9:51:28
 */
@Service
public class UploadService{
	
	public static final String ROOT = "upload"; 
	public static final String IMAGE = "images";
	public static final String AUDIO = "audio";
	public static final String VIDEO = "video";
	public static final String OTHER = "other";
	@Autowired
	private TokenParam tokenParam;
	
	private static Logger logger = LoggerFactory.getLogger(UploadService.class);
	
	/**
	 * 
	 * @Title: upload
	 * @Description:上传附件
	 * @Author tangsh
	 * @param uploadFile
	 * @param request
	 * @return
	 */
	public String upload(MultipartFile uploadFile, HttpServletRequest request) {
		String filename = uploadFile.getOriginalFilename();
		String file_path = IMAGE;
		if(RegularUtil.isImg(filename)) {
			file_path = IMAGE;
		}else if(RegularUtil.isAudio(filename)) {
			file_path = AUDIO;
		}else if(RegularUtil.isVideo(filename)) {
			file_path = VIDEO;
		}else {
			file_path = OTHER;
		}
		String folderdate= DateUtil.getDateYearMonth();
//		String realPath = new File(Paths.get(ROOT).toAbsolutePath().toString())+File.separator+file_path+File.separator+folderdate;
		String realPath = tokenParam.getFile_url()+File.separator+file_path+File.separator+folderdate;
		String mrurl= tokenParam.getFile_ip()+tokenParam.getFile_dir()+File.separator+file_path+File.separator+folderdate;
		File dir = new File(realPath);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		try {
			// 服务端保存的文件对象
			filename = DateUtil.getDateParam(15)+"."+RegularUtil.getStrend(filename,1);
			File fileServer = new File(dir, filename);
			
			logger.info("file文件真实路径:{}",fileServer.getAbsolutePath());
			// 2，实现上传
			uploadFile.transferTo(fileServer);
			
			String filePath = mrurl+File.separator+filename;
			// 3，返回可供访问的网络路径
			return filePath;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "上传失败";
	}
}
