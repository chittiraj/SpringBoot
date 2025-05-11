package com.dl.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.EqualsAndHashCode.Include;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CreateNewLead {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id ;
	
	@NotEmpty(message = "name is mandatory")
	@Size(min = 3, max = 30, message = "Name must be greater than 3 digits")
	private String name;
	
	@NotNull(message = "CC can't be null")
	@Positive(message = "Country code can be only positive")
	private Long cc;
	
	@NotNull(message = "Contact number mandatory")
	@Digits(integer = 10, fraction = 0, message = "Contact number must contain exactly 10 digits")
	private Long contactNo;
	
	@NotEmpty(message = "Email is mandatory")
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Invalid email format")
	private String email;
	
	@Digits(fraction = 0, integer = 5)
	@Min(value = 10000, message = "Fee cannot be less than 9999")
	@Max(value = 30000, message = "Fee cannot be greater than 30001" )
	@NotNull(message = "Fee cannot be null")
	private Double feeCoated;
	
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
