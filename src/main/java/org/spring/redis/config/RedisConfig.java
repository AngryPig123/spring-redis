package org.spring.redis.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

@Slf4j
@Configuration
public class RedisConfig {


    @Value("${data.redis.host}")
    private String HOST;

    @Value("${data.redis.port}")
    private int PORT;


    @Bean("jedisTemplate")
    public Jedis jedisTemplate() {
        Jedis jedis = null;
        try (JedisPool jedisPool = new JedisPool(HOST, PORT)) {
            jedis = jedisPool.getResource();
            log.info("get jedis");
        } catch (JedisException jedisException) {
            log.error("jedis bean registration fail = ", jedisException);
        }
        return jedis;
    }


}
