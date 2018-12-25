package com.iecas.oceanologybigdata.Redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class RedisUtils {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);


    /**
     * 是否存在key
     *
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
                logger.info("\033[47;31mDEL from REDIS \033[5m" + key[0] + " !!!");
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
                logger.info("DEL from REDIS " + CollectionUtils.arrayToList(key) + " !!!");

            }
        }
    }

    /**
     * 获取value
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        logger.info("\033[47;31mGET FROM REDIS, key: \033[5m" + key + " !!!");
        return key == null ?
                null : redisTemplate.opsForValue().get(key);
    }

    public boolean set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
        logger.info("SET TO REDIS, key: " + key + " !!!");
        return true;
    }


}