package com.hotel.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hotel.project.Model.BookingDetails;


@Repository
public interface BookingRepository extends MongoRepository<BookingDetails, Integer>{
	

}
