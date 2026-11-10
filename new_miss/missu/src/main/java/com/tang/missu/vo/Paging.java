package com.tang.missu.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

// 这里传入泛型，因为Paging是公共的封装，可能是Page<Spu> Page<Sku>
@Getter
@Setter
@NoArgsConstructor
public class Paging<T> {
    // 分页数据基本结构
    private Integer page; // 当前页码
    private Integer count; // 条数
    private Integer totalPage;// 总页数
    private Long total; //总条数
    // 列表数据
    List<T> items;

    //返回给前端的数据处理
    public Paging(Page<T> pageT) {
        this.initPageParameters(pageT);
        this.items = pageT.getContent();
    }

    // 获取分页数据
    void initPageParameters(Page<T> pageT) {
        // 以下方法都是Page对象内置的方法
        this.page = pageT.getNumber();
        this.count = pageT.getSize();
        this.totalPage = pageT.getTotalPages();
        this.total = pageT.getTotalElements();
    }
}
