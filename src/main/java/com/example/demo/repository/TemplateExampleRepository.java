package com.example.demo.repository;

import com.example.demo.model.Example;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * A repository implementation for testing if the error also occurs when using
 * {@link RedisTemplate}.
 */
@Component
@RequiredArgsConstructor
public class TemplateExampleRepository implements BaseExampleRepository<Example, String> {

	private static final String REDIS_HASH_PREFIX = Example.class.getAnnotation(RedisHash.class).value() + ":";

	private final RedisTemplate<String, Example> redisTemplate;

	@Override
	public Optional<Example> findById(String id) {
		return Optional.ofNullable(redisTemplate.opsForValue().get(REDIS_HASH_PREFIX + id));
	}

	@Override
	public Example save(Example example) {
		redisTemplate.opsForValue().set(REDIS_HASH_PREFIX + example.getId(), example);
		return example;
	}

}
