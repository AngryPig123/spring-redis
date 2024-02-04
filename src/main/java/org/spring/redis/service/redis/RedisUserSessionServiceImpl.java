package org.spring.redis.service.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.spring.redis.entity.redis.RedisUserSession;
import org.spring.redis.repository.redis.RedisUserSessionRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisUserSessionServiceImpl implements RedisUserSessionService {

    private final RedisUserSessionRepository redisUserSessionRepository;

    @Override
    public RedisUserSession saveRedisUserSession(RedisUserSession redisUserSession) {
        RedisUserSession result = redisUserSessionRepository.save(redisUserSession);
        log.info("result = {}", result);
        return result;
    }

}
