package org.spring.redis.redis;

public interface MyRedisService {
    <T> T hSet(String key, T t);    //  hash 를 저장한다.

    <T> T hGetAll(String key);  //  hash 에 모든 정보를 가져온다.

    <F> F hGet(String key, String hashKey, Class<F> uClass);    //  hash 안에 특정 필드를 가져온다

}


