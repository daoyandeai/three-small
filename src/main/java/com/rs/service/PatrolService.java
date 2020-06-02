package com.rs.service;

import com.rs.dao.ICompanyDao;
import com.rs.dao.ILogDao;
import com.rs.dao.IPatrolDao;
import com.rs.po.*;
import com.rs.util.CodeUtil;
import com.rs.util.RegularUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.List;

@Service
public class PatrolService extends BaseService<Patrol>{
    @Autowired
    private IPatrolDao patrolDao;
    @Autowired
    private ICompanyDao companyDao;
    @Autowired
    private ILogDao logDao;
    
    @Override
    public int save(Patrol form) throws ParseException {
		//将上传的图片list转存为字符串格式用“,”分开
		if(form.getPatrol_imgs_list()!=null&&form.getPatrol_imgs_list().size()>0){
			form.setPatrol_imgs(RegularUtil.getListToString(form.getPatrol_imgs_list(),","));
		}
		//将处罚方式list转存为字符串格式用“,”分开
		if(form.getPunish_name_list()!=null&&form.getPunish_name_list().size()>0){
			form.setPunish_name(RegularUtil.getListToString(form.getPunish_name_list(),","));
		}
		form.setPatrol_code(CodeUtil.getSystemCode("patrol"));
		//添加企业坐标
		Company c=new Company();
		c.setCompany_code(form.getCompany_code());
		c=companyDao.findByCode(c);
		if(c!=null) {
			form.setLongitude(c.getLongitude());
			form.setLatitude(c.getLatitude());
		}
    	return patrolDao.save(form);
    }
    
    public Patrol search(Patrol form,User user) {
    	Patrol patrol = new Patrol();
    	//form.setExamine(3);
    	form.setSearch_code(user.getUser_code());
    	patrol.setPager_count(patrolDao.findLeftJoinCompanyCount(form));
		patrol.setPager_list(patrolDao.findLeftJoinCompanyList(form));
		return patrol;
    }
    
    public Patrol appSearch(Patrol form) {
    	Patrol patrol = new Patrol();
    	patrol.setPager_count(patrolDao.findLeftJoinCompanyCountApp(form));
		patrol.setPager_list(patrolDao.findLeftJoinCompanyListApp(form));
		return patrol;
    }
    public List<Patrol> findLeftJoinCompanyAll(Patrol form) {
    	return patrolDao.findLeftJoinCompanyAll(form);
    }
    
    public Patrol findByPatrolCompany(Patrol form) {
    	form=patrolDao.findByCode(form);
    	if(form==null) {
    		return null;
    	}
    	form.setPatrol_imgs_list(RegularUtil.getStringToList(form.getPatrol_imgs(),","));    	
    	if(!StringUtils.isEmpty(form.getCompany_code())) {
    		Company c=new Company();
    		c.setCompany_code(form.getCompany_code());
    		c=companyDao.findByCode(c);
    		if(c!=null) {
    			form.setCompanytag_code(c.getCompanytag_code());
    			form.setIsblacklist(c.getIsblacklist());
    			Log log=logDao.findLastByCompanyCode(form.getCompany_code());
    			if(log!=null) {
    				form.setIsblacklist_remark(log.getLog_remark());
    			}
    		}
    	}
        return form;
    }
}
