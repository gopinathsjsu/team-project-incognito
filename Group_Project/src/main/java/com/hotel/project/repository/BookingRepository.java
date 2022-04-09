package com.hotel.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.hotel.project.Model.BookingDetails;

@Repository
public interface BookingRepository extends MongoRepository<BookingDetails, String> {

	@Query(value = "{reservationID: ?0}", delete = true)
	BookingDetails deleteByReservationID(String reservationID);

	@Query("{reservationID :?0}") // SQL Equivalent : SELECT * FROM Hotel WHERE reservationID=?
	BookingDetails getDetailsByReservationID(String reservationID);


}
