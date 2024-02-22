package com.blogapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.entities.User;
import com.blogapp.payloads.JWTAuthResponse;
import com.blogapp.payloads.LoginDto;
import com.blogapp.payloads.UserDto;
import com.blogapp.repositories.UserRepo;
import com.blogapp.services.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UserRepo repo;
	
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser( @Valid @RequestBody UserDto userDto){
		UserDto createUserDto=this.userService.registerNewUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}

	  @PostMapping("/login")
	    public ResponseEntity<JWTAuthResponse> authenticate(@RequestBody LoginDto loginDto){
	        String token = userService.login(loginDto);
	        
	        UserDto getuserByUsername = this.userService.getuserByEmail(loginDto.getUsernameOrEmail());
	 

	        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
	        jwtAuthResponse.setAccessToken(token);
	        jwtAuthResponse.setUser(getuserByUsername);

	        return ResponseEntity.ok(jwtAuthResponse);
	    }
}
