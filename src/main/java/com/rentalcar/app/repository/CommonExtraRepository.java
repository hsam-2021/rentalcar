package com.rentalcar.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rentalcar.app.models.entity.CommonExtra;

@Repository
public interface CommonExtraRepository extends JpaRepository<CommonExtra, Long>{

}
