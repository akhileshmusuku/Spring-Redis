package com.akhil.SpringRedis.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.akhil.SpringRedis.model.Response;
import com.akhil.SpringRedis.model.User;

@Repository
public interface UserDao {
	
	public Response saveUser(User user);
	public Map<Long, User> getAllUsers();
	public User getUserById(long id);
	public Response updateUser(User user);
	public Response deleteUser(long id);

}
