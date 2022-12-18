package com.rentalcar.app.models.dao;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rentalcar.app.models.entity.Reservation;

public interface IReservationDao extends CrudRepository<Reservation, Long> {

	
	  @Query("select u from Reservation u where u.initDate = ?1") 
	  public Optional<Reservation> findByDate(Date date);
	  
	  @Query("select u from Reservation u where u.client.idNumber = ?1") 
	  public Optional<Reservation> findByIdNumber(String idNumber);
}
