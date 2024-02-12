package micro.user.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import micro.user.service.entities.User;

public interface UserRepository extends JpaRepository<User, String>{

}
