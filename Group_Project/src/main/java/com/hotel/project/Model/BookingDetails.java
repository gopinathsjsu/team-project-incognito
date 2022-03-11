package com.hotel.project.Model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "BookingDetails")
public class BookingDetails  implements Serializable{
	
	
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
	
	
	private String customerName;
	private String emailID;
	private String phoneNumber;
	private String roomType;
	
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	private Date fromDate;
	private Date toDate;
		

	}
