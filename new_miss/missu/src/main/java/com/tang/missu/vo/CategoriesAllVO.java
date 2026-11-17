package com.tang.missu.vo;

import com.tang.missu.model.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


// Service中的结果映射
@Getter
@Setter
public class CategoriesAllVO {
    private List<CategoryPureVO> roots;
    private List<CategoryPureVO> subs;

    public CategoriesAllVO(Map<Integer,List<Category>> map){
        // 处理两个数组
        this.roots = map.get(1).stream()
                .map(r -> {return new CategoryPureVO(r);})
                .collect(Collectors.toList());
        // 函数式编程
        this.subs = map.get(2).stream()
                .map(CategoryPureVO::new)
                .collect(Collectors.toList());
    }
}
