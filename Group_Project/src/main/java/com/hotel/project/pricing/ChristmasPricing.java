package com.hotel.project.pricing;

import static com.hotel.project.Model.RoomRates.Family_Lounge_Rate;
import static com.hotel.project.Model.RoomRates.Single_room_Rate;
import static com.hotel.project.Model.RoomRates.Suite_Rate;
import static com.hotel.project.Model.RoomRates.double_room_Rate;

import org.springframework.stereotype.Component;


@Component
public class ChristmasPricing implements Pricing {

	int price;

	@Override
	public int getPricing(String roomType) {

		if (roomType.equalsIgnoreCase("Family_lounge")) {

			price = Family_Lounge_Rate + 275;
		}
		if (roomType.equalsIgnoreCase("single_room")) {

			price = Single_room_Rate + 90;
		}
		if (roomType.equalsIgnoreCase("double_room")) {

			price = double_room_Rate + 175;
		}
		if (roomType.equalsIgnoreCase("suite")) {

			price = Suite_Rate + 225;
		}

		return price;

	}
}
