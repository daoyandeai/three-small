package com.rs.service;

import com.rs.dao.ILogDao;
import com.rs.po.Log;
import com.rs.util.CodeUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @ClassName: NewsService
 * @Description: 新闻宣传服务层
 * @Author tangsh
 * @DateTime 2019年12月16日 下午3:10:30
 */
@Service
public class LogService extends BaseService<Log>{

    @Autowired
    private ILogDao logDao;
    
    public Log findLastByCompanyCode(String company_code) {
    	return logDao.findLastByCompanyCode(company_code);
    }
    
    public Integer saveObject(String log_type,String log_result,String log_remark,String log_user_code,String log_user_name,String company_code) {
    	Log log=new Log();
    	log.setLog_code(CodeUtil.getSystemCode("log"));
    	log.setLog_type(log_type);
    	log.setLog_result(log_result);
    	log.setLog_remark(log_remark);
    	log.setLog_user_code(log_user_code);
    	log.setLog_user_name(log_user_name);
    	log.setCompany_code(company_code);
    	return logDao.save(log);
    }	
}
