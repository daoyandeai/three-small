package com.rs.service;
import com.rs.dao.IMessDao;
import com.rs.po.Mess;
import com.rs.po.returnPo.MessReturn;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 平台消息推送服务层
 * @ClassName: MessService
 * @Description: 
 * @Author sven
 * @DateTime 2020年2月25日 下午5:54:16
 */
@Service
public class MessService extends BaseService<Mess> {
	@Autowired
	private IMessDao messDao;
	
	
	public MessReturn findByEvent(Mess form){
		return messDao.findByEvent(form);
	}
	
	public List<MessReturn> findByList_app(Mess form){
		return messDao.findByList_app(form);
	}
	
	public List<MessReturn> findByAll_app(Mess form){
		return messDao.findByList_app(form);
	}
	
	public MessReturn findByCode_app(Mess form){
		return messDao.findByCode_app(form);
	}
}
