package com.rentalcar.app.models.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

public class ReservationV implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idReservation;

	private int client;

	private String clientName;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date initDate;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date finalDate;

	private String pickupLoc;

	private String dropoffLoc;

	private String carType;

	private Double bookingAmount;

	public Long getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(Long idReservation) {
		this.idReservation = idReservation;
	}

	public int getClient() {
		return client;
	}

	public void setClient(int client) {
		this.client = client;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Date getInitDate() {
		return initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	public Date getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}

	public String getPickupLoc() {
		return pickupLoc;
	}

	public void setPickupLoc(String pickupLoc) {
		this.pickupLoc = pickupLoc;
	}

	public String getDropoffLoc() {
		return dropoffLoc;
	}

	public void setDropoffLoc(String dropoffLoc) {
		this.dropoffLoc = dropoffLoc;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public Double getBookingAmount() {
		return bookingAmount;
	}

	public void setBookingAmount(Double bookingAmount) {
		this.bookingAmount = bookingAmount;
	}

}
