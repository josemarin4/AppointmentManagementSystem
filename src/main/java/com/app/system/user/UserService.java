package com.app.system.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	private final UserRepository userRepo;
	
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public User addUser(User user) {
		
		String username = user.getUsername();
		String email = user.getEmail();
		
		if(userRepo.findByEmail(email) != null) {
			throw new IllegalArgumentException("Email already in use.");
		}
		
		if(userRepo.findByUsername(username) != null) {
			throw new IllegalArgumentException("Username already taken.");
		}
		
		userRepo.save(user);
		return user;
	}
	
	public User getUser(long id) {
		
		User user = userRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("User not found."));
		
		return user;
	}
	
	public User updateUser(User user, long id) {
		
		User currUser = userRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("User not found"));
		
		currUser.setId(id);
		currUser.setEmail(user.getEmail());
		currUser.setPassword(user.getPassword());
		currUser.setUsername(user.getUsername());
		
		userRepo.save(currUser);
		
		user.clearPassword();
		return currUser;
	}
	
	public boolean deleteUser(long id) {
		
		if(!userRepo.existsById(id)) {
			throw new IllegalArgumentException("User not found");
		}
		
		userRepo.deleteById(id);
		return true;
	}

}
