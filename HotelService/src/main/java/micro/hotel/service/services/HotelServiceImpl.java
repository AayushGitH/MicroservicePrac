package micro.hotel.service.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import micro.hotel.service.dao.HotelRepository;
import micro.hotel.service.entities.Hotel;
import micro.hotel.service.exception.ResourceNotFoundException;

@Service
public class HotelServiceImpl implements HotelService{
	
	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public Hotel saveHotel(Hotel hotel) {
		String random = UUID.randomUUID().toString();
		hotel.setHotelId(random);
		return this.hotelRepository.save(hotel);
	}

	@Override
	public Hotel getHotel(String id) {
		return this.hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel with given id is not in the server ..."));
	}

	@Override
	public List<Hotel> getAllHotels() {
		return this.hotelRepository.findAll();
	}

}

