package com.rs.service;

import com.rs.dao.IRegionDao;
import com.rs.po.Region;
import com.rs.util.CodeUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class RegionService extends BaseService<Region> {

    @Autowired
    private IRegionDao regionDao;

    public Region findByName(String form) { 
    	return regionDao.findByName(form);
    }
    
    public Region findOneByRegion(Region form) { 
    	return regionDao.findOneByRegion(form);
    }
    
    @Override
    public int save(Region form) {
    	//查询同级别最大的code
    	String last_code=regionDao.findLastByHcode(form.getRegion_high_code());
    	if(StringUtils.isEmpty(last_code)) {
    		last_code=form.getRegion_high_code();
    	}
    	form.setRegion_code(CodeUtil.getRegionCode(last_code, form.getRegion_type()));
    	return regionDao.save(form);
    }
    
    @Override
    public int update(Region t) {
    	//变更名称需要页面提供变更级别（和之前的节点名称 例如变更乡镇镇 需要提供XX省XX市XX县XX乡镇）
    	regionDao.updateUserRegion(t);
    	regionDao.updateReportRegion(t);
    	regionDao.updateQYTrainRegion(t);
    	regionDao.updateQYNewsRegion(t);
    	regionDao.updateCompanyRegion(t);
    	regionDao.updateDepartmentRegion(t);
    	regionDao.updateSXNewsRegion(t);
    	regionDao.updateSXTrainRegion(t);
    	regionDao.updateSXCaseCenterRegion(t);
    	return regionDao.update(t);
    }
    
    public int updateArea(Region t) {
    	//变更名称需要页面提供变更级别（和之前的节点名称 例如变更乡镇镇 需要提供XX省XX市XX县XX乡镇）
    	regionDao.updateUserRegion(t);
    	regionDao.updateReportRegion(t);
    	regionDao.updateQYTrainRegion(t);
    	regionDao.updateQYNewsRegion(t);
    	regionDao.updateCompanyRegion(t);
    	regionDao.updateDepartmentRegion(t);
    	regionDao.updateSXNewsRegion(t);
    	regionDao.updateSXTrainRegion(t);
    	regionDao.updateSXCaseCenterRegion(t);
    	if(StringUtils.isEmpty(t.getRegion_vill())) {
    		return regionDao.updateByHighCode(t);
    	}
    	t.setRegion_name(null);
    	return regionDao.update(t);
    }
    
    @Override
    public int delete(Region t) {
    	Region _r=new Region();
    	_r.setRegion_high_code(t.getRegion_code());
    	List<Region> _list=regionDao.findByAll(_r);
    	if(_list!=null&&_list.size()>0) {
    		return 0;
    	}
    	return regionDao.delete(t);
    }
    
}
