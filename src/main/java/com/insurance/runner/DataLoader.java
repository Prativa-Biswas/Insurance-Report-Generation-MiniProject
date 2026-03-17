package com.insurance.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.insurance.entity.CitizenInsurancePlan;
import com.insurance.repository.CitizenInsuranceRepository;

@Component
public class DataLoader implements CommandLineRunner {

	@Autowired
	private CitizenInsuranceRepository repo;

	@Override
	public void run(String... args) throws Exception {

		// deleting existing data to avoid duplicate data insertion
		 repo.deleteAll();
		// cash type data
		CitizenInsurancePlan c1 = new CitizenInsurancePlan();
		c1.setCitizenName("Steve");
		c1.setCitizenNumber(955376890l);
		c1.setGender("Male");
		c1.setPlanName("Cash");
		c1.setPlanStatus("Approved");
		c1.setStartDate(LocalDate.now());
		c1.setEndDate(LocalDate.now().plusMonths(6));
		c1.setBenefitAmount(5000);
		
		CitizenInsurancePlan c2 = new CitizenInsurancePlan();
		c2.setCitizenName("Smith");
		c2.setCitizenNumber(954676890l);
		c2.setGender("Male");
		c2.setPlanName("Cash");
		c2.setPlanStatus("Denial");
		c2.setDenialReason("High Paying Employee");
		
		CitizenInsurancePlan c3 = new CitizenInsurancePlan();
		c3.setCitizenName("Jessika");
		c3.setCitizenNumber(954576890l);
		c3.setGender("Female");
		c3.setPlanName("Cash");
		c3.setPlanStatus("Terminated");
		c3.setStartDate(LocalDate.now().minusMonths(4));
		c3.setEndDate(LocalDate.now().plusMonths(6));
		c3.setBenefitAmount(5000);
		c3.setTerminationReason("Got employed");
		c3.setTermintionDate(LocalDate.now());
		
		CitizenInsurancePlan c4 = new CitizenInsurancePlan();
		c4.setCitizenName("Leela");
		c4.setCitizenNumber(954373890l);
		c4.setGender("Female");
		c4.setPlanName("Cash");
		c4.setPlanStatus("Approved");
		c4.setStartDate(LocalDate.now().minusMonths(2));
		c4.setEndDate(LocalDate.now().plusMonths(4));
		c4.setBenefitAmount(6000);
		
		// Food type data
		CitizenInsurancePlan c5 = new CitizenInsurancePlan();
		c5.setCitizenName("Harsh");
		c5.setCitizenNumber(954376898l);
		c5.setGender("Male");
		c5.setPlanName("Food");
		c5.setPlanStatus("Approved");
		c5.setStartDate(LocalDate.now());
		c5.setEndDate(LocalDate.now().plusMonths(6));
		c5.setBenefitAmount(4000);
		
		CitizenInsurancePlan c6 = new CitizenInsurancePlan();
		c6.setCitizenName("Harshi");
		c6.setCitizenNumber(954376590l);
		c6.setGender("Female");
		c6.setPlanName("Cash");
		c6.setPlanStatus("Denial");
		c6.setDenialReason("High Paying Employee");
		
		CitizenInsurancePlan c7 = new CitizenInsurancePlan();
		c7.setCitizenName("Daven");
		c7.setCitizenNumber(994376890l);
		c7.setGender("Male");
		c7.setPlanName("Food");
		c7.setPlanStatus("Terminated");
		c7.setStartDate(LocalDate.now().minusMonths(4));
		c7.setEndDate(LocalDate.now().plusMonths(6));
		c7.setBenefitAmount(5000);
		c7.setTerminationReason("Got employed");
		c7.setTermintionDate(LocalDate.now());
		
		CitizenInsurancePlan c8 = new CitizenInsurancePlan();
		c8.setCitizenName("Aleena");
		c8.setCitizenNumber(954386890l);
		c8.setGender("Female");
		c8.setPlanName("Food");
		c8.setPlanStatus("Approved");
		c8.setStartDate(LocalDate.now().minusMonths(2));
		c8.setEndDate(LocalDate.now().plusMonths(4));
		c8.setBenefitAmount(5500);
		
		// Medical type data
		CitizenInsurancePlan c9 = new CitizenInsurancePlan();
		c9.setCitizenName("Docker");
		c9.setCitizenNumber(954076898l);
		c9.setGender("Male");
		c9.setPlanName("Medical");
		c9.setPlanStatus("Approved");
		c9.setStartDate(LocalDate.now());
		c9.setEndDate(LocalDate.now().plusMonths(6));
		c9.setBenefitAmount(4000);
		
		CitizenInsurancePlan c10 = new CitizenInsurancePlan();
		c10.setCitizenName("Jayant");
		c10.setCitizenNumber(954376590l);
		c10.setGender("Male");
		c10.setPlanName("Medical");
		c10.setPlanStatus("Denial");
		c10.setDenialReason("High Paying Employee");
		
		CitizenInsurancePlan c11 = new CitizenInsurancePlan();
		c11.setCitizenName("Justine");
		c11.setCitizenNumber(999376890l);
		c11.setGender("Male");
		c11.setPlanName("Medical");
		c11.setPlanStatus("Terminated");
		c11.setStartDate(LocalDate.now().minusMonths(4));
		c11.setEndDate(LocalDate.now().plusMonths(6));
		c11.setBenefitAmount(5000);
		c11.setTerminationReason("Got employed");
		c11.setTermintionDate(LocalDate.now());
		
		CitizenInsurancePlan c12 = new CitizenInsurancePlan();
		c12.setCitizenName("Katha");
		c12.setCitizenNumber(955386890l);
		c12.setGender("Female");
		c12.setPlanName("Medical");
		c12.setPlanStatus("Approved");
		c12.setStartDate(LocalDate.now().minusMonths(2));
		c12.setEndDate(LocalDate.now().plusMonths(4));
		c12.setBenefitAmount(5550);
		
		// Medical type data
		CitizenInsurancePlan c13 = new CitizenInsurancePlan();
		c13.setCitizenName("Kaven");
		c13.setCitizenNumber(954076898l);
		c13.setGender("Male");
		c13.setPlanName("Employment");
		c13.setPlanStatus("Approved");
		c13.setStartDate(LocalDate.now());
		c13.setEndDate(LocalDate.now().plusMonths(6));
		c13.setBenefitAmount(4000);
		
		CitizenInsurancePlan c14 = new CitizenInsurancePlan();
		c14.setCitizenName("Mira");
		c14.setCitizenNumber(954376590l);
		c14.setGender("Female");
		c14.setPlanName("Employment");
		c14.setPlanStatus("Denial");
		c14.setDenialReason("High Paying Employee");
		
		CitizenInsurancePlan c15 = new CitizenInsurancePlan();
		c15.setCitizenName("Catheline");
		c15.setCitizenNumber(999379890l);
		c15.setGender("Female");
		c15.setPlanName("Employment");
		c15.setPlanStatus("Terminated");
		c15.setStartDate(LocalDate.now().minusMonths(4));
		c15.setEndDate(LocalDate.now().plusMonths(6));
		c15.setBenefitAmount(5000);
		c15.setTerminationReason("Got employed");
		c15.setTermintionDate(LocalDate.now());
		
		CitizenInsurancePlan c16 = new CitizenInsurancePlan();
		c16.setCitizenName("Binith");
		c16.setCitizenNumber(955386090l);
		c16.setGender("Male");
		c16.setPlanName("Employment");
		c16.setPlanStatus("Approved");
		c16.setStartDate(LocalDate.now().minusMonths(2));
		c16.setEndDate(LocalDate.now().plusMonths(4));
		c16.setBenefitAmount(5550);
		
		List<CitizenInsurancePlan> list = Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10,c11, c12, c13, c14, c15,
				c16);
		
		repo.saveAll(list);

	}

}
