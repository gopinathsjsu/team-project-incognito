package com.hotel.project.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.project.Model.BookingDetails;
import com.hotel.project.Model.Employee;
import com.hotel.project.Model.EmployeeAuthenticationRequest;
import com.hotel.project.Model.Hotel;
import com.hotel.project.exception.HotelBusinessException;
import com.hotel.project.repository.EmployeeRepository;
import com.hotel.project.repository.HotelRepository;
import com.hotel.project.service.HotelServiceImpl;

@RestController
@RequestMapping("/api")
public class HotelController {

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	public HotelRepository hotelrepository;

	@Autowired
	public EmployeeRepository employeeRepository;

	public HotelServiceImpl hotelservice;

	@Autowired
	public HotelController(HotelServiceImpl hotelservice) {
		this.hotelservice = hotelservice;
	}

	@GetMapping("/searchHotel/{location}")
	public List<Hotel> searchHotel(@PathVariable String location) {

		return hotelservice.searchHotelByLocation(location);

	}

	@GetMapping("/getBooking")
	public List<BookingDetails> getBooking() {

		return hotelservice.getAllBookingDetails();
	}
	@PostMapping("/savehotel")
	public String saveHotel(@Valid @RequestBody Hotel hotel) {
		hotelrepository.save(hotel);

		return "added Hotel";

	}

	@GetMapping("/hotel/{id}")
	public Optional<Hotel> getHotel(@PathVariable int id) {
		return hotelrepository.findById(id);

	}

	@PostMapping("/employee/register")
	public String registerEmployee(@Valid @RequestBody Employee employee) {

		Employee employeeExists = employeeRepository.findUserByEmail(employee.getEmail());
		if (employeeExists != null) {
			throw new HotelBusinessException(
					"Employee with email: " + employee.getEmail() + " already exists. Please Login");
		}else {
		employeeRepository.save(employee);

		return "employee registered successfully";
		}
	}

	@PostMapping("/employee/login")
	public String employeeLogin(@Valid @RequestBody EmployeeAuthenticationRequest employee) {

		Employee employeeExists = employeeRepository.findUserByEmail(employee.getEmail());
		if (employeeExists != null) {
			return "signin succesfull";
		} else {
			throw new HotelBusinessException(
					"Employee with email: " + employee.getEmail() + " doesn't exists. Please SignUp");
		}

	}

}
