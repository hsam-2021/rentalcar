package com.rentalcar.app.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.rentalcar.app.models.service.interfaces.IReservationService;

@Controller
public class SuccessController {

	@Autowired
	IReservationService rentService;
	
	@GetMapping("/reservation/success")
	public String showSuccessPage(HttpSession session) {

		return "reservation/success/index";
	}
	
}
