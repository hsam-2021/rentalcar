package com.rentalcar.app.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rentalcar.app.models.entity.CommonExtra;
import com.rentalcar.app.models.entity.Office;
import com.rentalcar.app.models.entity.Reservation;
import com.rentalcar.app.models.service.interfaces.ICommonExtraService;
import com.rentalcar.app.models.service.interfaces.IOfficeMasterService;

@Controller
public class ExtrasController {
	
	@Autowired
	ICommonExtraService extraService;
	
	@Autowired
	IOfficeMasterService officeService;
	
	
	@GetMapping("/reservation/extrasconfig")
	public String showExtras (Model model, HttpSession session) {
		
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
		model.addAttribute("extras", extraService.findAll());
		return "reservation/extrasconfig/index";
	}
	
	@PostMapping("/reservation/extrasconfig")
	public String setExtras (@RequestParam(value="idExtra", required=false) List<Long> values,
			@RequestParam(value="insurance") String insuranceValue,
			@RequestParam(value="priceTireAndGlassProtection", required=false) String tireAndGlassProtection,
			HttpSession session) {
		Reservation rent = (Reservation) session.getAttribute("reservation");
		List<CommonExtra> extras = new ArrayList<CommonExtra>();
		
		if (insuranceValue.equalsIgnoreCase("basicInsurance")) {
			rent.setTopInsurance(false);
		} else {
			rent.setTopInsurance(true);
		}
		
		if (tireAndGlassProtection != null) {
			rent.setTireAndGlassProtection(true);
		} else {
			rent.setTireAndGlassProtection(false);
		}
		
		if (values != null) {
			for (Long s:values) {
				extras.add(extraService.findbyId(s));
			}
		}
		rent.setExtras(extras);
		session.setAttribute("reservation", rent);
		return "redirect:/reservation/customerdetails/";
	}
		
	
}
