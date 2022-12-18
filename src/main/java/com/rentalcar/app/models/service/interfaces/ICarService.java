package com.rentalcar.app.models.service.interfaces;

import java.util.List;

import com.rentalcar.app.models.entity.Car;
import com.rentalcar.app.models.entity.TypeTransmission;

public interface ICarService {
	public List<Car> findAll();
	public Car findById(Long id);
	public List<Car> findByCategory(String category);
	public List<Car> findByTransmission(TypeTransmission transmission);
	public List<Car> findByTransmissionAndCategory(TypeTransmission transmission, String Category);
	

}
