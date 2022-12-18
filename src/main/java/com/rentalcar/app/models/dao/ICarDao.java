package com.rentalcar.app.models.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.rentalcar.app.models.entity.Car;
import com.rentalcar.app.models.entity.TypeTransmission;

public interface ICarDao extends CrudRepository<Car, Long> {

	@Query("select u from Car u where u.category.codCategory = ?1")
	public Optional<List<Car>> findByCategory(String category);
	
	@Query("select u from Car u where u.transmission = ?1")
	public Optional<List<Car>> findByTransmission(TypeTransmission transmission);
	
	@Query("select u from Car u where u.transmission = ?1 and u.category.codCategory = ?2")
	public Optional<List<Car>> findByTransmissionAndCategory(TypeTransmission transmission, String category);
}
