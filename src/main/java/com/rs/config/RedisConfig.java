package com.rs.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
/**
 * 处理redis中文乱码配置
 * @ClassName: RedisConfig
 * @Description: 
 * @Author sven
 * @DateTime 2019年7月11日 下午4:38:33
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport{
	@SuppressWarnings("rawtypes")
	@Autowired
    private RedisTemplate redisTemplate;
	/**
     * RedisTemplate配置
     * @param factory
     * @return
     */
	@Bean
	@SuppressWarnings({ "rawtypes", "unchecked" })
    public RedisTemplate redisTemplateInit() {
        //设置序列化Key的实例化对象
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置序列化Value的实例化对象
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        //System.out.println("redisTemplate----"+redisTemplate);
        return redisTemplate;
    }

}
