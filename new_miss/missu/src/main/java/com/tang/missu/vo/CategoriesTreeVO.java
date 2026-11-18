package com.tang.missu.vo;

import com.tang.missu.model.Category;
import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
public class CategoriesTreeVO {
    private List<CategoryNodeVO> roots;

    public CategoriesTreeVO(Map<Integer, List<Category>> map) {
        List<Category> roots = map.get(1);
        List<Category> subs = map.get(2);

        // 第一遍：一级分类建索引，LinkedHashMap 保证输出顺序
        Map<Long, CategoryNodeVO> nodes = new LinkedHashMap<>();
        for (Category root : roots) {
            nodes.put(root.getId(), new CategoryNodeVO(root));
        }

        // 第二遍：二级分类按 parentId 挂上去
        for (Category sub : subs) {
            CategoryNodeVO parent = nodes.get(sub.getParentId());
            if (parent != null) {
                parent.addChild(new CategoryNodeVO(sub));
            }
        }

        this.roots = new ArrayList<>(nodes.values());
    }
}
