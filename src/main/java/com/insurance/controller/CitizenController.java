package com.insurance.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.insurance.dto.SearchRequest;
import com.insurance.service.CitizenPlanService;
import com.insurance.utils.AppConstants;

import jakarta.servlet.http.HttpServletResponse;


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

		
		return AppConstants.INDEX;
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
		return AppConstants.INDEX;
	}
	
	/**
	 * The method is to Accept the excel downloading request & generate Excel 
	 * @param response
	 * @throws Exception
	 */
	@GetMapping("/excel")
	public void getExcel(HttpServletResponse response) throws Exception {
		
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition", "attachment;fileName=plans.xls");
		service.exportExcel(response);
		
	}
	
	
	/**
	 * The method  is to Accept the PDF downloading request & generate PDF 
	 * @param response
	 * @throws Exception
	 */
	@GetMapping("/pdf")
	public void getPDF(HttpServletResponse response) throws Exception {
		
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "attachment;fileName=plans.pdf");
		service.exportPDF(response);
		
	}
	
	/**
	 * Handles email sending request for exporting reports in different formats (PDF/Excel).
	 * The format type is received dynamically through the URL path variable.
	 * Based on the selected type, the corresponding export service method is invoked.
	 * After processing, the user is returned to the same page with a success message.
	 *
	 * @param type   the type of file to be sent via email (e.g., "pdf" or "excel")
	 * @param search the search criteria used to filter the report data
	 * @param model  the model object used to pass data and messages to the view
	 * @return       the view name (index page) to be rendered after email is sent
	 * @throws Exception if any error occurs during file generation or email sending
	 */
	@GetMapping("/{type}/mail")
	public String  sendToMail(@PathVariable("type") String type,
			@ModelAttribute("search")  SearchRequest search , 
			Model model) throws Exception {
				
				   if("excel".equalsIgnoreCase(type)) {
					service.exportExcelToEmail();}
				   else if("pdf".equalsIgnoreCase(type)) {
					   service.exportPdfToEmail();
			   	}
		
				    init(model);
	    model.addAttribute("msg", "Email Sent Successfully ✅");
		return AppConstants.INDEX;
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
