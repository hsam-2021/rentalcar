package com.rentalcar.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.rentalcar.app.models.entity.Office;

public interface IOfficeDao extends CrudRepository<Office, Long> {

}
