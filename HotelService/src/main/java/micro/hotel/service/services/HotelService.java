package micro.hotel.service.services;

import java.util.List;

import micro.hotel.service.entities.Hotel;

public interface HotelService {

	// Create hotel
	Hotel saveHotel(Hotel hotel);
	
	// Get a single hotel
	Hotel getHotel(String id);
	
	// Get all hotels
	List<Hotel> getAllHotels();
}
