package com.rentalcar.app.models.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.rentalcar.app.models.entity.Reservation;
import com.rentalcar.app.models.entity.ReservationV;
import com.safe.hsap.domainobjects.services.DeleteReservation;
import com.safe.hsap.domainobjects.services.UpdateReservation;

public interface IReservationService {

	public void save(Reservation reserv);

	public Reservation findByNumReservation(Long reservation);

	public Reservation findByIdNumber(String idNumber);

	public List<ReservationV> getReservationDetails(int clientId);

	public ResponseEntity<String> deleteReservation(DeleteReservation deleteReservation);

	public ResponseEntity<String> updateReservation(UpdateReservation updateReservation);
}
