package com.example.demo.controller;

import com.example.demo.model.Example;
import com.example.demo.repository.BaseExampleRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpClientErrorException;

import static java.nio.charset.StandardCharsets.UTF_8;

/** Base class for rest controller. */
public interface BaseController {

	@PostMapping
	ResponseEntity<Example> create(@RequestBody Example example);

	@GetMapping("/only-get/{id}")
	ResponseEntity<Example> getWithoutIncreasingReadCounter(@PathVariable String id);

	@GetMapping("/increase-and-get/{id}")
	ResponseEntity<Example> getAndIncreaseReadCounter(@PathVariable String id);

	default ResponseEntity<Example> create(BaseExampleRepository<Example, String> repository, Example example) {
		return new ResponseEntity<>(repository.save(example), HttpStatus.CREATED);
	}

	default ResponseEntity<Example> loadExample(BaseExampleRepository<Example, String> repository, String id,
			boolean incrementReadCounter) {
		Example example = repository.findById(id).orElseThrow(() -> HttpClientErrorException
				.create(HttpStatus.NOT_FOUND, "not found", HttpHeaders.EMPTY, new byte[0], UTF_8));

		if (incrementReadCounter) {
			example.setReadCounter(example.getReadCounter() + 1);
			repository.save(example);
		}

		return ResponseEntity.ok(example);
	}

}
