package com.example.DMS.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.DMS.Models.Dog;
import java.util.List;


public interface DogRepository extends CrudRepository<Dog,Integer> {
	List<Dog> findByName(String name);
}
