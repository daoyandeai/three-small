package com.rs.po;


/**
 * 接口返回消息枚举
 *
 * @ClassName: CodeMsgEnum
 * @Description:
 * @Author sven
 * @DateTime 2019年7月26日 上午10:18:22
 */
public enum CodeMsgEnum {

    SUCCESS(200, "业务处理成功"),
    TOKEN_UNVALID(205, "TOKEN认证失败,请重新获取TOKEN"),
    PASSWORD_UNVALID(206,"登录密码无效"),
    TIMESTAMP_UNVALID(207, "请求时间戳为空或已过期"),
	APP_KEY_UNVALID(208, "app_key出错"),
    APP_SECRET_UNVALID(209, "app_secret出错"),
    SIGN_UNVALID(210, "签名无效,参数校验出错"),
    TRAIN_EXAM_TOPIC_SIZE_UNVALID(211, "培训试题题目长度无效"),
    TOKEN_DISCONNECT(228, "当前用户已在其他设备登录,请重新获取TOKEN"),
    USER_CODE_UNVALID(229, "用户编码无效"),
    
    ERROR(300, "业务处理失败"),
    YEAR_UNVALID(310,"年份格式无效"),
    SPECIAL_CHAR(311,"参数包含特殊字符"),
    NAME_EXIST(312,"该区域下食药所已存在"),
    SENSOR_NUMBER_EXIST(313,"传感器编号已存在"),
    SENSOR_THRESHOLD_EXIST(314,"传感器预警项已存在"),
    COMPANY_TYPE_EXIST(315,"食品监管分类已存在"),
    COMPANY_TAG_EXIST(316,"主体类型已存在"),
    PAGE_CONFIG_NAME_EXIST(317,"页面参数模板名称已存在"),
    PAGE_ATTR_EN_EXIST(318,"该模块下英文属性已存在"),
    PAGE_CONFIG_REGION_NAME_EXIST(319,"该区域下模板名称已存在"),
    
    PROVINCE_UNVALID(320, "请选择省"),
    CITY_UNVALID(321, "请选择市"),
    AREA_UNVALID(322, "请选择区"),
    TOWN_UNVALID(323, "请选择镇"),
    VILL_UNVALID(324, "请选择村（社区）"),
    DEPARTMENT_NAME_EMPTY(325, "请填写监管部门名称"),
    CALC_PERIOD_UNVALID(326, "周期为正整数"),
    MESS_EVENT_UNVALID(327, "消息类型值有误"),
    COMPANY_AREA_NAME_EXIST(328, "企业区域已存在"),
    DEVICE_BIND_EXIST(329, "该区域下已绑定监控设备"),
    INTEGRITY_LVL_UNVALID(330, "请选择评分"),
    REMARK_UNVALID(331, "请输入备注"),
    ADVICE_RATE_UNVALID(332, "请选择评定等级"),
    ADVICE_TYPE_UNVALID(333, "请选择评定类型"),
    LONGITUDE_UNVALID(334, "经度无效"),
    LATITUDE_UNVALID(335, "纬度无效"),
    CATEGORY_NAME_EXIST(336,"分类名称已存在"),
    SENSOR_NUMBER_UNVALID(337,"传感器编号无效"),
    COMPANY_CODE_UNVALID(338,"企业编码无效"),
    COMPANY_AREA_CODE_UNVALID(339,"设备区域编码无效"),
    SENSOR_CODE_UNVALID(340,"传感器编码无效"),
    DEVICE_NUMBER_EXIST(341, "摄像头编号已存在"),
    SENSOR_BIND_EXIST(342, "该区域下已绑定传感器"),
    
    PARAMETER_ERROR(400, "参数错误"),
    USER_NOT_IN_DEPART(401, "当前用户未在监管部门,请先加入后查看对应网格员"),
    COMPANY_UNDEFINED(402,"三小备案不存在"),
    USER_UNDEFINED(455,"用户不存在"),
    USER_EXIST(456,"用户已存在"),
    LOGINOUT_UNVALID(466, "用户注销失败,请核对登录信息"),
    LOGIN_UNVALID(499, "用户登录失败,请核对登录信息"),
    LOGIN_VALID(488,"用户登录成功"),
    LOGINOUT_VALID(477,"用户注销成功"),
    FILE_PATH_UNVALID(478,"orc识别附件路径无效"),
    DISCERN_TYPE_UNVALID(479,"orc识别附件类型无效"),
    
    EXCEPTION(500, "服务器异常"),
    
    
    /**************************群宴*******************************/
    USER_NAME_CONDUCT_UNVALID(600,"举办者名称不正确"),
    USER_MOBILEPHONE_CONDUCT_UNVALID(601,"举办者电话不正确"),
    PROVINCE_CONDUCT_UNVALID(602,"举办地【省】不正确"),
    CITY_CONDUCT_UNVALID(603,"举办地【市】不正确"),
    AREA_CONDUCT_UNVALID(604,"举办地【区县】不正确"),
    TOWN_CONDUCT_UNVALID(605,"举办地【乡镇】不正确"),
    VILL_CONDUCT_UNVALID(606,"举办地【 村社区】不正确"),
    ADDRESS_CONDUCT_UNVALID(607,"举办地【详细地址】不正确"),
    BANQUET_TIME_UNVALID(608,"办宴时间不正确"),
    BANQUET_DAY_UNVALID(609,"办宴天数不正确"),
    BANQUET_TYPE_UNVALID(610,"办宴类型不正确"),	
    BANQUET_PEOPLE_UNVALID(611,"就餐人数不正确"),	
    USER_CODE_MAINCHEF_UNVALID(612,"乡厨编码不正确"),	
    USER_NAME_MAINCHEF_UNVALID(613,"乡厨姓名不正确"),	
    USER_MOBILEPHONE_MAINCHEF_UNVALID(614,"乡厨电话不正确"),	
    REPORT_CODE_UNVALID(615,"报备编码无效"),	
    USER_NAME_SUBCHEF_UNVALID(616,"帮厨姓名不正确"),	
    USER_HEALTH_LOGO_SUBCHEF_UNVALID(617,"帮厨健康证无效"),	
    REPORT_STATE_UNVALID(618,"报备状态无效"),	
	
    KEY_PARAM_EXIST(700,"关键参数已存在"),
	KEY_PARAM_ISEMPTY(701, "关键参数为空|有误"),
	API_LIMIT(702, "API接口访问频率过高");
    /**
     * 返回代码
     */
    private int code;
    /**
     * 返回值
     */
    private String msg;

    private CodeMsgEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return this.code + "@" + this.msg;
    }
}
