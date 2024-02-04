package org.spring.redis.entity.redis;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@RedisHash(value = "user")
public class RedisUserSession {

    @Id
    private String hash;

    private String userId;

    private String password;

    private LocalDateTime signIn;

    @Builder
    public RedisUserSession(String userId, String password) {
        this.userId = userId;
        this.password = password;
        this.signIn = LocalDateTime.now();
    }

}
