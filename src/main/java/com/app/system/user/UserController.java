package com.app.system.user;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<User> getUser(@PathVariable long id) {
		
		User user = userService.getUser(id);
		
		if(user != null) {
			return ResponseEntity.ok(user);
		}
		else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	
	@PostMapping("/add")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		User addedUser = userService.addUser(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(addedUser);
		
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable long id) {
		
		User updatedUser = userService.updateUser(user, id);
		
		if(updatedUser != null) {
			return ResponseEntity.ok(updatedUser);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable long id) {
		
		boolean userDeleted = userService.deleteUser(id);
		if(userDeleted) {
			return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

}