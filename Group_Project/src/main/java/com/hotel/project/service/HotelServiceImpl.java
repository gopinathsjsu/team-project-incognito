package com.hotel.project.service;

import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.xml.datatype.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.project.Model.BookingDetails;
import com.hotel.project.Model.Hotel;
import com.hotel.project.exception.HotelBusinessException;
import com.hotel.project.repository.BookingRepository;
import com.hotel.project.repository.HotelRepository;
import com.hotel.project.validation.DateRangeValidator;

@Service
public class HotelServiceImpl implements HotelService{

	@Autowired
	public HotelRepository hotelrepository;
	
	@Autowired
	public BookingRepository bookingRepository;

	public DateRangeValidator dateRangeValidator;
	
 	public HotelServiceImpl(HotelRepository hotelrepository,DateRangeValidator dateRangeValidator) {
		this.hotelrepository = hotelrepository;
		this.dateRangeValidator = dateRangeValidator;
	}

	public List<Hotel> searchHotelByLocation(String location) {

		List<Hotel> hotelList = hotelrepository.getHotelByLocation(location);

		if (hotelList.isEmpty() || hotelList == null) {
			throw new HotelBusinessException("No hotels in " + location + "." + "Please try another location nearby");
		}
		return hotelList;
	}
	
	public BookingDetails createBooking(BookingDetails bookingDetails ){
		
		dateRangeValidator.validateDateRange(bookingDetails);
		
		
		return bookingRepository.save(bookingDetails);
		
	}
	
  public List<BookingDetails> getAllBookingDetails(){
		
		return (List<BookingDetails>) bookingRepository.findAll();
		
	}
	
  public void deleteBookingDetails(long id){
		
	  bookingRepository.deleteById(id);
		
	}

}
