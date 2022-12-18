package com.rentalcar.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category implements Serializable{
	
	private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_category")
	private Long idCategory;
	
	@Column(name = "cod_category")
	private String codCategory;
	
	@Column(name = "price_base")
	private Double priceBase;
	
	@Column(name = "price_base_insurance")
	private Double priceBaseInsurance;
	
	@Column(name = "price_top_insurance")
	private Double priceTopInsurance;
	
	@Column(name = "price_tire_and_glass_protection")
	private Double priceTireAndGlassProtection;

	public Category(String codCategory, Double priceBase, Double priceBaseInsurance, Double priceTopInsurance,
			Double priceTireAndGlassProtection) {
		
		this.codCategory = codCategory;
		this.priceBase = priceBase;
		this.priceBaseInsurance = priceBaseInsurance;
		this.priceTopInsurance = priceTopInsurance;
		this.priceTireAndGlassProtection = priceTireAndGlassProtection;
	}
	
	public Category() {
		
	}
	
	public Long getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Long idCategory) {
		this.idCategory = idCategory;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCodCategory() {
		return codCategory;
	}

	public void setCodCategory(String codCategory) {
		this.codCategory = codCategory;
	}

	public Double getPriceBase() {
		return priceBase;
	}

	public void setPriceBase(Double priceBase) {
		this.priceBase = priceBase;
	}

	public Double getPriceBaseInsurance() {
		return priceBaseInsurance;
	}

	public void setPriceBaseInsurance(Double priceBaseInsurance) {
		this.priceBaseInsurance = priceBaseInsurance;
	}

	public Double getPriceTopInsurance() {
		return priceTopInsurance;
	}

	public void setPriceTopInsurance(Double priceTopInsurance) {
		this.priceTopInsurance = priceTopInsurance;
	}

	public Double getPriceTireAndGlassProtection() {
		return priceTireAndGlassProtection;
	}

	public void setPriceTireAndGlassProtection(Double priceTireAndGlassProtection) {
		this.priceTireAndGlassProtection = priceTireAndGlassProtection;
	}

}
