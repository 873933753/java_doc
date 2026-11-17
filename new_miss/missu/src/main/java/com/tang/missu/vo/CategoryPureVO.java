package com.tang.missu.vo;


import com.tang.missu.model.Category;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class CategoryPureVO {
    private Long id;
    private String name;
    private Short isRoot;
    private Long parentId;
    private String img;
    private Long index;

    public CategoryPureVO(Category category){
        // 属性的拷贝，将 Category 拷贝到 CategoryPureVO
        BeanUtils.copyProperties(category,this);
    }
}
