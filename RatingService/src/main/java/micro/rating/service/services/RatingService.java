package micro.rating.service.services;

import java.util.List;

import micro.rating.service.entities.Rating;

public interface RatingService {

	// Create rating
	Rating createRating(Rating rating);
	
	// Get all ratings
	List<Rating> getRatings();
	
	// Get all ratings by userId
	List<Rating> getRatingsByUserId(String userId);
	
	// Get all ratings by hotelId
	List<Rating> getRatingsByHotelId(String hotelId);
}
