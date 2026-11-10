package com.tang.missu.api.v1;


import com.github.dozermapper.core.DozerBeanMapper;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.tang.missu.bo.PageCounter;
import com.tang.missu.exception.http.NotFoundException;
import com.tang.missu.model.Spu;
import com.tang.missu.service.SpuService;
import com.tang.missu.utils.CommonUtil;
import com.tang.missu.vo.Paging;
import com.tang.missu.vo.PagingDozer;
import com.tang.missu.vo.SpuSimplifyVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/spu")
@Validated
public class SpuController {

    /* 注册SpuService */
    @Autowired
    SpuService spuService;

    /* 商品详情 */
    /*
    * @PathVariable - 获取路径上的参数
    * @Positive + @Validated - 验证参数正数
    * */
    @GetMapping("/id/{id}/detail")
    public Spu getDetail(@PathVariable @Positive Long id){
        Spu spu = spuService.getSpu(id);
        if (spu == null){
            throw new NotFoundException(30003);
        }
        return spu;
    }

    /* 商品详情 - 数据简化 */
    @GetMapping("/id/{id}/simplify")
    public SpuSimplifyVO getSimplifyDetail(@PathVariable @Positive Long id){
        Spu spu = this.spuService.getSpu(id);
        SpuSimplifyVO vo = new SpuSimplifyVO();
        if (spu == null){
            throw new NotFoundException(30003);
        }
        // 将属性的值拷贝到vo中
        BeanUtils.copyProperties(spu,vo);
        return vo;
    }

    /* 商品列表 */
    @GetMapping("/latest?start= & count =")
    public List<SpuSimplifyVO> getLatestSpuList(@RequestParam(defaultValue = "0") Integer start,@RequestParam(defaultValue = "20") Integer count){
        // 数据源
        PageCounter pageCounter= CommonUtil.convertToPageParameter(start,count);
        Page<Spu> spuList = this.spuService.getLatestPagingSpu(pageCounter.getPage(),pageCounter.getSize());
        // 目标 -vo
        List<SpuSimplifyVO> vos = new ArrayList<>();
        // mapper
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        // 将spuList中的各项拷贝到vos中
        spuList.forEach( spu -> {
            SpuSimplifyVO vo = mapper.map(spu,SpuSimplifyVO.class);
            vos.add(vo);
        });

        return vos;
    }

    /* 商品列表-分页 */
    @GetMapping("/latestPaging")
    public PagingDozer<Spu,SpuSimplifyVO> getLatestSpuListPaging(@RequestParam(defaultValue = "0") Integer start,@RequestParam(defaultValue = "20") Integer count){
        // 数据源
        PageCounter pageCounter= CommonUtil.convertToPageParameter(start,count);
        Page<Spu> page = this.spuService.getLatestPagingSpu(pageCounter.getPage(),pageCounter.getSize());
        // 数据处理
//        Paging<Spu> paging = new Paging<>(spuList);

//        // 目标 -vo
//        List<SpuSimplifyVO> vos = new ArrayList<>();
//        // mapper
//        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
//        // 将spuList中的各项拷贝到vos中
//        paging.getItems().forEach( spu -> {
//            SpuSimplifyVO vo = mapper.map(spu,SpuSimplifyVO.class);
//            vos.add(vo);
//        });
//
//        return vos;

//        PagingDozer<Spu,SpuSimplifyVO> pagingDozer = new PagingDozer<>(page,SpuSimplifyVO.class);
//        return  pagingDozer;
          return new PagingDozer<>(page,SpuSimplifyVO.class); // 泛型可以被返回推断出
    }

    /* 根据分类id查spu列表 */
    @GetMapping("/by/category/{id}")
    public String getByCategoryIdList(){
        return "sss";
    }
}
