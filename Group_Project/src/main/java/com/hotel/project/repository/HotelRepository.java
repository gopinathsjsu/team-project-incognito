package com.hotel.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hotel.project.Model.Hotel;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, Integer>{

}
