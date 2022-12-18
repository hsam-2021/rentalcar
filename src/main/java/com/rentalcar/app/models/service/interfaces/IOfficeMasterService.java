package com.rentalcar.app.models.service.interfaces;

import java.util.List;

import com.rentalcar.app.models.entity.Office;

public interface IOfficeMasterService {
	
	public List<Office> findAll();
	public String findByIdAndReturnOfficeCode(Long idOffice);
}
