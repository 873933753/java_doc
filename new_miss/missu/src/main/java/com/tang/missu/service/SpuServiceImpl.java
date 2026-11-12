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

    /* 根据cid查列表：排序已写在方法名 OrderByCreateTimeDesc 里，Pageable 不再重复带 Sort */
    @Override
    public Page<Spu> getLatestPagingSpuByCategoryId(Long cId,Boolean isRoot, Integer pageNum, Integer size) {
        Pageable page = PageRequest.of(pageNum,size);
        // 如果是root的根据root查，如果不是root的分类，根据cid查
        Page<Spu> spus = null;
        if (isRoot) {
            spus = this.spuRepository.findByRootCategoryIdOrderByCreateTimeDesc(cId,page);
        }else{
            spus = this.spuRepository.findByCategoryIdOrderByCreateTimeDesc(cId,page);
        }
//        Page<Spu> spus = this.spuRepository.findByCategoryIdOrderByCreateTimeDesc(cId,page);
        return spus;
    }
}
