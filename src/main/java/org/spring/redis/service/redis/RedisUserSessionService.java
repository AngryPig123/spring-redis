package org.spring.redis.service.redis;

import org.spring.redis.entity.redis.RedisUserSession;

public interface RedisUserSessionService {
    RedisUserSession saveRedisUserSession(RedisUserSession redisUserSession);
}
