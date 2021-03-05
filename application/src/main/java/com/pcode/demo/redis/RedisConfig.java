package com.pcode.demo.redis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;


@Configuration
@ConditionalOnExpression("${pcode.redis.enabled:true}")
public class RedisConfig {
    @Primary
    @Bean
    @ConfigurationProperties("pcode.redis")
    public RedisProperties coreRedisProperties(){
        return new RedisProperties();
    }

    @Primary
    @Bean
    public LettuceConnectionFactory coreRedisFactory(){
        return RedisSetting.creatLettuceFactory(coreRedisProperties());
    }

    @Primary
    @Bean
    public RedisTemplate<Object,Object> coreRedisTemplate(){
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(coreRedisFactory());
//        Jackson2JsonRedisSerializer<Object> objectJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        objectJackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        RedisSetting.setSerializer(redisTemplate);
        return redisTemplate;
    }

    @Primary
    @Bean
    public StringRedisTemplate coreStringRedisTemplate(){
        return new StringRedisTemplate(coreRedisFactory());
    }
}
