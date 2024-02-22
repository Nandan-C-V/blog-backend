package com.blogapp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blogapp.config.AppConstants;
import com.blogapp.entities.Role;
import com.blogapp.repositories.RoleRepo;
import com.cloudinary.Cloudinary;

import io.jsonwebtoken.lang.Arrays;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class BlogAppApisApplication  implements CommandLineRunner{

	@Autowired
	private RoleRepo repo;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}

    @Bean
    ModelMapper modelMapper() {
		return new ModelMapper();
	}
    
    @Bean
    public Cloudinary getCloudinary() {
    	Map config=new HashMap<>();
    	config.put("cloud_name","");
    	config.put("api_key","");
    	config.put("api_secret","");
    	config.put("secure","");
    	return new Cloudinary(config);
    }
    
    
    
    
    
    

	@Override
	public void run(String... args) throws Exception {
		 try {
			Role role1=new Role(AppConstants.ADMIN_USER,"ROLE_ADMIN");
			Role role2=new Role(AppConstants.NORMAL_USER,"ROLE_NORMAL");
		
			List<Role> roles=List.of(role1,role2);
			List<Role> result = this.repo.saveAll(roles);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error");
		}
		
	}

	
	
	
}
