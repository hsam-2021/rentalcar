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
import com.rentalcar.app.models.entity.Office;
import com.rentalcar.app.models.entity.Reservation;
import com.rentalcar.app.models.entity.TypeTransmission;
import com.rentalcar.app.models.service.interfaces.ICarService;
import com.rentalcar.app.models.service.interfaces.IOfficeMasterService;

@Controller
public class VehicleSelectionController {

	@Autowired
	ICarService carService;
	
	@Autowired
	IOfficeMasterService officeService;
	
	@GetMapping("/reservation/vehicleselect")
	public String showVehicleList (HttpSession session, Model model) {	
		
		model.addAttribute("offices", officeService.findAll());
		
		Reservation rent = (Reservation) session.getAttribute("reservation");
		String officeCode = rent.getOfficeCode();
		String officeReturnCode = rent.getOfficeReturnCode();
		
		List<Office> offices = officeService.findAll();
		for (Office o : offices) {
			if (o.getOfficeCode().equalsIgnoreCase(officeCode)) {
				session.setAttribute("officeName", o.getOfficeName());		
			}
			
			if (o.getOfficeCode().equalsIgnoreCase(officeReturnCode)) {
				session.setAttribute("officeReturnName", o.getOfficeName());
			}
		}
		
		if (session.getAttribute("fleet") == null) {
			session.setAttribute("fleet", carService.findAll());
		}
		return "reservation/vehicleselect/index";
	}
	
	@GetMapping("/reservation/vehicleselect/selectCar")
	public String showPeriodSelector(HttpSession session, @RequestParam("id") Long idCar) {
		
		session.setAttribute("car", carService.findById(idCar));
		Reservation rent = (Reservation) session.getAttribute("reservation");
		rent.setCarCategory(carService.findById(idCar).getCategory().getCodCategory());
		session.setAttribute("reservation", rent);
		
		return "redirect:/reservation/extrasconfig";
	}
	
	@PostMapping("/reservation/vehicleselect")
	public String changeDates(@ModelAttribute("reservation") Reservation newDates, HttpSession session) {
		Reservation rent = (Reservation) session.getAttribute("reservation");
		
		if (newDates.getOfficeCode() != null) {
			rent.setOfficeCode(newDates.getOfficeCode());
		}
		
		if (newDates.getOfficeReturnCode() != null) {
			rent.setOfficeReturnCode(newDates.getOfficeReturnCode());
		}
		
		if (newDates.getInitDate() != null) {
			rent.setInitDate(newDates.getInitDate());
		}
		
		if (newDates.getFinalDate() != null) {
			rent.setFinalDate(newDates.getFinalDate());
		}
		
		if (initDateIsBeforeFinalDate(rent)) {
			session.setAttribute("reservation", rent);
		} else {
			session.setAttribute("errorDates", "Pick up date must be before return date!");
			return "reservation/dateselection/index"; 
		}
		
		return "redirect:/reservation/vehicleselect";
	}
	
	public boolean initDateIsBeforeFinalDate(Reservation rent) {
		if (rent.getInitDate().compareTo(rent.getFinalDate()) > 0) {
			return false;
		} else {
			return true;
		}
	}
	
	@PostMapping("/reservation/vehicleselect/filter")
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
		
		return "redirect:/reservation/vehicleselect";
	}
	
	
}
