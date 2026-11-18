package com.tang.missu.respoitory;

import com.tang.missu.model.GridCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GridCategoryRepository extends JpaRepository<GridCategory,Long>{
    List<GridCategory> findGridCategoriesByOrderByRootCategoryIdAsc();
}
