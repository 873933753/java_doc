package com.tang.missu.respoitory;

import com.tang.missu.model.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
// JpaRepository里传入泛型，第一个参数是要操作的实体，第二个参数是实体的主键id类型
public interface BannerRepository extends JpaRepository<Banner,Long> {
    Banner findOneByName(String name);
//Banner findFirstByName(String name);
//    List<Banner> findByName(String name);
}
