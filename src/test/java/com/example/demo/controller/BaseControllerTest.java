package com.example.demo.controller;

import com.example.demo.model.Example;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** Demonstrate the error. */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
public abstract class BaseControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	int run(String path, boolean increaseReadCount) throws Exception {
		String id = randomUUID().toString();

		Example example = new Example(id, 0);
		String baseUrl = "/api/v1/" + path;
		String getUrl = baseUrl + "/" + (increaseReadCount ? "increase-and-get" : "only-get") + "/{sessionId}";

		mockMvc.perform(
				post(baseUrl).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(example)))
				.andExpect(status().isCreated());

		AtomicInteger errorCounter = new AtomicInteger();

		IntStream.range(0, 100).parallel()
				.forEach(i -> Try
						.of(() -> mockMvc.perform(get(getUrl, id)).andExpect(jsonPath("$.id").value(example.getId())))
						.getOrElseGet(e -> fail("Error while loading example: {}", e.getMessage(), e)));

		return errorCounter.get();
	}

}
