package com.insurance.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="CITIZEN_INSURANCE_PLAN")
@Data
public class CitizenInsurancePlan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer citizenId;
	private String citizenName;
	private Long citizenNumber;
	private String gender;
	private String planName;
	private String planStatus;
	private LocalDate startDate; 
	private LocalDate endDate;
	private Integer benefitAmount;
	private String denialReason;
	private String terminationReason;
	private LocalDate termintionDate;
	


}
