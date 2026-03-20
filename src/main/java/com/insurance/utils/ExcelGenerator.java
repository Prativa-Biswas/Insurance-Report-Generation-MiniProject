package com.insurance.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import com.insurance.entity.CitizenInsurancePlan;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ExcelGenerator {
	
public void generate(HttpServletResponse response, List<CitizenInsurancePlan> citizenList ) throws Exception{
		
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
		
	}


public void generate(List<CitizenInsurancePlan> citizenList , File file) throws Exception{
	
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

	FileOutputStream outputStream= new FileOutputStream(file);
	workbook.write(outputStream);
	workbook.close();
	
}


}
