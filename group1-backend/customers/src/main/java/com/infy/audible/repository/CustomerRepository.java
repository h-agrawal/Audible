package com.infy.audible.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.infy.audible.collection.Customer;

public interface CustomerRepository extends MongoRepository<Customer, Long>{

	Optional<Customer> findByUsername(String username);


}
