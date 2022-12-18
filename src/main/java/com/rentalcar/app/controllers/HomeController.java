package com.rentalcar.app.controllers;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.rentalcar.app.models.entity.Reservation;
import com.rentalcar.app.models.service.interfaces.IOfficeMasterService;

@Controller
public class HomeController {

	@Autowired
	IOfficeMasterService officeService;
	
	@GetMapping("/")
	public String showHome (HttpSession session, Model model) {		
		Reservation rent = new Reservation();
		session.setAttribute("reservation", rent);
		model.addAttribute("offices", officeService.findAll());
		return "index";
	}
	
	@PostMapping("/")
	public String setDatesToRent(HttpSession session, @ModelAttribute("reservation") Reservation rent) {
		
		if (initDateIsBeforeFinalDate(rent)) {
			session.setAttribute("reservation", rent);
			session.setAttribute("differenceInDays", Utils.calculateDifferenceInDays (rent));
		} else {
			session.setAttribute("errorDates", "Pick up date must be before return date!");
			return "redirect:/"; 
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
	
}
