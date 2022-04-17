package com.hotel.project.repository;

import java.util.List;
import java.util.Optional;

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

	BookingDetails save(Optional<BookingDetails> details);

	@Query("{emailID :?0}")
	List<BookingDetails> findByEmail(String emailID);


}
