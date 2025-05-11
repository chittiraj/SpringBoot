package com.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.model.User;
import com.todo.service.UserService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/tutorial")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	@Autowired
	private UserService userService;

	// http://localhost:9091/tutorial/addTutorial
	// add tutorial
	@PostMapping("/addTutorial")
	public ResponseEntity<User> addTutorial(@Valid @RequestBody User user) {
		User tutorial = userService.addTutorial(user);
		return ResponseEntity.ok(tutorial);
	}

	// http://localhost:9091/tutorial/editTutorial
	// edit tutorial
	@PutMapping("/editTutorial")
	public User editTutorial(@Valid @RequestBody User user) {
		return userService.editTutorial(user);
	}

	// http://localhost:9091/tutorial/getAllTutorials
	// get all tutorials
	@GetMapping("/getAllTutorials")
	public List<User> getAllTutorial() {
		return userService.getAllTutorial();
	}

	// http://localhost:9091/tutorial/
	// delete tutorial
	@DeleteMapping("/{id}")
	public void deleteUserByid(@PathVariable Integer id) {
		userService.deleteUserByid(id);
	}

	// http://localhost:9091/tutorial/deleteAll
	// delete all tutorials
	@DeleteMapping("/deleteAll")
	public ResponseEntity<String> deleteAllUsers() {
		userService.deleteAllUser();
		return ResponseEntity.ok("deleted all tutroials");
	}
}
