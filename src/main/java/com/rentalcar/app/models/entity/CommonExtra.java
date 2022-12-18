package com.rentalcar.app.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "common_extras")
public class CommonExtra implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_extra")
	private Long idExtra;

	@Column(name = "description_extra")
	private String descriptionExtra;
	private Double price;
	
	@ManyToMany(mappedBy = "extras")
	private List<Reservation> reservations;

	public CommonExtra() {

	}

	public CommonExtra(String descriptionExtra, Double price) {
		super();
		this.descriptionExtra = descriptionExtra;
		this.price = price;
	}

	public Long getIdExtra() {
		return idExtra;
	}

	public void setIdExtra(Long idExtra) {
		this.idExtra = idExtra;
	}

	public String getDescriptionExtra() {
		return descriptionExtra;
	}

	public void setDescriptionExtra(String descriptionExtra) {
		this.descriptionExtra = descriptionExtra;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
