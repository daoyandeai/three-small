package com.rs.service;

import com.rs.dao.INewsDao;
import com.rs.po.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * @ClassName: NewsService
 * @Description: 新闻宣传服务层
 * @Author tangsh
 * @DateTime 2019年12月16日 下午3:10:30
 */
@Service
public class NewsService extends BaseService<News>{

    @Autowired
    private INewsDao newsDao;

    public List<News> findBySearch(News form) {return newsDao.findBySearch(form);
    }

    public int findBySearchCount(News form) {return newsDao.findBySearchCount(form);
    }
}
