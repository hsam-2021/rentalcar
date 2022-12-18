package com.rentalcar.app.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.rentalcar.app.models.entity.Car;
import com.rentalcar.app.models.entity.Client;
import com.rentalcar.app.models.entity.Reservation;
import com.rentalcar.app.models.service.interfaces.IReservationService;

@Controller
public class ConfirmationController {

	@Autowired
	IReservationService rentService;

	@GetMapping("/reservation/confirmation")
	public String showConfirmation(Model model, HttpSession session) {

		Reservation rent = (Reservation) session.getAttribute("reservation");
		Car car = (Car) session.getAttribute("car");
		model.addAttribute("sumExtras", Utils.calculateExtrasPrice(rent, car));
		model.addAttribute("sumCarBase", Utils.calculateCarBaseTotalPrice(rent, car));
		model.addAttribute("sumRentPrice", Utils.CalculateTotalReservationPrice(rent, car));

		return "reservation/confirmation/index";
	}

	@PostMapping("/reservation/confirmation")
	public String processConfirmation(HttpSession session) {
		Reservation rent = (Reservation) session.getAttribute("reservation");
		Client client = (Client) session.getAttribute("client");
		Car car = (Car) session.getAttribute("car");
		rent.setCarCategory(car.getCategory().getCodCategory());
		rent.setBookingAmount(Utils.CalculateTotalReservationPrice(rent, car));
		rent.setClient(client);
		rentService.save(rent);

		session.setAttribute("reservation", rent);

		return "redirect:/reservation/success";
	}

}
