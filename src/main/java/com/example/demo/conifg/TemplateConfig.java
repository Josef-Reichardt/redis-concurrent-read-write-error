package com.example.demo.conifg;

import com.example.demo.model.Example;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/** Configuration to enable {@link RedisTemplate}. */
@Configuration
public class TemplateConfig {

	@Bean
	RedisTemplate<String, Example> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		final RedisTemplate<String, Example> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		return template;
	}

}
