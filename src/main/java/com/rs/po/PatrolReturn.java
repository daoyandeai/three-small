package com.rs.po;

import java.io.Serializable;

public class PatrolReturn extends BasePo<PatrolReturn> implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     *巡查系统编码
     * */
    private String patrol_code;
    /**
     *巡查人
     * */
    private String patrol_user_name;
    /**
     *巡查结果
     * */
    private String patrol_result;
    /**
     *巡查时间
     * */
    private String patrol_time;
    /**
     *处理方式
     * */
    private String dispose_name;
    /**
     *处罚方式
     * */
    private String punish_name;
    /**
     *备注
     * */
    private String note;
    /**
     *小三档案编码
     * */
    private String company_code;
    /**
     *用户编码
     * */
    private String user_code;
    /**
     *风险评估（“危险”，“警告”，“正常”，“优秀”）
     * */
    private String risk_assessment;
    /**
     *添加时间
     * */
    private String add_time;

    public String getPatrol_code() {
        return patrol_code;
    }

    public String getDispose_name() {
        return dispose_name;
    }

    public void setDispose_name(String dispose_name) {
        this.dispose_name = dispose_name;
    }

    public String getRisk_assessment() {
        return risk_assessment;
    }

    public void setRisk_assessment(String risk_assessment) {
        this.risk_assessment = risk_assessment;
    }

    public void setPatrol_code(String patrol_code) {
        this.patrol_code = patrol_code;
    }

    public String getPatrol_user_name() {
        return patrol_user_name;
    }

    public void setPatrol_user_name(String patrol_user_name) {
        this.patrol_user_name = patrol_user_name;
    }

    public String getPatrol_result() {
        return patrol_result;
    }

    public void setPatrol_result(String patrol_result) {
        this.patrol_result = patrol_result;
    }

    public String getPatrol_time() {
        return patrol_time;
    }

    public void setPatrol_time(String patrol_time) {
        this.patrol_time = patrol_time;
    }

    public String getPunish_name() {
        return punish_name;
    }

    public void setPunish_name(String punish_name) {
        this.punish_name = punish_name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    @Override
    public String getAdd_time() {
        return add_time;
    }

    @Override
    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }
}