package com.rs.util;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.rs.po.TokenParam;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * JWT TOKEN
 * @ClassName: JwtUtil
 * @Description:
 * @Author sven
 * @DateTime 2019年6月10日 下午2:30:19
 */
public class JwtUtil {
	
	@Autowired
	private TokenParam tokenParam;
	
	
	public String getKey() {
		return tokenParam.getLogin_user();
	}
	
	public String getEncryKey() {
		return tokenParam.getEncry_key();
	}
	
	/**
	 * 获取token - json化 map信息
	 * 
	 * @param claimMaps
	 * @param encryKey
	 * @param secondTimeOut
	 * @return
	 */
	public static String getTokenByJson(Map<String, Object> claimMaps, String encryKey, int secondTimeOut) {
		return getToken(claimMaps, true, encryKey, secondTimeOut);
	}

	/**
	 * 获取token
	 * 
	 * @param claimMaps
	 * @param isJsonMpas
	 * @param encryKey
	 * @param secondTimeOut
	 * @return
	 */
	public static String getToken(Map<String, Object> claimMaps, boolean isJsonMaps, String encryKey,
			int secondTimeOut) {

		if (isJsonMaps) {
			claimMaps.forEach((key, val) -> {
				claimMaps.put(key, JSON.toJSONString(val));
			});
		}
		long currentTime = System.currentTimeMillis();
		return Jwts.builder().setId(UUID.randomUUID().toString()).setIssuedAt(new Date(currentTime)) // 签发时间
				.setSubject("system") // 说明
				.setIssuer("sven") // 签发者信息
				.setAudience("custom") // 接收用户
				.compressWith(CompressionCodecs.GZIP) // 数据压缩方式
				.signWith(SignatureAlgorithm.HS256, encryKey) // 加密方式
				.setExpiration(new Date(currentTime + secondTimeOut * 60 * 60 * 24)) // 过期时间戳
				.addClaims(claimMaps) // cla信息
				.compact();
	}

	/**
	 * 获取token中的claims信息
	 * 
	 * @param token
	 * @param encryKey
	 * @return
	 */
	private static Jws<Claims> getJws(String token, String encryKey) {
		return Jwts.parser().setSigningKey(encryKey).parseClaimsJws(token);
	}

	public static String getSignature(String token, String encryKey) {
		try {
			return getJws(token, encryKey).getSignature();
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * 获取token中head信息
	 * 
	 * @param token
	 * @param encryKey
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static JwsHeader getHeader(String token, String encryKey) {
		try {
			return getJws(token, encryKey).getHeader();
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 获取payload body信息
	 * 
	 * @param token
	 * @param encryKey
	 * @return
	 */
	public static Claims getClaimsBody(String token, String encryKey) {
		return getJws(token, encryKey).getBody();
	}

	/**
	 * 获取body某个值
	 * 
	 * @param token
	 * @param encryKey
	 * @param key
	 * @return
	 */
	public static Object getVal(String token, String encryKey, String key) {
		return getJws(token, encryKey).getBody().get(key);
	}

	/**
	 * 获取body某个值，json字符转实体
	 * 
	 * @param token
	 * @param encryKey
	 * @param key
	 * @param tClass
	 * @param <T>
	 * @return
	 */
	public static <T> T getValByT(String token, String encryKey, String key, Class<T> tClass) {
		try {
			String strJson = getVal(token, encryKey, key).toString();
			return JSON.parseObject(strJson, tClass);
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 是否过期
	 * 
	 * @param token
	 * @param encryKey
	 * @return
	 */
	public static boolean isExpiration(String token, String encryKey) {
		try {
			return getClaimsBody(token, encryKey).getExpiration().before(new Date());
		} catch (ExpiredJwtException ex) {
			return true;
		}
	}

	public static String getSubject(String token, String encryKey) {
		try {
			return getClaimsBody(token, encryKey).getSubject();
		} catch (Exception ex) {
			return "";
		}
	}
}
