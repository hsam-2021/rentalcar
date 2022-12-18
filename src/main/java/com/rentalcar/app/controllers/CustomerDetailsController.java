package com.rentalcar.app.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.rentalcar.app.controllers.beans.LoginBean;
import com.rentalcar.app.models.entity.Client;
import com.rentalcar.app.models.service.interfaces.IClientService;
import com.rentalcar.app.models.service.interfaces.ICommonExtraService;

@Controller
public class CustomerDetailsController {

	@Autowired
	ICommonExtraService extraService;

	@Autowired
	IClientService clientService;

	@GetMapping("/reservation/customerdetails")
	public String showCustomerDetailsForm() {
		return "reservation/customerdetails/index";
	}
	
	@GetMapping("/reservationDetails")
	public String getReservationDetails() {
		return "reservationDetails";
	}

	@PostMapping("/reservation/customerdetails")
	public String loginProcess(@ModelAttribute("login") LoginBean login, HttpSession session) {

		Client client = clientService.findByUser(login.getUserName());

		if (null != login && client != null && login.getPassword().equals(Utils.decodePass(client.getPassword()))) {
			session.setAttribute("client", client);
		} else {
			session.setAttribute("error_userAuthentification", "Username or password is wrong!");
			return "reservation/customerdetails/index";
		}
		return "redirect:/reservation/customerdetails";
	}

	@PostMapping("/reservation/customerdetails/registration")
	public String clientRegistration(HttpSession session, @ModelAttribute("registration") Client newClient) {
		if (Utils.isValid(newClient)) {
			if (!exists(newClient)) {
				clientService.save(newClient);
			} else {
				session.setAttribute("error_usernameTaken", "Sorry, this Username already exists");
				return "reservation/customerdetails/index";
			}
		} else {
			if (!newClient.getPassword().equals(newClient.getConfirmPassword())) {
				session.setAttribute("errorMessagePassMismatch", "Sorry, Password and Confirm Password Mismatch");
				return "reservation/customerdetails/index";
			} else {
				session.setAttribute("errorMessage", "Sorry, make sure to fill all the fields before continue");
				return "reservation/customerdetails/index";

			}
		}
		session.setAttribute("client", newClient);
		session.removeAttribute("tempClient");
		return "redirect:/reservation/customerdetails";
	}

	public boolean exists(Client client) {
		if (clientService.findByUser(client.getUserName()) != null) {
			return true;
		}
		return false;
	}

}
