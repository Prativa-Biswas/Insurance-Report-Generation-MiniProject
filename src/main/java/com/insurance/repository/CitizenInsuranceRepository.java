package com.insurance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.insurance.entity.CitizenInsurancePlan;

public interface CitizenInsuranceRepository extends JpaRepository<CitizenInsurancePlan, Integer> {

	@Query("select distinct planName from CitizenInsurancePlan ")
	 public List<String> getPlanNames();
	
	
	@Query("select distinct planStatus from CitizenInsurancePlan ")
	 public List<String> getPlanSatus();
			
}
