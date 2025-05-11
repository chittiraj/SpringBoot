package com.dl.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CreateNewLead {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id ;
	private String name;
	private long cc;
	private long contactNo;
	private String email;
	private double feeCoated;
	
	public CreateNewLead (LeadStatus leadStatus) {
		this.leadStatus = leadStatus;
	}
	
	@Enumerated(EnumType.STRING)
	private LeadStatus leadStatus;
	
	@Getter
	@AllArgsConstructor
	public enum LeadStatus{
		None,
		NotContacted,
		Attempted,
		WarmLead,
		Opportunity,
		AttenededDemo,
		Visited,
		Registered,
		ColdLead
		
	}
	
}
