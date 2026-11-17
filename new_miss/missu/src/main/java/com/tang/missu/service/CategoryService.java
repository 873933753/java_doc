package com.tang.missu.service;


import com.tang.missu.model.Category;
import com.tang.missu.respoitory.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Map<Integer, List<Category>> getAll(){
        /* 这里查两次，一次一级分类，一次二级分类 */
        List<Category> roots = this.categoryRepository.findCategoriesByIsRootOrderByIndexAsc(true);
        List<Category> subs = this.categoryRepository.findCategoriesByIsRootOrderByIndexAsc(false);
        // 返回所有的一级和二级分类
        Map<Integer,List<Category>> categories = new HashMap<>();
        categories.put(1,roots);
        categories.put(2,subs);
        return categories;
    }
}
