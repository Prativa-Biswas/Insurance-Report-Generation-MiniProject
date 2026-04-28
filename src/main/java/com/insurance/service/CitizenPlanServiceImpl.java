package com.insurance.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openpdf.text.DocumentException;
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


	private CitizenInsuranceRepository citizenRepo;
	private ExcelGenerator excelGenerator;
	private PDFGenerator pdfGenerator;
	private EmailUtils emailUtils;
	
	public CitizenPlanServiceImpl(CitizenInsuranceRepository citizenRepo,
            ExcelGenerator excelGenerator,
            PDFGenerator pdfGenerator,
            EmailUtils emailUtils) {
				this.citizenRepo = citizenRepo;
				this.excelGenerator = excelGenerator;
				this.pdfGenerator = pdfGenerator;
				this.emailUtils = emailUtils;
	}
	
	
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
		
		
		return citizenRepo.findAll(example);
	}

	@Override
	public Boolean exportExcel(HttpServletResponse response) throws IOException  {

		List<CitizenInsurancePlan> citizenList = citizenRepo.findAll();
		excelGenerator.generate(response, citizenList);
		
		return true;
	}

	@Override
	public Boolean exportPDF(HttpServletResponse response) throws DocumentException, IOException {

		List<CitizenInsurancePlan> citizenList = citizenRepo.findAll();
		pdfGenerator.generate(response, citizenList);	
		return true;
	}

	
	@Override
	public Boolean exportExcelToEmail() throws IOException {

		File file = new File("InsrusencePlan.xls");
		List<CitizenInsurancePlan> citizenList = citizenRepo.findAll();
		excelGenerator.generate(citizenList,file);

		String subject= "Text email subject";
		String body= "Text email Body";
		String to= "prativabiswas104@gmail.com";	
		emailUtils.sendEmail(subject, body, to, file);
		boolean deleted = file.delete();
		 if (!deleted) {
		        System.out.println("Warning: File not deleted");
		    }
				
		return true;
		
			
	}
	
	@Override
	public Boolean exportPdfToEmail() throws DocumentException, FileNotFoundException  {

		File file = new File("InsrusencePlan.pdf");
		
		List<CitizenInsurancePlan> citizenList = citizenRepo.findAll();
		pdfGenerator.generate(citizenList,file);		
		String subject= "Text email subject";
		String body= "Text email Body";
		String to= "prativabiswas104@gmail.com";

		
		emailUtils.sendEmail(subject, body, to, file);
		boolean deleted = file.delete();
		
		 if (!deleted) {
		        System.out.println("Warning: File not deleted");
		    }
			return true;
					

	}
}
