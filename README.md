This project should demonstrate an error which occurs when performing concurrent read and write redis operations.

I looks like Spring or Redis or something in between can't read values while they are written by `CrudRepository`.
The application behaves as if the value did not exist.

## How to run?

Simply clone this git repository and run tests.

For example by running

```shell script
mvn test
```

## What's happened?

There two test classes and each one has two test methods:

1. `RepositoryControllerTest`  
   Using a `CrudRepsoitory`: `ExampleRepository`
   1. `getWithoutIncreasingReadCounter`  
      Creates one entry in redis and reads it 100 times concurrently.  
      **This is working well.**
   2. `getAndIncreaseReadCounter`
      Creates one entry in redis and reads it 100 times concurrently.
      For each request the value is modified and written back to redis.  
      **This is _not_ working!**
2. `TemplateControllerTest`  
   Using a `RedisTemplate`: `TemplateExampleRepository`
   1. `getWithoutIncreasingReadCounter`  
      Creates one entry in redis and reads it 100 times concurrently.  
      **This is working well.**
   2. `getAndIncreaseReadCounter`
      Creates one entry in redis and reads it 100 times concurrently.
      For each request the value is modified and written back to redis.  
      **This is working well.**

## Result

|                                            | `CrudRepository` | `RedisTemplate` |
|--------------------------------------------|------------------|-----------------|
| **read 100 times concurrently:**           | working          | working         |
| **read and write 100 times concurrently:** | NOT working      | working         |
