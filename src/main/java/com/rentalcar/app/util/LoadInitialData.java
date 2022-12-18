package com.rentalcar.app.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.rentalcar.app.models.entity.Car;
import com.rentalcar.app.models.entity.Category;
import com.rentalcar.app.models.entity.Client;
import com.rentalcar.app.models.entity.CommonExtra;
import com.rentalcar.app.models.entity.Office;
import com.rentalcar.app.models.entity.TypeTransmission;
import com.rentalcar.app.repository.CarsRepository;
import com.rentalcar.app.repository.CategoriesRepository;
import com.rentalcar.app.repository.ClientsRepository;
import com.rentalcar.app.repository.CommonExtraRepository;
import com.rentalcar.app.repository.OfficeMasterRepository;
import com.rentalcar.app.repository.ReservationRepository;

@Component
public class LoadInitialData {

	@Autowired
	OfficeMasterRepository officeMasterRepository;

	@Autowired
	CategoriesRepository categoriesRepository;

	@Autowired
	CarsRepository carsRepository;

	@Autowired
	CommonExtraRepository commonExtraRepository;

	@Autowired
	ClientsRepository clientsRepository;

	@Autowired
	ReservationRepository reservationRepository;

	@EventListener
	public void loadInitialData(ApplicationReadyEvent event) {

		officeMasterRepository.deleteAll();
		categoriesRepository.deleteAll();
		carsRepository.deleteAll();
		commonExtraRepository.deleteAll();
		clientsRepository.deleteAll();
		reservationRepository.deleteAll();
		
		/* Load office data */
		List<Office> officeList = new ArrayList<Office>();
		officeList.add(new Office("NY-CV", "Brooklyn", "address", "11226", "NewYork", "USA"));
		officeList.add(new Office("NY-NB", "Bronx", "address", "10467", "NewYork", "USA"));
		officeList.add(new Office("NY-HG", "Flushing", "address", "11355", "NewYork", "USA"));
		officeList.add(new Office("NY-SG", "Woodside", "address", "11377", "NewYork", "USA"));
		officeList.add(new Office("NY-LC", "Staten Island", "address", "10314", "NewYork", "USA"));
		officeList.add(new Office("NY-GR", "Ridgewood", "address", "11385", "NewYork", "USA"));
		officeList.add(new Office("NY-SA", "Jackson Heights", "address", "11372", "NewYork", "USA"));
		officeList.add(new Office("NY-SM", "Huntington Station", "address", "11746", "NewYork", "USA"));
		officeList.add(new Office("NY-SA", "Jamaica", "address", "11432", "NewYork", "USA"));
		for (Office entity : officeList) {
			officeMasterRepository.save(entity);
		}

		/* Load Categories */
		List<Category> categoryList = new ArrayList<Category>();
		categoryList.add(new Category("CAT_SUP", 100.0, 900.50, 1200.0, 300.0));
		categoryList.add(new Category("CAT_MED", 50.0, 90.50, 600.0, 50.0));
		categoryList.add(new Category("CAT_ECO", 25.0, 50.50, 300.0, 25.0));

		for (Category entity : categoryList) {
			categoriesRepository.save(entity);
		}

		/* Load Car Details */
		List<Car> carList = new ArrayList<Car>();
		carList.add(new Car(categoriesRepository.getCatId("CAT_SUP"), "Audi A3 Cabrio", "Audi", TypeTransmission.MANUAL,
				true, 4, 3, 1, "/images/cat_sup/audi-a3-cabrio-2d-weiss-offen.png"));
		carList.add(new Car(categoriesRepository.getCatId("CAT_SUP"), "Mercedes-Benz Clase C", "Mercedes",
				TypeTransmission.MANUAL, true, 5, 5, 2, "/images/cat_sup/mb-c-klasse-4d-silber.png"));
		carList.add(new Car(categoriesRepository.getCatId("CAT_SUP"), "BMW Serie 2 Active Tourer", "BMW",
				TypeTransmission.MANUAL, true, 5, 5, 2, "/images/cat_sup/bmw-2er-active-tourer-5d-blau.png"));
		carList.add(new Car(categoriesRepository.getCatId("CAT_SUP"), "BMW Serie 3 SW", "BMW", TypeTransmission.MANUAL,
				true, 5, 5, 1, "/images/cat_sup/bmw-3er-kombi-grau.png"));
		carList.add(new Car(categoriesRepository.getCatId("CAT_SUP"), "BMW i8", "BMW", TypeTransmission.AUTOMATIC, true,
				2, 2, 1, "/images/cat_sup/bmw-i8-2d-weiss-us.png"));
		carList.add(new Car(categoriesRepository.getCatId("CAT_SUP"), "BMW X1 Aut", "BMW", TypeTransmission.AUTOMATIC,
				true, 5, 5, 1, "/images/cat_sup/bmw-x1-5d-weiss.png"));
		carList.add(new Car(categoriesRepository.getCatId("CAT_SUP"), "Jaguar F-Type Coupe", "Jaguar",
				TypeTransmission.AUTOMATIC, true, 2, 3, 1, "/images/cat_sup/jaguar-f-type-coupe-2d-silber.png"));
		carList.add(new Car(categoriesRepository.getCatId("CAT_SUP"), "Mercedes-Benz Clase A Aut", "Mercedes",
				TypeTransmission.AUTOMATIC, true, 5, 5, 1, "/images/cat_sup/mb-a-klasse-5d-weiss.png"));
		carList.add(new Car(categoriesRepository.getCatId("CAT_SUP"), "Mercedes-Benz SLC Cabrio", "Mercedes",
				TypeTransmission.MANUAL, true, 2, 3, 1, "/images/cat_sup/mb-slc-cabrio-2d-grau-offen.png"));
		carList.add(new Car(categoriesRepository.getCatId("CAT_SUP"), "MINI One Cabrio", "MINI",
				TypeTransmission.MANUAL, true, 4, 3, 1, "/images/cat_sup/mini-cooper-cabrio-2d-schwarz-offen.png"));
		carList.add(new Car(categoriesRepository.getCatId("CAT_MED"), "Citroen C4 Cactus Aut", "Citroën",
				TypeTransmission.AUTOMATIC, true, 5, 5, 1, "/images/cat_med/citroen-c4-cactus-5d-weiss.png"));
		carList.add(new Car(categoriesRepository.getCatId("CAT_MED"), "MINI", "MINI", TypeTransmission.MANUAL, true, 4,
				3, 1, "/images/cat_med/mini-cooper-3d-schwarz.png"));
		carList.add(new Car(categoriesRepository.getCatId("CAT_MED"), "Opel Zafira", "Opel", TypeTransmission.MANUAL,
				true, 5, 5, 2, "/images/cat_med/opel-zafira-5d-silber.png"));
		carList.add(new Car(categoriesRepository.getCatId("CAT_MED"), "Peugeot Partner", "Peugeot",
				TypeTransmission.MANUAL, true, 5, 5, 2, "/images/cat_med/peugeot-partner-tepee-van-braun.png"));
		carList.add(new Car(categoriesRepository.getCatId("CAT_MED"), "VW Golf", "VW", TypeTransmission.MANUAL, true, 5,
				5, 2, "/images/cat_med/vw-golf-5d-weiss.png"));
		carList.add(new Car(categoriesRepository.getCatId("CAT_MED"), "VW Golf SW", "VW", TypeTransmission.MANUAL, true,
				5, 5, 3, "/images/cat_med/vw-golf-kombi-grau.png"));
		carList.add(new Car(categoriesRepository.getCatId("CAT_MED"), "VW Polo Aut", "VW", TypeTransmission.AUTOMATIC,
				true, 4, 5, 1, "/images/cat_med/vw-polo-5d-schwarz.png"));
		carList.add(new Car(categoriesRepository.getCatId("CAT_MED"), "Ford Ka", "Ford", TypeTransmission.MANUAL, true,
				4, 3, 1, "/images/cat_eco/ford-ka-3d-blau.png"));
		carList.add(new Car(categoriesRepository.getCatId("CAT_ECO"), "Opel Corsa", "Opel", TypeTransmission.MANUAL,
				true, 5, 5, 1, "/images/cat_eco/opel-corsa-5d-weiss.png"));
		carList.add(new Car(categoriesRepository.getCatId("CAT_ECO"), "Peugeot 308", "Peugeot", TypeTransmission.MANUAL,
				true, 5, 5, 1, "/images/cat_eco/peugeot-308-5d-weiss.png"));
		carList.add(new Car(categoriesRepository.getCatId("CAT_ECO"), "Smart Forfour", "Smart", TypeTransmission.MANUAL,
				true, 4, 4, 1, "/images/cat_eco/smart-for-four-4d-lava.png"));
		carList.add(new Car(categoriesRepository.getCatId("CAT_ECO"), "VW Polo", "VW", TypeTransmission.MANUAL, true, 5,
				5, 1, "/images/cat_eco/vw-polo-5d-schwarz.png"));

		for (Car entity : carList) {
			carsRepository.save(entity);
		}

		/* Load common extra data */
		List<CommonExtra> commonExtraList = new ArrayList<CommonExtra>();
		commonExtraList.add(new CommonExtra("Baby seat guaranteed (0-13kg / Group 0+)", 11.99));
		commonExtraList.add(new CommonExtra("Child seat guaranteed (9-36kg / Group 1/2/3)", 11.99));
		commonExtraList.add(new CommonExtra("Booster seat guaranteed (15 - 36kg / Group 2/3)", 9.99));
		commonExtraList.add(new CommonExtra("Snow chains", 18.33));
		commonExtraList.add(new CommonExtra("Ski & Snowboard rack", 18.33));

		for (CommonExtra entity : commonExtraList) {
			commonExtraRepository.save(entity);
		}

		/* Load Admin Client data */
		Client client = new Client("admin", "admin", "53647852", "667854120", "admin@admin.com", "NewYork", "11745",
				"NYC", "US", "admin", "YWRtaW4=", "YWRtaW4=");
		clientsRepository.save(client);

	}

}
