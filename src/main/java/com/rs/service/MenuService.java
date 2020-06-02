package com.rs.service;

import com.rs.dao.IMenuButtonDao;
import com.rs.dao.IMenuDao;
import com.rs.po.Menu;
import com.rs.po.MenuButton;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 
 * @ClassName: MenuService
 * @Description: 菜单服务层
 * @Author tangsh
 * @DateTime 2019年12月18日 上午11:17:14
 */
@Service
public class MenuService  extends BaseService<Menu>{
	@Autowired
	private IMenuDao menuDao;
	@Autowired
	private IMenuButtonDao menuButtonDao;
	
	public List<Menu> findMenuAndButtonAll(Menu t){
		List<Menu> mlist=menuDao.findByAll(t);
		if(mlist!=null&&mlist.size()>0) {
			MenuButton mb=null;
			for(Menu m:mlist) {
				mb=new MenuButton();
				mb.setMenu_code(m.getMenu_code());
				m.setMenubutton_list(menuButtonDao.findByAll(mb));
			}
		}
		return mlist;
	}
}
