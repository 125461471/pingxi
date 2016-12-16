package com.heibaba;

import java.lang.reflect.Method;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableCaching
public class CacheConfig  extends CachingConfigurerSupport {
	
    @Bean
    public CacheManager cacheManager(RedisTemplate<?,?> redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        // Number of seconds before expiration. Defaults to unlimited (0s)
        cacheManager.setDefaultExpiration(0); //设置key-value超时时间,默认值0表示永不过期
        return cacheManager;
    }
	
	/**
	 * key
	 * 生成策略：包体_方法名_参数名
	 * @return
	 */
    @Bean
    public KeyGenerator shlKeyGenerator() {
    	
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append("_"+method.getName());
                //sb.append("_"+obj.toString());
                ObjectMapper objectMapper = new  ObjectMapper();
                for (Object obj : params) {
                	try {
						sb.append("_"+objectMapper.writeValueAsString(obj));
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					};
                }
                return sb.toString();
            }
        };
    }
}
