package com.nyanmeter.springboot.model;

import org.springframework.data.repository.CrudRepository;

public interface CatRepository extends CrudRepository<Cat, Long> {
}
