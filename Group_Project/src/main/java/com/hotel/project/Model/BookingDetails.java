package com.hotel.project.Model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "BookingDetails")
public class BookingDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * public BookingDetails(String id, String customerName, String emailID, String
	 * phoneNumber, String roomType, Date fromDate, Date toDate) { super(); this.id
	 * = id; this.customerName = customerName; this.emailID = emailID;
	 * this.phoneNumber = phoneNumber; this.roomType = roomType; this.fromDate =
	 * fromDate; this.toDate = toDate; }
	 */
	@Id
	private String reservationID;

	//@NotNull
	//@NotBlank(message = "Name is mandatory")
	private String customerName;
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
	private String emailID;
	// @Pattern(regexp="(^$|[0-9]{10})")
	/*
	 * @NotBlank(message = "phoneNumber is mandatory") private String phoneNumber;
	 */
	@NotBlank(message = "roomType is mandatory")
	private String roomType;
	private Integer number_of_adults;
	private Integer number_of_children;
	private LocalDate fromDate;
	private LocalDate toDate;
	private double rewardpoints;
	@NotNull(message = "Number of Rooms is mandatory")
	private int numberOfRooms;
	private int price;
	private Amenities amenities;
	

	public String getReservationID() {
		return reservationID;
	}

	public void setReservationID(String reservationID) {
		this.reservationID = reservationID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	/*
	 * public String getPhoneNumber() { return phoneNumber; }
	 * 
	 * public void setPhoneNumber(String phoneNumber) { this.phoneNumber =
	 * phoneNumber; }
	 */
	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return the numbder_of_children
	 */
	public Integer getNumber_of_children() {
		return number_of_children;
	}

	/**
	 * @param numbder_of_children the number_of_children to set
	 */
	public void setNumber_of_children(Integer number_of_children) {
		this.number_of_children = number_of_children;
	}

	/**
	 * @return the amenities
	 */
	public Amenities getAmenities() {
		return amenities;
	}

	/**
	 * @param amenities the amenities to set
	 */
	public void setAmenities(Amenities amenities) {
		this.amenities = amenities;
	}

	/**
	 * @return the number_of_adults
	 */
	public Integer getNumber_of_adults() {
		return number_of_adults;
	}

	/**
	 * @param number_of_adults the number_of_adults to set
	 */
	public void setNumber_of_adults(Integer number_of_adults) {
		this.number_of_adults = number_of_adults;
	}

	/**
	 * @return the rewardpoints
	 */
	public double getRewardpoints() {
		return rewardpoints;
	}

	/**
	 * @param rewardpoints the rewardpoints to set
	 */
	public void setRewardpoints(double rewardpoints) {
		this.rewardpoints = rewardpoints;
	}

	/**
	 * @return the numberOfRooms
	 */
	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	/**
	 * @param numberOfRooms the numberOfRooms to set
	 */
	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

}
