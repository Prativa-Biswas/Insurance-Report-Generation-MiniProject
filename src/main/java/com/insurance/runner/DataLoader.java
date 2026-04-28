package com.insurance.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.insurance.entity.CitizenInsurancePlan;
import com.insurance.repository.CitizenInsuranceRepository;
import com.insurance.utils.AppConstants;

@Component
public class DataLoader implements CommandLineRunner {

	
	private CitizenInsuranceRepository repo;
	
	 public DataLoader(CitizenInsuranceRepository repo) {
	        this.repo = repo;
	    }

	@Override
	public void run(String... args) throws Exception {

		// deleting existing data to avoid duplicate data insertion
		 repo.deleteAll();
		// cash type data
		CitizenInsurancePlan c1 = new CitizenInsurancePlan();
		c1.setCitizenName("Steve");
		c1.setCitizenNumber(955376890l);
		c1.setGender(AppConstants.MALE);
		c1.setPlanName(AppConstants.CASH);
		c1.setPlanStatus(AppConstants.APPROVED);
		c1.setStartDate(LocalDate.now());
		c1.setEndDate(LocalDate.now().plusMonths(6));
		
		CitizenInsurancePlan c2 = new CitizenInsurancePlan();
		c2.setCitizenName("Smith");
		c2.setCitizenNumber(954676890l);
		c2.setGender(AppConstants.MALE);
		c2.setPlanName(AppConstants.CASH);
		c2.setPlanStatus(AppConstants.DENAIL);
		c2.setDenialReason(AppConstants.HIGH_PAYING_EMPLOYEE);
		
		CitizenInsurancePlan c3 = new CitizenInsurancePlan();
		c3.setCitizenName("Jessika");
		c3.setCitizenNumber(954576890l);
		c3.setGender(AppConstants.FEMALE);
		c3.setPlanName(AppConstants.CASH);
		c3.setPlanStatus(AppConstants.TERMINATED);
		c3.setStartDate(LocalDate.now().minusMonths(4));
		c3.setEndDate(LocalDate.now().plusMonths(6));
		c3.setBenefitAmount(5000D);
		c3.setTerminationReason("Got employed");
		c3.setTermintionDate(LocalDate.now());
		
		CitizenInsurancePlan c4 = new CitizenInsurancePlan();
		c4.setCitizenName("Leela");
		c4.setCitizenNumber(954373890l);
		c4.setGender(AppConstants.FEMALE);
		c4.setPlanName(AppConstants.CASH);
		c4.setPlanStatus(AppConstants.APPROVED);
		c4.setStartDate(LocalDate.now().minusMonths(2));
		c4.setEndDate(LocalDate.now().plusMonths(4));
		c4.setBenefitAmount(6000D);
		
		// Food type data
		CitizenInsurancePlan c5 = new CitizenInsurancePlan();
		c5.setCitizenName("Harsh");
		c5.setCitizenNumber(954376898l);
		c5.setGender(AppConstants.MALE);
		c5.setPlanName(AppConstants.FOOD);
		c5.setPlanStatus(AppConstants.APPROVED);
		c5.setStartDate(LocalDate.now());
		c5.setEndDate(LocalDate.now().plusMonths(6));
		c5.setBenefitAmount(4000D);
		
		CitizenInsurancePlan c6 = new CitizenInsurancePlan();
		c6.setCitizenName("Harshi");
		c6.setCitizenNumber(954376590l);
		c6.setGender(AppConstants.FEMALE);
		c6.setPlanName(AppConstants.CASH);
		c6.setPlanStatus(AppConstants.DENAIL);
		c6.setDenialReason(AppConstants.HIGH_PAYING_EMPLOYEE);
		
		CitizenInsurancePlan c7 = new CitizenInsurancePlan();
		c7.setCitizenName("Daven");
		c7.setCitizenNumber(994376890l);
		c7.setGender(AppConstants.MALE);
		c7.setPlanName(AppConstants.FOOD);
		c7.setPlanStatus(AppConstants.TERMINATED);
		c7.setStartDate(LocalDate.now().minusMonths(4));
		c7.setEndDate(LocalDate.now().plusMonths(6));
		c7.setBenefitAmount(5000D);
		c7.setTerminationReason(AppConstants.GOT_EMPLOYED);
		c7.setTermintionDate(LocalDate.now());
		
		CitizenInsurancePlan c8 = new CitizenInsurancePlan();
		c8.setCitizenName("Aleena");
		c8.setCitizenNumber(954386890l);
		c8.setGender(AppConstants.FEMALE);
		c8.setPlanName(AppConstants.FOOD);
		c8.setPlanStatus(AppConstants.APPROVED);
		c8.setStartDate(LocalDate.now().minusMonths(2));
		c8.setEndDate(LocalDate.now().plusMonths(4));
		c8.setBenefitAmount(5500D);
		
		// Medical type data
		CitizenInsurancePlan c9 = new CitizenInsurancePlan();
		c9.setCitizenName("Docker");
		c9.setCitizenNumber(954076898l);
		c9.setGender(AppConstants.MALE);
		c9.setPlanName(AppConstants.MEDICAL);
		c9.setPlanStatus(AppConstants.APPROVED);
		c9.setStartDate(LocalDate.now());
		c9.setEndDate(LocalDate.now().plusMonths(6));
		c9.setBenefitAmount(4000D);
		
		CitizenInsurancePlan c10 = new CitizenInsurancePlan();
		c10.setCitizenName("Jayant");
		c10.setCitizenNumber(954376590l);
		c10.setGender(AppConstants.MALE);
		c10.setPlanName(AppConstants.MEDICAL);
		c10.setPlanStatus(AppConstants.DENAIL);
		c10.setDenialReason(AppConstants.HIGH_PAYING_EMPLOYEE);
		
		CitizenInsurancePlan c11 = new CitizenInsurancePlan();
		c11.setCitizenName("Justine");
		c11.setCitizenNumber(999376890l);
		c11.setGender(AppConstants.MALE);
		c11.setPlanName(AppConstants.MEDICAL);
		c11.setPlanStatus(AppConstants.TERMINATED);
		c11.setStartDate(LocalDate.now().minusMonths(4));
		c11.setEndDate(LocalDate.now().plusMonths(6));
		c11.setBenefitAmount(5000D);
		c11.setTerminationReason(AppConstants.GOT_EMPLOYED);
		c11.setTermintionDate(LocalDate.now());
		
		CitizenInsurancePlan c12 = new CitizenInsurancePlan();
		c12.setCitizenName("Katha");
		c12.setCitizenNumber(955386890l);
		c12.setGender(AppConstants.FEMALE);
		c12.setPlanName(AppConstants.MEDICAL);
		c12.setPlanStatus(AppConstants.APPROVED);
		c12.setStartDate(LocalDate.now().minusMonths(2));
		c12.setEndDate(LocalDate.now().plusMonths(4));
		c12.setBenefitAmount(5550D);
		
		// Medical type data
		CitizenInsurancePlan c13 = new CitizenInsurancePlan();
		c13.setCitizenName("Kaven");
		c13.setCitizenNumber(954076898l);
		c13.setGender(AppConstants.MALE);
		c13.setPlanName(AppConstants.EMPLOYMENT);
		c13.setPlanStatus(AppConstants.APPROVED);
		c13.setStartDate(LocalDate.now());
		c13.setEndDate(LocalDate.now().plusMonths(6));
		c13.setBenefitAmount(4000D);
		
		CitizenInsurancePlan c14 = new CitizenInsurancePlan();
		c14.setCitizenName("Mira");
		c14.setCitizenNumber(954376590l);
		c14.setGender(AppConstants.FEMALE);
		c14.setPlanName(AppConstants.EMPLOYMENT);
		c14.setPlanStatus(AppConstants.DENAIL);
		c14.setDenialReason(AppConstants.HIGH_PAYING_EMPLOYEE);
		
		CitizenInsurancePlan c15 = new CitizenInsurancePlan();
		c15.setCitizenName("Catheline");
		c15.setCitizenNumber(999379890l);
		c15.setGender(AppConstants.FEMALE);
		c15.setPlanName(AppConstants.EMPLOYMENT);
		c15.setPlanStatus(AppConstants.TERMINATED);
		c15.setStartDate(LocalDate.now().minusMonths(4));
		c15.setEndDate(LocalDate.now().plusMonths(6));
		c15.setBenefitAmount(5000D);
		c15.setTerminationReason(AppConstants.GOT_EMPLOYED);
		c15.setTermintionDate(LocalDate.now());
		
		CitizenInsurancePlan c16 = new CitizenInsurancePlan();
		c16.setCitizenName("Binith");
		c16.setCitizenNumber(955386090l);
		c16.setGender(AppConstants.MALE);
		c16.setPlanName(AppConstants.EMPLOYMENT);
		c16.setPlanStatus(AppConstants.APPROVED);
		c16.setStartDate(LocalDate.now().minusMonths(2));
		c16.setEndDate(LocalDate.now().plusMonths(4));
		c16.setBenefitAmount(5550D);
		
		List<CitizenInsurancePlan> list = Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10,c11, c12, c13, c14, c15,
				c16);
		
		repo.saveAll(list);

	}

}
