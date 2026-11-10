package com.tang.missu.service;

import com.tang.missu.model.Spu;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface SpuService {
    Spu getSpu(Long id);
    Page<Spu> getLatestPagingSpu(Integer pageNum,Integer size);
}
