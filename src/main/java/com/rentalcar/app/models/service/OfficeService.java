package com.rentalcar.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentalcar.app.models.dao.IOfficeDao;
import com.rentalcar.app.models.entity.Office;
import com.rentalcar.app.models.service.interfaces.IOfficeMasterService;

@Service
public class OfficeService implements IOfficeMasterService{

	@Autowired
	IOfficeDao officeDao;
	
	@Override
	public List<Office> findAll() {
		return (List<Office>) officeDao.findAll();
	}

	@Override
	public String findByIdAndReturnOfficeCode(Long idOffice) {
		return officeDao.findById(idOffice).get().getOfficeCode();
		
	}

}
