package com.rs.dao;

import com.rs.po.Company;
import com.rs.po.Department;
import com.rs.po.Menu;
import com.rs.po.User;
import com.rs.po.returnPo.UserReturn;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * 
 * @ClassName: IUserDao
 * @Description: 用户数据接口层
 * @Author tangsh
 * @DateTime 2019年12月18日 上午10:20:09
 */
@Repository
public interface IUserDao extends IBaseDao<User> {
	/**
	 * 
	 * @Title: findByLoginNameAndPass
	 * @Description: 根据用户登陆账号密码查询用户
	 * @Author tangsh
	 * @DateTime 2020年3月16日 下午6:01:11
	 * @param form
	 * @return
	 */
    User findByLoginNameAndPass(User form);

    /**
     * 
     * @Title: login
     * @Description: 根据登陆信息查询用户数据
     * @Author tangsh
     * @DateTime 2020年3月16日 下午6:01:32
     * @param form
     * @return
     */
    User login(User form);
    /**
     * 
     * @Title: findLoginMenu
     * @Description: 根据用户查询用户菜单权限
     * @Author tangsh
     * @DateTime 2020年3月16日 下午6:01:46
     * @param form
     * @return
     */
    List<Menu> findLoginMenu(User form);
    /**
     * 
     * @Title: findLoginMenuButton
     * @Description: 查询用户角色菜单按钮
     * @Author tangsh
     * @DateTime 2020年2月25日 下午2:09:30
     * @param form
     * @return
     */
    String findLoginMenuButton(User form);

    /**
     * 
     * @Title: findBySearch
     * @Description: 查询用户列表
     * @Author tangsh
     * @DateTime 2020年3月16日 下午6:02:07
     * @param form
     * @return
     */
    List<User> findBySearch(User form);
    
    /**
     * 
     * @Title: findByUserCol
     * @Description: 赛选列查询用户信息
     * @Author tangsh
     * @DateTime 2020年3月16日 下午6:02:32
     * @param form
     * @return
     */
    List<String> findByUserCol(User form);

    /**
     * 
     * @Title: findBySearchCount
     * @Description: 查询用户条目数
     * @Author tangsh
     * @DateTime 2020年3月16日 下午6:02:48
     * @param form
     * @return
     */
    Integer findBySearchCount(User form);
    /**
     * 根据监管部门查询信息员列表
     * @Title: findByDept
     * @Description: 
     * @Author sven
     * @DateTime 2020年1月9日 下午5:19:52
     * @param department_code
     * @return
     */
    List<UserReturn> findByDept(String department_code);
    
    /**
     * 
     * @Title: updateByLoginName
     * @Description: 根据用户名修改密码
     * @Author tangsh
     * @DateTime 2020年3月16日 下午6:00:25
     * @param form
     * @return
     */
    Integer updateByLoginName(User form);
    
    /**
     * 
     * @Title: findUserByCompanyList
     * @Description: 根据企业查询用户
     * @Author tangsh
     * @DateTime 2020年3月16日 下午5:58:39
     * @param form
     * @return
     */
    List<User> findUserByCompanyList(Company form);
    
    /**
     * 
     * @Title: findByCompanyCode
     * @Description: 根据企业查询一条用户
     * @Author tangsh
     * @DateTime 2020年3月16日 下午5:58:55
     * @param company_code
     * @return
     */
    User findByCompanyCode(String company_code);
    
    /**
     * 
     * @Title: findByGisUserList
     * @Description: 多表联查真实网格员列表
     * @Author tangsh
     * @DateTime 2020年3月16日 下午5:58:34
     * @param form
     * @return
     */
    List<UserReturn> findByGisUserList(User form);
    /**
     * 
     * @Title: findByGisUserCount
     * @Description: 多表联查真实网格员总数
     * @Author tangsh
     * @DateTime 2020年3月20日 下午4:25:52
     * @param form
     * @return
     */
    Integer findByGisUserCount(User form);
    
    /**
     * 
     * @Title: findDepartmentByUserCode
     * @Description: 根据用户编码查询所在监管部门 limit 1
     * @Author tangsh
     * @DateTime 2020年3月17日 下午2:48:29
     * @param user_code
     * @return
     */
    Department findDepartmentByUserCode(String user_code);
    /**
     * 查询参与培训的用户
     * @Title: findByPXUserGroupAddress
     * @Description: 
     * @Author sven
     * @DateTime 2020年3月23日 下午2:26:38
     * @param form
     * @return
     */
    List<User> findTrainUserGroupAddress(User form);
    /**
     * 查询不同培训状态的用户
     * @Title: findTrainStateUserGroupAddress
     * @Description: 
     * @Author sven
     * @DateTime 2020年3月23日 下午2:48:46
     * @param form
     * @return
     */
    List<User> findTrainStateUserGroupAddress(User form);
    
    /**
     * 
     * @Title: findBySortList
     * @Description: 查询乡厨用户列表
     * @Author tangsh
     * @DateTime 2020年4月7日 下午4:55:09
     * @param form
     * @return
     */
    List<User> findBySortList(User form);
    
    /**
     * 
     * @Title: findByHealthCount
     * @Description: 查询乡厨健康证
     * @Author tangsh
     * @DateTime 2020年4月7日 下午5:14:18
     * @param form
     * @return
     */
    Integer findByHealthCount(User form);
    
    /**
     * 根据报备查询乡厨对应的历史举办者列表
     * @Title: findListInnerReport
     * @Description: 
     * @Author sven
     * @DateTime 2020年4月16日 上午11:06:27
     * @param form
     * @return
     */
    List<User> findListInnerReport(User form);
    /**
     * 根据报备查询乡厨对应的历史举办者数量
     * @Title: findListInnerReport
     * @Description: 
     * @Author sven
     * @DateTime 2020年4月16日 上午11:06:27
     * @param form
     * @return
     */
    Integer findCountInnerReport(User form);
    /**
     * 根据字段符合查询
     * @Title: findByColumn
     * @Description: 
     * @Author sven
     * @DateTime 2020年4月16日 下午2:35:08
     * @param form
     * @return
     */
    User findByColumn(User form);
}
