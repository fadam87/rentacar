package pti.sb_rentacar_mvc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "cars")
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "plate_number")
	private String plateNumber;
	
	@Column(name = "active")
	private boolean active;
	
	@Column(name = "price")
	private int price;
	
}
