package pti.sb_rentacar_mvc.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pti.sb_rentacar_mvc.dto.CarListDTO;
import pti.sb_rentacar_mvc.dto.ReservationDTO;
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
}
