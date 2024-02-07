package org.spring.redis;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import redis.clients.jedis.Jedis;

@RequiredArgsConstructor
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, ThymeleafAutoConfiguration.class})
public class RedisApplication {


    public static void main(String[] args) {

        SpringApplication.run(RedisApplication.class, args);
    }


}
