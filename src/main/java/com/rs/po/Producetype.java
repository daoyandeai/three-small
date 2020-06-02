package com.rs.po;

public class Producetype {
    /**
    * 生产类别编码
    * */
    private String producetype_code;
    /**
     * 生产类别名称
     * */
    private String producetype_name;
    /**
     * 状态（1：启用、2：禁用）
     * */
    private Integer state;


    public String getProducetype_code() {
        return producetype_code;
    }

    public void setProducetype_code(String producetype_code) {
        this.producetype_code = producetype_code;
    }

    public String getProducetype_name() {
        return producetype_name;
    }

    public void setProducetype_name(String producetype_name) {
        this.producetype_name = producetype_name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

}