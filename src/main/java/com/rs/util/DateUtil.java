package com.rs.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 时间工具类
 * 
 * @ClassName: DateUtil
 * @Description:
 * @Author sven
 * @DateTime 2019年7月11日 下午4:41:55
 */
public class DateUtil {
	
	public static final String Y_M_FORMAT = "yyyy-MM";
	public static final String MONTH = "MONTH";
	public static final String YYYY_MM_DD_FORMAT = "yyyy-MM-dd";
	
	
	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);
	
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	public static String getDateYearMonth() {
		return (new SimpleDateFormat("yyyy-MM")).format(new Date());
	}
	
	public static String getDateYearMonthDate() {
		return (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
	}
	
	public static String getDateYearMonthDateChinese() {
		return (new SimpleDateFormat("yyyy年MM月dd日")).format(new Date());
	}
	
	public static String getDateYearMonthDateStr() {
		return (new SimpleDateFormat("yyyyMMdd")).format(new Date());
	}

	public static String getDYMStr(Date date) {
		return (new SimpleDateFormat("yyyy-MM-dd")).format(date);
	}

	public static Date strToDate(String time) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd").parse(time);
	}

	public static String getCurrentTime() {
		return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
	}
	
	public static String getCurrentTimeM() {
		return (new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(new Date());
	}

	public static String getDateYear(){
		return (new SimpleDateFormat("yyyy")).format(new Date());
	}

	public static String getDateHH() {
		return (new SimpleDateFormat("HH")).format(new Date());
	}
	
	/**
	 * 
	 * @Title: getDateParam
	 * @Description: 获取随机数 格式yyyyMMddHHmmssMS+len长度随机数
	 * @Author tangsh
	 * @DateTime 2019年12月25日 上午9:59:49
	 * @param len
	 * @return
	 */
	public static String getDateParam(int len) {
		return (new SimpleDateFormat("yyyyMMddHHmmssMS")).format(new Date()) + random(len);
	}
	
	/**
	 * 
	 * @Title: random
	 * @Description: 获取len长度随机数
	 * @Author tangsh
	 * @DateTime 2019年12月25日 上午10:00:11
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
	 * 获取开始结束时间差
	 * 
	 * @param endtime
	 * @param addtime
	 * @return
	 * @throws ParseException
	 */
	public static String getDatePoor(String endtime, String addtime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Long start = 0l;
		try {
			start = sdf.parse(addtime).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long end = 0l;
		try {
			end = sdf.parse(endtime).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 获得两个时间的毫秒时间差异
		long diff = end - start;
		return String.valueOf(diff);
	}

	/**
	 * 根据出生日期自动计算年龄
	 * 
	 * @Title: getAgeByBirth
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年8月5日 上午11:26:48
	 * @param birthDay
	 * @return
	 * @throws ParseException
	 */
	public static int getAgeByBirth(String birthDay) throws ParseException {
		Date dateBirth = format.parse(birthDay);
		int age = 0;
		Calendar cal = Calendar.getInstance();
		if (cal.before(birthDay)) { // 出生日期晚于当前时间，无法计算
			throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
		}
		int yearNow = cal.get(Calendar.YEAR); // 当前年份
		int monthNow = cal.get(Calendar.MONTH); // 当前月份
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); // 当前日期
		cal.setTime(dateBirth);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
		age = yearNow - yearBirth; // 计算整岁数
		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth)
					age--;// 当前日期在生日之前，年龄减一
			} else {
				age--;// 当前月份在生日之前，年龄减一
			}
		}
		return age;
	}

	/**
	 * 判断时间 istype 1代表默认年月日时分秒 2代表年月日时分3代表年月日
	 * 
	 * @Title: isValidDate
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年8月14日 下午3:42:20
	 * @param str
	 * @param istype
	 * @return
	 */
	public static boolean isValidDate(String str, int istype) {
		boolean convertSuccess = true;
		String datestr = "";
		if (istype == 1) {
			datestr = "yyyy-MM-dd HH:mm:ss";
		} else if (istype == 2) {
			datestr = "yyyy-MM-dd HH:mm";
		} else if (istype == 3) {
			datestr = "yyyy-MM-dd";
		} else if (istype == 4) {
			datestr = "yyyy-MM";
		}
		SimpleDateFormat format = new SimpleDateFormat(datestr);
		try {
			format.setLenient(false);
			format.parse(str);
		} catch (Exception e) {
			logger.error("时间：【{}】转换时间格式异常",str);
			convertSuccess = false;
		}
		return convertSuccess;
	}

	/**
	 * 根据开始-结束时间得出中间日期
	 * 
	 * @Title: getBetweenDate
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年8月16日 下午5:14:07
	 * @param start_time
	 * @param end_time
	 * @return
	 */
	public static List<String> getBetweenDate(String start_time, String end_time) {
		List<String> lDate = new ArrayList<String>();
		Date start;
		Date end;
		try {
			start = strToDate(start_time);
			lDate.add(start_time);
			Calendar calBegin = Calendar.getInstance();
			// 使用给定的 Date 设置此 Calendar 的时间
			calBegin.setTime(start);
			// 测试此日期是否在指定日期之后
			end = strToDate(end_time);
			while (end.after(calBegin.getTime())) {
				// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
				calBegin.add(Calendar.DAY_OF_MONTH, 1);
				lDate.add(getDYMStr(calBegin.getTime()));
			}
			/*
			 * for (String string : lDate) { System.out.println(string); }
			 */
		} catch (ParseException e) {
			System.out.println("转换时间格式异常");
			e.printStackTrace();
		}
		return lDate;
	}

	/**
	 * 计算时间单位
	 * 
	 * @Author tangsh
	 * @param str
	 *            分钟
	 * @return
	 */
	public static String getDayHouseMinute(String str) {
		Integer minutestr = Integer.parseInt(str);
		int day = minutestr / (60 * 24);
		int hour = (minutestr - (60 * 24 * day)) / 60;
		int minute = minutestr - 60 * 24 * day - 60 * hour;
		String returnstr = "";
		if (day > 0) {
			returnstr = day + "天" + hour + "小时" + minute + "分钟";
		} else {
			if (hour > 0) {
				returnstr = hour + "小时" + minute + "分钟";
			} else {
				returnstr = minute + "分钟";
			}
		}
		return returnstr;
	}

	/**
	 * 时间戳转换日期时间
	 * 
	 * @Author tangsh
	 * @param str
	 *            时间戳
	 * @return
	 */
	public static String timestampToDate(long time) {
		if (time < 10000000000L) {
			time = time * 1000;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sd = sdf.format(new Date(Long.parseLong(String.valueOf(time))));
		return sd;
	}

	/**
	 * 
	 * @Title: stampToTime
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年8月18日 下午6:06:02
	 * @param stamp
	 * @return
	 */
	public static String stampToTime(String stamp) {
		String sd = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sd = sdf.format(new Date(Long.parseLong(stamp + "000"))); // 时间戳转换日期
		// System.out.println(sd);
		return sd;
	}
	
	/**
	 * 计算时间差
	 * @Title: dateDiff
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年9月9日 下午3:34:56
	 * @param startTime
	 * @param endTime
	 * @param format
	 * @return
	 */
	public static Long dateDiff(String startTime, String endTime, String format) {
		// 按照传入的格式生成一个simpledateformate对象
		SimpleDateFormat sd = new SimpleDateFormat(format);
		long diff = 0l;
		// 获得两个时间的毫秒时间差异
		try {
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//System.out.println("diff-->"+diff);
		return diff;
	}
	
	/**
	 * 根据当前时间获取计算时间
	 * @Title: getDatePlusYear
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月23日 下午5:47:57
	 * @param format 格式化类型
	 * @param type   计算类型
	 * @param createDate 当前时间
	 * @param day  计算天数
	 * @return
	 */
	public static String calcDate(String format, String type, String createDate, int day) {
		String temp = "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			Date date = sdf.parse(createDate);
			Calendar cl = Calendar.getInstance();
			cl.setTime(date);
			if (("DATE").equals(type)) {
				cl.add(Calendar.DATE, day);
			} else if (("MINUTE").equals(type)) {
				cl.add(Calendar.MINUTE, day);
			} else if (("SECOND").equals(type)) {
				cl.add(Calendar.SECOND, day);
			}else if(("MONTH").equals(type)) {
				cl.add(Calendar.MONTH, day);
			}else if(("YEAR").equals(type)) {
				cl.add(Calendar.YEAR, day);
			}
			temp = sdf.format(cl.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	public static void main(String[] args) {
		//获取服务器时间
		String hour = DateUtil.getDateHH();
		boolean flag = true;
		if(DateUtil.dateDiff("20",hour,"HH") >= 0 && DateUtil.dateDiff("22",hour,"HH") <= 0) {
			flag = true;
		}else {
			flag = false;
		}
		System.out.println(flag);
			
	}
}
