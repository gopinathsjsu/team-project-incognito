package com.hotel.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hotel.project.Model.Customer;


@Repository
public interface CustomerRepository extends MongoRepository<Customer, Integer> {

	Customer findByEmail(String email);
	
	Customer findByUsername(String username);
}

