package com.rs.controller;

import com.alibaba.fastjson.JSONObject;
import com.rs.po.CodeMsgEnum;

/**
 * 基础控制层,用于设定返回格式
 * @ClassName: BaseController
 * @Description:
 * @Author sven
 * @DateTime 2019年6月5日 下午4:36:01
 */
public class BaseController {
	
	protected CodeMsgEnum CODEMSG = CodeMsgEnum.SUCCESS;
	protected static final CodeMsgEnum SUCCESS = CodeMsgEnum.SUCCESS;
	protected static final CodeMsgEnum USER_UNDEFINED = CodeMsgEnum.USER_UNDEFINED;
	protected static final CodeMsgEnum USER_EXIST = CodeMsgEnum.USER_EXIST;
	protected static final CodeMsgEnum ERROR = CodeMsgEnum.ERROR;
	protected static final CodeMsgEnum EXCEPTION = CodeMsgEnum.EXCEPTION;
	protected static final CodeMsgEnum PARAMETER_ERROR = CodeMsgEnum.PARAMETER_ERROR;
	protected static final CodeMsgEnum KEY_PARAM_EXIST = CodeMsgEnum.KEY_PARAM_EXIST;
	protected static final CodeMsgEnum TOKEN_UNVALID = CodeMsgEnum.TOKEN_UNVALID;
	protected static final CodeMsgEnum LOGIN_UNVALID = CodeMsgEnum.LOGIN_UNVALID;
	protected static final CodeMsgEnum LOGIN_VALID = CodeMsgEnum.LOGIN_VALID;
	protected static final CodeMsgEnum LOGINOUT_UNVALID = CodeMsgEnum.LOGINOUT_UNVALID;
	protected static final CodeMsgEnum LOGINOUT_VALID = CodeMsgEnum.LOGINOUT_VALID;
	protected static final CodeMsgEnum USER_CODE_UNVALID = CodeMsgEnum.USER_CODE_UNVALID;
	
	/**********验签区域*************/
	protected static final CodeMsgEnum APP_KEY_UNVALID = CodeMsgEnum.APP_KEY_UNVALID;
	protected static final CodeMsgEnum APP_SECRET_UNVALID = CodeMsgEnum.APP_SECRET_UNVALID;
	protected static final CodeMsgEnum SIGN_UNVALID = CodeMsgEnum.SIGN_UNVALID;
	protected static final CodeMsgEnum TIMESTAMP_UNVALID = CodeMsgEnum.TIMESTAMP_UNVALID;
	
	/**********普通验证区域*************/
	protected static final CodeMsgEnum TRAIN_EXAM_TOPIC_SIZE_UNVALID = CodeMsgEnum.TRAIN_EXAM_TOPIC_SIZE_UNVALID;
	protected static final CodeMsgEnum NAME_EXIST = CodeMsgEnum.NAME_EXIST;
	protected static final CodeMsgEnum SENSOR_NUMBER_EXIST = CodeMsgEnum.SENSOR_NUMBER_EXIST;
	protected static final CodeMsgEnum SENSOR_THRESHOLD_EXIST = CodeMsgEnum.SENSOR_THRESHOLD_EXIST;
	protected static final CodeMsgEnum COMPANY_TYPE_EXIST = CodeMsgEnum.COMPANY_TYPE_EXIST;
	protected static final CodeMsgEnum COMPANY_TAG_EXIST = CodeMsgEnum.COMPANY_TAG_EXIST;
	protected static final CodeMsgEnum USER_NOT_IN_DEPART = CodeMsgEnum.USER_NOT_IN_DEPART;
	protected static final CodeMsgEnum PAGE_CONFIG_NAME_EXIST = CodeMsgEnum.PAGE_CONFIG_NAME_EXIST;
	protected static final CodeMsgEnum PAGE_ATTR_EN_EXIST = CodeMsgEnum.PAGE_ATTR_EN_EXIST;
	protected static final CodeMsgEnum PAGE_CONFIG_REGION_NAME_EXIST = CodeMsgEnum.PAGE_CONFIG_REGION_NAME_EXIST;
	protected static final CodeMsgEnum PROVINCE_UNVALID = CodeMsgEnum.PROVINCE_UNVALID;
	protected static final CodeMsgEnum CITY_UNVALID = CodeMsgEnum.CITY_UNVALID;
	protected static final CodeMsgEnum AREA_UNVALID = CodeMsgEnum.AREA_UNVALID;
	protected static final CodeMsgEnum TOWN_UNVALID = CodeMsgEnum.TOWN_UNVALID;
	protected static final CodeMsgEnum VILL_UNVALID = CodeMsgEnum.VILL_UNVALID;
	protected static final CodeMsgEnum DEPARTMENT_NAME_EMPTY = CodeMsgEnum.DEPARTMENT_NAME_EMPTY;
	protected static final CodeMsgEnum CALC_PERIOD_UNVALID = CodeMsgEnum.CALC_PERIOD_UNVALID;
	protected static final CodeMsgEnum MESS_EVENT_UNVALID = CodeMsgEnum.MESS_EVENT_UNVALID;
	protected static final CodeMsgEnum COMPANY_AREA_NAME_EXIST = CodeMsgEnum.COMPANY_AREA_NAME_EXIST;
	protected static final CodeMsgEnum DEVICE_BIND_EXIST = CodeMsgEnum.DEVICE_BIND_EXIST;
	protected static final CodeMsgEnum INTEGRITY_LVL_UNVALID = CodeMsgEnum.INTEGRITY_LVL_UNVALID;
	protected static final CodeMsgEnum REMARK_UNVALID = CodeMsgEnum.REMARK_UNVALID;
	protected static final CodeMsgEnum ADVICE_RATE_UNVALID = CodeMsgEnum.ADVICE_RATE_UNVALID;
	protected static final CodeMsgEnum ADVICE_TYPE_UNVALID = CodeMsgEnum.ADVICE_TYPE_UNVALID;
	protected static final CodeMsgEnum LONGITUDE_UNVALID = CodeMsgEnum.LONGITUDE_UNVALID;
	protected static final CodeMsgEnum LATITUDE_UNVALID = CodeMsgEnum.LATITUDE_UNVALID;
	protected static final CodeMsgEnum COMPANY_UNDEFINED = CodeMsgEnum.COMPANY_UNDEFINED;
	protected static final CodeMsgEnum CATEGORY_NAME_EXIST = CodeMsgEnum.CATEGORY_NAME_EXIST;
	
	protected static final CodeMsgEnum USER_NAME_CONDUCT_UNVALID = CodeMsgEnum.USER_NAME_CONDUCT_UNVALID;
	protected static final CodeMsgEnum USER_MOBILEPHONE_CONDUCT_UNVALID = CodeMsgEnum.USER_MOBILEPHONE_CONDUCT_UNVALID;
	protected static final CodeMsgEnum PROVINCE_CONDUCT_UNVALID = CodeMsgEnum.PROVINCE_CONDUCT_UNVALID;
	protected static final CodeMsgEnum CITY_CONDUCT_UNVALID = CodeMsgEnum.CITY_CONDUCT_UNVALID;
	protected static final CodeMsgEnum AREA_CONDUCT_UNVALID = CodeMsgEnum.AREA_CONDUCT_UNVALID;
	protected static final CodeMsgEnum TOWN_CONDUCT_UNVALID = CodeMsgEnum.TOWN_CONDUCT_UNVALID;
	protected static final CodeMsgEnum VILL_CONDUCT_UNVALID = CodeMsgEnum.VILL_CONDUCT_UNVALID;
	protected static final CodeMsgEnum ADDRESS_CONDUCT_UNVALID = CodeMsgEnum.ADDRESS_CONDUCT_UNVALID;
	protected static final CodeMsgEnum BANQUET_TIME_UNVALID = CodeMsgEnum.BANQUET_TIME_UNVALID;
	protected static final CodeMsgEnum BANQUET_DAY_UNVALID = CodeMsgEnum.BANQUET_DAY_UNVALID;
	protected static final CodeMsgEnum BANQUET_TYPE_UNVALID = CodeMsgEnum.BANQUET_TYPE_UNVALID;
	protected static final CodeMsgEnum BANQUET_PEOPLE_UNVALID = CodeMsgEnum.BANQUET_PEOPLE_UNVALID;
	protected static final CodeMsgEnum USER_CODE_MAINCHEF_UNVALID = CodeMsgEnum.USER_CODE_MAINCHEF_UNVALID;
	protected static final CodeMsgEnum USER_NAME_MAINCHEF_UNVALID = CodeMsgEnum.USER_NAME_MAINCHEF_UNVALID;
	protected static final CodeMsgEnum USER_MOBILEPHONE_MAINCHEF_UNVALID = CodeMsgEnum.USER_MOBILEPHONE_MAINCHEF_UNVALID;
	protected static final CodeMsgEnum REPORT_CODE_UNVALID = CodeMsgEnum.REPORT_CODE_UNVALID;
	protected static final CodeMsgEnum USER_NAME_SUBCHEF_UNVALID = CodeMsgEnum.USER_NAME_SUBCHEF_UNVALID;
	protected static final CodeMsgEnum USER_HEALTH_LOGO_SUBCHEF_UNVALID = CodeMsgEnum.USER_HEALTH_LOGO_SUBCHEF_UNVALID;
	protected static final CodeMsgEnum REPORT_STATE_UNVALID = CodeMsgEnum.REPORT_STATE_UNVALID;
	protected static final CodeMsgEnum COMPANY_CODE_UNVALID = CodeMsgEnum.COMPANY_CODE_UNVALID;
	protected static final CodeMsgEnum COMPANY_AREA_CODE_UNVALID = CodeMsgEnum.COMPANY_AREA_CODE_UNVALID;
	protected static final CodeMsgEnum SENSOR_CODE_UNVALID = CodeMsgEnum.SENSOR_CODE_UNVALID;
	protected static final CodeMsgEnum SENSOR_NUMBER_UNVALID = CodeMsgEnum.SENSOR_NUMBER_UNVALID;
	protected static final CodeMsgEnum DEVICE_NUMBER_EXIST = CodeMsgEnum.DEVICE_NUMBER_EXIST;
	protected static final CodeMsgEnum SENSOR_BIND_EXIST = CodeMsgEnum.SENSOR_BIND_EXIST;
	protected static final CodeMsgEnum FILE_PATH_UNVALID = CodeMsgEnum.FILE_PATH_UNVALID;
	protected static final CodeMsgEnum DISCERN_TYPE_UNVALID = CodeMsgEnum.FILE_PATH_UNVALID;
	
	
	/**********正则区域*************/
	protected static final CodeMsgEnum YEAR_UNVALID = CodeMsgEnum.YEAR_UNVALID;
	protected static final CodeMsgEnum SPECIAL_CHAR = CodeMsgEnum.SPECIAL_CHAR;
	
	/**
	 * 返回值
	 * 
	 * @param data
	 *            数据
	 * @return object
	 */
	public Object finish(CodeMsgEnum codeMsgType, Object data) {
		JSONObject result = new JSONObject();
		result.put("code", codeMsgType.getCode());
		result.put("msg", codeMsgType.getMsg());
		result.put("data", data);
		return result;
	}
}
