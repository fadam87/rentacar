package pti.sb_rentacar_mvc.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pti.sb_rentacar_mvc.database.Database;
import pti.sb_rentacar_mvc.dto.CarDTO;
import pti.sb_rentacar_mvc.dto.CarListDTO;
import pti.sb_rentacar_mvc.dto.ReservationDTO;
import pti.sb_rentacar_mvc.dto.ReservationDTOList;
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
			
			CarDTO carDto = this.getCarDTOFromCar(currentCar);
		
			dtoList.add(carDto);
		}
		
		
		
		CarListDTO dto = new CarListDTO(dtoList, startDate, endDate);
		
		
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
		reservation.setUserAddress(userAddress);
		
		db.saveReservation(reservation);
		
		return dto;
	}
	
	private CarDTO getCarDTOFromCar(Car car) {
		
		
		CarDTO carDto = new CarDTO(car.getId(), car.getType(), car.getPlateNumber(), car.getPrice(), car.getImage());
		
		return carDto;
	}
	public ReservationDTOList getAllReservation() {
		
		List<Reservation> reservations = db.getAllReservation();
		
		List<ReservationDTO> dtoList = new ArrayList<>();
		
		for(int index = 0; index < reservations.size(); index++) {
			
			Reservation currentReservation = reservations.get(index);
			
			Car car = db.getCarById(currentReservation.getCarId());
			
			CarDTO carDto = this.getCarDTOFromCar(car);
			
			UserDTO userDto = new UserDTO(currentReservation.getUserName(), currentReservation.getUserPhone(), currentReservation.getUserAddress(), currentReservation.getUserEmail());		
					
			ReservationDTO currentDto = new ReservationDTO(carDto, currentReservation.getStartDate(), currentReservation.getEndDate(), userDto);
			
			dtoList.add(currentDto);
		} 
		
		ReservationDTOList dto = new ReservationDTOList();
		
		dto.setReservationDtoList(dtoList);
		return dto;
	}
	public CarListDTO getCarList() {
		
		List<Car> carList = db.getAllCar();
		List<CarDTO> carDtoList = new ArrayList<>();
		for (int index = 0; index < carList.size(); index++) {
			
			Car currentCar = carList.get(index);
			CarDTO carDto = this.getCarDTOFromCar(currentCar);
			carDtoList.add(carDto);
			
		}
		
		CarListDTO dto = new CarListDTO(carDtoList, null, null);
		
		return dto;
	}
	public CarDTO getCarDTOFromId(int carId) {
		
		Car car = db.getCarById(carId);
		CarDTO carDto = this.getCarDTOFromCar(car);
		
		return carDto;
	}
	public void saveCar(int carId, String plateNumber, String type, int price, boolean active) {
		
		Car car = new Car();
		car.setId(carId);
		car.setPlateNumber(plateNumber);
		car.setType(type);
		car.setPrice(price);
		car.setActive(active);
		
		db.updateCar(car);
	}
	public void addNewCar(String plateNumber, String type, int price, boolean active) {
		
		Car car = new Car();
		car.setId(0);
		car.setPlateNumber(plateNumber);
		car.setType(type);
		car.setPrice(price);
		car.setActive(active);
		
		db.inserNewCar(car);
	}
	public CarDTO uploadPic(MultipartFile file, int carId) throws IOException {
		
		byte[] bFile = file.getBytes();
		Car car = db.getCarById(carId);
		car.setImage(bFile);
		
		db.updateCar(car);
		
		car = db.getCarById(carId);
		
		CarDTO carDto = this.getCarDTOFromCar(car);
		
		return carDto;
	}
	
}
