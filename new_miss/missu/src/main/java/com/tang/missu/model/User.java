package com.tang.missu.model;

import com.tang.missu.utils.MapAndJson;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "user")
@Builder // 可以直接给成员变量赋值 - 用户存用户
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "delete_time is null") // 只查未删的用户
public class User extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int UNSIGNED not null")
    private Long id;

    @Size(max = 50)
    @Column(name = "openid", length = 50)
    private String openid;

    @Size(max = 60)
    @Column(name = "nickname", length = 60)
    private String nickname;

    @Column(name = "unify_uid")
    private Long unifyUid;

    @Size(max = 255)
    @Column(name = "email")
    private String email;

    @Size(max = 255)
    @Column(name = "password")
    private String password;

    @Size(max = 30)
    @Column(name = "mobile", length = 30)
    private String mobile;

//    @Lob
//    @Column(name = "wx_profile")
//    private String wxProfile;

    //    private String group; 用户分组 - 这里暂时不做

    // 用户的微信信息
    @Convert(converter = MapAndJson.class)
    @Column(name = "wx_profile")
    private Map<String,Object> wxProfile;



}