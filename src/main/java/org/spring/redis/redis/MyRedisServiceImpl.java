package org.spring.redis.redis;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class MyRedisServiceImpl implements MyRedisService {

    private final Jedis jedis;

    @Override
    public <T> T hSet(String key, T t) {
        jedis.hset(key, hSetHelper(t));
        return this.hGetAll(key);
    }

    @Override
    public <T> T hGetAll(String key) {
        return null;
    }

    @Override
    public <F> F hGet(String key, String hashKey, Class<F> uClass) {
        return null;
    }

    private <T> Map<String, String> hSetHelper(T t) {
        Map<String, String> setMap = new HashMap<>();
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(t);
                setMap.put(field.getName(), value.toString());
            } catch (IllegalAccessException illegalAccessException) {
                log.error("illegalAccessError = ", illegalAccessException);
            }
        }
        return setMap;
    }

    private <T> T hGetAllHelper(String key) {
        //  key 로 hash 정보를 가져온다.
        //  리플렉션을 이용해 객체를 만들어 반환한다.
        return null;
    }

    private <F> F hGetHelper(String key, String hashKey, Class<F> fClass) {
        //  key값을 이용해 redis 에서 hash 정보를 가져온다
        //  가져온 값중에서 field 값을 가져온다
        return null;
    }


}

