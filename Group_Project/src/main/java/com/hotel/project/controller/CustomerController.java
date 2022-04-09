package com.hotel.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.project.Model.BookingDetails;
import com.hotel.project.Model.BookingResponse;
import com.hotel.project.Model.Customer;
import com.hotel.project.repository.CustomerRepository;
import com.hotel.project.repository.HotelRepository;
import com.hotel.project.service.HotelServiceImpl;

@RestController
@RequestMapping("/hotel-booking")
public class CustomerController {

	@Autowired
	public HotelRepository hotelrepository;
	
	@Autowired
	public CustomerRepository customerRepository;

	public HotelServiceImpl hotelservice;

	@Autowired
	public CustomerController(HotelServiceImpl hotelservice) {
		this.hotelservice = hotelservice;
	}

	@PostMapping("/registerCustomer")
	public String registerCustomer(@RequestBody Customer customer) {

		return "registered";
	}

	@PostMapping("/createBooking")
	public ResponseEntity<BookingResponse> createBooking(@Valid @RequestBody BookingDetails customer) {

		BookingResponse bookingResponseDetails = hotelservice.createBooking(customer);

		return new ResponseEntity<BookingResponse>(bookingResponseDetails, HttpStatus.CREATED);
	}

	@GetMapping("/getBooking")
	public List<BookingDetails> getBooking() {

		return hotelservice.getAllBookingDetails();
	}
	
	@GetMapping("/getRewardPoints")
	public String getRewardPoints() {

		return "rewards";
	}


	@DeleteMapping("/cancel/{reservationID}")
	public String cancelBooking(@PathVariable("reservationID") String reservationID) {

		hotelservice.deleteBookingDetails(reservationID);
		return "booking cancelled";
	}

}
