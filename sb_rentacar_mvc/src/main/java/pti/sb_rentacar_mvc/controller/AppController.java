package pti.sb_rentacar_mvc.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		
		
		System.out.println(startDate +" " + endDate);
		
		
		return "carForRent.html";
	}
	
}
