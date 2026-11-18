package com.tang.missu.respoitory;

import com.tang.missu.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ThemeRepository extends JpaRepository<Theme,Long> {
    List<Theme> findThemesByOrderByIdDesc();

    // JPA 中用 @Query 自定义 JPQL（操作的是Model不是数据库表） 查询的方法声明。
    // 适用于不能用命名的方式推断的时候用
    // 这里的 Theme 代表 Theme模型不是表名
    // from Theme t  -- 从 Theme 实体查询，并给别名 t
    @Query("select t from Theme t where t.name in (:names1)")
    List<Theme> findByNames(@Param("names1") List<String> names);

    // 不写Quer也可以写成
//    List<Theme> findByNameIn(List<String> names);

    // 使用了Optional -> 表示"可能没有结果"
    Optional<Theme> findByName(String name);
}
