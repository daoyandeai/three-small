package com.rs.service;

import com.rs.po.Category;
import com.rs.po.returnPo.CategoryReturn;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService extends BaseService<Category>{

    public List<CategoryReturn> all() {
        List<CategoryReturn> list = new ArrayList<>();
        List<Category> all = findByAll(new Category());
        for (Category category:
                all) {
            CategoryReturn cr = new CategoryReturn();
            BeanUtils.copyProperties(category,cr);
            list.add(cr);
        }
        return list;
    }
}
