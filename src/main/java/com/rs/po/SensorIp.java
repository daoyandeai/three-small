package com.rs.po;
/**
 * 传感器IP端口实体类 
 * @ClassName: SensorIp
 * @Description: 
 * @Author sven
 * @DateTime 2020年1月11日 下午5:13:43
 */
public class SensorIp extends BasePo<SensorIp>{

	/**
	  * @Fields serialVersionUID : 
	  */
	
	private static final long serialVersionUID = 1L;
	/**
	 * 传感器客户端系统编码
	 */
	private String sensor_ip_code;
	/**
	 * 传感器ip
	 */
	private String ip;
	/**
	 * 传感器端口
	 */
	private int port;
	public String getSensor_ip_code() {
		return sensor_ip_code;
	}
	public void setSensor_ip_code(String sensor_ip_code) {
		this.sensor_ip_code = sensor_ip_code;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
}
