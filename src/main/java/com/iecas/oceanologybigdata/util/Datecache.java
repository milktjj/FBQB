package com.iecas.oceanologybigdata.util;

import org.apache.ibatis.cache.CacheKey;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.impl.copy.ReadWriteCopier;
import org.ehcache.spi.copy.Copier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Datecache implements org.apache.ibatis.cache.Cache {
    private static final Logger logger = LoggerFactory.getLogger(Datecache.class);
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final String id; // cache instance id
    private static CacheManager cacheManager;

    public Datecache(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object o, Object o1) {
        /*if(o == null)
            logger.info("Object1");
        if (o1 == null)
            logger.info("Object2");
        logger.info(o.toString());
        logger.info(o1.toString());*/
        getCache().put(o, FBQBUtils.copy(o1));
        logger.info("PUT into CACHE" + o.toString());
    }

    @Override
    public Object getObject(Object o) {
        logger.info("Get form CACHE" + o.toString());
        return FBQBUtils.copy(getCache().get(o));
    }

    @Override
    public Object removeObject(Object o) {
        Object v = getCache().get(o);
        getCache().remove(o);
        logger.info("REMOVE OBJECT" + o.toString() + "!!!");
        return v;
    }

    @Override
    public void clear() {
        getCache().clear();
        logger.info("CLEAR CACHE!!!");
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }


    public Cache getCache() {
        if (cacheManager == null) {
            cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                    .withCache("preConfigured",
                            CacheConfigurationBuilder.newCacheConfigurationBuilder(CacheKey.class,
                                    ArrayList.class, ResourcePoolsBuilder.heap(100))).
                            build();
            cacheManager.init();
        }
        return cacheManager.getCache("preConfigured", CacheKey.class, ArrayList.class);
    }

}