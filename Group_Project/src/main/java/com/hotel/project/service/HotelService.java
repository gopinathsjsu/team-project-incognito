package com.hotel.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hotel.project.Model.BookingDetails;
import com.hotel.project.Model.Hotel;
import com.hotel.project.repository.BookingRepository;
import com.hotel.project.repository.HotelRepository;

@Service
public class HotelService {

	@Autowired
	public HotelRepository hotelrepository;
	
	@Autowired
	public BookingRepository bookingRepository;

	public HotelService(HotelRepository hotelrepository) {
		this.hotelrepository = hotelrepository;
	}

	public List<Hotel> searchHotelByLocation(String location) {

		List<Hotel> hotelList = hotelrepository.getHotelByLocation(location);

		if (hotelList.isEmpty() || hotelList == null) {
			//throw new HotelBusinessException("No hotels in " + location + "." + "Please try another location nearby");
		}
		return hotelList;
	}
	
	public BookingDetails createBooking(BookingDetails customer){
		
		return bookingRepository.save(customer);
		
	}
	
  public List<BookingDetails> getAllBookingDetails(){
		
		return bookingRepository.findAll();
		
	}
	
  public void deleteBookingDetails(int id){
		
	  bookingRepository.deleteById(id);
		
	}

}
