package com.blogapp.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogapp.config.AppConstants;
import com.blogapp.entities.Role;
import com.blogapp.entities.User;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.payloads.LoginDto;
import com.blogapp.payloads.UserDto;
import com.blogapp.repositories.RoleRepo;
import com.blogapp.repositories.UserRepo;
import com.blogapp.security.JwtTokenProvider;
import com.blogapp.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	  public UserServiceImpl(
	           JwtTokenProvider jwtTokenProvider,
	            UserRepo userRepository,
	            PasswordEncoder passwordEncoder,
	            AuthenticationManager authenticationManager) {
	        this.authenticationManager = authenticationManager;
	        this.userRepository = userRepository;
	        this.passwordEncoder = passwordEncoder;
	        this.jwtTokenProvider = jwtTokenProvider;
	    }
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		this.userRepo.save(user);
		return this.UserDto(user);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Long userId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","Id",userId));
	
		
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		user.setUsername(userDto.getUsername());
		User updateUser=this.userRepo.save(user);
		
		return this.UserDto(updateUser);
	}

	@Override
	public UserDto getuserById(Long userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","Id",userId));
		
		// TODO Auto-generated method stub
		 return this.UserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users=this.userRepo.findAll();
		List<UserDto> userDtos=users.stream().map(user->this.UserDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","Id",userId));
		this.userRepo.delete(user);
	}
//	private User dtoToUser(UserDto userDto) {
//		User user=new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
//		return user;
//	}
	
	
//	Mapping using Model Mapper
	private User dtoToUser(UserDto userDto) {
		User user=modelMapper.map(userDto,User.class);
		
		return user;
	}
//	private UserDto UserDto(User user) {
//		UserDto userDto=new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
//		return userDto;
//	}
	private UserDto UserDto(User user) {
		UserDto userDto=modelMapper.map(user, UserDto.class);
		return userDto;
	}
	private AuthenticationManager authenticationManager;
    private UserRepo userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private RoleRepo roleRepo;
   

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

	@Override
	public com.blogapp.payloads.UserDto registerNewUser(com.blogapp.payloads.UserDto userDto) {
		// TODO Auto-generated method stub
		User user = this.modelMapper.map(userDto, User.class);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(role);
		User newUser=this.userRepo.save(user);
		
		
		
		
		return this.modelMapper.map(newUser, UserDto.class);
	}

	@Override
	public com.blogapp.payloads.UserDto getuserByUsernameOrEmail(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.blogapp.payloads.UserDto getuserByEmail(String email) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("user","Id",0L));
		
		// TODO Auto-generated method stub
		 return this.UserDto(user);
		
	}

	

}
