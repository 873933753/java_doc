package com.tang.missu.service;

import com.tang.missu.model.Spu;
import com.tang.missu.respoitory.SpuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<Spu> getLatestPagingSpu(Integer pageNum, Integer size) {
        // 构建Page对象，sql查询还是在findAll中
        Pageable page = PageRequest.of(pageNum,size, Sort.by("createTime").descending());
        Page<Spu> spus = this.spuRepository.findAll(page);
        return spus;
    }
}
