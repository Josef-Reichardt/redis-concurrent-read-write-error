package com.example.demo.controller;

import com.example.demo.model.Example;
import com.example.demo.repository.TemplateExampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Example rest controller. */
@RestController
@RequestMapping("/api/v1/" + TemplateController.BASE_PATH)
@RequiredArgsConstructor
public class TemplateController implements BaseController {

	static final String BASE_PATH = "template";

	private final TemplateExampleRepository templateExampleRepository;

	@Override
	public ResponseEntity<Example> create(Example example) {
		return create(templateExampleRepository, example);
	}

	@Override
	public ResponseEntity<Example> getWithoutIncreasingReadCounter(String id) {
		return loadExample(templateExampleRepository, id, false);
	}

	@Override
	public ResponseEntity<Example> getAndIncreaseReadCounter(String id) {
		return loadExample(templateExampleRepository, id, true);
	}

}
