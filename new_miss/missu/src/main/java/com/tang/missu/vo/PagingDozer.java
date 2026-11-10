package com.tang.missu.vo;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;


/* 如果不需要精简属性用Paging对象即可，如果需要精简属性需要使用PagingDozer */
/* T -代表源，K代表目标 */
public class PagingDozer<T,K> extends Paging {

//    @SuppressWarnings("unchecked") // 忽略警告
    public PagingDozer(Page<T> pageT,Class<K> classK) {
        // 调用Paging的基本属性赋值
        this.initPageParameters(pageT);
        // items属性复制 - Mapper
        List<T> items = pageT.getContent();
        // vo
        List<K> vos = new ArrayList<>();
        // mapper
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        // 将spuList中的各项拷贝到vos中
        items.forEach( t -> {
            // 目标源类不能直接K.class，通过构造函数传
            K vo = mapper.map(t,classK);
            vos.add(vo);
        });
        // 需要将数据set进去
        this.setItems(vos);
    }
}
