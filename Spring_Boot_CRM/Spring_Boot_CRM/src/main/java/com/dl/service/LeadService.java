package com.dl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dl.exception.LeadNotfoundException;
import com.dl.model.CreateNewLead;
import com.dl.repository.LeadRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class LeadService {

	@Autowired
	LeadRepository leadRepository;

	// Get All Leads
	public List<CreateNewLead> getAllLeads() {
		return leadRepository.findAll();
	}


	// Create New Lead
	public CreateNewLead createLead(CreateNewLead createNewLead) {
		return leadRepository.save(createNewLead);
	}

	// Update lead
	public CreateNewLead updateLead(CreateNewLead createNewLead) {
		return leadRepository.save(createNewLead);

	}

	// count the status
	public long countUser() {
		return leadRepository.count();
	}

	// Status
	public List<CreateNewLead> findOrderByStatus(CreateNewLead.LeadStatus leadStatus) {
		// findByLeadStatus is not there in hibernate. So, create manually in
		// leadRepository
		return leadRepository.findByLeadStatus(leadStatus);

	}

	// Delete
	public void deleteLead(Integer id) {
		leadRepository.deleteById(id);
	}

}
