package com.tang.missu.respoitory;

import com.tang.missu.model.Spu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpuRepository extends JpaRepository<Spu,Long> {
    // 根据id查spu
    Spu findOneById(Long id);

    // 根据categoryId查spu，按创建时间倒序
    Page<Spu> findByCategoryIdOrderByCreateTimeDesc(Long cid, Pageable pageable);

    // 根据rootId查spu
    Page<Spu> findByRootCategoryIdOrderByCreateTimeDesc(Long cid,Pageable pageable);
}
