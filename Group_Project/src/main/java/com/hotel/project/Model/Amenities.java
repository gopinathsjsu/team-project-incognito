package com.hotel.project.Model;

public class Amenities {

	private boolean daily_continental_breakfast = false;
	private boolean access_to_fitness_room = false;
	private boolean access_to_swimming_Pool_Jacuzzi = false;
	private boolean daily_parking = false;
	private boolean all_meals_included = false;

	public boolean isDaily_continental_breakfast() {
		return daily_continental_breakfast;
	}

	public void setDaily_continental_breakfast(boolean daily_continental_breakfast) {
		this.daily_continental_breakfast = daily_continental_breakfast;
	}

	public boolean isAccess_to_fitness_room() {
		return access_to_fitness_room;
	}

	public void setAccess_to_fitness_room(boolean access_to_fitness_room) {
		this.access_to_fitness_room = access_to_fitness_room;
	}

	public boolean isAccess_to_swimming_Pool_Jacuzzi() {
		return access_to_swimming_Pool_Jacuzzi;
	}

	public void setAccess_to_swimming_Pool_Jacuzzi(boolean access_to_swimming_Pool_Jacuzzi) {
		this.access_to_swimming_Pool_Jacuzzi = access_to_swimming_Pool_Jacuzzi;
	}

	public boolean isDaily_parking() {
		return daily_parking;
	}

	public void setDaily_parking(boolean daily_parking) {
		this.daily_parking = daily_parking;
	}

	public boolean isAll_meals_included() {
		return all_meals_included;
	}

	public void setAll_meals_included(boolean all_meals_included) {
		this.all_meals_included = all_meals_included;
	}

}
