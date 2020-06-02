package com.rs.service;

import com.rs.dao.IDictionaryDao;
import com.rs.po.Dictionary;
import com.rs.po.returnPo.DictionaryReturn;
import com.rs.po.returnPo.OpenDictionaryReturn;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
/**
 * 字典服务层
 * @ClassName: DictionaryService
 * @Description: 
 * @Author sven
 * @DateTime 2020年3月9日 上午11:00:12
 */
@Service
public class DictionaryService extends BaseService<Dictionary>{
    @Autowired
    private IDictionaryDao dictionaryDao;

    public Map<String ,Map<String,List<Dictionary>>> search(Dictionary form) {
        List<Dictionary> dictionarylist = new ArrayList<>();
        Map<String ,Map<String,List<Dictionary>>> map = new HashMap<>();
        //条件查询
        dictionarylist =  dictionaryDao.findBySearch(form);
        if(dictionarylist.size()>0){
            Iterator<Dictionary> iterator = dictionarylist.iterator();
            while (iterator.hasNext()){
            	
                Dictionary next = iterator.next();
                String module = next.getDictionary_module();
                //判断模块是否存在
                /*      数据结构：
                 * 		modlue ->  list<name> -> list<filed>
                 * */
                if(map.containsKey(module)){
                    //判断下拉标题是否存在
                    if(map.get(module).containsKey(next.getDictionary_name())){
                        map.get(module).get(next.getDictionary_name()).add(next);
                        //不存在则添加下拉标题框和下拉框内容
                    }else{
                        List<Dictionary> list = new ArrayList<Dictionary>();
                        //存放所有下拉数据
                        list.add(next);
                        //将下拉数据放在下拉标题框下
                        map.get(module).put(next.getDictionary_name(),list);
                    }
                    //如果不存在则添加
                }else {
                    List<Dictionary> list = new ArrayList<Dictionary>();
                    Map<String,List<Dictionary>> temp = new HashMap<>();
                    //存放所有下拉数据
                    list.add(next);
                    //存放下拉标题框
                    temp.put(next.getDictionary_name(),list);
                    //存放下拉所属模块
                    map.put(module,temp);
                }
            }
            
            
        }
        return map;
    }
    
    public Map<String ,Map<String,List<OpenDictionaryReturn>>> openSearch(Dictionary form) {
        List<Dictionary> dictionarylist = new ArrayList<>();
        Map<String ,Map<String,List<OpenDictionaryReturn>>> map = new HashMap<>();
        //条件查询
        form.setState(1);
        dictionarylist =  dictionaryDao.findBySearch(form);
        if(dictionarylist.size()>0){
        	OpenDictionaryReturn odr=null;
            Iterator<Dictionary> iterator = dictionarylist.iterator();
            while (iterator.hasNext()){
            	
                Dictionary next = iterator.next();
                String module = next.getDictionary_module();
                odr=new OpenDictionaryReturn();
                //判断模块是否存在
                /*      数据结构：
                 * 		modlue ->  list<name> -> list<filed>
                 * */
                if(map.containsKey(module)){
                    //判断下拉标题是否存在
                    if(map.get(module).containsKey(next.getDictionary_name())){
                    	BeanUtils.copyProperties(next, odr);
                        map.get(module).get(next.getDictionary_name()).add(odr);
                        //不存在则添加下拉标题框和下拉框内容
                    }else{
                        List<OpenDictionaryReturn> list = new ArrayList<OpenDictionaryReturn>();
                        //存放所有下拉数据
                        BeanUtils.copyProperties(next, odr);
                        list.add(odr);
                        //将下拉数据放在下拉标题框下
                        map.get(module).put(next.getDictionary_name(),list);
                    }
                    //如果不存在则添加
                }else {
                    List<OpenDictionaryReturn> list = new ArrayList<OpenDictionaryReturn>();
                    Map<String,List<OpenDictionaryReturn>> temp = new HashMap<>();
                    //存放所有下拉数据
                    BeanUtils.copyProperties(next, odr);
                    list.add(odr);
                    //存放下拉标题框
                    temp.put(next.getDictionary_name(),list);
                    //存放下拉所属模块
                    map.put(module,temp);
                }
            }
            
            
        }
        return map;
    }
    
    public List<DictionaryReturn> findByList_app(Dictionary form){
		return dictionaryDao.findByList_app(form);
	}
	
	public DictionaryReturn findByCode_app(Dictionary form){
		return dictionaryDao.findByCode_app(form);
	}
    
	
	public List<Dictionary> findByAll(Dictionary form) {
		return dictionaryDao.findBySearch(form);
	}
}
