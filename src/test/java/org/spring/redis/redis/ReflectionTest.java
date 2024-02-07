package org.spring.redis.redis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.spring.redis.user.TestInfo;
import org.spring.redis.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReflectionTest {


    @Autowired
    MyRedisService myRedisService;

    @Test
    public void jedisTest() throws ClassNotFoundException {

        UserInfo userInfo =
                UserInfo.builder()
                        .name("홍길동")
                        .password("1q2w3e4r!")
                        .email("ghdrlfehd@gmail.com")
                        .build();

        TestInfo testInfo =
                TestInfo.builder()
                        .val1("val1")
                        .val2("val2")
                        .build();

        UserInfo saveUserInfo = myRedisService.hSet("user", userInfo);
        TestInfo saveTestInfo = myRedisService.hSet("test", testInfo);

        UserInfo findUser = myRedisService.hGetAll("user", UserInfo.class);
        TestInfo findTest = myRedisService.hGetAll("test", TestInfo.class);

        Assertions.assertEquals(saveUserInfo,findUser);
        Assertions.assertEquals(saveTestInfo,findTest);

    }

}
