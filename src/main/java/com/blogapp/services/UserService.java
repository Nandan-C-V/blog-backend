package com.blogapp.services;

import java.util.List;

import com.blogapp.payloads.LoginDto;
import com.blogapp.payloads.UserDto;

public interface UserService {

	UserDto registerNewUser(UserDto user);
	String login(LoginDto loginDto);	
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Long userId);
	UserDto getuserById(Long userId);
	UserDto getuserByUsernameOrEmail(String name);
	UserDto getuserByEmail(String email);

	List<UserDto> getAllUsers();
	void deleteUser(Long userId);
}
