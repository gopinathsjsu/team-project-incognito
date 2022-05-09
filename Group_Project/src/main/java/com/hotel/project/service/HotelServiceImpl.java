package com.hotel.project.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
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
import com.hotel.project.pricing.ChristmasPricing;
import com.hotel.project.pricing.PublicHolidayPricing;
import com.hotel.project.pricing.SummerPricing;
import com.hotel.project.pricing.WeekDayPricing;
import com.hotel.project.pricing.WeekendPricing;
import com.hotel.project.repository.BookingRepository;
import com.hotel.project.repository.HotelRepository;
import com.hotel.project.response.BookingResponseDetailsBuilder;
import com.hotel.project.validation.DateRangeValidator;

@Service
public class HotelServiceImpl implements HotelService {
	
	
	public ChristmasPricing christmasPricing;
	public PublicHolidayPricing publicHolidayPricing;
	public WeekendPricing weekendPricing;
	public WeekDayPricing weekDayPricing;
	public SummerPricing summerPricing;

	@Autowired
	public BookingRepository bookingRepository;

	/* saving all the public holidays */
	// Mon, Jan 17, 2022
	private LocalDate Martin_Luther_King_day = LocalDate.of(2022, 1, 17);
	// Mon, May 30, 2022
	private LocalDate Memorial_day = LocalDate.of(2022, 5, 30);
	// Mon, Jul 4, 2022
	private LocalDate Independence_day = LocalDate.of(2022, 7, 04);
	// Mon, Sep 5, 2022
	private LocalDate Labor_day = LocalDate.of(2022, 9, 05);
	// Fri, Nov 11, 2022
	private LocalDate Veterans_day = LocalDate.of(2022, 11, 11);
	// Thu, Nov 24, 2022
	private LocalDate ThanksGiving_day = LocalDate.of(2022, 11, 24);
	// Mon, Dec 26, 2022
	private LocalDate Christmas_day = LocalDate.of(2022, 12, 26);

	@Autowired
	public HotelRepository hotelrepository;

	public DateRangeValidator dateRangeValidator;

	public BookingResponseDetailsBuilder bookingResponseDetailsbuilder;

	public HotelServiceImpl(HotelRepository hotelrepository, DateRangeValidator dateRangeValidator,
			BookingResponseDetailsBuilder bookingResponseDetailsbuilder,ChristmasPricing christmasPricing, PublicHolidayPricing publicHolidayPricing,
			WeekendPricing weekendPricing, WeekDayPricing weekDayPricing,SummerPricing summerPricing) {
		this.hotelrepository = hotelrepository;
		this.dateRangeValidator = dateRangeValidator;
		this.bookingResponseDetailsbuilder = bookingResponseDetailsbuilder;
				this.christmasPricing = christmasPricing;
				this.publicHolidayPricing = publicHolidayPricing;
				this.weekDayPricing = weekDayPricing;
				this.weekendPricing =weekendPricing;
				this.summerPricing = summerPricing;
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
		//bookingRepository.save(bookingDetails);
		
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
		
		/* saving on which day the start date and end date is falling in a week */
		DayOfWeek startDate = DayOfWeek.of(bookingDetails.getFromDate().get(ChronoField.DAY_OF_WEEK));
		DayOfWeek endDate = DayOfWeek.of(bookingDetails.getToDate().get(ChronoField.DAY_OF_WEEK));

		int oldPrice = updateddetails.getPrice();
		
		System.out.println(oldPrice +" OLDPRICE");
		List<LocalDate> dates = new ArrayList<LocalDate>();

		dates.add(Independence_day);
		dates.add(Martin_Luther_King_day);
		dates.add(Memorial_day);
		dates.add(Veterans_day);
		dates.add(Christmas_day);
		dates.add(Labor_day);
		dates.add(ThanksGiving_day);

		// System.out.println("public holidays" + dates.toString());

		LocalDate ChristmasStartDate = LocalDate.of(2022, 12, 24);
		LocalDate ChristmasEndDate = LocalDate.of(2023, 1, 02);

		LocalDate SummerStartDate = LocalDate.of(2022, 06, 01);
		LocalDate SummerEndDate = LocalDate.of(2023, 07, 30);


		if (dates.contains(bookingDetails.getFromDate()) || dates.contains(bookingDetails.getToDate())) {

			updateddetails.setPrice(bookingDetails.getNumberOfRooms()
					* (publicHolidayPricing.getPricing(bookingDetails.getRoomType())));
			System.out.println("print public holiday");
//			System.out.println(bookingResponse.getPrice());
		} else if ((bookingDetails.getFromDate().isAfter(ChristmasStartDate))
				&& (bookingDetails.getToDate().isBefore(ChristmasEndDate))) {

			System.out.println("christmas");
			updateddetails.setPrice(
					bookingDetails.getNumberOfRooms() * (christmasPricing.getPricing(bookingDetails.getRoomType())));
			System.out.println(updateddetails.getPrice()+ "printing price afterchekcing again ");
			
		} else if (startDate == DayOfWeek.SATURDAY || startDate == DayOfWeek.SUNDAY || endDate == DayOfWeek.SATURDAY
				|| endDate == DayOfWeek.SUNDAY) {
			System.out.println("weekend");
			updateddetails.setPrice(
					bookingDetails.getNumberOfRooms() * (weekendPricing.getPricing(bookingDetails.getRoomType())));

		} else if ((bookingDetails.getFromDate().isAfter(SummerStartDate))
				&& (bookingDetails.getToDate().isBefore(SummerEndDate))) {

			System.out.println("summer");
			updateddetails.setPrice(
					bookingDetails.getNumberOfRooms() * (summerPricing.getPricing(bookingDetails.getRoomType())));
		} else {
			 System.out.println("weekday");
			updateddetails.setPrice(
					bookingDetails.getNumberOfRooms() * (weekDayPricing.getPricing(bookingDetails.getRoomType())));
		}

		/*
		 * if reward points are less than 300 then customer gets 5% discount, if reward
		 * points are less than 700 n more than 300 then customer gets 10% discount, if
		 * reward points are more than 700 then customer gets 15% discount, if per one
		 * booking
		 */
		//int finalprice=0;
		if (updateddetails.getRewardpoints() <= 300 && updateddetails.getRewardpoints() >= 0) {

			int discountPrice = updateddetails.getPrice() * 5 / 100;
			System.out.println(discountPrice + "less than 300");
			int finalprice = updateddetails.getPrice() - discountPrice;
			updateddetails.setPrice(finalprice - oldPrice);

		} else if (updateddetails.getRewardpoints() <= 700 && updateddetails.getRewardpoints() >= 300) {
			int discountPrice = updateddetails.getPrice() * 10 / 100;
			System.out.println(discountPrice+ "\"bw 300 and 700\"");
			int finalprice = updateddetails.getPrice() - discountPrice;
			
			updateddetails.setPrice(finalprice - oldPrice);
			
		} else if (updateddetails.getRewardpoints() >= 700 ) {
			int discountPrice = updateddetails.getPrice() * 15 / 100;
			System.out.println(discountPrice + " discount bookingDetails.getRewardpoints() > 700 ");
			int finalprice =  updateddetails.getPrice() - discountPrice;
			System.out.println(finalprice + "finalprice");
			
			updateddetails.setPrice(finalprice - oldPrice);
			System.out.println(updateddetails.getPrice()+"  printing final price");
		}
		
		 //updateddetails.setPrice(finalprice - oldPrice);
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


}
