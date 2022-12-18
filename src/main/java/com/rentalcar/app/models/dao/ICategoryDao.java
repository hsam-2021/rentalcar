package com.rentalcar.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rentalcar.app.models.entity.Category;

public interface ICategoryDao extends CrudRepository<Category, Long> {

	@Query("select u from Category u where u.codCategory = ?1")
	public Category findCategoryByCode(String code);
	
}
