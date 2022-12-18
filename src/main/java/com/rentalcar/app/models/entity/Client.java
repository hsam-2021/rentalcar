package com.rentalcar.app.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Entity
@Table(name = "clients")
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_cliente")
	private int idClient;

	@NotEmpty(message = "The field is not optional")
	private String name;

	@NotEmpty(message = "The field is not optional")
	private String surname;

	@NotEmpty(message = "The field is not optional")
	@Column(name = "id_number")
	private String idNumber;

	@NotEmpty(message = "The field is not optional")
	@Column(name = "phone_number")
	private String phoneNumber;

	@NotEmpty(message = "The field is not optional")
	@Email(message = "formato incorrecto")
	private String email;

	private String address;
	private String postCode;
	private String city;
	private String country;

	@NotEmpty(message = "The field is not optional")
	@Column(name = "user_name")
	private String userName;

	@NotEmpty(message = "The field is not optional")
	private String password;

	@NotEmpty(message = "The field is not optional")
	private String confirmPassword;

	public Client() {

	}

	public Client(@NotEmpty(message = "The field is not optional") String name,
			@NotEmpty(message = "The field is not optional") String surname,
			@NotEmpty(message = "The field is not optional") String idNumber,
			@NotEmpty(message = "The field is not optional") String phoneNumber,
			@NotEmpty(message = "The field is not optional") @Email(message = "Incorrect Format") String email,
			String address, String postCode, String city, String country,
			@NotEmpty(message = "The field is not optional") String userName,
			@NotEmpty(message = "The field is not optional") String password,
			@NotEmpty(message = "The field is not optional") String confirmPassword) {
		super();
		this.name = name;
		this.surname = surname;
		this.idNumber = idNumber;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.postCode = postCode;
		this.city = city;
		this.country = country;
		this.userName = userName;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String user) {
		this.userName = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
