package micro.user.service.services;

import java.util.List;

import micro.user.service.entities.User;

public interface UserService {
	
	// Add user
	User saveUser(User user);
	
	// Get a single user
	User getUser(String id);
	
	// Get all users
	List<User> getAllUsers();
}
