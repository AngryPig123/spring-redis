package org.spring.redis.redis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.spring.redis.entity.redis.RedisUserSession;
import org.spring.redis.service.redis.RedisUserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisRepositoryTest {

    @Autowired
    RedisUserSessionService redisUserSessionService;

    @Test
    public void redis_user_session_set_test() {
        RedisUserSession redisUserSession = RedisUserSession.builder()
                .userId("홍길동")
                .password("1q2w3e4r!")
                .build();
        RedisUserSession session = redisUserSessionService.saveRedisUserSession(redisUserSession);
        Assertions.assertNotNull(session);
    }

}
