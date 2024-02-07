package org.spring.redis.redis;

public interface MyRedisService {
    <T> T hSet(String key, T t) throws ClassNotFoundException;    //  hash 를 저장한다.

    <T> T hGetAll(String key, Class<T> type) throws ClassNotFoundException;  //  hash 에 모든 정보를 가져온다.

    <F> F hGet(String key, String hashKey, Class<F> uClass);    //  hash 안에 특정 필드를 가져온다

}


