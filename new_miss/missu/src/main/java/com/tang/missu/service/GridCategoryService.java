package com.tang.missu.service;

import com.tang.missu.model.GridCategory;
import com.tang.missu.respoitory.GridCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GridCategoryService {
    @Autowired
    private GridCategoryRepository gridCategoryRepository;

    public List<GridCategory> getGridCategoryList(){
        // 根据 root_category_id 升序排列
        return gridCategoryRepository.findGridCategoriesByOrderByRootCategoryIdAsc();
    }
}
