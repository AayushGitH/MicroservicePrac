package micro.user.service.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import micro.user.service.dao.UserRepository;
import micro.user.service.entities.Hotel;
import micro.user.service.entities.Rating;
import micro.user.service.entities.User;
import micro.user.service.exceptions.ResourceNotFoundException;
import micro.user.service.external.services.HotelService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;

	private Logger logger = LoggerFactory.getLogger(UserService.class);

	@Override
	public User saveUser(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public User getUser(String id) {
		// Fetch user from the database
		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User is not present with the given id ..."));

		// Fetch rating of the above user from RATING SERVICE
		// http://localhost:8083/ratings/users/daecfb5a-9c95-4a13-91fc-f54fadaa0be3
		Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(),
				Rating[].class);
//		logger.info("{}", ratings);
		List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

		List<Rating> ratinglist = ratings.stream().map(
				rating -> {
					// API call to hotel service to get the hotel
					// http://localhost:8082/hotels/f9248eb3-7590-4e61-bcd8-de6cd30f5136
					// Can use getForObject also to get the hotel
					
					// I way (Using rest template)
					// ResponseEntity<Hotel> entity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
					// Hotel hotel = entity.getBody();
					
					// IIway(Using feign client)
					Hotel hotel = hotelService.getHotel(rating.getHotelId());
					
					// Set the hotel to rating
					rating.setHotel(hotel);
					
					// Return the rating
					return rating;
				}
		).collect(Collectors.toList());

		user.setRatings(ratinglist);
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}

}
