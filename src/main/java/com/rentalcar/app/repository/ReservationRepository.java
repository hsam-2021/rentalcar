package com.rentalcar.app.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.rentalcar.app.models.entity.Reservation;
import com.rentalcar.app.models.entity.Client;

@Repository
public interface ReservationRepository  extends JpaRepository<Reservation, Long>{

	public List<Reservation> findByClient(Client client);
	
	public List<Reservation> findAll();
	
	@Query("select a.userName from Client a where a.idClient =:clientId") 
	public String getClientName(@Param("clientId") int clientId);
	
	@Query("select a.name from Client a where a.idClient =:clientId") 
	public String getClientFirstName(@Param("clientId") int clientId);
	
	@Query("select a.surname from Client a where a.idClient =:clientId") 
	public String getClientLastName(@Param("clientId") int clientId);
	
	@Modifying
	@Transactional
	@Query("update Reservation a set a.initDate =:startDate, a.finalDate =:endDate ,officeCode =:officePickCode,officeReturnCode =:officeReturnCode where a.idReservation =:idReservation")
	public void updateProcessedTrainingImage( @Param("startDate") Date startDate,@Param("endDate") Date endDate,@Param("officePickCode") String officePickCode, @Param("officeReturnCode") String officeReturnCode, @Param("idReservation") Long idReservation);
		
	@Modifying
	@Transactional
	@Query("delete from Reservation where idReservation =:idReservation")
	public void deleteReservation(@Param("idReservation") Long idReservation);
}
