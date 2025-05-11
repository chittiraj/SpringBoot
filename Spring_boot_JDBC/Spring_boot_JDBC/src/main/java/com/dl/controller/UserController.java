package com.dl.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dl.model.User;
import com.dl.repository.UserRepository;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	
	private UserRepository userRepository;
	
	@PostMapping("/create")
	public ResponseEntity<String> createTable(){
		userRepository.create();
		String ResponseMessage = "Table created successfully";
		return ResponseEntity.ok(ResponseMessage);
	}
	
	@PostMapping("/createUser")
	public ResponseEntity<User> createUser(@RequestBody User user){
		userRepository.save(user);
		System.out.println("Added success");
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/findByUser/{id}")
	public ResponseEntity<User> getuserById(@PathVariable long id){
		User user = userRepository.findByAll(id);
		System.out.println("user id:" + id);
		return ResponseEntity.ok(user);
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User userdetails){
		User user = userRepository.findByAll(id);
		user.setName(userdetails.getName());
		user.setEmail(userdetails.getEmail());
		userRepository.update(user);
		return ResponseEntity.ok(user);
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable long id){
		userRepository.delete(id);
		String ResponseMessage = "User deleted succesfully";
		return ResponseEntity.ok(ResponseMessage);
		
	}
	
	@GetMapping()
	public ResponseEntity<List<User>> getAllUser(){
		List<User> all = userRepository.findAll();
		return ResponseEntity.ok(all);
		
	}
	
	
	
	
	
	
	

}
