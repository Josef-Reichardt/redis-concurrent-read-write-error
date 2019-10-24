package com.example.demo.controller;

import com.example.demo.model.Example;
import com.example.demo.repository.ExampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Example rest controller. */
@RestController
@RequestMapping("/api/v1/" + RepositoryController.BASE_PATH)
@RequiredArgsConstructor
public class RepositoryController implements BaseController {

	static final String BASE_PATH = "repository";

	private final ExampleRepository exampleRepository;

	@Override
	public ResponseEntity<Example> create(Example example) {
		return create(exampleRepository, example);
	}

	@Override
	public ResponseEntity<Example> getWithoutIncreasingReadCounter(String id) {
		return loadExample(exampleRepository, id, false);
	}

	@Override
	public ResponseEntity<Example> getAndIncreaseReadCounter(@PathVariable String id) {
		return loadExample(exampleRepository, id, true);
	}

}
