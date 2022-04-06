package com.hotel.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hotel.project.Model.Role;


public interface RoleRepository extends MongoRepository<Role, String> {

	Role findByRole(String role);
}
