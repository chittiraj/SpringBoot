package com.dl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dl.model.CreateNewLead;

public interface LeadRepository  extends JpaRepository<CreateNewLead, Integer>{
	//Create the findByLeadStatus here. so, here we can use in leadService...
	List<CreateNewLead> findByLeadStatus(CreateNewLead.LeadStatus leadStatus);
}
