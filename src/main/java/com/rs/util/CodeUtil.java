package com.rs.util;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.rs.po.User;
/**
 * 系统编码辅助类
 * @ClassName: CodeUtil
 * @Description:
 * @Author sven
 * @DateTime 2019年7月25日 上午10:49:38
 */
public class CodeUtil {

	/**
	 * 随机长度
	 * @Title: random
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年7月25日 上午10:50:02
	 * @param len
	 * @return
	 */
	public static String random(int len) {
		long lng = new Random().nextLong();
		if (lng < 0) {
			lng = lng * (-1);
		}
		String lngStr = "" + lng;
		if (lngStr.length() < len) {
			lngStr += random(len - lngStr.length());
		} else {
			lngStr = lngStr.substring(0, len);
		}
		return lngStr;
	}

	/**
	 * 根据表名获取主键编码
	 * 
	 * @Title: getSystemCode
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年7月25日 上午10:47:08
	 * @param name
	 * @return
	 */
	public static String getSystemCode(String name) {
		String prefix = "100";
		if (name.equals("user")) { // 用户
			prefix = "101";
		}else if (name.equals("role")) {
			prefix = "102";
		}else if (name.equals("report")) { // 报备
			prefix = "105";
		}else if (name.equals("reportProcess")) { // 报备场地
			prefix = "106";
		}else if (name.equals("reportProduct")) { // 报备菜品
			prefix = "107";
		}else if (name.equals("mainSubChef")) { // 帮厨
			prefix = "108";
		}else if (name.equals("train")) { // 培训
			prefix = "109";
		}else if (name.equals("trainCourse")) {//培训课程
			prefix = "110";
		}else if (name.equals("trainExam")) { //考试
			prefix = "111";
		}else if (name.equals("trainExamLog")) {
			prefix = "113";
		}else if (name.equals("trainCourseLog")) {//课程日志
			prefix = "116";
		}else if (name.equals("device")) {//设备
			prefix = "117";
		}else if (name.equals("advice")) {//执法建议
			prefix = "118";
		}else if (name.equals("sensor")) {//传感器
			prefix = "118";
		}else if (name.equals("sensorThreshold")) {//传感器预警值
			prefix = "119";
		}else if (name.equals("category")) {//品种分类
			prefix = "201";
		}else if (name.equals("companyType")) {//食品监管分类
			prefix = "212";
		}else if (name.equals("companyTag")) {//主体类型
			prefix = "213";
		}else if (name.equals("company")) {
			prefix = "214";
		}else if (name.equals("department")) {	//监管部门
			prefix = "215";
		}else if (name.equals("pageAttr")) { //页面属性
			prefix = "216";
		}else if (name.equals("pageConfig")) { //页面属性模板
			prefix = "217";
		}else if (name.equals("pageConfigRegion")) { //区域-页面模板中间表
			prefix = "218";
		}else if (name.equals("companyIntegrity")) { //企业诚信评价结果
			prefix = "219";
		}else if (name.equals("companyIntegrityLog")) { //企业诚信评价日志
			prefix = "220";
		}else if (name.equals("mess")) { //平台消息
			prefix = "221";
		}else if (name.equals("dictionary")) { //数据字典
			prefix = "222";
		}else if (name.equals("companyCCLJ")) { //餐厨垃圾处理
			prefix = "223";
		}else if (name.equals("companyArea")) { //企业区域
			prefix = "224";
		}else if (name.equals("foodSource")) {	//溯源主表
			prefix = "300";
		}else if (name.equals("foodSourceDetail")) {//溯源明细
			prefix = "301";
		}else if (name.equals("foodSourceAccessory")) {//溯源明细附件
			prefix = "302";
		}else if (name.equals("foodSourceSample")) {//留样
			prefix = "303";
		}else if (name.equals("patrol")) {
			prefix = "400";
		}else if (name.equals("patrolConfig")) {
			prefix = "401";
		}else if (name.equals("news")) {
			prefix = "501";
		}else if (name.equals("companyEmploy")) {
			prefix = "601";
		}else if (name.equals("accessory")) {
			prefix = "701";
		}else if (name.equals("newsCategory")) {
			prefix = "702";
		}else if (name.equals("log")) {
			prefix = "703";
		}else if (name.equals("checkSelf")) {
			prefix = "704";
		}else if (name.equals("complaintReport")) {
			prefix = "705";
		}else if (name.equals("caseCenter")) {
			prefix = "706";
		}else if (name.equals("mainSubChef")) {
			prefix = "707";
		}else if (name.equals("reportCheck")) {
			prefix = "708";
		}else if (name.equals("dispose")) {
			prefix = "709";
		}else if (name.equals("punish")) {
			prefix = "710";
		}
		
		prefix += System.currentTimeMillis();
		String len = CalcUtil.subtr("19", String.valueOf(prefix.length()));
		return prefix + random(Integer.valueOf(len)) + random(6);
	}
	
	/**
	 * 生成8位不重复udcode
	 * @Title: getUdCode
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年8月13日 下午3:54:01
	 * @param length
	 * @return
	 */
	public static String getUdCode(int length) {
		return UUID.randomUUID().toString().replace("-", "").substring(0, length);
	}
	
	/**
	 * unicode转中文
	 * @Title: unicodeToString
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年9月9日 上午11:51:33
	 * @param str
	 * @return
	 */
	public static String unicodeToString(String str) {
		return String.valueOf(JSON.parse(str));
	}

	/**
	 * 加
	 */
	public static String addtr(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.add(b2).toString();
	}

	/**
	 * 减
	 */
	public static String subtr(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.subtract(b2).toString();
	}

	/**
	 * 乘
	 */
	public static String multiply(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.multiply(b2).toString();
	}

	/**
	 * 除
	 */
	public static String division(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		if("0.00".equals(v1)||"0.00".equals(v2)) {
			return "0.00";
		}else {
			return b1.divide(b2, 10, RoundingMode.HALF_DOWN).toString();
		}
	}

	/**
	 * 乘
	 */
	public static String multiply(String v1, String v2, String v3) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		BigDecimal b3 = new BigDecimal(v3);
		return b1.multiply(b2).multiply(b3).toString();
	}
	/**
	 * 
	 * @Title: getSortNum
	 * @Description: 获取新增的编号
	 * @Author tangsh
	 * @DateTime 2019年11月25日 下午5:45:17
	 * @param str
	 * @return
	 */
	public static String getSortNum(String str) {
		Integer oldstr=Integer.parseInt(str);
		Integer newstr=1000001+oldstr;
		str=String.valueOf(newstr);
		return str.substring(1, str.length());
	}
	
	
	/**
	 * 
	 * @Title: getRegionCode
	 * @Description: 获取区域系统编码code
	 * @Author tangsh
	 * @DateTime 2020年2月4日 下午2:33:36
	 * @param last_code 排序的最后一个code 
	 * @param region_type 新增级别
	 * @return
	 */
	public static String getRegionCode(String last_code,int region_type) {
		String begin_str=last_code.substring(0,2);
		String str=last_code.substring(2,last_code.length());
		Long oldstr=Long.parseLong(str);
		Long newnum=0L;
		switch (region_type) {
		case 2:
			newnum=oldstr+100000000;
			break;
		case 3:
			newnum=oldstr+1000000;
			break;
		case 4:
			newnum=oldstr+1000;
			break;
		case 5:
			newnum=oldstr+1;
			break;
		default:
			break;
		}
		return begin_str+String.valueOf(newnum);
	}
	
	/**
	 * 根据用户类型设置Role  -群宴
	 * @param form
	 * @return
	 */
	public static Map<String, String> setUserRole(User form) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_code", form.getUser_code());

		if (("超级管理人员").equals(form.getUser_type())) {
			map.put("role_code", "1021501578606417000000001");

		} else if (("平台管理员").equals(form.getUser_type())||("监管员").equals(form.getUser_type())) {
			if(("1").equals(form.getUser_level())) {
				map.put("role_code", "1021501578606417000000002"); 
			}else if(("2").equals(form.getUser_level())) {
				map.put("role_code", "1021501578606417000000003");
			}else if(("3").equals(form.getUser_level())) {
				map.put("role_code", "1021501578606417000000004");
			}else if(("4").equals(form.getUser_level())) {
				map.put("role_code", "1021501578606417000000005");
			}else if(("5").equals(form.getUser_level())) {
				map.put("role_code", "1021501578606417000000011");
			}
		} else if(("协管员").equals(form.getUser_type())||("网格员").equals(form.getUser_type())) {
			map.put("role_code", "1021501578606417000000006");
		}else if (("乡村酒店").equals(form.getUser_type())) {
			map.put("role_code", "1021501578606417000000007");
		}else if (("农家乐").equals(form.getUser_type())) {
			map.put("role_code", "1021501578606417000000008");
		}else if (("乡厨").equals(form.getUser_type())) {
			map.put("role_code", "1021501578606417000000009");
		}else if (("举办者").equals(form.getUser_type())) {
			map.put("role_code", "1021501578606417000000010");
		}else if (("食品经营者").equals(form.getUser_type())) {
			map.put("role_code", "1021501578606417000000012");
		}else if (("配送企业").equals(form.getUser_type())) {
			map.put("role_code", "1021501578606417000000013");
		}
		return map;
	}
	
	
	/**
	 * 根据用户类型设置Role-三小
	 * @param form
	 * @return
	 */
	public static String setTsUserRole(User form) {
		if (("超级管理人员").equals(form.getUser_type())) {
			return "1022019578606417000000001";
		} else if (("平台管理员").equals(form.getUser_type())||("监管员").equals(form.getUser_type())) {
			if(("1").equals(form.getUser_level())) {
				return "1022019578606417000000002";
			}else if(("2").equals(form.getUser_level())) {
				return "1022019578606417000000003";
			}else if(("3").equals(form.getUser_level())) {
				return "1022019578606417000000004";
			}else if(("4").equals(form.getUser_level())) {
				return "1022019578606417000000005";
			}else if(("5").equals(form.getUser_level())) {
				return "1022019578606417000000006";
			}
		} else if(("协管员").equals(form.getUser_type())||("网格员").equals(form.getUser_type())) {
			return "1022019578606417000000007";
		}
		return "1";
	}
}
