package com.akhil.SpringRedis.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.akhil.SpringRedis.dao.UserDao;
import com.akhil.SpringRedis.model.Response;
import com.akhil.SpringRedis.model.User;

@Service
@EnableCaching
public class UserService {
	
	@Autowired
	private UserDao dao;
	
	public Response saveUser(User user) {
		
		return dao.saveUser(user);
	}
	
	public Map<Long, User> getAllUsers(){
		return dao.getAllUsers();
	}
	
	@Cacheable(key = "#id", value = "USER")
	public User getUserById(long id) {
		return dao.getUserById(id);
	}
	
	public Response updateUser(User user) {
		return dao.updateUser(user);
	}
	
	@CacheEvict(key = "#id", value = "USER")
	public Response deleteUser(long id) {
		return dao.deleteUser(id);
	}

}
