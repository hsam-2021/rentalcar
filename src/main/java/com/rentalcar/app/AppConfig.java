package com.rentalcar.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rentalcar.app.controllers.beans.LoginBean;
import com.rentalcar.app.models.entity.Reservation;


@Configuration
public class AppConfig {
	
	@Bean
	public LoginBean myBean() {
		return new LoginBean();
	}
	
	@Bean
	public Reservation myRent() {
		return new Reservation();
	}
	
}
