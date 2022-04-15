package com.hotel.project.validation;

import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

import com.hotel.project.Model.BookingDetails;
import com.hotel.project.exception.HotelBusinessException;

@Component
public class DateRangeValidator {

	public void validateDateRange(BookingDetails bookingDetails) {
		
		long noOfDaysDifference = ChronoUnit.DAYS.between(bookingDetails.getFromDate(), bookingDetails.getToDate());

		if (noOfDaysDifference < 0) {
			throw new HotelBusinessException("Start Date should not be greater than End Date");

		}
		if (noOfDaysDifference > 7) {
			throw new HotelBusinessException("Cannot book longer than 7 days");
		}

	}

}
