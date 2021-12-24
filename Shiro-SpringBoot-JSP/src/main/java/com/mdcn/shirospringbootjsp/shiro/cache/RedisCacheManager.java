package com.mdcn.shirospringbootjsp.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * @description: 自定义的Shiro缓存管理器 -> Redis缓存管理器
 * @author: Medicine
 * @createTime: 2021-12-24 20:33
 */
public class RedisCacheManager implements CacheManager {

    /**
     * @param cacheName 认证或者是授权缓存的统一名称
     * @return 缓存键值对
     * @throws CacheException 缓存错误
     */
    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
        // s = authorizationCache
        System.out.println("cacheName=" + cacheName);
        return new RedisCache<K,V>(cacheName);
    }
}
