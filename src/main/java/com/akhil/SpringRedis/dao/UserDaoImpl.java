package com.akhil.SpringRedis.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.akhil.SpringRedis.model.Response;
import com.akhil.SpringRedis.model.User;

@Component
public class UserDaoImpl implements UserDao{
	
	@SuppressWarnings("unused")
	@Autowired
	private RedisTemplate<String, User> redisTemplate;
	
	@SuppressWarnings("rawtypes")
	private HashOperations hashOperations;
	
	public UserDaoImpl(RedisTemplate<String, User> redisTemplate) {
		super();
		this.redisTemplate = redisTemplate;
		hashOperations = redisTemplate.opsForHash();
	}

	public static final String HASH_KEY = "USER";
	

	@SuppressWarnings("unchecked")
	@Override
	public Response saveUser(User user) {
		hashOperations.put(HASH_KEY, user.getId(), user);
		Response response = new Response();
		response.setMessage("user saved successfully");
		response.setStatusCode(200);
		
		return response;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Long, User> getAllUsers() {
		return hashOperations.entries(HASH_KEY);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response deleteUser(long id) {
		hashOperations.delete(HASH_KEY, id);
		Response response = new Response();
		response.setMessage("user deleted successfully");
		response.setStatusCode(200);
		
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUserById(long id) {
		
		System.out.println("from dao");
		
		return (User)hashOperations.get(HASH_KEY, id);
	}

	@Override
	public Response updateUser(User user) {
		
		saveUser(user);
		Response response = new Response();
		response.setMessage("user saved successfully");
		response.setStatusCode(200);
		
		return response;
	}
	
	

}
