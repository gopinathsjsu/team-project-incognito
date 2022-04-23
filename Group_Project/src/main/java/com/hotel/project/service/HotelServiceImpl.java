package com.hotel.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hotel.project.Model.Amenities;
import com.hotel.project.Model.BookingDetails;
import com.hotel.project.Model.BookingResponse;
import com.hotel.project.Model.Hotel;
import com.hotel.project.exception.HotelBusinessException;
import com.hotel.project.repository.BookingRepository;
import com.hotel.project.repository.HotelRepository;
import com.hotel.project.response.BookingResponseDetailsBuilder;
import com.hotel.project.validation.DateRangeValidator;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	public HotelRepository hotelrepository;

	@Autowired
	public BookingRepository bookingRepository;

	public DateRangeValidator dateRangeValidator;

	public BookingResponseDetailsBuilder bookingResponseDetailsbuilder;

	public HotelServiceImpl(HotelRepository hotelrepository, DateRangeValidator dateRangeValidator,
			BookingResponseDetailsBuilder bookingResponseDetailsbuilder) {
		this.hotelrepository = hotelrepository;
		this.dateRangeValidator = dateRangeValidator;
		this.bookingResponseDetailsbuilder = bookingResponseDetailsbuilder;
	}

	public List<Hotel> searchHotelByLocation(String location) {

		List<Hotel> hotelList = hotelrepository.getHotelByLocation(location);

		if (hotelList.isEmpty() || hotelList == null) {
			throw new HotelBusinessException("No hotels in " + location + "." + "Please try another location nearby");
		}
		return hotelList;
	}

	public BookingResponse createBooking(BookingDetails bookingDetails) {

		dateRangeValidator.validateDateRange(bookingDetails);

		List<BookingDetails> bookingDetailList = new ArrayList<BookingDetails>();

		bookingDetailList = bookingRepository.findAll();

		if (!bookingDetailList.isEmpty() && null != bookingDetailList) {
			for (BookingDetails bookingDetails2 : bookingDetailList) {

				/*
				 * If the customer is an existing customer then increase his reward points by
				 * 100 for everybooking...There will be one unique emailId per customer.
				 */
				if (bookingDetails.getEmailID().equalsIgnoreCase(bookingDetails2.getEmailID())) {

					bookingDetails.setRewardpoints(bookingDetails2.getRewardpoints() + 100);
				}
			}
		}

		bookingRepository.save(bookingDetails);

		return bookingResponseDetailsbuilder.buildResponse(bookingDetails);

	}

	public List<BookingDetails> getAllBookingDetails() {

		return (List<BookingDetails>) bookingRepository.findAll();

	}

	public BookingDetails deleteBookingDetails(String reservationID) {

		return bookingRepository.deleteByReservationID(reservationID);

	}

	public ResponseEntity<BookingDetails> updateBookingDetails(String reservationID, BookingDetails bookingDetails) {

		dateRangeValidator.validateDateRange(bookingDetails);

		BookingDetails updatedetails = bookingRepository.getDetailsByReservationID(reservationID);

		if (updatedetails != null) {

			updateRequestBuilder(updatedetails, bookingDetails);

			return new ResponseEntity<BookingDetails>(bookingRepository.save(updatedetails), HttpStatus.OK);

		} else {
			return new ResponseEntity<BookingDetails>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * method to build update the booking details
	 * 
	 * @param updateddetails
	 * @param bookingDetails
	 */
	private void updateRequestBuilder(BookingDetails updateddetails, BookingDetails bookingDetails) {

		if (bookingDetails.getRoomType() != null) {
			updateddetails.setRoomType(bookingDetails.getRoomType());
		}

		if (bookingDetails.getFromDate() != null) {
			updateddetails.setFromDate(bookingDetails.getFromDate());
		}
		if (bookingDetails.getToDate() != null) {
			updateddetails.setToDate(bookingDetails.getToDate());
		}
		if (bookingDetails.getNumber_of_adults() != null) {
			updateddetails.setNumber_of_adults(bookingDetails.getNumber_of_adults());
		}
		if (bookingDetails.getNumber_of_children() != null) {
			updateddetails.setNumber_of_children(bookingDetails.getNumber_of_children());
		}
		if (bookingDetails.getAmenities() != null) {
			Amenities amenities = new Amenities();

			if (bookingDetails.getAmenities().isAccess_to_fitness_room()) {
				amenities.setAccess_to_fitness_room(bookingDetails.getAmenities().isAccess_to_fitness_room());
			} else {
				amenities.setAccess_to_fitness_room(bookingDetails.getAmenities().isAccess_to_fitness_room());
			}
			if (bookingDetails.getAmenities().isAccess_to_swimming_Pool_Jacuzzi()) {
				amenities.setAccess_to_swimming_Pool_Jacuzzi(
						bookingDetails.getAmenities().isAccess_to_swimming_Pool_Jacuzzi());
			} else {
				amenities.setAccess_to_swimming_Pool_Jacuzzi(
						bookingDetails.getAmenities().isAccess_to_swimming_Pool_Jacuzzi());
			}
			if (bookingDetails.getAmenities().isAll_meals_included()) {
				amenities.setAll_meals_included(bookingDetails.getAmenities().isAll_meals_included());
			} else {
				amenities.setAll_meals_included(bookingDetails.getAmenities().isAll_meals_included());
			}
			if (bookingDetails.getAmenities().isDaily_continental_breakfast()) {
				amenities.setDaily_continental_breakfast(bookingDetails.getAmenities().isDaily_continental_breakfast());
			} else {
				amenities.setDaily_continental_breakfast(bookingDetails.getAmenities().isDaily_continental_breakfast());
			}
			if (bookingDetails.getAmenities().isDaily_parking()) {
				amenities.setDaily_parking(bookingDetails.getAmenities().isDaily_parking());
			} else {
				amenities.setDaily_parking(bookingDetails.getAmenities().isDaily_parking());
			}
			updateddetails.setAmenities(amenities);
		}

	}

	public BookingResponse getRewardPoints() {
		return null;
	}

}
