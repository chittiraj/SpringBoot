package com.dl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dl.model.CreateNewLead;
import com.dl.service.LeadService;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

	@Autowired
	LeadService leadService;

	public LeadController(LeadService leadService) {
		this.leadService = leadService;
	}

	// for creating the lead
	@PostMapping("/createLead")
	public CreateNewLead createLead(@RequestBody CreateNewLead createNewLead) {
		return leadService.createLead(createNewLead);
	}

	// get all leads
	@GetMapping("/getAllLeads")
	public List<CreateNewLead> getAllLeads() {
		return leadService.getAllLeads();

	}

	// update the lead
	@PutMapping("/updateLead")
	public CreateNewLead updateLead(@RequestBody CreateNewLead createNewLead) {
		return leadService.updateLead(createNewLead);

	}

	// count all leads
	@GetMapping("/users/count")
	public long countLeads() {
		return leadService.countUser();
	}

	// count the lead status by id
	@GetMapping("/LeadStatus/{status}")
	public ResponseEntity<Map<String, Object>> getCountAndOrderByStatus(
			@PathVariable("status") CreateNewLead.LeadStatus leadStatus) {
		List<CreateNewLead> orders = leadService.findOrderByStatus(leadStatus);
		long count = orders.size();
		HashMap<String, Object> response = new HashMap<>();
		response.put("count", count);
		response.put("orders", orders);
		return ResponseEntity.ok(response);
	}

	// deleting the lead
	@DeleteMapping("/{id}")
	public void deleteLead(@PathVariable Integer id) {
		leadService.deleteLead(id);
	}

}
