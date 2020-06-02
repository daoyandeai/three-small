package com.rs.po;

import com.rs.po.returnPo.SubjectDetailReturn;

import java.util.List;

public class SubjectDetail extends BasePo<SubjectDetail> {
    /**
	  * @Fields serialVersionUID : 
	  */
	
	private static final long serialVersionUID = 1L;
	/*
    * 详细经营信息编码
    * */
    private String subjectdetail_code;
    /*
     *分组名称（1：流通、2：生产、3：餐饮）
     * */
    private String subjectdetail_group;
    /*
     *经营名称
     * */
    private String subjectdetail_name;
    private List<SubjectDetail> subjectDetailList;
    private List<SubjectDetailReturn> subjectDetailReturnList;
    /*
    * 状态（1：启用、2：禁用）
    * */
    private Integer state;

    public List<SubjectDetailReturn> getSubjectDetailReturnList() {
        return subjectDetailReturnList;
    }

    public void setSubjectDetailReturnList(List<SubjectDetailReturn> subjectDetailReturnList) {
        this.subjectDetailReturnList = subjectDetailReturnList;
    }

    public List<SubjectDetail> getSubjectDetailList() {
        return subjectDetailList;
    }

    public void setSubjectDetailList(List<SubjectDetail> subjectDetailList) {
        this.subjectDetailList = subjectDetailList;
    }

    public String getSubjectdetail_code() {
        return subjectdetail_code;
    }

    public void setSubjectdetail_code(String subjectdetail_code) {
        this.subjectdetail_code = subjectdetail_code;
    }

    public String getSubjectdetail_group() {
        return subjectdetail_group;
    }

    public void setSubjectdetail_group(String subjectdetail_group) {
        this.subjectdetail_group = subjectdetail_group;
    }

    public String getSubjectdetail_name() {
        return subjectdetail_name;
    }

    public void setSubjectdetail_name(String subjectdetail_name) {
        this.subjectdetail_name = subjectdetail_name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

}