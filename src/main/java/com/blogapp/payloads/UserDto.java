package com.blogapp.payloads;
import java.util.HashSet;
import java.util.Set;

import com.blogapp.entities.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class UserDto {
		private int id;
		
	
		@NotEmpty
		@Size(min=4,message="Name must contain minimum 4 characters" )
		private String username;
		
		@Email
		@NotEmpty
		private String email;
		
	
		
		@Size(min=4,max=10,message="password must contain minimum 4 maximum 10 characters" )
		private String password;
	
		@NotEmpty
		@Size(min=3,message="About should contain atleast 3 characters" )
		private String about;

		@JsonIgnore
		private Set<RoleDto>roles=new HashSet<>();
		
		@JsonIgnore
		public String getPassword() {
			return this.password;
		}
		
		@JsonProperty
		public void setPassword(String password) {
			this.password=password;
		}
}

