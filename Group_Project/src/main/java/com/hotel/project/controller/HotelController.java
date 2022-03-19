package com.hotel.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.project.Model.Hotel;
import com.hotel.project.repository.HotelRepository;
import com.hotel.project.service.HotelService;


@RestController
@RequestMapping("/hotel-booking")
public class HotelController {

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	public HotelRepository hotelrepository;

	
	public HotelService hotelservice;
	
	@Autowired
	public HotelController(HotelService hotelservice){
	       this.hotelservice = hotelservice;
	    }
	
	@GetMapping("/searchHotel/{location}")
	public List<Hotel> searchHotel(@PathVariable String location) {

		return hotelservice.searchHotelByLocation(location);

	}

	@PostMapping("/savehotel")
	public String saveHotel(@RequestBody Hotel hotel) {
		hotelrepository.save(hotel);

		return "added Hotel";

	}

	@GetMapping("/hotel/{id}")
	public Optional<Hotel> getHotel(@PathVariable int id) {
		return hotelrepository.findById(id);

	}
}
