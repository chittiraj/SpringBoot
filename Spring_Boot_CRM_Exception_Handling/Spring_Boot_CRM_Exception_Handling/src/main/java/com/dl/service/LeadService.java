package com.dl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.dl.exception.EmailAlreadyExistsException;
import com.dl.exception.LeadNotfoundException;
import com.dl.model.CreateNewLead;
import com.dl.repository.LeadRepository;

import jakarta.validation.Valid;
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

	// find by id
	public CreateNewLead getLeadById(Integer id) {
		return leadRepository.findById(id)
				.orElseThrow(() -> new LeadNotfoundException("Lead with id " + id + " Not found."));

	}

	// Create New Lead
	public CreateNewLead createLead( CreateNewLead createNewLead) {
		if (leadRepository.existsByEmail(createNewLead.getEmail())) {
			throw new EmailAlreadyExistsException("Email Already Exists: " + createNewLead.getEmail());
		}
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
		if(leadRepository.existsById(id)) {
			leadRepository.deleteById(id);
		}
		else {
			throw new LeadNotfoundException("Lead with ID" + id + " Not found");
		}
		
	}

}
