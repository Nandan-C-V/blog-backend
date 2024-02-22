package com.blogapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.blogapp.entities.User;


public interface UserRepo extends JpaRepository<User, Long>{

	 	Optional<User> findByUsername(String username);
	 	Optional<User> findByEmail(String email);
	    Boolean existsByEmail(String email);

	    Optional<User> findByUsernameOrEmail(String username, String email);

	    boolean existsByUsername(String username);



 
   
}
