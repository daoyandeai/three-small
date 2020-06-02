package com.rs.po.returnPo;

import java.util.List;

public class SubjectDetailReturn{
    /**
    * 详细经营信息编码
    * */
    private String subjectdetail_code;
    /**
     *分组名称（1：流通、2：生产、3：餐饮）
     * */
    private String subjectdetail_group;
    /**
     *经营名称
     * */
    private String subjectdetail_name;
    private List<SubjectDetailReturn> pager_list;

    public List<SubjectDetailReturn> getPager_list() {
        return pager_list;
    }

    public void setPager_list(List<SubjectDetailReturn> pager_list) {
        this.pager_list = pager_list;
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

}