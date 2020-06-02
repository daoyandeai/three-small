package com.rs.service;

import com.rs.dao.ICompanyEmployDao;
import com.rs.dao.IDepartmentUserDao;
import com.rs.po.CompanyEmploy;
import com.rs.po.DepartmentUser;
import com.rs.po.User;
import com.rs.util.RegularUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @ClassName: CompanyEmployService
 * @Description: 企业从业人员服务层
 * @Author tangsh
 * @DateTime 2020年3月31日 上午10:56:32
 */
@Service
public class CompanyEmployService extends BaseService<CompanyEmploy>{
	@Autowired
    private ICompanyEmployDao companyEmployDao;
    @Autowired
    private IDepartmentUserDao departmentUserDao;
	
    /**
     * 
     * @Title: findBySearchList
     * @Description: 联查企业获取人员信息列表
     * @Author tangsh
     * @DateTime 2020年3月31日 上午10:54:08
     * @param form
     * @param user
     * @return
     */
    public List<CompanyEmploy> findBySearchList(CompanyEmploy form,User user){
    	form.setC_business_forms(RegularUtil.getStringToSqlString(user.getBusiness_forms(),","));
    	form.setC_companytag_codes(RegularUtil.getStringToSqlString(user.getCompanytag_codes(),","));
    	form.setC_companytype_codes(RegularUtil.getStringToSqlString(user.getCompanytype_codes(),","));
    	
    	if(user.getUser_type().equals("协管员")) {
    		form.setC_is_manage_or_info(1);
    		form.setC_user_code_info(user.getUser_code());
    		return companyEmployDao.findBySearchList(form);
		}else {
			DepartmentUser du=new DepartmentUser();
			du.setUser_code(user.getUser_code());
			int result=departmentUserDao.findByCount(du);
			if(result>0) {
				form.setC_is_manage_or_info(2);
	    		form.setC_user_code_manage(user.getUser_code());
	    		return companyEmployDao.findBySearchList(form);
			}else {
				form.setC_province(user.getUser_province());
				form.setC_city(user.getUser_city());
				form.setC_area(user.getUser_area());
				form.setC_town(user.getUser_town());
				form.setC_vill(user.getUser_vill());
				return companyEmployDao.findBySearchList(form);
			}
		}
    }
    
    /**
     * 
     * @Title: findBySearchCount
     * @Description: 联查企业获取人员信息总数
     * @Author tangsh
     * @DateTime 2020年3月31日 上午10:51:31
     * @param form
     * @param user
     * @return
     */
	public Integer findBySearchCount(CompanyEmploy form,User user){
		form.setC_business_forms(RegularUtil.getStringToSqlString(user.getBusiness_forms(),","));
    	form.setC_companytag_codes(RegularUtil.getStringToSqlString(user.getCompanytag_codes(),","));
    	form.setC_companytype_codes(RegularUtil.getStringToSqlString(user.getCompanytype_codes(),","));
    	
    	if(user.getUser_type().equals("协管员")) {
    		form.setC_is_manage_or_info(1);
    		form.setC_user_code_info(user.getUser_code());
    		return companyEmployDao.findBySearchCount(form);
		}else {
			DepartmentUser du=new DepartmentUser();
			du.setUser_code(user.getUser_code());
			int result=departmentUserDao.findByCount(du);
			if(result>0) {
				form.setC_is_manage_or_info(2);
	    		form.setC_user_code_manage(user.getUser_code());
	    		return companyEmployDao.findBySearchCount(form);
			}else {
				form.setC_province(user.getUser_province());
				form.setC_city(user.getUser_city());
				form.setC_area(user.getUser_area());
				form.setC_town(user.getUser_town());
				form.setC_vill(user.getUser_vill());
				return companyEmployDao.findBySearchCount(form);
			}
		}
    }
}
