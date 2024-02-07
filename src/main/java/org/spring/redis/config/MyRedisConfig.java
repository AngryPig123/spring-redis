package org.spring.redis.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;

@Slf4j
@Configuration
public class MyRedisConfig {

    @Value("${data.redis.host}")
    private String HOST;

    @Value("${data.redis.port}")
    private int PORT;

    @Value("${data.redis.maxTotal}")
    private int MAX_TOTAL;

    @Value("${data.redis.maxIdle}")
    private int MAX_IDLE;

    @Value("${data.redis.minIdle}")
    private int MIN_IDLE;

    @Value("${data.redis.maxWaitMillis}")
    private long MAX_WAIT_MILLIS;

    @Bean("myJedisPool")
    public JedisPool myJedisPool() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setJmxEnabled(false);
        poolConfig.setMaxTotal(MAX_TOTAL);
        poolConfig.setMaxIdle(MAX_IDLE);
        poolConfig.setMinIdle(MIN_IDLE);
        poolConfig.setMaxWaitMillis(MAX_WAIT_MILLIS);

        try {
            return new JedisPool(poolConfig, HOST, PORT);
        } catch (Exception e) {
            log.error("Error initializing Jedis pool", e);
            return null;
        }
    }

    @Bean
    public Jedis jedis(@Qualifier("myJedisPool") JedisPool jedisPool) {
        try (
                Jedis jedis = jedisPool.getResource();
        ) {
            return jedis;
        } catch (JedisException jedisException) {
            log.error("jedis bean registration fail", jedisException);
            return null;
        }
    }

}
