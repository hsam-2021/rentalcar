package com.rentalcar.app.models.service.interfaces;

import java.util.List;

import com.rentalcar.app.models.entity.Category;

public interface ICategoryService {

	public List<Category> findAll();
	public Category findCategoryByCode(String category);
	
}
