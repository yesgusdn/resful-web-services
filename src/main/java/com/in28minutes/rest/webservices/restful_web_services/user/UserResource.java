package com.in28minutes.rest.webservices.restful_web_services.user;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {
	
	private UserDaoService service;
	
	public UserResource(UserDaoService service) {
		super();
		this.service = service;
	}

	// Get /users
	@GetMapping(path="/users")
	public List<User> retrieveAllUsers(){
		return service.findAll();
	}
	
	// Get /users/1
	@GetMapping(path="/users/{id}")
	public User retrieveUser(@PathVariable int id){
		User user = service.findOne(id);
		
		if(user == null)
			throw new UserNotFoundException("id:" + id);
		
		return user;
	}
	
	// POST /users
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		
		// location - /users/4  => /users, user.getId()
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
							.path("/{id}")
							.buildAndExpand(savedUser.getId())
							.toUri();
		// 201  
		return ResponseEntity.created(location).build();
	}
	
	// DELETE
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		service.deleteById(id);
	}
	
}
