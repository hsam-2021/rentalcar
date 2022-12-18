package com.rentalcartest.app;

import static org.junit.Assert.assertNotNull;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rentalcar.app.models.entity.Client;
import com.rentalcar.app.models.entity.Reservation;
import com.rentalcar.app.models.entity.TypeTransmission;
import com.rentalcar.app.models.service.interfaces.ICarService;
import com.rentalcar.app.models.service.interfaces.ICategoryService;
import com.rentalcar.app.models.service.interfaces.IClientService;
import com.rentalcar.app.models.service.interfaces.IOfficeMasterService;
import com.rentalcar.app.models.service.interfaces.IReservationService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringCarApplicationTests {

	@Autowired
	IOfficeMasterService serviceOffice;
	@Autowired
	ICarService carService;
	@Autowired
	ICategoryService categoryService;
	@Autowired
	IClientService clientService;
	@Autowired
	IReservationService reservationService;
	
	//Test Office
	@Test
	public void caragarOficinas() {
		assertNotNull(serviceOffice.findAll());
	}
	
	@Test
	public void cargarCodigoOficina() {
		assertNotNull(serviceOffice.findByIdAndReturnOfficeCode(1L));
	}

	//Test Cars
	@Test
	public void cargarCoches() {
		assertNotNull(carService.findAll());
	}
	
	@Test
	public void cargarCochesPorCategoria() {
		assertNotNull(carService.findByCategory("CAT_SUP"));
	}
	
	@Test
	public void cargarCochesPorTransmision() {
		assertNotNull(carService.findByTransmission(TypeTransmission.AUTOMATIC));
	}
	
	@Test
	public void cargarCochesPorTransmisionAndCategoria() {
		assertNotNull(carService.findByTransmissionAndCategory(TypeTransmission.AUTOMATIC, "CAT_SUP"));
	}
	
	//Test Category
	@Test
	public void cargarCategorias() {
		assertNotNull(categoryService.findAll());
	}

	@Test
	public void cargarCategoriasPorCódigo() {
		assertNotNull(categoryService.findCategoryByCode("CAT_SUP"));
	}
	
	//Test client
	@Test
	public void saveClient() {
		Client client = new Client("Cliente 1", "Apellidos cliente 1", "39100579A", "607908970",
				"cliente@gmail.com", "Nápoles, 118", "08013", "Barcelona", "Spain", "user2", "12345","12345");
		
		clientService.save(client);
		assertNotNull(clientService.findById(1L));
	}
	
	@Test 
	public void findClientByUserName() {
		Client client = new Client("Cliente 1", "Apellidos cliente 1", "39100579B", "607908970",
				"cliente@gmail.com", "Nápoles, 118", "08013", "Barcelona", "Spain", "user2", "12345","12345");
		
		clientService.save(client);
		assertNotNull(clientService.findByUser("user2"));
	}
	
	@Test
	public void findClientByidNumber() {
		Client client = new Client("Cliente 1", "Apellidos cliente 1", "39100579C", "607908970",
				"cliente@gmail.com", "Nápoles, 118", "08013", "Barcelona", "Spain", "user3", "12345","12345");
		
		clientService.save(client);
		assertNotNull(clientService.findByidNumer("39100579C"));
	}
	
	// Test Reservation
	@Test
	public void saveReservation() {
		Client client = new Client("Cliente 1", "Apellidos cliente 1", "39100579F", "607908970",
				"cliente@gmail.com", "Nápoles, 118", "08013", "Barcelona", "Spain", "user3", "12345","12345");
		String officeCode = serviceOffice.findByIdAndReturnOfficeCode(1L);
		Date date = new Date();
		Reservation res = new Reservation(1001L, date, date, date, date, client, officeCode, "CAT_SUP");
		reservationService.save(res);
		assertNotNull(reservationService.findByIdNumber("39100579F"));
	}
	
	@Test
	public void findReservationByDni() {
		Client client = new Client("Cliente 1", "Apellidos cliente 1", "39100579G", "607908970",
				"cliente@gmail.com", "Nápoles, 118", "08013", "Barcelona", "Spain", "user3", "12345","12345");
		String officeCode = serviceOffice.findByIdAndReturnOfficeCode(1L);
		Date date = new Date();
		Reservation res = new Reservation(1001L, date, date, date, date, client, officeCode, "CAT_SUP");
		reservationService.save(res);
		assertNotNull(reservationService.findByIdNumber("39100579G"));
	}
}
