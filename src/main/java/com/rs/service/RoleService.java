package com.rs.service;

import com.rs.dao.IMenuDao;
import com.rs.dao.IRoleDao;
import com.rs.dao.IRoleMenuDao;
import com.rs.po.Menu;
import com.rs.po.Role;
import com.rs.po.RoleMenu;
import com.rs.po.User;
import com.rs.util.CodeUtil;
import com.rs.util.RegularUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 
 * @ClassName: RoleService
 * @Description: 角色服务层
 * @Author tangsh
 * @DateTime 2019年12月18日 上午11:48:38
 */
@Service
public class RoleService extends BaseService<Role>{
	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private IRoleMenuDao roleMenuDao;
	@Autowired
	private IMenuDao menuDao;
	
	@Override
	public int save(Role t) throws ParseException {
		String role_code=CodeUtil.getSystemCode("role");
		t.setRole_code(role_code);
		Integer result=roleDao.save(t);
		if(result>0) {
			if(t.getMenu_list()!=null&&t.getMenu_list().size()>0) {
				RoleMenu rm=null;
				List<RoleMenu> rmList=new ArrayList<RoleMenu>();
				for(Menu m:t.getMenu_list()) {
					rm=new RoleMenu();
					rm.setRole_code(role_code);
					rm.setMenu_code(m.getMenu_code());
					rm.setMenu_btns(RegularUtil.getListToString(m.getMenu_btns_list(),","));
					rmList.add(rm);
				}
				roleMenuDao.saveBatch(rmList);
			}
		}
		return result;
	}
	
	public Role single(Role t) {
		t=roleDao.findByCode(t);
		List<Menu> mlist=menuDao.findMenuByRole(t.getRole_code());
		if(mlist!=null&&mlist.size()>0) {
			RoleMenu rm=null;
			for(Menu m:mlist) {
				rm=new RoleMenu();
				rm.setMenu_code(m.getMenu_code());
				rm.setRole_code(t.getRole_code());
				m.setMenu_btns_list(RegularUtil.getStringToList(roleMenuDao.findButtonByTwoCode(rm),","));
			}
		}
		t.setMenu_list(mlist);
		return t;
	}
	
	@Override
	public int update(Role t) {
		Integer result=roleDao.update(t);
		if(result>0) {
			roleDao.deleteRoleMenu(t);
			
			String role_code=t.getRole_code();
			if(t.getMenu_list()!=null&&t.getMenu_list().size()>0) {
				RoleMenu rm=null;
				List<RoleMenu> rmList=new ArrayList<RoleMenu>();
				for(Menu m:t.getMenu_list()) {
					rm=new RoleMenu();
					rm.setRole_code(role_code);
					rm.setMenu_code(m.getMenu_code());
					rm.setMenu_btns(RegularUtil.getListToString(m.getMenu_btns_list(),","));
					rmList.add(rm);
				}
				roleMenuDao.saveBatch(rmList);
			}
		}
		return result;
	}
	
	
	@Override
	public int delete(Role t) {
		roleDao.deleteUserRole(t);
		roleDao.deleteRoleMenu(t);
		return roleDao.delete(t);
	}
	
	
	public List<Menu> findByMenu() {
		return roleDao.findByMenu();
	}
	
	public List<RoleMenu> findByRoleMenu(String role_code){
		return roleDao.findByRoleMenu(role_code);
	}
	
	public List<User> findByRoleUserList(Role t){
		return roleDao.findByRoleUserList(t);
	}
	
	public Integer findByRoleUserCount(Role t) {
		return roleDao.findByRoleUserCount(t);
	}
	
	public Integer deleteRoleUser(Map<String,String> map) {
		return roleDao.deleteRoleUser(map);
	}
	
	public Integer saveRoleUser(Map<String,String> map) {
		return roleDao.saveRoleUser(map);
	}
}
