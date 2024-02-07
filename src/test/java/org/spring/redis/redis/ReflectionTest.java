package org.spring.redis.redis;

import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.spring.redis.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@SpringBootTest
public class ReflectionTest {


    @Autowired
    MyRedisService myRedisService;

    @Test
    public void jedisTest() {
        UserInfo userInfo =
                UserInfo.builder()
                        .name("홍길동")
                        .password("1q2w3e4r!")
                        .email("ghdrlfehd@gmail.com")
                        .build();
        myRedisService.hSet("user", userInfo);
    }

}
