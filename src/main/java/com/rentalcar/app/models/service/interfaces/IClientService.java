package com.rentalcar.app.models.service.interfaces;

import java.util.List;
import com.rentalcar.app.models.entity.Client;

public interface IClientService {
	
	public List<Client> findAll();
	public Client findById(Long id);
	public void save(Client client);
	public void delete(Client client);
	public Client findByidNumer(String idNumber);
	public Client findByUser(String user);

}
