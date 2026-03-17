package com.insurance.service;

import java.util.List;

import com.insurance.dto.SearchRequest;
import com.insurance.entity.CitizenInsurancePlan;

public interface CitizenPlanService {
	
	public List<String> getAllUniquePlanName();
	public List<String> getAllUniquePlanStatus();
	public List<CitizenInsurancePlan>  getAllCitizenRecord(SearchRequest req);
	public Boolean exportExcel();
	public Boolean exportPDF();
}
