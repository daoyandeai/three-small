package com.rs.util.ucpass;
import java.io.IOException;

import com.alibaba.fastjson.JSONObject;
import com.rs.util.ucpass.restDemo.HttpClientUtil;
/**
 * 
 * @ClassName: RestTest
 * @Description: 
 * @Author tangsh
 * @DateTime 2020年2月11日 下午4:01:16
 */
public class RestTest {
	
	private static String SID = "7caf612988673309c92018e914859fdc";
	private static String TOKEN = "20a79012139ce32871edef6ff88b3193";
	private static String APPID = "2aaca5bf66274bb19cad03568cdc282d";
	private static String UID = "";
	
	
	public static void newSendSms(String rest_server,String is_test,String sid, String token, String appid, String templateid, String param, String mobile, String uid){
		try {
			StringBuffer sb = new StringBuffer("https://");
			sb.append(rest_server).append("/ol/sms");
			
			String url = sb.append("/sendsms").toString();
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("sid", sid);
			jsonObject.put("token", token);
			jsonObject.put("appid", appid);
			jsonObject.put("templateid", templateid);
			jsonObject.put("param", param);
			jsonObject.put("mobile", mobile);
			jsonObject.put("uid", uid);
			
			String body = jsonObject.toJSONString();
			
			System.out.println("body = " + body);
			
			String result=HttpClientUtil.postJson(is_test,url, body, null);
			System.out.println("Response content is: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送短信
	 * @param templateid 模板id
	 * @param param      短信内容
	 * @param mobile     手机号
	 */
	public static void sms(String rest_server,String is_test,String templateid,String param,String mobile) {
		newSendSms(rest_server,is_test,SID, TOKEN, APPID, templateid, param, mobile, UID);
	}
	
	
	
	/**
	 * 测试说明  启动main方法后，请在控制台输入数字(数字对应 相应的调用方法)，回车键结束
	 * 参数名称含义，请参考rest api 文档
	 * @throws IOException 
	 * @method main
	 */
	public static void main(String[] args) throws IOException{
		
	}
}
