package com.dl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

//For call the swagger in site. use this link in below.
//http://localhost:8080/swagger-ui/index.html
@Tag(name = "CRM Application Rest APIs for the Resources", description = "- getAllLeads, getLeadById, createLead, updateLead, getCountAndOrderByStatus, deleteLead")
@Validated
@RestController
@RequestMapping("/api/leads")
public class LeadController {

	@Autowired
	LeadService leadService;

	public LeadController(LeadService leadService) {
		this.leadService = leadService;
	}

	// get all leads
	@Operation(summary = "GET All LEADS - REST API", description = "Get All leads REST API is used to get all leads for the API")
	@ApiResponse(responseCode = "200", description = "HTTP Status is OK")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/getAllLeads")
	public List<CreateNewLead> getAllLeads() {
		return leadService.getAllLeads();
	}

	// find by id
	@Operation(summary = "GET BY ID - REST API", description = "Get by ID is used to get, user given id same to database for the API")
	@ApiResponse(responseCode = "200", description = "HTTP Status is OK")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/{id}")
	public ResponseEntity<CreateNewLead> getLeadByid(@PathVariable Integer id) {
		try {
			CreateNewLead lead = leadService.getLeadById(id);
			return ResponseEntity.ok(lead);
		} catch (LeadNotfoundException e) {
			throw e;
		}

	}

	// for creating the lead
	@Operation(summary = "CREATE LEAD - REST API", description = "Create lead is used to post the new lead for the API")
	@ApiResponse(responseCode = "200", description = "HTTP Status is OK")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@PostMapping("/createLeads")
	public ResponseEntity<?> createLead(@Valid @RequestBody CreateNewLead createNewLead) {
		try {
			CreateNewLead saveLead = leadService.createLead(createNewLead);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveLead);
		} catch (EmailAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error in occured");
		}

	}

	// update the lead
	@Operation(summary = "UPDATE LEAD - REST API", description = "Update lead is used to put the details for the API")
	@ApiResponse(responseCode = "200", description = "HTTP Status is OK")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@PutMapping("/updateLead")
	public CreateNewLead updateLead(@Valid @RequestBody CreateNewLead createNewLead) {
		return leadService.updateLead(createNewLead);
	}

	// count all leads
	@Operation(summary = "COUNT LEAD - REST API", description = "Count leads REST API is used to count the leads for the API")
	@ApiResponse(responseCode = "200", description = "HTTP Status is OK")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/users/count")
	public long countLeads() {
		return leadService.countUser();
	}

	// count the lead status by id
	@Operation(summary = "GET LEAD STATUS - REST API", description = "Get Lead status is used to check the status to get the matched status for the API")
	@ApiResponse(responseCode = "200", description = "HTTP Status is OK")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/{status}/LeadStatus")
	public ResponseEntity<Map<String, Object>> getCountAndOrderByStatus(
			@PathVariable("status") CreateNewLead.LeadStatus leadStatus) {
		List<CreateNewLead> orders = leadService.findOrderByStatus(leadStatus);
		long count = orders.size();
		HashMap<String, Object> response = new HashMap<>();
		response.put("count", count);
		response.put("orders", orders);
		return ResponseEntity.ok(response);
	}

	// delete the lead from the DataBase
	@Operation(summary = "DELETE LEAD - REST API", description = "Delete lead is used to delete the lead from the DB For the API")
	@ApiResponse(responseCode = "200", description = "HTTP Status is OK")
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteLead(@PathVariable Integer id) {
		try {
			leadService.deleteLead(id);
			return ResponseEntity.ok("Lead with id " + id + " deleted successfully");
		} catch (Exception e) {
			throw new LeadDeletionException("Error deleting wth id OR id is not found in database");
		}
	}

}
