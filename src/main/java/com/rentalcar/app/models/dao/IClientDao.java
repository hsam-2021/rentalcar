package com.rentalcar.app.models.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rentalcar.app.models.entity.Client;


public interface IClientDao extends CrudRepository<Client, Long> {

	
	  @Query("select u from Client u where u.idNumber = ?1") public
	  Optional<Client> findByidNumer(String idNumber);
	  
	  @Query("select u from Client u where u.userName = ?1") public
	  Optional<Client> findByUser(String user);
	 
}
