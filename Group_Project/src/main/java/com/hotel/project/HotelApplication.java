package com.hotel.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
/*
APIs - input and output of API should be in JSON and should include error handling and validation of inputs
APIs can be demonstrated using a basic Web/mobile UI or through Postman
UI will be used by Customers and Hotel employees
APIs should support following functionality:
Manage your Hotel rewards account
Search for Hotels
Book one or more rooms for stay up to 1 week
Options may be selected for each room for one or more amenities:
Daily Continental Breakfast
Access to fitness room
Access to Swimming Pool/Jacuzzi
Daily Parking
All meals included (Breakfast, Lunch, Dinner)
Room rates based on Room types and number of guests e.g. Double rooms, Suites, Singe room ( up to guests)
Use dynamic pricing - based on weekdays/weekends/holidays
Seasonal - peak season such as Summer/Christmas Holiday season
Customer Loyalty
Change/Cancel reservations
Enrolling as a new customer*/
@SpringBootApplication
/*
 * (exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class
 * })
 */
public class HotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);
	}

}
