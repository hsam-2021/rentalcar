package com.rentalcar.app.models.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
public class Car implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_car")
	private Long idCar;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "category_id")
	private Category category;

	private String model;
	private String marca;

	@Enumerated(EnumType.STRING)
	private TypeTransmission transmission;

	private boolean ac;
	private int seats;
	private int doors;

	@Column(name = "suit_cases")
	private int suitCases;

	private String photo;

	public Car() {

	}

	public Car(Category category, String model, String marca, TypeTransmission transmission, boolean ac, int seats,
			int doors, int suitCases, String photo) {
		this.category = category;
		this.model = model;
		this.marca = marca;
		this.transmission = transmission;
		this.ac = ac;
		this.seats = seats;
		this.doors = doors;
		this.suitCases = suitCases;
		this.photo = photo;
	}

	public Long getIdCar() {
		return idCar;
	}

	public void setIdCar(Long idCar) {
		this.idCar = idCar;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public TypeTransmission getTransmission() {
		return transmission;
	}

	public void setTransmission(TypeTransmission transmission) {
		this.transmission = transmission;
	}

	public boolean isAc() {
		return ac;
	}

	public void setAc(boolean ac) {
		this.ac = ac;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public int getDoors() {
		return doors;
	}

	public void setDoors(int doors) {
		this.doors = doors;
	}

	public int getSuitCases() {
		return suitCases;
	}

	public void setSuitCases(int suitCases) {
		this.suitCases = suitCases;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
