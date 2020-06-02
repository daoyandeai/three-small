package com.rs.service;

import com.rs.po.SubjectDetail;
import com.rs.po.returnPo.SubjectDetailReturn;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectDetailService extends BaseService<SubjectDetail> {
    public List<SubjectDetailReturn> all(SubjectDetail form) {
        List<SubjectDetailReturn> all = new ArrayList<>();
        String name = "";
        for(int i = 1;i <= 3;i++){
            form.setSubjectdetail_group(String.valueOf(i));
            switch (i){
                case (1): name = "流通";break;
                case (2): name = "生产";break;
                case (3): name = "餐饮";break;
            }
            SubjectDetailReturn subjectDetail = new SubjectDetailReturn();
            subjectDetail.setSubjectdetail_name(name);
            List<SubjectDetail> list = findByList(form);
            List<SubjectDetailReturn> sdrList = new ArrayList<>();
            for (SubjectDetail sd:
                    list) {
                SubjectDetailReturn sdr = new SubjectDetailReturn();
                BeanUtils.copyProperties(sd,sdr);
                sdrList.add(sdr);
            }
            subjectDetail.setPager_list(sdrList);
            all.add(subjectDetail);
        }
        return all;
    }
}
