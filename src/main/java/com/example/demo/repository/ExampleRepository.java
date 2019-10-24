package com.example.demo.repository;

import com.example.demo.model.Example;
import org.springframework.data.repository.CrudRepository;

/** Crud repository. */
public interface ExampleRepository extends CrudRepository<Example, String>, BaseExampleRepository<Example, String> {

}
