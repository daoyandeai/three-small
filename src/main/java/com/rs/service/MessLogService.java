package com.rs.service;
import com.alibaba.fastjson.JSON;
import com.rs.dao.ICompanyDao;
import com.rs.dao.IComplaintReportDao;
import com.rs.dao.IMessDao;
import com.rs.dao.IMessLogDao;
import com.rs.dao.IPatrolDao;
import com.rs.dao.ITrainExamLogDao;
import com.rs.dao.IUserDao;
import com.rs.po.CheckSelf;
import com.rs.po.Company;
import com.rs.po.ComplaintReport;
import com.rs.po.Mess;
import com.rs.po.MessLog;
import com.rs.po.Patrol;
import com.rs.po.TrainExamLog;
import com.rs.po.User;
import com.rs.po.WxTemplate;
import com.rs.po.WxTemplateData;
import com.rs.po.returnPo.MessLogReturn;
import com.rs.util.DateUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
/**
 * 
 * @ClassName: MessLogService
 * @Description: 消息日志服务层
 * @Author tangsh
 * @DateTime 2020年3月2日 上午10:56:11
 */
@Service
public class MessLogService extends BaseService<MessLog> {
	
	@Autowired
	private IMessLogDao messLogDao;
	@Autowired
	private IMessDao messDao;
	@Autowired
	private IUserDao userDao;
	@Autowired
	private ICompanyDao companyDao;
	@Autowired
	private CompanyService companyService;
	@Autowired
    private IPatrolDao patrolDao;
	@Autowired
	private CheckSelfService checkSelfService;
	@Autowired
	private ITrainExamLogDao trainExamLogDao;
	@Autowired
	private IComplaintReportDao complaintReportDao;
	/**
	 * 
	 * @Title: saveMessLog
	 * @Description: 根据事件保存消息日志接口
	 * @Author tangsh
	 * @DateTime 2020年3月3日 下午3:52:57
	 * @param mess
	 * @param user
	 * @return
	 */
	public Integer saveMessLogByEvent(Mess mess,User user) {
		if(mess==null||mess.getMess_event()==null||user==null) {
			return 0;
		}
		List<Mess> messlist=messDao.findByAll(mess);
		if(messlist==null||messlist.size()<1) {
			return 0;
		}
		MessLog ml=null;
		MessLog mltag=null;
		User u=null;
		Company c=null;
		List<User> ulist=null;
		List<MessLog> mllist=null;
		int pager_offset=0;
		int pager_openset=100;
		int num=0;
		for(Mess ms:messlist) {
			ml=new MessLog();
			BeanUtils.copyProperties(ms, ml);
			ml.setUser_code_add(user.getUser_code());
			ml.setUser_name_add(user.getUser_name());
			if(!StringUtils.isEmpty(ms.getMess_receive_person())) {//管理员
				u=new User();
				u.setUser_levels(ms.getMess_receive_person());
				u.setUser_state(1);
				while(true) {
					mllist=new ArrayList<MessLog>();
					u.setPager_offset(pager_offset);
		    		u.setPager_openset(pager_openset);
		    		ulist=userDao.findByAll(u);
					if(ulist!=null&&ulist.size()>0) {
						for(User _u:ulist) {
							mltag=new MessLog();
							BeanUtils.copyProperties(ml,mltag);
							mltag.setUser_code(_u.getUser_code());
							mltag.setUser_mobilephone(_u.getUser_mobilephone());
							mltag.setOpenid(_u.getOpenid());
							mltag.setIs_manager(2);
							if(!StringUtils.isEmpty(_u.getOpenid())) {
								mltag.setMess_wx_content(getMessLogContent(8, 2, _u.getOpenid(),  _u, ms.getMess_code()));
							}
							mltag.setMess_sms_content(getMessLogContent(8, 2,u, ms.getMess_code()));
							mltag.setOther_code(ms.getMess_code());
							mllist.add(mltag);
							num++;
						}
						messLogDao.saveBatch(mllist);
						if(ulist.size()<100) {
							break;
						}
						pager_offset=pager_offset+pager_openset;
					}else{
						break;
					}
				}
			}
			
			if(!StringUtils.isEmpty(ms.getCompanytype_codes())) {//企业人员
				c=new Company();
				c.setCompanytag_codes(ms.getCompanytag_codes());
				c.setCompanytype_codes(ms.getCompanytype_codes());
				while(true) {
					mllist=new ArrayList<MessLog>();
					c.setPager_offset(pager_offset);
		    		c.setPager_openset(pager_openset);
		    		ulist=userDao.findUserByCompanyList(c);
					if(ulist!=null&&ulist.size()>0) {
						for(User _u:ulist) {
							mltag=new MessLog();
							BeanUtils.copyProperties(ml,mltag);
							mltag.setUser_code(_u.getUser_code());
							mltag.setUser_mobilephone(_u.getUser_mobilephone());
							mltag.setOpenid(_u.getOpenid());
							mltag.setIs_manager(1);
							if(!StringUtils.isEmpty(_u.getOpenid())) {
								mltag.setMess_wx_content(getMessLogContent(8, 1, _u.getOpenid(),  _u, ms.getMess_code()));
							}
							mltag.setMess_sms_content(getMessLogContent(8, 1,u, ms.getMess_code()));
							mltag.setOther_code(ms.getMess_code());
							mllist.add(mltag);
							num++;
						}
						messLogDao.saveBatch(mllist);
						if(ulist.size()<100) {
							break;
						}
						pager_offset=pager_offset+pager_openset;
					}else{
						break;
					}
				}
			}
		}
		return num;
	}
	
	/**
	 * 
	 * @Title: saveMessLogByEventAndCompany
	 * @Description: 根据事件和企业编码保存消息日志接口（只发送企业）
	 * @Author tangsh
	 * @DateTime 2020年3月3日 下午4:13:53
	 * @param event
	 * @param company
	 * @param user
	 * @return
	 */
	public Integer saveMessLogByEventAndCompany(Integer event,String company_code,User user) {
		if(event==null||user==null||StringUtils.isEmpty(company_code)) {
			return 0;
		}
		Company company=companyDao.findByCode(company_code);
		if(company==null) {
			return 0;
		}
		Mess mess=new Mess();
		mess.setMess_event(event);
		mess.setCompanytag_codes(company.getCompanytag_code());
		mess.setCompanytype_codes(company.getCompanytype_code());
		List<Mess> messlist=messDao.findByAll(mess);
		if(messlist==null||messlist.size()<1) {
			return 0;
		}
		MessLog ml=null;
		List<MessLog> mllist=new ArrayList<MessLog>();
		User u=new User();
		u.setCompany_code(company_code);
		List<User> userlist=userDao.findByAll(u);
		if(userlist==null||userlist.size()<1) {
			return 0;
		}
		u=userlist.get(0);
		int num=0;
		for(Mess ms:messlist) {
			ml=new MessLog();
			BeanUtils.copyProperties(ms, ml);
			ml.setUser_code_add(user.getUser_code());
			ml.setUser_name_add(user.getUser_name());
			ml.setUser_code(u.getUser_code());
			ml.setUser_mobilephone(u.getUser_mobilephone());
			ml.setOpenid(u.getOpenid());
			ml.setOther_code(company_code);
			mllist.add(ml);
			num++;
		}
		messLogDao.saveBatch(mllist);
		return num;
	}
	
	/**
	 * 
	 * @Title: saveMessLogByEAndCAndM
	 * @Description: 保存企业事件日志信息，根据企业编码发送给企业及其相关负责人
	 * @Author tangsh
	 * @DateTime 2020年3月4日 上午9:21:18
	 * @param event
	 * @param company_code
	 * @param user
	 * @return
	 */
	public Integer saveMessLogByEAndCAndM(Integer event,String company_code,User user) {
		if(event==null||user==null||StringUtils.isEmpty(company_code)) {
			return 0;
		}
		Company company=companyDao.findByCode(company_code);
		if(company==null) {
			return 0;
		}
		String user_code_manage="";
		String user_mobilephone_manage="";
		String openid_manage="";
		
		String user_code_info="";
		String user_mobilephone_info="";
		String openid_info="";
		
		if(!StringUtils.isEmpty(company.getUser_code_manage())) {
			user_code_manage=company.getUser_code_manage();
		}
		if(!StringUtils.isEmpty(company.getUser_code_info())) {
			user_code_info=company.getUser_code_info();
		}
		User _user_manage=new User();
		if(user_code_manage.length()>0) {
			_user_manage.setUser_code(user_code_manage);
			_user_manage=userDao.findByCode(_user_manage);
			if(_user_manage!=null) {
				user_mobilephone_manage=_user_manage.getUser_mobilephone();
				openid_manage=_user_manage.getOpenid();
			}else {
				user_code_manage="";
			}
		}
		User _user_info=new User();
		if(user_code_info.length()>0) {
			_user_info=new User();
			_user_info.setUser_code(user_code_info);
			_user_info=userDao.findByCode(_user_info);
			if(_user_info!=null) {
				user_mobilephone_info=_user_info.getUser_mobilephone();
				openid_info=_user_info.getOpenid();
			}else {
				user_code_info="";
			}
		}
		
		Mess mess=new Mess();
		mess.setMess_event(event);
		mess.setCompanytag_codes(company.getCompanytag_code());
		mess.setCompanytype_codes(company.getCompanytype_code());
		List<Mess> messlist=messDao.findByAll(mess);
		if(messlist==null||messlist.size()<1) {
			return 0;
		}
		MessLog ml=null;
		MessLog ml1=null;
		MessLog ml2=null;
		List<MessLog> mllist=new ArrayList<MessLog>();
		User u=new User();
		u.setCompany_code(company_code);
		List<User> userlist=userDao.findByAll(u);
		if(userlist==null||userlist.size()<1) {
			return 0;
		}
		u=userlist.get(0);
		int num=0;
		for(Mess ms:messlist) {
			ml=new MessLog();
			BeanUtils.copyProperties(ms, ml);
			ml.setUser_code_add(user.getUser_code());
			ml.setUser_name_add(user.getUser_name());
			ml.setUser_code(u.getUser_code());
			ml.setUser_mobilephone(u.getUser_mobilephone());
			ml.setOpenid(u.getOpenid());
			ml.setIs_manager(1);
			if(!StringUtils.isEmpty(u.getOpenid())) {
				ml.setMess_wx_content(getMessLogContent(event, 1, u.getOpenid(), null, company_code));
			}
			ml.setOther_code(company_code);
			mllist.add(ml);
			num++;
			if(user_code_manage.length()>0) {
				ml1=new MessLog();
				BeanUtils.copyProperties(ms, ml1);
				ml1.setUser_code_add(user.getUser_code());
				ml1.setUser_name_add(user.getUser_name());
				ml1.setUser_code(user_code_manage);
				ml1.setUser_mobilephone(user_mobilephone_manage);
				ml1.setOpenid(openid_manage);
				ml.setIs_manager(2);
				if(!StringUtils.isEmpty(openid_manage)) {
					ml.setMess_wx_content(getMessLogContent(event, 2, openid_manage, _user_manage, company_code));
				}
				ml.setOther_code(company_code);
				mllist.add(ml1);
				num++;
			}
			if(user_code_info.length()>0) {
				ml2=new MessLog();
				BeanUtils.copyProperties(ms, ml2);
				ml2.setUser_code_add(user.getUser_code());
				ml2.setUser_name_add(user.getUser_name());
				ml2.setUser_code(user_code_info);
				ml2.setUser_mobilephone(user_mobilephone_info);
				ml2.setOpenid(openid_info);
				ml.setIs_manager(2);
				if(!StringUtils.isEmpty(openid_info)) {
					ml.setMess_wx_content(getMessLogContent(event, 2, openid_manage, _user_info, company_code));
				}
				ml.setOther_code(company_code);
				mllist.add(ml2);
				num++;
			}
		}
		messLogDao.saveBatch(mllist);
		return num;
	}
	
	public List<MessLogReturn> findByList_app(MessLog form){
		return messLogDao.findByList_app(form);
	}
	
	
	/**
	 * 
	 * @Title: saveMessLogByOtherCode
	 * @Description: 根据事件和关联事件关键编码新增消息
	 * @Author tangsh
	 * @DateTime 2020年3月5日 下午11:44:44
	 * @param event
	 * @param other_code
	 * @param user
	 * @return
	 */
	public Integer saveMessLogByOtherCode(Integer event,String company_code,String other_code,User user) {
		if(event==null||user==null||StringUtils.isEmpty(company_code)||StringUtils.isEmpty(other_code)) {
			return 0;
		}
		Company company=companyDao.findByCode(company_code);
		if(company==null) {
			return 0;
		}
		String user_code_manage="";
		String user_mobilephone_manage="";
		String openid_manage="";
		
		String user_code_info="";
		String user_mobilephone_info="";
		String openid_info="";
		
		if(!StringUtils.isEmpty(company.getUser_code_manage())) {
			user_code_manage=company.getUser_code_manage();
		}
		if(!StringUtils.isEmpty(company.getUser_code_info())) {
			user_code_info=company.getUser_code_info();
		}
		User _user_manage=new User();
		if(user_code_manage.length()>0) {
			_user_manage.setUser_code(user_code_manage);
			_user_manage=userDao.findByCode(_user_manage);
			if(_user_manage!=null) {
				user_mobilephone_manage=_user_manage.getUser_mobilephone();
				openid_manage=_user_manage.getOpenid();
			}else {
				user_code_manage="";
			}
		}
		User _user_info=new User();
		if(user_code_info.length()>0) {
			_user_info=new User();
			_user_info.setUser_code(user_code_info);
			_user_info=userDao.findByCode(_user_info);
			if(_user_info!=null) {
				user_mobilephone_info=_user_info.getUser_mobilephone();
				openid_info=_user_info.getOpenid();
			}else {
				user_code_info="";
			}
		}
		
		Mess mess=new Mess();
		mess.setMess_event(event);
		mess.setCompanytag_codes(company.getCompanytag_code());
		mess.setCompanytype_codes(company.getCompanytype_code());
		List<Mess> messlist=messDao.findByAll(mess);
		if(messlist==null||messlist.size()<1) {
			return 0;
		}
		MessLog ml=null;
		MessLog ml1=null;
		MessLog ml2=null;
		List<MessLog> mllist=new ArrayList<MessLog>();
		User u=userDao.findByCompanyCode(company_code);
		if(u==null) {
			return 0;
		}
		int num=0;
		for(Mess ms:messlist) {
			ml=new MessLog();
			BeanUtils.copyProperties(ms, ml);
			ml.setUser_code_add(user.getUser_code());
			ml.setUser_name_add(user.getUser_name());
			ml.setUser_code(u.getUser_code());
			ml.setUser_mobilephone(u.getUser_mobilephone());
			ml.setOpenid(u.getOpenid());
			ml.setIs_manager(1);
			if(!StringUtils.isEmpty(u.getOpenid())) {
				ml.setMess_wx_content(getMessLogContent(event, 1, u.getOpenid(), user, other_code));
			}
			ml.setMess_sms_content(getMessLogContent(event, 1,null, other_code));
			ml.setOther_code(other_code);
			mllist.add(ml);
			num++;
			if(user_code_manage.length()>0) {
				ml1=new MessLog();
				BeanUtils.copyProperties(ms, ml1);
				ml1.setUser_code_add(user.getUser_code());
				ml1.setUser_name_add(user.getUser_name());
				ml1.setUser_code(user_code_manage);
				ml1.setUser_mobilephone(user_mobilephone_manage);
				ml1.setOpenid(openid_manage);
				ml1.setIs_manager(2);
				if(!StringUtils.isEmpty(openid_manage)) {
					ml1.setMess_wx_content(getMessLogContent(event, 2, openid_manage, _user_manage, other_code));
				}
				ml1.setMess_sms_content(getMessLogContent(event, 2, _user_manage, other_code));
				ml1.setOther_code(other_code);
				mllist.add(ml1);
				num++;
			}
			if(user_code_info.length()>0) {
				ml2=new MessLog();
				BeanUtils.copyProperties(ms, ml2);
				ml2.setUser_code_add(user.getUser_code());
				ml2.setUser_name_add(user.getUser_name());
				ml2.setUser_code(user_code_info);
				ml2.setUser_mobilephone(user_mobilephone_info);
				ml2.setOpenid(openid_info);
				ml2.setIs_manager(2);
				if(!StringUtils.isEmpty(openid_info)) {
					ml2.setMess_wx_content(getMessLogContent(event, 2, openid_manage, _user_info, other_code));
				}
				ml2.setMess_sms_content(getMessLogContent(event, 2 , _user_info, other_code));
				ml2.setOther_code(other_code);
				mllist.add(ml2);
				num++;
			}
		}
		messLogDao.saveBatch(mllist);
		return num;
	}
	
	
	public String getMessLogContent(Integer event,Integer is_manager,String openid,User user,String search_code) {
		String str="";
		switch (event) {
		case 1:
			//必须传useradd 管理员
			if(is_manager==1) {//企业
				str=getWxTemplateContentStr("", openid, "3YNF0EG1PbOldutUTzBMzHQ53D60YT6sZOUCGdfrsK4", "您的备案申请进度更新了。", "审核通过", DateUtil.getCurrentTimeM(), "", "","", "详情");
			}else {
				//查询当天的更新数量
				Company c=new Company();
				c.setSearch_time_bigen(DateUtil.getDateYearMonthDate()+" 00:00:00");
				c.setSearch_time_end(DateUtil.getDateYearMonthDate()+" 23:59:59");
				c.setExamine("3");
				Integer count=companyService.findByUserCompanyCount(c,user)+1;
				str=getWxTemplateContentStr("", openid, "3YNF0EG1PbOldutUTzBMzHQ53D60YT6sZOUCGdfrsK4", "您有"+count+"条备案申请进度更新了。", "审核通过", DateUtil.getCurrentTimeM(), "", "","", "详情");
			}
			break;
		case 2:
			//必须传useradd 管理员  search_code 企业编码
			Company company=new Company();
			company.setCompany_code(search_code);
			company=companyService.findByCode(company);
			if(is_manager==1) {//企业
				str=getWxTemplateContentStr("", openid, "WcBkJG03xDex7hb0V2WjNTlwhgh8qfkQ11Gi8pR-ISc", "您的备案申请进度更新了。", "您申请的信息有误，现已驳回，请重新申请", user.getUser_name(), company.getUnpass_cause(), DateUtil.getCurrentTimeM(),"", "详情");
			}else {
				//查询当天的更新数量
				Company c=new Company();
				c.setSearch_time_bigen(DateUtil.getDateYearMonthDate()+" 00:00:00");
				c.setSearch_time_end(DateUtil.getDateYearMonthDate()+" 23:59:59");
				c.setExamine("2");
				Integer count=companyService.findByUserCompanyCount(c,user)+1;
				str=getWxTemplateContentStr("", openid, "WcBkJG03xDex7hb0V2WjNTlwhgh8qfkQ11Gi8pR-ISc", "您有"+count+"条备案申请进度更新了。", "您申请的信息有误，现已驳回，请重新申请", user.getUser_name(), company.getUnpass_cause(), DateUtil.getCurrentTimeM(),"", "详情");
			}
			break;
		case 3:
			//必须传useradd 管理员 search_code 巡查code
			Patrol patrol = new Patrol();
			patrol.setPatrol_code(search_code);
			patrol=patrolDao.findByCode(patrol);
			
			Company c=new Company();
			c.setCompany_code(patrol.getCompany_code());
			c=companyService.findByCode(c);
			
			if(is_manager==1) {//企业
				patrol.setPatrol_code(search_code);
				str=getWxTemplateContentStr("", openid, "T7k3u_7nH4IWr46KqVAZ3e5y_OF7_VqcbzdpADAYpgY", "您的备案巡查申请进度更新了。", c.getCity(), c.getCompany_name(), c.getBusiness_form(), patrol.getPatrol_user_name(),patrol.getPatrol_time(), "详情");
			}else {
				//查询当天的更新数量
				Patrol p = new Patrol();
				p.setSearch_code(user.getUser_code());
				p.setSearch_time_bigen(DateUtil.getDateYearMonthDate()+" 00:00:00");
				p.setSearch_time_end(DateUtil.getDateYearMonthDate()+" 23:59:59");
				p.setPatrol_state(2);
				Integer count=patrolDao.findLeftJoinCompanyCount(p)+1;
				str=getWxTemplateContentStr("", openid, "T7k3u_7nH4IWr46KqVAZ3e5y_OF7_VqcbzdpADAYpgY", "您有"+count+"条备案巡查进度更新了。",c.getCity(), c.getCompany_name(), c.getBusiness_form(), patrol.getPatrol_user_name(),patrol.getPatrol_time(), "详情");
			}
			break;
		case 4:
			//必须传useradd 管理员 search_code train_exam_code 考试编码
			TrainExamLog trainExamLog = new TrainExamLog();
			trainExamLog.setTrain_exam_log_code(search_code);
			trainExamLog = trainExamLogDao.findByCode(trainExamLog);
			String user_name = trainExamLog.getUser_name_chef();
			String train_exam_title = trainExamLog.getTrain_exam_title();
			String addtime = trainExamLog.getAddtime().substring(0,10);
			if(is_manager==1) {//企业
				str=getWxTemplateContentStr("", openid, "fXEytIOWZSvmNCIbQbVYlU9Tgm0mx3GNnnVyyC95y9g", "您的培训考核申请进度更新了。", user_name, train_exam_title, addtime, "不合格","", "详情");
			}else {
				//查询当天的更新数量
				trainExamLog = new TrainExamLog();
				trainExamLog.setUser_code_chef(user.getUser_code());
				trainExamLog.setAddtime(DateUtil.getDateYearMonthDate()+" 00:00:00");
				trainExamLog.setEnd_time(DateUtil.getDateYearMonthDate()+" 23:59:59");
				//网格员
				Integer count = 0;
				if(("5").equals(user.getUser_level())) {
					trainExamLog.setParam_filed("user_code_info");
				}else {
					trainExamLog.setParam_filed("user_code_manage");
				}
				count =  trainExamLogDao.findUnPassCount(trainExamLog)+1;
				str=getWxTemplateContentStr("", openid, "fXEytIOWZSvmNCIbQbVYlU9Tgm0mx3GNnnVyyC95y9g", "您有"+count+"条培训考核进度更新了。",user_name, train_exam_title, addtime, "不合格","", "详情");
			}
			break;
		case 5:
//			CheckSelf cs=new CheckSelf();
//			cs.setCheckself_code(search_code);
//			cs=checkSelfService.findByCode(cs);
			if(is_manager==1) {//企业
				str=getWxTemplateContentStr("", openid, "tDw3Uo3QkDZz0cGZXiwIQpiARnv7v5OUAEQJJe9jLb0", "您的自查自纠申请进度更新了。", DateUtil.getCurrentTimeM(), "未自查自纠", "", "","", "详情");
			}else {
				//查询当天的更新数量
				CheckSelf csm=new CheckSelf();
				csm.setIscheckself(2);
				Integer count=checkSelfService.findListCount(csm,user);
				str=getWxTemplateContentStr("", openid, "tDw3Uo3QkDZz0cGZXiwIQpiARnv7v5OUAEQJJe9jLb0", "您有"+count+"条自查自纠进度更新了。",DateUtil.getCurrentTimeM(), "未自查自纠", "", "","", "详情");
			}
			break;
		case 6:
			ComplaintReport cr=new ComplaintReport();
			cr.setComplaintreport_code(search_code);
			cr=complaintReportDao.findByCode(cr);
			if(is_manager==1) {//企业
				str=getWxTemplateContentStr("", openid, "y0A6j6qNrt_MkmyDFJ4l9WBWhdtCUwCfNIXMoZrEa-c", "您的投诉举报申请进度更新了。", cr.getComplaintreport_title(), "待处理", "", "","", "详情");
			}else {
				//查询投诉举报待处理
//				ComplaintReport crm=new ComplaintReport();
//				crm.setComplaintreport_state(1);
//				Integer count=0;//complaintReportDao.findListCount(crm,user)+1;
				str=getWxTemplateContentStr("", openid, "y0A6j6qNrt_MkmyDFJ4l9WBWhdtCUwCfNIXMoZrEa-c", "您刚收到1条新的投诉举报信息。",cr.getComplaintreport_title(), "待处理", "", "","", "详情");
			}
			break;
		case 7:
			cr=new ComplaintReport();
			cr.setComplaintreport_code(search_code);
			cr=complaintReportDao.findByCode(cr);
			if(is_manager==1) {//企业
				str=getWxTemplateContentStr("", openid, "y0A6j6qNrt_MkmyDFJ4l9WBWhdtCUwCfNIXMoZrEa-c", "您的投诉举报申请进度更新了。", cr.getComplaintreport_title(), "已处理", "", "","", "详情");
			}else {
//				//投诉举报已处理
//				ComplaintReport crm=new ComplaintReport();
//				crm.setComplaintreport_state(1);
//				Integer count=0;//complaintReportDao.findListCount(crm,user)+1;
				str=getWxTemplateContentStr("", openid, "y0A6j6qNrt_MkmyDFJ4l9WBWhdtCUwCfNIXMoZrEa-c", "您有1条投诉举报进度更新了。",cr.getComplaintreport_title(), "已处理", "", "","", "详情");
			}
			break;
		case 8:
			Mess _ms=new Mess();
			_ms.setMess_code(search_code);
			_ms=messDao.findByCode(_ms);
			String content="有一条新的应急公告信息";
			if(_ms!=null) {
				content=_ms.getMess_wx_content();
			}
			if(is_manager==1) {//企业
				str=getWxTemplateContentStr("", openid, "l-8dldL2HvcybaRj6vSL1jR6poo_QccyXScM2rsJV2M", content, "应急公告", DateUtil.getCurrentTimeM(), user.getUser_name(), "","", "详情");
			}else {
				str=getWxTemplateContentStr("", openid, "l-8dldL2HvcybaRj6vSL1jR6poo_QccyXScM2rsJV2M", content,"应急公告", DateUtil.getCurrentTimeM(), user.getUser_name(), "","", "详情");
			}
			break;
		case 9:
			Company company1=new Company();
			company1.setCompany_code(search_code);
			company1=companyService.findByCode(company1);
			if(is_manager==1) {//企业
				str=getWxTemplateContentStr("", openid, "ym1JsRRXM50BB07Rr8duzgeV8y5N-pgHcAqUJea0Sqc", "您的备案申请进度更新了。", company1.getCompany_name(),company1.getCity() , company1.getOperator()+" "+company1.getMobilephone(), company1.getAdd_time().substring(0,10),"", "详情");
			}else {
				//查询当天的更新数量
				Company c1=new Company();
				c1.setSearch_time_bigen(DateUtil.getDateYearMonthDate()+" 00:00:00");
				c1.setSearch_time_end(DateUtil.getDateYearMonthDate()+" 23:59:59");
				c1.setFiling_state(1);
				Integer count=companyService.findByUserCompanyCount(c1,user)+1;
				str=getWxTemplateContentStr("", openid, "ym1JsRRXM50BB07Rr8duzgeV8y5N-pgHcAqUJea0Sqc", "您有"+count+"条备案申请进度更新了。", company1.getCompany_name(), company1.getCity(), company1.getOperator()+" "+company1.getMobilephone(), company1.getAdd_time().substring(0,10),"", "详情");
			}
			break;
		case 10:
			patrol = new Patrol();
			patrol.setPatrol_code(search_code);
			patrol=patrolDao.findByCode(patrol);
			c=new Company();
			c.setCompany_code(patrol.getCompany_code());
			c=companyService.findByCode(c);
			if(is_manager==1) {//企业
			}else {
				//查询当天的更新数量
				Patrol p = new Patrol();
				p.setSearch_code(user.getUser_code());
				p.setSearch_time_bigen(DateUtil.getDateYearMonthDate()+" 00:00:00");
				p.setSearch_time_end(DateUtil.getDateYearMonthDate()+" 23:59:59");
				p.setPatrol_state(1);
				Integer count=patrolDao.findLeftJoinCompanyCount(p)+1;
				str=getWxTemplateContentStr("", openid, "iRO5RJQAF1Kx022H0RxHkRL8hRCDlu6vwWuRpVDIvDU", "您有"+count+"条工单巡查进度更新了。",patrol.getPatrol_code(), patrol.getPatrol_time(), c.getAddress(), "待巡查","", "详情");
			}
			break;
		default:
			
			break;
		}
		return str;
	}
	
	/**
	 * 
	 * @Title: saveMessLogByUserCode
	 * @Description: 指定用户电话和指定Openid发送消息
	 * @Author tangsh
	 * @DateTime 2020年3月13日 下午4:24:53
	 * @param mess_event 事件 目前只对6，7，10有用
	 * @param openid 给指定用户发
	 * @param user_code 给指定管理用户发
	 * @param user_mobilephone_info 给用户发
	 * @param other_code 对应事件的系统编码
	 * @param adduser 操作用户
	 * @param send_time 发送时间
	 * @return
	 */
	public Integer saveMessLogByUserCode(Integer mess_event,String openid,String user_code,String user_mobilephone_info,String other_code,User adduser,String send_time) {
		User seach_user=null;
		if(!StringUtils.isEmpty(user_code)) {
			seach_user=userDao.findByCode(user_code);
		}
		if(seach_user==null) {
			seach_user=new User();
		}
		Mess m=new Mess();
		m.setMess_event(mess_event);
		List<Mess> mlist=messDao.findByAll(m);
		if(mlist!=null&&mlist.size()>0) {
			MessLog ml=null;
			for(Mess ms:mlist) {
				if(!StringUtils.isEmpty(user_mobilephone_info)) {//指定用户					
					ml=new MessLog();
					BeanUtils.copyProperties(ms, ml);
					ml.setUser_code_add(adduser.getUser_code());
					ml.setUser_name_add(adduser.getUser_name());
					ml.setUser_code("");
					ml.setUser_mobilephone(user_mobilephone_info);
					ml.setOpenid(openid);
					ml.setIs_manager(1);
					if(!StringUtils.isEmpty(openid)) {
						ml.setMess_wx_content(getMessLogContent(mess_event, 1, openid, seach_user, other_code));
					}
					ml.setMess_sms_content(getMessLogContent(mess_event, 1 , seach_user, other_code));
					messLogDao.save(ml);
				}
				if(!StringUtils.isEmpty(seach_user.getUser_code())) {//指定管理员					
					ml=new MessLog();
					BeanUtils.copyProperties(ms, ml);
					ml.setUser_code_add(adduser.getUser_code());
					ml.setUser_name_add(adduser.getUser_name());
					ml.setUser_code(seach_user.getUser_code());
					ml.setUser_mobilephone(seach_user.getUser_mobilephone());
					ml.setOpenid(seach_user.getOpenid());
					ml.setIs_manager(2);
					if(!StringUtils.isEmpty(seach_user.getOpenid())) {
						ml.setMess_wx_content(getMessLogContent(mess_event, 2, seach_user.getOpenid(), seach_user, other_code));
					}
					ml.setMess_sms_content(getMessLogContent(mess_event, 2 , seach_user, other_code));
					if(!StringUtils.isEmpty(send_time)) {
						ml.setSend_time(send_time.substring(0,10));
					}
					messLogDao.save(ml);
				}
			}
		}
		return 1;
	}
	
	/**
	 * 方法重载,不含openid,为短信封装内容
	 * @Title: getMessLogContent
	 * @Description: 
	 * @Author sven
	 * @DateTime 2020年3月6日 下午8:53:41
	 * @param event
	 * @param is_manager
	 * @param user
	 * @param search_code
	 * @return
	 */
	public String getMessLogContent(Integer event,Integer is_manager,User user,String search_code) {
		String str="";
		switch (event) {
		case 1:
			//必须传useradd 管理员
			if(is_manager==1) {//企业
				str="提交的备案申请已通过审核";
			}else {
				//查询当天的更新数量
				Company c=new Company();
				c.setSearch_time_bigen(DateUtil.getDateYearMonthDate()+" 00:00:00");
				c.setSearch_time_end(DateUtil.getDateYearMonthDate()+" 23:59:59");
				c.setExamine("3");
				Integer count=companyService.findByUserCompanyCount(c,user);
				str="收到"+(count+1)+"条审核通过的备案信息";
			}
			break;
		case 2:
			//必须传useradd 管理员  search_code 企业编码
			Company company=new Company();
			company.setCompany_code(search_code);
			company=companyService.findByCode(company);
			if(is_manager==1) {//企业
				str="提交的备案申请被驳回，请登录平台查看相关详情并重新备案";
			}else {
				//查询当天的更新数量
				Company c=new Company();
				c.setSearch_time_bigen(DateUtil.getDateYearMonthDate()+" 00:00:00");
				c.setSearch_time_end(DateUtil.getDateYearMonthDate()+" 23:59:59");
				c.setExamine("2");
				Integer count=companyService.findByUserCompanyCount(c,user);
				str="收到"+(count+1)+"条被驳回的备案信息，请登录平台查看相关详情并重新备案";
			}
			break;
		case 3:
			//必须传useradd 管理员 search_code 巡查code
			Patrol patrol = new Patrol();
			patrol.setPatrol_code(search_code);
			patrol=patrolDao.findByCode(patrol);
			
			Company c=new Company();
			c.setCompany_code(patrol.getCompany_code());
			c=companyService.findByCode(c);
			
			if(is_manager==1) {//企业
				patrol.setPatrol_code(search_code);
				str="您"+DateUtil.getDateYearMonthDateChinese()+"的备案巡查不合格，请登录平台完善相关详情并重新接收巡查";
			}else {
				//查询当天的更新数量
				Patrol p = new Patrol();
				p.setSearch_code(user.getUser_code());
				p.setSearch_time_bigen(DateUtil.getDateYearMonthDate()+" 00:00:00");
				p.setSearch_time_end(DateUtil.getDateYearMonthDate()+" 23:59:59");
				p.setPatrol_state(2);
				Integer count=patrolDao.findLeftJoinCompanyCount(p);
				str=DateUtil.getDateYearMonthDateChinese()+"有"+(count+1)+"条备案巡查不合格，请登录平台查看相关详情并重新巡查";
			}
			break;
		case 4:
			//必须传useradd 管理员 search_code train_exam_code 考试编码
			if(is_manager==1) {//企业
				str="的培训考试不合格，请登录平台查看相关详情并重新考试";
			}else {
				//查询当天的更新数量
				TrainExamLog trainExamLog = new TrainExamLog();
				trainExamLog.setUser_code_chef(user.getUser_code());
				trainExamLog.setAddtime(DateUtil.getDateYearMonthDate()+" 00:00:00");
				trainExamLog.setEnd_time(DateUtil.getDateYearMonthDate()+" 23:59:59");
				//网格员
				Integer count = 0;
				if(("5").equals(user.getUser_level())) {
					trainExamLog.setParam_filed("user_code_info");
				}else {
					trainExamLog.setParam_filed("user_code_manage");
				}
				count =  trainExamLogDao.findUnPassCount(trainExamLog);
				str="有"+(count+1)+"条培训考试不合格信息，请登录平台查看相关详情";
			}
			break;
		case 5:
			CheckSelf cs=new CheckSelf();
			cs.setCheckself_code(search_code);
			cs=checkSelfService.findByCode(cs);
			if(is_manager==1) {//企业
				str="还没有完成企业自查自纠，请登录平台查看相关详情并完善企业自查自纠";
			}else {
				//查询当天的更新数量
				CheckSelf csm=new CheckSelf();
				csm.setIscheckself(2);
				Integer count=checkSelfService.findListCount(csm,user);
				str="有"+count+"家企业用户没有完成自查自纠，请登录平台查看相关详情并提醒企业自查自纠";
			}
			break;
		case 6:
			/*ComplaintReport cr=new ComplaintReport();
			cr.setComplaintreport_code(search_code);
			cr=complaintReportDao.findByCode(cr);*/
			if(is_manager==1) {//企业
				str="的投诉举报提交成功，请耐心等待平台处理结果";
			}else {
				//查询投诉举报待处理
//				ComplaintReport crm=new ComplaintReport();
//				crm.setComplaintreport_state(1);
//				Integer count=0;//complaintReportDao.findListCount(crm,user)+1;
				str="有1条投诉举报信息，请登录平台查看相关详情并及时处理";
			}
			break;
		case 7:
		/*	cr=new ComplaintReport();
			cr.setComplaintreport_code(search_code);
			cr=complaintReportDao.findByCode(cr);*/
			if(is_manager==1) {//企业
				str = "的投诉举报处理成功，登录平查看处理结果";
			}else {
//				//投诉举报已处理
//				ComplaintReport crm=new ComplaintReport();
//				crm.setComplaintreport_state(1);
//				Integer count=0;//complaintReportDao.findListCount(crm,user)+1;
				str = "有1条投诉举报信息处理成功，请登录平台查看处理结果";
			} 
			break;
		case 8:
			Mess _ms=new Mess();
			_ms.setMess_code(search_code);
			_ms=messDao.findByCode(_ms);
			str=_ms.getMess_sms_content();
			break;
		case 9:
			Company company1=new Company();
			company1.setCompany_code(search_code);
			company1=companyService.findByCode(company1);
			if(is_manager==1) {//企业
				str="提交的备案申请正在审核中，请登录平台查看相关详情并耐心等待";
			}else {
				//查询当天的更新数量
				Company c1=new Company();
				c1.setSearch_time_bigen(DateUtil.getDateYearMonthDate()+" 00:00:00");
				c1.setSearch_time_end(DateUtil.getDateYearMonthDate()+" 23:59:59");
				c1.setFiling_state(1);
				Integer count=companyService.findByUserCompanyCount(c1,user);
				str="有"+(count+1)+"条待审核的备案信息，请登录平台查看相关详情并审核备案";
			}
			break;
		default:
			patrol = new Patrol();
			patrol.setPatrol_code(search_code);
			patrol=patrolDao.findByCode(patrol);
			
			c=new Company();
			c.setCompany_code(patrol.getCompany_code());
			c=companyService.findByCode(c);
			
			if(is_manager==1) {//企业
			}else {
				//查询当天的更新数量
				Patrol p = new Patrol();
				p.setSearch_code(user.getUser_code());
				p.setSearch_time_bigen(DateUtil.getDateYearMonthDate()+" 00:00:00");
				p.setSearch_time_end(DateUtil.getDateYearMonthDate()+" 23:59:59");
				p.setPatrol_state(1);
				Integer count=patrolDao.findLeftJoinCompanyCount(p);
				str=count+"";
			}
			break;
		}
		return str;
	}
	
	/**
	 * 
	 * @Title: getWxTemplateContentStr
	 * @Description: 获取微信模板内容对应内容 值1~值4需要必须顺序填充
	 * @Author tangsh
	 * @DateTime 2020年3月5日 下午5:35:30
	 * @param url 跳转连接
	 * @param openid 用户ID
	 * @param template_id 模板ID
	 * @param title 模板标题描述
	 * @param keyword1val 值1
	 * @param keyword2val 值2
	 * @param keyword3val 值3
	 * @param keyword4val 值4
	 * @param remarkval 备注
	 * @return
	 */
	public static String getWxTemplateContentStr(String url,String openid,String template_id,String title,String keyword1val,String keyword2val,String keyword3val,String keyword4val,String keyword5val,String remarkval) {
		WxTemplate t=new WxTemplate();
		t.setUrl(url);  
		t.setTouser(openid);  
		t.setColor("#000000");  
		t.setTemplate_id(template_id);  
		Map<String,WxTemplateData> m = new HashMap<String,WxTemplateData>();  
		WxTemplateData first = new WxTemplateData();  
		first.setColor("#000000");  
		first.setValue(title);  
		m.put("first", first);  
  
		WxTemplateData keyword1 = new WxTemplateData();  
		keyword1.setColor("#000000");  
		keyword1.setValue(keyword1val);  
		m.put("keyword1", keyword1); 
		
		if(!StringUtils.isEmpty(keyword2val)) {
			WxTemplateData keyword2 = new WxTemplateData();  
			keyword2.setColor("#000000");  
			keyword2.setValue(keyword2val);
			m.put("keyword2", keyword2);
		}
		
		if(!StringUtils.isEmpty(keyword3val)) {
			WxTemplateData keyword3 = new WxTemplateData();  
			keyword3.setColor("#000000");  
			keyword3.setValue(keyword3val);
			m.put("keyword3", keyword3);
		}
		
		if(!StringUtils.isEmpty(keyword4val)) {
			WxTemplateData keyword4 = new WxTemplateData();  
			keyword4.setColor("#000000");  
			keyword4.setValue(keyword4val);
			m.put("keyword4", keyword4);
		}
		
		if(!StringUtils.isEmpty(keyword5val)) {
			WxTemplateData keyword5 = new WxTemplateData();  
			keyword5.setColor("#000000");  
			keyword5.setValue(keyword5val);
			m.put("keyword5", keyword5);
		}
		
		WxTemplateData remark = new WxTemplateData();  
		remark.setColor("#000000");  
		remark.setValue(remarkval);  
		m.put("remark", remark);
		t.setData(m);
		return JSON.toJSONString(t);
	}
	
	
}
