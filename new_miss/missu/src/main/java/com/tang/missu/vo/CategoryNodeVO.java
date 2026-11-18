package com.tang.missu.vo;

import com.tang.missu.model.Category;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

// 用于tree节点分类
@Getter
public class CategoryNodeVO extends CategoryPureVO{
    private List<CategoryNodeVO> children = new ArrayList<>();

    public CategoryNodeVO(Category category) {
        // 从父类继承基础属性
        super(category);
    }

    // 添加children
    public void addChild(CategoryNodeVO child) {
        this.children.add(child);
    }
}
