package com.rentalcar.app.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import com.rentalcar.app.models.entity.Car;
import com.rentalcar.app.models.entity.Client;
import com.rentalcar.app.models.entity.CommonExtra;
import com.rentalcar.app.models.entity.Reservation;

public class Utils {

	public static int calculateDifferenceInDays(Reservation rent) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
		cal.setTime(rent.getInitDate());
		int initDay = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(rent.getFinalDate());
		int finalDay = cal.get(Calendar.DAY_OF_MONTH);

		int difference = finalDay - initDay;

		return difference;
	}

	public static double calculateExtrasPrice(Reservation rent, Car car) {
		double sumExtras = 0.0;
		for (CommonExtra e : rent.getExtras()) {
			sumExtras += e.getPrice();
		}

		if (rent.getTopInsurance()) {
			sumExtras += car.getCategory().getPriceTopInsurance();
		} else {
			sumExtras += car.getCategory().getPriceBaseInsurance();
		}

		if (rent.getTireAndGlassProtection()) {
			sumExtras += car.getCategory().getPriceTireAndGlassProtection();
		}

		return sumExtras;
	}

	public static boolean isValid(Client newClient) {

		if (newClient != null) {
			if (!inputIsValid(newClient.getName())) {
				return false;
			}

			if (!inputIsValid(newClient.getSurname())) {
				return false;
			}

			if (!inputIsValid(newClient.getPhoneNumber())) {
				return false;
			}

			if (!inputIsValid(newClient.getIdNumber())) {
				return false;
			}

			if (!inputIsValid(newClient.getEmail())) {
				return false;
			}

			if (!inputIsValid(newClient.getUserName())) {
				return false;
			}

			if (!inputIsValid(newClient.getPassword())) {
				return false;
			}

			if (!matchPassword(newClient.getPassword(), newClient.getConfirmPassword())) {
				return false;
			}
		}

		return true;
	}

	public static boolean inputIsValid(String input) {
		if (input != null && !input.equals("") && !input.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean matchPassword(String password, String confirmPassword) {
		if (password != null && !password.equals("") && !password.isEmpty() && confirmPassword != null
				&& !confirmPassword.equals("") && !confirmPassword.isEmpty()) {
			if (password.equals(confirmPassword)) {
				return true;
			}
		}
		return false;
	}

	public static double calculateCarBaseTotalPrice(Reservation rent, Car car) {

		double carBasePrice = car.getCategory().getPriceBase();
		int days = calculateDifferenceInDays(rent);

		return carBasePrice * days;
	}

	public static double CalculateTotalReservationPrice(Reservation rent, Car car) {

		double sum = calculateExtrasPrice(rent, car) + calculateCarBaseTotalPrice(rent, car);

		return sum;
	}

	public static List<Car> carSort(List<Car> fleet, String priceOrderValue) {
		int n = fleet.size() - 1;
		int k;

		if (priceOrderValue.equalsIgnoreCase("ASCENDANT")) {
			for (int m = n; m >= 0; m--) {
				for (int i = 0; i < n; i++) {
					k = i + 1;
					if (fleet.get(i).getCategory().getPriceBase() > fleet.get(k).getCategory().getPriceBase()) {
						Collections.swap(fleet, i, k);
					}
				}
			}
		} else {
			for (int m = n; m >= 0; m--) {
				for (int i = 0; i < n; i++) {
					k = i + 1;
					if (fleet.get(i).getCategory().getPriceBase() < fleet.get(k).getCategory().getPriceBase()) {
						Collections.swap(fleet, i, k);
					}
				}
			}
		}

		return fleet;
	}

	public static long createReservationNumber(Client client, Reservation rent) {

		List<String> values = new ArrayList<String>();

		values.add(client.getName());
		values.add(client.getIdNumber());
		values.add(rent.getCarCategory());
		values.add(rent.getOfficeCode());

		long result = 17;
		for (String s : values) {
			if (s != null) {
				result = (37 * result + s.hashCode());
				if (result < 0) {
					(result) *= -1;
				}
			}
		}
		return result;
	}

	public static String decodePass(String pass) {
		Decoder decoder = Base64.getDecoder();
		byte[] bytes = decoder.decode(pass);
		return new String(bytes);
	}

	public static String encodePass(String pass) {

		Encoder encoder = Base64.getEncoder();
		String encodedString = encoder.encodeToString(pass.getBytes());
		return encodedString;

	}
	
}
