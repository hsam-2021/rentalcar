package com.rentalcar.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentalcar.app.models.dao.ICommonExtraDao;
import com.rentalcar.app.models.entity.CommonExtra;
import com.rentalcar.app.models.service.interfaces.ICommonExtraService;

@Service
public class CommonExtraService implements ICommonExtraService {

	@Autowired
	ICommonExtraDao extraService;
	
	@Override
	public List<CommonExtra> findAll() {
		return (List<CommonExtra>) extraService.findAll();
	}

	@Override
	public CommonExtra findbyId(Long id) {
		return extraService.findById(id).orElse(null);
	}

}
