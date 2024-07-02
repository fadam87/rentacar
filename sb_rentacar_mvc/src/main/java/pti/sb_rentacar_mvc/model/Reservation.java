package pti.sb_rentacar_mvc.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservations")
public class Reservation {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "car_id")
	private int carId;
	
	@Column(name = "start_date")
	private LocalDate startDate;
	
	@Column(name = "end_date")
	private LocalDate endDate;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "user_email")
	private String userEmail;	
	
	@Column(name = "user_phone")
	private int userPhone;
	
	@Column(name = "user_address")
	private String userAddress;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public int getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(int userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAdress) {
		this.userAddress = userAdress;
	}
	
	
	
}
