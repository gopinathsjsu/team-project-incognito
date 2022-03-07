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

@RestController
@RequestMapping("/search")
public class SearchController {

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	public HotelRepository hotelrepository;

	@GetMapping
	public List<String> searchHotel(@PathVariable String location) {

		hotelrepository.findAll();

		return null;

	}

	@GetMapping("/test")
	public String testDocker() {

		return "hello";
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
