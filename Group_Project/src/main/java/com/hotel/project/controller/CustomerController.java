package com.hotel.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.project.Model.BookingDetails;
import com.hotel.project.Model.BookingResponse;
import com.hotel.project.Model.Customer;
import com.hotel.project.exception.HotelBusinessException;
import com.hotel.project.repository.BookingRepository;
import com.hotel.project.repository.CustomerRepository;
import com.hotel.project.repository.HotelRepository;
import com.hotel.project.service.CustomerServiceImpl;
import com.hotel.project.service.HotelServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/hotel-booking")
public class CustomerController {

	@Autowired
	public HotelRepository hotelrepository;

	@Autowired
	public BookingRepository bookingRepository;

	@Autowired
	public CustomerRepository customerRepository;

	public HotelServiceImpl hotelservice;

	@Autowired
	private CustomerServiceImpl customerService;

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

	
	@PutMapping("/updateBooking/{reservationID}")
	public ResponseEntity<BookingDetails> updateBooking(@Valid @PathVariable("reservationID") String reservationID,
			@Valid @RequestBody BookingDetails bookingDetails) {

		ResponseEntity<BookingDetails> updatedDetails = hotelservice.updateBookingDetails(reservationID, bookingDetails);

		return new ResponseEntity<BookingDetails>(updatedDetails.getBody(), HttpStatus.OK);

	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/getBooking/{emailID}")
	public List<BookingDetails> getBookingOfCustomer(@Valid @PathVariable("emailID") String emailID){

		List<BookingDetails> bookingDetails = bookingRepository.findByEmail(emailID);

         return bookingDetails;	
	}

	@GetMapping("/getRewardPoints/{emailID}")
	public String getRewardPoints(@Valid @PathVariable("emailID") String emailID) {

		Customer customerexists = customerService.findUserByEmail(emailID);
		double sum = 0;

		if (null != customerexists) {

			List<BookingDetails> bookingDetails = bookingRepository.findByEmail(emailID);

			if (!bookingDetails.isEmpty() && bookingDetails != null) {

				sum = bookingDetails.get(bookingDetails.size()-1).getRewardpoints();
						//bookingDetails.stream().mapToDouble(de -> de.getRewardpoints()).sum();

				return " your reward points are " + sum;

			} else {

				throw new HotelBusinessException("No customer with " + emailID + " exists");
			}

		} else {

			throw new HotelBusinessException("No customer with " + emailID + " exists");
		}

	}

	@DeleteMapping("/cancel/{reservationID}")
	public String cancelBooking(@PathVariable("reservationID") String reservationID) {

		hotelservice.deleteBookingDetails(reservationID);
		return "booking cancelled";
	}

}
