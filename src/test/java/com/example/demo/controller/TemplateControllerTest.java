package com.example.demo.controller;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

/** Tests for {@link TemplateController}. */
public class TemplateControllerTest extends BaseControllerTest {

	/**
	 * Tests {@link TemplateController#getWithoutIncreasingReadCounter(String)}.
	 * @throws Exception On error.
	 */
	@Test
	public void getWithoutIncreasingReadCounter() throws Exception {
		assertThat(run(TemplateController.BASE_PATH, false)).isEqualTo(0);
	}

	/**
	 * Tests {@link TemplateController#getAndIncreaseReadCounter(String)}.
	 * @throws Exception On error.
	 */
	@Test
	public void getAndIncreaseReadCounter() throws Exception {
		assertThat(run(TemplateController.BASE_PATH, true)).isEqualTo(0);
	}

}
