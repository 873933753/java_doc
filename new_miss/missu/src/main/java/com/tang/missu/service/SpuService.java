package com.tang.missu.service;

import com.tang.missu.model.Spu;

import java.util.List;

public interface SpuService {
    Spu getSpu(Long id);
    List<Spu> getLatestPagingSpu();
}
