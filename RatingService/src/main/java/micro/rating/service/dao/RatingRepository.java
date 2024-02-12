package micro.rating.service.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import micro.rating.service.entities.Rating;

public interface RatingRepository extends MongoRepository<Rating, String>{
	// Custom finder methods
	List<Rating> findByUserId(String userId);
	List<Rating> findByHotelId(String hotelId);
}
