package com.insurance.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.openpdf.text.DocumentException;

import com.insurance.dto.SearchRequest;
import com.insurance.entity.CitizenInsurancePlan;

import jakarta.servlet.http.HttpServletResponse;

public interface CitizenPlanService {
	
	public List<String> getAllUniquePlanName();
	public List<String> getAllUniquePlanStatus();
	public List<CitizenInsurancePlan>  getAllCitizenRecord(SearchRequest req);
	public Boolean exportExcel(HttpServletResponse response) throws IOException;
	public Boolean exportPDF(HttpServletResponse response) throws DocumentException, IOException ;
	
	public Boolean exportExcelToEmail() throws IOException;
	public Boolean exportPdfToEmail() throws DocumentException, FileNotFoundException;
}
