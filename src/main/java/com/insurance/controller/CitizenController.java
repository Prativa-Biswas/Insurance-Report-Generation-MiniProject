package com.insurance.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.insurance.dto.SearchRequest;
import com.insurance.service.CitizenPlanService;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/citizen")
public class CitizenController {		
	
	private CitizenPlanService service;
	
	/**
	 * Constructs CitizenController with required service dependency.
	 * 
	 * Uses constructor-based dependency injection to initialize
	 * CitizenPlanService.
	 *
	 * @param service 
	 * the service used to handle business logic for citizen plans
	 */
	public CitizenController(CitizenPlanService service) {
		
		this.service=service;
	}
	
    /**
     * This method is to load index page
     * @param model
     * @return
     */
	@GetMapping("/")
	public String getIndexPage(Model model) {
		
		model.addAttribute("search", new SearchRequest());
		init(model);

		
		return "index";
	}

	/**
	 * This method is to support dynamic search and return result based on the search to index page
	 * @param search
	 * @param model
	 * @return
	 */
	@PostMapping("/search")
	public String postMethodName(@ModelAttribute("search")  SearchRequest search, Model model) {
			
		model.addAttribute("citizenData",service.getAllCitizenRecord(search));
		init(model);
		return "index";
	}
	
	
	@GetMapping("/excel")
	public void getExcel(HttpServletResponse response) throws Exception {
		
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition", "attachment;fileName=plans.xls");
		service.exportExcel(response);
		
	}
	
	
	@GetMapping("/pdf")
	public void getPDF(HttpServletResponse response) throws Exception {
		
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "attachment;fileName=plans.pdf");
		service.exportPDF(response);
		
	}
	
	
	
	/**
	 * Initializes model attributes required for the search form.
	 * 
	 * Fetches unique plan names and plan statuses from the service layer
	 * and adds them to the model for populating dropdown fields in the UI.
	 *
	 * @param model 
	 * the Model object used to pass data to the view
	 */
	private void init(Model model) {
		
		model.addAttribute("plans", service.getAllUniquePlanName());
		model.addAttribute("Status", service.getAllUniquePlanStatus());
	}
	

}
