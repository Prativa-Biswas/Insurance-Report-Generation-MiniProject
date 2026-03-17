package com.insurance.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import com.insurance.dto.SearchRequest;
import com.insurance.entity.CitizenInsurancePlan;
import com.insurance.repository.CitizenInsuranceRepository;

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
				
				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			        LocalDate localDate = LocalDate.parse(req.getStartDate(), formatter);
				
				citizenEx.setStartDate(localDate);
			}
			
			
			if(req.getEndDate()!=null && !req.getEndDate().isBlank()) {
				
				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			        LocalDate localDate = LocalDate.parse(req.getEndDate(), formatter);
				
				citizenEx.setEndDate(localDate);
			}
			
			
		Example<CitizenInsurancePlan> example = Example.of(citizenEx);
		
		List<CitizenInsurancePlan> list = citizenRepo.findAll(example);
		
		return list;
	}

	@Override
	public Boolean exportExcel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean exportPDF() {
		// TODO Auto-generated method stub
		return null;
	}

}
