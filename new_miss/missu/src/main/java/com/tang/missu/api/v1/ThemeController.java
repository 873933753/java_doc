package com.tang.missu.api.v1;


import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.tang.missu.exception.http.NotFoundException;
import com.tang.missu.model.Theme;
import com.tang.missu.service.ThemeService;
import com.tang.missu.vo.ThemePureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/theme")
public class ThemeController {

    @Autowired
    private ThemeService themeService;


    /* 根据前端传的names返回对应主题数据 */
    @GetMapping("/by/names")
    public List<ThemePureVO> getThemeGroupByNames(@RequestParam(name = "names") String names){
        // 处理参数：names = 1,2,3
        List<String> nameList = Arrays.asList(names.split(","));
        List<Theme> themes = themeService.findByNames(nameList);
        // 数据经 VO处理
        List<ThemePureVO> list = new ArrayList<>();
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        themes.forEach(t -> {
            ThemePureVO vo = mapper.map(t,ThemePureVO.class);
            list.add(vo);
        });
        return list;
    }

    /* 获取具体主题并带spuList */
    @GetMapping("/name/{name}/with_spu")
    public Theme getThemeByNameWithSpu(@PathVariable(name = "name") String themeName){
        Optional<Theme> optionalTheme = this.themeService.findByName(themeName);
        // 为空时用 orElseThrow 给出有意义的异常
        return optionalTheme.orElseThrow(()-> new NotFoundException(30003));
//        return themeName;
    }
}
