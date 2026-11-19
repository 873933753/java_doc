package com.tang.missu.respoitory;

import com.tang.missu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    // 因为User可能不存在，所以用Optional。
    Optional<User> findUserByOpenid(String openid);

    // 暂时未用到
    User findByEmail(String email);
    User findFirstById(Long id);
    User findByUnifyUid(Long uuid);
}
