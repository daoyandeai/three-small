package com.rs.util;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.baidu.aip.ocr.AipOcr;
/**
 * OCR图像识别辅助类
 * @ClassName: BaiDuOCRUtil
 * @Description: 
 * @Author sven
 * @DateTime 2020年6月1日 上午9:45:34
 */
@Configuration
public class BaiDuOCRUtil {

    public static final String APP_ID = "11376224";
    public static final String API_KEY = "9VEkjmCCcuOdoxUHnhZcrdpW";
    public static final String SECRET_KEY = "F0ETLIcrZFij6VssPjS177NWklKkEUHj";

	private static Logger logger = LoggerFactory.getLogger(BaiDuOCRUtil.class);
	
	private static AipOcr apiOcr;
	 
	static {
		apiOcr = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
		 // 可选：设置网络连接参数
		apiOcr.setConnectionTimeoutInMillis(2000);
		apiOcr.setSocketTimeoutInMillis(60000);
	}
	
	/**
	 * 解析营业执照
	 * @Title: parseLicence
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年6月1日 上午10:19:02
	 * @param file_path
	 * @return
	 * @throws JSONException
	 */
	public static Object parseLicence(String file_path) throws JSONException {
        // 参数为本地图片路径
		//file_path = "e:/营业执照.png";
		
		JSONObject jsonObject = apiOcr.basicGeneral(file_path, new HashMap<String, String>());
        JSONArray words_result = (org.json.JSONArray) jsonObject.get("words_result");
        
        logger.info("营业执照百度返回数据：{}",jsonObject);
        
        Map<String,Object> result = new HashMap<>();
        if (words_result.length() > 0) {
            for (int key = 0; key < words_result.length(); key++) {
                JSONObject object = (JSONObject) words_result.get(key);
                String value = object.getString("words");
                if(value.startsWith("法定代表人")){
    	             result.put("operator",value.substring(5,value.length()));
                }
                if(value.trim().startsWith("名称")){
    	            result.put("company_name",value.substring(2,value.length()));
                }else if(value.trim().startsWith("称")){
    	           result.put("company_name",value.substring(1,value.length()));
                }
                if(value.startsWith("统一社会信用代码")){
                    result.put("credit_code",value.substring(8,value.length()));
                }
                if(value.startsWith("证照编号")){
                    result.put("record_code",value.substring(4,value.length()));
                }
                if(value.startsWith("成立日期")){
                    result.put("record_time",replaceYMD(value.substring(4,value.length())));
                }
                if(value.startsWith("营业期限")){
                    result.put("unuseful_time",replaceYMD(value.substring(16,value.length())));
                }
            }
        }
        logger.info("营业执照系统封装数据：{}",result);
        return result;
    }
	
	
	
	/**
	 * 解析身份证
	 * @Title: parseIdCard
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年6月1日 下午2:58:38
	 * @param file_path   本地附件
	 * @param id_card_side front：身份证含照片的一面；back：身份证带国徽的一面
	 * @return
	 * @throws JSONException
	 */
	public static Object parseIdCard(String file_path,String id_card_side) throws JSONException {
		
		//file_path = "e:/front.jpg";
		//file_path = "e:/back.jpg";
		
		HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "true");
        options.put("detect_risk", "false");
        
		JSONObject jsonObject = apiOcr.basicGeneral(file_path, new HashMap<String, String>());
        JSONArray words_result = (org.json.JSONArray) jsonObject.get("words_result");
        
        logger.info("身份证百度返回数据：{}",jsonObject);
        Map<String,Object> result = new HashMap<>();
        if (words_result.length() > 0) {
            for (int key = 0; key < words_result.length(); key++) {
                JSONObject object = (JSONObject) words_result.get(key);
                String value = object.getString("words");
                if("front".equals(id_card_side)) {
                	 if(value.startsWith("姓名")){
        	             result.put("user_name",value.substring(2,value.length()));
                    }
                    if(value.startsWith("性别")){
       	             result.put("user_sex",value.substring(2,3));
                    }
                    if(value.indexOf("民族") > -1){
          	             result.put("user_nation",value.substring(5,value.length()));
                    }
                    if(value.startsWith("公民身份号码")){
          	             result.put("user_idcard",value.substring(6,value.length()));
                    }
                    if(value.startsWith("出生")){
         	             result.put("user_birthday",replaceYMD(value.substring(2,value.length())));
                   }
                }else {
                	
                }
            }
        }
        logger.info("身份证系统封装数据：{}",result);
        return result;
    }
	
	
	/**
	 * 解析菜品
	 * @Title: parseDish
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年6月1日 下午3:33:54
	 * @param file_path
	 * @return
	 * @throws JSONException
	 */
	public static Object parseDish(String file_path) throws JSONException {
        // 参数为本地图片路径
		//file_path = "e:/黑椒汁.png"; 
		
		JSONObject jsonObject = apiOcr.basicGeneral(file_path, new HashMap<String, String>());
        JSONArray words_result = (org.json.JSONArray) jsonObject.get("words_result");
        
        logger.info("菜品百度返回数据：{}",jsonObject);
        
        Map<String,Object> result = new HashMap<>();
        if (words_result.length() > 0) {
            for (int key = 0; key < words_result.length(); key++) {
                JSONObject object = (JSONObject) words_result.get(key);
                String value = object.getString("words");
                if(key == 0){
    	             result.put("product_name",value);
                }
            }
        }
        logger.info("菜品系统封装数据：{}",result);
        return result;
    }
	
	private static String replaceYMD(String value) {
		return value.replace("年", "-").replace("月", "-").replace("日", "");
	}
}	
