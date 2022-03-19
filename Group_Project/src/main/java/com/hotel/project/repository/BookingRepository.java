package com.hotel.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hotel.project.Model.BookingDetails;


@Repository
public interface BookingRepository extends CrudRepository<BookingDetails, Long>{
	
	//check if it is mongo or crud later
	@Override
	void delete(BookingDetails deleted);
	

}
