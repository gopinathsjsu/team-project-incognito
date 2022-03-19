package com.hotel.project.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
	private Long reservationID;
	
	private String customerName;
	private String emailID;
	private String phoneNumber;
	private String roomType;
	private int numbder_of_persons;
	private int numbder_of_children;
	private Date fromDate;
	private Date toDate;
	
	public long getReservationID() {
		return reservationID;
	}
	public void setReservationID(long reservationID) {
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
	/**
	 * @return the numbder_of_persons
	 */
	public int getNumbder_of_persons() {
		return numbder_of_persons;
	}
	/**
	 * @param numbder_of_persons the numbder_of_persons to set
	 */
	public void setNumbder_of_persons(int numbder_of_persons) {
		this.numbder_of_persons = numbder_of_persons;
	}
	/**
	 * @return the numbder_of_children
	 */
	public int getNumbder_of_children() {
		return numbder_of_children;
	}
	/**
	 * @param numbder_of_children the numbder_of_children to set
	 */
	public void setNumbder_of_children(int numbder_of_children) {
		this.numbder_of_children = numbder_of_children;
	}

	}
