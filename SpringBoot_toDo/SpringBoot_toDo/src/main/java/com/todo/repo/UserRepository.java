package com.todo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
