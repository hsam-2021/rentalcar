package com.rentalcar.app.models.service.interfaces;

import java.util.List;

import com.rentalcar.app.models.entity.CommonExtra;

public interface ICommonExtraService {
	public List<CommonExtra> findAll();
	public CommonExtra findbyId(Long id);
}
