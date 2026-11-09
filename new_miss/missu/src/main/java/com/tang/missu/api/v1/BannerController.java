package com.tang.missu.api.v1;

import com.tang.missu.model.Banner;
import com.tang.missu.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banner")
public class BannerController {

    /* 注入 */
    @Autowired
    private BannerService bannerService;

    @GetMapping("name/{name}")
    public Banner test(@PathVariable(name = "name") String name){
//        return bannerService.getByName(name);
        return bannerService.getByName(name);
    }

}
