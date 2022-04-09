package com.hotel.project.service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public BookingResponse getRewardPoints() {
		return null;
	}

}
