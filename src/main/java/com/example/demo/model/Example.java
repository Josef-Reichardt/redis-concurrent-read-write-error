package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/** Example model. */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("example")
public class Example {

	@Id
	private String id;

	private int readCounter;

}
