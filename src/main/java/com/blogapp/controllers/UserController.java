package com.blogapp.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.payloads.Apiresponse;
import com.blogapp.payloads.JWTAuthResponse;
import com.blogapp.payloads.LoginDto;
import com.blogapp.payloads.UserDto;
import com.blogapp.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth/")
@CrossOrigin

public class UserController {
	
	/*
	 * post-create user
	 * put-update user
	 * delete-delete user
	 * get-user get
	 * 
	 * 
	 */
	
	@Autowired
	private UserService userService;


	@PostMapping("/user")
	public ResponseEntity<UserDto> createUser( @Valid @RequestBody UserDto userDto){
		UserDto createUserDto=this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}
	
	// update
	@PutMapping("/user/{userId}")
	public ResponseEntity<UserDto> updateUser( @Valid @RequestBody UserDto userDto,@PathVariable("userId") Long uId){
		UserDto updatedUser=this.userService.updateUser(userDto, uId);
		return ResponseEntity.ok(updatedUser);
	}
	//delete user
//	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/user/{userId}")
	public ResponseEntity<Apiresponse> deleteUser(@PathVariable("userId") Long uId) {
		this.userService.deleteUser(uId);
		return new  ResponseEntity<Apiresponse>(new Apiresponse("user deleted successfully",true),HttpStatus.OK);
	}
	//get single user
	@GetMapping("/user")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	//get single user
		@GetMapping("/user/{userId}")
		public ResponseEntity<UserDto> getsingleUsers(@PathVariable("userId") Long uId){
			return ResponseEntity.ok(this.userService.getuserById(uId));
		}
	
}
