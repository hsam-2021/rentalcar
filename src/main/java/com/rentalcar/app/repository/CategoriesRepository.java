package com.rentalcar.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.rentalcar.app.models.entity.Category;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, Long>{ 
	
	@Query("Select a from Category a where a.codCategory =:codCategory")
	public Category getCatId(@Param("codCategory") String codCategory);

}
