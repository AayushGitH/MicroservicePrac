package micro.rating.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import micro.rating.service.entities.Rating;
import micro.rating.service.services.RatingService;

@RequestMapping("/ratings")
@RestController
public class RatingController {

	@Autowired
	private RatingService ratingService;

	// Create rating
	@PostMapping("/save")
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.ratingService.createRating(rating));
	}

	// Get all ratings
	@GetMapping("/all")
	public ResponseEntity<List<Rating>> getRatings() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.ratingService.getRatings());
	}

	// Get ratings by User id
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.ratingService.getRatingsByUserId(userId));
	}

	// Get ratings by Hotel id
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.ratingService.getRatingsByHotelId(hotelId));
	}
}
