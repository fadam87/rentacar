package pti.sb_rentacar_mvc.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pti.sb_rentacar_mvc.database.Database;
import pti.sb_rentacar_mvc.dto.CarDTO;
import pti.sb_rentacar_mvc.dto.CarListDTO;
import pti.sb_rentacar_mvc.dto.ReservationDTO;
import pti.sb_rentacar_mvc.dto.UserDTO;
import pti.sb_rentacar_mvc.model.Car;
import pti.sb_rentacar_mvc.model.Reservation;

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
	public ReservationDTO getReservationDto(int carId, LocalDate startDate, LocalDate endDate) {
		
		Car car = db.getCarById(carId);
		
		ReservationDTO dto = null;
		
		if (car != null) {
		
			CarDTO carDto = this.getCarDTOFromCar(car);
			
			dto = new ReservationDTO(carDto, startDate, endDate, null);
		
		}
		

		
		return dto;
	}
	public ReservationDTO saveReservation(int carId, LocalDate startDate, LocalDate endDate, String userName, String userAddress,
			String userEmail, int userPhone) {
		// TODO Auto-generated method stub
		
		UserDTO userDto = new UserDTO(userName, userPhone, userAddress, userEmail);
		Car car = db.getCarById(carId);
		CarDTO carDto = this.getCarDTOFromCar(car);
		
		ReservationDTO dto = new ReservationDTO(carDto, startDate, endDate, userDto);
		
		Reservation reservation = new Reservation();
		reservation.setCarId(carId);
		reservation.setStartDate(startDate);
		reservation.setEndDate(endDate);
		reservation.setUserName(userName);
		reservation.setUserEmail(userEmail);
		reservation.setUserPhone(userPhone);
		reservation.setUserAdress(userAddress);
		
		db.saveReservation(reservation);
		
		return dto;
	}
	
	private CarDTO getCarDTOFromCar(Car car) {
		
		
		CarDTO carDto = new CarDTO(car.getId(), car.getType(), car.getPlateNumber(), car.getPrice());
		
		return carDto;
	}
	
}
