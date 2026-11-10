package com.tang.missu.api.v1;


import com.tang.missu.exception.http.NotFoundException;
import com.tang.missu.model.Spu;
import com.tang.missu.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/spu")
@Validated
public class SpuController {

    /* 注册SpuService */
    @Autowired
    SpuService spuService;

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

    /* 商品列表 */
    @GetMapping("/latest")
    public List<Spu> getLatestSpuList(){
        return this.spuService.getLatestPagingSpu();
    }
}
