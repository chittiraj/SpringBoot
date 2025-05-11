package com.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.todo.exception.UserNotFoundException;
import com.todo.model.User;
import com.todo.repo.UserRepository;

import jakarta.validation.Valid;

@Validated
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	// add tutorial
	public User addTutorial(@Valid User user) {
		return userRepository.save(user);
	}

	// get All tutorials
	public List<User> getAllTutorial() {
		return userRepository.findAll();
	}

	// edit tutorial
	public User editTutorial(User user) {
		return userRepository.save(user);
	}

	// delete tutorial
	public void deleteUserByid(Integer id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
		} else {
			throw new UserNotFoundException("User Id " + id + " is not found ");
		}
	}

	// delete all tutorials
	public void deleteAllUser() {
		if (!userRepository.findAll().isEmpty()) {
			userRepository.deleteAll();
		} else {
			throw new UserNotFoundException("DB is already empty");
		}

	}

}
