package com.rs.controller;

import com.rs.dao.IMainSubChefDao;
import com.rs.po.Company;
import com.rs.po.MainSubChef;
import com.rs.po.Patrol;
import com.rs.po.Report;
import com.rs.po.ReportProcess;
import com.rs.po.ReportSubChef;
import com.rs.po.TokenParam;
import com.rs.po.User;
import com.rs.po.returnPo.CompanyReturn;
import com.rs.po.returnPo.ReportCheckReturn;
import com.rs.service.CompanyService;
import com.rs.service.PatrolService;
import com.rs.service.ReportCheckService;
import com.rs.service.ReportProcessService;
import com.rs.service.ReportService;
import com.rs.service.ReportSubChefService;
import com.rs.service.UserService;
import com.rs.util.RegularUtil;
import com.rs.util.TokenUserUtil;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @ClassName: ReportController
 * @Description: 文件导出控制层
 * @Author tangsh
 * @DateTime 2020年2月24日 上午9:40:38
 */
@Transactional
@RestController
@RequestMapping("/api/report")
public class ReportController extends BaseController {

	@Autowired
	private PatrolService patrolService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private UserService userService;
	@Autowired
	private IMainSubChefDao mainSubChefDao;
	@Autowired
	private ReportService reportService;
	@Autowired
	private ReportProcessService reportProcessService;
	@Autowired
	private ReportSubChefService reportSubChefService;
	@Autowired
	private ReportCheckService reportCheckService;
	@Autowired
	private TokenParam tokenParam;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	private Logger logger = LoggerFactory.getLogger(ReportController.class);
	
	/**
	 * 
	 * @Title: exportPatrolSearch
	 * @Description: 导出巡查列表方法
	 * @Author tangsh
	 * @DateTime 2020年2月24日 下午5:08:02
	 * @param form
	 * @param request
	 * @param response
	 */
	@GetMapping(value = "/export_patrol_search")
	public void exportPatrolSearch(Patrol form,HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/vnd.ms-excel");
	    OutputStream fOut = null;
	    String codedFileName = "下载文件";
	    User user = TokenUserUtil.generateUser(request, tokenParam);
	    redisTemplate.delete("export_patrol_search" + user.getUser_loginname());
		try {
			codedFileName = java.net.URLEncoder.encode(form.getDown_name(), "UTF-8");  
	        response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");  
	        SXSSFWorkbook sXSSFWorkbook = new SXSSFWorkbook();
	        SXSSFSheet sheet = sXSSFWorkbook.createSheet(form.getDown_name());
	        sheet.setColumnWidth(0, 1500);
            sheet.setColumnWidth(1, 5000);
            sheet.setColumnWidth(2, 5000);  
            sheet.setColumnWidth(3, 5000);  
            sheet.setColumnWidth(4, 5000);  
            sheet.setColumnWidth(5, 4000);  
            sheet.setColumnWidth(6, 4000);  
            sheet.setColumnWidth(7, 4000);  
            sheet.setColumnWidth(8, 6000);  
	        CellStyle xcs=getStyle(sXSSFWorkbook,1);
	        CellStyle xcs_f=getStyle(sXSSFWorkbook,null);
	        int j=0;
	        SXSSFRow row =sheet.createRow((int)0);
	        row.setHeightInPoints(15);
	        SXSSFCell cell0 = row.createCell((int)0);
            cell0.setCellValue("序号"); 
            cell0.setCellStyle(xcs);
            SXSSFCell cell1 = row.createCell((int)1);
            cell1.setCellValue("工单号"); 
            cell1.setCellStyle(xcs);
            SXSSFCell cell2 = row.createCell((int)2);
            cell2.setCellValue("巡查对象"); 
            cell2.setCellStyle(xcs);
            SXSSFCell cell3 = row.createCell((int)3);  
            cell3.setCellValue("企业类型"); 
            cell3.setCellStyle(xcs);
            SXSSFCell cell4 = row.createCell((int)4);  
            cell4.setCellValue("监管部门"); 
            cell4.setCellStyle(xcs);
            SXSSFCell cell5 = row.createCell((int)5);  
            cell5.setCellValue("最近派发时间"); 
            cell5.setCellStyle(xcs);
            SXSSFCell cell6 = row.createCell((int)6);  
            cell6.setCellValue("最新巡查时间"); 
            cell6.setCellStyle(xcs);
            SXSSFCell cell7 = row.createCell((int)7);  
            cell7.setCellValue("最近巡查人"); 
            cell7.setCellStyle(xcs);
            SXSSFCell cell8 = row.createCell((int)8);  
            cell8.setCellValue("最近巡查结果"); 
            cell8.setCellStyle(xcs);
	        
            form.setPager_offset(0);
            if(form.getPager_openset()<1) {
            	form.setPager_openset(10000);
            }
            if(user!=null) {
            	Patrol patrol=patrolService.search(form, user);
    	        if(patrol.getPager_list()!=null&&patrol.getPager_list().size()>0) {
    	        	List<Patrol> plist=patrol.getPager_list();
    	        	for(int i=0;i<plist.size();i++) {
    	        		SXSSFRow nextrow = sheet.createRow((int)j+1);
    	    	        SXSSFCell nextcell0 = nextrow.createCell((int)0);
    	    	        nextcell0.setCellType(CellType.NUMERIC);  
    	    	        nextcell0.setCellValue(i+1);
    	    	        nextcell0.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell1 = nextrow.createCell((int)1);
    	    	        nextcell1.setCellType(CellType.STRING);  
    	    	        nextcell1.setCellValue(plist.get(i).getWork_order()==null?"":plist.get(i).getWork_order()); 
    	    	        nextcell1.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell2 = nextrow.createCell((int)2);
    	    	        nextcell2.setCellType(CellType.STRING);  
    	    	        nextcell2.setCellValue(plist.get(i).getCompany_name()==null?"":plist.get(i).getCompany_name()); 
    	    	        nextcell2.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell3 = nextrow.createCell((int)3);
    	    	        nextcell3.setCellType(CellType.STRING);  
    	    	        nextcell3.setCellValue(plist.get(i).getBusiness_form()==null?"":plist.get(i).getBusiness_form()); 
    	    	        nextcell3.setCellStyle(xcs_f); 
    	    	        SXSSFCell nextcell4 = nextrow.createCell((int)4);
    	    	        nextcell4.setCellType(CellType.STRING);  
    	    	        nextcell4.setCellValue(plist.get(i).getDepartment_name()==null?"":plist.get(i).getDepartment_name()); 
    	    	        nextcell4.setCellStyle(xcs_f); 
    	    	        SXSSFCell nextcell5 = nextrow.createCell((int)5);
    	    	        nextcell5.setCellType(CellType.STRING);  
    	    	        nextcell5.setCellValue(plist.get(i).getAdd_time()==null?"":plist.get(i).getAdd_time().substring(0,19)); 
    	    	        nextcell5.setCellStyle(xcs_f); 
    	    	        SXSSFCell nextcell6 = nextrow.createCell((int)6);
    	    	        nextcell6.setCellType(CellType.STRING);
    	    	        nextcell6.setCellValue(plist.get(i).getPatrol_time()==null?"":plist.get(i).getPatrol_time()); 
    	    	        nextcell6.setCellStyle(xcs_f); 
    	    	        SXSSFCell nextcell7 = nextrow.createCell((int)7);
    	    	        nextcell7.setCellType(CellType.STRING);  
    	    	        nextcell7.setCellValue(plist.get(i).getPatrol_user_name()==null?"":plist.get(i).getPatrol_user_name()); 
    	    	        nextcell7.setCellStyle(xcs_f); 
    	    	        SXSSFCell nextcell8 = nextrow.createCell((int)8);
    	    	        nextcell8.setCellType(CellType.STRING);  
    	    	        nextcell8.setCellValue(plist.get(i).getPatrol_result()==null?"":plist.get(i).getPatrol_result()); 
    	    	        nextcell8.setCellStyle(xcs_f); 
    	    	        
    	    	        j++;
    	        	}
    	        }
            }
			
			fOut = response.getOutputStream();  
			sXSSFWorkbook.write(fOut);
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.info(ex.toString());
		}finally{  
			try{
				fOut.flush();  
				fOut.close();  
			}catch(IOException e){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				logger.info(e.toString());
			}
			if(user!=null) {
				redisTemplate.opsForValue().set("export_patrol_search" + user.getUser_loginname(),"open",tokenParam.getDown_time(),TimeUnit.SECONDS); 
			}
		}  
	}
	
	/**
	 * 
	 * @Title: exportPatrolDouble
	 * @Description: 双随机巡查列表下载
	 * @Author tangsh
	 * @DateTime 2020年2月24日 下午5:08:45
	 * @param form
	 * @param request
	 * @param response
	 */
	@GetMapping(value = "/export_patrol_double")
	public void exportPatrolDouble(Patrol form,HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();  
	    session.setAttribute("state", null);
		response.setContentType("application/vnd.ms-excel");
	    OutputStream fOut = null;
	    String codedFileName = "下载文件";
	    User user = TokenUserUtil.generateUser(request, tokenParam);
	    redisTemplate.delete("export_patrol_double" + user.getUser_loginname());
		try {
			codedFileName = java.net.URLEncoder.encode(form.getDown_name(), "UTF-8");  
	        response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");  
	        SXSSFWorkbook sXSSFWorkbook = new SXSSFWorkbook();
	        SXSSFSheet sheet = sXSSFWorkbook.createSheet(form.getDown_name());
	        sheet.setColumnWidth(0, 1500);
            sheet.setColumnWidth(1, 4000);
            sheet.setColumnWidth(2, 3000);  
            sheet.setColumnWidth(3, 3000);  
            sheet.setColumnWidth(4, 5000);  
            sheet.setColumnWidth(5, 4000);  
            sheet.setColumnWidth(6, 4000); 
            sheet.setColumnWidth(7, 3000); 
            CellStyle xcs=getStyle(sXSSFWorkbook,1);
	        CellStyle xcs_f=getStyle(sXSSFWorkbook,null);
	        int j=0;
	        SXSSFRow row =sheet.createRow((int)0);
	        row.setHeightInPoints(15);
	        SXSSFCell cell0 = row.createCell((int)0);
            cell0.setCellValue("序号"); 
            cell0.setCellStyle(xcs);
            SXSSFCell cell1 = row.createCell((int)1);
            cell1.setCellValue("工单号"); 
            cell1.setCellStyle(xcs);
            SXSSFCell cell2 = row.createCell((int)2);
            cell2.setCellValue("派发人"); 
            cell2.setCellStyle(xcs);
            SXSSFCell cell3 = row.createCell((int)3);  
            cell3.setCellValue("抽查人"); 
            cell3.setCellStyle(xcs);
            SXSSFCell cell4 = row.createCell((int)4);  
            cell4.setCellValue("抽查对象"); 
            cell4.setCellStyle(xcs);
            SXSSFCell cell5 = row.createCell((int)5);  
            cell5.setCellValue("派发时间"); 
            cell5.setCellStyle(xcs);
            SXSSFCell cell6 = row.createCell((int)6);  
            cell6.setCellValue("巡查时间"); 
            cell6.setCellStyle(xcs);
            SXSSFCell cell7 = row.createCell((int)7);  
            cell7.setCellValue("巡查结果"); 
            cell7.setCellStyle(xcs);
	        
            form.setPager_offset(0);
            if(form.getPager_openset()<1) {
            	form.setPager_openset(10000);
            }
            if(user!=null) {
            	Patrol patrol=patrolService.search(form, user);
    	        if(patrol.getPager_list()!=null&&patrol.getPager_list().size()>0) {
    	        	List<Patrol> plist=patrol.getPager_list();
    	        	for(int i=0;i<plist.size();i++) {
    	        		SXSSFRow nextrow = sheet.createRow((int)j+1);
    	    	        SXSSFCell nextcell0 = nextrow.createCell((int)0);
    	    	        nextcell0.setCellType(CellType.NUMERIC);  
    	    	        nextcell0.setCellValue(i+1);
    	    	        nextcell0.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell1 = nextrow.createCell((int)1);
    	    	        nextcell1.setCellType(CellType.STRING);  
    	    	        nextcell1.setCellValue(plist.get(i).getWork_order()==null?"":plist.get(i).getWork_order()); 
    	    	        nextcell1.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell2 = nextrow.createCell((int)2);
    	    	        nextcell2.setCellType(CellType.STRING);  
    	    	        nextcell2.setCellValue(plist.get(i).getUser_name()==null?"":plist.get(i).getUser_name()); 
    	    	        nextcell2.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell3 = nextrow.createCell((int)3);
    	    	        nextcell3.setCellType(CellType.STRING);  
    	    	        nextcell3.setCellValue(plist.get(i).getPatrol_user_name()==null?"":plist.get(i).getPatrol_user_name()); 
    	    	        nextcell3.setCellStyle(xcs_f); 
    	    	        SXSSFCell nextcell4 = nextrow.createCell((int)4);
    	    	        nextcell4.setCellType(CellType.STRING);  
    	    	        nextcell4.setCellValue(plist.get(i).getCompany_name()==null?"":plist.get(i).getCompany_name()); 
    	    	        nextcell4.setCellStyle(xcs_f); 
    	    	        SXSSFCell nextcell5 = nextrow.createCell((int)5);
    	    	        nextcell5.setCellType(CellType.STRING);  
    	    	        nextcell5.setCellValue(plist.get(i).getAdd_time()==null?"":plist.get(i).getAdd_time().substring(0,19)); 
    	    	        nextcell5.setCellStyle(xcs_f); 
    	    	        SXSSFCell nextcell6 = nextrow.createCell((int)6);
    	    	        nextcell6.setCellType(CellType.STRING);
    	    	        nextcell6.setCellValue(plist.get(i).getPatrol_time()==null?"":plist.get(i).getPatrol_time()); 
    	    	        nextcell6.setCellStyle(xcs_f); 
    	    	        SXSSFCell nextcell7 = nextrow.createCell((int)7);
    	    	        nextcell7.setCellType(CellType.STRING);  
    	    	        nextcell7.setCellValue(plist.get(i).getPatrol_result()==null?"":plist.get(i).getPatrol_user_name()); 
    	    	        nextcell7.setCellStyle(xcs_f); 
    	    	        
    	    	        j++;
    	        	}
    	        }
            }
			
			fOut = response.getOutputStream();  
			sXSSFWorkbook.write(fOut);
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.info(ex.toString());
		}finally{  
			try{
				fOut.flush();  
				fOut.close();  
			}catch(IOException e){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				logger.info(e.toString());
			}  
			if(user!=null) {
				redisTemplate.opsForValue().set("export_patrol_double" + user.getUser_loginname(),"open",tokenParam.getDown_time(),TimeUnit.SECONDS); 
			} 
		}  
	}
	
	/**
	 * 
	 * @Title: exportCompanySearch
	 * @Description: 备案列表下载
	 * @Author tangsh
	 * @DateTime 2020年3月23日 上午11:28:57
	 * @param form
	 * @param request
	 * @param response
	 */
	@GetMapping(value = "/export_company_search")
	public void exportCompanySearch(Company form,HttpServletRequest request, HttpServletResponse response) {
	    String codedFileName = "下载文件";
	    if(!StringUtils.isEmpty(form.getDown_name())) {
	    	codedFileName=form.getDown_name();
	    }
	    User user = TokenUserUtil.generateUser(request, tokenParam);
	    redisTemplate.delete("export_company_search" + user.getUser_loginname());
		try {
	        SXSSFWorkbook sXSSFWorkbook = new SXSSFWorkbook();
	        SXSSFSheet sheet = sXSSFWorkbook.createSheet(form.getDown_name());
	        sheet.setColumnWidth(0, 1500);
            sheet.setColumnWidth(1, 5000);
            sheet.setColumnWidth(2, 5000);  
            sheet.setColumnWidth(3, 5000);  
            sheet.setColumnWidth(4, 6000);  
            sheet.setColumnWidth(5, 6000);  
            sheet.setColumnWidth(6, 5000);  
            sheet.setColumnWidth(7, 5000);  
            sheet.setColumnWidth(8, 5000);  
            sheet.setColumnWidth(9, 3000);  
            sheet.setColumnWidth(10, 4000);  
            sheet.setColumnWidth(11, 4000);  
	        CellStyle xcs=getStyle(sXSSFWorkbook,1);
	        CellStyle xcs_f=getStyle(sXSSFWorkbook,null);
	        int j=0;
	        SXSSFRow row =sheet.createRow((int)0);
	        row.setHeightInPoints(15);
	        SXSSFCell cell0 = row.createCell((int)0);
            cell0.setCellValue("序号"); 
            cell0.setCellStyle(xcs);
            SXSSFCell cell1 = row.createCell((int)1);
            cell1.setCellValue("主体类型"); 
            cell1.setCellStyle(xcs);
            SXSSFCell cell2 = row.createCell((int)2);
            cell2.setCellValue("主体业态"); 
            cell2.setCellStyle(xcs);
            SXSSFCell cell3 = row.createCell((int)3);  
            cell3.setCellValue("单位名称"); 
            cell3.setCellStyle(xcs);            
            SXSSFCell cell4 = row.createCell((int)4);
            cell4.setCellValue("法人/经营者"); 
            cell4.setCellStyle(xcs);
            SXSSFCell cell5 = row.createCell((int)5);  
            cell5.setCellValue("备案证号/许可证号"); 
            cell5.setCellStyle(xcs);
            SXSSFCell cell6 = row.createCell((int)6); 
            Integer r_filing_state=form.getFiling_state();
            if(r_filing_state==null) {
            	cell6.setCellValue("备案时间"); 
            	r_filing_state=2;
            }else {
            	if(r_filing_state==4) {
                	cell6.setCellValue("驳回时间"); 
                }else if(r_filing_state==3) {
                	cell6.setCellValue("归档时间"); 
                }else {
                	cell6.setCellValue("备案时间"); 
                }
            }
            cell6.setCellStyle(xcs);
            SXSSFCell cell7 = row.createCell((int)7);  
            cell7.setCellValue("监管部门"); 
            cell7.setCellStyle(xcs);
            SXSSFCell cell8 = row.createCell((int)8);  
            cell8.setCellValue("营业面积（㎡）"); 
            cell8.setCellStyle(xcs);
            SXSSFCell cell9 = row.createCell((int)9);  
            cell9.setCellValue("从业人数"); 
            cell9.setCellStyle(xcs);
            SXSSFCell cell10 = row.createCell((int)10);  
            cell10.setCellValue("业务类型"); 
            cell10.setCellStyle(xcs);
            SXSSFCell cell11 = row.createCell((int)11);  
            cell11.setCellValue("证件状态"); 
            cell11.setCellStyle(xcs);
            
	        
            form.setPager_offset(0);
            if(form.getPager_openset()<1) {
            	form.setPager_openset(10000);
            }
            if(user!=null) {
            	List<CompanyReturn> crlist=companyService.findByUserCompanyList(form, user);
    	        if(crlist!=null&&crlist.size()>0) {
    	        	for(int i=0;i<crlist.size();i++) {
    	        		SXSSFRow nextrow = sheet.createRow((int)j+1);
    	    	        SXSSFCell nextcell0 = nextrow.createCell((int)0);
    	    	        nextcell0.setCellType(CellType.NUMERIC);  
    	    	        nextcell0.setCellValue(i+1);
    	    	        nextcell0.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell1 = nextrow.createCell((int)1);
    	    	        nextcell1.setCellType(CellType.STRING);  
    	    	        if(!StringUtils.isEmpty(crlist.get(i).getCompanytag_code())) {
    	    	        	nextcell1.setCellValue(getCompanyTagName(crlist.get(i).getCompanytag_code())); 
    	    	        }else {
    	    	        	nextcell1.setCellValue(""); 
    	    	        }    	    	        
    	    	        nextcell1.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell2 = nextrow.createCell((int)2);
    	    	        nextcell2.setCellType(CellType.STRING);  
    	    	        nextcell2.setCellValue(crlist.get(i).getBusiness_form()==null?"":crlist.get(i).getBusiness_form()); 
    	    	        nextcell2.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell3 = nextrow.createCell((int)3);
    	    	        nextcell3.setCellType(CellType.STRING);  
    	    	        nextcell3.setCellValue(crlist.get(i).getCompany_name()==null?"":crlist.get(i).getCompany_name()); 
    	    	        nextcell3.setCellStyle(xcs_f); 
    	    	        SXSSFCell nextcell4 = nextrow.createCell((int)4);
    	    	        nextcell4.setCellType(CellType.STRING);  
    	    	        nextcell4.setCellValue(crlist.get(i).getOperator()==null?"":crlist.get(i).getOperator()); 
    	    	        nextcell4.setCellStyle(xcs_f); 
    	    	        SXSSFCell nextcell5 = nextrow.createCell((int)5);
    	    	        nextcell5.setCellType(CellType.STRING);  
    	    	        nextcell5.setCellValue(crlist.get(i).getRecord_code()==null?"":crlist.get(i).getRecord_code()); 
    	    	        nextcell5.setCellStyle(xcs_f); 
    	    	        SXSSFCell nextcell6 = nextrow.createCell((int)6);
    	    	        nextcell6.setCellType(CellType.STRING);
    	    	        if(r_filing_state>2) {
    	    	        	nextcell6.setCellValue(crlist.get(i).getFiling_state_time()==null?"":crlist.get(i).getFiling_state_time()); 
    	                }else {
    	                	nextcell6.setCellValue(crlist.get(i).getAdd_time()==null?"":crlist.get(i).getAdd_time().substring(0, 19)); 
    	                }
    	    	        nextcell6.setCellStyle(xcs_f); 
    	    	        SXSSFCell nextcell7 = nextrow.createCell((int)7);
    	    	        nextcell7.setCellType(CellType.STRING);  
    	    	        nextcell7.setCellValue(crlist.get(i).getDepartment_name()==null?"":crlist.get(i).getDepartment_name()); 
    	    	        nextcell7.setCellStyle(xcs_f); 
    	    	        SXSSFCell nextcell8 = nextrow.createCell((int)8);
    	    	        nextcell8.setCellType(CellType.STRING);  
    	    	        nextcell8.setCellValue(crlist.get(i).getPlace_area()==null?"":crlist.get(i).getPlace_area()); 
    	    	        nextcell8.setCellStyle(xcs_f); 
    	    	        SXSSFCell nextcell9 = nextrow.createCell((int)9);
    	    	        nextcell9.setCellType(CellType.STRING);  
    	    	        nextcell9.setCellValue(crlist.get(i).getEmploy_num()==null?"":crlist.get(i).getEmploy_num().toString()); 
    	    	        nextcell9.setCellStyle(xcs_f); 
    	    	        SXSSFCell nextcell10 = nextrow.createCell((int)10);
    	    	        nextcell10.setCellType(CellType.STRING);
    	    	        Integer b_type=crlist.get(i).getBusiness_type();
    	    	        if(!StringUtils.isEmpty(b_type)) {
    	    	        	if(b_type==1) {
        	    	        	nextcell10.setCellValue("首次"); 
    	    	        	}else if(b_type==2) {
    	    	        		nextcell10.setCellValue("延续");  
    	    	        	}else if(b_type==3) {
    	    	        		nextcell10.setCellValue("注销");  
    	    	        	}else {
    	    	        		nextcell10.setCellValue("变更"); 
    	    	        	}
    	    	        }else {
    	    	        	nextcell10.setCellValue("首次"); 
    	    	        }
    	    	        nextcell10.setCellStyle(xcs_f); 
    	    	        SXSSFCell nextcell11 = nextrow.createCell((int)11);
    	    	        nextcell11.setCellType(CellType.STRING);
    	    	        Integer state=crlist.get(i).getState();
    	    	        if(!StringUtils.isEmpty(state)) {
    	    	        	if(state==1) {
        	    	        	nextcell11.setCellValue("有效"); 
    	    	        	}else if(state==2) {
    	    	        		nextcell11.setCellValue("过期");  
    	    	        	}else if(state==3) {
    	    	        		nextcell11.setCellValue("注销");  
    	    	        	}else {
    	    	        		nextcell11.setCellValue("即将过期"); 
    	    	        	}
    	    	        }else {
    	    	        	nextcell11.setCellValue("有效"); 
    	    	        }
    	    	        nextcell11.setCellStyle(xcs_f); 
    	    	        j++;
    	        	}
    	        }
            }
            downLoadExcel(codedFileName, response, sXSSFWorkbook);
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.info(ex.toString());
		}finally{  
			if(user!=null) {
				redisTemplate.opsForValue().set("export_company_search" + user.getUser_loginname(),"open",tokenParam.getDown_time(),TimeUnit.SECONDS); 
			}
		}  
	}
	
	
	
	/**
	 * 
	 * @Title: exportCompanyDetail
	 * @Description: 备案详情下载
	 * @Author tangsh
	 * @DateTime 2020年4月9日 上午10:09:36
	 * @param form
	 * @param request
	 * @param response
	 */
	@GetMapping(value = "/export_company_detail")
	public void exportCompanyDetail(Company form,HttpServletRequest request, HttpServletResponse response) {
	    String codedFileName = "下载文件";
	    if(!StringUtils.isEmpty(form.getDown_name())) {
	    	codedFileName=form.getDown_name();
	    }
	    User user = TokenUserUtil.generateUser(request, tokenParam);
	    redisTemplate.delete("export_company_detail" + user.getUser_loginname());
		try {
			ClassPathResource cpr = new ClassPathResource("/templates/"+"companydetail.xlsx");
			InputStream is = cpr.getInputStream();
			Workbook sXSSFWorkbook = new XSSFWorkbook(is);
	        org.apache.poi.ss.usermodel.Sheet sheet =sXSSFWorkbook.getSheetAt(0);
	        CompanyReturn cr=companyService.detail(form);
            if(cr!=null) {
            	Row row = sheet.getRow(0);
            	Cell cell0 = row.getCell(0);
            	cell0.setCellValue(StringUtils.isEmpty(cr.getCompany_name())?"":cr.getCompany_name());
            	Row row2 = sheet.getRow(2);
            	Cell cell2_1 = row2.getCell(1);
            	cell2_1.setCellValue(getCompanyTagName(cr.getCompanytag_code()));
            	Cell cell2_4 = row2.getCell(4);
            	cell2_4.setCellValue(StringUtils.isEmpty(cr.getBusiness_form())?"":cr.getBusiness_form());
            	Row row3 = sheet.getRow(3);
            	Cell cell3_1 = row3.getCell(1);
            	cell3_1.setCellValue(StringUtils.isEmpty(cr.getCompany_name())?"":cr.getCompany_name());
            	Cell cell3_4 = row3.getCell(4);
            	cell3_4.setCellValue(StringUtils.isEmpty(cr.getOperator())?"":cr.getOperator());
            	Row row4 = sheet.getRow(4);
            	Cell cell4_1 = row4.getCell(1);
            	cell4_1.setCellValue(StringUtils.isEmpty(cr.getMobilephone())?"":cr.getMobilephone());
            	Cell cell4_4 = row4.getCell(4);
            	cell4_4.setCellValue(StringUtils.isEmpty(cr.getIdcard())?"":cr.getIdcard());
            	Row row5 = sheet.getRow(5);
            	Cell cell5_1 = row5.getCell(1);
            	cell5_1.setCellValue(cr.getAdd_time().substring(0,19));
            	Row row7 = sheet.getRow(7);
            	Cell cell7_1 = row7.getCell(1);
            	String code=StringUtils.isEmpty(cr.getRecord_code())?"":cr.getRecord_code();
            	if(StringUtils.isEmpty(code)) {
            		code=StringUtils.isEmpty(cr.getCredit_code())?"":cr.getCredit_code();
            	}
            	cell7_1.setCellValue(code);            	
            	Cell cell7_4 = row7.getCell(4);
            	cell7_4.setCellValue(StringUtils.isEmpty(cr.getIssue_time())?"":cr.getIssue_time());
            	Row row8 = sheet.getRow(8);
            	Cell cell8_1 = row8.getCell(1);
            	cell8_1.setCellValue(StringUtils.isEmpty(cr.getUnuseful_time())?"":cr.getUnuseful_time());
            	Row row9 = sheet.getRow(9);
            	Cell cell9_1 = row9.getCell(1);
            	String address=StringUtils.isEmpty(cr.getProvince())?"":cr.getProvince();
            	address+=StringUtils.isEmpty(cr.getCity())?"":cr.getCity();
            	address+=StringUtils.isEmpty(cr.getArea())?"":cr.getArea();
            	address+=StringUtils.isEmpty(cr.getTown())?"":cr.getTown();
            	address+=StringUtils.isEmpty(cr.getVill())?"":cr.getVill();
            	cell9_1.setCellValue(address);
            	Row row10 = sheet.getRow(10);
            	Cell cell10_1 = row10.getCell(1);
            	cell10_1.setCellValue(StringUtils.isEmpty(cr.getAddress())?"":cr.getAddress());
            	Row row11 = sheet.getRow(11);
            	Cell cell11_1 = row11.getCell(1);
            	cell11_1.setCellValue(StringUtils.isEmpty(cr.getBusiness_range())?"":cr.getBusiness_range());
            	Row row12 = sheet.getRow(12);
            	Cell cell12_1 = row12.getCell(1);
            	cell12_1.setCellValue(StringUtils.isEmpty(cr.getMain_subject())?"":cr.getMain_subject());
            	Row row13 = sheet.getRow(13);
            	Cell cell13_1 = row13.getCell(1);
            	cell13_1.setCellValue(StringUtils.isEmpty(cr.getSub_subject())?"":cr.getSub_subject());
            	Row row14 = sheet.getRow(14);
            	Cell cell14_1 = row14.getCell(1);
            	cell14_1.setCellValue(StringUtils.isEmpty(cr.getProduce_form())?"":cr.getProduce_form());
            	Row row15 = sheet.getRow(15);
            	Cell cell15_1 = row15.getCell(1);
            	cell15_1.setCellValue(StringUtils.isEmpty(cr.getProducetype_codes())?"":cr.getProducetype_codes());
            	Row row16 = sheet.getRow(16);
            	Cell cell16_1 = row16.getCell(1);
            	cell16_1.setCellValue(StringUtils.isEmpty(cr.getPlace_area())?"":cr.getPlace_area());
            	Cell cell16_4 = row16.getCell(4);
            	cell16_4.setCellValue(StringUtils.isEmpty(cr.getEmploy_num())?"":cr.getEmploy_num().toString());
            	Row row18 = sheet.getRow(18);
            	Cell cell18_1 = row18.getCell(1);
            	cell18_1.setCellValue(StringUtils.isEmpty(cr.getDepartment_name())?"":cr.getDepartment_name());
            	Cell cell18_4 = row18.getCell(4);
            	cell18_4.setCellValue(StringUtils.isEmpty(cr.getRegion_name())?"":cr.getRegion_name());
            	Row row19 = sheet.getRow(19);
            	Cell cell19_1 = row19.getCell(1);
            	if(cr.getUser_name_manage_list()!=null&&cr.getUser_name_manage_list().size()>0) {
            		cell19_1.setCellValue(RegularUtil.getListToString(cr.getUser_name_manage_list(), "、"));
            	}else {
            		cell19_1.setCellValue("");
            	}
            	Cell cell19_4 = row19.getCell(4);
            	if(cr.getUser_moblephone_manage_list()!=null&&cr.getUser_moblephone_manage_list().size()>0) {
            		cell19_4.setCellValue(RegularUtil.getListToString(cr.getUser_moblephone_manage_list(), "、"));
            	}else {
            		cell19_4.setCellValue("");
            	}
            	Row row20 = sheet.getRow(20);
            	Cell cell20_1 = row20.getCell(1);
            	if(cr.getUser_name_info_list()!=null&&cr.getUser_name_info_list().size()>0) {
            		cell20_1.setCellValue(RegularUtil.getListToString(cr.getUser_name_manage_list(), "、"));
            	}else {
            		cell20_1.setCellValue("");
            	}
            	Cell cell20_4 = row20.getCell(4);
            	if(cr.getUser_moblephone_info_list()!=null&&cr.getUser_moblephone_info_list().size()>0) {
            		cell20_4.setCellValue(RegularUtil.getListToString(cr.getUser_moblephone_info_list(), "、"));
            	}else {
            		cell20_4.setCellValue("");
            	}
            	if(cr.getCompanyEmployReturnList()!=null&&cr.getCompanyEmployReturnList().size()>0) {
            		Row row21 = sheet.getRow(21);
                	Cell cell21_0 = row21.getCell(0);
//                	cell21_1.setCellValue(StringUtils.isEmpty(cr.getProduce_form())?"":cr.getProduce_form());
                	cell21_0.setCellValue("从业人员");
            		int j=22;
            		Row rownext =null;
            		Cell cellnext_1=null;
            		Cell cellnext_2=null;
            		Cell cellnext_4=null;
            		Cell cellnext_5=null;
                	for(int i=0;i<cr.getCompanyEmployReturnList().size();i++) {
                		rownext = sheet.getRow(j);
                		cellnext_1 = rownext.getCell(0);
                		cellnext_1.setCellValue("姓名：");
                    	cellnext_2 = rownext.getCell(1);
                    	cellnext_2.setCellValue(StringUtils.isEmpty(cr.getCompanyEmployReturnList().get(i).getEmploy_name())?"":cr.getCompanyEmployReturnList().get(i).getEmploy_name());
                    	cellnext_4 = rownext.getCell(3);
                    	cellnext_4.setCellValue("健康证有效期：");
                    	cellnext_5 = rownext.getCell(4);
                    	cellnext_5.setCellValue(StringUtils.isEmpty(cr.getCompanyEmployReturnList().get(i).getUser_health_datedue())?"":cr.getCompanyEmployReturnList().get(i).getUser_health_datedue());
                    	j++;
                	}
            	}
            }
            downLoadExcel(codedFileName, response, sXSSFWorkbook);
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.info(ex.toString());
		}finally{  
			if(user!=null) {
				redisTemplate.opsForValue().set("export_company_detail" + user.getUser_loginname(),"open",tokenParam.getDown_time(),TimeUnit.SECONDS); 
			}
		}  
	}
	
	
	/**
	 * 
	 * @Title: exportConductSearch
	 * @Description: 人员权限-举办者下载
	 * @Author tangsh
	 * @DateTime 2020年4月9日 上午10:09:54
	 * @param form
	 * @param request
	 * @param response
	 */
	@GetMapping(value = "/export_conduct_search")
	public void exportConductSearch(User form,HttpServletRequest request, HttpServletResponse response) {
	    String codedFileName = "举办者信息表";
	    if(!StringUtils.isEmpty(form.getDown_name())) {
	    	codedFileName=form.getDown_name();
	    }
	    User user = TokenUserUtil.generateUser(request, tokenParam);
	    redisTemplate.delete("export_conduct_search" + user.getUser_loginname());
		try {
	        SXSSFWorkbook sXSSFWorkbook = new SXSSFWorkbook();
	        SXSSFSheet sheet = sXSSFWorkbook.createSheet("举办者信息");
	        sheet.setColumnWidth(0, 3500);
	        sheet.setColumnWidth(1, 3500);
	        sheet.setColumnWidth(2, 3500);  
	        sheet.setColumnWidth(3, 12000);  
	        CellStyle xcs=getStyle(sXSSFWorkbook,1);
	        CellStyle xcs_f=getStyle(sXSSFWorkbook,null);
	        int j=0;
	        SXSSFRow row =sheet.createRow((int)0);
	        row.setHeightInPoints(15);
	        SXSSFCell cell0 = row.createCell((int)0);
	        cell0.setCellValue("姓名"); 
	        cell0.setCellStyle(xcs);
	        SXSSFCell cell1 = row.createCell((int)1);
	        cell1.setCellValue("手机号"); 
	        cell1.setCellStyle(xcs);
	        SXSSFCell cell2 = row.createCell((int)2);
	        cell2.setCellValue("性别"); 
	        cell2.setCellStyle(xcs);
	        SXSSFCell cell3 = row.createCell((int)3);  
	        cell3.setCellValue("所属区域"); 
	        cell3.setCellStyle(xcs);   
            
	        
            form.setPager_offset(0);
            if(form.getPager_openset()<1) {
            	form.setPager_openset(10000);
            }
            form.setUser_type("举办者");
            if(user!=null) {
            	List<User> ulist=userService.findByQyList(form,user);
    	        if(ulist!=null&&ulist.size()>0) {
    	        	for(int i=0;i<ulist.size();i++) {
    	        		SXSSFRow nextrow = sheet.createRow((int)j+1);
    	    	        SXSSFCell nextcell0 = nextrow.createCell((int)0);
    	    	        nextcell0.setCellType(CellType.STRING);  
    	    	        nextcell0.setCellValue(ulist.get(i).getUser_name()==null?"":ulist.get(i).getUser_name());
    	    	        nextcell0.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell1 = nextrow.createCell((int)1);
    	    	        nextcell1.setCellType(CellType.STRING);
    	    	        nextcell1.setCellValue(ulist.get(i).getUser_mobilephone()==null?"":ulist.get(i).getUser_mobilephone()); 
    	    	        nextcell1.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell2 = nextrow.createCell((int)2);
    	    	        nextcell2.setCellType(CellType.STRING);  
    	    	        nextcell2.setCellValue(ulist.get(i).getUser_sex()==null?"":ulist.get(i).getUser_sex()); 
    	    	        nextcell2.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell3 = nextrow.createCell((int)3);
    	    	        nextcell3.setCellType(CellType.STRING);  
    	    	        String address=ulist.get(i).getUser_province()==null?"":ulist.get(i).getUser_province();
    					address+=ulist.get(i).getUser_city()==null?"":ulist.get(i).getUser_city();
    					address+=ulist.get(i).getUser_area()==null?"":ulist.get(i).getUser_area();
    					address+=ulist.get(i).getUser_town()==null?"":ulist.get(i).getUser_town();
    					address+=ulist.get(i).getUser_vill()==null?"":ulist.get(i).getUser_vill();
    	    	        nextcell3.setCellValue(address); 
    	    	        nextcell3.setCellStyle(xcs_f); 
    	    	        
    	    	        j++;
    	        	}
    	        }
            }
            downLoadExcel(codedFileName, response, sXSSFWorkbook);
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.info(ex.toString());
		}finally{  
			if(user!=null) {
				redisTemplate.opsForValue().set("export_conduct_search" + user.getUser_loginname(),"open",tokenParam.getDown_time(),TimeUnit.SECONDS); 
			}
		}  
	}
	
	/**
	 * 
	 * @Title: exportManageSearch
	 * @Description: 人员权限-平台管理员下载
	 * @Author tangsh
	 * @DateTime 2020年4月9日 上午10:22:20
	 * @param form
	 * @param request
	 * @param response
	 */
	@GetMapping(value = "/export_manage_search")
	public void exportManageSearch(User form,HttpServletRequest request, HttpServletResponse response) {
	    String codedFileName = "平台管理员信息表";
	    if(!StringUtils.isEmpty(form.getDown_name())) {
	    	codedFileName=form.getDown_name();
	    }
	    User user = TokenUserUtil.generateUser(request, tokenParam);
	    redisTemplate.delete("export_manage_search" + user.getUser_loginname());
		try {
	        SXSSFWorkbook sXSSFWorkbook = new SXSSFWorkbook();
	        SXSSFSheet sheet = sXSSFWorkbook.createSheet("平台管理员信息");
	        sheet.setColumnWidth(0, 7000);
	        sheet.setColumnWidth(1, 3500);
	        sheet.setColumnWidth(2, 3500);  
	        sheet.setColumnWidth(3, 12000);  
	        sheet.setColumnWidth(4, 3500);  
	        CellStyle xcs=getStyle(sXSSFWorkbook,1);
	        CellStyle xcs_f=getStyle(sXSSFWorkbook,null);
	        int j=0;
	        SXSSFRow row =sheet.createRow((int)0);
	        row.setHeightInPoints(15);
	        SXSSFCell cell0 = row.createCell((int)0);
	        cell0.setCellValue("姓名"); 
	        cell0.setCellStyle(xcs);
	        SXSSFCell cell1 = row.createCell((int)1);
	        cell1.setCellValue("手机号"); 
	        cell1.setCellStyle(xcs);
	        SXSSFCell cell2 = row.createCell((int)2);
	        cell2.setCellValue("性别"); 
	        cell2.setCellStyle(xcs);
	        SXSSFCell cell3 = row.createCell((int)3);  
	        cell3.setCellValue("所属区域"); 
	        cell3.setCellStyle(xcs);
	        SXSSFCell cell4 = row.createCell((int)4);
	        cell4.setCellValue("级别"); 
	        cell4.setCellStyle(xcs);
            
	        
            form.setPager_offset(0);
            if(form.getPager_openset()<1) {
            	form.setPager_openset(10000);
            }
            form.setUser_type("平台管理员");
            if(user!=null) {
            	List<User> ulist=userService.findByQyList(form,user);
    	        if(ulist!=null&&ulist.size()>0) {
    	        	for(int i=0;i<ulist.size();i++) {
    	        		SXSSFRow nextrow = sheet.createRow((int)j+1);
    	    	        SXSSFCell nextcell0 = nextrow.createCell((int)0);
    	    	        nextcell0.setCellType(CellType.STRING);  
    	    	        nextcell0.setCellValue(ulist.get(i).getUser_name()==null?"":ulist.get(i).getUser_name());
    	    	        nextcell0.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell1 = nextrow.createCell((int)1);
    	    	        nextcell1.setCellType(CellType.STRING);
    	    	        nextcell1.setCellValue(ulist.get(i).getUser_mobilephone()==null?"":ulist.get(i).getUser_mobilephone()); 
    	    	        nextcell1.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell2 = nextrow.createCell((int)2);
    	    	        nextcell2.setCellType(CellType.STRING);  
    	    	        nextcell2.setCellValue(ulist.get(i).getUser_sex()==null?"":ulist.get(i).getUser_sex()); 
    	    	        nextcell2.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell3 = nextrow.createCell((int)3);
    	    	        nextcell3.setCellType(CellType.STRING);  
    	    	        String address=ulist.get(i).getUser_province()==null?"":ulist.get(i).getUser_province();
    					address+=ulist.get(i).getUser_city()==null?"":ulist.get(i).getUser_city();
    					address+=ulist.get(i).getUser_area()==null?"":ulist.get(i).getUser_area();
    					address+=ulist.get(i).getUser_town()==null?"":ulist.get(i).getUser_town();
    					address+=ulist.get(i).getUser_vill()==null?"":ulist.get(i).getUser_vill();
    	    	        nextcell3.setCellValue(address); 
    	    	        nextcell3.setCellStyle(xcs_f); 
    	    	        SXSSFCell nextcell4 = nextrow.createCell((int)4);
    	    	        nextcell4.setCellType(CellType.STRING); 
    	    	        String _level="";
    					switch (ulist.get(i).getUser_level()) {
    					case "1":
    						_level="省级";
    						break;
    					case "2":
    						_level="市级";
    						break;
    					case "3":
    						_level="区/县级";
    						break;
    					case "4":
    						_level="乡镇级";
    						break;
    					default:
    						_level="村社区";
    						break;
    					}
    	    	        nextcell4.setCellValue(_level); 
    	    	        nextcell4.setCellStyle(xcs_f);
    	    	        
    	    	        j++;
    	        	}
    	        }
            }
            downLoadExcel(codedFileName, response, sXSSFWorkbook);
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.info(ex.toString());
		}finally{  
			if(user!=null) {
				redisTemplate.opsForValue().set("export_manage_search" + user.getUser_loginname(),"open",tokenParam.getDown_time(),TimeUnit.SECONDS); 
			}
		}  
	}
	
	/**
	 * 
	 * @Title: exportAssistantSearch
	 * @Description: 人员权限-协管员下载
	 * @Author tangsh
	 * @DateTime 2020年4月9日 上午10:23:17
	 * @param form
	 * @param request
	 * @param response
	 */
	@GetMapping(value = "/export_assistant_search")
	public void exportAssistantSearch(User form,HttpServletRequest request, HttpServletResponse response) {
	    String codedFileName = "协管员信息表";
	    if(!StringUtils.isEmpty(form.getDown_name())) {
	    	codedFileName=form.getDown_name();
	    }
	    User user = TokenUserUtil.generateUser(request, tokenParam);
	    redisTemplate.delete("export_assistant_search" + user.getUser_loginname());
		try {
	        SXSSFWorkbook sXSSFWorkbook = new SXSSFWorkbook();
	        SXSSFSheet sheet = sXSSFWorkbook.createSheet("协管员信息");
	        sheet.setColumnWidth(0, 3500);
	        sheet.setColumnWidth(1, 3500);
	        sheet.setColumnWidth(2, 3500);  
	        sheet.setColumnWidth(3, 12000);  
	        CellStyle xcs=getStyle(sXSSFWorkbook,1);
	        CellStyle xcs_f=getStyle(sXSSFWorkbook,null);
	        int j=0;
	        SXSSFRow row =sheet.createRow((int)0);
	        row.setHeightInPoints(15);
	        SXSSFCell cell0 = row.createCell((int)0);
	        cell0.setCellValue("姓名"); 
	        cell0.setCellStyle(xcs);
	        SXSSFCell cell1 = row.createCell((int)1);
	        cell1.setCellValue("手机号"); 
	        cell1.setCellStyle(xcs);
	        SXSSFCell cell2 = row.createCell((int)2);
	        cell2.setCellValue("性别"); 
	        cell2.setCellStyle(xcs);
	        SXSSFCell cell3 = row.createCell((int)3);  
	        cell3.setCellValue("所属区域"); 
	        cell3.setCellStyle(xcs);   
            
	        
            form.setPager_offset(0);
            if(form.getPager_openset()<1) {
            	form.setPager_openset(10000);
            }
            form.setUser_type("协管员");
            if(user!=null) {
            	List<User> ulist=userService.findByQyList(form,user);
    	        if(ulist!=null&&ulist.size()>0) {
    	        	for(int i=0;i<ulist.size();i++) {
    	        		SXSSFRow nextrow = sheet.createRow((int)j+1);
    	    	        SXSSFCell nextcell0 = nextrow.createCell((int)0);
    	    	        nextcell0.setCellType(CellType.STRING);  
    	    	        nextcell0.setCellValue(ulist.get(i).getUser_name()==null?"":ulist.get(i).getUser_name());
    	    	        nextcell0.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell1 = nextrow.createCell((int)1);
    	    	        nextcell1.setCellType(CellType.STRING);
    	    	        nextcell1.setCellValue(ulist.get(i).getUser_mobilephone()==null?"":ulist.get(i).getUser_mobilephone()); 
    	    	        nextcell1.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell2 = nextrow.createCell((int)2);
    	    	        nextcell2.setCellType(CellType.STRING);  
    	    	        nextcell2.setCellValue(ulist.get(i).getUser_sex()==null?"":ulist.get(i).getUser_sex()); 
    	    	        nextcell2.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell3 = nextrow.createCell((int)3);
    	    	        nextcell3.setCellType(CellType.STRING);  
    	    	        String address=ulist.get(i).getUser_province()==null?"":ulist.get(i).getUser_province();
    					address+=ulist.get(i).getUser_city()==null?"":ulist.get(i).getUser_city();
    					address+=ulist.get(i).getUser_area()==null?"":ulist.get(i).getUser_area();
    					address+=ulist.get(i).getUser_town()==null?"":ulist.get(i).getUser_town();
    					address+=ulist.get(i).getUser_vill()==null?"":ulist.get(i).getUser_vill();
    	    	        nextcell3.setCellValue(address); 
    	    	        nextcell3.setCellStyle(xcs_f); 
    	    	        
    	    	        j++;
    	        	}
    	        }
            }
            downLoadExcel(codedFileName, response, sXSSFWorkbook);
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.info(ex.toString());
		}finally{  
			if(user!=null) {
				redisTemplate.opsForValue().set("export_assistant_search" + user.getUser_loginname(),"open",tokenParam.getDown_time(),TimeUnit.SECONDS); 
			}
		}  
	}
	
	/**
	 * 
	 * @Title: exportAgritainmentSearch
	 * @Description: 人员权限-农家乐下载
	 * @Author tangsh
	 * @DateTime 2020年4月9日 上午10:48:02
	 * @param form
	 * @param request
	 * @param response
	 */
	@GetMapping(value = "/export_agritainment_search")
	public void exportAgritainmentSearch(User form,HttpServletRequest request, HttpServletResponse response) {
	    String codedFileName = "农家乐信息表";
	    if(!StringUtils.isEmpty(form.getDown_name())) {
	    	codedFileName=form.getDown_name();
	    }
	    User user = TokenUserUtil.generateUser(request, tokenParam);
	    redisTemplate.delete("export_agritainment_search" + user.getUser_loginname());
		try {
	        SXSSFWorkbook sXSSFWorkbook = new SXSSFWorkbook();
	        SXSSFSheet sheet = sXSSFWorkbook.createSheet("农家乐信息");
	        sheet.setColumnWidth(0, 3500);
	        sheet.setColumnWidth(1, 3500);
	        sheet.setColumnWidth(2, 3500);  
	        sheet.setColumnWidth(3, 3500);  
	        sheet.setColumnWidth(4, 12000);  
	        CellStyle xcs=getStyle(sXSSFWorkbook,1);
	        CellStyle xcs_f=getStyle(sXSSFWorkbook,null);
	        int j=0;
	        SXSSFRow row =sheet.createRow((int)0);
	        row.setHeightInPoints(15);
	        SXSSFCell cell0 = row.createCell((int)0);
	        cell0.setCellValue("企业名称"); 
	        cell0.setCellStyle(xcs);
	        SXSSFCell cell1 = row.createCell((int)1);
	        cell1.setCellValue("姓名"); 
	        cell1.setCellStyle(xcs);
	        SXSSFCell cell2 = row.createCell((int)2);
	        cell2.setCellValue("手机号"); 
	        cell2.setCellStyle(xcs);
	        SXSSFCell cell3 = row.createCell((int)3);  
	        cell3.setCellValue("性别"); 
	        cell3.setCellStyle(xcs);   
	        SXSSFCell cell4 = row.createCell((int)4);  
	        cell4.setCellValue("所属区域"); 
	        cell4.setCellStyle(xcs);   
            
	        
            form.setPager_offset(0);
            if(form.getPager_openset()<1) {
            	form.setPager_openset(10000);
            }
            form.setUser_type("农家乐");
            if(user!=null) {
            	List<User> ulist=userService.findByQyList(form,user);
    	        if(ulist!=null&&ulist.size()>0) {
    	        	for(int i=0;i<ulist.size();i++) {
    	        		SXSSFRow nextrow = sheet.createRow((int)j+1);
    	    	        SXSSFCell nextcell0 = nextrow.createCell((int)0);
    	    	        nextcell0.setCellType(CellType.STRING);  
    	    	        nextcell0.setCellValue(ulist.get(i).getCompany_name()==null?"":ulist.get(i).getCompany_name());
    	    	        nextcell0.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell1 = nextrow.createCell((int)1);
    	    	        nextcell1.setCellType(CellType.STRING);
    	    	        nextcell1.setCellValue(ulist.get(i).getUser_name()==null?"":ulist.get(i).getUser_name()); 
    	    	        nextcell1.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell2 = nextrow.createCell((int)2);
    	    	        nextcell2.setCellType(CellType.STRING);  
    	    	        nextcell2.setCellValue(ulist.get(i).getUser_mobilephone()==null?"":ulist.get(i).getUser_mobilephone()); 
    	    	        nextcell2.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell3 = nextrow.createCell((int)3);
    	    	        nextcell3.setCellType(CellType.STRING);  
    	    	        nextcell3.setCellValue(ulist.get(i).getUser_sex()==null?"":ulist.get(i).getUser_sex()); 
    	    	        nextcell3.setCellStyle(xcs_f); 
    	    	        SXSSFCell nextcell4 = nextrow.createCell((int)4);
    	    	        nextcell4.setCellType(CellType.STRING); 
    	    	        String address=ulist.get(i).getUser_province()==null?"":ulist.get(i).getUser_province();
    					address+=ulist.get(i).getUser_city()==null?"":ulist.get(i).getUser_city();
    					address+=ulist.get(i).getUser_area()==null?"":ulist.get(i).getUser_area();
    					address+=ulist.get(i).getUser_town()==null?"":ulist.get(i).getUser_town();
    					address+=ulist.get(i).getUser_vill()==null?"":ulist.get(i).getUser_vill();
    	    	        nextcell4.setCellValue(address); 
    	    	        nextcell4.setCellStyle(xcs_f);
    	    	        
    	    	        j++;
    	        	}
    	        }
            }
            downLoadExcel(codedFileName, response, sXSSFWorkbook);
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.info(ex.toString());
		}finally{  
			if(user!=null) {
				redisTemplate.opsForValue().set("export_agritainment_search" + user.getUser_loginname(),"open",tokenParam.getDown_time(),TimeUnit.SECONDS); 
			}
		}  
	}
	
	/**
	 * 
	 * @Title: exportHotelSearch
	 * @Description: 人员权限-乡村酒店下载
	 * @Author tangsh
	 * @DateTime 2020年4月9日 上午10:50:14
	 * @param form
	 * @param request
	 * @param response
	 */
	@GetMapping(value = "/export_hotel_search")
	public void exportHotelSearch(User form,HttpServletRequest request, HttpServletResponse response) {
	    String codedFileName = "酒店信息表";
	    if(!StringUtils.isEmpty(form.getDown_name())) {
	    	codedFileName=form.getDown_name();
	    }
	    User user = TokenUserUtil.generateUser(request, tokenParam);
	    redisTemplate.delete("export_hotel_search" + user.getUser_loginname());
		try {
	        SXSSFWorkbook sXSSFWorkbook = new SXSSFWorkbook();
	        SXSSFSheet sheet = sXSSFWorkbook.createSheet("乡村酒店信息");
	        sheet.setColumnWidth(0, 3500);
	        sheet.setColumnWidth(1, 3500);
	        sheet.setColumnWidth(2, 3500);  
	        sheet.setColumnWidth(3, 3500);  
	        sheet.setColumnWidth(4, 12000);  
	        CellStyle xcs=getStyle(sXSSFWorkbook,1);
	        CellStyle xcs_f=getStyle(sXSSFWorkbook,null);
	        int j=0;
	        SXSSFRow row =sheet.createRow((int)0);
	        row.setHeightInPoints(15);
	        SXSSFCell cell0 = row.createCell((int)0);
	        cell0.setCellValue("企业名称"); 
	        cell0.setCellStyle(xcs);
	        SXSSFCell cell1 = row.createCell((int)1);
	        cell1.setCellValue("姓名"); 
	        cell1.setCellStyle(xcs);
	        SXSSFCell cell2 = row.createCell((int)2);
	        cell2.setCellValue("手机号"); 
	        cell2.setCellStyle(xcs);
	        SXSSFCell cell3 = row.createCell((int)3);  
	        cell3.setCellValue("性别"); 
	        cell3.setCellStyle(xcs);   
	        SXSSFCell cell4 = row.createCell((int)4);  
	        cell4.setCellValue("所属区域"); 
	        cell4.setCellStyle(xcs);   
            
	        
            form.setPager_offset(0);
            if(form.getPager_openset()<1) {
            	form.setPager_openset(10000);
            }
            form.setUser_type("乡村酒店");
            if(user!=null) {
            	List<User> ulist=userService.findByQyList(form,user);
    	        if(ulist!=null&&ulist.size()>0) {
    	        	for(int i=0;i<ulist.size();i++) {
    	        		SXSSFRow nextrow = sheet.createRow((int)j+1);
    	    	        SXSSFCell nextcell0 = nextrow.createCell((int)0);
    	    	        nextcell0.setCellType(CellType.STRING);  
    	    	        nextcell0.setCellValue(ulist.get(i).getCompany_name()==null?"":ulist.get(i).getCompany_name());
    	    	        nextcell0.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell1 = nextrow.createCell((int)1);
    	    	        nextcell1.setCellType(CellType.STRING);
    	    	        nextcell1.setCellValue(ulist.get(i).getUser_name()==null?"":ulist.get(i).getUser_name()); 
    	    	        nextcell1.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell2 = nextrow.createCell((int)2);
    	    	        nextcell2.setCellType(CellType.STRING);  
    	    	        nextcell2.setCellValue(ulist.get(i).getUser_mobilephone()==null?"":ulist.get(i).getUser_mobilephone()); 
    	    	        nextcell2.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell3 = nextrow.createCell((int)3);
    	    	        nextcell3.setCellType(CellType.STRING);  
    	    	        nextcell3.setCellValue(ulist.get(i).getUser_sex()==null?"":ulist.get(i).getUser_sex()); 
    	    	        nextcell3.setCellStyle(xcs_f); 
    	    	        SXSSFCell nextcell4 = nextrow.createCell((int)4);
    	    	        nextcell4.setCellType(CellType.STRING); 
    	    	        String address=ulist.get(i).getUser_province()==null?"":ulist.get(i).getUser_province();
    					address+=ulist.get(i).getUser_city()==null?"":ulist.get(i).getUser_city();
    					address+=ulist.get(i).getUser_area()==null?"":ulist.get(i).getUser_area();
    					address+=ulist.get(i).getUser_town()==null?"":ulist.get(i).getUser_town();
    					address+=ulist.get(i).getUser_vill()==null?"":ulist.get(i).getUser_vill();
    	    	        nextcell4.setCellValue(address); 
    	    	        nextcell4.setCellStyle(xcs_f);
    	    	        
    	    	        j++;
    	        	}
    	        }
            }
            downLoadExcel(codedFileName, response, sXSSFWorkbook);
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.info(ex.toString());
		}finally{  
			if(user!=null) {
				redisTemplate.opsForValue().set("export_hotel_search" + user.getUser_loginname(),"open",tokenParam.getDown_time(),TimeUnit.SECONDS); 
			}
		}  
	}
	
	/**
	 * 
	 * @Title: exportDeliverySearch
	 * @Description: 人员权限-配送企业下载
	 * @Author tangsh
	 * @DateTime 2020年4月9日 上午10:57:18
	 * @param form
	 * @param request
	 * @param response
	 */
	@GetMapping(value = "/export_delivery_search")
	public void exportDeliverySearch(User form,HttpServletRequest request, HttpServletResponse response) {
	    String codedFileName = "配送企业信息表";
	    if(!StringUtils.isEmpty(form.getDown_name())) {
	    	codedFileName=form.getDown_name();
	    }
	    User user = TokenUserUtil.generateUser(request, tokenParam);
	    redisTemplate.delete("export_delivery_search" + user.getUser_loginname());
		try {
	        SXSSFWorkbook sXSSFWorkbook = new SXSSFWorkbook();
	        SXSSFSheet sheet = sXSSFWorkbook.createSheet("配送企业信息");
	        sheet.setColumnWidth(0, 3500);
	        sheet.setColumnWidth(1, 3500);
	        sheet.setColumnWidth(2, 3500);  
	        sheet.setColumnWidth(3, 3500);  
	        sheet.setColumnWidth(4, 12000);  
	        CellStyle xcs=getStyle(sXSSFWorkbook,1);
	        CellStyle xcs_f=getStyle(sXSSFWorkbook,null);
	        int j=0;
	        SXSSFRow row =sheet.createRow((int)0);
	        row.setHeightInPoints(15);
	        SXSSFCell cell0 = row.createCell((int)0);
	        cell0.setCellValue("企业名称"); 
	        cell0.setCellStyle(xcs);
	        SXSSFCell cell1 = row.createCell((int)1);
	        cell1.setCellValue("姓名"); 
	        cell1.setCellStyle(xcs);
	        SXSSFCell cell2 = row.createCell((int)2);
	        cell2.setCellValue("手机号"); 
	        cell2.setCellStyle(xcs);
	        SXSSFCell cell3 = row.createCell((int)3);  
	        cell3.setCellValue("性别"); 
	        cell3.setCellStyle(xcs);   
	        SXSSFCell cell4 = row.createCell((int)4);  
	        cell4.setCellValue("所属区域"); 
	        cell4.setCellStyle(xcs);   
            
	        
            form.setPager_offset(0);
            if(form.getPager_openset()<1) {
            	form.setPager_openset(10000);
            }
            form.setUser_type("配送企业");
            if(user!=null) {
            	List<User> ulist=userService.findByQyList(form,user);
    	        if(ulist!=null&&ulist.size()>0) {
    	        	for(int i=0;i<ulist.size();i++) {
    	        		SXSSFRow nextrow = sheet.createRow((int)j+1);
    	    	        SXSSFCell nextcell0 = nextrow.createCell((int)0);
    	    	        nextcell0.setCellType(CellType.STRING);  
    	    	        nextcell0.setCellValue(ulist.get(i).getCompany_name()==null?"":ulist.get(i).getCompany_name());
    	    	        nextcell0.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell1 = nextrow.createCell((int)1);
    	    	        nextcell1.setCellType(CellType.STRING);
    	    	        nextcell1.setCellValue(ulist.get(i).getUser_name()==null?"":ulist.get(i).getUser_name()); 
    	    	        nextcell1.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell2 = nextrow.createCell((int)2);
    	    	        nextcell2.setCellType(CellType.STRING);  
    	    	        nextcell2.setCellValue(ulist.get(i).getUser_mobilephone()==null?"":ulist.get(i).getUser_mobilephone()); 
    	    	        nextcell2.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell3 = nextrow.createCell((int)3);
    	    	        nextcell3.setCellType(CellType.STRING);  
    	    	        nextcell3.setCellValue(ulist.get(i).getUser_sex()==null?"":ulist.get(i).getUser_sex()); 
    	    	        nextcell3.setCellStyle(xcs_f); 
    	    	        SXSSFCell nextcell4 = nextrow.createCell((int)4);
    	    	        nextcell4.setCellType(CellType.STRING); 
    	    	        String address=ulist.get(i).getUser_province()==null?"":ulist.get(i).getUser_province();
    					address+=ulist.get(i).getUser_city()==null?"":ulist.get(i).getUser_city();
    					address+=ulist.get(i).getUser_area()==null?"":ulist.get(i).getUser_area();
    					address+=ulist.get(i).getUser_town()==null?"":ulist.get(i).getUser_town();
    					address+=ulist.get(i).getUser_vill()==null?"":ulist.get(i).getUser_vill();
    	    	        nextcell4.setCellValue(address); 
    	    	        nextcell4.setCellStyle(xcs_f);
    	    	        
    	    	        j++;
    	        	}
    	        }
            }
            downLoadExcel(codedFileName, response, sXSSFWorkbook);
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.info(ex.toString());
		}finally{  
			if(user!=null) {
				redisTemplate.opsForValue().set("export_delivery_search" + user.getUser_loginname(),"open",tokenParam.getDown_time(),TimeUnit.SECONDS); 
			}
		}  
	}
	
	/**
	 * 
	 * @Title: exportChefSearch
	 * @Description: 人员权限-乡厨下载
	 * @Author tangsh
	 * @DateTime 2020年4月9日 上午11:04:59
	 * @param form
	 * @param request
	 * @param response
	 */
	@GetMapping(value = "/export_chef_search")
	public void exportChefSearch(User form,HttpServletRequest request, HttpServletResponse response) {
	    String codedFileName = "乡厨信息表";
	    if(!StringUtils.isEmpty(form.getDown_name())) {
	    	codedFileName=form.getDown_name();
	    }
	    User user = TokenUserUtil.generateUser(request, tokenParam);
	    redisTemplate.delete("export_chef_search" + user.getUser_loginname());
		try {
	        SXSSFWorkbook sXSSFWorkbook = new SXSSFWorkbook();
	        SXSSFSheet sheet = sXSSFWorkbook.createSheet("乡厨信息");
	        sheet.setColumnWidth(0, 3500);
	        sheet.setColumnWidth(1, 3500);
	        sheet.setColumnWidth(2, 5500);  
	        sheet.setColumnWidth(3, 5000);  
	        sheet.setColumnWidth(4, 12500);  
	        sheet.setColumnWidth(5, 5000); 
	        sheet.setColumnWidth(6, 3500); 
	        sheet.setColumnWidth(7, 3500); 
	        sheet.setColumnWidth(8, 3500); 
	        sheet.setColumnWidth(9, 3500); 
	        CellStyle xcs=getStyle(sXSSFWorkbook,1);
	        CellStyle xcs_f=getStyle(sXSSFWorkbook,null);
	        int j=0;
	        SXSSFRow row =sheet.createRow((int)0);
	        row.setHeightInPoints(15);
	        SXSSFCell cell0 = row.createCell((int)0);
	        cell0.setCellValue("姓名"); 
	        cell0.setCellStyle(xcs);
	        SXSSFCell cell1 = row.createCell((int)1);
	        cell1.setCellValue("手机号"); 
	        cell1.setCellStyle(xcs);
	        SXSSFCell cell2 = row.createCell((int)2);
	        cell2.setCellValue("身份证号"); 
	        cell2.setCellStyle(xcs);
	        SXSSFCell cell3 = row.createCell((int)3);  
	        cell3.setCellValue("性别"); 
	        cell3.setCellStyle(xcs);   
	        SXSSFCell cell4 = row.createCell((int)4);  
	        cell4.setCellValue("所属区域"); 
	        cell4.setCellStyle(xcs);
			SXSSFCell cell5 = row.createCell((int)5);
			cell5.setCellValue("报备次数"); 
			cell5.setCellStyle(xcs);
			SXSSFCell cell6 = row.createCell((int)6);
			cell6.setCellValue("办宴次数"); 
			cell6.setCellStyle(xcs);
			SXSSFCell cell7 = row.createCell((int)7);
			cell7.setCellValue("vip状态"); 
			cell7.setCellStyle(xcs);
			SXSSFCell cell8 = row.createCell((int)8);
			cell8.setCellValue("审核状态"); 
			cell8.setCellStyle(xcs);
			SXSSFCell cell9 = row.createCell((int)9);
			cell9.setCellValue("用户状态"); 
			cell9.setCellStyle(xcs);   
            
	        
            form.setPager_offset(0);
            if(form.getPager_openset()<1) {
            	form.setPager_openset(10000);
            }
            form.setUser_type("乡厨");
            if(user!=null) {
            	List<User> ulist=userService.findByQyList(form,user);
    	        if(ulist!=null&&ulist.size()>0) {
    	        	for(int i=0;i<ulist.size();i++) {
    	        		SXSSFRow nextrow = sheet.createRow((int)j+1);
    	    	        SXSSFCell nextcell0 = nextrow.createCell((int)0);
    	    	        nextcell0.setCellType(CellType.STRING);  
    	    	        nextcell0.setCellValue(ulist.get(i).getUser_name()==null?"":ulist.get(i).getUser_name());
    	    	        nextcell0.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell1 = nextrow.createCell((int)1);
    	    	        nextcell1.setCellType(CellType.STRING);
    	    	        nextcell1.setCellValue(ulist.get(i).getUser_mobilephone()==null?"":ulist.get(i).getUser_mobilephone()); 
    	    	        nextcell1.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell2 = nextrow.createCell((int)2);
    	    	        nextcell2.setCellType(CellType.STRING);  
    	    	        nextcell2.setCellValue(ulist.get(i).getUser_idcard()==null?"暂无数据":ulist.get(i).getUser_idcard()); 
    	    	        nextcell2.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell3 = nextrow.createCell((int)3);
    	    	        nextcell3.setCellType(CellType.STRING);  
    	    	        nextcell3.setCellValue(ulist.get(i).getUser_sex()==null?"":ulist.get(i).getUser_sex()); 
    	    	        nextcell3.setCellStyle(xcs_f); 
    	    	        SXSSFCell nextcell4 = nextrow.createCell((int)4);
    	    	        nextcell4.setCellType(CellType.STRING); 
    	    	        String address=ulist.get(i).getUser_province()==null?"":ulist.get(i).getUser_province();
    					address+=ulist.get(i).getUser_city()==null?"":ulist.get(i).getUser_city();
    					address+=ulist.get(i).getUser_area()==null?"":ulist.get(i).getUser_area();
    					address+=ulist.get(i).getUser_town()==null?"":ulist.get(i).getUser_town();
    					address+=ulist.get(i).getUser_vill()==null?"":ulist.get(i).getUser_vill();
    	    	        nextcell4.setCellValue(address); 
    	    	        nextcell4.setCellStyle(xcs_f);
    	    	        
    	    	        SXSSFCell nextcell5 = nextrow.createCell((int)5);
    	    	        nextcell5.setCellType(CellType.STRING);  
    	    	        nextcell5.setCellValue(ulist.get(i).getReport_count()==null?0:ulist.get(i).getReport_count()); 
    	    	        nextcell5.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell6 = nextrow.createCell((int)6);
    	    	        nextcell6.setCellType(CellType.STRING);  
    	    	        nextcell6.setCellValue(ulist.get(i).getBanquet_count()==null?0:ulist.get(i).getBanquet_count()); 
    	    	        nextcell6.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell7 = nextrow.createCell((int)7);
    	    	        nextcell7.setCellType(CellType.STRING);  
    	    	        nextcell7.setCellValue(Integer.valueOf(2).equals(ulist.get(i).getVip_state())?"vip":"非vip"); 
    	    	        nextcell7.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell8 = nextrow.createCell((int)8);
    	    	        nextcell8.setCellType(CellType.STRING);  
    	    	        if(ulist.get(i).getUser_audit_state()==1) {
    	    	        	nextcell8.setCellValue("待审核"); 
    					}else if(ulist.get(i).getUser_audit_state()==2) {
    						nextcell8.setCellValue("通过"); 
    					}else if(ulist.get(i).getUser_audit_state()==3) {
    						nextcell8.setCellValue("不通过"); 
    					}
    	    	        nextcell8.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell9 = nextrow.createCell((int)9);
    	    	        nextcell9.setCellType(CellType.STRING);  
    	    	        nextcell9.setCellValue(ulist.get(i).getUser_state()==1?"启用":"禁用"); 
    	    	        nextcell9.setCellStyle(xcs_f);
    	    	        
    	    	        j++;
    	        	}
    	        }
            }
            downLoadExcel(codedFileName, response, sXSSFWorkbook);
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.info(ex.toString());
		}finally{  
			if(user!=null) {
				redisTemplate.opsForValue().set("export_chef_search" + user.getUser_loginname(),"open",tokenParam.getDown_time(),TimeUnit.SECONDS); 
			}
		}  
	}
	
	/**
	 * 
	 * @Title: exportMainSubChefSearch
	 * @Description: 人员权限-帮厨下载
	 * @Author tangsh
	 * @DateTime 2020年4月9日 上午11:10:36
	 * @param form
	 * @param request
	 * @param response
	 */
	@GetMapping(value = "/export_mainsubchef_search")
	public void exportMainSubChefSearch(MainSubChef form,HttpServletRequest request, HttpServletResponse response) {
	    String codedFileName = "帮厨人员信息表";
	    if(!StringUtils.isEmpty(form.getDown_name())) {
	    	codedFileName=form.getDown_name();
	    }
	    User user = TokenUserUtil.generateUser(request, tokenParam);
	    redisTemplate.delete("export_mainsubchef_search" + user.getUser_loginname());
		try {
	        SXSSFWorkbook sXSSFWorkbook = new SXSSFWorkbook();
	        SXSSFSheet sheet = sXSSFWorkbook.createSheet("帮厨人员信息");
	        sheet.setColumnWidth(0, 3500);
	        sheet.setColumnWidth(1, 7000);  
	        CellStyle xcs=getStyle(sXSSFWorkbook,1);
	        CellStyle xcs_f=getStyle(sXSSFWorkbook,null);
	        int j=0;
	        SXSSFRow row =sheet.createRow((int)0);
	        row.setHeightInPoints(15);
	        SXSSFCell cell0 = row.createCell((int)0);
	        cell0.setCellValue("姓名"); 
	        cell0.setCellStyle(xcs);
	        SXSSFCell cell1 = row.createCell((int)1);
	        cell1.setCellValue("身份证号"); 
	        cell1.setCellStyle(xcs);
            
            if(user!=null) {
            	List<MainSubChef> msclist=mainSubChefDao.findByAll(form);
    	        if(msclist!=null&&msclist.size()>0) {
    	        	for(int i=0;i<msclist.size();i++) {
    	        		SXSSFRow nextrow = sheet.createRow((int)j+1);
    	    	        SXSSFCell nextcell0 = nextrow.createCell((int)0);
    	    	        nextcell0.setCellType(CellType.STRING);  
    	    	        nextcell0.setCellValue(msclist.get(i).getUser_name_subchef()==null?"":msclist.get(i).getUser_name_subchef());
    	    	        nextcell0.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell1 = nextrow.createCell((int)1);
    	    	        nextcell1.setCellType(CellType.STRING);
    	    	        nextcell1.setCellValue(msclist.get(i).getUser_idcard_subchef()==null?"":msclist.get(i).getUser_idcard_subchef()); 
    	    	        nextcell1.setCellStyle(xcs_f);
    	    	        
    	    	        j++;
    	        	}
    	        }
            }
            downLoadExcel(codedFileName, response, sXSSFWorkbook);
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.info(ex.toString());
		}finally{  
			if(user!=null) {
				redisTemplate.opsForValue().set("export_mainsubchef_search" + user.getUser_loginname(),"open",tokenParam.getDown_time(),TimeUnit.SECONDS); 
			}
		}  
	}
	
	/**
	 * 
	 * @Title: exportReportSearch
	 * @Description: 群宴报备查询结果导出
	 * @Author tangsh
	 * @DateTime 2020年4月13日 下午5:51:59
	 * @param form
	 * @param request
	 * @param response
	 */
	@GetMapping(value = "/export_report_search")
	public void exportReportSearch(Report form,HttpServletRequest request, HttpServletResponse response) {
	    String codedFileName = "报备列表";
	    if(!StringUtils.isEmpty(form.getDown_name())) {
	    	codedFileName=form.getDown_name();
	    }
	    User user = TokenUserUtil.generateUser(request, tokenParam);
	    redisTemplate.delete("export_report_search" + user.getUser_loginname());
		try {
	        SXSSFWorkbook sXSSFWorkbook = new SXSSFWorkbook();
	        SXSSFSheet sheet = sXSSFWorkbook.createSheet("报备信息");
	        sheet.setColumnWidth(0, 3500);
	        sheet.setColumnWidth(1, 5500);
	        sheet.setColumnWidth(2, 3000);
	        sheet.setColumnWidth(3, 4000);
	        sheet.setColumnWidth(4, 4000);
	        sheet.setColumnWidth(5, 4000);
	        sheet.setColumnWidth(6, 6000);
	        sheet.setColumnWidth(7, 1000);
	        sheet.setColumnWidth(8, 3500);
	        sheet.setColumnWidth(9, 1500);
	        sheet.setColumnWidth(10, 3500);
	        sheet.setColumnWidth(11, 2000);
	        sheet.setColumnWidth(12, 3500);
	        sheet.setColumnWidth(13, 3500);
	        sheet.setColumnWidth(14, 4000);
	        sheet.setColumnWidth(15, 4000);
	        
	        CellStyle xcs_bt=getStyle(sXSSFWorkbook,2);
	        CellStyle xcs=getStyle(sXSSFWorkbook,1);
	        CellStyle xcs_f=getStyle(sXSSFWorkbook,null);
	        
	        SXSSFRow bt_r =sheet.createRow((int)0);
	        bt_r.setHeightInPoints(15);
			SXSSFCell bt_c = bt_r.createCell((int)0);
			String address = "";
			if(!StringUtils.isEmpty(user.getUser_province())) {
				address += user.getUser_province();
			}else {
				address += (StringUtils.isEmpty(form.getProvince_conduct())? "" :form.getProvince_conduct());
			}
			
			if(!StringUtils.isEmpty(user.getUser_city())) {
				address += user.getUser_city();
			}else {
				address += (StringUtils.isEmpty(form.getCity_conduct())? "" :form.getCity_conduct());
			}
			
			if(!StringUtils.isEmpty(user.getUser_area())) {
				address += user.getUser_area();
			}else {
				address += (StringUtils.isEmpty(form.getArea_conduct())? "" :form.getArea_conduct());
			}
			if(!StringUtils.isEmpty(user.getUser_town())) {
				address += user.getUser_town();
			}else {
				address += (StringUtils.isEmpty(form.getTown_conduct())? "" :form.getTown_conduct());
			}
			if(!StringUtils.isEmpty(user.getUser_vill())) {
				address += user.getUser_vill();
			}else {
				address += (StringUtils.isEmpty(form.getVill_conduct())? "" :form.getVill_conduct());
			}
			bt_c.setCellValue(address+"（"+(StringUtils.isEmpty(form.getAddtime())?"":form.getAddtime())+"-"+(StringUtils.isEmpty(form.getEndtime())?"":form.getEndtime())+"）"); 
			bt_c.setCellStyle(xcs_bt);
			CellRangeAddress cellRangeAddress =new CellRangeAddress(0, 0, 0, 14);
			sheet.addMergedRegion(cellRangeAddress);
			
	        int j=1;
	        SXSSFRow row =sheet.createRow((int)1);
	        row.setHeightInPoints(15);
	        SXSSFCell cell0 = row.createCell((int)0);
	        cell0.setCellValue("序号"); 
	        cell0.setCellStyle(xcs);
	        SXSSFCell cell1 = row.createCell((int)1);
	        cell1.setCellValue("报备时间"); 
	        cell1.setCellStyle(xcs);
	        SXSSFCell cell2 = row.createCell((int)2);
	        cell2.setCellValue("办宴时间"); 
	        cell2.setCellStyle(xcs);
	        SXSSFCell cell3 = row.createCell((int)3);  
	        cell3.setCellValue("举办者"); 
	        cell3.setCellStyle(xcs);   
	        SXSSFCell cell4 = row.createCell((int)4);  
	        cell4.setCellValue("举办类型"); 
	        cell4.setCellStyle(xcs);
			SXSSFCell cell5 = row.createCell((int)5);
			cell5.setCellValue("举办者电话"); 
			cell5.setCellStyle(xcs);
			SXSSFCell cell6 = row.createCell((int)6);
			cell6.setCellValue("举办地址"); 
			cell6.setCellStyle(xcs);
			SXSSFCell cell7 = row.createCell((int)7);
			cell7.setCellValue("办宴天数"); 
			cell7.setCellStyle(xcs);
			SXSSFCell cell8 = row.createCell((int)8);
			cell8.setCellValue("就餐人数"); 
			cell8.setCellStyle(xcs);
			SXSSFCell cell9 = row.createCell((int)9);
			cell9.setCellValue("厨师"); 
			cell9.setCellStyle(xcs);   
			SXSSFCell cell10 = row.createCell((int)10);
			cell10.setCellValue("厨师电话"); 
			cell10.setCellStyle(xcs);   
			SXSSFCell cell11 = row.createCell((int)11);
			cell11.setCellValue("健康证"); 
			cell11.setCellStyle(xcs);   
			SXSSFCell cell12 = row.createCell((int)12);
			cell12.setCellValue("报备状态"); 
			cell12.setCellStyle(xcs);   
			SXSSFCell cell13 = row.createCell((int)13);
			cell13.setCellValue("管理状态"); 
			cell13.setCellStyle(xcs);   
			SXSSFCell cell14 = row.createCell((int)14);
			cell14.setCellValue("审核人员"); 
			cell14.setCellStyle(xcs);   
			SXSSFCell cell15 = row.createCell((int)15);
			cell15.setCellValue("现场检查人员"); 
			cell15.setCellStyle(xcs);   
            
	        
            form.setPager_offset(0);
            if(form.getPager_openset()<1) {
            	form.setPager_openset(10000);
            }
            
            if(("举办者").equals(user.getUser_type())) {
				form.setUser_code(user.getUser_code());
			}else if(("乡厨").equals(user.getUser_type()) || ("农家乐").equals(user.getUser_type()) || ("乡村酒店").equals(user.getUser_type())){
				form.setUser_code_mainchef(user.getUser_code());
			}else if(("平台管理员").equals(user.getUser_type()) || ("协管员").equals(user.getUser_type())) {
				if(StringUtils.isEmpty(form.getProvince_conduct())) {
					form.setProvince_conduct(user.getUser_province());
				}
				if(StringUtils.isEmpty(form.getCity_conduct())) {
					form.setCity_conduct( user.getUser_city());
				}
				if(StringUtils.isEmpty(form.getArea_conduct())) {
					form.setArea_conduct(user.getUser_area());
				}
				if(StringUtils.isEmpty(form.getTown_conduct())) {
					form.setTown_conduct(user.getUser_town());
				}
				if(StringUtils.isEmpty(form.getVill_conduct())) {
					form.setVill_conduct(user.getUser_vill());
				}
				form.setReport_full(2);
			}
            
            if(user!=null) {
            	List<Report> reportlist=reportService.findReportUser_Join(form);
    	        if(reportlist!=null&&reportlist.size()>0) {
    	        	for(int i=0;i<reportlist.size();i++) {
    	        		SXSSFRow nextrow = sheet.createRow((int)j+1);
    	    	        SXSSFCell nextcell0 = nextrow.createCell((int)0);
    	    	        nextcell0.setCellType(CellType.NUMERIC);  
    	    	        nextcell0.setCellValue(i+1);
    	    	        nextcell0.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell1 = nextrow.createCell((int)1);
    	    	        nextcell1.setCellType(CellType.STRING);
    	    	        nextcell1.setCellValue(reportlist.get(i).getAddtime()==null?"":reportlist.get(i).getAddtime().substring(0, 19)); 
    	    	        nextcell1.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell2 = nextrow.createCell((int)2);
    	    	        nextcell2.setCellType(CellType.STRING);  
    	    	        nextcell2.setCellValue(reportlist.get(i).getBanquet_time()==null?"":reportlist.get(i).getBanquet_time()); 
    	    	        nextcell2.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell3 = nextrow.createCell((int)3);
    	    	        nextcell3.setCellType(CellType.STRING);  
    	    	        nextcell3.setCellValue(reportlist.get(i).getUser_name_conduct()==null?"":reportlist.get(i).getUser_name_conduct()); 
    	    	        nextcell3.setCellStyle(xcs_f); 
    	    	        SXSSFCell nextcell4 = nextrow.createCell((int)4);
    	    	        nextcell4.setCellType(CellType.STRING); 
    	    	        Integer banquet_type = reportlist.get(i).getBanquet_type()==null?6:reportlist.get(i).getBanquet_type();
    					String banquet_type_str = "";
    					if(banquet_type == 1) {
    						banquet_type_str = "红事";
    					}else if(banquet_type == 2) {
    						banquet_type_str = "白事";
    					}else if(banquet_type == 3) {
    						banquet_type_str = "生日";
    					}else if(banquet_type == 4) {
    						banquet_type_str = "状元";
    					}else if(banquet_type == 5) {
    						banquet_type_str = "乔迁";
    					}else if(banquet_type == 6) {
    						banquet_type_str = "其他";
    					}
    	    	        nextcell4.setCellValue(banquet_type_str); 
    	    	        nextcell4.setCellStyle(xcs_f);
    	    	        
    	    	        SXSSFCell nextcell5 = nextrow.createCell((int)5);
    	    	        nextcell5.setCellType(CellType.STRING);  
    	    	        nextcell5.setCellValue(reportlist.get(i).getUser_mobilephone_conduct()==null?"":reportlist.get(i).getUser_mobilephone_conduct()); 
    	    	        nextcell5.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell6 = nextrow.createCell((int)6);
    	    	        nextcell6.setCellType(CellType.STRING);  
    	    	        nextcell6.setCellValue(reportlist.get(i).getProvince_conduct()+reportlist.get(i).getCity_conduct()+reportlist.get(i).getArea_conduct()+reportlist.get(i).getVill_conduct()+reportlist.get(i).getTown_conduct()); 
    	    	        nextcell6.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell7 = nextrow.createCell((int)7);
    	    	        nextcell7.setCellType(CellType.STRING);  
    	    	        nextcell7.setCellValue(reportlist.get(i).getBanquet_day()==null?"":reportlist.get(i).getBanquet_day()); 
    	    	        nextcell7.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell8 = nextrow.createCell((int)8);
    	    	        nextcell8.setCellType(CellType.STRING);  
	    	        	nextcell8.setCellValue(reportlist.get(i).getBanquet_people()==null?"":reportlist.get(i).getBanquet_people()); 
    	    	        nextcell8.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell9 = nextrow.createCell((int)9);
    	    	        nextcell9.setCellType(CellType.STRING);  
    	    	        nextcell9.setCellValue(reportlist.get(i).getUser_name_mainchef()==null?"":reportlist.get(i).getUser_name_mainchef()); 
    	    	        nextcell9.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell10 = nextrow.createCell((int)10);
    	    	        nextcell10.setCellType(CellType.STRING);  
    	    	        nextcell10.setCellValue(reportlist.get(i).getUser_mobilephone_mainchef()==null?"":reportlist.get(i).getUser_mobilephone_mainchef()); 
    	    	        nextcell10.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell11 = nextrow.createCell((int)11);
    	    	        nextcell11.setCellType(CellType.STRING);  
    	    	        String user_health_logo = reportlist.get(i).getUser_health_logo();
    					if(("images/user_health_logo.png").equals(user_health_logo)) {
    						nextcell11.setCellValue("无");
    					}else {
    						nextcell11.setCellValue("有");
    					}
    	    	        nextcell11.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell12 = nextrow.createCell((int)12);
    	    	        nextcell12.setCellType(CellType.STRING); 
    	    	        Integer report_state = reportlist.get(i).getReport_state();
    					if(report_state == 1) {
    							nextcell12.setCellValue("待审核");
    						}else if(report_state == 2) {
    							nextcell12.setCellValue("无效");
    						}else if(report_state == 3) {
    							nextcell12.setCellValue("待检查");
    						}else if(report_state == 4) {
    							nextcell12.setCellValue("检查合格");
    						}else if(report_state == 5) {
    							nextcell12.setCellValue("检查不合格");
    					}
    	    	        nextcell12.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell13 = nextrow.createCell((int)13);
    	    	        nextcell13.setCellType(CellType.STRING);
    	    	        Integer report_timeout_state = reportlist.get(i).getReport_timeout_state();
    					if(report_timeout_state == 2 && (report_state == 1 || report_state == 3)) {
    						nextcell13.setCellValue("过期");
    					}else {
    						nextcell13.setCellValue("正常");
    					}
    	    	        nextcell13.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell14 = nextrow.createCell((int)14);
    	    	        nextcell14.setCellType(CellType.STRING);  
    	    	        nextcell14.setCellValue(reportlist.get(i).getUser_name_qualified()==null?"":reportlist.get(i).getUser_name_qualified()); 
    	    	        nextcell14.setCellStyle(xcs_f);
    	    	        SXSSFCell nextcell15 = nextrow.createCell((int)15);
    	    	        nextcell15.setCellType(CellType.STRING);  
    	    	        nextcell15.setCellValue(reportlist.get(i).getUser_name_check()==null?"":reportlist.get(i).getUser_name_check()); 
    	    	        nextcell15.setCellStyle(xcs_f);
    	    	        
    	    	        j++;
    	        	}
    	        }
            }
            downLoadExcel(codedFileName, response, sXSSFWorkbook);
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.info(ex.toString());
		}finally{  
			if(user!=null) {
				redisTemplate.opsForValue().set("export_report_search" + user.getUser_loginname(),"open",tokenParam.getDown_time(),TimeUnit.SECONDS); 
			}
		}  
	}
	
	/**
	 * 
	 * @Title: exportReportDetail
	 * @Description: 报备详情导出
	 * @Author tangsh
	 * @DateTime 2020年4月13日 下午5:53:16
	 * @param form
	 * @param request
	 * @param response
	 */
	@GetMapping(value = "/export_report_detail")
	public void exportReportDetail(Report form,ReportSubChef reportSubChef,HttpServletRequest request, HttpServletResponse response) {
	    String codedFileName = "导出报备表";
	    if(!StringUtils.isEmpty(form.getDown_name())) {
	    	codedFileName=form.getDown_name();
	    }
	    User user = TokenUserUtil.generateUser(request, tokenParam);
	    redisTemplate.delete("export_report_detail" + user.getUser_loginname());
		try {
	        SXSSFWorkbook sXSSFWorkbook = new SXSSFWorkbook();
	        SXSSFSheet sheet = sXSSFWorkbook.createSheet("导出报备表");
	        sheet.setColumnWidth(0, 4000);
	        sheet.setColumnWidth(1, 4000);
	        sheet.setColumnWidth(2, 6500);
	        sheet.setColumnWidth(3, 5500);  
	        CellStyle xcs_bt=getStyle(sXSSFWorkbook,3);
	        CellStyle xcs=getStyle(sXSSFWorkbook,4);
	        
	        Report report = reportService.findByCode(form);
	        ReportProcess reportProcess = reportProcessService.findByReportCode(form.getReport_code());
	        if(reportProcess == null) {
	        	reportProcess = new ReportProcess();
	        }
	        
	        SXSSFRow row0 = sheet.createRow((int)0);  
			row0.setHeightInPoints(20);
			SXSSFCell cell0 = row0.createCell((int)0);
			cell0.setCellValue("农村家庭自办群体性宴席申报信息表"); 
			cell0.setCellStyle(xcs_bt);
			sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
			
			SXSSFRow row1 = sheet.createRow((int)1);  
            row1.setHeightInPoints(25);
			SXSSFCell cell1 = row1.createCell((int)0);
			cell1.setCellValue("举办者信息");
			cell1.setCellStyle(xcs);
			sheet.addMergedRegion(new CellRangeAddress(1,1,0,3));
			
			SXSSFRow row2 = sheet.createRow((int)2);  
            row2.setHeightInPoints(25);
			SXSSFCell cell0_r2 = row2.createCell((int)0);
			cell0_r2.setCellValue("举办者姓名"); 
			SXSSFCell cell1_r2 = row2.createCell((int)1);
			cell1_r2.setCellValue(report.getUser_name_conduct()); 
			sheet.addMergedRegion(new CellRangeAddress(2,2,1,3));
			
			SXSSFRow row3 = sheet.createRow((int)3);  
			row3.setHeightInPoints(25);
			SXSSFCell cell0_r3 = row3.createCell((int)0);
			cell0_r3.setCellValue("联系电话"); 
			SXSSFCell cell1_r3 = row3.createCell((int)1);
			cell1_r3.setCellValue(report.getUser_mobilephone_conduct()); 
			sheet.addMergedRegion(new CellRangeAddress(3,3,1,3));
			
			
			SXSSFRow row4 = sheet.createRow((int)4);  
            row4.setHeightInPoints(25);
			SXSSFCell cell0_r4 = row4.createCell((int)0);
			cell0_r4.setCellValue("厨师信息"); 
			cell0_r4.setCellStyle(xcs);
			sheet.addMergedRegion(new CellRangeAddress(4,4,0,3));
			
			SXSSFRow row5 = sheet.createRow((int)5);  
			row5.setHeightInPoints(25);
			SXSSFCell cell0_r5 = row5.createCell((int)0);
			cell0_r5.setCellValue("主厨姓名"); 
			SXSSFCell cell1_r5 = row5.createCell((int)1);
			cell1_r5.setCellValue(report.getUser_name_mainchef()); 
			sheet.addMergedRegion(new CellRangeAddress(5,5,1,3));
			
			SXSSFRow row6 = sheet.createRow((int)6);  
			row6.setHeightInPoints(25);
			SXSSFCell cell0_r6 = row6.createCell((int)0);
			cell0_r6.setCellValue("联系电话"); 
			SXSSFCell cell1_r6 = row6.createCell((int)1);
			cell1_r6.setCellValue(report.getUser_mobilephone_mainchef()); 
			sheet.addMergedRegion(new CellRangeAddress(6,6,1,3));
			
			//查询帮厨
			List<ReportSubChef> reportSubChefList = reportSubChefService.findByList(reportSubChef);
			if(reportSubChefList != null && reportSubChefList.size() > 0) {
				for(int i = 0 ; i < reportSubChefList.size() ; i++) {
					ReportSubChef rsc = reportSubChefList.get(i);
					
					SXSSFRow nextrow = sheet.createRow((int)i+7);
					nextrow.setHeightInPoints(25);
					SXSSFCell nextcell0 = nextrow.createCell((int)0);
					nextcell0.setCellValue("帮厨姓名");
					SXSSFCell nextcell1 = nextrow.createCell((int)1);
					nextcell1.setCellValue(rsc.getUser_name_subchef());
					
					SXSSFCell nextcell2 = nextrow.createCell((int)2);
					nextcell2.setCellValue("健康证");
					SXSSFCell nextcell3 = nextrow.createCell((int)3);
					String user_health_logo_subchef = rsc.getUser_health_logo_subchef();
					if(("images/phone/add_photos_ic.png").equals(user_health_logo_subchef)) {
						nextcell3.setCellValue("无");
					}else {
						nextcell3.setCellValue("有");
					}
				}
			}
			
			//办宴信息
			Integer index = reportSubChefList.size()+1;
			SXSSFRow row7 = sheet.createRow(6+index);
			row7.setHeightInPoints(25);
			SXSSFCell cell0_r7 = row7.createCell((int)0);
			cell0_r7.setCellValue("办宴信息");
			cell0_r7.setCellStyle(xcs);
			sheet.addMergedRegion(new CellRangeAddress(6+index,6+index,0,3));
			
			SXSSFRow row8 = sheet.createRow(7+index);
			row8.setHeightInPoints(20);
			SXSSFCell cell0_r8 = row8.createCell((int)0);
			cell0_r8.setCellValue("举办地点");
			SXSSFCell cell1_r8 = row8.createCell((int)1);
			cell1_r8.setCellValue(report.getProvince_conduct()+report.getCity_conduct()+report.getArea_conduct()+report.getVill_conduct()+report.getTown_conduct());
			sheet.addMergedRegion(new CellRangeAddress(7+index,7+index,1,3));
			
			SXSSFRow row9 = sheet.createRow(8+index);
			row9.setHeightInPoints(20);
			SXSSFCell cell0_r9 = row9.createCell((int)0);
			cell0_r9.setCellValue("办宴时间");
			SXSSFCell cell1_r9 = row9.createCell((int)1);
			cell1_r9.setCellValue(report.getBanquet_time());
			SXSSFCell cell2_r9 = row9.createCell((int)2);
			cell2_r9.setCellValue("就餐人数");
			SXSSFCell cell3_r9 = row9.createCell((int)3);
			cell3_r9.setCellValue(report.getBanquet_people());
			
			SXSSFRow row10 = sheet.createRow(9+index);
			row10.setHeightInPoints(20);
			SXSSFCell cell0_r10 = row10.createCell((int)0);
			cell0_r10.setCellValue("办宴类型");
			SXSSFCell cell1_r10 = row10.createCell((int)1);
			Integer banquet_type = report.getBanquet_type();
			String banquet_type_str = "";
			if(banquet_type == 1) {
				banquet_type_str = "红事";
			}else if(banquet_type == 2) {
				banquet_type_str = "白事";
			}else if(banquet_type == 3) {
				banquet_type_str = "生日";
			}else if(banquet_type == 4) {
				banquet_type_str = "状元";
			}else if(banquet_type == 5) {
				banquet_type_str = "乔迁";
			}else if(banquet_type == 6) {
				banquet_type_str = "其他";
			}
			cell1_r10.setCellValue(banquet_type_str);
			SXSSFCell cell2_r10 = row10.createCell((int)2);
			cell2_r10.setCellValue("办宴天数");
			SXSSFCell cell3_r10 = row10.createCell((int)3);
			cell3_r10.setCellValue(report.getBanquet_day());
			
			//加工场地信息
			SXSSFRow row11 = sheet.createRow(10+index);
			row11.setHeightInPoints(20);
			SXSSFCell cell0_r11 = row11.createCell((int)0);
			cell0_r11.setCellValue("加工场地信息");
			cell0_r11.setCellStyle(xcs);
			sheet.addMergedRegion(new CellRangeAddress(10+index,10+index,0,3));
			
			SXSSFRow row12 = sheet.createRow(11+index);
			row11.setHeightInPoints(20);
			SXSSFCell cell0_r12 = row12.createCell((int)0);
			cell0_r12.setCellValue("使用水源");
			SXSSFCell cell1_r12 = row12.createCell((int)1);
			cell1_r12.setCellValue(reportProcess.getWater_source());
			SXSSFCell cell2_r12 = row12.createCell((int)2);
			cell2_r12.setCellValue("餐具消毒方式");
			SXSSFCell cell3_r12 = row12.createCell((int)3);
			cell3_r12.setCellValue(reportProcess.getTablewater_disinfect());
			
			SXSSFRow row13 = sheet.createRow(12+index);
			row13.setHeightInPoints(20);
			SXSSFCell cell0_r13 = row13.createCell((int)0);
			cell0_r13.setCellValue("留样设施");
			SXSSFCell cell1_r13 = row13.createCell((int)1);
			if(reportProcess.getReservedsample_state() == null) {
				cell1_r13.setCellValue("");
			}else {
				cell1_r13.setCellValue(reportProcess.getReservedsample_state() == 1 ? "有" : "无");
			}
			SXSSFCell cell2_r13 = row13.createCell((int)2);
			cell2_r13.setCellValue("有无贮存和使用有毒有害物品");
			SXSSFCell cell3_r13 = row13.createCell((int)3);
			if(reportProcess.getNarcotics_state() == null) {
				cell3_r13.setCellValue("");
			}else {
				cell3_r13.setCellValue(reportProcess.getNarcotics_state() == 1 ? "无" : "有");
			}
			
			SXSSFRow row14 = sheet.createRow(13+index);
			row14.setHeightInPoints(20);
			SXSSFCell cell0_r14 = row14.createCell((int)0);
			cell0_r14.setCellValue("餐具保洁柜");
			SXSSFCell cell1_r14 = row14.createCell((int)1);
			cell1_r14.setCellValue(reportProcess.getCleancontainer_count() == null ? "" : reportProcess.getCleancontainer_count().toString());
			SXSSFCell cell2_r14 = row14.createCell((int)2);
			cell2_r14.setCellValue("冰箱");
			SXSSFCell cell3_r14 = row14.createCell((int)3);
			cell3_r14.setCellValue(reportProcess.getRefrigerator_count() == null ? "" : reportProcess.getRefrigerator_count().toString());
			
			SXSSFRow row15 = sheet.createRow(14+index);
			row15.setHeightInPoints(20);
			SXSSFCell cell0_r15 = row15.createCell((int)0);
			cell0_r15.setCellValue("专用消毒柜数");
			SXSSFCell cell1_r15 = row15.createCell((int)1);
			cell1_r15.setCellValue(reportProcess.getDisinfectioncabinet_count() == null ? "" : reportProcess.getDisinfectioncabinet_count().toString());
			SXSSFCell cell2_r15 = row15.createCell((int)2);
			cell2_r15.setCellValue("食品留样柜");
			SXSSFCell cell3_r15 = row15.createCell((int)3);
			cell3_r15.setCellValue(reportProcess.getReservedsample_count() == null ? "" : reportProcess.getReservedsample_count().toString());
			
			SXSSFRow row16 = sheet.createRow(15+index);
			row16.setHeightInPoints(20);
			SXSSFCell cell0_r16 = row16.createCell((int)0);
			cell0_r16.setCellValue("垃圾桶带盖");
			SXSSFCell cell1_r16 = row16.createCell((int)1);
			cell1_r16.setCellValue(reportProcess.getGarbage_count() == null ? "" : reportProcess.getGarbage_count().toString());
			SXSSFCell cell2_r16 = row16.createCell((int)2);
			cell2_r16.setCellValue("防鼠、防蝇、防尘设施");
			SXSSFCell cell3_r16 = row16.createCell((int)3);
			cell3_r16.setCellValue(reportProcess.getRatproof_count() == null ? "" : reportProcess.getRatproof_count().toString());
			
			SXSSFRow row17 = sheet.createRow(16+index);
			row17.setHeightInPoints(20);
			SXSSFCell cell0_r17 = row17.createCell((int)0);
			cell0_r17.setCellValue("专用洗菜、洗肉洗鱼池");
			SXSSFCell cell1_r17 = row17.createCell((int)1);
			cell1_r17.setCellValue(reportProcess.getWashvegetable_count() == null ? "" : reportProcess.getWashvegetable_count().toString());
			
			SXSSFRow row18 = sheet.createRow(17+index);
			row18.setHeightInPoints(20);
			
			SXSSFRow row19 = sheet.createRow(18+index);
			row19.setHeightInPoints(20);
			SXSSFCell cell0_r19 = row19.createCell((int)0);
			cell0_r19.setCellValue("乡镇（街道办事处）意见：");
			sheet.addMergedRegion(new CellRangeAddress(17+index,17+index,0,1));
			
			SXSSFRow row20 = sheet.createRow(19+index);
			row20.setHeightInPoints(20);
			SXSSFCell cell0_r20 = row20.createCell((int)2);
			cell0_r20.setCellValue("同意办宴（章）");
			
			SXSSFRow row21 = sheet.createRow(20+index);
			row21.setHeightInPoints(20);
			
			SXSSFRow row22 = sheet.createRow(21+index);
			row22.setHeightInPoints(20);
			SXSSFCell cell0_r22 = row22.createCell((int)0);
			cell0_r22.setCellValue("审核人员：");
			SXSSFCell cell1_r22 = row22.createCell((int)1);
			cell1_r22.setCellValue(report.getUser_name_qualified());
			
			SXSSFRow row23 = sheet.createRow(22+index);
			row23.setHeightInPoints(20);
			SXSSFRow row24 = sheet.createRow(23+index);
			row24.setHeightInPoints(20);
			
			SXSSFRow row25 = sheet.createRow(24+index);
			row25.setHeightInPoints(20);
			SXSSFCell cell0_r25 = row25.createCell((int)2);
			cell0_r25.setCellValue("审核时间：");
			SXSSFCell cell1_r25 = row25.createCell((int)3);
			cell1_r25.setCellValue(report.getQualified_time() == null ? "" : report.getQualified_time().substring(0, 19));
			
			SXSSFRow row26 = sheet.createRow(25+index);
			row26.setHeightInPoints(20);
			
			SXSSFRow row27 = sheet.createRow(26+index);
			row27.setHeightInPoints(20);
			SXSSFCell cell0_r27 = row27.createCell((int)0);
			cell0_r27.setCellValue("申报人签字：");
			
			SXSSFRow row28 = sheet.createRow(27+index);
			row28.setHeightInPoints(20);
			SXSSFRow row29 = sheet.createRow(28+index);
			row29.setHeightInPoints(20);
			
			SXSSFRow row30 = sheet.createRow(29+index);
			row30.setHeightInPoints(20);
			SXSSFCell cell0_r30 = row30.createCell((int)2);
			cell0_r30.setCellValue("报备时间：");
			SXSSFCell cell1_r30 = row30.createCell((int)3);
			cell1_r30.setCellValue(report.getAddtime().substring(0, 19));
			
            downLoadExcel(codedFileName, response, sXSSFWorkbook);
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.info(ex.toString());
		}finally{  
			if(user!=null) {
				redisTemplate.opsForValue().set("export_report_detail" + user.getUser_loginname(),"open",tokenParam.getDown_time(),TimeUnit.SECONDS); 
			}
		}  
	}
	
	
	/**
	 * 
	 * @Title: exportReportCheckSearch
	 * @Description: 报备检查导出
	 * @Author tangsh
	 * @DateTime 2020年4月14日 上午9:28:39
	 * @param form
	 * @param reportSubChef
	 * @param request
	 * @param response
	 */
	@GetMapping(value = "/export_report_check_search")
	public void exportReportCheckSearch(Report form,ReportSubChef reportSubChef,HttpServletRequest request, HttpServletResponse response) {
	    String codedFileName = "导出检查表";
	    if(!StringUtils.isEmpty(form.getDown_name())) {
	    	codedFileName=form.getDown_name();
	    }
	    User user = TokenUserUtil.generateUser(request, tokenParam);
	    redisTemplate.delete("export_report_check_search" + user.getUser_loginname());
		try {
	        SXSSFWorkbook sXSSFWorkbook = new SXSSFWorkbook();
	        SXSSFSheet sheet = sXSSFWorkbook.createSheet("导出检查表");
	        sheet.setColumnWidth(0, 4000);
	        sheet.setColumnWidth(1, 4000);
	        sheet.setColumnWidth(2, 6500);
	        sheet.setColumnWidth(3, 5500);
	        
	        CellStyle xcs_bt=getStyle(sXSSFWorkbook,3);
	        CellStyle xcs=getStyle(sXSSFWorkbook,4);
	        
	        Report report = reportService.findByCode(form);
	        ReportCheckReturn reportCheck = reportCheckService.findByReportCode(form.getReport_code());
	        if(reportCheck == null) {
	        	reportCheck = new ReportCheckReturn();
	        }
	        SXSSFRow row0 = sheet.createRow(0);  
			row0.setHeightInPoints(20);
			SXSSFCell cell0 = row0.createCell((int)0);
			cell0.setCellValue("自办群体性宴席现场检查指导记录"); 
			cell0.setCellStyle(xcs_bt);
			sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
			
			SXSSFRow row1 = sheet.createRow(1);  
            row1.setHeightInPoints(25);
			SXSSFCell cell1 = row1.createCell((int)0);
			cell1.setCellValue("举办者信息");
			cell1.setCellStyle(xcs);
			sheet.addMergedRegion(new CellRangeAddress(1,1,0,3));
			
			SXSSFRow row2 = sheet.createRow(2);  
            row2.setHeightInPoints(25);
			SXSSFCell cell0_r2 = row2.createCell((int)0);
			cell0_r2.setCellValue("举办者姓名"); 
			SXSSFCell cell1_r2 = row2.createCell((int)1);
			cell1_r2.setCellValue(report.getUser_name_conduct()); 
			sheet.addMergedRegion(new CellRangeAddress(2,2,1,3));
			
			SXSSFRow row3 = sheet.createRow(3);  
			row3.setHeightInPoints(25);
			SXSSFCell cell0_r3 = row3.createCell((int)0);
			cell0_r3.setCellValue("联系电话"); 
			SXSSFCell cell1_r3 = row3.createCell((int)1);
			cell1_r3.setCellValue(report.getUser_mobilephone_conduct()); 
			sheet.addMergedRegion(new CellRangeAddress(3,3,1,3));
			
			
			SXSSFRow row4 = sheet.createRow(4);  
            row4.setHeightInPoints(25);
			SXSSFCell cell0_r4 = row4.createCell((int)0);
			cell0_r4.setCellValue("厨师信息"); 
			cell0_r4.setCellStyle(xcs);
			sheet.addMergedRegion(new CellRangeAddress(4,4,0,3));
			
			SXSSFRow row5 = sheet.createRow(5);  
			row5.setHeightInPoints(25);
			SXSSFCell cell0_r5 = row5.createCell((int)0);
			cell0_r5.setCellValue("主厨姓名"); 
			SXSSFCell cell1_r5 = row5.createCell((int)1);
			cell1_r5.setCellValue(report.getUser_name_mainchef()); 
			sheet.addMergedRegion(new CellRangeAddress(5,5,1,3));
			
			SXSSFRow row6 = sheet.createRow(6);  
			row6.setHeightInPoints(25);
			SXSSFCell cell0_r6 = row6.createCell((int)0);
			cell0_r6.setCellValue("联系电话"); 
			SXSSFCell cell1_r6 = row6.createCell((int)1);
			cell1_r6.setCellValue(report.getUser_mobilephone_mainchef()); 
			sheet.addMergedRegion(new CellRangeAddress(6,6,1,3));
			
			//检查信息
			SXSSFRow row7 = sheet.createRow(7);  
			row7.setHeightInPoints(25);
			SXSSFCell cell0_r7 = row7.createCell((int)0);
			cell0_r7.setCellValue("检查信息"); 
			cell0_r7.setCellStyle(xcs);
			sheet.addMergedRegion(new CellRangeAddress(7,7,0,3));
			
			SXSSFRow row8 = sheet.createRow(8);
			row8.setHeightInPoints(20);
			SXSSFCell cell0_r8 = row8.createCell((int)0);
			cell0_r8.setCellValue("主厨个人卫生");
			SXSSFCell cell1_r8 = row8.createCell((int)1);
			cell1_r8.setCellValue(reportCheck.getCheck_mainchef_health() == null ? "" : (reportCheck.getCheck_mainchef_health() == 1 ? "合格" : "不合格"));
			SXSSFCell cell2_r8 = row8.createCell((int)2);
			cell2_r8.setCellValue("帮厨个人卫生");
			SXSSFCell cell3_r8 = row8.createCell((int)3);
			cell3_r8.setCellValue(reportCheck.getCheck_subchef_health() == null ? "" : (reportCheck.getCheck_subchef_health() == 1 ? "合格" : "不合格"));
			
			SXSSFRow row9 = sheet.createRow(9);
			row9.setHeightInPoints(20);
			SXSSFCell cell0_r9 = row9.createCell((int)0);
			cell0_r9.setCellValue("加工场地");
			SXSSFCell cell1_r9 = row9.createCell((int)1);
			cell1_r9.setCellValue(reportCheck.getCheck_process_standard() == null ? "" : (reportCheck.getCheck_process_standard() == 1 ? "合格" : "不合格"));
			SXSSFCell cell2_r9 = row9.createCell((int)2);
			cell2_r9.setCellValue("食品储存");
			SXSSFCell cell3_r9 = row9.createCell((int)3);
			cell3_r9.setCellValue(reportCheck.getCheck_store() == null ? "" : (reportCheck.getCheck_store() == 1 ? "合格" : "不合格"));
			
			SXSSFRow row10 = sheet.createRow(10);
			row10.setHeightInPoints(20);
			SXSSFCell cell0_r10 = row10.createCell((int)0);
			cell0_r10.setCellValue("餐具消毒");
			SXSSFCell cell1_r10 = row10.createCell((int)1);
			cell1_r10.setCellValue(reportCheck.getCheck_disinfection() == null ? "" : (reportCheck.getCheck_disinfection() == 1 ? "合格" : "不合格"));
			SXSSFCell cell2_r10 = row10.createCell((int)2);
			cell2_r10.setCellValue("饮用水");
			SXSSFCell cell3_r10 = row10.createCell((int)3);
			cell3_r10.setCellValue(reportCheck.getCheck_water() == null ? "" : (reportCheck.getCheck_water() == 1 ? "合格" : "不合格"));
			
			SXSSFRow row11 = sheet.createRow(11);
			row11.setHeightInPoints(20);
			SXSSFCell cell0_r11 = row11.createCell((int)0);
			cell0_r11.setCellValue("食品留样");
			SXSSFCell cell1_r11 = row11.createCell((int)1);
			cell1_r11.setCellValue(reportCheck.getCheck_reserved_sample() == null ? "" : (reportCheck.getCheck_reserved_sample() == 1 ? "合格" : "不合格"));
			SXSSFCell cell2_r11 = row11.createCell((int)2);
			cell2_r11.setCellValue("禁用食材");
			SXSSFCell cell3_r11 = row11.createCell((int)3);
			cell3_r11.setCellValue(reportCheck.getCheck_forbidden_food() == null ? "" : (reportCheck.getCheck_forbidden_food() == 1 ? "合格" : "不合格"));
			
			SXSSFRow row12 = sheet.createRow(12);
			row12.setHeightInPoints(20);
			SXSSFCell cell0_r12 = row12.createCell((int)0);
			cell0_r12.setCellValue("食品发票");
			SXSSFCell cell1_r12 = row12.createCell((int)1);
			cell1_r12.setCellValue(reportCheck.getCheck_invoice() == null ? "" : (reportCheck.getCheck_invoice() == 1 ? "合格" : "不合格"));
			SXSSFCell cell2_r12 = row12.createCell((int)2);
			cell2_r12.setCellValue("有毒有害物质");
			SXSSFCell cell3_r12 = row12.createCell((int)3);
			cell3_r12.setCellValue(reportCheck.getCheck_poison() == null ? "" : (reportCheck.getCheck_poison() == 1 ? "合格" : "不合格"));
			
			SXSSFRow row13 = sheet.createRow(13);
			row13.setHeightInPoints(20);
			SXSSFCell cell0_r13 = row13.createCell((int)0);
			cell0_r13.setCellValue("现场照片");
			SXSSFCell cell1_r13 = row13.createCell((int)1);
			cell1_r13.setCellValue(reportCheck.getCheck_imgurls() == null ? "无" : "有");
			SXSSFCell cell2_r13 = row13.createCell((int)2);
			cell2_r13.setCellValue("风险点");
			SXSSFCell cell3_r13 = row13.createCell((int)3);
			cell3_r13.setCellValue(reportCheck.getCheck_risk());
			
			SXSSFRow row14 = sheet.createRow(14);
			row14.setHeightInPoints(20);
			SXSSFCell cell0_r14 = row14.createCell((int)0);
			cell0_r14.setCellValue("风险点整改情况：");
			SXSSFCell cell1_r14 = row14.createCell((int)1);
			cell1_r14.setCellValue(reportCheck.getCheck_risk_suggestion());
			sheet.addMergedRegion(new CellRangeAddress(13,13,1,3));
			
			SXSSFRow row15 = sheet.createRow(15);
			row15.setHeightInPoints(20);
			SXSSFRow row16 = sheet.createRow(16);
			row16.setHeightInPoints(20);
			
			SXSSFRow row17 = sheet.createRow(17);
			row16.setHeightInPoints(20);
			SXSSFCell cell0_r17 = row17.createCell((int)0);
			cell0_r17.setCellValue("检查人员：");
			SXSSFCell cell1_r17 = row17.createCell((int)1);
			cell1_r17.setCellValue(report.getUser_name_check() == null ? "" : report.getUser_name_check());
			
			SXSSFRow row18 = sheet.createRow(18);
			row18.setHeightInPoints(20);
			SXSSFRow row19 = sheet.createRow(19);
			row19.setHeightInPoints(20);
			
			SXSSFRow row20 = sheet.createRow(20);
			row20.setHeightInPoints(20);
			SXSSFCell cell0_r20 = row20.createCell((int)2);
			cell0_r20.setCellValue("检查时间：");
			SXSSFCell cell1_r20 = row20.createCell((int)3);
			cell1_r20.setCellValue(report.getCheck_time() == null ? "" : report.getCheck_time().substring(0,19));
			
			SXSSFRow row21 = sheet.createRow(21);
			row21.setHeightInPoints(20);
			SXSSFRow row22 = sheet.createRow(22);
			row22.setHeightInPoints(20);
			
			
			SXSSFRow row23 = sheet.createRow(23);
			row23.setHeightInPoints(20);
			SXSSFCell cell0_r23 = row23.createCell((int)0);
			cell0_r23.setCellValue("申报人签字：");
			
			SXSSFRow row24 = sheet.createRow(24);
			row24.setHeightInPoints(20);
			SXSSFRow row25 = sheet.createRow(25);
			row25.setHeightInPoints(20);
			
			SXSSFRow row26 = sheet.createRow(26);
			row26.setHeightInPoints(20);
			SXSSFCell cell0_r26 = row26.createCell((int)2);
			cell0_r26.setCellValue("报备时间：");
			SXSSFCell cell1_r26 = row26.createCell((int)3);
			cell1_r26.setCellValue(report.getAddtime().substring(0,19));
	       
            
            downLoadExcel(codedFileName, response, sXSSFWorkbook);
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.info(ex.toString());
		}finally{  
			if(user!=null) {
				redisTemplate.opsForValue().set("export_report_check_search" + user.getUser_loginname(),"open",tokenParam.getDown_time(),TimeUnit.SECONDS); 
			}
		}  
	}
	
	//******************************************公共方法区域**************************//
	
	/**
	 * 
	 * @Title: getStyle
	 * @Description: 设置单元格字体样式
	 * @Author tangsh
	 * @DateTime 2020年2月24日 上午10:58:43
	 * @param sSXSSFWorkbook 工作簿
	 * @param s_num 样式类型 允许NULL
	 * @return
	 */
	private static CellStyle getStyle(SXSSFWorkbook sXSSFWorkbook,Integer s_num) {
		CellStyle cellStyle = sXSSFWorkbook.createCellStyle();
		Font font = sXSSFWorkbook.createFont(); 
		if(s_num!=null) {
			switch (s_num) {
			case 1:
				cellStyle.setAlignment(HorizontalAlignment.CENTER); 
				cellStyle.setBorderBottom(BorderStyle.THIN); 
				cellStyle.setBorderLeft(BorderStyle.THIN);
				cellStyle.setBorderTop(BorderStyle.THIN);
				cellStyle.setBorderRight(BorderStyle.THIN);
				cellStyle.setWrapText(true);
				   
				font.setFontName("宋体");    
				font.setBold(true);
				font.setFontHeightInPoints((short) 11);
		        cellStyle.setFont(font);
				break;
			case 2:
				cellStyle.setAlignment(HorizontalAlignment.CENTER); 
				cellStyle.setWrapText(true);
				   
				font.setFontName("仿宋");    
				font.setFontHeightInPoints((short) 14);
		        cellStyle.setFont(font);
				break;
			case 3:
				cellStyle.setAlignment(HorizontalAlignment.CENTER); 
				cellStyle.setWrapText(true);
				font.setFontName("仿宋");    
				font.setFontHeightInPoints((short) 14);
				font.setBold(true);
				cellStyle.setFont(font);
				break;
			case 4:
				cellStyle.setWrapText(true);
				font.setFontName("仿宋");    
				font.setFontHeightInPoints((short) 14);
				font.setBold(true);
				cellStyle.setFont(font);
				
				break;
			case 5:
				cellStyle.setAlignment(HorizontalAlignment.LEFT); 
				cellStyle.setBorderBottom(BorderStyle.THIN); 
				cellStyle.setBorderLeft(BorderStyle.THIN);
				cellStyle.setBorderTop(BorderStyle.THIN);
				cellStyle.setBorderRight(BorderStyle.THIN);
				cellStyle.setWrapText(true);

				font.setFontName("仿宋");    
				font.setFontHeightInPoints((short) 14);
				cellStyle.setFont(font);
				break;
			default:
				break;
			}
		}else {
			cellStyle.setAlignment(HorizontalAlignment.LEFT); 
			cellStyle.setBorderBottom(BorderStyle.THIN); 
			cellStyle.setBorderLeft(BorderStyle.THIN);
			cellStyle.setBorderTop(BorderStyle.THIN);
			cellStyle.setBorderRight(BorderStyle.THIN);
			cellStyle.setWrapText(true);
			   
			font.setFontName("宋体");    
			font.setBold(false);
			font.setFontHeightInPoints((short) 11);
	        cellStyle.setFont(font);
		}
		return cellStyle;
	}
	
	/**
	 * 
	 * @Title: getCompanyTagName
	 * @Description: 企业类型
	 * @Author tangsh
	 * @DateTime 2020年3月23日 上午10:28:48
	 * @param code
	 * @return
	 */
	private static String getCompanyTagName(String code) {
		if(code.equals("1001574645421581111111101")) {
			return "四川省小作坊备案证";
		}else if(code.equals("1001574645421581111111102")) {
			return "四川省小经营店备案证（流通）";
		}else if(code.equals("1001574645421581111111103")) {
			return "食品经营许可证（餐饮）";
		}else if(code.equals("1001574645421581111111104")) {
			return "四川省小经营店备案证（餐饮）";
		}else {
			return "食品经营许可证（流通）";
		}
	}
	
	/**
	 * 
	 * @Title: downLoadExcel
	 * @Description: 统一下载方法
	 * @Author tangsh
	 * @DateTime 2020年3月23日 上午11:27:42
	 * @param fileName
	 * @param response
	 * @param SXSSFWorkbook
	 */
	public static void downLoadExcel(String fileName, HttpServletResponse response, Workbook sxssfWorkbook) {
		OutputStream fOut = null;
		try {
			response.setCharacterEncoding("UTF-8");
			response.setHeader("content-Type", "application/vnd.ms-excel");
			response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xls");
			fOut = response.getOutputStream();  
			sxssfWorkbook.write(fOut);
		} catch (IOException e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}finally{  
			try{
				fOut.flush();  
				fOut.close();  
			}catch(IOException e){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		}
	}
	
	
	/**
	 * 
	 * @Title: getKeyOrNull
	 * @Description: 获取map中第一个key值
	 * @Author tangsh
	 * @DateTime 2020年2月24日 上午11:30:26
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unused")
	private static String getFirstKey(Map<String, Object> map) {
    	String obj = "";
        for (Entry<String, Object> entry : map.entrySet()) {
            obj = entry.getKey();
            if (obj != null) {
                break;
            }
        }
        return  obj;
    }
	
	/**
	 * 
	 * @Title: getFirstOrNull
	 * @Description: 获取map中第一个数据值
	 * @Author tangsh
	 * @DateTime 2020年2月24日 上午11:30:51
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unused")
	private static Object getFirstValue(Map<String, Object> map) {
		Object obj = null;
        for (Entry<String, Object> entry : map.entrySet()) {
            obj = entry.getValue();
            if (obj != null) {
                break;
            }
        }
        return  obj;
    }	
	
	/**
	 * 
	 * @Title: check
	 * @Description: 用于关闭输出流和重复提交
	 * @Author tangsh
	 * @DateTime 2020年2月24日 上午10:37:41
	 * @param down_name 下载的方法名词
	 * @param request
	 * @param response
	 * @return
	 */
	@GetMapping(value = "/check")  
	public Object check(String down_name,HttpServletRequest request,HttpServletResponse response){ 
		Boolean flag = false;
		CODEMSG = SUCCESS;
		User user = TokenUserUtil.generateUser(request, tokenParam);
		if(user==null){
			CODEMSG = USER_UNDEFINED;
			return finish(CODEMSG,null);
		}
		String _open = (String)redisTemplate.opsForValue().get(down_name + user.getUser_loginname());
		if(!StringUtils.isEmpty(_open)){
			flag = true;
			redisTemplate.delete(down_name + user.getUser_loginname());
		}
		return finish(CODEMSG, flag);
	}
}
