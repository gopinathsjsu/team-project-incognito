package com.hotel.project.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.project.Model.Customer;
import com.hotel.project.Model.CustomerAuthenticationRequest;
import com.hotel.project.exception.HotelBusinessException;
import com.hotel.project.repository.CustomerRepository;
import com.hotel.project.security.JwtTokenGenerator;
import com.hotel.project.service.CustomerServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 40000)
@RequestMapping("/api/auth")
public class AuthenticationController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenGenerator jwtTokenGenerator;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	private CustomerServiceImpl customerService;

	@SuppressWarnings("rawtypes")
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/signin")
	public ResponseEntity signin(@Valid @RequestBody CustomerAuthenticationRequest data) {
		try {
			String username = data.getEmail();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
			String token = jwtTokenGenerator.createToken(username, this.customerRepository.findByEmail(username).getRoles());
			Map<Object, Object> model = new HashMap<>();
			model.put("username", username);
			model.put("token", token);
			return ok(model);
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalid email/password ");
		}
	}

	@SuppressWarnings("rawtypes")
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/signup")
	public ResponseEntity signup(@Valid @RequestBody Customer customer) {
		Customer customerexists = customerService.findUserByEmail(customer.getEmail());
		if (customerexists != null) {
			throw new HotelBusinessException("Customer with email: " + customer.getEmail() + " already exists. Please Login");
		}
		customerService.saveCustomer(customer);
		Map<Object, Object> model = new HashMap<>();
		model.put("message", "User signup successfull");
		return ok(model);
	}
}
