package com.rentalcar.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "offices_master")
public class Office implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_office")
	private Long idOficce;

	@Column(name = "office_code")
	private String officeCode;

	@Column(name = "office_name")
	private String officeName;

	private String address;

	@Column(name = "post_code")
	private String postCode;

	private String city;
	private String country;

	public Office() {

	}

	public Office(String officeCode, String officeName, String address, String postCode, String city, String country) {
		this.officeCode = officeCode;
		this.officeName = officeName;
		this.address = address;
		this.postCode = postCode;
		this.city = city;
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Long getIdOficce() {
		return idOficce;
	}

	public void setIdOficce(Long idOficce) {
		this.idOficce = idOficce;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
