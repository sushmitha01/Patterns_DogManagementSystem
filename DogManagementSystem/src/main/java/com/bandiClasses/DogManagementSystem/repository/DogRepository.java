package com.bandiClasses.DogManagementSystem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bandiClasses.DogManagementSystem.Model.Dog;

/**
 * 
 * @author S555058
 *
 */
public interface DogRepository extends CrudRepository<Dog, Integer>{
List<Dog> findByName(String name);
}
