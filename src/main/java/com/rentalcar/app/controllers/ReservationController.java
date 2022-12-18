package com.rentalcar.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rentalcar.app.models.entity.ReservationV;
import com.rentalcar.app.models.service.interfaces.IReservationService;
import com.safe.hsap.domainobjects.services.DeleteReservation;
import com.safe.hsap.domainobjects.services.UpdateReservation;

@RestController
public class ReservationController {
	@Autowired
	IReservationService rentService;
	
	@RequestMapping(value = "/getCustomerReservation", method = RequestMethod.GET)
	public List<ReservationV> getReservationDetails(@RequestParam(value = "clientId") int clientId){
		return rentService.getReservationDetails(clientId);
	}
	
	@RequestMapping(value = "/deleteReservation", method = RequestMethod.POST)
	public ResponseEntity<String>  deleteReservation(@RequestBody DeleteReservation deleteReservation){
		return rentService.deleteReservation(deleteReservation);
	}
	
	@RequestMapping(value = "/updateReservation", method = RequestMethod.POST)
	public ResponseEntity<String>  updateReservation(@RequestBody UpdateReservation updateReservation){
		return rentService.updateReservation(updateReservation);
	}
	
}
