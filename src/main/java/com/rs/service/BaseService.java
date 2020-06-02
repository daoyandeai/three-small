package com.rs.service;
import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rs.dao.IBaseDao;
import com.rs.po.BasePo;
@Service
public abstract class BaseService<T extends BasePo<T>>{
	@Autowired
	private IBaseDao<T> baseDao;
	
	public int save(T t) throws ParseException {
		int result = baseDao.save(t);
		return result;
	}
	
	public int saveBatch(T t) {
		int result = baseDao.saveBatch(t.getPager_list());
		return result;
	}
	
	public int updateBatch(T t) {
		int result = baseDao.updateBatch(t.getPager_list());
		return result;
	}
	
	public int delete(T t) {
		int result = baseDao.delete(t);
		return	result;		
	}

	public int update(T t) {
		int result = baseDao.update(t);
		return	result;
	}

	public List<T> findByAll(T t) {
		List<T> datalist = baseDao.findByAll(t);
		return datalist;
	}
	
	public int findByCount(T t) {
		return baseDao.findByCount(t);
	}
	
	public List<T> findByList(T t) {
		List<T> datalist = baseDao.findByList(t);
		return datalist;
	}
	
	public T findByCode(T t) {
		t = baseDao.findByCode(t);
		return t;
	}
	
	public int findByExist(T t) {
		return baseDao.findByExist(t);
	}
}
