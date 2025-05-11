package com.dl.repository;

import java.util.List;

import com.dl.model.User;

public interface UserRepository {
	
	int create();
	int save (User user);
	int update(User user );
	int delete(long id);
	User findByAll(long id );
	List<User> findAll();

}
