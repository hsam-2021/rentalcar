package com.rentalcar.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rentalcar.app.models.entity.Office;

@Repository
public interface OfficeMasterRepository extends JpaRepository<Office, Long>{ 

}
