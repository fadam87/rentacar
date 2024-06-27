package pti.sb_rentacar_mvc.dto;

public class CarDTO {

	private int id;
	private String type;
	private String plateNumber;
	private int price;

	public CarDTO(int id, String type, String plateNumber, int price) {
		super();
		this.id = id;
		this.type = type;
		this.plateNumber = plateNumber;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
}
