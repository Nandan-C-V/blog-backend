package com.blogapp.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.blogapp.payloads.CategoryDto;
import com.blogapp.payloads.UserDto;
import com.blogapp.services.CategoryService;
import com.blogapp.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth/")
@CrossOrigin
public class CategoryController {
	
	/*
	 * post-create user
	 * put-update user
	 * delete-delete user
	 * get-user get
	 */
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/categories")
	public ResponseEntity<CategoryDto> createCategory( @Valid @RequestBody CategoryDto cDto){
		CategoryDto createUserDto=this.categoryService.createCategory(cDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}
	
	// update
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory( @Valid @RequestBody CategoryDto cDto,@PathVariable("catId") Integer cId){
		CategoryDto updatedCategory=this.categoryService.updateCategory(cDto,cId);
		return ResponseEntity.ok(updatedCategory);
	}
	//delete user
	@DeleteMapping("/{catId}")
	public ResponseEntity<Apiresponse> deleteUser(@PathVariable("catId") Integer uId) {
		this.categoryService.deleteCategory(uId);
		return new  ResponseEntity<Apiresponse>(new Apiresponse("user deleted successfully",true),HttpStatus.OK);
	}
	//get single user
	@GetMapping("/category")
	public ResponseEntity<List<CategoryDto>> getAllUsers(){
		return ResponseEntity.ok(this.categoryService.getAllCategory());
	}
	//get single user
		@GetMapping("/{catId}")
		public ResponseEntity<CategoryDto> getsingleUsers(@PathVariable("catId") Integer uId){
			return ResponseEntity.ok(this.categoryService.getCategory(uId));
		}
	
}
