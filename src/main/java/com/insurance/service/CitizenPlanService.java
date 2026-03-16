package com.insurance.service;

import java.util.List;

import com.insurance.dto.SearchRquest;
import com.insurance.entity.CitizenInsurancePlan;
import com.insurance.utils.ExcelGenerator;
import com.insurance.utils.PDFGenerator;

public interface CitizenPlanService {
	
	public List<String> getAllUniquePlanName();
	public List<String> getAllUniquePlanStatus();
	public List<CitizenInsurancePlan>  getAllCitizenRecord(SearchRquest req);
//	public PDFGenerator getPDFofAllDtata();
//	public ExcelGenerator getExcelofAllDtata();
	
	public Boolean exportExcel();
	public Boolean exportPDF();
}
