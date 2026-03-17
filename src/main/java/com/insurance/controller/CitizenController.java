package com.insurance.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.insurance.dto.SearchRequest;
import com.insurance.service.CitizenPlanService;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/citizen")
public class CitizenController {		
	
	private CitizenPlanService service;
	
	//Constructor injection for best practice
	public CitizenController(CitizenPlanService service) {
		
		this.service=service;
	}
	
	@GetMapping("/")
	public String getIndexPage(Model model) {
		
		model.addAttribute("search", new SearchRequest());
		init(model);

		
		return "index";
	}

	@PostMapping("/search")
	public String postMethodName(@ModelAttribute("search")  SearchRequest search, Model model) {
			
		model.addAttribute("citizenData",service.getAllCitizenRecord(search));
		init(model);
		return "index";
	}
	
	
	private void init(Model model) {
		
		model.addAttribute("plans", service.getAllUniquePlanName());
		model.addAttribute("Status", service.getAllUniquePlanStatus());
	}
	

}
