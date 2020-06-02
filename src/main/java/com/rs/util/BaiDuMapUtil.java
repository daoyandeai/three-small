package com.rs.util;
import java.text.DecimalFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
/**
 * 百度地图Util
 * 
 * @ClassName: BaiDuMapUtil
 * @Description:
 * @Author sven
 * @DateTime 2019年9月6日 下午3:52:27
 */
public class BaiDuMapUtil {

	private static Logger logger = LoggerFactory.getLogger(BaiDuMapUtil.class);

	/**
	 * 根据坐标解析位置
	 * 
	 * @Title: getLocByLngAndLat
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年9月6日 下午3:53:14
	 * @param longitude
	 * @param latitude
	 * @return
	 * @throws Exception
	 */
	public static String getLocByLngAndLat(String longitude, String latitude) throws Exception {
		String locJson = HttpUtil.sendGet("http://api.map.baidu.com/geoconv/v1/?coords=" + longitude + "," + latitude+"&from=1&to=5&ak=oqnvdL14rNlcc5gi520BAbRZlW57T4NI", "UTF-8", null);
		JSONObject jobject = JSON.parseObject(locJson);
		JSONArray jsonArray = jobject.getJSONArray("result");
		// 根据经纬度获取百度坐标
		String lat = jsonArray.getJSONObject(0).getString("y");
		String lng = jsonArray.getJSONObject(0).getString("x");
		// 根据百度坐标获取地理位置
		String addrJson = HttpUtil.sendGet("http://api.map.baidu.com/geocoder/v2/?ak=7GAxnzcDaWjbiLx19K3Hge1OKBuK5alo&location=" + lat+ "," + lng + "&output=json&pois=1", "UTF-8", null);
		// System.out.println(addrJson);
		JSONObject jobjectaddr = JSON.parseObject(addrJson);
		String addr = jobjectaddr.getJSONObject("result").getString("addressComponent");
		// 获取城市
		String city = JSON.parseObject(addr).getString("city");
		logger.info("city:{}", city);
		return city;
	}

	/**
	 * 根据地理位置逆解析坐标
	 * 
	 * @Title: getLngAndLatByLoc
	 * @Description:
	 * @Author sven
	 * @DateTime 2020年3月24日 上午10:52:17
	 * @param loca
	 * @return
	 */
	public static String[] getLngAndLatByLoc(String address) {
		String [] arr = new String[2];
		if (!StringUtils.isEmpty(address)) {
			address = address.replaceAll("\\s*", "").replace("#", "栋").replace("null", "");
			if(StringUtils.isEmpty(address)) {
				return arr;
			}
			String url = "http://api.map.baidu.com/geocoder/v2/?address=" + address+ "&output=json&ak=oqnvdL14rNlcc5gi520BAbRZlW57T4NI";
			String json = HttpUtil.sendGet(url, "UTF-8", null);
			if (!StringUtils.isEmpty(json)) {
				JSONObject obj = JSONObject.parseObject(json);
				if ("0".equals(obj.getString("status"))) {
					double lng = obj.getJSONObject("result").getJSONObject("location").getDouble("lng"); // 经度
					double lat = obj.getJSONObject("result").getJSONObject("location").getDouble("lat"); // 纬度
					DecimalFormat df = new DecimalFormat("#.######");
					logger.info("lng:{},lat:{}", lng,lat);
					arr[0] = df.format(lng);
					arr[1] = df.format(lat);
					return arr;
				}
			}
		}
		return arr;
	}
	public static void main(String[] args) {
		System.out.println(getLngAndLatByLoc("11111111").length);
	}
}
