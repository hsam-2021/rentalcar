package com.rentalcar.app.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.rentalcar.app.controllers.beans.LoginBean;
import com.rentalcar.app.models.entity.Client;
import com.rentalcar.app.models.service.interfaces.IClientService;

@Controller
public class UserController {

	@Autowired
	IClientService clientService;

	@GetMapping("/user/login")
	public String showLoginForm(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "user/login/index";
	}

	@PostMapping("/user/login")
	public String loginProcess(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("login") LoginBean login, HttpSession session) {

		Client client = clientService.findByUser(login.getUserName());
		if (null != login && client != null && login.getPassword().equals(Utils.decodePass(client.getPassword()))) {
			session.setAttribute("client", client);
		} else {
			session.setAttribute("error_userAuthentification", "Username or password is wrong!");
			return "user/login/index";
		}
		return "redirect:/";
	}

	@GetMapping(value = "/user/logout")
	public String logout(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		httpSession.invalidate();
		return "redirect:/";
	}

	@GetMapping("/user/registration")
	public String showRegisterForm(HttpServletRequest request, HttpServletResponse response) {
		Client client = new Client();
		HttpSession session = request.getSession(true);
		session.setAttribute("tempClient", client);
		return "user/registration/index";
	}

	@PostMapping("/user/registration")
	public String registerProcess(HttpSession session, @ModelAttribute("registration") Client newClient) {
		session.setAttribute("errorMessagePassMismatch", null);
		session.setAttribute("errorMessage", null);
		session.setAttribute("errorMessage", null);

		if (Utils.isValid(newClient)) {
			if (!exists(newClient)) {
				clientService.save(newClient);
			} else {
				session.setAttribute("error_usernameTaken", "Sorry, this Username already exists");
				return "user/registration/index";
			}
		} else {
			if (!newClient.getPassword().equals(newClient.getConfirmPassword())) {
				session.setAttribute("errorMessagePassMismatch", "Sorry, Password and Confirm Password Mismatch");
				return "user/registration/index";
			} else {
				session.setAttribute("errorMessage", "Sorry, make sure to fill all the fields before continue");
				return "user/registration/index";
			}
		}
		session.setAttribute("client", newClient);
		session.removeAttribute("tempClient");
		return "redirect:/";
	}

	public boolean exists(Client client) {
		if (clientService.findByUser(client.getUserName()) != null) {
			return true;
		}
		return false;
	}
}
