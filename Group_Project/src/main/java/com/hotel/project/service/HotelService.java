package com.hotel.project.service;

import java.util.List;

import com.hotel.project.Model.BookingDetails;
import com.hotel.project.Model.BookingResponse;
import com.hotel.project.Model.Hotel;


//Service Pattern for Reservation
public interface HotelService {
	
	public BookingResponse createBooking(BookingDetails customer);
	
	public List<BookingDetails> getAllBookingDetails();

	public BookingDetails deleteBookingDetails(String id);
	
	public List<Hotel> searchHotelByLocation(String location);


}
