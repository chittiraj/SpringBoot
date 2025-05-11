package com.dl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dl.exception.EmailAlreadyExistsException;
import com.dl.exception.LeadDeletionException;
import com.dl.exception.LeadNotfoundException;
import com.dl.model.CreateNewLead;
import com.dl.service.LeadService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/leads")
public class LeadController {
	
	@Autowired
	LeadService leadService ;

	public LeadController(LeadService leadService) {
		this.leadService = leadService;
	}
	//get all leads
	@GetMapping("/getAllLeads")
	public List<CreateNewLead> getAllLeads(){
		return leadService.getAllLeads();
	}
	//find by id 
	@GetMapping("/{id}")
	public ResponseEntity<CreateNewLead> getLeadByid(@PathVariable Integer id){
		try {
			CreateNewLead lead = leadService.getLeadById(id);
			return ResponseEntity.ok(lead);
		}
		catch (LeadNotfoundException e) {
			throw e ;
		}
		
	}
	//for creating the lead
	@PostMapping("/createLeads")
	public ResponseEntity<?> createLead(@Valid @RequestBody CreateNewLead createNewLead){
		try {
			CreateNewLead saveLead = leadService.createLead(createNewLead);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveLead);
		}
		catch (EmailAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error in occured");
		}
		
	}
	//update the lead
	@PutMapping("/updateLead")
	public CreateNewLead updateLead(@Valid @RequestBody CreateNewLead createNewLead){
		return leadService.updateLead(createNewLead);
	}
	//count all leads
	@GetMapping("/users/count")
	public long countLeads() {
		return leadService.countUser();
	}
	//count the lead status by id 
	@GetMapping("/{status}/LeadStatus")
	public ResponseEntity<Map<String, Object>> getCountAndOrderByStatus(@PathVariable("status") CreateNewLead.LeadStatus leadStatus){
		List<CreateNewLead> orders = leadService.findOrderByStatus(leadStatus);
		long count = orders.size();
		HashMap<String, Object> response = new HashMap<>();
		response.put("count", count);
		response.put("orders", orders);
		return ResponseEntity.ok(response);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteLead(@PathVariable Integer id) {
		try {
			leadService.deleteLead(id);
			return ResponseEntity.ok("Lead with id " + id + " deleted successfully") ;
		} catch (Exception e) {
			throw new LeadDeletionException("Error deleting wth id OR id is not found in database");
		}
	}

}
