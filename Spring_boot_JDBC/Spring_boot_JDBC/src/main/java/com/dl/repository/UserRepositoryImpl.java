package com.dl.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dl.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

	
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public int create() {
		
		String qurey = "create table user(id bigint auto_increment primary key, name varchar(255), email varchar(255))";
		
		return jdbcTemplate.update(qurey);
	}
	@Override
	public int save(User user) {
		String  qurey = "insert into user(name, email) values(?,?)";
		return jdbcTemplate.update(qurey, user.getName(), user.getEmail());
	}
	@Override
	public int update(User user) {
		String qurey = "update user set name= ?, email = ? where id = ?";
		return jdbcTemplate.update(qurey, user.getName(), user.getEmail(), user.getId());
	}
	@Override
	public int delete(long id) {
		String qurey = "delete from user where id=?";
		return jdbcTemplate.update(qurey,id);
	}
	@SuppressWarnings("deprecation")
	@Override
	public User findByAll(long id) {
		String qurey = "select * from user where id =?";
		return jdbcTemplate.queryForObject(qurey, new Object[] {id}, new UserRowMapper());
	}
	@Override
	public List<User> findAll() {
		String qurey = "select * from user";
		return jdbcTemplate.query(qurey, new UserRowMapper());
	}

}
