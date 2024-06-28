package pti.sb_rentacar_mvc.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pti.sb_rentacar_mvc.database.Database;
import pti.sb_rentacar_mvc.dto.CarDTO;
import pti.sb_rentacar_mvc.dto.CarListDTO;
import pti.sb_rentacar_mvc.model.Car;

@Service
public class AppService {

	private Database db;
	@Autowired
	public AppService(Database db) {
		super();
		this.db = db;
	}
	public CarListDTO getAvaibleCarList(LocalDate startDate, LocalDate endDate) {
		
		List<Car> carList = null;
		List<CarDTO> dtoList = new ArrayList<>();
		if(startDate.isBefore(endDate) || startDate.isEqual(endDate)) {		//frontenden kéne vizsgálni
			carList = db.getAvaibleCars(startDate, endDate);	
		}
		else {
			
			carList = db.getAllCar();
			
		}
		
		for(int index = 0; index < carList.size(); index++) {
			
			Car currentCar = carList.get(index);
			
			CarDTO carDto = new CarDTO(currentCar.getId(), currentCar.getPlateNumber(), currentCar.getType() ,currentCar.getPrice());
		
			dtoList.add(carDto);
		}
		
		
		
		CarListDTO dto = new CarListDTO(dtoList, endDate, endDate);
		
		
		return dto;
	}
	
	
	
}
