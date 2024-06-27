package pti.sb_rentacar_mvc.dto;

import java.time.LocalDate;
import java.util.List;

public class CarListDTO {

	private List<CarDTO> carList;
	private LocalDate startDate;
	private LocalDate endDate;

	public CarListDTO(List<CarDTO> carList, LocalDate startDate, LocalDate endDate) {
		super();
		this.carList = carList;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public List<CarDTO> getCarList() {
		return carList;
	}

	public void setCarList(List<CarDTO> carList) {
		this.carList = carList;
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
	
	
	
	
}
