package com.hotel.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hotel.project.Model.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, Integer> {
	Employee findByEmail(String email);

	Employee findUserByEmail(String email);

}
