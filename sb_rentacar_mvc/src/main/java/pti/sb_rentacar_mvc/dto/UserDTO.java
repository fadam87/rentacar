package pti.sb_rentacar_mvc.dto;

public class UserDTO {

	private String userName;
	private int userPhone;
	private String userAddress;
	private String userEmail;
	
	public UserDTO(String userName, int userPhone, String userAddress, String userEmail) {
		super();
		this.userName = userName;
		this.userPhone = userPhone;
		this.userAddress = userAddress;
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	
	
}
