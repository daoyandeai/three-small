package com.rs.util;

import java.io.File;

/**
 * 
 * @ClassName: FileUtil
 * @Description: 文件操作工具类
 * @Author tangsh
 * @DateTime 2020年6月1日 下午5:18:18
 */
public class FileUtil {

	/**
	 * 
	 * @Title: deleteAllFilesOfDir
	 * @Description: 删除文件夹
	 * @Author tangsh
	 * @DateTime 2020年6月1日 下午5:18:54
	 * @param path
	 */
	public static void deleteAllFilesOfDir(File path) {
		if (null != path) {
			if (!path.exists())
				return;
			if (path.isFile()) {
				boolean result = path.delete();
				int tryCount = 0;
				while (!result && tryCount++ < 10) {
					System.gc();
					result = path.delete();
				}
			}
			File[] files = path.listFiles();
			if (null != files) {
				for (int i = 0; i < files.length; i++) {
					deleteAllFilesOfDir(files[i]);
				}
			}
			path.delete();
		}
	}
	
	/**
	 * 
	 * @Title: deleteFile
	 * @Description: 删除文件
	 * @Author tangsh
	 * @DateTime 2020年6月1日 下午5:19:46
	 * @param pathname
	 * @return
	 */
	public static boolean deleteFile(String pathname){
		boolean result = false;
		File file = new File(pathname);
		if (file.exists()) {
			file.delete();
			result = true;
		}
		return result;
	}
}
