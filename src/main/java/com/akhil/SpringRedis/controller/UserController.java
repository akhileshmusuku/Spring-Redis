package com.akhil.SpringRedis.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akhil.SpringRedis.model.Response;
import com.akhil.SpringRedis.model.User;
import com.akhil.SpringRedis.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/save", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Response> saveUser(@RequestBody User user){
		
		Response resp = userService.saveUser(user);
		return new ResponseEntity<Response>(resp, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/getUsers")
	public ResponseEntity<Map<Long, User>>getAllUsers(){
		Map<Long, User> map =  userService.getAllUsers();
		return new ResponseEntity<Map<Long, User>>(map, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable long id){
		User user = userService.getUserById(id);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PutMapping(value = "/update")
	public ResponseEntity<Response> updateUser(@RequestBody User user){
		Response resp = userService.updateUser(user);
		return new ResponseEntity<Response>(resp, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Response> deleteUser(@PathVariable long id){
		Response resp = userService.deleteUser(id);
		return new ResponseEntity<Response>(resp, HttpStatus.OK);
		
	}
	

}
