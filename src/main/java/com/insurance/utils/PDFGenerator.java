package com.insurance.utils;

import java.util.List;

import org.openpdf.text.Document;
import org.openpdf.text.Element;
import org.openpdf.text.Font;
import org.openpdf.text.FontFactory;
import org.openpdf.text.Paragraph;
import org.openpdf.text.Phrase;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import com.insurance.entity.CitizenInsurancePlan;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class PDFGenerator {
	
	public void generate(HttpServletResponse response, List<CitizenInsurancePlan> citizenList ) throws Exception{
	
		Document document = new Document();
		PdfWriter.getInstance(document, response.getOutputStream());	   	
		Paragraph para= new Paragraph("Citizen Insurance plan ", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, java.awt.Color.BLUE));
		para.setSpacingAfter(15f);   // space after heading
		para.setAlignment(Element.ALIGN_CENTER);// Paragraph Center alignment
		
		document.open();
		document.add(para);
		//  for Table Font Style
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, java.awt.Color.MAGENTA);
		
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
	}
}
