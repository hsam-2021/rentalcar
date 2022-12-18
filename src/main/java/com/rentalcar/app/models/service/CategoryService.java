package com.rentalcar.app.models.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentalcar.app.models.dao.ICategoryDao;
import com.rentalcar.app.models.entity.Category;
import com.rentalcar.app.models.service.interfaces.ICategoryService;


@Service
public class CategoryService implements ICategoryService {

	@Autowired
	ICategoryDao categoryService;
		
	@Override
	public Category findCategoryByCode(String category) {
		return categoryService.findCategoryByCode(category);
	}

	@Override
	public List<Category> findAll() {
		return (List<Category>) categoryService.findAll();
	}

}
