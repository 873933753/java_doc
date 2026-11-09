package com.tang.missu.service;


import com.tang.missu.model.Banner;

/* service层写业务逻辑 */
/* BannerService接口 */
public interface BannerService {
    Banner getByName(String name);
}
