package com.rs.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

/**
 * 正则辅助类
 * 
 * @ClassName: RegularUtil
 * @Description:
 * @Author sven
 * @DateTime 2019年7月25日 上午11:09:17
 */
public class RegularUtil {

	/**
	 * 判断手机号码
	 * 
	 * @Title: isMobilePhone
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年7月25日 上午11:09:52
	 * @param str
	 * @return
	 */
	public static boolean isMobilePhone(String str) {
		Pattern pattern = Pattern.compile("^1((3|4|5|7|8|9){1}\\d{1}|70)\\d{8}$");
		//Pattern pattern = Pattern.compile("^1\\d{10}$");
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	/**
	 * 身高
	 * @Title: isHeight
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年7月29日 下午4:52:52
	 * @param str
	 * @return
	 */
	public static boolean isHeight(String str) {
		Pattern pattern = Pattern.compile("(^[1-9]{1}\\d{0,2})(\\.\\d{0,2})?$");
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	/**
	 * 体重
	 * @Title: isWeight
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年7月29日 下午4:53:01
	 * @param str
	 * @return
	 */
	public static boolean isWeight(String str) {
		Pattern pattern = Pattern.compile("(^[1-9]{1}\\d{0,2})(\\.\\d{0,2})?$");
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	
	/**
	 * 年龄
	 * @Title: isAge
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年7月29日 下午4:53:41
	 * @param str
	 * @return
	 */
	public static boolean isAge(String str) {
		Pattern pattern = Pattern.compile("^[1-9]{1}\\d{1}?$");
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 性别
	 * @Title: isSex
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年7月29日 下午4:54:26
	 * @param str
	 * @return
	 */
	public static boolean isSex(String str) {
		Pattern pattern = Pattern.compile("^[1-2]?$");
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 只包含数字和字母
	 * 
	 * @Title: isLoginNamePass
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年7月27日 上午11:40:12
	 * @param str
	 * @return
	 */
	public static boolean isLoginNamePass(String str) {
		Pattern pattern = Pattern.compile("^[A-Za-z0-9]*$");
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	/**
	 * 字符长度范围
	 * 
	 * @Title: isLength
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年7月27日 上午11:40:49
	 * @param str
	 * @param start
	 * @param end
	 * @return
	 */
	public static boolean isLength(String str, int start, int end) {
		if(StringUtils.isEmpty(str)) {
			return false;
		}
		int length = str.length();
		if (length < start || length > end) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * 字符长度
	 * 
	 * @Title: isLength
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年7月27日 上午11:40:49
	 * @param str
	 * @param start
	 * @param end
	 * @return
	 */
	public static boolean matchLength(String str, int length) {
		if(StringUtils.isEmpty(str)) {
			return true;
		}
		if (str.length() != length) {
			return true;
		}
		return false;
	}
	
	/**
	 * 生日
	 * 
	 * @Title: isLength
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年7月27日 上午11:40:49
	 * @param str
	 * @param start
	 * @param end
	 * @return
	 */
	public static boolean isBirthday(String birthday) {
		boolean flag = true;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if(birthday.substring(0,4).compareTo(DateUtil.getDateYear()) <= -1) {
				String regex = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
				Pattern pattern = Pattern.compile(regex);
				Matcher m = pattern.matcher(birthday);
				boolean dateFlag = m.matches();
				if (dateFlag) {
					formatter.parse(birthday);
				} else {
					flag = false;
				}
			}else {
				flag = false;
			}
		} catch (Exception e) {
			flag = false;
		}
		//System.out.println("flag------>"+flag);
		return flag;
	}

	/**
	 * 判断特殊字符
	 * 
	 * @Title: isSpecial_all
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年7月28日 下午3:58:19
	 * @param str
	 * @return
	 */
	public static boolean isSpecialChar(String str) {
		Pattern pattern = Pattern.compile("^[^\\`\\~\\^\\|\\;\\:\"\\'\\?\\\\\\？\\<\\>]*$");
		Matcher matcher = pattern.matcher(str);
		return !matcher.matches();
	}

	/**
	 * 判断图片格式
	 * 
	 * @Title: isImg
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年7月28日 下午7:30:25
	 * @param str
	 * @return
	 */
	public static boolean isImg(String str) {
		Pattern pattern = Pattern.compile("(?i).+?\\.(jpg|gif|bmp|png|jpeg)");
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	/**
	 * 判断音频格式
	 * 
	 * @Title: isImg
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年7月28日 下午7:30:25
	 * @param str
	 * @return
	 */
	public static boolean isAudio(String str) {
		Pattern pattern = Pattern.compile("(?i).+?\\.(wav|flac|ape|alac|mp3|amr)");
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	/**
	 * 判断视频格式
	 * 
	 * @Title: isImg
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年7月28日 下午7:30:25
	 * @param str
	 * @return
	 */
	public static boolean isVideo(String str) {
		Pattern pattern = Pattern.compile("(?i).+?\\.(rm|rmvb|flv|mpg|mpe|avi|mp4)");
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 判断整数_0到任意数字
	 * @Title: isNumberZero
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年8月18日 上午11:12:15
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		if(StringUtils.isEmpty(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile("^[0-9]*$");
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	
	/**
	 * 判断整数_1到任意数字
	 * @Title: isNumberZero
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年8月18日 上午11:12:15
	 * @param str
	 * @return
	 */
	public static boolean OneToAnyNumber(String str) {
		if(StringUtils.isEmpty(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile("^[1-9]\\d{0,4}?$");
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 包含小数
	 * @Title: isNumberZero
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年8月18日 上午11:12:15
	 * @param str
	 * @return
	 */
	public static boolean isDouble(String str) {
		if(StringUtils.isEmpty(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile("^(0|[1-9]{1}\\d{0,10})(\\.\\d{1,2})?$");
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 根据.切取字符串
	 * @Title: getStrend
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年8月27日 下午9:06:02
	 * @param str
	 * @param num
	 * @return
	 */
	public static String getStrend(String c,String str, int num) {
		String[] strVale = str.split("["+c+"]");
		str = strVale[num];
		return str;
	}
	
	/**
	 * 
	 * @Title: getListToString
	 * @Description: 把list转换为String字符串 根据指定分隔符号
	 * @Author tangsh
	 * @DateTime 2019年12月31日 下午2:10:09
	 * @param list
	 * @param charstr
	 * @return
	 */
	public static String getListToString(List<String> list,String charstr) {
        StringBuffer sb = new StringBuffer();
        if (list != null && list.size() > 0) {
            for (String s : list) {
            	if(!StringUtils.isEmpty(s)) {
            		sb.append(s + charstr);
                }
            }
            if (sb.length() > 1) {
                return sb.substring(0, sb.length() - 1);
            }
        }
        return "";
    }
	
	/**
	 * 
	 * @Title: getStringToList
	 * @Description: 把String字符串转换为list 根据指定分隔符号
	 * @Author tangsh
	 * @DateTime 2020年1月6日 上午10:32:23
	 * @param s
	 * @param charstr
	 * @return
	 */
	public static List<String> getStringToList(String s,String charstr){
        List<String> list = new ArrayList<>();
        if(!StringUtils.isEmpty(s)){
            String[] split = s.split(charstr);
            if(split!=null&&split.length>0){
                for (String temp: split) {
                	if(!StringUtils.isEmpty(temp)) {
                		list.add(temp);
                	}
                }
            }
        }
        return list;
    }
	
	/**
	 * 
	 * @Title: getListToSqlString
	 * @Description: 把list变成sql字符串
	 * @Author tangsh
	 * @DateTime 2020年1月7日 下午2:24:23
	 * @param strlist
	 * @return
	 */
	public static String getListToSqlString(List<String> strlist){
        String strsql="";
	    if(strlist!=null&&strlist.size()>0){
	        for (String str: strlist) {
	        	if(!StringUtils.isEmpty(str)) {
	        		strsql+="'"+str+"',";
	        	}
	        }
	        strsql=strsql.substring(0,strsql.length()-1);
	     }
        return strsql;
    }
	
	/**
	 * 
	 * @Title: getStringToSqlString
	 * @Description: string转换为sql string
	 * @Author tangsh
	 * @DateTime 2020年1月7日 下午2:30:32
	 * @param str
	 * @param charstr 分隔符号
	 * @return
	 */
	public static String getStringToSqlString(String str,String charstr){
		String strsql="";
        if(!StringUtils.isEmpty(str)){
            String[] split = str.split(charstr);
            if(split!=null&&split.length>0){
                for (String temp: split) {
                	if(!StringUtils.isEmpty(temp)) {
                		strsql+="'"+temp+"',";
                	}
                }
                strsql=strsql.substring(0,strsql.length()-1);
            }
        }
        return strsql;
    }
	
	
	/**
	 * 判断年份
	 * @Title: isYear
	 * @Description: 
	 * @Author sven
	 * @DateTime 2019年12月16日 上午10:32:38
	 * @param str
	 * @return
	 */
	public static boolean isYear(String year) {
		Pattern pattern = Pattern.compile("^(?:175[3-9])|(?:17[6-9][0-9])|(?:1[8-9]\\d\\d)|(?:[2-9]\\d\\d\\d)*$");
		Matcher matcher = pattern.matcher(year);
		return matcher.matches();
		
	}
	
	/**
	 * 
	 * @Title: getStrend
	 * @Description: 根据.切取字符串
	 * @Author tangsh
	 * @DateTime 2019年12月25日 上午10:00:59
	 * @param str
	 * @param num
	 * @return
	 */
	public static String getStrend(String str, int num) {
		String[] strVale = str.split("[.]");
		str = strVale[num];
		return str;
	}
	
	/**
	 * 空字符串
	 * @Title: trimZero
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月10日 下午2:24:34
	 * @param str
	 * @return
	 */
	public static boolean trimZero(String str) {
		if(str.trim().length() == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 
	 * @Title: isPrimitive
	 * @Description: 判断一个对象是否是基本类型或基本类型的封装类型
	 * @Author tangsh
	 * @DateTime 2020年4月17日 上午9:26:03
	 * @param obj
	 * @return
	 */
	public static boolean isPrimitive(Object obj) {
		try {
			return ((Class<?>)obj.getClass().getField("TYPE").get(null)).isPrimitive();
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 
	 * @Title: isPrimitiveOrString
	 * @Description: 判断一个对象是否是基本类型或基本类型的封装类型或者纯String对象
	 * @Author tangsh
	 * @DateTime 2020年4月17日 上午9:26:25
	 * @param obj
	 * @return
	 */
	public static boolean isPrimitiveOrString(Object obj) {
		if(obj==null) {
			return true;
		}
		if(!isPrimitive(obj)) {
			if(!obj.getClass().getName().equals("java.lang.String")) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
	}
}
