package pti.sb_rentacar_mvc.controller;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import pti.sb_rentacar_mvc.dto.CarDTO;
import pti.sb_rentacar_mvc.dto.CarListDTO;
import pti.sb_rentacar_mvc.dto.ReservationDTO;
import pti.sb_rentacar_mvc.dto.ReservationDTOList;
import pti.sb_rentacar_mvc.service.AppService;

@Controller
public class AppController {

	private AppService service;
	
	
	@Autowired
	public AppController(AppService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/")
	public String startPage() {
		
		
		
		return "index.html";	
	}
	
	@GetMapping("/cars")
	public String getCars(Model model, 
						@RequestParam("start") LocalDate startDate,
						@RequestParam("end") LocalDate endDate) {
		
		
		CarListDTO carListDto = service.getAvaibleCarList(startDate, endDate);
		
		model.addAttribute("carlistdto" , carListDto);
		
		
		return "carForRent.html";
	}
	@GetMapping("/cars/startreserve")
	public String getUsersData(Model model,
								@RequestParam("carid") int carId,
								@RequestParam("start") LocalDate startDate,
								@RequestParam("end") LocalDate endDate) {
		
		ReservationDTO dto = service.getReservationDto(carId, startDate, endDate);
		
		
		model.addAttribute("dto",dto);
		
		return"carReserve.html";
	}
	@PostMapping("/cars/endreserve")
	public String endReserve(Model model,
								@RequestParam("carid") int carId,
								@RequestParam("start") LocalDate startDate,
								@RequestParam("end") LocalDate endDate,
								@RequestParam("name") String userName,
								@RequestParam("address") String userAddress,
								@RequestParam("email") String userEmail,
								@RequestParam("tel") int userPhone){
									
		
		ReservationDTO dto = service.saveReservation(carId, startDate, endDate, userName, userAddress, userEmail, userPhone);
		
		model.addAttribute("dto",dto);
									
		return "success.html";							
		}
	
	@GetMapping("/admin")
	public String startAdmin() {
		
		return "admin.html";
		
	}
	
	@GetMapping("/admin/list")
	public String adminListReservations(Model model) {
		
		ReservationDTOList dto = service.getAllReservation();
		
		model.addAttribute("dto",dto);
		
		return "reservationlist.html";
	}
	@GetMapping("/admin/cars")
	public String carAdmin(Model model) {
		
		CarListDTO carListDto = service.getCarList();
		
		model.addAttribute("dto",carListDto);
		
		return "selectioncar.html";
	}
	@GetMapping("/admin/cars/mod")
	public String carModification(Model model,
								@RequestParam("carid") int carId) {
		
		CarDTO carDto = service.getCarDTOFromId(carId);
		
		model.addAttribute("dto" , carDto);
		
		return "modificationcar.html";
	}
	@PostMapping("/admin/cars/mod/modification")
	public String carModificationSave(Model model,
									@RequestParam("platenumber") String PlateNumber,
									@RequestParam("id") int carId,
									@RequestParam("type") String type,
									@RequestParam("price") int price,
									@RequestParam("active") boolean active)
	{
		service.saveCar(carId, PlateNumber, type, price, active);
		
		return "admin.html";	
	}
	
	@PostMapping("/admin/cars/new")
	public String addNewCar(Model model,
									@RequestParam("platenumber") String PlateNumber,
									@RequestParam("type") String type,
									@RequestParam("price") int price,
									@RequestParam("active") boolean active)
	{
		service.addNewCar(PlateNumber, type, price, active);
		
		return "admin.html";	
	}
	
	@PostMapping("/admin/cars/uploadpic")
	public String uploadPic(Model model,
							@RequestParam("file") MultipartFile file,
							@RequestParam("carid") int carId) throws IOException {
		
		CarDTO carDto = service.uploadPic(file, carId);
		
		model.addAttribute("car", carDto);
		
	return "succesupload.html";	
	}
}
