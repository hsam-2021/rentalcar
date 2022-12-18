package com.rentalcar.app.models.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rentalcar.app.models.dao.IReservationDao;
import com.rentalcar.app.models.entity.Client;
import com.rentalcar.app.models.entity.Reservation;
import com.rentalcar.app.models.entity.ReservationV;
import com.rentalcar.app.models.service.interfaces.IReservationService;
import com.rentalcar.app.repository.ClientsRepository;
import com.rentalcar.app.repository.ReservationRepository;
import com.safe.hsap.domainobjects.services.DeleteReservation;
import com.safe.hsap.domainobjects.services.UpdateReservation;

@Service
public class ResercationService implements IReservationService {

	@Autowired
	IReservationDao reservationDao;

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	ClientsRepository clientsRepository;

	@Override
	public void save(Reservation reserv) {
		reservationDao.save(reserv);

	}

	@Override
	public Reservation findByNumReservation(Long reservation) {
		return reservationDao.findById(reservation).orElse(null);
	}

	@Override
	public Reservation findByIdNumber(String idNumber) {
		return reservationDao.findByIdNumber(idNumber).orElse(null);
	}

	@Override
	public List<ReservationV> getReservationDetails(int clientId) {
		String client = null;
		String clientName = null;
		List<ReservationV> reservationList = null;
		List<Reservation> reservation = null;
		client = reservationRepository.getClientName(clientId);
		if (client != null) {
			if (client.equalsIgnoreCase("admin")) {
				if (reservationRepository.findAll().size() > 0) {
					reservation = reservationRepository.findAll();
					reservationList = new ArrayList<ReservationV>();
					for (Reservation res : reservation) {
						ReservationV resv = new ReservationV();
						resv.setBookingAmount(res.getBookingAmount());
						if (res.getCarCategory().equals("CAT_SUP")) {
							resv.setCarType("PLUS");
						} else if (res.getCarCategory().equals("CAT_MED")) {
							resv.setCarType("STANDARD");
						} else {
							resv.setCarType("ECONOMIC");
						}
						resv.setClient(res.getClient().getIdClient());
						clientName = reservationRepository.getClientFirstName(res.getClient().getIdClient()).concat(" ")
								.concat(reservationRepository.getClientLastName(res.getClient().getIdClient()));
						resv.setClientName(clientName);
						resv.setDropoffLoc(res.getOfficeReturnCode());
						resv.setIdReservation(res.getIdReservation());
						resv.setFinalDate(res.getFinalDate());
						resv.setInitDate(res.getInitDate());
						resv.setPickupLoc(res.getOfficeCode());
						reservationList.add(resv);
					}
				}
				return reservationList;
			} else {
				Client clt = clientsRepository.getClient(clientId);
				if (clt != null) {
					if (reservationRepository.findByClient(clt) != null) {
						reservation = reservationRepository.findByClient(clt);
						reservationList = new ArrayList<ReservationV>();
						for (Reservation res : reservation) {
							ReservationV resv = new ReservationV();
							resv.setBookingAmount(res.getBookingAmount());
							if (res.getCarCategory().equals("CAT_SUP")) {
								resv.setCarType("PLUS");
							} else if (res.getCarCategory().equals("CAT_MED")) {
								resv.setCarType("STANDARD");
							} else {
								resv.setCarType("ECONOMIC");
							}
							resv.setClient(clientId);
							clientName = reservationRepository.getClientFirstName(clientId).concat(" ")
									.concat(reservationRepository.getClientLastName(clientId));
							resv.setClientName(client);
							resv.setDropoffLoc(res.getOfficeReturnCode());
							resv.setIdReservation(res.getIdReservation());
							resv.setFinalDate(res.getFinalDate());
							resv.setInitDate(res.getInitDate());
							resv.setPickupLoc(res.getOfficeCode());
							reservationList.add(resv);
						}
					}
				}
				return reservationList;
			}
		} else {
			return reservationList;
		}
	}

	@Override
	public ResponseEntity<String> deleteReservation(DeleteReservation deleteReservation) {
		String message = null;
		if (deleteReservation != null) {
			try {
				reservationDao.deleteById(deleteReservation.getIdReservation());
				// reservationRepository.deleteReservation(deleteReservation.getIdReservation());
				message = "Reservation Deleted Successfully";
			} catch (Exception e) {
				message = "Error Deleting Passenger " + e.getMessage();
			}
		} else {
			message = "No Reservation Records to delete";
		}
		return ResponseEntity.ok().body(message);

	}

	@Override
	public ResponseEntity<String> updateReservation(UpdateReservation updateReservation) {

		String message = null;
		if (updateReservation != null) {
			try {
				reservationRepository.updateProcessedTrainingImage(updateReservation.getStartDate(),
						updateReservation.getEndDate(), updateReservation.getOfficePickCode(),
						updateReservation.getOfficeReturnCode(), updateReservation.getIdReservation());
				message = "Reservation updated Successfully";
			} catch (Exception e) {
				message = "Error Updating Reservation " + e.getMessage();
			}
		} else {
			message = "No Reservation Records to update";
		}

		return ResponseEntity.ok().body(message);

	}
}
