package com.rentalcar.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.rentalcar.app.models.entity.Client;

@Repository
public interface ClientsRepository extends JpaRepository<Client, Long> {
	@Query("select a from Client a where a.idClient =:clientId")
	public Client getClient(@Param("clientId") int clientId);
}
