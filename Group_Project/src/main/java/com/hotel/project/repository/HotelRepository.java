package com.hotel.project.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.hotel.project.Model.Hotel;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, Integer>{
	
	  @Query("{location :?0}")                                           //SQL Equivalent : SELECT * FROM Hotel WHERE LOCATION=?
      List<Hotel> getHotelByLocation(String location);

	  

}
