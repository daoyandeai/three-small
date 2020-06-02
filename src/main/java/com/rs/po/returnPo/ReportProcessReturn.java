package com.rs.po.returnPo;

import java.io.Serializable;
/**
 * 加工场地卫生设施实体类
 * @ClassName: ReportProcessReturn
 * @Description: 
 * @Author sven
 * @DateTime 2020年4月15日 下午5:20:58
 */
public class ReportProcessReturn implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 加工场地卫生设施系统编码
	 */
	private String report_process_code;          
	/**
	 * 使用水源
	 */
	private String water_source;                 
	/**
	 * 餐具消毒方式
	 */
	private String tablewater_disinfect;         
	/**
	 * 留样设施(1：有、2：无)
	 */
	private Integer reservedsample_state;        
	/**
	 * 有无贮存或使用有毒有害物品(1：无、2：有)
	 */
	private Integer narcotics_state;             
	/**
	 * 餐具保洁柜数量
	 */
	private Integer cleancontainer_count;        
	/**
	 * 冰箱数量
	 */
	private Integer refrigerator_count;          
	/**
	 * 专用消毒柜数量
	 */
	private Integer disinfectioncabinet_count;   
	/**
	 * 食品留样柜数量
	 */
	private Integer reservedsample_count;        
	/**
	 * 垃圾桶数量
	 */
	private Integer garbage_count;               
	/**
	 * 防鼠、防蝇、防尘设施数量
	 */
	private Integer ratproof_count;              
	/**
	 * 专用洗菜、洗肉、洗鱼池数量
	 */
	private Integer washvegetable_count;         
	/**
	 * 报备系统编码
	 */
	private String report_code;
	public String getReport_process_code() {
		return report_process_code;
	}
	public void setReport_process_code(String report_process_code) {
		this.report_process_code = report_process_code;
	}
	public String getWater_source() {
		return water_source;
	}
	public void setWater_source(String water_source) {
		this.water_source = water_source;
	}
	public String getTablewater_disinfect() {
		return tablewater_disinfect;
	}
	public void setTablewater_disinfect(String tablewater_disinfect) {
		this.tablewater_disinfect = tablewater_disinfect;
	}
	public Integer getReservedsample_state() {
		return reservedsample_state;
	}
	public void setReservedsample_state(Integer reservedsample_state) {
		this.reservedsample_state = reservedsample_state;
	}
	public Integer getNarcotics_state() {
		return narcotics_state;
	}
	public void setNarcotics_state(Integer narcotics_state) {
		this.narcotics_state = narcotics_state;
	}
	public Integer getCleancontainer_count() {
		return cleancontainer_count;
	}
	public void setCleancontainer_count(Integer cleancontainer_count) {
		this.cleancontainer_count = cleancontainer_count;
	}
	public Integer getRefrigerator_count() {
		return refrigerator_count;
	}
	public void setRefrigerator_count(Integer refrigerator_count) {
		this.refrigerator_count = refrigerator_count;
	}
	public Integer getDisinfectioncabinet_count() {
		return disinfectioncabinet_count;
	}
	public void setDisinfectioncabinet_count(Integer disinfectioncabinet_count) {
		this.disinfectioncabinet_count = disinfectioncabinet_count;
	}
	public Integer getReservedsample_count() {
		return reservedsample_count;
	}
	public void setReservedsample_count(Integer reservedsample_count) {
		this.reservedsample_count = reservedsample_count;
	}
	public Integer getGarbage_count() {
		return garbage_count;
	}
	public void setGarbage_count(Integer garbage_count) {
		this.garbage_count = garbage_count;
	}
	public Integer getRatproof_count() {
		return ratproof_count;
	}
	public void setRatproof_count(Integer ratproof_count) {
		this.ratproof_count = ratproof_count;
	}
	public Integer getWashvegetable_count() {
		return washvegetable_count;
	}
	public void setWashvegetable_count(Integer washvegetable_count) {
		this.washvegetable_count = washvegetable_count;
	}
	public String getReport_code() {
		return report_code;
	}
	public void setReport_code(String report_code) {
		this.report_code = report_code;
	}                  
}
