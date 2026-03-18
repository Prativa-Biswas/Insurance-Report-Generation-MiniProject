package com.insurance.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openpdf.text.Document;
import org.openpdf.text.Element;
import org.openpdf.text.FontFactory;
import org.openpdf.text.Paragraph;
import org.openpdf.text.Phrase;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.insurance.dto.SearchRequest;
import com.insurance.entity.CitizenInsurancePlan;
import com.insurance.repository.CitizenInsuranceRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CitizenPlanServiceImpl implements CitizenPlanService {

	@Autowired
	private CitizenInsuranceRepository citizenRepo;
	
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

		Workbook workbook= new HSSFWorkbook();
		
		// 1. Create the CellStyle for the header
        CellStyle headerStyle = workbook.createCellStyle();
        
        // 2. Create and configure the Font
        Font headerFont = workbook.createFont();
        headerFont.setBold(true); // Make font bold
        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(org.apache.poi.ss.usermodel.IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		
		Sheet sheet = workbook.createSheet();  
		Row headerRow = sheet.createRow(0);
	

		headerRow.createCell(0).setCellValue("ID");
		headerRow.getCell(0).setCellStyle(headerStyle);

		headerRow.createCell(1).setCellValue("Citizen Name");
		headerRow.getCell(1).setCellStyle(headerStyle);

		headerRow.createCell(2).setCellValue("Gender");
		headerRow.getCell(2).setCellStyle(headerStyle);

		headerRow.createCell(3).setCellValue("Insurance Plan");
		headerRow.getCell(3).setCellStyle(headerStyle);

		headerRow.createCell(4).setCellValue("Insurance Status");
		headerRow.getCell(4).setCellStyle(headerStyle);

		headerRow.createCell(5).setCellValue("Start Date");
		headerRow.getCell(5).setCellStyle(headerStyle);

		headerRow.createCell(6).setCellValue("End Date");
		headerRow.getCell(6).setCellStyle(headerStyle);

		headerRow.createCell(7).setCellValue("Benefit Amount");
		headerRow.getCell(7).setCellStyle(headerStyle);

		headerRow.createCell(8).setCellValue("Denial Reason");
		headerRow.getCell(8).setCellStyle(headerStyle);

		headerRow.createCell(9).setCellValue("Termination Date");
		headerRow.getCell(9).setCellStyle(headerStyle);

		headerRow.createCell(10).setCellValue("Termination Reason");
		headerRow.getCell(10).setCellStyle(headerStyle);
		
		int rowIndex=1;
		
		List<CitizenInsurancePlan> citizenList = citizenRepo.findAll();
		
		for(CitizenInsurancePlan plan: citizenList ) {
			Row dataRow = sheet.createRow(rowIndex);
			dataRow.createCell(0).setCellValue(plan.getCitizenId());
			dataRow.createCell(1).setCellValue(plan.getCitizenName());
			dataRow.createCell(2).setCellValue(plan.getGender());
			dataRow.createCell(3).setCellValue(plan.getPlanName());
			dataRow.createCell(4).setCellValue(plan.getPlanStatus());
			
			if(plan.getStartDate()!=null) {
			dataRow.createCell(5).setCellValue(plan.getStartDate()+"");}
			else {
					dataRow.createCell(5).setCellValue("NA");	}
			
			if(plan.getEndDate()!=null) {
			dataRow.createCell(6).setCellValue(plan.getEndDate()+"");}
			else {
				dataRow.createCell(6).setCellValue("NA");  }
			
			if(plan.getBenefitAmount()!=null) {
			dataRow.createCell(7).setCellValue(plan.getBenefitAmount());
			}
			else {
				dataRow.createCell(7).setCellValue("NA");
			}
			dataRow.createCell(8).setCellValue(plan.getDenialReason());
			
			if(plan.getTermintionDate()!=null) {
			dataRow.createCell(9).setCellValue(plan.getTermintionDate()+"");  }
			else {
				dataRow.createCell(9).setCellValue("NA");
			}
			
			dataRow.createCell(10).setCellValue(plan.getTerminationReason());

			rowIndex++;			
		}

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		 
		return true;
	}

	@Override
	public Boolean exportPDF(HttpServletResponse response) throws Exception {

		Document document = new Document();
		PdfWriter.getInstance(document, response.getOutputStream());	   	
		Paragraph para= new Paragraph("Citizen Insurance plan ", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, java.awt.Color.BLUE));
		para.setSpacingAfter(15f);   // space after heading
		para.setAlignment(Element.ALIGN_CENTER);// Paragraph Center alignment
		
		document.open();
		document.add(para);
		//  for Table Font Style
		org.openpdf.text.Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, java.awt.Color.MAGENTA);
		
		PdfPTable table = new PdfPTable(11);
		table.setSpacingBefore(10f);  // ✅ adds gap from paragraph
		table.setWidthPercentage(100);// Table width management
		
		table.addCell(new Phrase("ID",font)); // here font used to Style the heading line
		table.addCell( new Phrase("Citizen Name",font));
		table.addCell( new Phrase("Gender",font));
		table.addCell( new Phrase("Insurance Plan",font));
		table.addCell( new Phrase("Insurance Status",font));
		table.addCell( new Phrase("Start Date",font));
		table.addCell( new Phrase("End Date",font));
		table.addCell( new Phrase("Benefit Amount",font));
		table.addCell( new Phrase("Denail Reason",font));
		table.addCell( new Phrase("Termination Date",font));
		table.addCell( new Phrase("Termination Reason",font));

		table.getDefaultCell().setPadding(5);
		List<CitizenInsurancePlan> citizenList = citizenRepo.findAll();
		
		for(CitizenInsurancePlan plan:citizenList) {
			
			table.addCell(String.valueOf(plan.getCitizenId()) );
			table.addCell(plan.getCitizenName());
			table.addCell(plan.getGender());
			table.addCell(plan.getPlanName());
			table.addCell(plan.getPlanStatus());
			
			if(plan.getStartDate()!=null) {
			table.addCell(plan.getStartDate()+"");}
			else { table.addCell("NA");	}
			
			if(plan.getEndDate()!=null) {
			table.addCell(plan.getEndDate()+"");}
			else { table.addCell("NA");}
			
			if(plan.getBenefitAmount()!=null) {
			table.addCell(String.valueOf(plan.getBenefitAmount()));}
			else { table.addCell("NA");}
			
			table.addCell(plan.getDenialReason());
			
			if(plan.getTermintionDate()!=null) {
			table.addCell(plan.getTermintionDate()+"");}
			else { table.addCell("NA");}

			table.addCell(plan.getTerminationReason());
		}
		
		document.add(table);
		document.close();
				
		return true;
	}

}
