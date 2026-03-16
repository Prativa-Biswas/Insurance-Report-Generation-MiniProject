package com.insurance.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.dto.SearchRquest;
import com.insurance.entity.CitizenInsurancePlan;
import com.insurance.service.CitizenPlanService;
import org.springframework.web.bind.annotation.RequestParam;


//@RestController
@Controller
@RequestMapping("/citizen")
public class CitizenController {		
	
	private CitizenPlanService service;
	
	//Constructor injection for best practice
	public CitizenController(CitizenPlanService service) {
		
		this.service=service;
	}
	
	@GetMapping("/")
	public String getMethodName() {
		return "index";
	}
	
	
	@GetMapping("/plan")
	public List<String > getAllInsurancePlanName(){
		return service.getAllUniquePlanName();
		
	}
	
	@GetMapping("/status")
	public List<String > getAllInsurancePlanStatus(){
		return service.getAllUniquePlanStatus();
		
	}
	
	@GetMapping("/dynamicSearch")
	public List<CitizenInsurancePlan> getAllDataBySearch( @ModelAttribute SearchRquest req) {
		return service.getAllCitizenRecord(req);
	}
	

}
