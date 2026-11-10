package com.tang.missu.service;

import com.tang.missu.model.Spu;
import com.tang.missu.respoitory.SpuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SpuServiceImpl implements SpuService{
    /* 注入SpuRepository */
    @Autowired
    SpuRepository spuRepository;

    @Override
    public Spu getSpu(Long id) {
        Spu spu = this.spuRepository.findOneById(id);
        return spu;
    }

    /* 查列表 */
    @Override
    public List<Spu> getLatestPagingSpu() {
        List<Spu> spus = this.spuRepository.findAll();
        return spus;
    }
}
