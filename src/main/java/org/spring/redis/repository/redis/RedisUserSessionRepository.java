package org.spring.redis.repository.redis;

import org.spring.redis.entity.redis.RedisUserSession;
import org.springframework.data.repository.CrudRepository;


public interface RedisUserSessionRepository extends CrudRepository<RedisUserSession, String> {
}
