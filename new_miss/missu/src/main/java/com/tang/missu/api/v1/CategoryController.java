package com.tang.missu.api.v1;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.tang.missu.model.Category;
import com.tang.missu.service.CategoryService;
import com.tang.missu.vo.CategoriesAllVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /*@GetMapping("/all")
    public List<CategoriesAllVO> getAll(){
        List<Category> categories =  this.categoryService.getAll();
        List<CategoriesAllVO> vos = new ArrayList<>();
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        categories.forEach(category -> {
            CategoriesAllVO vo = mapper.map(category, CategoriesAllVO.class);
            vos.add(vo);
        });
        return vos;
    }*/

    @GetMapping("/all")
    public CategoriesAllVO getAll(){
        Map<Integer,List<Category>> categories = categoryService.getAll();
        return new CategoriesAllVO(categories);
    }


}
