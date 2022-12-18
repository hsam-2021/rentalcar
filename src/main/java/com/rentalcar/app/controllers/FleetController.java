package com.rentalcar.app.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rentalcar.app.models.entity.Car;
import com.rentalcar.app.models.entity.Reservation;
import com.rentalcar.app.models.entity.TypeTransmission;
import com.rentalcar.app.models.service.interfaces.ICarService;

@Controller
public class FleetController {

	@Autowired
	ICarService carService;
	
	@GetMapping("/fleet")
	public String showFleet(HttpSession session, Model model) {
		
		if (session.getAttribute("fleet") == null) {
			session.setAttribute("fleet", carService.findAll());
		}
		return "fleet/index";
	}
	
	
	@GetMapping("/selectCar")
	public String showPeriodSelector(HttpSession session, @ModelAttribute("reservation") Reservation rent, @RequestParam("id") Long idCar) {
		session.setAttribute("car", carService.findById(idCar));
		rent.setCarCategory(carService.findById(idCar).getCategory().getCodCategory());
		session.setAttribute("reservation", rent);
		return "redirect:/reservation/dateselection/";
	}
	
	@PostMapping("/fleet/filter")
	public String filterCarFleet(HttpSession session,
			@RequestParam(name="categorySelection") String categoryValue,
			@RequestParam(name="transmissionSelection") TypeTransmission transmissionValue,
			@RequestParam(name="priceOrderSelection") String priceOrderValue) {
		
		List<Car> fleet = carService.findAll();
		List<Car> filteredFleet = new ArrayList<Car>();
		
			if (!categoryValue.isEmpty()) {
				session.setAttribute("category", categoryValue);
				for (Car c : fleet) {
					if (c.getCategory().getCodCategory().equalsIgnoreCase(categoryValue)) {
						filteredFleet.add(c);
					}
				}
				fleet.clear();
				fleet.addAll(filteredFleet);
			}else {
				session.removeAttribute("category");
			}
			
			if (transmissionValue != null) {
				session.setAttribute("transmission", transmissionValue.toString());
				filteredFleet.clear();
				for (Car c : fleet) {
					if (c.getTransmission().equals(transmissionValue)) {
						filteredFleet.add(c);
					}
				}
				fleet.clear();
				fleet.addAll(filteredFleet);
			} else {
				session.removeAttribute("transmission");
			}
			
			if (priceOrderValue != null) {
				session.setAttribute("priceOrder", priceOrderValue);
				if (!fleet.isEmpty()) {
					filteredFleet = Utils.carSort(fleet, priceOrderValue);
				}
			} else {
				session.removeAttribute("priceOrder");
			}
		
		session.setAttribute("fleet", filteredFleet);	
		
		return "redirect:/fleet";
	}
	
}
