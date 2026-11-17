package com.tang.missu.respoitory;

import com.tang.missu.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
//    Category findAll();
    // 根据isRoot查几级分类，且按排序升序
    List<Category> findCategoriesByIsRootOrderByIndexAsc(Boolean isRoot);
}
