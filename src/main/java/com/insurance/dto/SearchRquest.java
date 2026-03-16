package com.insurance.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SearchRquest {

	private String planName;
	private String planStatus;
	private String gender;
	private LocalDate startDate;
	private LocalDate endDate;

	
}
