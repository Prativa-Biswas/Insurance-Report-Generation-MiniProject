package com.insurance.service;

import java.util.List;

import com.insurance.dto.SearchRequest;
import com.insurance.entity.CitizenInsurancePlan;

import jakarta.servlet.http.HttpServletResponse;

public interface CitizenPlanService {
	
	public List<String> getAllUniquePlanName();
	public List<String> getAllUniquePlanStatus();
	public List<CitizenInsurancePlan>  getAllCitizenRecord(SearchRequest req);
	public Boolean exportExcel(HttpServletResponse response) throws Exception;
	public Boolean exportPDF(HttpServletResponse response) throws Exception;
}
