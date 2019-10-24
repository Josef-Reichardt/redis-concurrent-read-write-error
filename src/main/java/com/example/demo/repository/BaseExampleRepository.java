package com.example.demo.repository;

import java.util.Optional;

/**
 * Base interface for different repositories.
 *
 * @param <V> Value type.
 * @param <K> Key type.
 */
public interface BaseExampleRepository<V, K> {

	Optional<V> findById(K id);

	V save(V example);

}
