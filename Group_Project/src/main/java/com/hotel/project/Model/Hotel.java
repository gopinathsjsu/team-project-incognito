package com.hotel.project.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Hotel")
public class Hotel {
	
	/*
	 * public Hotel(String hotelName, String id, String location) { super();
	 * this.hotelName = hotelName; this.id = id; this.location = location; }
	 */
	/*
	 * @NonNull
	 * 
	 * @Size(min=2, max=30)
	 */
	private String hotelName;
	
	@Id
	private String id;
	private String location;
	/**
	 * @return the hotelName
	 */
	public String getHotelName() {
		return hotelName;
	}
	/**
	 * @param hotelName the hotelName to set
	 */
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
}
