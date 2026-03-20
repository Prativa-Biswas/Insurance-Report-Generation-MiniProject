package com.insurance.service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.insurance.dto.SearchRequest;
import com.insurance.entity.CitizenInsurancePlan;
import com.insurance.repository.CitizenInsuranceRepository;
import com.insurance.utils.EmailUtils;
import com.insurance.utils.ExcelGenerator;
import com.insurance.utils.PDFGenerator;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class CitizenPlanServiceImpl implements CitizenPlanService {

	@Autowired
	private CitizenInsuranceRepository citizenRepo;
	
	@Autowired
	private ExcelGenerator excelGenerator;
	
	@Autowired
	private PDFGenerator pdfGenerator;
	@Autowired
	private EmailUtils emailUtils;
	
	@Override
	public List<String> getAllUniquePlanName() {

		return citizenRepo.getPlanNames();
	}

	@Override
	public List<String> getAllUniquePlanStatus() {

		return citizenRepo.getPlanSatus();
	}

	
	@Override
	public List<CitizenInsurancePlan> getAllCitizenRecord(SearchRequest req) {

		CitizenInsurancePlan citizenEx= new CitizenInsurancePlan();
		
		if(req.getPlanName()!=null && !req.getPlanName().isBlank()) {
			citizenEx.setPlanName(req.getPlanName());
		}
		
		if(req.getPlanStatus()!=null && !req.getPlanStatus().isBlank()) {
			citizenEx.setPlanStatus(req.getPlanStatus());
		}
		
		if(req.getGender()!=null && !req.getGender().isBlank()) {
			citizenEx.setGender(req.getGender());
		}
		
			if(req.getStartDate()!=null && !req.getStartDate().isBlank()) {
				// Converting String Input data to Date Format
				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			        LocalDate localDate = LocalDate.parse(req.getStartDate(), formatter);
				
				citizenEx.setStartDate(localDate);
			}
			
			
			if(req.getEndDate()!=null && !req.getEndDate().isBlank()) {
				// Converting String Input data to Date Format
				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			        LocalDate localDate = LocalDate.parse(req.getEndDate(), formatter);
				
				citizenEx.setEndDate(localDate);
			}
			
			
		Example<CitizenInsurancePlan> example = Example.of(citizenEx);
		
		List<CitizenInsurancePlan> list = citizenRepo.findAll(example);
		
		return list;
	}

	@Override
	public Boolean exportExcel(HttpServletResponse response) throws Exception {

		List<CitizenInsurancePlan> citizenList = citizenRepo.findAll();
		excelGenerator.generate(response, citizenList);
		
		return true;
	}

	@Override
	public Boolean exportPDF(HttpServletResponse response) throws Exception {

		List<CitizenInsurancePlan> citizenList = citizenRepo.findAll();
		pdfGenerator.generate(response, citizenList);	
		return true;
	}

	
	@Override
	public Boolean exportExcelToEmail() throws Exception {

		File file = new File("InsrusencePlan.xls");
		List<CitizenInsurancePlan> citizenList = citizenRepo.findAll();
		excelGenerator.generate(citizenList,file);

		String subject= "Text email subject";
		String body= "Text email Body";
		String to= "prativabiswas104@gmail.com";	
		emailUtils.sendEmail(subject, body, to, file);
		file.delete();
		
		return true;
	}
	
	@Override
	public Boolean exportPdfToEmail() throws Exception {

		File file = new File("InsrusencePlan.pdf");
		List<CitizenInsurancePlan> citizenList = citizenRepo.findAll();
		pdfGenerator.generate(citizenList,file);		
		String subject= "Text email subject";
		String body= "Text email Body";
		String to= "prativabiswas104@gmail.com";

		
		emailUtils.sendEmail(subject, body, to, file);
		file.delete();
		
		return true;
	}
}
