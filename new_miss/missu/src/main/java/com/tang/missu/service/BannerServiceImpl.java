package com.tang.missu.service;

import com.tang.missu.model.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tang.missu.respoitory.BannerRepository;


// BannerService接口的实现,这里代码无论如何修改不会影响BannerController中的代码
@Service // 要打在实现类上
public class BannerServiceImpl implements BannerService {
    // 注入repositry层
    @Autowired
    private BannerRepository bannerRepository;

    @Override
    public Banner getByName (String name) {
        Banner banner = bannerRepository.findOneByName(name);
        return banner;
    }
}
