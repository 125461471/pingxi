package com.heibaba;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


@Configuration
@EnableRedisHttpSession
@EnableRedisRepositories
public class RedisConfig {
	
	@Value("${spring.redis.host}")
	private String host;
	@Value("${spring.redis.port}")
	private int port;
	@Value("${spring.redis.database}")
	private int database;
	@Value("${spring.redis.password}") 
	private String password;
	@Value("${spring.redis.timeout}")
	private int timeout;
	@Value("${spring.redis.pool.max-active}")
	private int  maxActive;
	@Value("${spring.redis.pool.max-idle}")
	private int  maxIdle;
	@Value("${spring.redis.pool.max-wait}")
	private long maxWait;
	@Value("${spring.redis.pool.min-idle}")
	private int minIdle;
	
	@Bean
	public JedisPool redisPoolFactory() {
		
		JedisPoolConfig jedisPoolConfig = getJedisPoolConfig();
		JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password, database);

		return jedisPool;
	}
	
    private JedisPoolConfig getJedisPoolConfig() {
    	
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(maxActive);
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMaxWaitMillis(maxWait);
		jedisPoolConfig.setMinIdle(minIdle);
		
		return jedisPoolConfig;
	}
    
	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(host);
		factory.setPort(port);
		factory.setDatabase(database);
		factory.setPassword(password);	
		factory.setTimeout(timeout);
		
		return factory;
	}

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
    	
        StringRedisTemplate template = new StringRedisTemplate(factory);
        setSerializer(template); //设置序列化工具
        
        return template;
    }
    
    private void setSerializer(StringRedisTemplate template) {
    	
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
    	ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
    }

}
