package pti.sb_rentacar_mvc.dto;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
public class ReservationDTO {

	private CarDTO carDto;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private UserDTO userDto;

	public ReservationDTO(CarDTO carDto, LocalDate startDate, LocalDate endDate, UserDTO userDto) {
		super();
		this.carDto = carDto;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userDto = userDto;
	}

	public CarDTO getCarDto() {
		return carDto;
	}

	public void setCarDto(CarDTO carDto) {
		this.carDto = carDto;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public UserDTO getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDTO userDto) {
		this.userDto = userDto;
	}
	
	public long getLongOfReservation() {

		
		long rentalDays = ChronoUnit.DAYS.between(startDate, endDate)  + 1;
		return rentalDays;
	}
	
	public long getReservationPrice() {
		
		long returnValue = this.getLongOfReservation()*this.carDto.getPrice();
		
		return returnValue;
		
		
	}
	
}
