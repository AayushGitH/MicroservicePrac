package micro.rating.service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import micro.rating.service.dao.RatingRepository;
import micro.rating.service.entities.Rating;

@Service
public class RatingServiceImpl implements RatingService{
	
	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public Rating createRating(Rating rating) {
		return this.ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getRatings() {
		return this.ratingRepository.findAll();
	}

	@Override
	public List<Rating> getRatingsByUserId(String userId) {
		return this.ratingRepository.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingsByHotelId(String hotelId) {
		return this.ratingRepository.findByHotelId(hotelId);
	}

}
