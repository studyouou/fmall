package org.og.fmall.commontools.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author:ougen
 * @date:2019/9/2515:17
 */
@Configuration
@EnableConfigurationProperties(OwnRedisProperties.class)
@ConditionalOnClass(JedisPool.class)
public class RedissonAutoConfiguration {
    private static Logger logger = LoggerFactory.getLogger(RedissonAutoConfiguration.class);

    @Autowired
    private OwnRedisProperties ownRedisProperties;

    @Bean
    public JedisPool redisClient(){
        logger.info("jedisPool is Initial");
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(ownRedisProperties.getMaxIdle());
        poolConfig.setMinIdle(ownRedisProperties.getMinIdle());
        poolConfig.setMaxWaitMillis(ownRedisProperties.getMaxWait());
        JedisPool pool = new JedisPool(poolConfig,ownRedisProperties.getAddress(),ownRedisProperties.getPort()
                                        ,ownRedisProperties.getTimeout(),ownRedisProperties.getPassword());
        return pool;
    }
}
