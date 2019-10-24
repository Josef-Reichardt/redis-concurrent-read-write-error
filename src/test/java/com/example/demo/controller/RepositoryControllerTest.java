package com.example.demo.controller;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

/** Tests for {@link RepositoryController}. */
public class RepositoryControllerTest extends BaseControllerTest {

	/**
	 * Tests {@link RepositoryController#getWithoutIncreasingReadCounter(String)}.
	 * @throws Exception On error.
	 */
	@Test
	public void getWithoutIncreasingReadCounter() throws Exception {
		assertThat(run(RepositoryController.BASE_PATH, false)).isEqualTo(0);
	}

	/**
	 * Tests {@link RepositoryController#getAndIncreaseReadCounter(String)}.
	 * @throws Exception On error.
	 */
	@Test
	public void getAndIncreaseReadCounter() throws Exception {
		assertThat(run(RepositoryController.BASE_PATH, true)).isEqualTo(0);
	}

}
