package com.in28minutes.rest.webservices.restful_web_services.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.webservices.restful_web_services.jpa.PostRepository;
import com.in28minutes.rest.webservices.restful_web_services.jpa.UserRepository;
import com.in28minutes.rest.webservices.restful_web_services.post.Post;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {
	
	private UserDaoService service;
	private UserRepository userRepository;
	private PostRepository postRepository;
	
	public UserJpaResource(UserDaoService service, UserRepository userRepository, PostRepository postRepository) {
		super();
		this.service = service;
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}

	// Get /jpa/users
	@GetMapping(path="/jpa/users")
	public List<User> retrieveAllUsers(){
		return userRepository.findAll();
	}
	
	// Get /jpa/users/1
	@GetMapping(path="/jpa/users/{id}")
	public User retrieveUser(@PathVariable int id){
		Optional<User> user = userRepository.findById(id);
		
		if(user.isEmpty()) throw new UserNotFoundException("id:" + id);
		
		return user.get();
	}
	
	// POST /jpa/users
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		
		// location - /jpa/users/4  => /jpa/users, user.getId()
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
							.path("/{id}")
							.buildAndExpand(savedUser.getId())
							.toUri();
		// 201  
		return ResponseEntity.created(location).build();
	}
	
	// DELETE
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	@GetMapping("/jpa/posts")
	public List<Post> retrievePost() {
		List<Post> posts = postRepository.findAll();
		return posts;
	}
	
	@GetMapping("/jpa/{id}/posts")
	public List<Post> retrieveUserPost(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		
		if(user.isEmpty()) throw new UserNotFoundException("id:" + id);
				
		return user.get().getPosts();
	}
	
	@PostMapping("/jpa/{id}/posts")
	public ResponseEntity<Post> createPost(@PathVariable int id, @Valid @RequestBody Post post) {
		Optional<User> user = userRepository.findById(id);
		
		if(user.isEmpty()) throw new UserNotFoundException("id:" + id);
		
		post.setUser(user.get());
		
		Post savedPost = postRepository.save(post);
		
		// location - /jpa/users/4  => /jpa/users, user.getId()
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
							.path("/{id}")
							.buildAndExpand(savedPost.getId())
							.toUri();
		// 201  
		return ResponseEntity.created(location).build();		
	}
}
