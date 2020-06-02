package com.rs.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 签名工具类
 * 
 * @ClassName: SignUtil
 * @Description:
 * @Author sven
 * @DateTime 2019年12月11日 下午4:24:00
 */
public class SignUtil {
	private static final Logger logger = LoggerFactory.getLogger(SignUtil.class);

	/**
	 * 所有的请求参数都会在这里进行asc加密
	 * 
	 * @Title: verifySign
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年12月11日 下午4:24:06
	 * @param app_secret
	 * @param params
	 * @return
	 */
	public static boolean verifySign(String app_secret, SortedMap<String, Object> params) {
		Object urlSign = params.get("sign");
		logger.info("client Sign : {}", urlSign);
		if (StringUtils.isEmpty(urlSign)) {
			return false;
		}
		// 把参数加密
		String paramsSign = getParamsSign(app_secret, params);
		logger.info("server Sign : {}", paramsSign);
		return !StringUtils.isEmpty(paramsSign) && urlSign.equals(paramsSign);
	}

	/**
	 * 得到签名
	 * 
	 * @Title: getParamsSign
	 * @Description:
	 * @Author sven
	 * @DateTime 2019年12月11日 下午4:24:34
	 * @param app_secret
	 * @param params
	 * @return
	 */
	public static String getParamsSign(String app_secret, SortedMap<String, Object> params) {
		// 要先去掉 client 里的 sign
		params.remove("sign");
		// 加入app_secret
		params.put("app_secret", app_secret);
		List<Object> list =null;
		JSONObject jsonObject =null;
		for (Entry<String, Object> _s : params.entrySet()) {
			if(_s.getValue() instanceof List) {
				if (params.containsKey(_s.getKey())) {
					JSONArray jsonArray = (JSONArray) params.get(_s.getKey());
					list = new ArrayList<Object>();
					SortedMap<String, Object> map = null;
					Boolean flag=false;
					for (int i = 0; i < jsonArray.size(); i++) {
						if(jsonArray.get(i) instanceof JSONObject) {
							jsonObject = jsonArray.getJSONObject(i);
							@SuppressWarnings("unchecked")
							Map<String, Object> allRequestParam = JSONObject.parseObject(jsonObject.toJSONString(), Map.class);
							map = new TreeMap<String, Object>();
							for (@SuppressWarnings("rawtypes") Map.Entry entry : allRequestParam.entrySet()) {
								map.put((String) entry.getKey(), (Object) entry.getValue());
							}
							list.add(map);
							
							flag=true;
						}
					}
					if(flag) {
						params.put(_s.getKey(), list);
					}
				}
			}else {
				if(!RegularUtil.isPrimitiveOrString(_s.getValue())) {
					try {
						JSONObject _obj=JSON.parseObject(_s.getValue().toString());
						Iterator<String> keys = _obj.keySet().iterator();
						SortedMap<String, Object> objmap = new TreeMap<String, Object>();
						while (keys.hasNext()) {
				            String key = keys.next();
				            objmap.put(key, _obj.get(key));
				        }
						params.put(_s.getKey(), objmap);
					}catch (Exception e) {
						logger.error("转换异常:{}",_s.getValue());
					}
					
				}
			}
		}
		String paramsJsonStr = JSONObject.toJSONString(params,SerializerFeature.WriteMapNullValue);
		logger.info("server sign json : {}", paramsJsonStr);
		return DigestUtils.md5DigestAsHex(paramsJsonStr.getBytes()).toUpperCase();
	}
}