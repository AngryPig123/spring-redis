package org.spring.redis.redis;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class MyRedisServiceImpl implements MyRedisService {

    private final String CLASS_INFO = "classInfo";

    private final Jedis jedis;

    @Override
    public <T> T hSet(String key, T t) throws ClassNotFoundException {
        long result = jedis.hset(key, hSetHelper(t));   //  hSet을 해주는 부분.
        if (result <= 0L) {
            return null;    //  저장이 안되면 null 반환
        } else {
            return t;   //  저장되면 t 반환
        }
    }

    @Override
    public <T> T hGetAll(String key, Class<T> type) throws ClassNotFoundException {

        Map<String, String> stringStringMap = jedis.hgetAll(key);
        String classInfo = stringStringMap.get(CLASS_INFO);

        Class<T> aClass = (Class<T>) Class.forName(classInfo);

        T instance = null;

        try {
            Constructor<?>[] constructors = aClass.getDeclaredConstructors();

            for (Constructor<?> constructor : constructors) {
                if (stringStringMap.size() - 1 == constructor.getParameterTypes().length) {

                    Class<?>[] paramTypes = constructor.getParameterTypes(); // 생성자의 매개변수 타입을 가져와야 함
                    Object[] args = new Object[paramTypes.length];
                    int index = 0;
                    for (String paramName : stringStringMap.keySet()) {
                        if (!paramName.equals(CLASS_INFO)) {
                            args[index++] = stringStringMap.get(paramName); // 매개변수 값 설정
                        }
                    }
                    instance = (T) constructor.newInstance(args); // 새로운 인스턴스 생성
                    break; // 적절한 생성자를 찾았으므로 루프 종료
                }
            }

            if (instance == null) {
                return null;
            }

            for (Field field : aClass.getDeclaredFields()) {
                field.setAccessible(true);  //  접근 제한 해제
                String fieldName = field.getName();
                String mapValue = stringStringMap.get(fieldName);
                if (mapValue != null) {
                    field.set(instance, mapValue);
                }
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            log.error("exception = ", e);
            return null;
        }

        return instance;
    }


    @Override
    public <F> F hGet(String key, String hashKey, Class<F> uClass) {
        return null;
    }

    private <T> Map<String, String> hSetHelper(T t) {

        Map<String, String> setMap = new HashMap<>();
        setClassInfoToHash(t, setMap);

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

    //  전달받은 클래스 정보를 셋팅해주기 위한 메소드.
    private <T> void setClassInfoToHash(T t, Map<String, String> setMap) {
        setMap.put(CLASS_INFO, t.getClass().toString().replace("class ", ""));
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
