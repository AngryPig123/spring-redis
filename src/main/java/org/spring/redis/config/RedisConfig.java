package org.spring.redis.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Slf4j
@Configuration
public class RedisConfig {


    @Value("${data.redis.host}")
    private String HOST;

    @Value("${data.redis.port}")
    private int PORT;


    @Bean("sessionRedisConnectionsFactory")
    public RedisConnectionFactory sessionRedisConnectionsFactory() {
        return redisConnectionFactory(HOST, PORT);
    }

    @Bean("sessionRedisTemplate")
    public RedisTemplate<String, Object> sessionRedisTemplate() {
        return returnRedisTemplate(sessionRedisConnectionsFactory());
    }

    private RedisConnectionFactory redisConnectionFactory(final String host, final int port) {
        return new LettuceConnectionFactory(host, port);
    }

    private RedisTemplate<String, Object> returnRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));

        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));

        redisTemplate.setDefaultSerializer(new Jackson2JsonRedisSerializer<>(Object.class));

        return redisTemplate;
    }


}
