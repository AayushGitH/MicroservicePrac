package micro.hotel.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import micro.hotel.service.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String>{

}
