package com.rs.po;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
//@PropertySource(value = { "classpath:config/token.properties" })
public class TokenParam implements Serializable{
	
	/**
	  * @Fields serialVersionUID : 
	  */
	
	private static final long serialVersionUID = 1L;

	@Value("${token.login_user}")
	private String login_user;
	
	@Value("${token.encry_key}")
	private String encry_key;
	
	@Value("${token.second_timeout}")
	private Integer second_timeout;
	
	@Value("${baidu.baidu_url}")
	private String baidu_url;
	
	@Value("${baidu.baidu_uri}")
	private String baidu_uri;
	
	@Value("${baidu.baidu_ak}")
	private String baidu_ak;
	
	@Value("${system.token_enable}")
	private boolean token_enable;
	
	@Value("${sign.app_key}")
	private String app_key;
	
	@Value("${sign.app_secret}")
	private String app_secret;
	
	@Value("${bos.bos_url}")
	private String bos_url;
	
	@Value("${bos.bos_video_uri}")
	private String bos_video_uri;
	
	@Value("${bos.bos_capture_pic}")
	private String bos_capture_pic;
	
	@Value("${bos.bos_review_pics}")
	private String bos_review_pics;
	
	@Value("${fileupload.file_url}")
	private String file_url;
	
	@Value("${fileupload.file_ip}")
	private String file_ip;
	
	@Value("${fileupload.file_dir}")
	private String file_dir;
	
	@Value("${navigation.qyurl}")
	private String qyurl;
	
	@Value("${is_test}")
	private String is_test;
	
	@Value("${rest_server}")
	private String rest_server;
	
	@Value("${report.down_time}")
	private Integer down_time;
	
	@Value("${wx.appid}")
	private String appid;
	
	@Value("${wx.secret}")
	private String secret;

	@Value("${mq.mq_prefix}")
	private String mq_prefix;
	
	@Value("${mq.sensor_queue}")
	private String sensor_queue;
	
	@Value("${mq.sensor_routing_key}")
	private String sensor_routing_key;
	
	@Value("${mq.sensor_exchange}")
	private String sensor_exchange;
	
	@Value("${mq.sensor_log_queue}")
	private String sensor_log_queue;
	
	@Value("${mq.sensor_log_routing_key}")
	private String sensor_log_routing_key;
	
	@Value("${mq.sensor_log_exchange}")
	private String sensor_log_exchange;
	
	@Value("${baidu.app_id}")
	private String app_id;
	
	@Value("${baidu.api_key}")
	private String api_key;
	
	@Value("${baidu.secret_key}")
	private String secret_key;
	
	public String getLogin_user() {
		return login_user;
	}

	public void setLogin_user(String login_user) {
		this.login_user = login_user;
	}

	public String getEncry_key() {
		return encry_key;
	}

	public void setEncry_key(String encry_key) {
		this.encry_key = encry_key;
	}

	public Integer getSecond_timeout() {
		return second_timeout;
	}

	public void setSecond_timeout(Integer second_timeout) {
		this.second_timeout = second_timeout;
	}

	public String getBaidu_url() {
		return baidu_url;
	}

	public void setBaidu_url(String baidu_url) {
		this.baidu_url = baidu_url;
	}

	public String getBaidu_uri() {
		return baidu_uri;
	}

	public void setBaidu_uri(String baidu_uri) {
		this.baidu_uri = baidu_uri;
	}

	public String getBaidu_ak() {
		return baidu_ak;
	}

	public void setBaidu_ak(String baidu_ak) {
		this.baidu_ak = baidu_ak;
	}

	public boolean isToken_enable() {
		return token_enable;
	}

	public void setToken_enable(boolean token_enable) {
		this.token_enable = token_enable;
	}

	public String getApp_key() {
		return app_key;
	}

	public void setApp_key(String app_key) {
		this.app_key = app_key;
	}

	public String getApp_secret() {
		return app_secret;
	}

	public void setApp_secret(String app_secret) {
		this.app_secret = app_secret;
	}

	public String getBos_url() {
		return bos_url;
	}

	public void setBos_url(String bos_url) {
		this.bos_url = bos_url;
	}

	public String getBos_video_uri() {
		return bos_video_uri;
	}

	public void setBos_video_uri(String bos_video_uri) {
		this.bos_video_uri = bos_video_uri;
	}

	public String getFile_url() {
		return file_url;
	}

	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}

	public String getFile_ip() {
		return file_ip;
	}

	public void setFile_ip(String file_ip) {
		this.file_ip = file_ip;
	}

	public String getFile_dir() {
		return file_dir;
	}

	public void setFile_dir(String file_dir) {
		this.file_dir = file_dir;
	}

	public String getBos_capture_pic() {
		return bos_capture_pic;
	}

	public void setBos_capture_pic(String bos_capture_pic) {
		this.bos_capture_pic = bos_capture_pic;
	}

	public String getBos_review_pics() {
		return bos_review_pics;
	}

	public void setBos_review_pics(String bos_review_pics) {
		this.bos_review_pics = bos_review_pics;
	}

	public String getQyurl() {
		return qyurl;
	}

	public void setQyurl(String qyurl) {
		this.qyurl = qyurl;
	}

	public String getIs_test() {
		return is_test;
	}

	public void setIs_test(String is_test) {
		this.is_test = is_test;
	}

	public String getRest_server() {
		return rest_server;
	}

	public void setRest_server(String rest_server) {
		this.rest_server = rest_server;
	}

	public Integer getDown_time() {
		return down_time;
	}

	public void setDown_time(Integer down_time) {
		this.down_time = down_time;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getMq_prefix() {
		return mq_prefix;
	}

	public void setMq_prefix(String mq_prefix) {
		this.mq_prefix = mq_prefix;
	}

	public String getSensor_queue() {
		return sensor_queue;
	}

	public void setSensor_queue(String sensor_queue) {
		this.sensor_queue = sensor_queue;
	}

	public String getSensor_routing_key() {
		return sensor_routing_key;
	}

	public void setSensor_routing_key(String sensor_routing_key) {
		this.sensor_routing_key = sensor_routing_key;
	}

	public String getSensor_exchange() {
		return sensor_exchange;
	}

	public void setSensor_exchange(String sensor_exchange) {
		this.sensor_exchange = sensor_exchange;
	}

	public String getSensor_log_queue() {
		return sensor_log_queue;
	}

	public void setSensor_log_queue(String sensor_log_queue) {
		this.sensor_log_queue = sensor_log_queue;
	}

	public String getSensor_log_routing_key() {
		return sensor_log_routing_key;
	}

	public void setSensor_log_routing_key(String sensor_log_routing_key) {
		this.sensor_log_routing_key = sensor_log_routing_key;
	}

	public String getSensor_log_exchange() {
		return sensor_log_exchange;
	}

	public void setSensor_log_exchange(String sensor_log_exchange) {
		this.sensor_log_exchange = sensor_log_exchange;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getApi_key() {
		return api_key;
	}

	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}

	public String getSecret_key() {
		return secret_key;
	}

	public void setSecret_key(String secret_key) {
		this.secret_key = secret_key;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
