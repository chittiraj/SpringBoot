package com.dl.repository;


import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.dl.model.User;


public class UserRowMapper implements RowMapper<User> {
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		User user = new User();
		user.setId(rs.getLong("id"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		
		return user;
	}
}
