package pti.sb_rentacar_mvc.dto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class CarDTO {

	private int id;
	private String type;
	private String plateNumber;
	private int price;
	private byte[] image;

	

	public CarDTO(int id, String type, String plateNumber, int price, byte[] image) {
		super();
		this.id = id;
		this.type = type;
		this.plateNumber = plateNumber;
		this.price = price;
		this.image = image;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public String getImageBase64() throws IOException {
		
		String returnString = "";
		
		if (image == null || image.length == 0) {
	        
			Path path = Paths.get("src/main/resources/static/nincs_kep.png");
			byte[] defaultImage = Files.readAllBytes(path);
			returnString = Base64.getEncoder().encodeToString(defaultImage);
			
			
		}
		
		else {
			returnString = Base64.getEncoder().encodeToString(image);
		}
		return returnString;
	}
	
}
