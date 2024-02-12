package micro.user.service.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import micro.user.service.entities.User;
import micro.user.service.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	Logger logger = LoggerFactory.getLogger(UserController.class);

	// Save a user
	@PostMapping("/save")
	public ResponseEntity<?> saveUser(@RequestBody User user){
		String random = UUID.randomUUID().toString();
		user.setUserId(random);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.userService.saveUser(user));
	}
	
	// Get a single user
	int retryCount=1;
	@GetMapping("/{userId}")
//	@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallBack")
//	@Retry(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallBack")
	@RateLimiter(name="userRateLimiter", fallbackMethod = "ratingHotelFallBack")
	public ResponseEntity<User> getUser(@PathVariable("userId") String userId){
		logger.info("Retry count : {}",retryCount);
		retryCount++;
		return ResponseEntity.ok(this.userService.getUser(userId));
	}
	
	// Get all users
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	// Fallback method
	public ResponseEntity<User> ratingHotelFallBack(String userId,Exception ex){
		logger.info("Fallback is executed because service is down: " , ex.getMessage());
		User user = User.builder().userId("124").name("Dummy").email("Dummy@gmail.com").about("Dummy description about the dummy user").build();
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
}
